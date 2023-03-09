package com.sandra.bancoDB.menus;

import javax.swing.JOptionPane;

import com.sandra.bancoDB.App;
import com.sandra.bancoDB.databases.DBCliente;
import com.sandra.bancoDB.utilidades.UIGestorCliente;

public class ClienteMenu {

	public static void switchCliente() {
		String accion;
		try {
			accion = JOptionPane
					.showInputDialog(null, "Seleccione una opción: ", "MENÚ CLIENTE", JOptionPane.PLAIN_MESSAGE, null,
							new Object[] { "Inserción de un cliente", "Obtención de un cliente",
									"Obtención de todos los clientes", "Actualización de un cliente",
									"Eliminación de un cliente" }, null).toString();
		} catch (NullPointerException e) {
			accion = "Volver atrás";
		}

		switch (accion) {
		case "Inserción de un cliente" -> JOptionPane.showMessageDialog(null,(DBCliente.addCliente(UIGestorCliente.addCliente(
				DBCliente.getId_gestores()))) ? "Inserción realizada correctamente":"No se ha podido insertar el cliente");
		case "Obtención de un cliente" -> DBCliente.getCliente(UIGestorCliente.getId("Introduzca el ID del cliente a obtener: ", "Input", 3));
		case "Obtención de todos los clientes" -> DBCliente.getClientes();
		case "Actualización de un cliente" -> {
			int id_cliente = UIGestorCliente.getId("Introduzca el ID del cliente que quieres modificar: ", "Input", 3);
			if (DBCliente.existeCliente(id_cliente)) ClienteUpdateMenu.switchUpdateCliente(id_cliente);
		}
		case "Eliminación de un cliente" -> {
			int id_cliente = UIGestorCliente.getId("Introduzca el ID del cliente que quieres eliminar: ", "Input", 3);
			if (DBCliente.existeCliente(id_cliente)) UIGestorCliente.delete("cliente", id_cliente);
		}
		case "Volver atrás" -> App.mainMenu();
		}
		if (!accion.equals("Volver atrás")) switchCliente();
	}
}