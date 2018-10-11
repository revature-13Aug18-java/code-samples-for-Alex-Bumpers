package com.revature.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Employees;
import com.revature.models.Reimbursements;
import com.revature.util.ConnectionUtil;

public class EmployeesDaoImpl implements EmployeesDao {

	@Override
	public Employees getEmployeesById(int id) {
		
		Employees e = new Employees();
		String sql = "SELECT * FROM EMPLOYEES WHERE EMP_ID = ?";
		ResultSet rs = null;
		
		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				e.setId(rs.getInt("EMP_ID"));
				e.setName(rs.getString("EMP_NAME"));
				e.setManagerId(rs.getInt("MANAGER_ID"));
				e.setPosition(rs.getString("POSITION"));
				e.setUsername(rs.getString("EMP_USERNAME"));
				e.setPassword(rs.getString("EMP_PASSWORD"));
				e.setIsManager(rs.getInt("IS_MANAGER"));
				
			}
			
		} catch (SQLException|IOException e1) {
			e1.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
				
		
		return e;
	}

	@Override
	public Employees getEmployeesByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employees> getAllEmployees() {
		List<Employees> employeesList = new ArrayList<Employees>();

		String sql = "SELECT * FROM EMPLOYEES";

		ResultSet rs = null;

		try (Connection con = ConnectionUtil.getConnection(); Statement s = con.createStatement();) {

			rs = s.executeQuery(sql);

			while (rs.next()) {
				Employees e = new Employees();

				int employeeId = rs.getInt("EMP_ID");
				e.setId(employeeId);

				String name = rs.getString("EMP_NAME");
				e.setName(name);

				int managerId = rs.getInt("MANAGER_ID");
				e.setManagerId(managerId);

				String position = rs.getString("POSITION");
				e.setPosition(position);

				String username = rs.getString("EMP_USERNAME");
				e.setUsername(username);
				
				String password = rs.getString("EMP_PASSWORD");
				e.setPassword(password);
				
				int isManager = rs.getInt("IS_MANAGER");
				e.setIsManager(isManager);

				employeesList.add(e);

			}

		} catch (SQLException | IOException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return employeesList;
	}

	@Override
	public int createEmployee(Employees employee) {
		int employeesCreated = 0;
		String sql = "INSERT INTO EMPLOYEES (EMP_NAME, MANAGER_ID, POSITION, EMP_USERNAME, EMP_PASSWORD, IS_MANAGER)  VALUES (?,?,?,?,?,?)";
		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, employee.getName());
			ps.setInt(2, employee.getManagerId());
			ps.setString(3, employee.getPosition());
			ps.setString(4, employee.getUsername());
			ps.setString(5, employee.getPassword());
			ps.setInt(6, employee.getIsManager());

			employeesCreated = ps.executeUpdate();

		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return employeesCreated;
	}

	@Override
	public Employees getEmployeeByUsername(String username) {
		Employees e = new Employees();
		String sql = "SELECT * FROM EMPLOYEES WHERE EMP_USERNAME = ?";
		ResultSet rs = null;
		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
			
			ps.setString(1, username);
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				e.setId(rs.getInt("EMP_ID"));
				e.setName(rs.getString("EMP_NAME"));
				e.setManagerId(rs.getInt("MANAGER_ID"));
				e.setPosition(rs.getString("POSITION"));
				e.setUsername(rs.getString("EMP_USERNAME"));
				e.setPassword(rs.getString("EMP_PASSWORD"));
				e.setIsManager(rs.getInt("IS_MANAGER"));
			}
			
		} catch (SQLException|IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return e;
	}

	@Override
	public int updateEmployeeById(int id) {
		int updatedEmployee = 0;
		String sql = "UPDATE EMPLOYEES SET EMP_NAME = ? WHERE EMP_ID = ?";
		
		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
			
			ps.setInt(1, id);
			updatedEmployee = ps.executeUpdate();
		} catch (SQLException|IOException e) {
			e.printStackTrace();
		}
		return updatedEmployee;
	}
	
	@Override
	public int updateEmployeeByName(String name) {
		int updatedEmployee = 0;
		String sql = "UPDATE EMPLOYEES SET EMP_NAME = ? WHERE EMP_NAME = ?";
		
		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
			
			ps.setString(1, name);
			updatedEmployee = ps.executeUpdate();
		} catch (SQLException|IOException e) {
			e.printStackTrace();
		}
		return updatedEmployee;
	}
	
}


