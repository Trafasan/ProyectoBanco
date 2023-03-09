package com.sandra.bancoDB.utilidades;

import java.sql.Timestamp;

import javax.swing.JOptionPane;

import com.sandra.bancoDB.entidades.Mensaje;
import com.sandra.bancoDB.utilidades.UIMensaje;

public class UIMensaje {
	// Obtención de un mensaje
	public static int getMensaje() {
		return Integer.parseInt(JOptionPane.showInputDialog("Inserte el ID del mensaje a obtener: "));
	}

	// Envío de un mensaje
	// Comprobación previa de que existen las personas con ese ID (gestor y cliente)
	public static int comprobarId(String persona) {
		return Integer.parseInt(JOptionPane.showInputDialog(null, "Introduzca el ID del "+persona+":", "ENVÍO DEL MENSAJE", 1));
	}

	public static Mensaje addMensaje(int id_origen, int id_destino) {
		String texto = JOptionPane.showInputDialog("Inserte el texto del nuevo mensaje: ");
		Long datetime = System.currentTimeMillis();
		Timestamp fecha = new Timestamp(datetime);
		return new Mensaje(id_origen, id_destino, texto, fecha);
	}
}