package com.project.controllers;

import javax.swing.JOptionPane;

import com.project.databases.DBTransferencia;
import com.project.functions.MainMenu;
import com.project.models.Cliente;
import com.project.utils.UITransferencia;

public class TransferenciaController {

	public static void switchTransferencia() {
		DBTransferencia dbTransferencia = new DBTransferencia();
		String nuevaOpcion;
		try {
			nuevaOpcion = JOptionPane.showInputDialog(null, "Inserte una opción: ", "MENÚ TRANSFERENCIAS",
					JOptionPane.PLAIN_MESSAGE, null, new Object[] { "Obtención de una transferencia",
							"Obtención de todas las transferencias", "Envío de una transferencia" }, null).toString();
		} catch (Exception e) {
			nuevaOpcion = "Volver atrás";
		}

		switch (nuevaOpcion) {
		case "Obtención de una transferencia":
			dbTransferencia.leerUnaTransferencia(UITransferencia.obtencionTransferencia());
			switchTransferencia();
			break;
		case "Obtención de todas las transferencias":
			dbTransferencia.leerTransferencias();
			switchTransferencia();
			break;
		case "Envío de una transferencia":
			boolean existeId_ordenante, existeId_beneficiario;
			Cliente comprobacionId_ordenante, comprobacionId_beneficiario;
			int id_ordenante, id_beneficiario;
			do {
				do{
					comprobacionId_ordenante = UITransferencia.comprobarId_ordenante();
					id_ordenante = comprobacionId_ordenante.getId_cliente();
					existeId_ordenante = dbTransferencia.comprobarId_ordenante(comprobacionId_ordenante);
				}while(existeId_ordenante == false);
				
				do{
					comprobacionId_beneficiario = UITransferencia.comprobarId_beneficiario();
					id_beneficiario = comprobacionId_beneficiario.getId_cliente();
					existeId_beneficiario = dbTransferencia.comprobarId_beneficiario(comprobacionId_beneficiario);
				}while(existeId_beneficiario == false);
				
				if(id_ordenante == id_beneficiario) {
					JOptionPane.showMessageDialog(null, "El cliente beneficiario no puede ser el cliente ordenante", "ERROR", 0);
				}
			}while(id_ordenante == id_beneficiario);
			// dbMensaje.enviarMensaje(UIMensaje.envíoMensaje(comprobacionId_origen, comprobacionId_destino));
			JOptionPane.showMessageDialog(null, "Transferencia no realizada\nSe debe retocar la función");
			//JOptionPane.showMessageDialog(null, "Envío realizado correctamente");
			switchTransferencia();
			break;
		case "Volver atrás":
			MainMenu.opciones();
		}
	}
}