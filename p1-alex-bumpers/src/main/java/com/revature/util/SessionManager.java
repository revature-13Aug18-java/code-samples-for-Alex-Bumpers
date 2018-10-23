package com.revature.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.revature.dao.EmployeesDao;
import com.revature.dao.EmployeesDaoImpl;
import com.revature.dao.ReimbursementsDao;
import com.revature.dao.ReimbursementsDaoImpl;
import com.revature.models.Employees;
import com.revature.models.Reimbursements;

public class SessionManager {

	public void createSessionForManagerAccounts(String username, HttpSession session) {
		EmployeesDao ed = new EmployeesDaoImpl();
		Employees employee = ed.getEmployeeByUsername(username);
		
		session.setAttribute("isManager", employee.getIsManager());
		session.setAttribute("id", employee.getId());
		session.setAttribute("name", employee.getName());
		session.setAttribute("position", employee.getPosition());
	}

	// validates that user does indeed exist and has a valid account with associated
	// data, then sets the user session to true
	public boolean validateUserExistence(HttpServletRequest request) {
		boolean userExists = false;
		HttpSession session = request.getSession(false);

		if (session != null && session.getAttribute("username") != null) {
			userExists = true;
		}

		return userExists;
	}
}
