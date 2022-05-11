package com.infomanage.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.utils.SQLUtil;

@Repository
public class InfoDAOImpl implements InfoDAO {

	@Override
	public void insertFAQ(InfoVO infoVO) {
		final String INSERT_FAQ = "INSERT INTO `FAQ` (`FAQ_NO`,`QUESTION`,`ANSWER`,`CREATE_TIME`,`STATUS`) VALUES (?,?,?,?,?)";
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(SQLUtil.URL, SQLUtil.USER, SQLUtil.PASSWORD);
			pstmt = con.prepareStatement(INSERT_FAQ);
			pstmt.setString(1, infoVO.getNo());
			pstmt.setString(2, infoVO.getName());
			pstmt.setString(3, infoVO.getContent());
			pstmt.setTimestamp(4, infoVO.getCreateTime());
			pstmt.setString(5, infoVO.getStatus());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLUtil.closeResource(con, pstmt, null);
		}

	}

	@Override
	public void updateFAQ(InfoVO infoVO) {
		final String UPDATE_FAQ = "UPDATE `FAQ` set `QUESTION`=?, `ANSWER`=?, `STATUS`=?, `UPDATE_TIME`=? where `FAQ_NO` = ?";
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(SQLUtil.URL, SQLUtil.USER, SQLUtil.PASSWORD);
			pstmt = con.prepareStatement(UPDATE_FAQ);

			pstmt.setString(1, infoVO.getName());
			pstmt.setString(2, infoVO.getContent());
			pstmt.setString(3, infoVO.getStatus());
			pstmt.setTimestamp(4, infoVO.getUpdateTime());
			pstmt.setString(5, infoVO.getNo());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLUtil.closeResource(con, pstmt, null);
		}
	}

	@Override
	public void updateFAQStatus(InfoVO infoVO) {
		final String UPDATE_FAQ_STATUS = "UPDATE `FAQ` set `STATUS`=?, `UPDATE_TIME`=? where `FAQ_NO` = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(SQLUtil.URL, SQLUtil.USER, SQLUtil.PASSWORD);
			pstmt = con.prepareStatement(UPDATE_FAQ_STATUS);
			
			pstmt.setString(1, infoVO.getStatus());
			pstmt.setTimestamp(2, infoVO.getUpdateTime());
			pstmt.setString(3, infoVO.getNo());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLUtil.closeResource(con, pstmt, null);
		}		
	}

	@Override
	public String getLatestFaqNo() {
		final String GET_LATEST_FAQNO = "SELECT SUBSTRING(`FAQ_NO`,4,5) AS `LATEST_NO` FROM `FAQ` ORDER BY `FAQ_NO` DESC LIMIT 1";
		String latestNo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			con = DriverManager.getConnection(SQLUtil.URL, SQLUtil.USER, SQLUtil.PASSWORD);
			pstmt = con.prepareStatement(GET_LATEST_FAQNO);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				latestNo = rs.getString("LATEST_NO");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLUtil.closeResource(con, pstmt, rs);
		}
		return latestNo;
	}

	@Override
	public List<InfoVO> getAllFAQ() {
		final String GET_ALL_FAQ = "SELECT *, CASE `STATUS` WHEN 0 THEN '隱藏' WHEN 1 THEN '公開發布' END AS `STATUS_CH`, SUBSTRING(`ANSWER`,1,8) AS `SHORT_CONTENT`, SUBSTRING(`QUESTION`,1,10) AS `SHORT_NAME`,SUBSTRING(`CREATE_TIME`,1,16) AS `SHORT_CREATE`, SUBSTRING(`UPDATE_TIME`,1,16) AS `SHORT_UPDATE` FROM `FAQ`";
		List<InfoVO> list = new ArrayList<InfoVO>();
		InfoVO vo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(SQLUtil.URL, SQLUtil.USER, SQLUtil.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_FAQ);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				vo = new InfoVO();
				vo.setNo(rs.getString("FAQ_NO"));
				vo.setName(rs.getString("QUESTION"));
				vo.setContent(rs.getString("ANSWER"));
				vo.setCreateTime(rs.getTimestamp("CREATE_TIME"));
				vo.setUpdateTime(rs.getTimestamp("UPDATE_TIME"));
				vo.setStatus(rs.getString("STATUS_CH"));
				vo.setShortContent(rs.getString("SHORT_CONTENT"));
				vo.setShortName(rs.getString("SHORT_NAME"));
				vo.setShortCreateTime(rs.getString("SHORT_CREATE"));
				vo.setShortUpdateTime(rs.getString("SHORT_UPDATE"));
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
	public void insertIntro(InfoVO infoVO) {
		final String INSERT_INTRO = "INSERT INTO `SERVICE_INTRODUCTION` (`SERVICE_NO`,`SERVICE_NAME`,`SERVICE_CONTENT`,`CREATE_TIME`,`STATUS`) VALUES (?,?,?,?,?)";
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(SQLUtil.URL, SQLUtil.USER, SQLUtil.PASSWORD);
			pstmt = con.prepareStatement(INSERT_INTRO);
			pstmt.setString(1, infoVO.getNo());
			pstmt.setString(2, infoVO.getName());
			pstmt.setString(3, infoVO.getContent());
			pstmt.setTimestamp(4, infoVO.getCreateTime());
			pstmt.setString(5, infoVO.getStatus());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLUtil.closeResource(con, pstmt, null);
		}

	}

	@Override
	public void updateIntro(InfoVO infoVO) {
		final String UPDATE_INTRO = "UPDATE `SERVICE_INTRODUCTION` set `SERVICE_NAME`=?, `SERVICE_CONTENT`=?, `STATUS`=?, `UPDATE_TIME`=? where `SERVICE_NO` = ?";
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(SQLUtil.URL, SQLUtil.USER, SQLUtil.PASSWORD);
			pstmt = con.prepareStatement(UPDATE_INTRO);

			pstmt.setString(1, infoVO.getName());
			pstmt.setString(2, infoVO.getContent());
			pstmt.setString(3, infoVO.getStatus());
			pstmt.setTimestamp(4, infoVO.getUpdateTime());
			pstmt.setString(5, infoVO.getNo());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLUtil.closeResource(con, pstmt, null);
		}
	}

	@Override
	public void updateIntroStatus(InfoVO infoVO) {
		final String UPDATE_INTRO_STATUS = "UPDATE `SERVICE_INTRODUCTION` set `STATUS`=?, `UPDATE_TIME`=? where `SERVICE_NO` = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(SQLUtil.URL, SQLUtil.USER, SQLUtil.PASSWORD);
			pstmt = con.prepareStatement(UPDATE_INTRO_STATUS);
			
			pstmt.setString(1, infoVO.getStatus());
			pstmt.setTimestamp(2, infoVO.getUpdateTime());
			pstmt.setString(3, infoVO.getNo());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLUtil.closeResource(con, pstmt, null);
		}		
	}

	@Override
	public String getLatestIntroNo() {
		final String GET_LATEST_INTRONO = "SELECT SUBSTRING(`SERVICE_NO`,2,3) AS `LATEST_NO` FROM `SERVICE_INTRODUCTION` ORDER BY `SERVICE_NO` DESC LIMIT 1";
		String latestNo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			con = DriverManager.getConnection(SQLUtil.URL, SQLUtil.USER, SQLUtil.PASSWORD);
			pstmt = con.prepareStatement(GET_LATEST_INTRONO);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				latestNo = rs.getString("LATEST_NO");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLUtil.closeResource(con, pstmt, rs);
		}
		return latestNo;
	}

	@Override
	public List<InfoVO> getAllIntro() {
		final String GET_ALL_INTRO = "SELECT *, CASE `STATUS` WHEN 0 THEN '隱藏' WHEN 1 THEN '公開發布' END AS `STATUS_CH`, SUBSTRING(`SERVICE_CONTENT`,1,8) AS `SHORT_CONTENT`, SUBSTRING(`SERVICE_NAME`,1,10) AS `SHORT_NAME`,SUBSTRING(`CREATE_TIME`,1,16) AS `SHORT_CREATE`, SUBSTRING(`UPDATE_TIME`,1,16) AS `SHORT_UPDATE` FROM `SERVICE_INTRODUCTION`";
		List<InfoVO> list = new ArrayList<InfoVO>();
		InfoVO vo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(SQLUtil.URL, SQLUtil.USER, SQLUtil.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_INTRO);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				vo = new InfoVO();
				vo.setNo(rs.getString("SERVICE_NO"));
				vo.setName(rs.getString("SERVICE_NAME"));
				vo.setContent(rs.getString("SERVICE_CONTENT"));
				vo.setCreateTime(rs.getTimestamp("CREATE_TIME"));
				vo.setUpdateTime(rs.getTimestamp("UPDATE_TIME"));
				vo.setStatus(rs.getString("STATUS_CH"));
				vo.setShortContent(rs.getString("SHORT_CONTENT"));
				vo.setShortName(rs.getString("SHORT_NAME"));
				vo.setShortCreateTime(rs.getString("SHORT_CREATE"));
				vo.setShortUpdateTime(rs.getString("SHORT_UPDATE"));
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
	public void insertNews(InfoVO infoVO) {
		final String INSERT_INFO = "INSERT INTO `LATEST_NEWS` (`NEWS_NO`,`NEWS_TITLE`,`NEWS_CONTENT`,`CREATE_TIME`,`STATUS`) VALUES (?,?,?,?,?)";
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(SQLUtil.URL, SQLUtil.USER, SQLUtil.PASSWORD);
			pstmt = con.prepareStatement(INSERT_INFO);
			pstmt.setString(1, infoVO.getNo());
			pstmt.setString(2, infoVO.getName());
			pstmt.setString(3, infoVO.getContent());
			pstmt.setTimestamp(4, infoVO.getCreateTime());
			pstmt.setString(5, infoVO.getStatus());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLUtil.closeResource(con, pstmt, null);
		}
	}

	@Override
	public void updateNews(InfoVO infoVO) {
		final String UPDATE_INFO = "UPDATE `LATEST_NEWS` set `NEWS_TITLE`=?, `NEWS_CONTENT`=?, `STATUS`=?, `UPDATE_TIME`=? where `NEWS_NO` = ?";
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(SQLUtil.URL, SQLUtil.USER, SQLUtil.PASSWORD);
			pstmt = con.prepareStatement(UPDATE_INFO);

			pstmt.setString(1, infoVO.getName());
			pstmt.setString(2, infoVO.getContent());
			pstmt.setString(3, infoVO.getStatus());
			pstmt.setTimestamp(4, infoVO.getUpdateTime());
			pstmt.setString(5, infoVO.getNo());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLUtil.closeResource(con, pstmt, null);
		}
	}
	
	@Override
	public void updateNewsStatus(InfoVO infoVO) {
		final String UPDATE_NEWS_STATUS = "UPDATE `LATEST_NEWS` set `STATUS`=?, `UPDATE_TIME`=? where `NEWS_NO` = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(SQLUtil.URL, SQLUtil.USER, SQLUtil.PASSWORD);
			pstmt = con.prepareStatement(UPDATE_NEWS_STATUS);
			
			pstmt.setString(1, infoVO.getStatus());
			pstmt.setTimestamp(2, infoVO.getUpdateTime());
			pstmt.setString(3, infoVO.getNo());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLUtil.closeResource(con, pstmt, null);
		}
	}

	@Override
	public String getLatestNewsNo() {
		final String GET_LATEST_NEWSNO = "SELECT SUBSTRING(`NEWS_NO`,5,6) AS `LATEST_NO` FROM `LATEST_NEWS` ORDER BY `NEWS_NO` DESC LIMIT 1";
		String latestNo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			con = DriverManager.getConnection(SQLUtil.URL, SQLUtil.USER, SQLUtil.PASSWORD);
			pstmt = con.prepareStatement(GET_LATEST_NEWSNO);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				latestNo = rs.getString("LATEST_NO");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLUtil.closeResource(con, pstmt, rs);
		}
		return latestNo;
	}

	@Override
	public List<InfoVO> getAllNews() {
		final String GET_ALL_NEWS = "SELECT *, CASE `STATUS` WHEN 0 THEN '隱藏' WHEN 1 THEN '公開發布' END AS `STATUS_CH`, SUBSTRING(`NEWS_CONTENT`,1,8) AS `SHORT_CONTENT`, SUBSTRING(`NEWS_TITLE`,1,10) AS `SHORT_NAME`,SUBSTRING(`CREATE_TIME`,1,16) AS `SHORT_CREATE`, SUBSTRING(`UPDATE_TIME`,1,16) AS `SHORT_UPDATE` FROM `LATEST_NEWS`";
		List<InfoVO> list = new ArrayList<InfoVO>();
		InfoVO vo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(SQLUtil.URL, SQLUtil.USER, SQLUtil.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_NEWS);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				vo = new InfoVO();
				vo.setNo(rs.getString("NEWS_NO"));
				vo.setName(rs.getString("NEWS_TITLE"));
				vo.setContent(rs.getString("NEWS_CONTENT"));
				vo.setCreateTime(rs.getTimestamp("CREATE_TIME"));
				vo.setUpdateTime(rs.getTimestamp("UPDATE_TIME"));
				vo.setStatus(rs.getString("STATUS_CH"));
				vo.setShortContent(rs.getString("SHORT_CONTENT"));
				vo.setShortName(rs.getString("SHORT_NAME"));
				vo.setShortCreateTime(rs.getString("SHORT_CREATE"));
				vo.setShortUpdateTime(rs.getString("SHORT_UPDATE"));
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
	public InfoVO selectIntroByNo(String createTimeId) {
		//TODO: there may be useless, confirm to delete
		return null;
	}

	@Override
	public InfoVO selectNewsByNo(String createTimeId) {
		final String GET_A_NEWS = "SELECT * FROM `LATEST_NEWS` JOIN ( SELECT *,REGEXP_REPLACE(`CREATE_TIME`, '[^0-9]+', '') AS `CREATE_TIME_ID` FROM `LATEST_NEWS` WHERE `STATUS`= 1 ) SUB_TABLE ON `LATEST_NEWS`.`NEWS_NO` = SUB_TABLE.`NEWS_NO` WHERE CREATE_TIME_ID = ? ";
		InfoVO vo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(SQLUtil.URL, SQLUtil.USER, SQLUtil.PASSWORD);
			pstmt = con.prepareStatement(GET_A_NEWS);
			pstmt.setString(1, createTimeId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				vo = new InfoVO();
				vo.setName(rs.getString("NEWS_TITLE"));
				vo.setContent(rs.getString("NEWS_CONTENT"));
				vo.setCreateTime(rs.getTimestamp("CREATE_TIME"));
				vo.setUpdateTime(rs.getTimestamp("UPDATE_TIME"));
				vo.setNo(rs.getString("CREATE_TIME_ID"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLUtil.closeResource(con, pstmt, rs);
		}
		return vo;
	}
	
	@Override
	public List<InfoVO> getValidNews(){
		final String GET_VALID_NEWS = "SELECT *,REGEXP_REPLACE(`CREATE_TIME`, '[^0-9]+', '') AS `CREATE_TIME_ID` FROM `LATEST_NEWS` WHERE `STATUS`= 1 ORDER BY `CREATE_TIME` DESC";
		List<InfoVO> list = new ArrayList<InfoVO>();
		InfoVO vo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(SQLUtil.URL, SQLUtil.USER, SQLUtil.PASSWORD);
			pstmt = con.prepareStatement(GET_VALID_NEWS);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				vo = new InfoVO();
				vo.setName(rs.getString("NEWS_TITLE"));
				vo.setContent(rs.getString("NEWS_CONTENT"));
				vo.setCreateTime(rs.getTimestamp("CREATE_TIME"));
				vo.setUpdateTime(rs.getTimestamp("UPDATE_TIME"));
				vo.setNo(rs.getString("CREATE_TIME_ID"));
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
	public InfoVO selectFAQByNo(String createTimeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<InfoVO> getValidFAQ() {
		final String GET_VALID_FAQ = "SELECT *,REGEXP_REPLACE(`CREATE_TIME`, '[^0-9]+', '') AS `CREATE_TIME_ID` FROM `FAQ` WHERE `STATUS`= 1 ORDER BY `CREATE_TIME` DESC";
		List<InfoVO> list = new ArrayList<InfoVO>();
		InfoVO vo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(SQLUtil.URL, SQLUtil.USER, SQLUtil.PASSWORD);
			pstmt = con.prepareStatement(GET_VALID_FAQ);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				vo = new InfoVO();
				vo.setName(rs.getString("QUESTION"));
				vo.setContent(rs.getString("ANSWER"));
				vo.setCreateTime(rs.getTimestamp("CREATE_TIME"));
				vo.setUpdateTime(rs.getTimestamp("UPDATE_TIME"));
				vo.setNo(rs.getString("CREATE_TIME_ID"));
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
	public List<InfoVO> getValidIntro() {
		final String GET_VALID_INFO = "SELECT *,REGEXP_REPLACE(`CREATE_TIME`, '[^0-9]+', '') AS `CREATE_TIME_ID` FROM `SERVICE_INTRODUCTION` WHERE `STATUS`= 1 ORDER BY `CREATE_TIME` DESC";
		List<InfoVO> list = new ArrayList<InfoVO>();
		InfoVO vo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(SQLUtil.URL, SQLUtil.USER, SQLUtil.PASSWORD);
			pstmt = con.prepareStatement(GET_VALID_INFO);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				vo = new InfoVO();
				vo.setName(rs.getString("SERVICE_NAME"));
				vo.setContent(rs.getString("SERVICE_CONTENT"));
				vo.setCreateTime(rs.getTimestamp("CREATE_TIME"));
				vo.setUpdateTime(rs.getTimestamp("UPDATE_TIME"));
				vo.setNo(rs.getString("CREATE_TIME_ID"));
				list.add(vo);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLUtil.closeResource(con, pstmt, rs);
		}
		return list;
	}

	public static void main(String[] args) {
		InfoDAOImpl dao = new InfoDAOImpl();
//		List<InfoVO> list = dao.getValidNews();
//		for (InfoVO vo : list) {
//			System.out.println(vo.getContent());
//		}
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		System.out.println(dtf.format(LocalDateTime.now()));

		InfoVO vo = new InfoVO();
//		vo.setNo("NEWS03");
//		vo.setName("【公告】網站維修公告");
//		vo.setContent("本平台預計於2022/02/13 23:00 至 2022/02/14 01:00 進行維修，可能造成該時段網站無法進入，造成不便敬請見諒");
//		vo.setCreateTime(java.sql.Timestamp.valueOf(dtf.format(LocalDateTime.now())));
//		dao.insertNews(vo);
		
//		InfoVO vo = new InfoVO();
//		vo.setNo("NEWS08");
//		vo.setStatus("0");
//		vo.setName("【公告】測試");
//		vo.setContent("測試");
//		vo.setUpdateTime(java.sql.Timestamp.valueOf(dtf.format(LocalDateTime.now())));
//		dao.updateNews(vo);
		
		vo = dao.selectNewsByNo("20220202203654");
		System.out.println(vo.getName());

	}

}
