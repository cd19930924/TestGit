package com.driveorder.model.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.driveorder.model.dao.DriveOrderDAO;
import com.driveorder.model.vo.DriveOrderVO;
import com.utils.Util;

public class DriveOrderJDBCDAOImpl implements DriveOrderDAO {
	private static final String INSERT_STMT = "INSERT INTO DRIVE_ORDER (MEM_ID,DRIVER_ID,START_POINT,END_POINT,DISTANCE,SEND_DRIVE_DATE,SEND_DRIVE_TIME,ORDER_AMOUNT,CONTACT_NAME,CONTACT_NUMBER) SELECT * FROM (SELECT ?,?,?,?,?,?,?,?,?,?) AS TMP WHERE NOT EXISTS (SELECT * FROM DRIVE_ORDER WHERE DRIVER_ID = ? AND SEND_DRIVE_DATE = ? AND SEND_DRIVE_TIME = ?)LIMIT 1";
	private static final String UPDATE = "UPDATE DRIVE_ORDER SET DRIVER_ID = ?,SEND_DRIVE_DATE = ?,SEND_DRIVE_TIME = ?,CONTACT_NAME = ?,CONTACT_NUMBER = ?,ORDER_STATUS = ?,DRIVE_FEEDBACK = ?,UPDATE_TIME = ? WHERE DRIVE_ORDER_ID = ?";
	private static final String GET_ONE_STMT_ID = 
			"SELECT DRIVE_ORDER_ID,MEM_ID,DRIVER_ID,DRIVER_NAME,DRIVER_PHONE,CAR_NUMBER,START_POINT,END_POINT,DISTANCE,SEND_DRIVE_DATE,SEND_DRIVE_TIME,ORDER_AMOUNT,CONTACT_NAME,CONTACT_NUMBER,ORDER_STATUS,DRIVE_FEEDBACK,CREATE_TIME,UPDATE_TIME FROM DRIVE_ORDER NATURAL JOIN DRIVER WHERE DRIVE_ORDER_ID = ?";
	private static final String GET_ALL_STMT_DATE = 
			"SELECT DRIVE_ORDER_ID,MEM_ID,DRIVER_ID,START_POINT,END_POINT,DISTANCE,SEND_DRIVE_DATE,SEND_DRIVE_TIME,ORDER_AMOUNT,CONTACT_NAME,CONTACT_NUMBER,ORDER_STATUS,DRIVE_FEEDBACK,CREATE_TIME,UPDATE_TIME FROM DRIVE_ORDER WHERE SEND_DRIVE_DATE = ?";
	private static final String GET_ALL_STMT_MEMID = 
			"SELECT DRIVE_ORDER_ID,MEM_ID,DRIVER_ID,START_POINT,END_POINT,DISTANCE,SEND_DRIVE_DATE,SEND_DRIVE_TIME,ORDER_AMOUNT,CONTACT_NAME,CONTACT_NUMBER,ORDER_STATUS,DRIVE_FEEDBACK,CREATE_TIME,UPDATE_TIME FROM DRIVE_ORDER WHERE MEM_ID = ?";
	private static final String GET_ALL_STMT_MEMID_ORDER = 
			"SELECT DRIVE_ORDER_ID,MEM_ID,DRIVER_ID,START_POINT,END_POINT,DISTANCE,SEND_DRIVE_DATE,SEND_DRIVE_TIME,ORDER_AMOUNT,CONTACT_NAME,CONTACT_NUMBER,ORDER_STATUS,DRIVE_FEEDBACK,CREATE_TIME,UPDATE_TIME FROM DRIVE_ORDER WHERE MEM_ID = ? ORDER BY ORDER_STATUS,SEND_DRIVE_DATE,SEND_DRIVE_TIME";
	private static final String GET_ALL_STMT_MEMID_STATUS = 
			"SELECT DRIVE_ORDER_ID,MEM_ID,DRIVER_ID,START_POINT,END_POINT,DISTANCE,SEND_DRIVE_DATE,SEND_DRIVE_TIME,ORDER_AMOUNT,CONTACT_NAME,CONTACT_NUMBER,ORDER_STATUS,DRIVE_FEEDBACK,CREATE_TIME,UPDATE_TIME FROM DRIVE_ORDER WHERE MEM_ID = ? AND ORDER_STATUS = ? ORDER BY ORDER_STATUS,SEND_DRIVE_DATE,SEND_DRIVE_TIME";
	private static final String GET_ALL_STMT_MEMID_DATE = 
			"SELECT DRIVE_ORDER_ID,MEM_ID,DRIVER_ID,START_POINT,END_POINT,DISTANCE,SEND_DRIVE_DATE,SEND_DRIVE_TIME,ORDER_AMOUNT,CONTACT_NAME,CONTACT_NUMBER,ORDER_STATUS,DRIVE_FEEDBACK,CREATE_TIME,UPDATE_TIME FROM DRIVE_ORDER WHERE MEM_ID = ? AND SEND_DRIVE_DATE = ? ORDER BY ORDER_STATUS,SEND_DRIVE_DATE,SEND_DRIVE_TIME";
	private static final String GET_ALL_STMT_DRIVERID = 
			"SELECT DRIVE_ORDER_ID,MEM_ID,DRIVER_ID,START_POINT,END_POINT,DISTANCE,SEND_DRIVE_DATE,SEND_DRIVE_TIME,ORDER_AMOUNT,CONTACT_NAME,CONTACT_NUMBER,ORDER_STATUS,DRIVE_FEEDBACK,CREATE_TIME,UPDATE_TIME FROM DRIVE_ORDER WHERE DRIVER_ID = ?";
	private static final String GET_ALL_STMT_STATUS = 
			"SELECT DRIVE_ORDER_ID,MEM_ID,DRIVER_ID,START_POINT,END_POINT,DISTANCE,SEND_DRIVE_DATE,SEND_DRIVE_TIME,ORDER_AMOUNT,CONTACT_NAME,CONTACT_NUMBER,ORDER_STATUS,DRIVE_FEEDBACK,CREATE_TIME,UPDATE_TIME FROM DRIVE_ORDER WHERE ORDER_STATUS = ?";
	private static final String GET_ALL_STMT = 
			"SELECT DRIVE_ORDER_ID,MEM_ID,DRIVER_ID,START_POINT,END_POINT,DISTANCE,SEND_DRIVE_DATE,SEND_DRIVE_TIME,ORDER_AMOUNT,CONTACT_NAME,CONTACT_NUMBER,ORDER_STATUS,DRIVE_FEEDBACK,CREATE_TIME,UPDATE_TIME FROM DRIVE_ORDER";

	@Override
	public DriveOrderVO insert(DriveOrderVO driveOrderVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setInt(1, driveOrderVO.getMemId());
			pstmt.setInt(2, driveOrderVO.getDriverId());
			pstmt.setString(3, driveOrderVO.getStartPoint());
			pstmt.setString(4, driveOrderVO.getEndPoint());
			pstmt.setDouble(5, driveOrderVO.getDistance());
			pstmt.setDate(6, driveOrderVO.getSendDriveDate());
			pstmt.setTime(7, driveOrderVO.getSendDriveTime());
			pstmt.setDouble(8, driveOrderVO.getOrderAmount());
			pstmt.setString(9, driveOrderVO.getContactName());
			pstmt.setString(10, driveOrderVO.getContactNumber());
			pstmt.setInt(11, driveOrderVO.getDriverId());
			pstmt.setDate(12, driveOrderVO.getSendDriveDate());
			pstmt.setTime(13, driveOrderVO.getSendDriveTime());

			pstmt.executeUpdate();
			
			rs = pstmt.executeQuery("SELECT LAST_INSERT_ID()");
			
			if(rs.next()) {
				driveOrderVO.setDrverOrderId(rs.getInt("LAST_INSERT_ID()"));
			}
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			throw new RuntimeException(e.getMessage());
		} catch (SQLException se) {
			// TODO: handle exception
			throw new RuntimeException(se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					// TODO: handle exception
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace(System.err);
				}
			}
		}
		return driveOrderVO;
	}

	@Override
	public void update(DriveOrderVO driveOrderVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setInt(1, driveOrderVO.getDriverId());
			pstmt.setDate(2, driveOrderVO.getSendDriveDate());
			pstmt.setTime(3, driveOrderVO.getSendDriveTime());
			pstmt.setString(4, driveOrderVO.getContactName());
			pstmt.setString(5, driveOrderVO.getContactNumber());
			pstmt.setString(6, driveOrderVO.getOrderStatus());
			pstmt.setString(7, driveOrderVO.getDriveFeedback());
			pstmt.setTimestamp(8, driveOrderVO.getUpdateTime());
			pstmt.setInt(9, driveOrderVO.getDrverOrderId());

			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			throw new RuntimeException(e.getMessage());
		} catch (SQLException se) {
			// TODO: handle exception
			throw new RuntimeException(se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					// TODO: handle exception
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public DriveOrderVO findByOrderId(Integer driverOrderId) {
		// TODO Auto-generated method stub
		DriveOrderVO driveOrderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT_ID);
			pstmt.setInt(1, driverOrderId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				driveOrderVO = new DriveOrderVO();
				driveOrderVO.setDrverOrderId(rs.getInt("DRIVE_ORDER_ID"));
				driveOrderVO.setMemId(rs.getInt("MEM_ID"));
				driveOrderVO.setDriverId(rs.getInt("DRIVER_ID"));
				driveOrderVO.setDriverName(rs.getString("DRIVER_NAME"));
				driveOrderVO.setDriverPhone(rs.getString("DRIVER_PHONE"));
				driveOrderVO.setCarNumber(rs.getString("CAR_NUMBER"));
				driveOrderVO.setStartPoint(rs.getString("START_POINT"));
				driveOrderVO.setEndPoint(rs.getString("END_POINT"));
				driveOrderVO.setDistance(rs.getDouble("DISTANCE"));
				driveOrderVO.setSendDriveDate(rs.getDate("SEND_DRIVE_DATE"));
				driveOrderVO.setSendDriveTime(rs.getTime("SEND_DRIVE_TIME"));
				driveOrderVO.setOrderAmount(rs.getDouble("ORDER_AMOUNT"));
				driveOrderVO.setContactName(rs.getString("CONTACT_NAME"));
				driveOrderVO.setContactNumber(rs.getString("CONTACT_NUMBER"));
				driveOrderVO.setOrderStatus(rs.getString("ORDER_STATUS"));
				driveOrderVO.setDriveFeedback(rs.getString("DRIVE_FEEDBACK"));
				driveOrderVO.setCreateTime(rs.getTimestamp("CREATE_TIME"));
				driveOrderVO.setUpdateTime(rs.getTimestamp("UPDATE_TIME"));
			}
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			throw new RuntimeException(e.getMessage());
		} catch (SQLException se) {
			// TODO: handle exception
			throw new RuntimeException(se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					// TODO: handle exception
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace(System.err);
				}
			}
		}
		return driveOrderVO;
	}

	@Override
	public List<DriveOrderVO> findByDate(Date sendDriveDate) {
		// TODO Auto-generated method stub
		List<DriveOrderVO> list = new ArrayList<DriveOrderVO>();
		DriveOrderVO driveOrderVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT_DATE);
			pstmt.setDate(1, sendDriveDate);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				driveOrderVO = new DriveOrderVO();
				driveOrderVO.setDrverOrderId(rs.getInt("DRIVE_ORDER_ID"));
				driveOrderVO.setMemId(rs.getInt("MEM_ID"));
				driveOrderVO.setDriverId(rs.getInt("DRIVER_ID"));
				driveOrderVO.setStartPoint(rs.getString("START_POINT"));
				driveOrderVO.setEndPoint(rs.getString("END_POINT"));
				driveOrderVO.setDistance(rs.getDouble("DISTANCE"));
				driveOrderVO.setSendDriveDate(rs.getDate("SEND_DRIVE_DATE"));
				driveOrderVO.setSendDriveTime(rs.getTime("SEND_DRIVE_TIME"));
				driveOrderVO.setOrderAmount(rs.getDouble("ORDER_AMOUNT"));
				driveOrderVO.setContactName(rs.getString("CONTACT_NAME"));
				driveOrderVO.setContactNumber(rs.getString("CONTACT_NUMBER"));
				driveOrderVO.setOrderStatus(rs.getString("ORDER_STATUS"));
				driveOrderVO.setDriveFeedback(rs.getString("DRIVE_FEEDBACK"));
				driveOrderVO.setCreateTime(rs.getTimestamp("CREATE_TIME"));
				driveOrderVO.setUpdateTime(rs.getTimestamp("UPDATE_TIME"));
				list.add(driveOrderVO);
			}
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			throw new RuntimeException(e.getMessage());
		} catch (SQLException se) {
			// TODO: handle exception
			throw new RuntimeException(se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					// TODO: handle exception
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	@Override
	public List<DriveOrderVO> findByMemId(Integer memId) {
		// TODO Auto-generated method stub
		List<DriveOrderVO> list = new ArrayList<DriveOrderVO>();
		DriveOrderVO driveOrderVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT_MEMID);
			pstmt.setInt(1, memId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				driveOrderVO = new DriveOrderVO();
				driveOrderVO.setDrverOrderId(rs.getInt("DRIVE_ORDER_ID"));
				driveOrderVO.setMemId(rs.getInt("MEM_ID"));
				driveOrderVO.setDriverId(rs.getInt("DRIVER_ID"));
				driveOrderVO.setStartPoint(rs.getString("START_POINT"));
				driveOrderVO.setEndPoint(rs.getString("END_POINT"));
				driveOrderVO.setDistance(rs.getDouble("DISTANCE"));
				driveOrderVO.setSendDriveDate(rs.getDate("SEND_DRIVE_DATE"));
				driveOrderVO.setSendDriveTime(rs.getTime("SEND_DRIVE_TIME"));
				driveOrderVO.setOrderAmount(rs.getDouble("ORDER_AMOUNT"));
				driveOrderVO.setContactName(rs.getString("CONTACT_NAME"));
				driveOrderVO.setContactNumber(rs.getString("CONTACT_NUMBER"));
				driveOrderVO.setOrderStatus(rs.getString("ORDER_STATUS"));
				driveOrderVO.setDriveFeedback(rs.getString("DRIVE_FEEDBACK"));
				driveOrderVO.setCreateTime(rs.getTimestamp("CREATE_TIME"));
				driveOrderVO.setUpdateTime(rs.getTimestamp("UPDATE_TIME"));
				list.add(driveOrderVO);
			}
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			throw new RuntimeException(e.getMessage());
		} catch (SQLException se) {
			// TODO: handle exception
			throw new RuntimeException(se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					// TODO: handle exception
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	@Override
	public List<DriveOrderVO> findByMemIdOrder(Integer memId) {
		// TODO Auto-generated method stub
		List<DriveOrderVO> list = new ArrayList<DriveOrderVO>();
		DriveOrderVO driveOrderVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT_MEMID_ORDER);
			pstmt.setInt(1, memId);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				driveOrderVO = new DriveOrderVO();
				driveOrderVO.setDrverOrderId(rs.getInt("DRIVE_ORDER_ID"));
				driveOrderVO.setMemId(rs.getInt("MEM_ID"));
				driveOrderVO.setDriverId(rs.getInt("DRIVER_ID"));
				driveOrderVO.setStartPoint(rs.getString("START_POINT"));
				driveOrderVO.setEndPoint(rs.getString("END_POINT"));
				driveOrderVO.setDistance(rs.getDouble("DISTANCE"));
				driveOrderVO.setSendDriveDate(rs.getDate("SEND_DRIVE_DATE"));
				driveOrderVO.setSendDriveTime(rs.getTime("SEND_DRIVE_TIME"));
				driveOrderVO.setOrderAmount(rs.getDouble("ORDER_AMOUNT"));
				driveOrderVO.setContactName(rs.getString("CONTACT_NAME"));
				driveOrderVO.setContactNumber(rs.getString("CONTACT_NUMBER"));
				driveOrderVO.setOrderStatus(rs.getString("ORDER_STATUS"));
				driveOrderVO.setDriveFeedback(rs.getString("DRIVE_FEEDBACK"));
				driveOrderVO.setCreateTime(rs.getTimestamp("CREATE_TIME"));
				driveOrderVO.setUpdateTime(rs.getTimestamp("UPDATE_TIME"));
				list.add(driveOrderVO);
			}
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			throw new RuntimeException(e.getMessage());
		} catch (SQLException se) {
			// TODO: handle exception
			throw new RuntimeException(se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					// TODO: handle exception
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	@Override
	public List<DriveOrderVO> findByMemIdStatus(String orderStatus,Integer memId) {
		// TODO Auto-generated method stub
		List<DriveOrderVO> list = new ArrayList<DriveOrderVO>();
		DriveOrderVO driveOrderVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT_MEMID_STATUS);
			pstmt.setInt(1, memId);
			pstmt.setString(2, orderStatus);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				driveOrderVO = new DriveOrderVO();
				driveOrderVO.setDrverOrderId(rs.getInt("DRIVE_ORDER_ID"));
				driveOrderVO.setMemId(rs.getInt("MEM_ID"));
				driveOrderVO.setDriverId(rs.getInt("DRIVER_ID"));
				driveOrderVO.setStartPoint(rs.getString("START_POINT"));
				driveOrderVO.setEndPoint(rs.getString("END_POINT"));
				driveOrderVO.setDistance(rs.getDouble("DISTANCE"));
				driveOrderVO.setSendDriveDate(rs.getDate("SEND_DRIVE_DATE"));
				driveOrderVO.setSendDriveTime(rs.getTime("SEND_DRIVE_TIME"));
				driveOrderVO.setOrderAmount(rs.getDouble("ORDER_AMOUNT"));
				driveOrderVO.setContactName(rs.getString("CONTACT_NAME"));
				driveOrderVO.setContactNumber(rs.getString("CONTACT_NUMBER"));
				driveOrderVO.setOrderStatus(rs.getString("ORDER_STATUS"));
				driveOrderVO.setDriveFeedback(rs.getString("DRIVE_FEEDBACK"));
				driveOrderVO.setCreateTime(rs.getTimestamp("CREATE_TIME"));
				driveOrderVO.setUpdateTime(rs.getTimestamp("UPDATE_TIME"));
				list.add(driveOrderVO);
			}
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			throw new RuntimeException(e.getMessage());
		} catch (SQLException se) {
			// TODO: handle exception
			throw new RuntimeException(se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					// TODO: handle exception
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	
	//透過會員ID與日期搜尋訂單
			@Override
			public List<DriveOrderVO> findByMemIdDate(Date sendDriveDate,Integer memId) {
				
				List<DriveOrderVO> list = new ArrayList<DriveOrderVO>();
				DriveOrderVO driveOrderVO = null;
				
				Connection con = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				
				try {
					Class.forName(Util.DRIVER);
					con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
					pstmt = con.prepareStatement(GET_ALL_STMT_MEMID_DATE);
					pstmt.setInt(1, memId);
					pstmt.setDate(2, sendDriveDate);
					
					rs = pstmt.executeQuery();
					
					while (rs.next()) {
						driveOrderVO = new DriveOrderVO();
						driveOrderVO.setDrverOrderId(rs.getInt("DRIVE_ORDER_ID"));
						driveOrderVO.setMemId(rs.getInt("MEM_ID"));
						driveOrderVO.setDriverId(rs.getInt("DRIVER_ID"));
						driveOrderVO.setStartPoint(rs.getString("START_POINT"));
						driveOrderVO.setEndPoint(rs.getString("END_POINT"));
						driveOrderVO.setDistance(rs.getDouble("DISTANCE"));
						driveOrderVO.setSendDriveDate(rs.getDate("SEND_DRIVE_DATE"));
						driveOrderVO.setSendDriveTime(rs.getTime("SEND_DRIVE_TIME"));
						driveOrderVO.setOrderAmount(rs.getDouble("ORDER_AMOUNT"));
						driveOrderVO.setContactName(rs.getString("CONTACT_NAME"));
						driveOrderVO.setContactNumber(rs.getString("CONTACT_NUMBER"));
						driveOrderVO.setOrderStatus(rs.getString("ORDER_STATUS"));
						driveOrderVO.setDriveFeedback(rs.getString("DRIVE_FEEDBACK"));
						driveOrderVO.setCreateTime(rs.getTimestamp("CREATE_TIME"));
						driveOrderVO.setUpdateTime(rs.getTimestamp("UPDATE_TIME"));
						list.add(driveOrderVO);
					}
				} catch (ClassNotFoundException e) {
					// TODO: handle exception
					throw new RuntimeException(e.getMessage());
				} catch (SQLException se) {
					// TODO: handle exception
					throw new RuntimeException(se.getMessage());
				} finally {
					if (pstmt != null) {
						try {
							pstmt.close();
						} catch (SQLException se) {
							// TODO: handle exception
							se.printStackTrace(System.err);
						}
					}
					if (con != null) {
						try {
							con.close();
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace(System.err);
						}
					}
				}
				return list;
			}
			
	@Override
	public List<DriveOrderVO> findByDriverId(Integer driverId) {
		// TODO Auto-generated method stub
		List<DriveOrderVO> list = new ArrayList<DriveOrderVO>();
		DriveOrderVO driveOrderVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT_DRIVERID);
			pstmt.setInt(1, driverId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				driveOrderVO = new DriveOrderVO();
				driveOrderVO.setDrverOrderId(rs.getInt("DRIVE_ORDER_ID"));
				driveOrderVO.setMemId(rs.getInt("MEM_ID"));
				driveOrderVO.setDriverId(rs.getInt("DRIVER_ID"));
				driveOrderVO.setStartPoint(rs.getString("START_POINT"));
				driveOrderVO.setEndPoint(rs.getString("END_POINT"));
				driveOrderVO.setDistance(rs.getDouble("DISTANCE"));
				driveOrderVO.setSendDriveDate(rs.getDate("SEND_DRIVE_DATE"));
				driveOrderVO.setSendDriveTime(rs.getTime("SEND_DRIVE_TIME"));
				driveOrderVO.setOrderAmount(rs.getDouble("ORDER_AMOUNT"));
				driveOrderVO.setContactName(rs.getString("CONTACT_NAME"));
				driveOrderVO.setContactNumber(rs.getString("CONTACT_NUMBER"));
				driveOrderVO.setOrderStatus(rs.getString("ORDER_STATUS"));
				driveOrderVO.setDriveFeedback(rs.getString("DRIVE_FEEDBACK"));
				driveOrderVO.setCreateTime(rs.getTimestamp("CREATE_TIME"));
				driveOrderVO.setUpdateTime(rs.getTimestamp("UPDATE_TIME"));
				list.add(driveOrderVO);
			}
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			throw new RuntimeException(e.getMessage());
		} catch (SQLException se) {
			// TODO: handle exception
			throw new RuntimeException(se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					// TODO: handle exception
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	
	@Override
	public List<DriveOrderVO> findByStatus(String orderStatus) {
		// TODO Auto-generated method stub
				List<DriveOrderVO> list = new ArrayList<DriveOrderVO>();
				DriveOrderVO driveOrderVO = null;

				Connection con = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;

				try {
					Class.forName(Util.DRIVER);
					con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
					pstmt = con.prepareStatement(GET_ALL_STMT_STATUS);
					pstmt.setString(1, orderStatus);

					rs = pstmt.executeQuery();

					while (rs.next()) {
						driveOrderVO = new DriveOrderVO();
						driveOrderVO.setDrverOrderId(rs.getInt("DRIVE_ORDER_ID"));
						driveOrderVO.setMemId(rs.getInt("MEM_ID"));
						driveOrderVO.setDriverId(rs.getInt("DRIVER_ID"));
						driveOrderVO.setStartPoint(rs.getString("START_POINT"));
						driveOrderVO.setEndPoint(rs.getString("END_POINT"));
						driveOrderVO.setDistance(rs.getDouble("DISTANCE"));
						driveOrderVO.setSendDriveDate(rs.getDate("SEND_DRIVE_DATE"));
						driveOrderVO.setSendDriveTime(rs.getTime("SEND_DRIVE_TIME"));
						driveOrderVO.setOrderAmount(rs.getDouble("ORDER_AMOUNT"));
						driveOrderVO.setContactName(rs.getString("CONTACT_NAME"));
						driveOrderVO.setContactNumber(rs.getString("CONTACT_NUMBER"));
						driveOrderVO.setOrderStatus(rs.getString("ORDER_STATUS"));
						driveOrderVO.setDriveFeedback(rs.getString("DRIVE_FEEDBACK"));
						driveOrderVO.setCreateTime(rs.getTimestamp("CREATE_TIME"));
						driveOrderVO.setUpdateTime(rs.getTimestamp("UPDATE_TIME"));
						list.add(driveOrderVO);
					}
				} catch (ClassNotFoundException e) {
					// TODO: handle exception
					throw new RuntimeException(e.getMessage());
				} catch (SQLException se) {
					// TODO: handle exception
					throw new RuntimeException(se.getMessage());
				} finally {
					if (pstmt != null) {
						try {
							pstmt.close();
						} catch (SQLException se) {
							// TODO: handle exception
							se.printStackTrace(System.err);
						}
					}
					if (con != null) {
						try {
							con.close();
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace(System.err);
						}
					}
				}
				return list;
	}

	@Override
	public List<DriveOrderVO> getAll() {
		// TODO Auto-generated method stub
		List<DriveOrderVO> list = new ArrayList<DriveOrderVO>();
		DriveOrderVO driveOrderVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				driveOrderVO = new DriveOrderVO();
				driveOrderVO.setDrverOrderId(rs.getInt("DRIVE_ORDER_ID"));
				driveOrderVO.setMemId(rs.getInt("MEM_ID"));
				driveOrderVO.setDriverId(rs.getInt("DRIVER_ID"));
				driveOrderVO.setStartPoint(rs.getString("START_POINT"));
				driveOrderVO.setEndPoint(rs.getString("END_POINT"));
				driveOrderVO.setDistance(rs.getDouble("DISTANCE"));
				driveOrderVO.setSendDriveDate(rs.getDate("SEND_DRIVE_DATE"));
				driveOrderVO.setSendDriveTime(rs.getTime("SEND_DRIVE_TIME"));
				driveOrderVO.setOrderAmount(rs.getDouble("ORDER_AMOUNT"));
				driveOrderVO.setContactName(rs.getString("CONTACT_NAME"));
				driveOrderVO.setContactNumber(rs.getString("CONTACT_NUMBER"));
				driveOrderVO.setOrderStatus(rs.getString("ORDER_STATUS"));
				driveOrderVO.setDriveFeedback(rs.getString("DRIVE_FEEDBACK"));
				driveOrderVO.setCreateTime(rs.getTimestamp("CREATE_TIME"));
				driveOrderVO.setUpdateTime(rs.getTimestamp("UPDATE_TIME"));
				list.add(driveOrderVO);
			}
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			throw new RuntimeException(e.getMessage());
		} catch (SQLException se) {
			// TODO: handle exception
			throw new RuntimeException(se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					// TODO: handle exception
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	
	
	public static void main(String[] args) {

		DriveOrderJDBCDAOImpl dao = new DriveOrderJDBCDAOImpl();

//		�s�W
//		DriveOrderVO driverOrderVO1 = new DriveOrderVO();
//		driverOrderVO1.setMemId(2);
//		driverOrderVO1.setDriverId(6);
//		driverOrderVO1.setStartPoint("我家");
//		driverOrderVO1.setEndPoint("他家");
//		driverOrderVO1.setDistance(new Double(6.8));
//		driverOrderVO1.setSendDriveDate(java.sql.Date.valueOf("2022-02-21"));
//		driverOrderVO1.setSendDriveTime(java.sql.Time.valueOf("19:00:00"));
//		driverOrderVO1.setOrderAmount(new Double(250));
//		driverOrderVO1.setContactName("誰啦");
//		driverOrderVO1.setContactNumber("0122333444");
//		
//		dao.insert(driverOrderVO1);

//		�ק�

//		DriverOrderVO driverOrderVO2 = dao.findByOrderId(1);
//		driverOrderVO2.setDriverId(7);
//		driverOrderVO2.setSendDriveDate(java.sql.Date.valueOf("2022-01-16"));
//		driverOrderVO2.setSendDriveTime(java.sql.Time.valueOf("16:18:00"));
//		driverOrderVO2.setContactName("��");
//		driverOrderVO2.setContactNumber("0122333444");
//		driverOrderVO2.setDriveFeedback("������s����");
//		driverOrderVO2.setOrderStatus("0");
//		driverOrderVO2.setUpdateTime(java.sql.Timestamp.valueOf("2022-01-17 19:20:41"));
//		
//		dao.update(driverOrderVO2);

//		�z�L�q��ID�d��
//		DriveOrderVO driverOrderVO3 = dao.findByOrderId(1);
//		System.out.print(driverOrderVO3.getDrverOrderId()+",");
//		System.out.print(driverOrderVO3.getMemId()+",");
//		System.out.print(driverOrderVO3.getDriverId()+",");
//		System.out.print(driverOrderVO3.getDriverName() + ",");
//		System.out.print(driverOrderVO3.getDriverPhone() + ",");
//		System.out.print(driverOrderVO3.getCarNumber() + ",");
//		System.out.print(driverOrderVO3.getStartPoint()+",");
//		System.out.print(driverOrderVO3.getEndPoint()+",");
//		System.out.print(driverOrderVO3.getDistance()+",");
//		System.out.print(driverOrderVO3.getSendDriveDate()+",");
//		System.out.print(driverOrderVO3.getSendDriveTime()+",");
//		System.out.print(driverOrderVO3.getOrderAmount()+",");
//		System.out.print(driverOrderVO3.getContactName()+",");
//		System.out.print(driverOrderVO3.getContactNumber()+",");
//		System.out.print(driverOrderVO3.getOrderStatus()+",");
//		System.out.print(driverOrderVO3.getDriveFeedback()+",");
//		System.out.print(driverOrderVO3.getCreateTime()+",");
//		System.out.println(driverOrderVO3.getUpdateTime()+",");
//		System.out.println("---------------------");

//		�z�L�q�����d��
//		List<DriverOrderVO> list = dao.findByDate(java.sql.Date.valueOf("2022-01-16"));
//		for (DriverOrderVO driverOrderVO3 : list) {
//			System.out.print(driverOrderVO3.getDrverOrderId() + ",");
//			System.out.print(driverOrderVO3.getMemId() + ",");
//			System.out.print(driverOrderVO3.getDriverId() + ",");
//			System.out.print(driverOrderVO3.getStartPoint() + ",");
//			System.out.print(driverOrderVO3.getEndPoint() + ",");
//			System.out.print(driverOrderVO3.getDistance() + ",");
//			System.out.print(driverOrderVO3.getSendDriveDate() + ",");
//			System.out.print(driverOrderVO3.getSendDriveTime() + ",");
//			System.out.print(driverOrderVO3.getOrderAmount() + ",");
//			System.out.print(driverOrderVO3.getContactName() + ",");
//			System.out.print(driverOrderVO3.getContactNumber() + ",");
//			System.out.print(driverOrderVO3.getOrderStatus() + ",");
//			System.out.print(driverOrderVO3.getDriveFeedback() + ",");
//			System.out.print(driverOrderVO3.getCreateTime() + ",");
//			System.out.println(driverOrderVO3.getUpdateTime() + ",");
//			System.out.println("---------------------");
//		}
		
//		�z�L�|��ID�d��
//		List<DriveOrderVO> list = dao.findByMemId(1);
//		for (DriveOrderVO driverOrderVO3 : list) {
//			System.out.print(driverOrderVO3.getDrverOrderId() + ",");
//			System.out.print(driverOrderVO3.getMemId() + ",");
//			System.out.print(driverOrderVO3.getDriverId() + ",");
//			System.out.print(driverOrderVO3.getStartPoint() + ",");
//			System.out.print(driverOrderVO3.getEndPoint() + ",");
//			System.out.print(driverOrderVO3.getDistance() + ",");
//			System.out.print(driverOrderVO3.getSendDriveDate() + ",");
//			System.out.print(driverOrderVO3.getSendDriveTime() + ",");
//			System.out.print(driverOrderVO3.getOrderAmount() + ",");
//			System.out.print(driverOrderVO3.getContactName() + ",");
//			System.out.print(driverOrderVO3.getContactNumber() + ",");
//			System.out.print(driverOrderVO3.getOrderStatus() + ",");
//			System.out.print(driverOrderVO3.getDriveFeedback() + ",");
//			System.out.print(driverOrderVO3.getCreateTime() + ",");
//			System.out.println(driverOrderVO3.getUpdateTime() + ",");
//			System.out.println("---------------------");
//		}
		
//		�z�L�q��ID�d��
//		List<DriverOrderVO> list = dao.findByDriverId(7);
//		for (DriverOrderVO driverOrderVO3 : list) {
//			System.out.print(driverOrderVO3.getDrverOrderId() + ",");
//			System.out.print(driverOrderVO3.getMemId() + ",");
//			System.out.print(driverOrderVO3.getDriverId() + ",");
//			System.out.print(driverOrderVO3.getStartPoint() + ",");
//			System.out.print(driverOrderVO3.getEndPoint() + ",");
//			System.out.print(driverOrderVO3.getDistance() + ",");
//			System.out.print(driverOrderVO3.getSendDriveDate() + ",");
//			System.out.print(driverOrderVO3.getSendDriveTime() + ",");
//			System.out.print(driverOrderVO3.getOrderAmount() + ",");
//			System.out.print(driverOrderVO3.getContactName() + ",");
//			System.out.print(driverOrderVO3.getContactNumber() + ",");
//			System.out.print(driverOrderVO3.getOrderStatus() + ",");
//			System.out.print(driverOrderVO3.getDriveFeedback() + ",");
//			System.out.print(driverOrderVO3.getCreateTime() + ",");
//			System.out.println(driverOrderVO3.getUpdateTime() + ",");
//			System.out.println("---------------------");
//		}
		
		List<DriveOrderVO> list = dao.findByMemIdDate(java.sql.Date.valueOf("2022-01-16"), 1);
		for (DriveOrderVO driverOrderVO3 : list) {
			System.out.print(driverOrderVO3.getDrverOrderId() + ",");
			System.out.print(driverOrderVO3.getMemId() + ",");
			System.out.print(driverOrderVO3.getDriverId() + ",");
			System.out.print(driverOrderVO3.getStartPoint() + ",");
			System.out.print(driverOrderVO3.getEndPoint() + ",");
			System.out.print(driverOrderVO3.getDistance() + ",");
			System.out.print(driverOrderVO3.getSendDriveDate() + ",");
			System.out.print(driverOrderVO3.getSendDriveTime() + ",");
			System.out.print(driverOrderVO3.getOrderAmount() + ",");
			System.out.print(driverOrderVO3.getContactName() + ",");
			System.out.print(driverOrderVO3.getContactNumber() + ",");
			System.out.print(driverOrderVO3.getOrderStatus() + ",");
			System.out.print(driverOrderVO3.getDriveFeedback() + ",");
			System.out.print(driverOrderVO3.getCreateTime() + ",");
			System.out.println(driverOrderVO3.getUpdateTime() + ",");
			System.out.println("---------------------");
		}
		
//		�d�ߩҦ��q��
//		List<DriverOrderVO> list = dao.getAll();
//		for (DriverOrderVO driverOrderVO3 : list) {
//			System.out.print(driverOrderVO3.getDrverOrderId() + ",");
//			System.out.print(driverOrderVO3.getMemId() + ",");
//			System.out.print(driverOrderVO3.getDriverId() + ",");
//			System.out.print(driverOrderVO3.getStartPoint() + ",");
//			System.out.print(driverOrderVO3.getEndPoint() + ",");
//			System.out.print(driverOrderVO3.getDistance() + ",");
//			System.out.print(driverOrderVO3.getSendDriveDate() + ",");
//			System.out.print(driverOrderVO3.getSendDriveTime() + ",");
//			System.out.print(driverOrderVO3.getOrderAmount() + ",");
//			System.out.print(driverOrderVO3.getContactName() + ",");
//			System.out.print(driverOrderVO3.getContactNumber() + ",");
//			System.out.print(driverOrderVO3.getOrderStatus() + ",");
//			System.out.print(driverOrderVO3.getDriveFeedback() + ",");
//			System.out.print(driverOrderVO3.getCreateTime() + ",");
//			System.out.println(driverOrderVO3.getUpdateTime() + ",");
//			System.out.println("---------------------");
//		}
	}

}
