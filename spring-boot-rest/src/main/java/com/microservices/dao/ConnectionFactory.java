package com.microservices.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	static Connection connection;
	private ConnectionFactory() {}

	public static Connection getInstance() {
		
		if(connection == null) {
			try {
				Class.forName("com");
				connection = DriverManager.getConnection("","","");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return connection;
		}else {
			return connection;
		}
	}
}
