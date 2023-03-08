package com.sandra.bancoDB.utilidades;

import java.sql.Timestamp;

import javax.swing.JOptionPane;

import com.sandra.bancoDB.entidades.Gestor;
import com.sandra.bancoDB.entidades.Mensaje;
import com.sandra.bancoDB.utilidades.UIMensaje;

public class UIMensaje {
	// 12. Obtención de un mensaje
	public static Mensaje obtencionMensaje() {
		int n = Integer.parseInt(JOptionPane.showInputDialog("Inserte el ID del mensaje a obtener: "));
		Mensaje mensaje = new Mensaje(n);
		return mensaje;
	}

	// 14. Envío de un mensaje

	// Comprobación previa de que existe el gestor con ese ID (origen y destino)
	public static Gestor comprobarId_origen() {
		int o = Integer.parseInt(JOptionPane.showInputDialog(null, "Introduzca el ID del gestor remitente:",
				"ENVÍO DEL MENSAJE", 1));
		Gestor gestor = new Gestor(o);
		return gestor;
	}
	
	public static Gestor comprobarId_destino() {
		int d = Integer.parseInt(JOptionPane.showInputDialog(null, "Introduzca el ID del gestor destinatario:",
				"ENVÍO DEL MENSAJE", 1));
		Gestor gestor = new Gestor(d);
		return gestor;
	}

	public static Mensaje envíoMensaje(Gestor comprobacionId_origen, Gestor comprobacionId_destino) {
		int id_origen = comprobacionId_origen.getId_gestor();
		int id_destino = comprobacionId_destino.getId_gestor();
		String texto = JOptionPane.showInputDialog("Inserte el texto del nuevo mensaje: ");
		Long datetime = System.currentTimeMillis();
		Timestamp fecha = new Timestamp(datetime);

		Mensaje mensaje = new Mensaje(id_origen, id_destino, texto, fecha);

		return mensaje;

	}
}