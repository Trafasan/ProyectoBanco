package com.project.controllers;

import javax.swing.JOptionPane;

import com.project.databases.DBMensaje;
import com.project.functions.MainMenu;
import com.project.models.Gestor;
import com.project.utils.UIMensaje;

public class MensajeController {

	public static void switchMensaje() {
		DBMensaje dbMensaje = new DBMensaje();
		String nuevaOpcion;
		try {
			nuevaOpcion = JOptionPane.showInputDialog(null, "Inserte una opción: ", "MENÚ MENSAJES",
					JOptionPane.PLAIN_MESSAGE, null, new Object[] { "Obtención de un mensaje",
							"Obtención de todos los mensajes", "Envío de un mensaje" }, null).toString();
		} catch (Exception e) {
			nuevaOpcion = "Volver atrás";
		}

		switch (nuevaOpcion) {
		case "Obtención de un mensaje":
			dbMensaje.leerUnMensaje(UIMensaje.obtencionMensaje());
			switchMensaje();
			break;
		case "Obtención de todos los mensajes":
			dbMensaje.leerMensajes();
			switchMensaje();
			break;
		case "Envío de un mensaje":
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
					JOptionPane.showMessageDialog(null, "El gestor destinatario no puede ser el gestor remitente", "ERROR", 0);
				}
			}while(id_origen == id_destino);
			dbMensaje.enviarMensaje(UIMensaje.envíoMensaje(comprobacionId_origen, comprobacionId_destino));
			JOptionPane.showMessageDialog(null, "Envío realizado correctamente");
			switchMensaje();
			break;
		case "Volver atrás":
			MainMenu.opciones();
		}
	}
}