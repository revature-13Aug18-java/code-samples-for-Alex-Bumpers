package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.EmployeesDao;
import com.revature.dao.EmployeesDaoImpl;
import com.revature.dao.ReimbursementsDao;
import com.revature.dao.ReimbursementsDaoImpl;
import com.revature.models.Employees;
import com.revature.models.Reimbursements;
import com.revature.util.ReimbursementManager;
import com.revature.util.SessionManager;

public class ViewEmployeesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
//
    public ViewEmployeesServlet() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		if (session != null) {
			request.getRequestDispatcher("Views/allEmployees.html").forward(request, response);
		} else {
			response.sendRedirect("login");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	