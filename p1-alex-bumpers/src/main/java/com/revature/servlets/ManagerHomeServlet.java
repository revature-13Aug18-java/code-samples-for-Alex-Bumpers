package com.revature.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.dao.ReimbursementsDao;
import com.revature.dao.ReimbursementsDaoImpl;
import com.revature.models.Reimbursements;

/**
 * Servlet implementation class ManagerHomeServlet
 */
public class ManagerHomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManagerHomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		
		if (session != null) {
			request.getRequestDispatcher("Views/managerHome.html").forward(request, response);
		} else {
			response.sendRedirect("login");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
// start post process
		
		doGet(request, response);
		
		int reimbursementId = Integer.parseInt(request.getParameter("reimbursementId"));
		String reimbursementStatus = request.getParameter("reimbursementStatus");
		
		// APPROVES
		Reimbursements r = new Reimbursements();
		r.setReimbursementId(reimbursementId);
		r.setReimbursementStatus(reimbursementStatus);
		
		ReimbursementsDao rd = new ReimbursementsDaoImpl();
		int approvedReimbursements = rd.approveReimbursementByErbId(reimbursementId);
		System.out.println(approvedReimbursements);
//		
		if (approvedReimbursements==1) {
			response.sendRedirect("managerhome");
		} else {
			response.sendRedirect("create");
		}
		
		

	}
	

}
