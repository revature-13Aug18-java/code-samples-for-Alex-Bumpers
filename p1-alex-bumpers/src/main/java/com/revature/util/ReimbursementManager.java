package com.revature.util;

import java.util.ArrayList;
import java.util.List;

import com.revature.dao.EmployeesDao;
import com.revature.dao.EmployeesDaoImpl;
import com.revature.models.Employees;
import com.revature.models.Reimbursements;

public class ReimbursementManager {
	
	public List<Reimbursements> connectEmployeeERB(List<Reimbursements> reimbursements) {
		List<Reimbursements> empArr = new ArrayList<>();
		empArr.addAll(reimbursements);
		EmployeesDao ed = new EmployeesDaoImpl();
		
		for (Reimbursements r : empArr) {
			Employees emp = ed.getEmployeesById(r.getEmployeeId());
			emp.setPassword("");
			r.setEmployees(emp);
			
			if (r.getManagerId() != 0) {
				Employees mgmt = ed.getEmployeesById(r.getManagerId());
				r.setManagers(mgmt);
			}
		}
		
		return empArr;
	}
}
