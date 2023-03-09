package com.sandra.bancoDB.menus;

import javax.swing.JOptionPane;

import com.sandra.bancoDB.databases.DBGestor;
import com.sandra.bancoDB.utilidades.UIGestorCliente;

public class GestorUpdateMenu {
	public static void switchUpdateGestor(int id) {
		String update;
		try {
			update = JOptionPane.showInputDialog(null, "Seleccione el dato que quiere actualizar: ", "ACTUALIZACIÓN DEL GESTOR "+id,
					JOptionPane.PLAIN_MESSAGE, null,
					new Object[] {"Nombre", "Apellido(s)", "DNI", "Usuario", "Contraseña", "Correo"}, null).toString();
		} catch (NullPointerException e) {
			update = "Volver atrás";
		}

		switch (update) {
		case "Nombre" -> JOptionPane.showMessageDialog(null,
				(DBGestor.updateDatoGestor(UIGestorCliente.updateGestor("el nuevo nombre", id), "nombre")) ?
						"Nombre actualizado correctamente":"No se ha podido actualizar el nombre");
		case "Apellido(s)" -> JOptionPane.showMessageDialog(null,
				(DBGestor.updateDatoGestor(UIGestorCliente.updateGestor("el(los) nuevo(s) apellido(s)", id), "apellido")) ?
						"Apellido(s) actualizado(s) correctamente":"No se ha podido actualizar el(los) apellido(s)");
		case "DNI" -> JOptionPane.showMessageDialog(null,
				(DBGestor.updateDatoGestor(UIGestorCliente.updateGestor("el nuevo DNI", id), "dni")) ?
						"DNI actualizado correctamente":"No se ha podido actualizar el DNI");
		case "Usuario" -> JOptionPane.showMessageDialog(null,
				(DBGestor.updateDatoGestor(UIGestorCliente.updateGestor("el nuevo usuario", id), "usuario")) ?
						"Usuario actualizado correctamente":"No se ha podido actualizar el usuario");
		case "Contraseña" -> {
			String password = UIGestorCliente.nuevoPassword("Introduzca la nueva contraseña", "ACTUALIZACIÓN DEL GESTOR " + id, 1);
			JOptionPane.showMessageDialog(null,
					(DBGestor.updateDatoGestor(UIGestorCliente.updatePasswordGestor(password, id), "password")) ?
							"Contraseña actualizada correctamente":"No se ha podido actualizar la contraseña");
		}
		case "Correo" -> JOptionPane.showMessageDialog(null,
				(DBGestor.updateDatoGestor(UIGestorCliente.updateGestor("el nuevo correo", id), "correo")) ?
						"Correo actualizado correctamente":"No se ha podido actualizar el correo");
		}
		if (!update.equals("Volver atrás")) switchUpdateGestor(id);
	}
}