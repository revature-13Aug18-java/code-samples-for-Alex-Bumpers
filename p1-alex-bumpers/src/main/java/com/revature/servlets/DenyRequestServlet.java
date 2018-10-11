package com.revature.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.dao.ReimbursementsDao;
import com.revature.dao.ReimbursementsDaoImpl;
import com.revature.models.Reimbursements;

/**
 * Servlet implementation class DenyRequestServlet
 */
public class DenyRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public DenyRequestServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
		
		int reimbursementId = Integer.parseInt(request.getParameter("reimbursementId"));
		String reimbursementStatus = request.getParameter("reimbursementStatus");
		
		// denies
		Reimbursements r = new Reimbursements();
		r.setReimbursementId(reimbursementId);
		r.setReimbursementStatus(reimbursementStatus);
		
		ReimbursementsDao rd = new ReimbursementsDaoImpl();
		int deniedReimbursements = rd.denyReimbursementByErbId(reimbursementId);
		System.out.println(deniedReimbursements);
//		
		if (deniedReimbursements==1) {
			response.sendRedirect("managerhome");
		} else {
			response.sendRedirect("create");
		}
		
	}

}
