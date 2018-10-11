package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.ReimbursementsDao;
import com.revature.dao.ReimbursementsDaoImpl;
import com.revature.models.Reimbursements;
import com.revature.util.ReimbursementManager;
import com.revature.util.SessionManager;

/**
 * Servlet implementation class EmpERBsServlet
 */
public class EmpERBsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmpERBsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		SessionManager sm = new SessionManager();
		int id = (Integer) session.getAttribute("id");
		
		ReimbursementsDao emp = new ReimbursementsDaoImpl();
		
		ObjectMapper om = new ObjectMapper();
		PrintWriter pw = response.getWriter();
		
//		int empId = Integer.parseInt(employeeId);
		
		List<Reimbursements> reimbursementsList = emp.getReimbursementsByEmployeeId(id);
		System.out.println(reimbursementsList);
		boolean userExistsInSession = sm.validateUserExistence(request);
		if (userExistsInSession) {
		ReimbursementManager rm = new ReimbursementManager();
		reimbursementsList = rm.connectEmployeeERB(reimbursementsList);
		String erbsString = om.writeValueAsString(reimbursementsList);
		erbsString = "{\"erbemployee\": " + erbsString + "}";
		pw.write(erbsString);
		} else {
			System.out.println("nope");
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
