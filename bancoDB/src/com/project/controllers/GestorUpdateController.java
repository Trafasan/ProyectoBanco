package com.project.controllers;

import javax.swing.JOptionPane;

import com.project.databases.DBGestor;
import com.project.models.Gestor;
import com.project.utils.UIGestor;

public class GestorUpdateController {

	public static void switchUpdateGestor(Gestor updateGestor) {
		DBGestor dbGestor = new DBGestor();
		String update;
		try {
			update = JOptionPane.showInputDialog(null, "Seleccione el dato que quiere actualizar: ", "ACTUALIZACIÓN DEL GESTOR "+updateGestor.getId_gestor(),
					JOptionPane.PLAIN_MESSAGE, null,
					new Object[] { "Nombre", "Apellido(s)", "DNI", "Usuario", "Contraseña", "Correo"}, null).toString();
		} catch (Exception e) {
			update = "Volver atrás";
		}

		switch (update) {
		case "Nombre" -> dbGestor.updateNombreGestor(UIGestor.updateGestor(updateGestor, "nombre"));
		case "Apellido(s)" -> dbGestor.updateApellidoGestor(UIGestor.updateApellidoGestor(updateGestor));
		case "DNI" -> dbGestor.updateDniGestor(UIGestor.updateGestor(updateGestor, "DNI"));
		case "Usuario" -> dbGestor.updateUsuarioGestor(UIGestor.updateGestor(updateGestor, "usuario"));
		case "Contraseña" -> dbGestor.updatePasswordGestor(UIGestor.updatePasswordGestor(updateGestor));
		case "Correo" -> dbGestor.updateCorreoGestor(UIGestor.updateGestor(updateGestor, "correo"));
		}
		if (!update.equals("Volver atrás")) {
			JOptionPane.showMessageDialog(null, update+" actualizado"+((update.equals("Apellido(s)")) ? "(s)": "")+" correctamente");
			switchUpdateGestor(updateGestor);
		}
	}
}