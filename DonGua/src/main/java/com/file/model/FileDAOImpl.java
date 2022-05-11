package com.file.model;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.utils.SQLUtil;

@Repository
public class FileDAOImpl implements FileDAO {

	// DataSource
	@Autowired
	private DataSource ds;

//	private static DataSource ds = null;
//	static {
//		try {
//			Context ctx = new InitialContext();
//			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/group6");
//		} catch (NamingException ne) {
//			ne.printStackTrace();
//		}
//	}

	// 新增申請者的照片
	private static final String INSERT_FILE_STMT = "INSERT INTO FILE (CARER_ID, FILE_TYPE_NO, FILE_CONTENT) VALUES (?, ?, ?)";

	// (CarerID) 查詢此照護員申請之上傳照片
	private static final String GET_PIC = "SELECT * FROM FILE F LEFT JOIN FILE_ITEM FI ON F.FILE_TYPE_NO = FI.FILE_TYPE_NO WHERE F.CARER_ID = ?";

	// 更新照護員照片
	private static final String UPDATE_CARERMEM_PIC = "UPDATE FILE SET FILE_CONTENT = ? WHERE FILE_TYPE_NO = ? AND CARER_ID = ?";

	@Override
	public void insertApplyFile(FileVO fileVo, InputStream inputStream) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_FILE_STMT);

			pstmt.setInt(1, fileVo.getCarerID());
			pstmt.setString(2, fileVo.getFileTypeNo());
			pstmt.setBlob(3, inputStream);

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	// executeBatch批量更新照片
	@Override
	public int[] insertApplyFileBatch(List<FileVO> fileVoList, List<InputStream> inputStreamList) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int[] result = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_FILE_STMT);
			/** 設定不自動提交，以便於在出現異常的時候資料庫回滾 **/
			con.setAutoCommit(false);

			for (int i = 0; i < fileVoList.size(); i++) {
				FileVO fileVo = fileVoList.get(i);
				InputStream inputStream = inputStreamList.get(i);
				pstmt.setInt(1, fileVo.getCarerID());
				pstmt.setString(2, fileVo.getFileTypeNo());
				pstmt.setBlob(3, inputStream);
				pstmt.addBatch();
			}
			result = pstmt.executeBatch();
			con.commit();
			System.out.println("成功了插入了" + result.length + "行");
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
			if (con != null) {
				try {
					con.rollback();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return result;
	}

	// 更新照護員照片
	@Override
	public int[] updateCarerPic(List<FileVO> fileVoList, List<InputStream> inputStreamList) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int[] result = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_CARERMEM_PIC);
			/** 設定不自動提交，以便於在出現異常的時候資料庫回滾 **/
			con.setAutoCommit(false);

			for (int i = 0; i < fileVoList.size(); i++) {
				FileVO fileVo = fileVoList.get(i);
				InputStream inputStream = inputStreamList.get(i);
				pstmt.setBlob(1, inputStream);
				pstmt.setString(2, fileVo.getFileTypeNo());
				pstmt.setInt(3, fileVo.getCarerID());
				pstmt.addBatch();
			}
			result = pstmt.executeBatch();
			con.commit();
			System.out.println("成功了插入了" + result.length + "行");
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
			if (con != null) {
				try {
					con.rollback();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.setAutoCommit(true);
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return result;

	}

	@Override
	public FileVO findHeadShotByCarerId(Integer carerId) {
		final String GET_HEAD_SHOT = "SELECT `CARER_ID`,`FILE_CONTENT` FROM `FILE` WHERE `FILE_TYPE_NO` = 'P01' AND `CARER_ID` = ?";
		FileVO fileVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(SQLUtil.URL, SQLUtil.USER, SQLUtil.PASSWORD);
			pstmt = con.prepareStatement(GET_HEAD_SHOT);
			pstmt.setInt(1, carerId);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				fileVO = new FileVO();
				Integer carer_Id = rs.getInt("CARER_ID");
				Blob blob = rs.getBlob("FILE_CONTENT");

				InputStream inputStream = blob.getBinaryStream();
				ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
				byte[] buffer = new byte[4096];
				int bytesRead = -1;

				while ((bytesRead = inputStream.read(buffer)) != -1) {
					outputStream.write(buffer, 0, bytesRead);
				}

				byte[] imageBytes = outputStream.toByteArray();
				String fileContent = Base64.getEncoder().encodeToString(imageBytes);

				inputStream.close();
				outputStream.close();

				fileVO.setCarerID(carer_Id);
				fileVO.setFileContentString(fileContent);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			SQLUtil.closeResource(con, pstmt, rs);
		}

		return fileVO;
	}

	@Override
	public List<FileVO> selectAll() {
		final String GET_ALL = "SELECT * FROM `FILE` WHERE `FILE_TYPE_NO` = 'P01' ";
		List<FileVO> list = new ArrayList<FileVO>();
		FileVO fileVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(SQLUtil.URL, SQLUtil.USER, SQLUtil.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				fileVO = new FileVO();
				Integer carer_Id = rs.getInt("CARER_ID");
				Blob blob = rs.getBlob("FILE_CONTENT");

				InputStream inputStream = blob.getBinaryStream();
				ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
				byte[] buffer = new byte[4096];
				int bytesRead = -1;

				while ((bytesRead = inputStream.read(buffer)) != -1) {
					outputStream.write(buffer, 0, bytesRead);
				}

				byte[] imageBytes = outputStream.toByteArray();
				String fileContent = Base64.getEncoder().encodeToString(imageBytes);

				inputStream.close();
				outputStream.close();

				fileVO.setCarerID(carer_Id);
				fileVO.setFileContentString(fileContent);
				list.add(fileVO);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			SQLUtil.closeResource(con, pstmt, rs);
		}

		return list;
	}

	@Override
	public List<FileVO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FileVO> getPic(Integer carerID) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<FileVO> list = new ArrayList<FileVO>();
		;
		FileVO fileVo = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_PIC);
			pstmt.setInt(1, carerID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				fileVo = new FileVO();
				Integer carerId = rs.getInt("CARER_ID");
				String fileTypeNo = rs.getString("FILE_TYPE_NO");
				String fileTypeName = rs.getString("FILE_TYPE_NAME");
				Integer fileID = rs.getInt("FILE_ID");
				Blob blob = rs.getBlob("FILE_CONTENT");

				InputStream inputStream = blob.getBinaryStream();
				ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
				byte[] buffer = new byte[4096];
				int bytesRead = -1;

				while ((bytesRead = inputStream.read(buffer)) != -1) {
					outputStream.write(buffer, 0, bytesRead);
				}

				byte[] imageBytes = outputStream.toByteArray();
				String applyFileContent = Base64.getEncoder().encodeToString(imageBytes);

				inputStream.close();
				outputStream.close();

				fileVo.setCarerID(carerId);
				fileVo.setFileTypeNo(fileTypeNo);
				fileVo.setFileTypeName(fileTypeName);
				fileVo.setApplyFileContent(applyFileContent);
				fileVo.setFileID(fileID);

				list.add(fileVo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			SQLUtil.closeResource(con, pstmt, rs);
		}

		return list;
	}
//	public static void main(String[] args) {
//		FileDAOImpl dao = new FileDAOImpl();
//		List<FileVO> list = dao.getPic(1);
//		for (FileVO vo : list) {
//			System.out.println(vo.getApplyFileContent());
//		}
//	}

}
