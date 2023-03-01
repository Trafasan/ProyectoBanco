package com.sandra.bancoDB.databases;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.sandra.bancoDB.connection.DBConnection;
import com.sandra.bancoDB.models.Cliente;
import com.sandra.bancoDB.models.Gestor;

public class DBLogin {
	DBConnection connection = new DBConnection();

	ImageIcon preocupado = new ImageIcon("src/images/preocupado.png");

	ArrayList<Gestor> usuarioG = new ArrayList<Gestor>();
	ArrayList<Cliente> usuarioC = new ArrayList<Cliente>();

	public boolean comprobarUsuarioG(Gestor gestor) {
		boolean existeUsuarioG = false;
		try {
			PreparedStatement statement = connection.getConnection()
					.prepareStatement("SELECT * FROM gestor WHERE usuario=?");
			statement.setString(1, gestor.getActualizar());

			ResultSet resultados = statement.executeQuery();
			while (resultados.next()) {
				usuarioG.add(new Gestor(resultados.getInt("id"), resultados.getString("nombre"),
						resultados.getString("apellido"), resultados.getString("dni"), resultados.getString("usuario"),
						resultados.getString("password"), resultados.getString("correo")));
			}

			if (usuarioG.size() == 0) {
				JOptionPane.showMessageDialog(null, "No existe ningún gestor con ese usuario", "ERROR", 0, preocupado);
			} else {
				existeUsuarioG = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return existeUsuarioG;
	}

	public String obtenerPasswordG(String usuario) {
		String password = null;
		try {
			PreparedStatement statement = connection.getConnection()
					.prepareStatement("SELECT * FROM gestor WHERE usuario=?");
			statement.setString(1, usuario);

			ResultSet resultados = statement.executeQuery();
			while (resultados.next()) {
				usuarioG.add(new Gestor(resultados.getInt("id"), resultados.getString("nombre"),
						resultados.getString("apellido"), resultados.getString("dni"), resultados.getString("usuario"),
						resultados.getString("password"), resultados.getString("correo")));
			}

			password = usuarioG.get(0).getPassword();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return password;
	}

	public String obtenerNombreG(String usuario) {
		String nombre = null;
		try {
			PreparedStatement statement = connection.getConnection()
					.prepareStatement("SELECT * FROM gestor WHERE usuario=?");
			statement.setString(1, usuario);

			ResultSet resultados = statement.executeQuery();
			while (resultados.next()) {
				usuarioG.add(new Gestor(resultados.getInt("id"), resultados.getString("nombre"),
						resultados.getString("apellido"), resultados.getString("dni"), resultados.getString("usuario"),
						resultados.getString("password"), resultados.getString("correo")));
			}

			nombre = usuarioG.get(0).getNombre();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nombre;
	}

	public String obtenerApellidoG(String usuario) {
		String apellido = null;
		try {
			PreparedStatement statement = connection.getConnection()
					.prepareStatement("SELECT * FROM gestor WHERE usuario=?");
			statement.setString(1, usuario);

			ResultSet resultados = statement.executeQuery();
			while (resultados.next()) {
				usuarioG.add(new Gestor(resultados.getInt("id"), resultados.getString("nombre"),
						resultados.getString("apellido"), resultados.getString("dni"), resultados.getString("usuario"),
						resultados.getString("password"), resultados.getString("correo")));
			}

			apellido = usuarioG.get(0).getApellido();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return apellido;
	}

	public boolean comprobarUsuarioC(Cliente cliente) {
		boolean existeUsuarioC = false;
		try {
			PreparedStatement statement = connection.getConnection()
					.prepareStatement("SELECT * FROM cliente WHERE usuario=?");
			statement.setString(1, cliente.getActualizar());

			ResultSet resultados = statement.executeQuery();
			while (resultados.next()) {
				usuarioC.add(new Cliente(resultados.getInt("id"), resultados.getInt("id_gestor"),
						resultados.getString("nombre"), resultados.getString("apellido"), resultados.getString("dni"),
						resultados.getString("usuario"), resultados.getString("password"),
						resultados.getString("correo"), resultados.getDouble("saldo")));
			}

			if (usuarioC.size() == 0) {
				JOptionPane.showMessageDialog(null, "No existe ningún cliente con ese usuario", "ERROR", 0, preocupado);
			} else {
				existeUsuarioC = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return existeUsuarioC;
	}

	public String obtenerPasswordC(String usuario) {
		String password = null;
		try {
			PreparedStatement statement = connection.getConnection()
					.prepareStatement("SELECT * FROM cliente WHERE usuario=?");
			statement.setString(1, usuario);

			ResultSet resultados = statement.executeQuery();
			while (resultados.next()) {
				usuarioC.add(new Cliente(resultados.getInt("id"), resultados.getInt("id_gestor"),
						resultados.getString("nombre"), resultados.getString("apellido"), resultados.getString("dni"),
						resultados.getString("usuario"), resultados.getString("password"),
						resultados.getString("correo"), resultados.getDouble("saldo")));
			}

			password = usuarioC.get(0).getPassword();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return password;
	}

	public String obtenerNombreC(String usuario) {
		String nombre = null;
		try {
			PreparedStatement statement = connection.getConnection()
					.prepareStatement("SELECT * FROM cliente WHERE usuario=?");
			statement.setString(1, usuario);

			ResultSet resultados = statement.executeQuery();
			while (resultados.next()) {
				usuarioC.add(new Cliente(resultados.getInt("id"), resultados.getInt("id_gestor"),
						resultados.getString("nombre"), resultados.getString("apellido"), resultados.getString("dni"),
						resultados.getString("usuario"), resultados.getString("password"),
						resultados.getString("correo"), resultados.getDouble("saldo")));
			}

			nombre = usuarioC.get(0).getNombre();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nombre;
	}

	public String obtenerApellidoC(String usuario) {
		String apellido = null;
		try {
			PreparedStatement statement = connection.getConnection()
					.prepareStatement("SELECT * FROM cliente WHERE usuario=?");
			statement.setString(1, usuario);

			ResultSet resultados = statement.executeQuery();
			while (resultados.next()) {
				usuarioC.add(new Cliente(resultados.getInt("id"), resultados.getInt("id_gestor"),
						resultados.getString("nombre"), resultados.getString("apellido"), resultados.getString("dni"),
						resultados.getString("usuario"), resultados.getString("password"),
						resultados.getString("correo"), resultados.getDouble("saldo")));
			}

			apellido = usuarioC.get(0).getApellido();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return apellido;
	}
}
