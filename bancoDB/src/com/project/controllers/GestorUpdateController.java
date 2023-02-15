package com.project.controllers;

import javax.swing.JOptionPane;

import com.project.databases.DBGestor;
import com.project.models.Gestor;
import com.project.utils.UIGestor;

public class GestorUpdateController {

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
		case "Nombre" -> dbGestor.updateNombreGestor(UIGestor.updateGestor(updateGestor, "el nuevo nombre", id));
		case "Apellido(s)" -> dbGestor.updateApellidoGestor(UIGestor.updateGestor(updateGestor, "el(los) nuevo(s) apellido(s)", id));
		case "DNI" -> dbGestor.updateDniGestor(UIGestor.updateGestor(updateGestor, "el nuevo DNI", id));
		case "Usuario" -> dbGestor.updateUsuarioGestor(UIGestor.updateGestor(updateGestor, "el nuevo usuario", id));
		case "Contraseña" -> {
			String password = UIGestor.nuevoPassword("Introduzca la nueva contraseña", "ACTUALIZACIÓN DEL GESTOR " + id, 1);
			if (password != null) {
				dbGestor.updatePasswordGestor(UIGestor.updatePasswordGestor(updateGestor, password, id));
				JOptionPane.showMessageDialog(null, "Contraseña actualizada correctamente");
			}
		}
		case "Correo" -> dbGestor.updateCorreoGestor(UIGestor.updateGestor(updateGestor, "el nuevo correo", id));
		}
		if (!update.equals("Contraseña") && !update.equals("Volver atrás")) JOptionPane.showMessageDialog(null, update+" actualizado"+((update.equals("Apellido(s)")) ? "(s)": "")+" correctamente");
		if (!update.equals("Volver atrás")) switchUpdateGestor(updateGestor);
	}
}