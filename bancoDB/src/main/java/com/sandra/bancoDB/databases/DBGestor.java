package com.sandra.bancoDB.databases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.sandra.bancoDB.connection.DBConnection;
import com.sandra.bancoDB.entidades.Gestor;

public class DBGestor {
	public static ImageIcon preocupado = new ImageIcon("src/main/java/com/sandra/bancoDB/images/preocupado.png");
	public static Connection con = DBConnection.conexion();
	public static PreparedStatement ps;
	public static ResultSet rs;

	public static void addGestor(Gestor gestor) {
		try {
			ps = con.prepareStatement("INSERT INTO gestor (id, nombre, apellido, dni, usuario, password, correo)VALUES(?,?,?,?,?,?,?)");
			ps.setInt(1, gestor.getId_gestor());
			ps.setString(2, gestor.getNombre());
			ps.setString(3, gestor.getApellido());
			ps.setString(4, gestor.getDni());
			ps.setString(5, gestor.getUsuario());
			ps.setString(6, gestor.getPassword());
			ps.setString(7, gestor.getCorreo());
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static List<Gestor> datosGestores() {
		List<Gestor> gestores = new ArrayList<Gestor>();
		try {
			ps = con.prepareStatement("SELECT * FROM gestor");
			rs = ps.executeQuery();
			while (rs.next()) {
				gestores.add(new Gestor(rs.getInt("id"), rs.getString("nombre"),
						rs.getString("apellido"), rs.getString("dni"), rs.getString("usuario"),
						rs.getString("password"), rs.getString("correo")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return gestores;
	}

	public static void getGestor(int id_gestor) {
		List<Gestor> gestores = datosGestores().stream().filter(e->e.getId_gestor() == id_gestor).collect(Collectors.toList());
		if (gestores.size() == 0) JOptionPane.showMessageDialog(null, "No existe ningún gestor con ese ID\nSe le redigirá al menú Gestor", "ERROR", 2, preocupado);
		else gestores.forEach(System.out::println);
	}
	
	public static void getGestores() {
		List<Gestor> gestores = datosGestores();
		if (gestores.size() == 0) JOptionPane.showMessageDialog(null, "No existe ningún gestor en la base de datos\nSe le redigirá al menú Gestor", "ERROR", 2, preocupado);
		else gestores.forEach(System.out::println);
	}

	public static boolean existeGestor(int id_gestor) {
		List<Gestor> gestores = datosGestores().stream().filter(e->e.getId_gestor() == id_gestor).collect(Collectors.toList());
		if (gestores.size()==0) JOptionPane.showMessageDialog(null, "No existe ningún gestor con ese ID\nSe le redigirá al menú Gestor", "ERROR", 0, preocupado);
		else {
			JOptionPane.showMessageDialog(null, "Se encontró el gestor", "BÚSQUEDA FINALIZADA", 1);
			return true;
			}
		return false;
	}

	public static void updateDatoGestor(Gestor gestor, String dato) {
		try {
			ps = con.prepareStatement("UPDATE gestor SET "+dato+"=? WHERE id=?");
			ps.setString(1, gestor.getUpdateDato());
			ps.setInt(2, gestor.getId_gestor());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void deleteGestor(int id_gestor) {
		try {
			ps = con.prepareStatement("DELETE FROM gestor WHERE id=?");
			ps.setInt(1, id_gestor);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
