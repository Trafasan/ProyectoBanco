package com.project.environments;

import java.sql.*;

public class DBConnection {

	Connection connection;

	public DBConnection() {
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3309/banco", "root", "Trafasan");

		} catch (Exception e) {
			System.out.println("No conecta");
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		return connection;
	}

}