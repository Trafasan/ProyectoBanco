package com.sandra.bancoDB.utilidades;

import java.sql.Timestamp;

import javax.swing.JOptionPane;

import com.sandra.bancoDB.entidades.Mensaje;
import com.sandra.bancoDB.utilidades.UIMensaje;

public class UIMensaje {
	// 12. Obtención de un mensaje
	public static Mensaje obtencionMensaje() {
		return new Mensaje(Integer.parseInt(JOptionPane.showInputDialog("Inserte el ID del mensaje a obtener: ")));
	}

	// 14. Envío de un mensaje
	// Comprobación previa de que existe el gestor con ese ID (origen y destino)
	public static int comprobarId(String tipoGestor) {
		return Integer.parseInt(JOptionPane.showInputDialog(null, "Introduzca el ID del gestor "+tipoGestor+":", "ENVÍO DEL MENSAJE", 1));
	}

	public static Mensaje addMensaje(int id_origen, int id_destino) {
		String texto = JOptionPane.showInputDialog("Inserte el texto del nuevo mensaje: ");
		Long datetime = System.currentTimeMillis();
		Timestamp fecha = new Timestamp(datetime);
		return new Mensaje(id_origen, id_destino, texto, fecha);
	}
}