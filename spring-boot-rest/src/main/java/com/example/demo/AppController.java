package com.example.demo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.dao.EmployeeDAO;
import com.microservices.model.Employee;

@RestController
public class AppController {

	private static Logger LOG = LogManager.getLogger(AppController.class);
	
	@Autowired
	EmployeeDAO employeeDAO;
	
	@Autowired
	Employee employee;
	
	@RequestMapping(value = "/employeeData", method = RequestMethod.POST)
	public void persistData(@RequestBody Employee employee) {
		LOG.info("data is : " + employee);
		employeeDAO.saveData(employee);
	}
	
	@RequestMapping(value = "/getEmployeeData", method = RequestMethod.POST)
	public void getData(@RequestBody Employee employee) {
		LOG.info("Employee count is : " + employeeDAO.getEmployeeCount(employee.getDepartment()));
	}
	
	@RequestMapping(value = "/getEmployeeDataCity", method = RequestMethod.POST)
	public void getDataCity(@RequestBody Employee employee) {
		LOG.info("Employee count is : " + employeeDAO.getEmployeeCountInCity(employee.getCity()));
	}
	
	@RequestMapping(value = "/updateEmployeeData", method = RequestMethod.PUT)
	public void updateEmployeeData(@RequestBody Employee employee) {
		LOG.info("Employee count is : " + employeeDAO.getEmployeeCountInCity(employee.getCity()));
	}
}
