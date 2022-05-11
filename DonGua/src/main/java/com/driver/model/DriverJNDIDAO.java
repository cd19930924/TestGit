package com.driver.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DriverJNDIDAO implements DriverDAO {
	@Autowired
	private DataSource ds;
	
//	static {
//		try {
//			Context ctx = new InitialContext();
//			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DaliyWarm");
//		} catch (NamingException e) {
//			e.printStackTrace();
//		}
//	}
	
	private static final String INSERT_STMT = 
			"INSERT INTO DRIVER (DRIVER_NAME,DRIVER_PHONE,CAR_NUMBER,DRIVER_EMAIL,SERVICE_STATUS)VALUES(?,?,?,?,?)";
	private static final String UPDATE = 
			"UPDATE DRIVER SET DRIVER_NAME = ?,DRIVER_PHONE = ?,CAR_NUMBER = ?,DRIVER_EMAIL = ?,SERVICE_STATUS = ? WHERE DRIVER_ID = ?";
	private static final String GET_ONE_STMT_ID = 
			"SELECT DRIVER_ID,DRIVER_NAME,DRIVER_PHONE,CAR_NUMBER,DRIVER_EMAIL,SERVICE_STATUS FROM  DRIVER WHERE DRIVER_ID = ?";
	private static final String GET_ONE_STMT_NAME = 
			"SELECT DRIVER_ID,DRIVER_NAME,DRIVER_PHONE,CAR_NUMBER,DRIVER_EMAIL,SERVICE_STATUS FROM  DRIVER WHERE DRIVER_NAME LIKE ?";
	private static final String GET_ALL_STMT =
			"SELECT  DRIVER_NAME, DRIVER_PHONE, CAR_NUMBER, DRIVER_EMAIL, SERVICE_STATUS ,CASE WHEN DRIVER_ID < 10 THEN CONCAT(0,`DRIVER_ID`) WHEN DRIVER_ID>=10 THEN DRIVER_ID END AS DRIVER_ID FROM DRIVER";
//	
	@Override
	public void insert(DriverVO driveVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, driveVO.getDriverName());
			pstmt.setString(2, driveVO.getDriverPhone());
			pstmt.setString(3, driveVO.getCarNumber());
			pstmt.setString(4, driveVO.getDriverEmail());
			pstmt.setString(5, driveVO.getServiceStatus());

			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());
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
	
	@Override
	public void update(DriverVO driverVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, driverVO.getDriverName());
			pstmt.setString(2, driverVO.getDriverPhone());
			pstmt.setString(3, driverVO.getCarNumber());
			pstmt.setString(4, driverVO.getDriverEmail());
			pstmt.setString(5, driverVO.getServiceStatus());
			pstmt.setInt(6, driverVO.getDriverId());

			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());
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

	@Override
	public DriverVO findById(Integer driverId) {
		DriverVO driverVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT_ID);
			
			pstmt.setInt(1,driverId);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				driverVO = new DriverVO();
				driverVO.setDriverId(rs.getInt("DRIVER_ID"));
				driverVO.setDriverName(rs.getString("DRIVER_NAME"));
				driverVO.setDriverPhone(rs.getString("DRIVER_PHONE"));
				driverVO.setCarNumber(rs.getString("CAR_NUMBER"));
				driverVO.setDriverEmail(rs.getString("DRIVER_EMAIL"));
				driverVO.setServiceStatus(rs.getString("SERVICE_STATUS"));
			}
			
		} catch (SQLException se) {
			throw new RuntimeException(se.getMessage());
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt!=null) {
				try {	
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
				
			}
			if (con!=null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
				
			}
		}
		return driverVO;
	}

	@Override
	public List<DriverVO> findByName(String driverName) {
		List<DriverVO> list = new ArrayList<DriverVO>();
		DriverVO driverVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT_NAME);
			
			pstmt.setString(1,driverName);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				driverVO = new DriverVO();
				driverVO.setDriverId(rs.getInt("DRIVER_ID"));
				driverVO.setDriverName(rs.getString("DRIVER_NAME"));
				driverVO.setDriverPhone(rs.getString("DRIVER_PHONE"));
				driverVO.setCarNumber(rs.getString("CAR_NUMBER"));
				driverVO.setDriverEmail(rs.getString("DRIVER_EMAIL"));
				driverVO.setServiceStatus(rs.getString("SERVICE_STATUS"));
				list.add(driverVO);
			}
			
		} catch (SQLException se) {
			throw new RuntimeException(se.getMessage());
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
				
			}
			if (con!=null) {
				try {
					con.close();
				} catch (Exception e) {
				}
				
			}
		}
		return list;
	}

	@Override
	public List<DriverVO> getAll() {
		List<DriverVO> list = new ArrayList<DriverVO>();
		DriverVO driverVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				driverVO = new DriverVO();
				driverVO.setDriverIdStr(rs.getString("DRIVER_ID"));
				driverVO.setDriverName(rs.getString("DRIVER_NAME"));
				driverVO.setDriverPhone(rs.getString("DRIVER_PHONE"));
				driverVO.setCarNumber(rs.getString("CAR_NUMBER"));
				driverVO.setDriverEmail(rs.getString("DRIVER_EMAIL"));
				driverVO.setServiceStatus(rs.getString("SERVICE_STATUS"));
				list.add(driverVO);
			}
			
		} catch (SQLException se) {
			throw new RuntimeException(se.getMessage());
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
				
			}
			if (con!=null) {
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
	public void serviceStatusUpdate(DriverVO driverVO) {
		final String UPDATE = "UPDATE DRIVER SET SERVICE_STATUS = ? WHERE DRIVER_ID = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, driverVO.getServiceStatus());
			pstmt.setInt(2, Integer.parseInt(driverVO.getDriverIdStr()));

			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());
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


}
