package com.project.controllers;

import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

import com.project.models.Gestor;
import com.project.utils.UIGestor;

public class GestorUpdateController {

	public static void switchUpdateGestor(List<Gestor> gestores, Scanner sc, int id) {
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
		case "Nombre" -> UIGestor.updateNombreGestor(gestores, sc, id);
		case "Apellido(s)" -> UIGestor.updateApellidoGestor(gestores, sc, id);
		case "DNI" -> UIGestor.updateDniGestor(gestores, sc, id);
		case "Usuario" -> UIGestor.updateUsuarioGestor(gestores, sc, id);
		case "Contraseña" -> UIGestor.updatePasswordGestor(gestores, sc, id);
		case "Correo" -> UIGestor.updateCorreoGestor(gestores, sc, id);
		}
		if (!update.equals("Volver atrás")) System.out.println("Actualización realizada correctamente");
	}
}
