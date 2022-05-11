package com.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.Executor;

public class ConnectionInstance {
	static Connection con=null;
    public static Connection getConnection()
    {
        try {
			if (con != null && !(con.isClosed())) return con;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        // get db, user, pass from settings file
        return getConnection("CFA104G6", SQLUtils.USER,SQLUtils.PASSWORD);
    }

    private static Connection getConnection(String db_name,String user_name,String password)
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/"+db_name+"?user="+user_name+"&password="+password);
            System.out.println("Refresh Connection");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return con;        
    }
}
