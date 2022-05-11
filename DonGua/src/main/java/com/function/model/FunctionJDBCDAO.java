package com.function.model;

import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FunctionJDBCDAO implements FunctionDAO {

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
	
	private static final String GET_ALL_FUNCTION = "SELECT FUNCTION_NO,FUNCTION_NAME FROM CFA104G6.FUNCTION";

	@Override
	public List<FunctionVO> getAll() {
		List<FunctionVO> list = new ArrayList<FunctionVO>();
		FunctionVO functionVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_FUNCTION);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				functionVO = new FunctionVO();
				functionVO.setFunctionNo(rs.getString("FUNCTION_NO"));
				functionVO.setFunctionName(rs.getString("FUNCTION_NAME"));
				
				list.add(functionVO); // Store the row in the list
			}

		} catch (SQLException se) {
			throw new RuntimeException(se.getMessage());
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
		return list;

	}
}
