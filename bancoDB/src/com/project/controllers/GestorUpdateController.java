package com.project.controllers;

import javax.swing.JOptionPane;

import com.project.databases.DBGestor;
import com.project.models.Gestor;
import com.project.utils.UIGestor;

public class GestorUpdateController {

	public static void switchUpdateGestor(Gestor comprobacionGestor) {
		DBGestor dbGestor = new DBGestor();
		String opcionAct;
		try {
			opcionAct = JOptionPane.showInputDialog(null, "Seleccione dato que quiere actualizar: ", "ACTUALIZACIÓN DEL GESTOR "+comprobacionGestor.getId_gestor(),
					JOptionPane.PLAIN_MESSAGE, null,
					new Object[] { "Nombre", "Apellido(s)", "DNI", "Usuario", "Contraseña", "Correo"}, null).toString();
		} catch (Exception e) {
			opcionAct = "Volver atrás";
		}

		switch (opcionAct) {
		case "Nombre":
			dbGestor.updateNombreGestor(UIGestor.updateNombreGestor(comprobacionGestor));
			JOptionPane.showMessageDialog(null, "Nombre actualizado correctamente");
			switchUpdateGestor(comprobacionGestor);
			break;
		case "Apellido(s)":
			dbGestor.updateApellidoGestor(UIGestor.updateApellidoGestor(comprobacionGestor));
			JOptionPane.showMessageDialog(null, "Apellido(s) actualizado(s) correctamente");
			switchUpdateGestor(comprobacionGestor);
			break;
		case "DNI":
			dbGestor.updateDniGestor(UIGestor.updateDniGestor(comprobacionGestor));
			JOptionPane.showMessageDialog(null, "DNI actualizado correctamente");
			switchUpdateGestor(comprobacionGestor);
			break;
		case "Usuario":
			dbGestor.updateUsuarioGestor(UIGestor.updateUsuarioGestor(comprobacionGestor));
			JOptionPane.showMessageDialog(null, "Usuario actualizado correctamente");
			switchUpdateGestor(comprobacionGestor);
			break;
		case "Contraseña":
			dbGestor.updatePasswordGestor(UIGestor.updateUsuarioGestor(comprobacionGestor));
			JOptionPane.showMessageDialog(null, "Contraseña actualizada correctamente");
			switchUpdateGestor(comprobacionGestor);
			break;
		case "Correo":
			dbGestor.updateCorreoGestor(UIGestor.updateCorreoGestor(comprobacionGestor));
			JOptionPane.showMessageDialog(null, "Correo actualizado correctamente");
			switchUpdateGestor(comprobacionGestor);
			break;
		case "Volver atrás":
			break;
		}
	}
}