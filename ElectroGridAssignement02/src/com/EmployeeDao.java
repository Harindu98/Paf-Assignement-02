package com;

import com.jersy.dbconnect.dbConnection;

import java.sql.*;

public class EmployeeDao {


    public String InsertDao(String id, String age, String name, String empD, String DOB, String email, String number) {

        String output = "";

        try {
            //DB connection
            Connection con = dbConnection.connect();
            if (con == null) {
                return "Error while connecting to the database";
            }

            PreparedStatement ps = con.prepareStatement("insert into Employee values(?,?,?,?, ?,?, ?)"); //insert data feild for table
            ps.setInt(1, Integer.parseInt(id));
            ps.setString(2, name);
            ps.setInt(3, Integer.parseInt(age));
            ps.setString(4, empD);
            ps.setString(5, DOB);
            ps.setString(6, email);
            ps.setString(7, number);
            //execute the statement
            ps.execute();
            con.close();
            String newProjects = readEmployee();
            output = "{\"status\":\"success\", \"data\": \"" + newProjects + "\"}";
        } catch (Exception e) {
            output = "{\"status\":\"error\", \"data\": \"Error while inserting the projects.\"}";
            System.err.println(e.getMessage());
        }
        return output;


    }


    public String DeleteEmployeeDao(int id) {
        String output = "";
        Connection con = dbConnection.connect();
        PreparedStatement ps = null; //sql query code for filtering data
        try {
            ps = con.prepareStatement("DELETE FROM Employee WHERE EmpID=?");
            ps.setInt(1, id);
            ps.executeUpdate();

            String newProjects = readEmployee();
            output = "{\"status\":\"success\", \"data\": \"" + newProjects + "\"}";
        } catch (Exception e) {
            output = "{\"status\":\"error\", \"data\": \"Error while deleting the project.\"}";
            System.err.println(e.getMessage());
        }
        return output;


    }


    public String changeEmployee(int id, int age, String name, String empD, String DOB, String email, String number) {
        Connection con = dbConnection.connect();
        String output = "";
        try {

            PreparedStatement ps = con.prepareStatement("update Employee set  EmpName=? , EmpAge = ? , EmpDept= ? , EmpDOB =? , EmpEmail = ? , ContractNum = ? where EmpId=?");
            ps.setInt(7, id);
            ps.setString(1, name);
            ps.setInt(2, age);
            ps.setString(3, empD);
            ps.setString(4, DOB);
            ps.setString(5, email);
            ps.setString(6, number);
            ps.executeUpdate();


            String newProjects = readEmployee();
            output = "{\"status\":\"success\", \"data\": \"" + newProjects + "\"}";
        } catch (Exception e) {
            output = "{\"status\":\"error\", \"data\": \"Error while inserting the projects.\"}";
            System.err.println(e.getMessage());
        }
        return output;


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
                    + "<th>EmpDept</th>" + "<th>EmpDOB</th>" + "<th>EmpEmail</th>" + " <th>ContractNum</th>" + "<th>Update</th><th>Remove</th></tr>";
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