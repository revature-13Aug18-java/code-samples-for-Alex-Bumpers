package com.revature.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.dao.EmployeesDao;
import com.revature.dao.EmployeesDaoImpl;

/**
 * Servlet implementation class EmployeeHomeServlet
 */
public class EmployeeHomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public EmployeeHomeServlet() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		
		if (session != null) {
			request.getRequestDispatcher("Views/employeeHome.html").forward(request, response);
//			String employeeId = (String) session.getAttribute("id");
//			
//			EmployeesDao emp = new EmployeesDaoImpl();
//			
//			ObjectMapper om = new ObjectMapper()zzzzzzzzz
			
			
		} else {
			response.sendRedirect("login");
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
