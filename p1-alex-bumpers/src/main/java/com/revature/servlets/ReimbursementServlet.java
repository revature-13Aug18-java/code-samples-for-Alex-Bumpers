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
import com.revature.dao.ReimbursementsDao;
import com.revature.dao.ReimbursementsDaoImpl;
import com.revature.models.Reimbursements;
import com.revature.util.ReimbursementManager;
import com.revature.util.SessionManager;

/**
 * Servlet implementation class ReimbursementServlet
 */
public class ReimbursementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ReimbursementServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		SessionManager sm = new SessionManager();

		String reimbursementStatus = request.getParameter("reimbursementStatus");
		String employeeId = request.getParameter("employeeId");
		String reimbursementId = request.getParameter("reimbursementId");
		String action = request.getParameter("action");

		ObjectMapper om = new ObjectMapper();
		PrintWriter pw = response.getWriter();

		ReimbursementsDao rd = new ReimbursementsDaoImpl();
		List<Reimbursements> reimbursements = new ArrayList<Reimbursements>();

		if (action != null && reimbursementId != null) {

			try {
				int reimbursementIdNumber = Integer.parseInt(reimbursementId);
				Reimbursements r = rd.getReimbursementById(reimbursementIdNumber);

				if (action.equalsIgnoreCase("approve") && r != null) {
					System.out.println("trying to remove " + r + "request");
				} else if (action.equalsIgnoreCase("deny") && r != null) {
					System.out.println("trying to remove " + r + "request");
					rd.denyReimbursementByErbId(reimbursementIdNumber);
				} else {
					pw.write("N/A");
				}

			} catch (Exception e) {
				System.out.println("Please check reimbursementId!");
				pw.write("N/A");
			}
		}

		if (reimbursementStatus != null) {
			if (reimbursementStatus.equalsIgnoreCase("pending")) {
				reimbursements = rd.getAllPendingReimbursements();
			} else if (reimbursementStatus.equalsIgnoreCase("resolved")) {
				reimbursements = rd.getAllResolvedReimbursements();
			}

			// if param for employeeId is in session, get ERB for that employeeId
		} else if (employeeId != null) {
			try {
				int id = Integer.parseInt(employeeId);
				reimbursements = rd.getAllResolvedReimbursementsEmployeeId(id);
			} catch (Exception e) {
				pw.write("N/A");
			}
		} else {
			// get all erbs if no emp id exists in params
			reimbursements = rd.getAllReimbursements();
		}

		boolean userExistsInSession = sm.validateUserExistence(request);
		ReimbursementManager rm = new ReimbursementManager();
		reimbursements = rm.connectEmployeeERB(reimbursements);

		if (userExistsInSession) {
			String reimbursementsString = om.writeValueAsString(reimbursements);
			reimbursementsString = "{\"reimbursements\": " + reimbursementsString + "}";
			pw.write(reimbursementsString);
		} else {
			response.sendRedirect("login");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// start post process
		
		doGet(request, response);
		
	}

}
