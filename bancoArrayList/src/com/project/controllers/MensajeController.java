package com.project.controllers;

import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

import com.project.functions.MainMenu;
import com.project.models.Cliente;
import com.project.models.Gestor;
import com.project.models.Mensaje;
import com.project.models.Transferencia;
import com.project.utils.UIGestor;
import com.project.utils.UIMensaje;

public class MensajeController {

	public static void switchMensaje(List<Gestor> gestores, List<Cliente> clientes, List<Mensaje> mensajes,
			List<Transferencia> transferencias, Scanner sc) {
		String accion;
		try {
			accion = JOptionPane.showInputDialog(null, "Inserte una opción: ", "MENÚ MENSAJES",
					JOptionPane.PLAIN_MESSAGE, null, new Object[] { "Obtención de un mensaje",
							"Obtención de todos los mensajes", "Envío de un mensaje" },
					null).toString();
		} catch (Exception e) {
			accion = "Volver atrás";
		}
		switch (accion) {
		case "Obtención de un mensaje" -> {
			if (mensajes.size() == 0) System.err.println("No existe ningún mensaje. Se le redigirá al Menú Mensajes.");
			else UIMensaje.getMensaje(mensajes, sc);
		}
		case "Obtención de todos los mensajes" -> {
			if (mensajes.size() == 0) System.err.println("No existe ningún mensaje. Se le redigirá al Menú Mensajes.");
			else UIMensaje.getMensajes(mensajes);
		}
		case "Envío de un mensaje" -> {
			int id_origen = UIMensaje.comprobarId(gestores, sc, "remitente");
			int id_destino = -1;
			if (UIGestor.existeGestor(gestores, id_origen, "Mensajes")) {
				id_destino = UIMensaje.comprobarId(gestores, sc, "destinatario");
				if (UIGestor.existeGestor(gestores, id_destino, "Mensajes")) UIMensaje.addMensaje(mensajes, id_origen, id_destino, sc);
			}
		}
		case "Volver atrás" -> MainMenu.opciones(gestores, clientes, mensajes, transferencias, sc);
		}
		if (!accion.equals("Volver atrás")) switchMensaje(gestores, clientes, mensajes, transferencias, sc);
	}
}
