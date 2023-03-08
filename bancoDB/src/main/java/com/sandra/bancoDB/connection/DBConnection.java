package com.sandra.bancoDB.connection;

import java.sql.*;

public class DBConnection {
	// Datos de mi base de datos
	final static String URI = "jdbc:mysql://localhost:3306/banco";
	final static String USER = "root";
	final static String PASSWORD = "trafasan";
	
	public static Connection con;
	
	/**
	 * Método que conecta con la base de datos.
	 * @return La conexión a la base de datos.<br>
	 * 		   Si ha fallado la conexión, la conexión es nula.
	 */
	public static Connection conexion() {
		con = null;
    	try {
    		con = DriverManager.getConnection(URI, USER, PASSWORD);
    	} catch (SQLException e) {
    		System.err.println("No se ha podido conectar correctamente con la base de datos.");
		}
    	return con;
    }
	
	/**
	 * Método que desconecta con la base de datos.
	 * @return True si desconecta correctamente, False si falla la desconexión
	 */
	public static boolean desconexion() {
		try {
			if(!con.isClosed()) {
				con.close();
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}