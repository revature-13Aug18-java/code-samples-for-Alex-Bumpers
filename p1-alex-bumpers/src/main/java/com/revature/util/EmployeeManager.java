package com.revature.util;

import java.util.ArrayList;
import java.util.List;

import com.revature.dao.EmployeesDao;
import com.revature.dao.EmployeesDaoImpl;
import com.revature.models.Employees;
import com.revature.models.Reimbursements;

public class EmployeeManager {
	public List<Employees> connectEmployeeERB(List<Employees> employees) {
//		List<Employees> empArr = new ArrayList<>();
//		empArr.addAll(employees);
//		EmployeesDao ed = new EmployeesDaoImpl();
//		
//		for (Employees r : empArr) {
//			Employees emp = ed.getEmployeesById(r.getId());
//			emp.setPassword("");
//			r.setId(emp);
//			
//			if (r.getManagerId() != 0) {
//				Employees mgmt = ed.getEmployeesById(r.getManagerId());
//				r.setManagers(mgmt);
//			}
//		}
		
		return null;
	}
}
