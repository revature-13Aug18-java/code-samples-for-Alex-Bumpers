package com.revature.tests;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import com.revature.dao.ReimbursementsDaoImpl;
import com.revature.models.Reimbursements;
import com.revature.util.ConnectionUtil;

public class AppUnitTests {
	
	@Test
	public void canCreateReimbursement() {
		try (Connection con = ConnectionUtil.getConnection()) {
			Reimbursements r = new Reimbursements();
			r.setReimbursementId(1);
			r.setReimbursementAmount(300);
			r.setReimbursementStatus("DENIED");
			r.setManagerId(2);
			r.setEmployeeId(4);
			
			ReimbursementsDaoImpl rdi = new ReimbursementsDaoImpl();
			assertEquals(1, rdi.createReimbursement(r));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void canDenyReimbursementById() {
		try (Connection con = ConnectionUtil.getConnection()) {
			Reimbursements r = new Reimbursements();
			r.setEmployeeId(1);
			ReimbursementsDaoImpl rdi = new ReimbursementsDaoImpl();
			assertEquals(1, rdi.denyReimbursementByErbId(1));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void canApproveReimbursementById() {
		try (Connection con = ConnectionUtil.getConnection()) {
			Reimbursements r = new Reimbursements();
			r.setEmployeeId(1);
			ReimbursementsDaoImpl rdi = new ReimbursementsDaoImpl();
			assertEquals(1, rdi.approveReimbursementByErbId(1));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
