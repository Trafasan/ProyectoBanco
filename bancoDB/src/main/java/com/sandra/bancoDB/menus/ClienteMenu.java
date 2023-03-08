package com.sandra.bancoDB.menus;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.sandra.bancoDB.App;
import com.sandra.bancoDB.databases.DBCliente;
import com.sandra.bancoDB.entidades.Cliente;
import com.sandra.bancoDB.utilidades.UICliente;

public class ClienteMenu {

	public static void switchCliente() {
		boolean existeCliente;
		Cliente comprobacionCliente;
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
		case "Inserción de un cliente" -> {
			DBCliente.addCliente(UICliente.addCliente(DBCliente.getId_gestores()));
			JOptionPane.showMessageDialog(null, "Inserción realizada correctamente");
		}
		case "Obtención de un cliente" -> DBCliente.getCliente(UICliente.getCliente("a obtener"));
		case "Obtención de todos los clientes" -> DBCliente.getClientes();
		case "Actualización de un cliente" -> {
			Cliente updateCliente = UICliente.getCliente("que quieres modificar");
			if (DBCliente.existeCliente(updateCliente) && updateCliente != null) ClienteUpdateMenu.switchUpdateCliente(updateCliente);
		}
		case "Eliminación de un cliente" -> {
			do {
				comprobacionCliente = UICliente.comprobarCliente();
				// existeCliente = dbCliente.comprobarCliente(comprobacionCliente);
				existeCliente = true;
			}while(existeCliente == false);
			int confirmacion;
			try {
				confirmacion = JOptionPane.showConfirmDialog(null,
						"¿Seguro que quieres eliminar al cliente " + comprobacionCliente.getId_cliente() + "?",
						"MENSAJE DE CONFIRMACIÓN", 0, 3, new ImageIcon("src/main/java/com/sandra/bancoDB/images/eliminar.png"));
			} catch (Exception e) {
				confirmacion = 1;
			}
			switch (confirmacion) {
			case 0 -> {
				DBCliente.borrarCliente(comprobacionCliente);
				JOptionPane.showMessageDialog(null, "Eliminación realizada correctamente");
			}
			case 1 -> JOptionPane.showMessageDialog(null, "No se ha eliminado el cliente " + comprobacionCliente.getId_cliente());
			}
		}
		case "Volver atrás" -> App.mainMenu();
		}
		if (!accion.equals("Volver atrás")) switchCliente();
	}
}