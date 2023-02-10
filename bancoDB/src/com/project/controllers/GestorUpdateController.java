package com.project.controllers;

import javax.swing.JOptionPane;

import com.project.databases.DBGestor;
import com.project.models.Gestor;
import com.project.utils.UIGestor;

public class GestorUpdateController {

	public static void switchUpdateGestor(Gestor comprobacionGestor) {
		DBGestor dbGestor = new DBGestor();
		String update;
		try {
			update = JOptionPane.showInputDialog(null, "Seleccione el dato que quiere actualizar: ", "ACTUALIZACIÓN DEL GESTOR "+comprobacionGestor.getId_gestor(),
					JOptionPane.PLAIN_MESSAGE, null,
					new Object[] { "Nombre", "Apellido(s)", "DNI", "Usuario", "Contraseña", "Correo"}, null).toString();
		} catch (Exception e) {
			update = "Volver atrás";
		}

		switch (update) {
		case "Nombre" -> {
			dbGestor.updateNombreGestor(UIGestor.updateNombreGestor(comprobacionGestor));
			JOptionPane.showMessageDialog(null, "Nombre actualizado correctamente");
			switchUpdateGestor(comprobacionGestor);
		}
		case "Apellido(s)" -> {
			dbGestor.updateApellidoGestor(UIGestor.updateApellidoGestor(comprobacionGestor));
			JOptionPane.showMessageDialog(null, "Apellido(s) actualizado(s) correctamente");
			switchUpdateGestor(comprobacionGestor);
		}
		case "DNI" -> {
			dbGestor.updateDniGestor(UIGestor.updateDniGestor(comprobacionGestor));
			JOptionPane.showMessageDialog(null, "DNI actualizado correctamente");
			switchUpdateGestor(comprobacionGestor);
		}
		case "Usuario" -> {
			dbGestor.updateUsuarioGestor(UIGestor.updateUsuarioGestor(comprobacionGestor));
			JOptionPane.showMessageDialog(null, "Usuario actualizado correctamente");
			switchUpdateGestor(comprobacionGestor);
		}
		case "Contraseña" -> {
			dbGestor.updatePasswordGestor(UIGestor.updateUsuarioGestor(comprobacionGestor));
			JOptionPane.showMessageDialog(null, "Contraseña actualizada correctamente");
			switchUpdateGestor(comprobacionGestor);
		}
		case "Correo" -> {
			dbGestor.updateCorreoGestor(UIGestor.updateCorreoGestor(comprobacionGestor));
			JOptionPane.showMessageDialog(null, "Correo actualizado correctamente");
			switchUpdateGestor(comprobacionGestor);
		}
		}
	}
}