package com.sandra.bancoDB.menus;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.sandra.bancoDB.App;
import com.sandra.bancoDB.databases.DBMensaje;
import com.sandra.bancoDB.utilidades.UIMensaje;

public class MensajeMenu {

	public static void switchMensaje() {
		DBMensaje dbMensaje = new DBMensaje();
		
		String accion;
		try {
			accion = JOptionPane.showInputDialog(null, "Seleccione una opción: ", "MENÚ MENSAJES",
					JOptionPane.PLAIN_MESSAGE, null, new Object[] { "Obtención de un mensaje",
							"Obtención de todos los mensajes", "Envío de un mensaje" }, null).toString();
		} catch (Exception e) {
			accion = "Volver atrás";
		}

		switch (accion) {
		case "Obtención de un mensaje" -> {
			dbMensaje.getMensaje(UIMensaje.obtencionMensaje());
			switchMensaje();
		}
		case "Obtención de todos los mensajes" -> {
			dbMensaje.leerMensajes();
			switchMensaje();
		}
		case "Envío de un mensaje" -> {
			int id_origen, id_destino;
			do {
				do{
					id_origen = UIMensaje.comprobarId("remitente");
				} while(!DBMensaje.comprobarId(id_origen));
				
				do{
					id_destino = UIMensaje.comprobarId("destinatario");
				} while(!DBMensaje.comprobarId(id_destino));
				
				if(id_origen == id_destino) {
					JOptionPane.showMessageDialog(null, "El gestor destinatario no puede ser el gestor remitente", "ERROR", 0, new ImageIcon("src/main/java/com/sandra/bancoDB/images/preocupado.png"));
				}
			}while(id_origen == id_destino);
			dbMensaje.enviarMensaje(UIMensaje.addMensaje(id_origen, id_destino));
			JOptionPane.showMessageDialog(null, "Envío realizado correctamente");
			switchMensaje();
		}
		case "Volver atrás" ->  App.mainMenu();
		}
	}
}