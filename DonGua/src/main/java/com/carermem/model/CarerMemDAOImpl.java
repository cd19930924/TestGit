package com.carermem.model;

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
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import com.careskills.model.CareSkillsVO;
import com.file.model.FileVO;
import com.member.model.vo.MemberVO;
import com.utils.CarerSearchUtils;
import com.utils.SQLUtil;
@Repository
public class CarerMemDAOImpl implements CarerMemDAO {

	// DataSource
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/group6");
		} catch (NamingException ne) {
			ne.printStackTrace();
		}
	}

	// 搜尋照護員個人資料
	private static final String SELECT_ONE_CARER = "SELECT * FROM CARER AS C LEFT JOIN BANK AS B ON C.BANK_CODE = B.BANK_CODE WHERE C.CARER_ID = ?";

	// 更新照護員資料
	private static final String UPDATE_CARERMEM_DATA = "UPDATE CARER SET SERVICE_TYPE = ?, SERVICE_DIST_NO = ?, PRICE_HOUR = ?, PRICE_HALFDAY = ?, PRICE_DAY = ?, INTRO = ?, BANK_CODE = ?, BANK_ACCT = ?, UPDATE_TIME = current_timestamp() WHERE CARER_ID = ?";

	@Override
	public long isMemberExist(String memberAccount, String password) throws ClassNotFoundException {
		final String MEM_STATUS = "SELECT * FROM MEMBER WHERE MEM_ACCT = ? and MEM_PWD = ? and MEM_STATUS in (1,2)";
		long memberId = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(SQLUtil.URL, SQLUtil.USER, SQLUtil.PASSWORD);
			pstmt = con.prepareStatement(MEM_STATUS);
			pstmt.setString(1, memberAccount);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				memberId = rs.getLong("MEM_ID");
				return memberId;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLUtil.closeResource(con, pstmt, rs);
		}
		return 0;
	}

	@Override
	public boolean isCarerIdExist(long carerId) throws ClassNotFoundException {
		final String CARER_STATUS = "SELECT * FROM `CARER` WHERE CARER_ID = ?";
		Connection con = null;
		boolean ok = false;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(SQLUtil.URL, SQLUtil.USER, SQLUtil.PASSWORD);
			pstmt = con.prepareStatement(CARER_STATUS);
			pstmt.setLong(1, carerId);
			rs = pstmt.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLUtil.closeResource(con, pstmt, rs);
		}
		return ok;
	}

	@Override
	public boolean activeCarerStatus(String carerAccount) throws ClassNotFoundException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCarerAcctExist(String carerAccount) throws ClassNotFoundException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public CarerMemVO findByCarerId(Integer carerId) {
		final String GET_ONE_CARER = "SELECT *, CASE `SERVICE_TYPE` WHEN 0 THEN '居家照護' WHEN 1 THEN '醫院看護' WHEN 2 THEN '居家看護、醫院看護' END AS `SERVICE_TYPE_STR`,LEFT(`MEM_NAME`,1) AS `SURNAME`, CASE `MEM_GENDER` WHEN 0 THEN '先生' WHEN 1 THEN '小姐' END AS `GENDER` FROM `CARER` LEFT JOIN MEMBER ON MEMBER.MEM_ID = CARER.CARER_ID LEFT JOIN `DISTRICT` ON `CARER`.`SERVICE_DIST_NO` = `DISTRICT`.`DIST_NO` LEFT JOIN `COUNTY` ON `DISTRICT`.`CTY_NO` = `COUNTY`.`CTY_NO` WHERE `CARER_ID` = ? ";

		CarerMemVO carerVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(SQLUtil.URL, SQLUtil.USER, SQLUtil.PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_CARER);
			pstmt.setInt(1, carerId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				carerVO = new CarerMemVO();
				carerVO.setCarerID(rs.getInt("CARER_ID"));
				carerVO.setIntro(rs.getString("INTRO"));
				carerVO.setPriceHour(rs.getDouble("PRICE_HOUR"));
				carerVO.setPriceHalfday(rs.getDouble("PRICE_HALFDAY"));
				carerVO.setPriceDay(rs.getDouble("PRICE_DAY"));
				carerVO.setSearchDist(rs.getString("DIST_NAME"));
				carerVO.setSearchName(rs.getString("SURNAME"));
				carerVO.setSearchGender(rs.getString("GENDER"));
				carerVO.setSearchCounty(rs.getString("CTY_NAME"));
				carerVO.setServiceType(rs.getString("SERVICE_TYPE_STR"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLUtil.closeResource(con, pstmt, rs);
		}
		return carerVO;

	}

	@Override
	public List<CarerMemVO> getAll() {
		final String GET_ALL_CARER = "SELECT * FROM `CARER` order by `CARER_ID` ";
		List<CarerMemVO> list = new ArrayList<>();
		CarerMemVO carerVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(SQLUtil.URL, SQLUtil.USER, SQLUtil.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_CARER);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				carerVO = new CarerMemVO();
				carerVO.setCarerID(rs.getInt("CARER_ID"));
				carerVO.setServiceDistNo(rs.getString("SERVICE_DIST_NO"));
				carerVO.setServiceType(rs.getString("SERVICE_TYPE"));
				carerVO.setIntro(rs.getString("INTRO"));
				carerVO.setPriceHour(rs.getDouble("PRICE_HOUR"));
				carerVO.setPriceHalfday(rs.getDouble("PRICE_HALFDAY"));
				carerVO.setPriceDay(rs.getDouble("PRICE_DAY"));
				list.add(carerVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLUtil.closeResource(con, pstmt, rs);
		}
		return list;
	}

	@Override
	public List<CarerMemVO> getAll(Map<String, String[]> map) {
		List<CarerMemVO> list = new ArrayList<>();
		CarerMemVO carerVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(SQLUtil.URL, SQLUtil.USER, SQLUtil.PASSWORD);
			String searchMultiCarer = "SELECT *, CASE `SERVICE_TYPE` WHEN 0 THEN '居家照護' WHEN 1 THEN '醫院看護' WHEN 2 THEN '居家看護、醫院看護' END AS `SERVICE_TYPE_STR`,LEFT(`MEM_NAME`,1) AS `SURNAME`, CASE `MEM_GENDER` WHEN 0 THEN '先生' WHEN 1 THEN '小姐' END AS `GENDER` FROM `CARER` LEFT JOIN MEMBER ON MEMBER.MEM_ID = CARER.CARER_ID LEFT JOIN `DISTRICT` ON `CARER`.`SERVICE_DIST_NO` = `DISTRICT`.`DIST_NO` LEFT JOIN `COUNTY` ON `DISTRICT`.`CTY_NO` = `COUNTY`.`CTY_NO`"
					+ CarerSearchUtils.getWhereCondition(map);
			pstmt = con.prepareStatement(searchMultiCarer);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				carerVO = new CarerMemVO();
				carerVO.setCarerID(rs.getInt("CARER_ID"));
				carerVO.setSearchDist(rs.getString("DIST_NAME"));
				carerVO.setSearchName(rs.getString("SURNAME"));
				carerVO.setSearchGender(rs.getString("GENDER"));
				carerVO.setSearchCounty(rs.getString("CTY_NAME"));
				carerVO.setServiceType(rs.getString("SERVICE_TYPE_STR"));
				carerVO.setIntro(rs.getString("INTRO"));
				carerVO.setPriceHour(rs.getDouble("PRICE_HOUR"));
				carerVO.setPriceHalfday(rs.getDouble("PRICE_HALFDAY"));
				carerVO.setPriceDay(rs.getDouble("PRICE_DAY"));
				list.add(carerVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLUtil.closeResource(con, pstmt, rs);
		}
		return list;
	}

	@Override
	public void insert(CarerMemVO carerMemVO) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(CarerMemVO carerMemVO) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Integer carerID) {
		// TODO Auto-generated method stub

	}

	@Override
	public MemberVO getCarerInfoByCarerId(Integer carerId) {
		final String GET_NAME_AND_GENDER = "SELECT `MEM_ID`,LEFT(`MEM_NAME`,1) as `MEM_NAME`, CASE `MEM_GENDER` WHEN 0 THEN '先生' WHEN 1 THEN '小姐' END as `MEM_GENDER` FROM `MEMBER` WHERE `MEM_ID` = ? ";
		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(SQLUtil.URL, SQLUtil.USER, SQLUtil.PASSWORD);
			pstmt = con.prepareStatement(GET_NAME_AND_GENDER);
			pstmt.setInt(1, carerId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				memberVO = new MemberVO();
				memberVO.setMemId(rs.getLong("MEM_ID"));
				memberVO.setMemName(rs.getString("MEM_NAME"));
				memberVO.setMemGender(rs.getString("MEM_GENDER"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLUtil.closeResource(con, pstmt, rs);
		}
		return memberVO;
	}

	@Override
	public FileVO getHeadShotByCarerId(Integer carerId) {

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
	public FileVO getCertificationByCarerId(Integer carerId) {
		final String GET_CERTIFI = "select `CARER_ID`, GROUP_CONCAT(FILE_TYPE_NAME) as CERTIFICATION from `V_CARERFILE` group by `CARER_ID` having `CARER_ID` = ? ";
		FileVO fileVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(SQLUtil.URL, SQLUtil.USER, SQLUtil.PASSWORD);
			pstmt = con.prepareStatement(GET_CERTIFI);
			pstmt.setInt(1, carerId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				fileVO = new FileVO();
				fileVO.setCarerID(rs.getInt("CARER_ID"));
				fileVO.setCertification(rs.getString("CERTIFICATION"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLUtil.closeResource(con, pstmt, rs);
		}
		return fileVO;
	}

	@Override
	public CareSkillsVO getSkillsByCarerId(Integer carerId) {
		final String GET_SKILLS = "SELECT * FROM `V_SINGLESKILL` WHERE `CARER_ID` = ? ";

		CareSkillsVO carerSkillsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(SQLUtil.URL, SQLUtil.USER, SQLUtil.PASSWORD);
			pstmt = con.prepareStatement(GET_SKILLS);
			pstmt.setInt(1, carerId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				carerSkillsVO = new CareSkillsVO();
				carerSkillsVO.setCarerID(rs.getInt("CARER_ID"));
				carerSkillsVO.setSkillName(rs.getString("SKILL_NAME"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLUtil.closeResource(con, pstmt, rs);
		}
		return carerSkillsVO;
	}

	// List包證照=>顯示於瀏覽單一照護員
	@Override
	public List<FileVO> findCertiByCarerId(Integer carerId) {
		final String SQL = "SELECT * FROM `FILE` JOIN `FILE_ITEM` ON `FILE`.`FILE_TYPE_NO` = `FILE_ITEM`.`FILE_TYPE_NO` WHERE `FILE`.`FILE_TYPE_NO` LIKE 'C%' AND `CARER_ID` = ? ";
		List<FileVO> list = new ArrayList<>();
		FileVO vo = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(SQLUtil.URL, SQLUtil.USER, SQLUtil.PASSWORD);
			pstmt = con.prepareStatement(SQL);
			pstmt.setInt(1, carerId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				vo = new FileVO();
				vo.setFileTypeNo(rs.getString("FILE_TYPE_NAME"));
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLUtil.closeResource(con, pstmt, rs);
		}
		return list;
	}

	// List包技能=>顯示於瀏覽單一照護員
	@Override
	public List<CareSkillsVO> findCarerSkills(Integer carerId, String skillType) {
		final String SQL = "SELECT * FROM `CARE_SKILLS` JOIN `SKILL` ON `CARE_SKILLS`.`SKILL_NO` = `SKILL`.`SKILL_NO` WHERE `CARER_ID` = ? AND `SKILL_TYPE` = ?";
		List<CareSkillsVO> list = new ArrayList<>();
		CareSkillsVO vo = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(SQLUtil.URL, SQLUtil.USER, SQLUtil.PASSWORD);
			pstmt = con.prepareStatement(SQL);
			pstmt.setInt(1, carerId);
			pstmt.setString(2, skillType);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				vo = new CareSkillsVO();
				vo.setSkillName(rs.getString("SKILL_NAME"));
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLUtil.closeResource(con, pstmt, rs);
		}
		return list;
	}

	// 後臺用:管理照護員
	public List<CarerMemVO> findAllCarer() {
		final String GET_ALL_CARER = "SELECT *,CASE `MEM_GENDER` WHEN 0 THEN '男' WHEN 1 THEN '女' END AS `GENDER`,`CARER`.`CANCEL_COUNT` AS `CARER_CANCEL_COUNT`, CASE `CARER_STATUS` WHEN 0 THEN '暫停服務' WHEN 1 THEN '正常' WHEN 2 THEN '停權' WHEN 3 THEN '黑名單' END AS `CARER_STATUS_STR` FROM `CARER` LEFT JOIN `MEMBER` ON `CARER`.`CARER_ID` = `MEMBER`.`MEM_ID` LEFT JOIN `DISTRICT` ON `CARER`.`SERVICE_DIST_NO` = `DISTRICT`.`DIST_NO` LEFT JOIN `COUNTY` ON `DISTRICT`.`CTY_NO` = `COUNTY`.`CTY_NO`;";
		List<CarerMemVO> list = new ArrayList<>();
		CarerMemVO carerVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(SQLUtil.URL, SQLUtil.USER, SQLUtil.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_CARER);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				carerVO = new CarerMemVO();
				carerVO.setCarerID(rs.getInt("CARER_ID"));
				carerVO.setCarerAcct(rs.getString("CARER_ACCT"));
				carerVO.setSearchName(rs.getString("MEM_NAME"));
				carerVO.setPhoneNumber(rs.getString("MEM_PHONE"));
				carerVO.setEmailAccount(rs.getString("MEM_EMAIL"));
				carerVO.setSearchCounty(rs.getString("CTY_NAME"));
				carerVO.setServiceDistNo(rs.getString("DIST_NAME"));
				carerVO.setCancelCount(rs.getInt("CARER_CANCEL_COUNT"));
				carerVO.setCarerStatus(rs.getString("CARER_STATUS_STR"));
				carerVO.setServiceType(rs.getString("SERVICE_TYPE"));
				carerVO.setIntro(rs.getString("INTRO"));
				carerVO.setPriceHour(rs.getDouble("PRICE_HOUR"));
				carerVO.setPriceHalfday(rs.getDouble("PRICE_HALFDAY"));
				carerVO.setPriceDay(rs.getDouble("PRICE_DAY"));
				carerVO.setBankCode(rs.getString("BANK_CODE"));
				carerVO.setBankAcct(rs.getString("BANK_ACCT"));
				carerVO.setSearchGender(rs.getString("GENDER"));
				carerVO.setSearchAge(rs.getString("MEM_AGE"));
				carerVO.setSearchAddr(rs.getString("MEM_ADDR"));
				list.add(carerVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLUtil.closeResource(con, pstmt, rs);
		}
		return list;
	}

	// 後臺用：更新狀態
	@Override
	public void updateMemStatus(CarerMemVO vo) {
		final String UPDATE = "UPDATE `CARER` set `CARER_STATUS`=?, `UPDATE_TIME`=? where `CARER_ID` = ?";
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(SQLUtil.URL, SQLUtil.USER, SQLUtil.PASSWORD);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, vo.getCarerStatus());
			pstmt.setTimestamp(2, vo.getUpdateTime());
			pstmt.setInt(3, vo.getCarerID());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLUtil.closeResource(con, pstmt, null);
		}

	}

	@Override
	public CarerMemVO selectCarer(Integer id) {
		final String SELECT_CARER = "select * from CARER left join MEMBER on CARER_ID = MEM_ID where CARER_ID = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CarerMemVO vo = new CarerMemVO();

		try {
			con = DriverManager.getConnection(SQLUtil.URL, SQLUtil.USER, SQLUtil.PASSWORD);
			pstmt = con.prepareStatement(SELECT_CARER);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				vo.setCarerID(id);
				vo.setSearchName(rs.getString("MEM_NAME"));
				vo.setSearchGender(rs.getString("MEM_GENDER"));
				vo.setSearchAge(rs.getString("MEM_AGE"));
				vo.setPhoneNumber(rs.getString("MEM_PHONE"));
				vo.setEmailAccount(rs.getString("MEM_EMAIL"));

				System.out.println("查詢單一照護員：" + vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLUtil.closeResource(con, pstmt, rs);
		}

		return vo;
	}

	@Override
	public CarerMemVO selectOneCarer(Integer carerID) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		CarerMemVO carerMemVo = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(SELECT_ONE_CARER);
			pstmt.setInt(1, carerID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				carerMemVo = new CarerMemVO();

				carerMemVo.setCarerID(rs.getInt("CARER_ID"));
				carerMemVo.setCarerAcct(rs.getString("CARER_ACCT"));
				carerMemVo.setCarerPwd(rs.getString("CARER_PWD"));
				carerMemVo.setServiceDistNo(rs.getString("SERVICE_DIST_NO"));
				carerMemVo.setBankCode(rs.getString("BANK_CODE"));
				carerMemVo.setBankAcct(rs.getString("BANK_ACCT"));
				carerMemVo.setServiceType(rs.getString("SERVICE_TYPE"));
				carerMemVo.setIntro(rs.getString("INTRO"));
				carerMemVo.setPriceHour(rs.getDouble("PRICE_HOUR"));
				carerMemVo.setPriceHalfday(rs.getDouble("PRICE_HALFDAY"));
				carerMemVo.setPriceDay(rs.getDouble("PRICE_DAY"));
				carerMemVo.setCancelCount(rs.getInt("CANCEL_COUNT"));
				carerMemVo.setCarerStatus(rs.getString("CARER_STATUS"));
				carerMemVo.setBankName(rs.getString("BANK_NAME"));

			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
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
		return carerMemVo;
	}

	@Override
	public void updateCarerMemData(String serviceType, String serviceDistNo, Double priceHour, Double priceHalfday,
			Double priceDay, String intro, String bankCode, String bankAcct, Integer carerID) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_CARERMEM_DATA);

			pstmt.setString(1, serviceType);
			pstmt.setString(2, serviceDistNo);
			pstmt.setDouble(3, priceHour);
			pstmt.setDouble(4, priceHalfday);
			pstmt.setDouble(5, priceDay);
			pstmt.setString(6, intro);
			pstmt.setString(7, bankCode);
			pstmt.setString(8, bankAcct);
			pstmt.setInt(9, carerID);
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

	public static void main(String[] args) {
		CarerMemDAOImpl dao = new CarerMemDAOImpl();
//		List<FileVO> list = dao.findCertiByCarerId(1);
//		for (FileVO vo:list) {
//			System.out.println(vo.getFileTypeNo());
//		}

//		List<CareSkillsVO> list = dao.findCarerSkills(1, "0");
//		for (CareSkillsVO vo:list) {
//			System.out.println(vo.getSkillsName());
//		}

		List<CarerMemVO> list = dao.findAllCarer();
		for (CarerMemVO vo : list) {
			System.out.println(vo.getCancelCount());
			System.out.println(vo.getCarerAcct());
			System.out.println(vo.getSearchName());
			System.out.println(vo.getCarerID());
			System.out.println(vo.getPhoneNumber());
			System.out.println(vo.getEmailAccount());
			System.out.println(vo.getServiceDistNo());
			System.out.println(vo.getSearchCounty());
			System.out.println(vo.getBankCode());
			System.out.println(vo.getBankAcct());
			System.out.println(vo.getSearchGender());
			System.out.println(vo.getSearchAddr());
		}
	}
}
