package com.revature.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.dao.EmployeesDao;
import com.revature.dao.EmployeesDaoImpl;
import com.revature.dao.ReimbursementsDao;
import com.revature.dao.ReimbursementsDaoImpl;
import com.revature.models.Employees;
import com.revature.models.Reimbursements;

public class EditProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public EditProfileServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		if (session != null) {
			request.getRequestDispatcher("Views/editProfile.html").forward(request, response);
		} else {
			response.sendRedirect("login");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
		
		int empId = Integer.parseInt(request.getParameter("id"));
		String empName = request.getParameter("name");
		
		Employees e = new Employees();
		e.setId(empId);
		e.setName(empName);
		
		EmployeesDao ed = new EmployeesDaoImpl();
		int updatedEmployee = ed.updateEmployeeById(empId);
//		int updatedEmployee = ed.updateEmployeeByName(empName);
		System.out.println(updatedEmployee);
//		
		if (updatedEmployee == 1) {
			response.sendRedirect("employeehome");
		} else {
			response.sendRedirect("profile");
		}
	}

}
