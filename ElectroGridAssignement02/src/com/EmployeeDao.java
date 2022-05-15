package com;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.jersy.dbconnect.dbConnection;

public class EmployeeDao {


	public static String InsertDao( String id, String age, String name, String empD, String DOB, String email, String number ) {

		

		Connection con = dbConnection.connect();

		try {

			PreparedStatement ps1 = con.prepareStatement("select EmpID from Employee where EmpID=?"); //SQL query for selected EmpId
			ps1.setInt(1,Integer.parseInt(id));
			ResultSet rrs = ps1.executeQuery();
			
			
				//if same id input then get error msg
			if (rrs.next()) {
				return "Already Exist EmpID";
			} else {

				
				PreparedStatement ps = con.prepareStatement("insert into Employee values(?,?,?,?, ?,?, ?)"); //insert data feild for table
				ps.setInt(1, Integer.parseInt(id));
				ps.setString(2, name);
				ps.setInt(3, Integer.parseInt(age));
				ps.setString(4, empD);
				ps.setString(5, DOB);
				ps.setString(6, email);
				ps.setString(7, number);

				int i = ps.executeUpdate();

				if (i > 0) {
					return "success rquest";
				} else {
					return "failed rquest";
				}

			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		} finally {
			try {
				con.close();
			} catch (SQLException e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		return "fail rquest";

	}
	
	
	
	
	
	public static boolean DeleteEmployeeDao(int id) {

		Connection con = dbConnection.connect();

		try {

			PreparedStatement ps = con.prepareStatement("DELETE FROM Employee WHERE EmpID=?"); //sql query code for filtering data
			ps.setInt(1, id);  //catch up only emp id keys .
			int i = ps.executeUpdate();

			if (i > 0) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}

	}

	
	
	
	
	public static boolean checkEmployee(int id) {

		Connection con = dbConnection.connect(); 

		try {

			PreparedStatement ps = con.prepareStatement("select * from Employee where EmpID=?"); //catch up empID and then can update feilds
			ps.setInt(1,id);
			
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}

	}

	//employee chnge values  part in   setup
	public static boolean changeEmployee( int id, int age, String name, String empD, String DOB, String email, String number) {

		Connection con = dbConnection.connect();

		try {

			PreparedStatement ps = con.prepareStatement("update Employee set  EmpName=? , EmpAge = ? , EmpDept= ? , EmpDOB =? , EmpEmail = ? , ContractNum = ? where EmpId=?");
			ps.setInt(7,id);
			ps.setString(1, name);
			ps.setInt(2, age);
			ps.setString(3, empD);
			ps.setString(4, DOB);
			ps.setString(5, email);
			ps.setString(6, number);
			int i = ps.executeUpdate();

			if (i > 0) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}

	}
	
	
	
	

	
	
	
	
	
	public String readEmployee() {
		String output = "";
		try {
			Connection con = dbConnection.connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}

			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>EmpID</th><th>EmpName</th>" + "<th>EmpAge</th>"
					+ "<th>EmpDept</th>" + "<th>EmpDOB</th>" + "<th>EmpEmail</th>" + " <th>ContractNum</th></tr>" + "<th>Update</th><th>Remove</th></tr>";
			String query = "select * from Employee";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				int EmpID = rs.getInt(1);
				String EmpName = rs.getString(2);
				int EmpAge = rs.getInt(3);
				String EmpDept = rs.getString(4);
				String EmpDOB = rs.getString(5);
				String EmpEmail = rs.getString(6);
				String ContractNum = rs.getString(7);

				// Add into the html table
				output += "<tr><td><input id='hidIDUpdate'" + " name='hidIDUpdate' " + " type='hidden' value='" + EmpID
						+ "'>" + EmpID + "</td>";
				output += "<td>" + EmpName + "</td>";
				output += "<td>" + EmpAge + "</td>";
				output += "<td>" + EmpDept + "</td>";
				output += "<td>" + EmpDOB + "</td>";
				output += "<td>" + EmpEmail + "</td>";
				output += "<td>" + ContractNum + "</td>";
				
				// buttons
				output += "<td><input name='btnUpdate'" + " type='button' value='Update'"
						+ "class='btnUpdate btn btn-secondary'></td>" + "<td><input name='btnRemove'"
						+ "type='button' value='Remove'" + " class='btnRemove btn btn-danger'" + " data-itemid='"
						+ EmpID + "'>" + "</td></tr>";
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the card details.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
