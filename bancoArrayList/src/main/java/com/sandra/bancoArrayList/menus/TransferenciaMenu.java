package com.sandra.bancoArrayList.menus;

import java.util.List;

import javax.swing.JOptionPane;

import com.sandra.bancoArrayList.App;
import com.sandra.bancoArrayList.entidades.Cliente;
import com.sandra.bancoArrayList.entidades.Gestor;
import com.sandra.bancoArrayList.entidades.Mensaje;
import com.sandra.bancoArrayList.entidades.Transferencia;
import com.sandra.bancoArrayList.utilidades.ClienteUtils;
import com.sandra.bancoArrayList.utilidades.TransferenciaUtils;

public class TransferenciaMenu {

	public static void switchTransferencia(List<Gestor> gestores, List<Cliente> clientes, List<Mensaje> mensajes, List<Transferencia> transferencias) {
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
			else TransferenciaUtils.getTransferencia(transferencias);
		}
		case "Obtención de todas las transferencias" -> {
			if (transferencias.size() == 0) System.err.println("No existe ninguna transferencia. Se le redigirá al Menú Transferencias.");
			else TransferenciaUtils.getTransferencias(transferencias);
		}
		case "Envío de una transferencia" -> {
			int id_ordenante = TransferenciaUtils.comprobarId(clientes, "ordenante");
			int id_beneficiario = -1;
			if (ClienteUtils.existeCliente(clientes, id_ordenante, "Transferencias")) {
				id_beneficiario = TransferenciaUtils.comprobarId(clientes, "destinatario");
				if (ClienteUtils.existeCliente(clientes, id_beneficiario, "Transferencias"))
					TransferenciaUtils.addTransferencia(transferencias, clientes, id_ordenante, id_beneficiario);
			}
		}
		case "Volver atrás" -> App.mainMenu(gestores, clientes, mensajes, transferencias);
		}
		if (!accion.equals("Volver atrás")) switchTransferencia(gestores, clientes, mensajes, transferencias);
	}
}
