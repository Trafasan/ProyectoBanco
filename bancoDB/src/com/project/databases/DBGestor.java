package com.project.databases;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.project.environments.DBConnection;
import com.project.models.Gestor;

public class DBGestor {
	DBConnection connection = new DBConnection();

	ImageIcon preocupado = new ImageIcon("src/images/preocupado.png");

	ArrayList<Gestor> gestores = new ArrayList<Gestor>();

	public void crearUnGestor(Gestor gestor) {
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

	public void crearGestorAleatorio(Gestor gestor) {
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

	public void leerUnGestor(Gestor gestor) {
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

			if (gestores.size() == 0) {
				JOptionPane.showMessageDialog(null, "No existe ningún gestor con ese ID", "ERROR", 2, preocupado);
			} else {
				for (int x = 0; x < gestores.size(); x++) {
					System.out.println("Datos del gestor " + gestores.get(x).getId_gestor());
					System.out.println("Nombre: " + gestores.get(x).getNombre());
					System.out.println("Apellido: " + gestores.get(x).getApellido());
					System.out.println("DNI: " + gestores.get(x).getDni());
					System.out.println("Usuario: " + gestores.get(x).getUsuario());
					System.out.println("Contraseña: " + gestores.get(x).getPassword());
					System.out.println("Correo: " + gestores.get(x).getCorreo() + "\n");
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void leerGestores() {
		try {
			PreparedStatement statement = connection.getConnection().prepareStatement("SELECT * FROM gestor");
			ResultSet resultados = statement.executeQuery();

			while (resultados.next()) {
				System.out.println("Datos del gestor " + resultados.getInt("id"));
				System.out.println("Nombre: " + resultados.getString("nombre"));
				System.out.println("Apellido: " + resultados.getString("apellido"));
				System.out.println("DNI: " + resultados.getString("dni"));
				System.out.println("Usuario: " + resultados.getString("usuario"));
				System.out.println("Contraseña: " + resultados.getString("password"));
				System.out.println("Correo: " + resultados.getString("correo") + "\n");

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public boolean comprobarGestor(Gestor gestor) {
		boolean existeGestor = false;
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

			if (gestores.size() == 0) {
				JOptionPane.showMessageDialog(null, "No existe ningún gestor con ese ID", "ERROR", 0, preocupado);
			} else {
				JOptionPane.showMessageDialog(null, "Se encontró el gestor", "BÚSQUEDA FINALIZADA", 1);
				existeGestor = true;

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return existeGestor;
	}

	public void updateNombreGestor(Gestor gestor) {
		try {
			PreparedStatement statement = connection.getConnection()
					.prepareStatement("UPDATE gestor SET nombre=? WHERE id=?");
			statement.setString(1, gestor.getActualizar());
			statement.setInt(2, gestor.getId_gestor());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateApellidoGestor(Gestor gestor) {
		try {
			PreparedStatement statement = connection.getConnection()
					.prepareStatement("UPDATE gestor SET apellido=? WHERE id=?");
			statement.setString(1, gestor.getActualizar());
			statement.setInt(2, gestor.getId_gestor());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateDniGestor(Gestor gestor) {
		try {
			PreparedStatement statement = connection.getConnection()
					.prepareStatement("UPDATE gestor SET dni=? WHERE id=?");
			statement.setString(1, gestor.getActualizar());
			statement.setInt(2, gestor.getId_gestor());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateUsuarioGestor(Gestor gestor) {
		try {
			PreparedStatement statement = connection.getConnection()
					.prepareStatement("UPDATE gestor SET usuario=? WHERE id=?");
			statement.setString(1, gestor.getActualizar());
			statement.setInt(2, gestor.getId_gestor());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updatePasswordGestor(Gestor gestor) {
		try {
			PreparedStatement statement = connection.getConnection()
					.prepareStatement("UPDATE gestor SET password=? WHERE id=?");
			statement.setString(1, gestor.getActualizar());
			statement.setInt(2, gestor.getId_gestor());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateCorreoGestor(Gestor gestor) {
		try {
			PreparedStatement statement = connection.getConnection()
					.prepareStatement("UPDATE gestor SET correo=? WHERE id=?");
			statement.setString(1, gestor.getActualizar());
			statement.setInt(2, gestor.getId_gestor());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void borrarGestor(Gestor gestor) {
		try {
			PreparedStatement statement = connection.getConnection().prepareStatement("DELETE FROM gestor WHERE id=?");
			statement.setInt(1, gestor.getId_gestor());
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
