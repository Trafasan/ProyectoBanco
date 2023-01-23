package com.project.databases;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.project.environments.DBConnection;
import com.project.models.Gestor;
import com.project.models.Mensaje;

public class DBMensaje {
	DBConnection connection = new DBConnection();

	ArrayList<Gestor> origen = new ArrayList<Gestor>();
	ArrayList<Gestor> destino = new ArrayList<Gestor>();
	ArrayList<Mensaje> mensajes = new ArrayList<Mensaje>();

	public void leerUnMensaje(Mensaje mensaje) {
		try {
			PreparedStatement statement = connection.getConnection()
					.prepareStatement("SELECT * FROM mensaje WHERE id=?");
			statement.setInt(1, mensaje.getId_mensaje());

			ResultSet resultados = statement.executeQuery();
			while (resultados.next()) {
				mensajes.add(new Mensaje(resultados.getInt("id"), resultados.getInt("id_origen"),
						resultados.getInt("id_destino"), resultados.getString("texto"),
						resultados.getTimestamp("fecha")));
			}

			if (mensajes.size() == 0) {
				JOptionPane.showMessageDialog(null, "No existe ningún mensaje con ese ID", "ERROR", 2);
			} else {
				for (int x = 0; x < mensajes.size(); x++) {
					System.out.println("Datos del mensaje " + mensajes.get(x).getId_mensaje());
					System.out.println("ID del gestor remitente: " + mensajes.get(x).getId_origen());
					System.out.println("ID del gestor destinatario: " + mensajes.get(x).getId_destino());
					System.out.println("Texto: " + mensajes.get(x).getTexto());
					System.out.println("Fecha: " + mensajes.get(x).getFecha() + "\n");
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void leerMensajes() {
		try {
			PreparedStatement statement = connection.getConnection().prepareStatement("SELECT * FROM mensaje");
			ResultSet resultados = statement.executeQuery();

			while (resultados.next()) {
				System.out.println("Datos del mensaje " + resultados.getInt("id"));
				System.out.println("ID del gestor remitente: " + resultados.getInt("id_origen"));
				System.out.println("ID del gestor destinatario: " + resultados.getInt("id_destino"));
				System.out.println("Texto: " + resultados.getString("texto"));
				System.out.println("Fecha: " + resultados.getTimestamp("fecha") + "\n");
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public boolean comprobarId_origen(Gestor gestor) {
		boolean existeId_origen = false;
		try {
			PreparedStatement statement = connection.getConnection()
					.prepareStatement("SELECT * FROM gestor WHERE id=?");
			statement.setInt(1, gestor.getId_gestor());

			ResultSet resultados = statement.executeQuery();
			while (resultados.next()) {
				origen.add(new Gestor(resultados.getInt("id"), resultados.getString("nombre"),
						resultados.getString("apellido"), resultados.getString("dni"), resultados.getString("usuario"),
						resultados.getString("password"), resultados.getString("correo")));
			}

			if (origen.size() == 0) {
				JOptionPane.showMessageDialog(null, "No existe ningún gestor con ese ID", "ERROR", 0);
			} else {
				for (int x = 0; x < origen.size(); x++) {
					JOptionPane.showMessageDialog(null, "Se encontró el gestor", "BÚSQUEDA FINALIZADA", 1);
					existeId_origen = true;
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return existeId_origen;
	}

	public boolean comprobarId_destino(Gestor gestor) {
		boolean existeId_destino = false;
		try {
			PreparedStatement statement = connection.getConnection()
					.prepareStatement("SELECT * FROM gestor WHERE id=?");
			statement.setInt(1, gestor.getId_gestor());

			ResultSet resultados = statement.executeQuery();
			while (resultados.next()) {
				destino.add(new Gestor(resultados.getInt("id"), resultados.getString("nombre"),
						resultados.getString("apellido"), resultados.getString("dni"), resultados.getString("usuario"),
						resultados.getString("password"), resultados.getString("correo")));
			}

			if (destino.size() == 0) {
				JOptionPane.showMessageDialog(null, "No existe ningún gestor con ese ID", "ERROR", 0);
			} else {
				for (int x = 0; x < destino.size(); x++) {
					JOptionPane.showMessageDialog(null, "Se encontró el gestor", "BÚSQUEDA FINALIZADA", 1);
					existeId_destino = true;
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return existeId_destino;
	}

	// Puede que sirva para el envío
	public void enviarMensaje(Mensaje mensaje) {
		try {
			PreparedStatement statement = connection.getConnection()
					.prepareStatement("INSERT INTO mensaje (id, id_origen, id_destino, texto, fecha)VALUES(?,?,?,?,?)");
			statement.setInt(1, mensaje.getId_mensaje());
			statement.setInt(2, mensaje.getId_origen());
			statement.setInt(3, mensaje.getId_destino());
			statement.setString(4, mensaje.getTexto());
			statement.setTimestamp(5, mensaje.getFecha());
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
