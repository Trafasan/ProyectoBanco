package com.project.controllers;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.project.databases.DBCliente;
import com.project.functions.MainMenu;
import com.project.models.Cliente;
import com.project.models.Gestor;
import com.project.utils.UICliente;

public class ClienteController {

	public static void switchCliente() {
		DBCliente dbCliente = new DBCliente();
		String nuevaOpcion;
		try {
			nuevaOpcion = JOptionPane
					.showInputDialog(null, "Inserte una opción: ", "MENÚ CLIENTE", JOptionPane.PLAIN_MESSAGE, null,
							new Object[] { "Inserción de un cliente", "Obtención de un cliente",
									"Obtención de todos los clientes", "Actualización de un cliente",
									"Eliminación de un cliente" }, null).toString();
		} catch (Exception e) {
			nuevaOpcion = "Volver atrás";
		}

		switch (nuevaOpcion) {
		case "Inserción de un cliente":
			boolean existeId_gestor;
			Gestor comprobacionId_gestor;
			do {
				comprobacionId_gestor = UICliente.comprobarId_gestorInsertar();
				existeId_gestor = dbCliente.comprobarId_gestorInsertar(comprobacionId_gestor);
			}while (existeId_gestor == false);
			dbCliente.crearUnCliente(UICliente.insertarCliente(comprobacionId_gestor));
			JOptionPane.showMessageDialog(null, "Inserción realizada correctamente");
			switchCliente();
			break;
		case "Obtención de un cliente":
			dbCliente.leerUnCliente(UICliente.obtencionCliente());
			switchCliente();
			break;
		case "Obtención de todos los clientes":
			dbCliente.leerClientes();
			switchCliente();
			break;
		case "Actualización de un cliente":
			boolean existeCliente;
			Cliente comprobacionCliente;
			do {
				comprobacionCliente = UICliente.comprobarCliente();
				existeCliente = dbCliente.comprobarCliente(comprobacionCliente);
			}while(existeCliente == false);
			ClienteUpdateController.switchUpdateCliente(comprobacionCliente);
			switchCliente();
			break;
		case "Eliminación de un cliente":
			do {
				comprobacionCliente = UICliente.comprobarCliente();
				existeCliente = dbCliente.comprobarCliente(comprobacionCliente);
			}while(existeCliente == false);
			int confirmacion;
			try {
				ImageIcon icon = new ImageIcon("src/images/eliminar.png");
				confirmacion = JOptionPane.showConfirmDialog(null,
						"¿Seguro que quieres eliminar al cliente " + comprobacionCliente.getId_cliente() + "?",
						"MENSAJE DE CONFIRMACIÓN", 0, 3, icon);
			} catch (Exception e) {
				confirmacion = 1;
			}
			switch (confirmacion) {
			case 0:
				dbCliente.borrarCliente(comprobacionCliente);
				JOptionPane.showMessageDialog(null, "Eliminación realizada correctamente");
				break;
			case 1:
				JOptionPane.showMessageDialog(null,
						"No se ha eliminado el cliente " + comprobacionCliente.getId_cliente());
				break;
			}
			switchCliente();
			break;
		case "Volver atrás":
			MainMenu.opciones();
		}
	}
}