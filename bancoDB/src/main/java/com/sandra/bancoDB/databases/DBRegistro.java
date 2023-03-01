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

public class DBRegistro {
	DBConnection connection = new DBConnection();
	
	ImageIcon preocupado = new ImageIcon("src/images/preocupado.png");
	
	ArrayList<Gestor> usuarioG = new ArrayList<Gestor>();
	ArrayList<Cliente> usuarioC = new ArrayList<Cliente>();
	
	public boolean comprobarUsuarioG(Gestor gestor) {
		boolean existeUsuarioG = true;
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
				existeUsuarioG = false;
			} else {
				JOptionPane.showMessageDialog(null, "Ya existe un gestor con ese usuario", "ERROR", 0, preocupado);
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
		boolean existeUsuarioC = true;
		try {
			PreparedStatement statement = connection.getConnection()
					.prepareStatement("SELECT * FROM cliente WHERE usuario=?");
			statement.setString(1, cliente.getActualizar());

			ResultSet resultados = statement.executeQuery();
			while (resultados.next()) {
				usuarioC.add(new Cliente(resultados.getInt("id"), resultados.getInt("id_gestor"), resultados.getString("nombre"),
						resultados.getString("apellido"), resultados.getString("dni"), resultados.getString("usuario"),
						resultados.getString("password"), resultados.getString("correo"), resultados.getDouble("saldo")));
			}

			if (usuarioC.size() == 0) {
				existeUsuarioC = false;
			} else {
				JOptionPane.showMessageDialog(null, "Ya existe un cliente con ese usuario", "ERROR", 0, preocupado);
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
				usuarioC.add(new Cliente(resultados.getInt("id"), resultados.getInt("id_gestor"), resultados.getString("nombre"),
						resultados.getString("apellido"), resultados.getString("dni"), resultados.getString("usuario"),
						resultados.getString("password"), resultados.getString("correo"), resultados.getDouble("saldo")));
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
				usuarioC.add(new Cliente(resultados.getInt("id"), resultados.getInt("id_gestor"), resultados.getString("nombre"),
						resultados.getString("apellido"), resultados.getString("dni"), resultados.getString("usuario"),
						resultados.getString("password"), resultados.getString("correo"), resultados.getDouble("saldo")));
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
				usuarioC.add(new Cliente(resultados.getInt("id"), resultados.getInt("id_gestor"), resultados.getString("nombre"),
						resultados.getString("apellido"), resultados.getString("dni"), resultados.getString("usuario"),
						resultados.getString("password"), resultados.getString("correo"), resultados.getDouble("saldo")));
			}
			
			apellido = usuarioC.get(0).getApellido();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return apellido;
	}
	
	public void crearGestor(Gestor gestor) {
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
	
	public void crearCliente(Cliente cliente) {
		try {
			PreparedStatement statement = connection.getConnection().prepareStatement(
					"INSERT INTO cliente (id, id_gestor, nombre, apellido, dni, usuario, password, correo, saldo)VALUES(?,?,?,?,?,?,?,?,?)");
			statement.setInt(1, cliente.getId_cliente());
			statement.setInt(2, cliente.getId_gestor());
			statement.setString(3, cliente.getNombre());
			statement.setString(4, cliente.getApellido());
			statement.setString(5, cliente.getDni());
			statement.setString(6, cliente.getUsuario());
			statement.setString(7, cliente.getPassword());
			statement.setString(8, cliente.getCorreo());
			statement.setDouble(9, cliente.getSaldo());
			statement.execute();
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
				System.out.println("Nombre: " + resultados.getString("nombre")+" " + resultados.getString("apellido"));
				System.out.println("Correo electrónico: " + resultados.getString("correo") + "\n");
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}
	
	public ArrayList<Gestor> obtenerId_gestor() {
		ArrayList<Gestor> id_gestores = new ArrayList<Gestor>();
		try {
			PreparedStatement statement = connection.getConnection()
					.prepareStatement("SELECT * FROM cliente");

			ResultSet resultados = statement.executeQuery();
			while (resultados.next()) {
				id_gestores.add(new Gestor(resultados.getInt("id")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id_gestores;
	}
}
