package com.sandra.bancoArrayList.menus;

import java.util.List;

import javax.swing.JOptionPane;

import com.sandra.bancoArrayList.entidades.Gestor;
import com.sandra.bancoArrayList.utilidades.GestorUtils;

public class GestorUpdateMenu {

	public static void switchUpdateGestor(List<Gestor> gestores, int id) {
		String update;
		try {
			update = JOptionPane
					.showInputDialog(null, "Seleccione dato que quiere actualizar: ",
							"ACTUALIZACIÓN DEL GESTOR " + (id+1), JOptionPane.PLAIN_MESSAGE, null,
							new Object[] { "Nombre", "Apellido(s)", "DNI", "Usuario", "Contraseña", "Correo" }, null)
					.toString();
		} catch (Exception e) {
			update = "Volver atrás";
		}
		switch (update) {
		case "Nombre" -> GestorUtils.updateNombreGestor(gestores, id);
		case "Apellido(s)" -> GestorUtils.updateApellidoGestor(gestores, id);
		case "DNI" -> GestorUtils.updateDniGestor(gestores, id);
		case "Usuario" -> GestorUtils.updateUsuarioGestor(gestores, id);
		case "Contraseña" -> GestorUtils.updatePasswordGestor(gestores, id);
		case "Correo" -> GestorUtils.updateCorreoGestor(gestores, id);
		}
		if (!update.equals("Volver atrás")) System.out.println("Actualización realizada correctamente");
	}
}
