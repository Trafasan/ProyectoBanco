package com.sandra.bancoDB.menus;

import javax.swing.JOptionPane;

import com.sandra.bancoDB.databases.DBGestor;
import com.sandra.bancoDB.entidades.Gestor;
import com.sandra.bancoDB.utilidades.UIGestor;

public class GestorUpdateMenu {

	public static void switchUpdateGestor(Gestor updateGestor) {
		DBGestor dbGestor = new DBGestor();
		int id = updateGestor.getId_gestor();
		String update;
		try {
			update = JOptionPane.showInputDialog(null, "Seleccione el dato que quiere actualizar: ", "ACTUALIZACIÓN DEL GESTOR "+id,
					JOptionPane.PLAIN_MESSAGE, null,
					new Object[] { "Nombre", "Apellido(s)", "DNI", "Usuario", "Contraseña", "Correo"}, null).toString();
		} catch (NullPointerException e) {
			update = "Volver atrás";
		}

		switch (update) {
		case "Nombre" -> dbGestor.updateDatoGestor(UIGestor.updateGestor(updateGestor, "el nuevo nombre", id), "nombre");
		case "Apellido(s)" -> dbGestor.updateDatoGestor(UIGestor.updateGestor(updateGestor, "el(los) nuevo(s) apellido(s)", id), "apellido");
		case "DNI" -> dbGestor.updateDatoGestor(UIGestor.updateGestor(updateGestor, "el nuevo DNI", id), "dni");
		case "Usuario" -> dbGestor.updateDatoGestor(UIGestor.updateGestor(updateGestor, "el nuevo usuario", id), "usuario");
		case "Contraseña" -> {
			String password = UIGestor.nuevoPassword("Introduzca la nueva contraseña", "ACTUALIZACIÓN DEL GESTOR " + id, 1);
			if (password != null) {
				dbGestor.updateDatoGestor(UIGestor.updatePasswordGestor(updateGestor, password, id), "password");
				JOptionPane.showMessageDialog(null, "Contraseña actualizada correctamente");
			}
		}
		case "Correo" -> dbGestor.updateDatoGestor(UIGestor.updateGestor(updateGestor, "el nuevo correo", id), "correo");
		}
		if (!update.equals("Contraseña") && !update.equals("Volver atrás")) JOptionPane.showMessageDialog(null, update+" actualizado"+((update.equals("Apellido(s)")) ? "(s)": "")+" correctamente");
		if (!update.equals("Volver atrás")) switchUpdateGestor(updateGestor);
	}
}