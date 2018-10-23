package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.EmployeesDao;
import com.revature.dao.EmployeesDaoImpl;
import com.revature.models.Employees;
import com.revature.models.Reimbursements;
import com.revature.util.ReimbursementManager;
import com.revature.util.SessionManager;

/**
 * Servlet implementation class GetAllEmployeesServlet
 */
public class GetAllEmployeesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAllEmployeesServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		SessionManager sm = new SessionManager();

		String name = request.getParameter("name");
		String employeeId = request.getParameter("id");
		String position = request.getParameter("position");
		String username = request.getParameter("username");
		String action = request.getParameter("action");

		ObjectMapper om = new ObjectMapper();
		PrintWriter pw = response.getWriter();

		EmployeesDao ed = new EmployeesDaoImpl();
		List<Employees> employees = new ArrayList<Employees>();

		if (action != null && employeeId != null) {

			try {
				int employeeIdNumber = Integer.parseInt(employeeId);
				Employees e = ed.getEmployeesById(employeeIdNumber);

			} catch (Exception e) {
				System.out.println("Please check employeeId!");
				pw.write("N/A");
			}
		}
		
		employees = ed.getAllEmployees();

//		
		

		boolean userExistsInSession = sm.validateUserExistence(request);

		if (userExistsInSession) {
			String employeesString = om.writeValueAsString(employees);
			employeesString = "{\"employees\": " + employeesString + "}";
			pw.write(employeesString);
		} else {
			response.sendRedirect("login");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
