package com.collection.model;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
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

import org.springframework.stereotype.Repository;

import com.utils.SQLUtil;
@Repository
public class CollectionDAOImpl implements CollectionDAO {

	@Override
	public void addCollection(CollectionVO vo) {
		final String INSERT = "INSERT INTO `COLLECTION` (`MEM_ID`, `CARER_ID`, `COLL_TIME`) VALUES (? , ?, CURRENT_TIMESTAMP) ";
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(SQLUtil.URL, SQLUtil.USER, SQLUtil.PASSWORD);
			pstmt = con.prepareStatement(INSERT);
			pstmt.setInt(1, vo.getMemId());
			pstmt.setInt(2, vo.getCarerId());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLUtil.closeResource(con, pstmt, null);
		}
	}

	@Override
	public void deleteCollection(Integer memberId, Integer carerId) {
		final String DELETE = "DELETE FROM `COLLECTION` WHERE `COLLECTION`.`MEM_ID` = ? AND `COLLECTION`.`CARER_ID` = ? ";
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(SQLUtil.URL, SQLUtil.USER, SQLUtil.PASSWORD);
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setInt(1, memberId);
			pstmt.setInt(2, carerId);
			pstmt.executeUpdate();
			con.commit();
			con.setAutoCommit(true);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLUtil.closeResource(con, pstmt, null);
		}
	}

	@Override
	public List<CollectionVO> findByMemId(Integer memId) throws IOException {
		final String SQL = "SELECT `COLLECTION`.`CARER_ID` AS `CARER_ID`, LEFT(`MEMBER`.`MEM_NAME`,1) AS `SURNAME`, CASE `MEMBER`.`MEM_GENDER` WHEN 0 THEN '先生' WHEN 1 THEN '小姐' END AS `GENDER`, `COUNTY`.`CTY_NAME` AS `CTY_NAME`, `DISTRICT`.`DIST_NAME` AS `DIST_NAME`, `FILE`.`FILE_CONTENT` AS `PHOTO` FROM `COLLECTION` LEFT JOIN `MEMBER` ON `COLLECTION`.`CARER_ID` = `MEMBER`.`MEM_ID` LEFT JOIN `DISTRICT` ON `DISTRICT`.`DIST_NO` = `MEMBER`.`DIST_NO` LEFT JOIN `COUNTY` ON `COUNTY`.`CTY_NO`=`DISTRICT`.`CTY_NO` LEFT JOIN `FILE` ON `COLLECTION`.`CARER_ID` = `FILE`.`CARER_ID` WHERE `FILE`.`FILE_TYPE_NO`= 'P01' AND `COLLECTION`.`MEM_ID` = ? ";
		List<CollectionVO> list = new ArrayList<CollectionVO>();
		CollectionVO vo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(SQLUtil.URL, SQLUtil.USER, SQLUtil.PASSWORD);
			pstmt = con.prepareStatement(SQL);
			pstmt.setInt(1, memId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				vo = new CollectionVO();
				Integer carerId = rs.getInt("CARER_ID");
				String surName = rs.getString("SURNAME");
				String gender = rs.getString("GENDER");
				String county = rs.getString("CTY_NAME");
				String dist = rs.getString("DIST_NAME");
				Blob blob = rs.getBlob("PHOTO");
				
				InputStream inputStream = blob.getBinaryStream();
				ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
				byte[] buffer = new byte[4096];
				int bytesRead = -1;
				
				while ((bytesRead = inputStream.read(buffer))!= -1) {
					outputStream.write(buffer,0,bytesRead);
				}
				
				byte[] imageBytes = outputStream.toByteArray();
				String photo = Base64.getEncoder().encodeToString(imageBytes);
				
				inputStream.close();
				outputStream.close();
				
				vo.setCarerId(carerId);
				vo.setCarerSurname(surName);
				vo.setCarerGender(gender);
				vo.setCarerCounty(county);
				vo.setCarerDist(dist);
				vo.setCarerPhoto(photo);
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
	public Integer findServiceTimes(Integer memberId, Integer carerId) {
		final String SQL = "SELECT COUNT(*) AS `COUNT` FROM `CARE_ORDER` JOIN `CARE_REQUEST` ON `CARE_ORDER`.`REQUEST_ID` = `CARE_REQUEST`.`REQUEST_ID` WHERE `CARE_REQUEST`.`MEM_ID` = ? AND `CARE_ORDER`.`CARER_ID` = ? ";
		Integer count = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(SQLUtil.URL, SQLUtil.USER, SQLUtil.PASSWORD);
			pstmt = con.prepareStatement(SQL);
			pstmt.setInt(1,memberId);
			pstmt.setInt(2,carerId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				count = Integer.valueOf(rs.getInt("COUNT"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLUtil.closeResource(con, pstmt, rs);
		}
		return count;
	}
	
	public static void main(String[] args) {
		CollectionDAOImpl dao = new CollectionDAOImpl();
		Integer count = dao.findServiceTimes(3344, 1);
		System.out.println(count);
		dao.deleteCollection(88888, 99999);;
	}

	@Override
	public Integer isCollected(Integer memberId, Integer carerId) {
		final String CHECK_COLLECTED = "select count(*) as `COUNT` from `COLLECTION` where MEM_ID = ? AND CARER_ID = ? ";
		Integer count = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(SQLUtil.URL, SQLUtil.USER, SQLUtil.PASSWORD);
			pstmt = con.prepareStatement(CHECK_COLLECTED);
			pstmt.setInt(1,memberId);
			pstmt.setInt(2,carerId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				count = Integer.valueOf(rs.getInt("COUNT"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLUtil.closeResource(con, pstmt, rs);
		}
		return count;
	}

}
