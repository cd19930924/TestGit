package com.carerequest.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.careapply.model.CareApplyVO;
import com.requesttab.model.RequestTabVO;
import com.utils.RequestSearchUtils;
import com.utils.SQLUtil;

@Repository
public class CareRequestDAOImpl implements CareRequestDAO {

	static {
		try {
			Class.forName(SQLUtil.DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static final String SELECT_ID = "select last_insert_id()";
	private static final String SELECT_REQUEST = "select * from CARE_REQUEST where REQUEST_ID = ?";
//	private static final String SELECT_ALL_REQUEST = "select * from CARE_REQUEST where STATUS != 1";
	private static final String SELECT_ALL_MEM_REQUEST = "select * from CARE_REQUEST where MEM_ID = ? and STATUS != 1";
	private static final String INSERT_REQUEST = "insert into CARE_REQUEST "
			+ "(MEM_ID, PATIENT_NAME, PATIENT_GENDER, PATIENT_AGE, PATIENT_ADDR, START_TIME, END_TIME, SERVICE_TYPE, NOTE, STATUS) "
			+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?, '2')";
	private static final String UPDATE_REQUEST = "update CARE_REQUEST "
			+ "set SERVICE_TYPE = ?, PATIENT_ADDR = ?, START_TIME = ?, END_TIME = ?, NOTE = ?, UPDATE_TIME = current_timestamp() where REQUEST_ID = ?";
	private static final String UPDATE_STATUS = "update CARE_REQUEST set STATUS = ?, UPDATE_TIME = current_timestamp() where REQUEST_ID = ?";

	@Override
	public CareRequestVO select(Integer id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CareRequestVO vo = new CareRequestVO();

		try {
			con = DriverManager.getConnection(SQLUtil.URL, SQLUtil.USER, SQLUtil.PASSWORD);
			pstmt = con.prepareStatement(SELECT_REQUEST);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				vo.setRequestId(id);
				vo.setMemId(rs.getInt("MEM_ID"));
				vo.setPatientName(rs.getString("PATIENT_NAME"));
				vo.setPatientGender(rs.getString("PATIENT_GENDER"));
				vo.setPatientAge(rs.getInt("PATIENT_AGE"));
				vo.setPatientAddr(rs.getString("PATIENT_ADDR"));
				vo.setStartTime(rs.getTimestamp("START_TIME"));
				vo.setEndTime(rs.getTimestamp("END_TIME"));
				vo.setServiceType(rs.getString("SERVICE_TYPE"));
				vo.setNote(rs.getString("NOTE"));
				vo.setStatus(rs.getString("STATUS"));
				vo.setCreateTime(rs.getTimestamp("CREATE_TIME"));
				vo.setUpdateTime(rs.getTimestamp("UPDATE_TIME"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLUtil.closeResource(con, pstmt, rs);
		}

		return vo;
	}

//	@Override
//	public List<CareRequestPVO> selectAll() {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		List<CareRequestPVO> list = new ArrayList<>();
//
//		try {
//			con = DriverManager.getConnection(SQLUtil.URL, SQLUtil.USER, SQLUtil.PASSWORD);
//			pstmt = con.prepareStatement(SELECT_ALL_REQUEST);
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				CareRequestPVO pvo = new CareRequestPVO();
//
//				pvo.setRequestId(rs.getInt("REQUEST_ID"));
//				pvo.setPatientName(rs.getString("PATIENT_NAME"));
//				pvo.setStartTime(rs.getTimestamp("START_TIME"));
//				pvo.setEndTime(rs.getTimestamp("END_TIME"));
//				pvo.setStatus(rs.getString("STATUS"));
//
//				list.add(pvo);

//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			SQLUtil.closeResource(con, pstmt, rs);
//		}
//
//		return list;
//	}

	@Override
	public List<CareRequestPVO> selectAll(Integer id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<CareRequestPVO> list = new ArrayList<>();

		try {
			con = DriverManager.getConnection(SQLUtil.URL, SQLUtil.USER, SQLUtil.PASSWORD);
			pstmt = con.prepareStatement(SELECT_ALL_MEM_REQUEST);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				CareRequestPVO pvo = new CareRequestPVO();

				pvo.setRequestId(rs.getInt("REQUEST_ID"));
				pvo.setPatientName(rs.getString("PATIENT_NAME"));
				pvo.setStartTime(rs.getTimestamp("START_TIME"));
				pvo.setEndTime(rs.getTimestamp("END_TIME"));
				pvo.setStatus(rs.getString("STATUS"));

				list.add(pvo);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLUtil.closeResource(con, pstmt, rs);
		}

		return list;
	}

	@Override
	public int insert(CareRequestVO vo) {
		Connection con = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(SQLUtil.URL, SQLUtil.USER, SQLUtil.PASSWORD);
			pstmt = con.prepareStatement(INSERT_REQUEST);
			stmt = con.createStatement();

			pstmt.setInt(1, vo.getMemId());
			pstmt.setString(2, vo.getPatientName());
			pstmt.setString(3, vo.getPatientGender());
			pstmt.setInt(4, vo.getPatientAge());
			pstmt.setString(5, vo.getPatientAddr());
			pstmt.setTimestamp(6, vo.getStartTime());
			pstmt.setTimestamp(7, vo.getEndTime());
			pstmt.setString(8, vo.getServiceType());
			pstmt.setString(9, vo.getNote());

			pstmt.executeUpdate();

			rs = stmt.executeQuery(SELECT_ID);

			if (rs.next()) {
				int id = rs.getInt(1);

				vo.setRequestId(id);


				return id;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();

				} catch (SQLException se) {
					se.printStackTrace();
				}
			}

			SQLUtil.closeResource(con, pstmt, rs);
		}

		return 0;
	}

	@Override
	public void update(CareRequestVO vo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(SQLUtil.URL, SQLUtil.USER, SQLUtil.PASSWORD);
			pstmt = con.prepareStatement(UPDATE_REQUEST);

			pstmt.setString(1, vo.getServiceType());
			pstmt.setString(2, vo.getPatientAddr());
			pstmt.setTimestamp(3, vo.getStartTime());
			pstmt.setTimestamp(4, vo.getEndTime());
			pstmt.setString(5, vo.getNote());
			pstmt.setInt(6, vo.getRequestId());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLUtil.closeResource(con, pstmt, null);
		}
	}

	@Override
	public void updateStatus(Integer id, String type) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(SQLUtil.URL, SQLUtil.USER, SQLUtil.PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STATUS);

			pstmt.setString(1, type);
			pstmt.setInt(2, id);

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLUtil.closeResource(con, pstmt, null);
		}
	}

	@Override
	public List<CareRequestVO> getAll(Map<String, String[]> map) {
		List<CareRequestVO> list = new ArrayList<CareRequestVO>();
		CareRequestVO careRequestVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(SQLUtil.URL, SQLUtil.USER, SQLUtil.PASSWORD);
			String searchMultiRequest = "SELECT `REQUEST_ID`, LEFT(`PATIENT_NAME`,1) as `PATIENT_LNAME`, CASE `PATIENT_GENDER` WHEN 0 THEN '先生' WHEN 1 THEN '小姐' END as `PATIENT_GENDER`,`PATIENT_AGE`,CASE\r\n"
					+ "	  WHEN INSTR(`PATIENT_ADDR`,'醫院') >0 THEN LEFT(`PATIENT_ADDR`,instr(`PATIENT_ADDR`,'院'))\r\n"
					+ "      WHEN INSTR(`PATIENT_ADDR`,'區') >0 THEN LEFT(`PATIENT_ADDR`,instr(`PATIENT_ADDR`,'區'))\r\n"
					+ "      WHEN INSTR(`PATIENT_ADDR`,'縣') =3  THEN \r\n" + "		case \r\n"
					+ "			when instr(`PATIENT_ADDR`,'鄉')>0 THEN left(`PATIENT_ADDR`,instr(`PATIENT_ADDR`,'鄉'))\r\n"
					+ "			when instr(`PATIENT_ADDR`,'鎮')>0 THEN left(`PATIENT_ADDR`,instr(`PATIENT_ADDR`,'鎮'))\r\n"
					+ "			when instr(`PATIENT_ADDR`,'市')>0 THEN left(`PATIENT_ADDR`,instr(`PATIENT_ADDR`,'市'))\r\n"
					+ "		end\r\n"
					+ "   END  AS `DIST`,`START_TIME`, `END_TIME`, CASE `SERVICE_TYPE` WHEN 0 THEN '居家照護' WHEN 1 THEN '醫院看護' END as `SERVICE_TYPE`, `NOTE` FROM `CARE_REQUEST` where `STATUS` = 2 "
					+ RequestSearchUtils.getWhereCondition(map);

			pstmt = con.prepareStatement(searchMultiRequest);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				careRequestVO = new CareRequestVO();
				careRequestVO.setRequestId(rs.getInt("REQUEST_ID"));
				careRequestVO.setPatientName(rs.getString("PATIENT_LNAME"));
				careRequestVO.setPatientGender(rs.getString("PATIENT_GENDER"));
				careRequestVO.setPatientAge(rs.getInt("PATIENT_AGE"));
				careRequestVO.setPatientAddr(rs.getString("DIST"));
				careRequestVO.setStartTime(rs.getTimestamp("START_TIME"));
				careRequestVO.setEndTime(rs.getTimestamp("END_TIME"));
				careRequestVO.setServiceType(rs.getString("SERVICE_TYPE"));
				careRequestVO.setNote(rs.getString("NOTE"));
				// TODO : CARER APPLY : JOIN APPLY TABLE
				list.add(careRequestVO);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLUtil.closeResource(con, pstmt, rs);
		}
		return list;
	}

	// 後臺用
	@Override
	public List<CareRequestMVO> selectAll() {
		final String ALL = "SELECT `REQUEST_ID`,`MEM_ID`,`PATIENT_NAME`, CASE `PATIENT_GENDER` WHEN 0 THEN '男' WHEN 1 THEN '女' END as `PATIENT_GENDER`,`PATIENT_AGE` ,`PATIENT_ADDR`,`START_TIME`, `END_TIME`, CASE `SERVICE_TYPE` WHEN 0 THEN '居家照護' WHEN 1 THEN '醫院看護' END as `SERVICE_TYPE`, `NOTE`, CASE `STATUS` WHEN 0 THEN '取消' WHEN 2 THEN '應徵中' END as `STATUS`, `CREATE_TIME`,`UPDATE_TIME`,SUBSTRING(`PATIENT_ADDR`,1,6) AS `SHORT_ADDR`, SUBSTRING(`START_TIME`,6,11) AS `SHORT_START`, SUBSTRING(`END_TIME`,6,11) AS `SHORT_END` FROM `CARE_REQUEST` where `STATUS` in (0, 2)";
		List<CareRequestMVO> list = new ArrayList<CareRequestMVO>();
		CareRequestMVO vo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(SQLUtil.URL, SQLUtil.USER, SQLUtil.PASSWORD);
			pstmt = con.prepareStatement(ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				vo = new CareRequestMVO();
				vo.setRequestId(rs.getInt("REQUEST_ID"));
				vo.setMemId(rs.getInt("MEM_ID"));
				vo.setPatientName(rs.getString("PATIENT_NAME"));
				vo.setPatientGender(rs.getString("PATIENT_GENDER"));
				vo.setPatientAge(rs.getInt("PATIENT_AGE"));
				vo.setPatientAddr(rs.getString("PATIENT_ADDR"));
				vo.setStartTime(rs.getTimestamp("START_TIME"));
				vo.setEndTime(rs.getTimestamp("END_TIME"));
				vo.setShortPatientAddr(rs.getString("SHORT_ADDR"));
				vo.setShortStartTime(rs.getString("SHORT_START"));
				vo.setShortEndTime(rs.getString("SHORT_END"));
				vo.setServiceType(rs.getString("SERVICE_TYPE"));
				vo.setNote(rs.getString("NOTE"));
				vo.setStatus(rs.getString("STATUS"));
				vo.setCreateTime(rs.getTimestamp("CREATE_TIME"));
				vo.setUpdateTime(rs.getTimestamp("UPDATE_TIME"));
				list.add(vo);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLUtil.closeResource(con, pstmt, rs);
		}

		return list;
	}

	@Override
	public RequestTabVO findTabsStrByRequestId(Integer requestId) {
		final String REQUEST_TAB = "SELECT rt.`REQUEST_ID`, GROUP_CONCAT(st.`SERVICE_TAB_NAME` separator '、' ) as `SERVICE_TAB_NO` FROM `REQUEST_TAB` rt LEFT JOIN `SERVICE_TAB` st ON rt.`SERVICE_TAB_NO` = st.`SERVICE_TAB_NO` group by rt.`REQUEST_ID` having rt.`REQUEST_ID` = ?;";
		RequestTabVO vo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(SQLUtil.URL, SQLUtil.USER, SQLUtil.PASSWORD);
			pstmt = con.prepareStatement(REQUEST_TAB);
			pstmt.setInt(1, requestId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				vo = new RequestTabVO();
				vo.setRequestId(rs.getInt("REQUEST_ID"));
				vo.setServiceTabNo(rs.getString("SERVICE_TAB_NO"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLUtil.closeResource(con, pstmt, rs);
		}
		return vo;
	}

	@Override
	public String findTabsStrInStringTypeByRequestId(Integer requestId) {
		final String REQUEST_TAB = "SELECT GROUP_CONCAT(st.`SERVICE_TAB_NAME` separator '、' ) as `SERVICE_TAB_NO` FROM `REQUEST_TAB` rt LEFT JOIN `SERVICE_TAB` st ON rt.`SERVICE_TAB_NO` = st.`SERVICE_TAB_NO` group by rt.`REQUEST_ID` having rt.`REQUEST_ID` = ?;";
		String tabsStr = "";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(SQLUtil.URL, SQLUtil.USER, SQLUtil.PASSWORD);
			pstmt = con.prepareStatement(REQUEST_TAB);
			pstmt.setInt(1, requestId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				tabsStr = new String(rs.getString("SERVICE_TAB_NO"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLUtil.closeResource(con, pstmt, rs);
		}
		return tabsStr;
	}

	@Override
	public Boolean isApplied(Integer requestId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RequestTabVO> findTabsByRequestId(Integer requestId) {
		final String REQUEST_TAB = "SELECT rt.`REQUEST_ID`, CASE st.`SERVICE_NO` WHEN '01' THEN '一般照護服務' WHEN '02' THEN '進階照護服務' END as `SERVICE_NAME` , st.`SERVICE_TAB_NAME` FROM `REQUEST_TAB` rt LEFT JOIN `SERVICE_TAB` st ON rt.`SERVICE_TAB_NO` = st.`SERVICE_TAB_NO` where rt.`REQUEST_ID` = ?";
		List<RequestTabVO> list = new ArrayList<RequestTabVO>();
		RequestTabVO requestTabVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(SQLUtil.URL, SQLUtil.USER, SQLUtil.PASSWORD);
			pstmt = con.prepareStatement(REQUEST_TAB);
			pstmt.setInt(1, requestId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				requestTabVO = new RequestTabVO();
				requestTabVO.setRequestId(rs.getInt("REQUEST_ID"));
				requestTabVO.setServiceNo(rs.getString("SERVICE_NAME"));
				requestTabVO.setServiceTabNo(rs.getString("SERVICE_TAB_NAME"));

				list.add(requestTabVO);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLUtil.closeResource(con, pstmt, rs);
		}

		return list;
	}

	@Override
	public List<RequestTabVO> findTabsOfService(Integer requestId, String serviceNo) {
		final String TAB = "SELECT *  FROM `REQUEST_TAB` rt LEFT JOIN `SERVICE_TAB` st ON rt.`SERVICE_TAB_NO` = st.`SERVICE_TAB_NO` WHERE `REQUEST_ID` = ? AND `SERVICE_NO` = ? ";
		List<RequestTabVO> list = new ArrayList<RequestTabVO>();
		RequestTabVO requestTabVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(SQLUtil.URL, SQLUtil.USER, SQLUtil.PASSWORD);
			pstmt = con.prepareStatement(TAB);
			pstmt.setInt(1, requestId);
			pstmt.setString(2, serviceNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				requestTabVO = new RequestTabVO();
				requestTabVO.setServiceTabNo(rs.getString("SERVICE_TAB_NAME"));
				list.add(requestTabVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLUtil.closeResource(con, pstmt, rs);
		}
		return list;
	}

	@Override
	public CareRequestVO findByRequestId(Integer requestId) {
		final String GET_ONE_REQ = "SELECT `MEM_ID`,`REQUEST_ID`, LEFT(`PATIENT_NAME`,1) as `PATIENT_LNAME`, CASE `PATIENT_GENDER` WHEN 0 THEN '先生' WHEN 1 THEN '小姐' END as `PATIENT_GENDER`,`PATIENT_AGE`, CASE\r\n"
				+ "	  WHEN INSTR(`PATIENT_ADDR`,'醫院') >0 THEN LEFT(`PATIENT_ADDR`,instr(`PATIENT_ADDR`,'院'))\r\n"
				+ "      WHEN INSTR(`PATIENT_ADDR`,'區') >0 THEN LEFT(`PATIENT_ADDR`,instr(`PATIENT_ADDR`,'區'))\r\n"
				+ "      WHEN INSTR(`PATIENT_ADDR`,'縣') =3  THEN \r\n" + "		case \r\n"
				+ "			when instr(`PATIENT_ADDR`,'鄉')>0 THEN left(`PATIENT_ADDR`,instr(`PATIENT_ADDR`,'鄉'))\r\n"
				+ "			when instr(`PATIENT_ADDR`,'鎮')>0 THEN left(`PATIENT_ADDR`,instr(`PATIENT_ADDR`,'鎮'))\r\n"
				+ "			when instr(`PATIENT_ADDR`,'市')>0 THEN left(`PATIENT_ADDR`,instr(`PATIENT_ADDR`,'市'))\r\n"
				+ "		end\r\n"
				+ "   END  AS `DIST`,`START_TIME`, `END_TIME`, CASE `SERVICE_TYPE` WHEN 0 THEN '居家照護' WHEN 1 THEN '醫院看護' END as `SERVICE_TYPE`, `NOTE`, CASE `STATUS` WHEN 0 THEN '已取消' WHEN 1 THEN '已媒合' WHEN 2 THEN '開放應徵中' END as `STATUS` FROM `CARE_REQUEST` where `REQUEST_ID` = ?;";

		CareRequestVO careRequestVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(SQLUtil.URL, SQLUtil.USER, SQLUtil.PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_REQ);
			pstmt.setInt(1, requestId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				careRequestVO = new CareRequestVO();
				careRequestVO.setRequestId(rs.getInt("REQUEST_ID"));
				careRequestVO.setPatientName(rs.getString("PATIENT_LNAME"));
				careRequestVO.setPatientGender(rs.getString("PATIENT_GENDER"));
				careRequestVO.setPatientAge(rs.getInt("PATIENT_AGE"));
				careRequestVO.setPatientAddr(rs.getString("DIST"));
				careRequestVO.setStartTime(rs.getTimestamp("START_TIME"));
				careRequestVO.setEndTime(rs.getTimestamp("END_TIME"));
				careRequestVO.setServiceType(rs.getString("SERVICE_TYPE"));
				careRequestVO.setNote(rs.getString("NOTE"));
				careRequestVO.setStatus(rs.getString("STATUS"));
				careRequestVO.setMemId(rs.getInt("MEM_ID"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLUtil.closeResource(con, pstmt, rs);
		}

		return careRequestVO;
	}

	@Override
	public List<CareApplyVO> findAppliersByRequestId(Integer requestId) {
		final String APPLIERS = "SELECT `REQUEST_ID`,`CARER_ID`,CASE `STATUS` WHEN 0 THEN '被拒絕' WHEN 1 THEN '待會員確認' END AS `STATUS`, `APPLY_TIME` FROM `CARE_APPLY` WHERE `REQUEST_ID` = ?";
		List<CareApplyVO> list = new ArrayList<CareApplyVO>();
		CareApplyVO vo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(SQLUtil.URL, SQLUtil.USER, SQLUtil.PASSWORD);
			pstmt = con.prepareStatement(APPLIERS);
			pstmt.setInt(1, requestId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				vo = new CareApplyVO();
				vo.setRequestId(rs.getInt("REQUEST_ID"));
				vo.setCarerId(rs.getInt("CARER_ID"));
				vo.setApplyTime(rs.getTimestamp("APPLY_TIME"));
				vo.setStatus(rs.getString("STATUS"));
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLUtil.closeResource(con, pstmt, rs);
		}
		return list;
	}

	public List<CareApplyVO> findAllAppliers() {
		final String ALL = "SELECT `REQUEST_ID`,`CARER_ID`,CASE `STATUS` WHEN 0 THEN '被拒絕' WHEN 1 THEN '待會員確認' END AS `STATUS`, `APPLY_TIME` FROM `CARE_APPLY` WHERE `STATUS` IN (0,1)";
		List<CareApplyVO> list = new ArrayList<CareApplyVO>();
		CareApplyVO vo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(SQLUtil.URL, SQLUtil.USER, SQLUtil.PASSWORD);
			pstmt = con.prepareStatement(ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				vo = new CareApplyVO();
				vo.setRequestId(rs.getInt("REQUEST_ID"));
				vo.setCarerId(rs.getInt("CARER_ID"));
				vo.setApplyTime(rs.getTimestamp("APPLY_TIME"));
				vo.setStatus(rs.getString("STATUS"));
				list.add(vo);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLUtil.closeResource(con, pstmt, rs);
		}

		return list;
	}

	@Override
	public CareRequestMVO selectAllIncludeAppliers() {
		final String ALL = "";
		return null;
	}

	@Override
	public Integer numberOfAppliers(Integer requestId) {
		final String NUM = "SELECT COUNT(*) as `NUM` FROM `CARE_APPLY` GROUP BY `REQUEST_ID` HAVING `REQUEST_ID` = ? ";
		Integer appliers = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(SQLUtil.URL, SQLUtil.USER, SQLUtil.PASSWORD);
			pstmt = con.prepareStatement(NUM);
			pstmt.setInt(1, requestId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				appliers = Integer.valueOf(rs.getInt("NUM"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLUtil.closeResource(con, pstmt, rs);
		}
		return appliers;

	}
	@Override
	public Integer hasApplied(Integer carerId, Integer requestId) {
		final String HAS_APPLIED = "SELECT COUNT(*) AS COUNT FROM `CARE_APPLY` WHERE `STATUS`= 1 AND `CARER_ID` = ? AND `REQUEST_ID` = ? ";
		Integer check = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(SQLUtil.URL, SQLUtil.USER, SQLUtil.PASSWORD);
			pstmt = con.prepareStatement(HAS_APPLIED);
			pstmt.setInt(1, carerId);
			pstmt.setInt(2, requestId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				check = Integer.valueOf(rs.getInt("COUNT"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLUtil.closeResource(con, pstmt, rs);
		}

		return check;
	}
	
	@Override
	public Integer beRejected(Integer carerId, Integer requestId) {
		final String HAS_APPLIED = "SELECT COUNT(*) AS COUNT FROM `CARE_APPLY` WHERE `STATUS`= 0 AND `CARER_ID` = ? AND `REQUEST_ID` = ? ";
		Integer check = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(SQLUtil.URL, SQLUtil.USER, SQLUtil.PASSWORD);
			pstmt = con.prepareStatement(HAS_APPLIED);
			pstmt.setInt(1, carerId);
			pstmt.setInt(2, requestId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				check = Integer.valueOf(rs.getInt("COUNT"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLUtil.closeResource(con, pstmt, rs);
		}

		return check;
	}
	
	//後臺用
	@Override
	public void updateRequestStatus(CareRequestVO careRequestVO) {
		final String UPDATE_REQUEST_STATUS = "UPDATE `CARE_REQUEST` set `STATUS`=?, `UPDATE_TIME`= ? where `REQUEST_ID` = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(SQLUtil.URL, SQLUtil.USER, SQLUtil.PASSWORD);
			pstmt = con.prepareStatement(UPDATE_REQUEST_STATUS);
			
			pstmt.setString(1, careRequestVO.getStatus());
			pstmt.setTimestamp(2, careRequestVO.getUpdateTime());
			pstmt.setInt(3, careRequestVO.getRequestId());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLUtil.closeResource(con, pstmt, null);
		}		
	}
}
