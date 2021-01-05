package com.microservices.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.microservices.model.Employee;

@Component
public class EmployeeDAO {

	private static Logger LOG = LogManager.getLogger(EmployeeDAO.class);

	Connection connection = ConnectionFactory.getInstance();
	PreparedStatement preparedStatement;

	public void saveData(Employee employee) {

		String saveQuery = "insert into employee values(?,?,?,?,?,?,?)";
		try {
			preparedStatement = connection.prepareStatement(saveQuery);
			preparedStatement.setString(1, employee.getEmployeeId());
			preparedStatement.setString(2, employee.getFirstName());
			preparedStatement.setString(3, employee.getLastName());
			preparedStatement.setString(4, employee.getCity());
			preparedStatement.setString(5, employee.getAge());
			preparedStatement.setString(6, employee.getSalary());
			preparedStatement.setString(7, employee.getDepartment());

			preparedStatement.executeQuery();

		} catch (SQLException e) {
			LOG.error("Exception in persisting the data " , e);
		}finally {
			try {
				connection.close();
			} catch (SQLException e) {
				LOG.error("Exception in connection close " , e);
			}
		}


	}

	public int getEmployeeCount(String department) {

		String countQuery = "select count(*) from employee where department="+department;

		try {
			preparedStatement = connection.prepareStatement(countQuery);
			ResultSet resultSet = preparedStatement.executeQuery();
			ResultSetMetaData metaData = resultSet.getMetaData();

			int employeeCount = metaData.getColumnCount();

			LOG.info("count of employee in department "+department + " is : " + metaData.getColumnCount());
			return employeeCount;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;


	}

	public int getEmployeeCountInCity(String city) {

		String countQuery = "select count(*) from employee where city="+city;

		try {
			preparedStatement = connection.prepareStatement(countQuery);
			ResultSet resultSet = preparedStatement.executeQuery();
			ResultSetMetaData metaData = resultSet.getMetaData();

			int employeeCount = metaData.getColumnCount();

			LOG.info("count of employee in city "+city + " is : " + metaData.getColumnCount());
			return employeeCount;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;


	}
	
	public void updateEmployeeData(Employee employee) {

		String updateQuery = "update employee set firstName="+employee.getFirstName()
			+",lastName="+employee.getLastName()+",city="+employee.getCity()
			+",age="+employee.getAge()+",salary="+employee.getSalary()+",department="+employee.getDepartment()
			+" where employeeId = "+employee.getEmployeeId();

		
		try {
			preparedStatement = connection.prepareStatement(updateQuery);
			preparedStatement.executeQuery();

		} catch (SQLException e) {
			LOG.error("Exception in update " ,e);
		}

	}

}
