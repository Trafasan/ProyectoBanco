package com.sandra.bancoArrayList.menus;

import java.util.List;

import javax.swing.JOptionPane;

import com.sandra.bancoArrayList.App;
import com.sandra.bancoArrayList.entidades.Cliente;
import com.sandra.bancoArrayList.entidades.Gestor;
import com.sandra.bancoArrayList.entidades.Mensaje;
import com.sandra.bancoArrayList.entidades.Transferencia;
import com.sandra.bancoArrayList.utilidades.GestorUtils;
import com.sandra.bancoArrayList.utilidades.MensajeUtils;

public class MensajeMenu {

	public static void switchMensaje(List<Gestor> gestores, List<Cliente> clientes, List<Mensaje> mensajes,
			List<Transferencia> transferencias) {
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
			else MensajeUtils.getMensaje(mensajes);
		}
		case "Obtención de todos los mensajes" -> {
			if (mensajes.size() == 0) System.err.println("No existe ningún mensaje. Se le redigirá al Menú Mensajes.");
			else MensajeUtils.getMensajes(mensajes);
		}
		case "Envío de un mensaje" -> {
			int id_origen = MensajeUtils.comprobarId(gestores, "remitente");
			int id_destino = -1;
			if (GestorUtils.existeGestor(gestores, id_origen, "Mensajes")) {
				id_destino = MensajeUtils.comprobarId(gestores, "destinatario");
				if (GestorUtils.existeGestor(gestores, id_destino, "Mensajes")) MensajeUtils.addMensaje(mensajes, id_origen, id_destino);
			}
		}
		case "Volver atrás" -> App.mainMenu(gestores, clientes, mensajes, transferencias);
		}
		if (!accion.equals("Volver atrás")) switchMensaje(gestores, clientes, mensajes, transferencias);
	}
}
