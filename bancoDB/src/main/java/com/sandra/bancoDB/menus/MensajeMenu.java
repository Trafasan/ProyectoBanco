package com.sandra.bancoDB.menus;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.sandra.bancoDB.databases.DBMensaje;
import com.sandra.bancoDB.entidades.Gestor;
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
			dbMensaje.leerUnMensaje(UIMensaje.obtencionMensaje());
			switchMensaje();
		}
		case "Obtención de todos los mensajes" -> {
			dbMensaje.leerMensajes();
			switchMensaje();
		}
		case "Envío de un mensaje" -> {
			boolean existeId_origen, existeId_destino;
			Gestor comprobacionId_origen, comprobacionId_destino;
			int id_origen, id_destino;
			do {
				do{
					comprobacionId_origen = UIMensaje.comprobarId_origen();
					id_origen = comprobacionId_origen.getId_gestor();
					existeId_origen = dbMensaje.comprobarId_origen(comprobacionId_origen);
				}while(existeId_origen == false);
				
				do{
					comprobacionId_destino = UIMensaje.comprobarId_destino();
					id_destino = comprobacionId_destino.getId_gestor();
					existeId_destino = dbMensaje.comprobarId_destino(comprobacionId_destino);
				}while(existeId_destino == false);
				
				if(id_origen == id_destino) {
					JOptionPane.showMessageDialog(null, "El gestor destinatario no puede ser el gestor remitente", "ERROR", 0, new ImageIcon("src/main/java/com/sandra/bancoDB/images/preocupado.png"));
				}
			}while(id_origen == id_destino);
			dbMensaje.enviarMensaje(UIMensaje.envíoMensaje(comprobacionId_origen, comprobacionId_destino));
			JOptionPane.showMessageDialog(null, "Envío realizado correctamente");
			switchMensaje();
		}
		case "Volver atrás" -> MainMenu.opciones();
		}
	}
}