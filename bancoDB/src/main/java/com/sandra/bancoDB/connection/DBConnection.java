package com.sandra.bancoDB.connection;

import java.sql.*;

public class DBConnection {

	Connection connection;

	public DBConnection() {
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/banco", "root", "trafasan");

		} catch (Exception e) {
			System.out.println("No conecta");
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		return connection;
	}

}