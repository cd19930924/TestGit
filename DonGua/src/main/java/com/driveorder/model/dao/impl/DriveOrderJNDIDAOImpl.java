package com.driveorder.model.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.driveorder.model.dao.DriveOrderDAO;
import com.driveorder.model.vo.DriveOrderVO;

@Repository
public class DriveOrderJNDIDAOImpl implements DriveOrderDAO {
	
	@Autowired
	private static DataSource ds = null;
	
//	static {
//		try {
//			Context ctx = new InitialContext();
//			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DaliyWarm");
//		} catch (NamingException e) {
//			e.printStackTrace();
//		}
//	}
	
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

	//新增派車訂單
	@Override
	public DriveOrderVO insert(DriveOrderVO driveOrderVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
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
			
		} catch (SQLException e) {
			// TODO: handle exception
			throw new RuntimeException(e.getMessage());
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

	//修改派車訂單
	@Override
	public void update(DriveOrderVO driveOrderVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setInt(1, driveOrderVO.getDriverId());
			pstmt.setDate(2, driveOrderVO.getSendDriveDate());
			pstmt.setTime(3, driveOrderVO.getSendDriveTime());
			pstmt.setString(4, driveOrderVO.getContactName());
			pstmt.setString(5, driveOrderVO.getContactNumber());
			pstmt.setString(6, driveOrderVO.getOrderStatus());
			pstmt.setString(7, driveOrderVO.getDriveFeedback());
			pstmt.setTimestamp(8, new Timestamp(System.currentTimeMillis()));
			pstmt.setInt(9, driveOrderVO.getDrverOrderId());

			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			throw new RuntimeException(e.getMessage());
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

	//透過派車訂單ID搜尋派車訂單
	@Override
	public DriveOrderVO findByOrderId(Integer driverOrderId) {
		
		DriveOrderVO driveOrderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
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
		} catch (SQLException e) {
			// TODO: handle exception
			throw new RuntimeException(e.getMessage());
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

	//透過派車訂單日期搜尋訂單
	@Override
	public List<DriveOrderVO> findByDate(Date sendDriveDate) {
		
		List<DriveOrderVO> list = new ArrayList<DriveOrderVO>();
		DriveOrderVO driveOrderVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
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
		} catch (SQLException e) {
			// TODO: handle exception
			throw new RuntimeException(e.getMessage());
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
	
	//透過會員ID搜尋派車訂單
	@Override
	public List<DriveOrderVO> findByMemId(Integer memId) {
		
		List<DriveOrderVO> list = new ArrayList<DriveOrderVO>();
		DriveOrderVO driveOrderVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
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
		} catch (SQLException e) {
			// TODO: handle exception
			throw new RuntimeException(e.getMessage());
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
	
	//透過會員ID搜尋派車訂單並透過訂單狀態排序
	@Override
	public List<DriveOrderVO> findByMemIdOrder(Integer memId) {
		
		List<DriveOrderVO> list = new ArrayList<DriveOrderVO>();
		DriveOrderVO driveOrderVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
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
		} catch (SQLException e) {
			// TODO: handle exception
			throw new RuntimeException(e.getMessage());
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
	
	//透過會員ID與訂單狀態搜尋訂單
	@Override
	public List<DriveOrderVO> findByMemIdStatus(String orderStatus,Integer memId) {
		
		List<DriveOrderVO> list = new ArrayList<DriveOrderVO>();
		DriveOrderVO driveOrderVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
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
		} catch (SQLException e) {
			// TODO: handle exception
			throw new RuntimeException(e.getMessage());
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
				con = ds.getConnection();
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
			} catch (SQLException e) {
				// TODO: handle exception
				throw new RuntimeException(e.getMessage());
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

	//透過司機ID搜尋派車訂單
	@Override
	public List<DriveOrderVO> findByDriverId(Integer driverId) {
		
		List<DriveOrderVO> list = new ArrayList<DriveOrderVO>();
		DriveOrderVO driveOrderVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
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
		} catch (SQLException e) {
			// TODO: handle exception
			throw new RuntimeException(e.getMessage());
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
	
	//透過訂單狀態搜尋派車訂單
	@Override
	public List<DriveOrderVO> findByStatus(String orderStatus) {
		
				List<DriveOrderVO> list = new ArrayList<DriveOrderVO>();
				DriveOrderVO driveOrderVO = null;

				Connection con = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;

				try {
					con = ds.getConnection();
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
				} catch (SQLException e) {
					// TODO: handle exception
					throw new RuntimeException(e.getMessage());
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

	//搜尋所有派車訂單
	@Override
	public List<DriveOrderVO> getAll() {
		
		List<DriveOrderVO> list = new ArrayList<DriveOrderVO>();
		DriveOrderVO driveOrderVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
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
		} catch (SQLException e) {
			// TODO: handle exception
			throw new RuntimeException(e.getMessage());
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

}
