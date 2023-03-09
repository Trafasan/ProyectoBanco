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
import com.sandra.bancoDB.entidades.Mensaje;

public class DBMensaje {
	public static ImageIcon preocupado = new ImageIcon("src/main/java/com/sandra/bancoDB/images/preocupado.png");
	public static Connection con = DBConnection.conexion();
	public static PreparedStatement ps;
	public static ResultSet rs;
	
	public static List<Mensaje> datosMensajes() {
		List<Mensaje> mensajes = new ArrayList<Mensaje>();
		try {
			ps = con.prepareStatement("SELECT * FROM gestor");
			rs = ps.executeQuery();
			while (rs.next()) {
				mensajes.add(new Mensaje(rs.getInt("id"), rs.getInt("id_gestor"),
						rs.getInt("id_cliente"), rs.getString("texto"),
						rs.getTimestamp("fecha")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mensajes;
	}

	public static void getMensaje(int id_mensaje) {
		List<Mensaje> mensajes = datosMensajes().stream().filter(e->e.getId_gestor() == id_mensaje).collect(Collectors.toList());
		if (mensajes.size() == 0) JOptionPane.showMessageDialog(null, "No existe ningún mensaje con ese ID\\nSe le redigirá al menú Mensajes", "ERROR", 2, preocupado);
		else mensajes.forEach(System.out::println);
	}
	
	public static void getMensajes() {
		List<Mensaje> mensajes = datosMensajes();
		if (mensajes.size() == 0) JOptionPane.showMessageDialog(null, "No existe ningún mensaje en la base de datos\nSe le redigirá al menú Mensajes", "ERROR", 2, preocupado);
		else mensajes.forEach(System.out::println);
	}

	public static boolean addMensaje(Mensaje mensaje) {
		try {
			ps = con.prepareStatement("INSERT INTO mensaje (id, id_gestor, id_cliente, texto, fecha)VALUES(?,?,?,?,?)");
			ps.setInt(1, mensaje.getId_mensaje());
			ps.setInt(2, mensaje.getId_gestor());
			ps.setInt(3, mensaje.getId_cliente());
			ps.setString(4, mensaje.getTexto());
			ps.setTimestamp(5, mensaje.getFecha());
			ps.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
