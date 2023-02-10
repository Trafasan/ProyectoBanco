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
		boolean existeCliente;
		Cliente comprobacionCliente;
		String accion;
		try {
			accion = JOptionPane
					.showInputDialog(null, "Seleccione una opción: ", "MENÚ CLIENTE", JOptionPane.PLAIN_MESSAGE, null,
							new Object[] { "Inserción de un cliente", "Obtención de un cliente",
									"Obtención de todos los clientes", "Actualización de un cliente",
									"Eliminación de un cliente" }, null).toString();
		} catch (Exception e) {
			accion = "Volver atrás";
		}

		switch (accion) {
		case "Inserción de un cliente" -> {
			boolean existeId_gestor;
			Gestor comprobacionId_gestor;
			do {
				comprobacionId_gestor = UICliente.comprobarId_gestorInsertar();
				existeId_gestor = dbCliente.comprobarId_gestorInsertar(comprobacionId_gestor);
			}while (existeId_gestor == false);
			dbCliente.crearUnCliente(UICliente.insertarCliente(comprobacionId_gestor));
			JOptionPane.showMessageDialog(null, "Inserción realizada correctamente");
			switchCliente();
		}
		case "Obtención de un cliente" -> {
			dbCliente.leerUnCliente(UICliente.obtencionCliente());
			switchCliente();
		}
		case "Obtención de todos los clientes" -> {
			dbCliente.leerClientes();
			switchCliente();
		}
		case "Actualización de un cliente" -> {
			do {
				comprobacionCliente = UICliente.comprobarCliente();
				existeCliente = dbCliente.comprobarCliente(comprobacionCliente);
			}while(existeCliente == false);
			ClienteUpdateController.switchUpdateCliente(comprobacionCliente);
			switchCliente();
		}
		case "Eliminación de un cliente" -> {
			do {
				comprobacionCliente = UICliente.comprobarCliente();
				existeCliente = dbCliente.comprobarCliente(comprobacionCliente);
			}while(existeCliente == false);
			int confirmacion;
			try {
				confirmacion = JOptionPane.showConfirmDialog(null,
						"¿Seguro que quieres eliminar al cliente " + comprobacionCliente.getId_cliente() + "?",
						"MENSAJE DE CONFIRMACIÓN", 0, 3, new ImageIcon("src/images/eliminar.png"));
			} catch (Exception e) {
				confirmacion = 1;
			}
			switch (confirmacion) {
			case 0 -> {
				dbCliente.borrarCliente(comprobacionCliente);
				JOptionPane.showMessageDialog(null, "Eliminación realizada correctamente");
			}
			case 1 -> JOptionPane.showMessageDialog(null, "No se ha eliminado el cliente " + comprobacionCliente.getId_cliente());
			}
			switchCliente();
		}
		case "Volver atrás" -> MainMenu.opciones();
		}
	}
}