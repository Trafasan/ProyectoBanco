package com.sandra.bancoDB.menus;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.sandra.bancoDB.databases.DBCliente;
import com.sandra.bancoDB.utilidades.UIGestorCliente;

public class ClienteUpdateMenu {

	public static void switchUpdateCliente(int id) {
		String update;
		try {
			update = JOptionPane.showInputDialog(null, "Seleccione el dato que quiere actualizar: ", "ACTUALIZACIÓN DEL CLIENTE "+id,
					JOptionPane.PLAIN_MESSAGE, null,
					new Object[] {"ID del gestor", "Nombre", "Apellido(s)", "DNI", "Usuario", "Contraseña", "Correo", "Saldo"}, null).toString();
		} catch (NullPointerException e) {
			update = "Volver atrás";
		}

		switch (update) {
		case "ID del gestor" -> {
			int id_gestor = UIGestorCliente.getId("Introduzca el ID del nuevo gestor:", "ACTUALIZACIÓN DEL CLIENTE " + id, 1);
			if (UIGestorCliente.existeIdGestor(DBCliente.getId_gestores(), id_gestor))
				JOptionPane.showMessageDialog(null,
						(DBCliente.updateDatoCilente(UIGestorCliente.updateId_gestorCliente(id, id_gestor), "id_gestor")) ?
								"ID del gestor actualizado correctamente":"No se ha podido actualizar el ID del gestor");
			else JOptionPane.showMessageDialog(null, "No existe ningún gestor con ese ID\nSe le redigirá al menú Cliente", "ERROR", 0, new ImageIcon("src/main/java/com/sandra/bancoDB/images/preocupado.png"));
		}
		case "Nombre" -> JOptionPane.showMessageDialog(null,
				(DBCliente.updateDatoCilente(UIGestorCliente.updateCliente("el nuevo nombre", id), "nombre")) ?
						"Nombre actualizado correctamente":"No se ha podido actualizar el nombre");
		case "Apellido(s)" -> JOptionPane.showMessageDialog(null,
				(DBCliente.updateDatoCilente(UIGestorCliente.updateCliente("el(los) nuevo(s) apellido(s)", id), "apellido")) ?
						"Apellido(s) actualizado(s) correctamente":"No se ha podido actualizar el(los) apellido(s)");
		case "DNI" -> JOptionPane.showMessageDialog(null,
				(DBCliente.updateDatoCilente(UIGestorCliente.updateCliente("el nuevo DNI", id), "dni")) ?
						"DNI actualizado correctamente":"No se ha podido actualizar el DNI");
		case "Usuario" -> JOptionPane.showMessageDialog(null,
				(DBCliente.updateDatoCilente(UIGestorCliente.updateCliente("el nuevo usuario", id), "usuario")) ?
						"Usuario actualizado correctamente":"No se ha podido actualizar el usuario");
		case "Contraseña" -> {
			String password = UIGestorCliente.nuevoPassword("Introduzca la nueva contraseña", "ACTUALIZACIÓN DEL CLIENTE " + id, 1);
			JOptionPane.showMessageDialog(null,
					(DBCliente.updateDatoCilente(UIGestorCliente.updatePasswordCliente(id, password), "password")) ?
							"Contraseña actualizada correctamente":"No se ha podido actualizar la contraseña");
		}
		case "Correo" -> JOptionPane.showMessageDialog(null,
				(DBCliente.updateDatoCilente(UIGestorCliente.updateCliente("el nuevo correo", id), "correo")) ?
						"Correo actualizado correctamente":"No se ha podido actualizar el correo");
		case "Saldo" -> JOptionPane.showMessageDialog(null,
				(DBCliente.updateDatoCilente(UIGestorCliente.updateSaldoCliente(id), "saldo")) ?
						"Saldo actualizado correctamente":"No se ha podido actualizar el saldo");
		}
		if (!update.equals("Volver atrás")) switchUpdateCliente(id);
	}
}