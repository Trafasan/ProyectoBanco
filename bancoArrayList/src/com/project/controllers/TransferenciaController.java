package com.project.controllers;

import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

import com.project.functions.MainMenu;
import com.project.models.Cliente;
import com.project.models.Gestor;
import com.project.models.Mensaje;
import com.project.models.Transferencia;
import com.project.utils.UICliente;
import com.project.utils.UITransferencia;

public class TransferenciaController {

	public static void switchTransferencia(List<Gestor> gestores, List<Cliente> clientes, List<Mensaje> mensajes, List<Transferencia> transferencias, Scanner sc) {
		String accion;
		try {
			accion = JOptionPane
					.showInputDialog(null, "Inserte una opción: ", "MENÚ TRANSFERENCIAS", JOptionPane.PLAIN_MESSAGE,
							null, new Object[] { "Obtención de una transferencia",
									"Obtención de todas las transferencias", "Envío de una transferencia" }, null).toString();
		} catch (Exception e) {
			accion = "Volver atrás";
		}

		switch (accion) {
		case "Obtención de una transferencia" -> {
			if (transferencias.size() == 0) System.err.println("No existe ninguna transferencia. Se le redigirá al Menú Transferencias.");
			else UITransferencia.getTransferencia(transferencias, sc);
		}
		case "Obtención de todas las transferencias" -> {
			if (transferencias.size() == 0) System.err.println("No existe ninguna transferencia. Se le redigirá al Menú Transferencias.");
			else UITransferencia.getTransferencias(transferencias);
		}
		case "Envío de una transferencia" -> {
			int id_ordenante = UITransferencia.comprobarId(clientes, sc, "ordenante");
			int id_beneficiario = -1;
			if (UICliente.existeCliente(clientes, sc, id_ordenante, "Transferencias")) {
				id_beneficiario = UITransferencia.comprobarId(clientes, sc, "destinatario");
				if (UICliente.existeCliente(clientes, sc, id_beneficiario, "Transferencias"))
					UITransferencia.addTransferencia(transferencias, clientes, id_ordenante, id_beneficiario, sc);
			}
		}
		case "Volver atrás" -> MainMenu.opciones(gestores, clientes, mensajes, transferencias, sc);
		}
		if (!accion.equals("Volver atrás")) switchTransferencia(gestores, clientes, mensajes, transferencias, sc);
	}
}
