package com.driverschedule.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.utils.Util;


public class DriverScheduleJNDIDAOImpl implements DriverScheduleDAO {
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DaliyWarm");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO DRIVER_SCHEDULE"
			+ "(DRIVER_ID,SCHEDULE_DATE)"
			+ "VALUES(?,?)";
	private static final String UPDATE = " UPDATE DRIVER_SCHEDULE SET SCHEDULE_STATUS = ? WHERE DRIVER_ID = ? AND SCHEDULE_DATE = ?";
	private static final String GET_ALL_WORK_ID = "SELECT DRIVER_ID FROM DRIVER WHERE SERVICE_STATUS = 0";
	private static final String GET_ONE_STMT_ID = 
			"SELECT DRIVER_ID, SCHEDULE_DATE,SCHEDULE_STATUS FROM  DRIVER_SCHEDULE WHERE DRIVER_ID = ?";
	private static final String GET_ONE_STMT_DATE = 
			"SELECT DRIVER_ID, SCHEDULE_DATE,SCHEDULE_STATUS FROM  DRIVER_SCHEDULE WHERE SCHEDULE_DATE  = ?";
	private static final String GET_ONE_STMT_ID_DATE = 
			"SELECT DRIVER_ID, SCHEDULE_DATE,SCHEDULE_STATUS FROM  DRIVER_SCHEDULE WHERE DRIVER_ID = ? AND SCHEDULE_DATE = ?";
	private static final String GET_ALl_STMT = 
			"SELECT DRIVER_ID, SCHEDULE_DATE,SCHEDULE_STATUS FROM  DRIVER_SCHEDULE ORDER BY SCHEDULE_DATE,DRIVER_ID";
	private static final String REFRESH_ALl_STMT = 
			"INSERT INTO DRIVER_SCHEDULE (DRIVER_ID,SCHEDULE_DATE) SELECT * FROM (SELECT ?,?) AS TMP WHERE NOT EXISTS (SELECT DRIVER_ID,SCHEDULE_DATE FROM DRIVER_SCHEDULE WHERE DRIVER_ID = ? AND SCHEDULE_DATE = ?)LIMIT 1";
			
	@Override
	public void insert(DriverScheduleVO driverScheduleVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setInt(1, driverScheduleVO.getDriverId());
			pstmt.setDate(2, driverScheduleVO.getScheduleDate());
			pstmt.executeUpdate();
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
	public DriverScheduleVO checkSchedule(Date scheduleDate, Integer startInex, Integer endIndex) {
		DriverScheduleVO driverScheduleVO = null;
		String checkStr = "";
		
		for(int i = 0;i<endIndex;i++) {
			checkStr+="2";
		}
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		
		try {
			
			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT_DATE);
			
			pstmt.setDate(1,scheduleDate );
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				if(checkStr.equals(rs.getString("SCHEDULE_STATUS").substring(startInex, startInex+endIndex))){
					driverScheduleVO = new DriverScheduleVO();
					StringBuilder newString = new StringBuilder(rs.getString("SCHEDULE_STATUS"));
					for(int i = startInex;i<(startInex+endIndex);i++) {
						newString.setCharAt(i,'1');
					}
					driverScheduleVO.setDriverId(rs.getInt("DRIVER_ID"));
					driverScheduleVO.setScheduleDate(rs.getDate("SCHEDULE_DATE"));
					driverScheduleVO.setScheduleStatus(newString.toString());
					break;
				}
			}
			
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			throw new RuntimeException(e.getMessage());
		} catch (SQLException se) {
			// TODO: handle exception
			throw new RuntimeException(se.getMessage());
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					// TODO: handle exception
					se.printStackTrace(System.err);
				}
			}
			if (pstmt!=null) {
				try {	
					pstmt.close();
				} catch (SQLException se) {
					// TODO: handle exception
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
		return driverScheduleVO;
	
	}
	
	@Override
	public void update(DriverScheduleVO driverScheduleVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			
			pstmt.setString(1, driverScheduleVO.getScheduleStatus());
			pstmt.setInt(2, driverScheduleVO.getDriverId());
			pstmt.setDate(3, driverScheduleVO.getScheduleDate());
			pstmt.executeUpdate();
			
		} catch (SQLException se) {
			// TODO: handle exception
			throw new RuntimeException("A database error occured." + se.getMessage());
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
	public void refreshAll(List<Integer> allDriverId) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(REFRESH_ALl_STMT);
			long today = System.currentTimeMillis();
			for(int i = 1;i<=60;i++) {
				
				for(Integer driverId:allDriverId) {
					
					pstmt.setInt(1,driverId );
					pstmt.setDate(2,new java.sql.Date(today));
					pstmt.setInt(3,driverId );
					pstmt.setDate(4,new java.sql.Date(today));
					pstmt.executeUpdate();
				}
				today += 24*60*60*1000;
			}
			
			
		} catch (SQLException se) {
			// TODO: handle exception
			throw new RuntimeException(se.getMessage());
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					// TODO: handle exception
					se.printStackTrace(System.err);
				}
			}
			if (pstmt!=null) {
				try {	
					pstmt.close();
				} catch (SQLException se) {
					// TODO: handle exception
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
		
	}
	
	@Override
	public List<DriverScheduleVO> findById(Integer driverId) {
		// TODO Auto-generated method stub
		List<DriverScheduleVO> list = new ArrayList<DriverScheduleVO>();
		DriverScheduleVO driverScheduleVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT_ID);
			
			pstmt.setInt(1,driverId );
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				driverScheduleVO = new DriverScheduleVO();
				driverScheduleVO.setDriverId(rs.getInt("DRIVER_ID"));
				driverScheduleVO.setScheduleDate(rs.getDate("SCHEDULE_DATE"));
				driverScheduleVO.setScheduleStatus(rs.getString("SCHEDULE_STATUS"));
				list.add(driverScheduleVO);
			}
			
		} catch (SQLException se) {
			// TODO: handle exception
			throw new RuntimeException(se.getMessage());
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					// TODO: handle exception
					se.printStackTrace(System.err);
				}
			}
			if (pstmt!=null) {
				try {	
					pstmt.close();
				} catch (SQLException se) {
					// TODO: handle exception
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
	public List<DriverScheduleVO> findByDate(Date scheduleDate) {
		// TODO Auto-generated method stub
		List<DriverScheduleVO> list = new ArrayList<DriverScheduleVO>();
		DriverScheduleVO driverScheduleVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT_DATE);
			
			pstmt.setDate(1,scheduleDate );
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				driverScheduleVO = new DriverScheduleVO();
				driverScheduleVO.setDriverId(rs.getInt("DRIVER_ID"));
				driverScheduleVO.setScheduleDate(rs.getDate("SCHEDULE_DATE"));
				driverScheduleVO.setScheduleStatus(rs.getString("SCHEDULE_STATUS"));
				list.add(driverScheduleVO);
			}
			
		} catch (SQLException se) {
			// TODO: handle exception
			throw new RuntimeException(se.getMessage());
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					// TODO: handle exception
					se.printStackTrace(System.err);
				}
			}
			if (pstmt!=null) {
				try {	
					pstmt.close();
				} catch (SQLException se) {
					// TODO: handle exception
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
	public List<DriverScheduleVO> findByIdAndDate(Integer driverId, Date scheduleDate) {
		List<DriverScheduleVO> list = new ArrayList<DriverScheduleVO>();
		DriverScheduleVO driverScheduleVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT_ID_DATE);
			
			pstmt.setInt(1,driverId );
			pstmt.setDate(2,scheduleDate );
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				driverScheduleVO = new DriverScheduleVO();
				driverScheduleVO.setDriverId(rs.getInt("DRIVER_ID"));
				driverScheduleVO.setScheduleDate(rs.getDate("SCHEDULE_DATE"));
				driverScheduleVO.setScheduleStatus(rs.getString("SCHEDULE_STATUS"));
				list.add(driverScheduleVO);
			}
			
		} catch (SQLException se) {
			// TODO: handle exception
			throw new RuntimeException(se.getMessage());
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					// TODO: handle exception
					se.printStackTrace(System.err);
				}
			}
			if (pstmt!=null) {
				try {	
					pstmt.close();
				} catch (SQLException se) {
					// TODO: handle exception
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
	public List<Integer> findWorkDriver() {
		// TODO Auto-generated method stub
		List<Integer> list = new ArrayList<Integer>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_WORK_ID);
			
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				list.add(rs.getInt("DRIVER_ID"));
			}
			
		} catch (SQLException se) {
			// TODO: handle exception
			throw new RuntimeException(se.getMessage());
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					// TODO: handle exception
					se.printStackTrace(System.err);
				}
			}
			if (pstmt!=null) {
				try {	
					pstmt.close();
				} catch (SQLException se) {
					// TODO: handle exception
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
public List<DriverScheduleVO> getAll() {
	// TODO Auto-generated method stub
	List<DriverScheduleVO> list = new ArrayList<DriverScheduleVO>();
	DriverScheduleVO driverScheduleVO = null;
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs =null;
	
	try {
		
		con = ds.getConnection();
		pstmt = con.prepareStatement(GET_ALl_STMT);
		
		
		rs = pstmt.executeQuery();
		
		while (rs.next()) {
			driverScheduleVO = new DriverScheduleVO();
			driverScheduleVO.setDriverId(rs.getInt("DRIVER_ID"));
			driverScheduleVO.setScheduleDate(rs.getDate("SCHEDULE_DATE"));
			driverScheduleVO.setScheduleStatus(rs.getString("SCHEDULE_STATUS"));
			list.add(driverScheduleVO);
		}
		
	} catch (SQLException se) {
		// TODO: handle exception
		throw new RuntimeException(se.getMessage());
	} finally {
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException se) {
				// TODO: handle exception
				se.printStackTrace(System.err);
			}
		}
		if (pstmt!=null) {
			try {	
				pstmt.close();
			} catch (SQLException se) {
				// TODO: handle exception
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

}
