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

public class ClienteController {
	
	public static void switchCliente(List<Gestor> gestores, List<Cliente> clientes, List<Mensaje> mensajes, List<Transferencia> transferencias, Scanner sc) {
		String accion;
		try {
			accion = JOptionPane
					.showInputDialog(null, "Inserte una opción: ", "MENÚ CLIENTE", JOptionPane.PLAIN_MESSAGE, null,
							new Object[] { "Inserción de un cliente", "Obtención de un cliente",
									"Obtención de todos los clientes", "Actualización de un cliente",
									"Eliminación de un cliente" }, null).toString();
		} catch (Exception e) {
			accion = "Volver atrás";
		}

		switch (accion) {
		case "Inserción de un cliente" -> UICliente.addCliente(gestores, clientes, sc);
		case "Obtención de un cliente" -> {
			if (clientes.size() == 0) System.err.println("No existe ningún cliente. Se le redigirá al Menú Cliente.");
			else UICliente.getCliente(clientes, sc);
		}
		case "Obtención de todos los clientes" -> {
			if (clientes.size() == 0) System.err.println("No existe ningún cliente. Se le redigirá al Menú Cliente.");
			else UICliente.getClientes(clientes);
		}
		case "Actualización de un cliente" -> {
			if (clientes.size() == 0) System.err.println("No existe ningún cliente. Se le redigirá al Menú Cliente.");
			int id = UICliente.comprobarIdCliente(clientes, sc);
			if (UICliente.existeCliente(clientes, sc, id, "Cliente")) ClienteUpdateController.switchUpdateCliente(gestores, clientes, sc, id);
		}
		case "Eliminación de un cliente" -> {
			if (clientes.size() == 0) System.err.println("No existe ningún cliente. Se le redigirá al Menú Cliente.");
			else UICliente.deleteCliente(clientes, sc);
		}
		case "Volver atrás" -> MainMenu.opciones(gestores, clientes, mensajes, transferencias, sc);
		}
		if (!accion.equals("Volver atrás")) switchCliente(gestores, clientes, mensajes, transferencias, sc);
	}
}
