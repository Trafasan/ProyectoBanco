package com.sandra.bancoDB.menus;

import javax.swing.JOptionPane;

import com.sandra.bancoDB.App;
import com.sandra.bancoDB.databases.DBCliente;
import com.sandra.bancoDB.databases.DBGestor;
import com.sandra.bancoDB.databases.DBMensaje;
import com.sandra.bancoDB.utilidades.UIMensaje;

public class MensajeMenu {

	public static void switchMensaje() {		
		String accion;
		try {
			accion = JOptionPane.showInputDialog(null, "Seleccione una opción: ", "MENÚ MENSAJES",
					JOptionPane.PLAIN_MESSAGE, null, new Object[] { "Obtención de un mensaje",
							"Obtención de todos los mensajes", "Envío de un mensaje" }, null).toString();
		} catch (Exception e) {
			accion = "Volver atrás";
		}

		switch (accion) {
		case "Obtención de un mensaje" -> DBMensaje.getMensaje(UIMensaje.getMensaje());
		case "Obtención de todos los mensajes" -> DBMensaje.getMensajes();
		case "Envío de un mensaje" -> {
			int id_gestor, id_cliente;
			do{
				id_gestor = UIMensaje.comprobarId("gestor");
			} while(!DBGestor.existeGestor(id_gestor));
			do{
				id_cliente = UIMensaje.comprobarId("cliente");
			} while(!DBCliente.existeCliente(id_cliente));
			if (DBMensaje.addMensaje(UIMensaje.addMensaje(id_gestor, id_cliente))) JOptionPane.showMessageDialog(null, "Envío realizado correctamente");
		}
		case "Volver atrás" ->  App.mainMenu();
		}
		if (!accion.equals("Volver atrás")) switchMensaje();
	}
}