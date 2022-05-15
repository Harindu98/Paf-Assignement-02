package com;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class PaymentApi
 */
@WebServlet("/PaymentApi")
public class PaymentApi extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	EmployeeDao emp = new EmployeeDao();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaymentApi() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		String output = emp.InsertDao(
				request.getParameter("EMP_ID"),
				request.getParameter("EMP_Age"),
				request.getParameter("EMP_Name"),
				request.getParameter("EMP_Deparment"),
				request.getParameter("EMP_DOB"),
				request.getParameter("EMP_Email"),
				request.getParameter("EMP_Number")
		);
		response.getWriter().write(output);
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map paras = getParasMap(request);
		String output = emp.changeEmployee(
				Integer.parseInt(paras.get("EMP_ID").toString()),
				Integer.parseInt(paras.get("EMP_Age").toString()),
				paras.get("EMP_Name").toString(),
				paras.get("EMP_Deparment").toString(),
				paras.get("EMP_DOB").toString(),
				paras.get("EMP_Email").toString(),
				paras.get("EMP_Number").toString()
		);
		response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map paras = getParasMap(request);
		String output =emp.DeleteEmployeeDao(Integer.parseInt(paras.get("EMP_ID").toString()));
		response.getWriter().write(output);
	}
	private static Map getParasMap(HttpServletRequest request)
	{
		Map<String, String> map = new HashMap<String, String>();
		try
		{
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
			String queryString = scanner.hasNext() ?
					scanner.useDelimiter("\\A").next() : "";
			scanner.close();
			String[] params = queryString.split("&");
			for (String param : params)
			{

				String[] p = param.split("=");
				map.put(p[0], p[1]);
			}
		}
		catch (Exception e)
		{
			System.out.print("Error!!");
		}
		return map;
	}
}
