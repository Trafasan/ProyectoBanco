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
import com.sandra.bancoDB.entidades.Mensaje;

public class DBMensaje {
	Connection con = DBConnection.conexion();

	public static ImageIcon preocupado = new ImageIcon("src/main/java/com/sandra/bancoDB/images/preocupado.png");

	ArrayList<Gestor> origen = new ArrayList<Gestor>();
	ArrayList<Gestor> destino = new ArrayList<Gestor>();
	ArrayList<Mensaje> mensajes = new ArrayList<Mensaje>();

	public void getMensaje(Mensaje mensaje) {
		try {
			PreparedStatement statement = con.prepareStatement("SELECT * FROM mensaje WHERE id=?");
			statement.setInt(1, mensaje.getId_mensaje());

			ResultSet resultados = statement.executeQuery();
			while (resultados.next()) {
				mensajes.add(new Mensaje(resultados.getInt("id"), resultados.getInt("id_origen"),
						resultados.getInt("id_destino"), resultados.getString("texto"),
						resultados.getTimestamp("fecha")));
			}
			if (mensajes.size() == 0) JOptionPane.showMessageDialog(null, "No existe ningún mensaje con ese ID", "ERROR", 2, preocupado);
			else mensajes.forEach(System.out::println);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void leerMensajes() {
		try {
			PreparedStatement statement = con.prepareStatement("SELECT * FROM mensaje");
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

	public static boolean comprobarId(int id_gestor) {
		List<Gestor> gestores = DBGestor.datosGestores().stream().filter(e->e.getId_gestor() == id_gestor).collect(Collectors.toList());
		if (gestores.size() == 0) {
			JOptionPane.showMessageDialog(null, "No existe ningún gestor con ese ID", "ERROR", 0, preocupado);
		}
		else {
			JOptionPane.showMessageDialog(null, "Se encontró el gestor", "BÚSQUEDA FINALIZADA", 1);
			return true;
		}
		return false;
	}

	// Puede que sirva para el envío
	public void enviarMensaje(Mensaje mensaje) {
		try {
			PreparedStatement statement = con.prepareStatement("INSERT INTO mensaje (id, id_origen, id_destino, texto, fecha)VALUES(?,?,?,?,?)");
			statement.setInt(1, mensaje.getId_mensaje());
			statement.setInt(2, mensaje.getId_gestor());
			statement.setInt(3, mensaje.getId_cliente());
			statement.setString(4, mensaje.getTexto());
			statement.setTimestamp(5, mensaje.getFecha());
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
