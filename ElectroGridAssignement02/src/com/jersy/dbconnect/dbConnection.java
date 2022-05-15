package com.jersy.dbconnect;

import java.sql.Connection;
import java.sql.DriverManager;

public class dbConnection {


	public static Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jersyproject", "root", "root2022");
			// For testing
			System.out.print("---Successfully connected DataBases---");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

}
