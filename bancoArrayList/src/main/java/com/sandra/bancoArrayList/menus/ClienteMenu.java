package com.sandra.bancoArrayList.menus;

import java.util.List;

import javax.swing.JOptionPane;

import com.sandra.bancoArrayList.App;
import com.sandra.bancoArrayList.entidades.Cliente;
import com.sandra.bancoArrayList.entidades.Gestor;
import com.sandra.bancoArrayList.entidades.Mensaje;
import com.sandra.bancoArrayList.entidades.Transferencia;
import com.sandra.bancoArrayList.utilidades.ClienteUtils;

public class ClienteMenu {
	
	public static void switchCliente(List<Gestor> gestores, List<Cliente> clientes, List<Mensaje> mensajes, List<Transferencia> transferencias) {
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
		case "Inserción de un cliente" -> ClienteUtils.addCliente(gestores, clientes);
		case "Obtención de un cliente" -> {
			if (clientes.size() == 0) System.err.println("No existe ningún cliente. Se le redigirá al Menú Cliente.");
			else ClienteUtils.getCliente(clientes);
		}
		case "Obtención de todos los clientes" -> {
			if (clientes.size() == 0) System.err.println("No existe ningún cliente. Se le redigirá al Menú Cliente.");
			else ClienteUtils.getClientes(clientes);
		}
		case "Actualización de un cliente" -> {
			if (clientes.size() == 0) System.err.println("No existe ningún cliente. Se le redigirá al Menú Cliente.");
			int id = ClienteUtils.comprobarIdCliente(clientes);
			if (ClienteUtils.existeCliente(clientes, id, "Cliente")) ClienteUpdateMenu.switchUpdateCliente(gestores, clientes, id);
		}
		case "Eliminación de un cliente" -> {
			if (clientes.size() == 0) System.err.println("No existe ningún cliente. Se le redigirá al Menú Cliente.");
			else ClienteUtils.deleteCliente(clientes);
		}
		case "Volver atrás" -> App.mainMenu(gestores, clientes, mensajes, transferencias);
		}
		if (!accion.equals("Volver atrás")) switchCliente(gestores, clientes, mensajes, transferencias);
	}
}
