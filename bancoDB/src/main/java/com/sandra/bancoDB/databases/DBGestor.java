package com.sandra.bancoDB.databases;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.sandra.bancoDB.connection.DBConnection;
import com.sandra.bancoDB.entidades.Gestor;

public class DBGestor {
	DBConnection connection = new DBConnection();
	ImageIcon preocupado = new ImageIcon("src/main/java/com/sandra/bancoDB/images/preocupado.png");
	ArrayList<Gestor> gestores = new ArrayList<Gestor>();

	public void addGestor(Gestor gestor) {
		try {
			PreparedStatement statement = connection.getConnection().prepareStatement(
					"INSERT INTO gestor (id, nombre, apellido, dni, usuario, password, correo)VALUES(?,?,?,?,?,?,?)");
			statement.setInt(1, gestor.getId_gestor());
			statement.setString(2, gestor.getNombre());
			statement.setString(3, gestor.getApellido());
			statement.setString(4, gestor.getDni());
			statement.setString(5, gestor.getUsuario());
			statement.setString(6, gestor.getPassword());
			statement.setString(7, gestor.getCorreo());
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void getGestor(Gestor gestor) {
		try {
			PreparedStatement statement = connection.getConnection().prepareStatement("SELECT * FROM gestor WHERE id=?");
			statement.setInt(1, gestor.getId_gestor());
			ResultSet resultados = statement.executeQuery();
			while (resultados.next()) {
				gestores.add(new Gestor(resultados.getInt("id"), resultados.getString("nombre"),
						resultados.getString("apellido"), resultados.getString("dni"), resultados.getString("usuario"),
						resultados.getString("password"), resultados.getString("correo")));
			}
			if (gestores.size() == 0) JOptionPane.showMessageDialog(null, "No existe ningún gestor con ese ID\nSe le redigirá al menú Gestor", "ERROR", 2, preocupado);
			else for (int i=0; i<gestores.size(); i++) System.out.println(gestores.get(i));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void getGestores() {
		try {
			PreparedStatement statement = connection.getConnection().prepareStatement("SELECT * FROM gestor");
			ResultSet resultados = statement.executeQuery();
			while (resultados.next()) {
				gestores.add(new Gestor(resultados.getInt("id"), resultados.getString("nombre"),
						resultados.getString("apellido"), resultados.getString("dni"), resultados.getString("usuario"),
						resultados.getString("password"), resultados.getString("correo")));
			}
			if (gestores.size() == 0) JOptionPane.showMessageDialog(null, "No existe ningún gestor en la base de datos\nSe le redigirá al menú Gestor", "ERROR", 2, preocupado);
			else for (int i=0; i<gestores.size(); i++) System.out.println(gestores.get(i));
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public boolean existeGestor(Gestor gestor) {
		try {
			PreparedStatement statement = connection.getConnection()
					.prepareStatement("SELECT * FROM gestor WHERE id=?");
			statement.setInt(1, gestor.getId_gestor());

			ResultSet resultados = statement.executeQuery();
			while (resultados.next()) {
				gestores.add(new Gestor(resultados.getInt("id"), resultados.getString("nombre"),
						resultados.getString("apellido"), resultados.getString("dni"), resultados.getString("usuario"),
						resultados.getString("password"), resultados.getString("correo")));
			}
			if (gestores.size()==0) JOptionPane.showMessageDialog(null, "No existe ningún gestor con ese ID\nSe le redigirá al menú Gestor", "ERROR", 0, preocupado);
			else {
				JOptionPane.showMessageDialog(null, "Se encontró el gestor", "BÚSQUEDA FINALIZADA", 1);
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public void updateDatoGestor(Gestor gestor, String dato) {
		try {
			PreparedStatement statement = connection.getConnection()
					.prepareStatement("UPDATE gestor SET "+dato+"=? WHERE id=?");
			statement.setString(1, gestor.getActualizar());
			statement.setInt(2, gestor.getId_gestor());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteGestor(Gestor gestor) {
		try {
			PreparedStatement statement = connection.getConnection().prepareStatement("DELETE FROM gestor WHERE id=?");
			statement.setInt(1, gestor.getId_gestor());
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
