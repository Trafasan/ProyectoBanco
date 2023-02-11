package com.project.controllers;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.project.databases.DBGestor;
import com.project.functions.MainMenu;
import com.project.models.Gestor;
import com.project.utils.UIGestor;

public class GestorController {

	public static void switchGestor() {
		DBGestor dbGestor = new DBGestor();
		Gestor updateGestor;
		String accion;
		try {
			accion = JOptionPane
					.showInputDialog(null, "Seleccione una opción: ", "MENÚ GESTOR", JOptionPane.PLAIN_MESSAGE, null,
							new Object[] { "Inserción de un gestor", "Inserción de varios gestores",
									"Obtención de un gestor", "Obtención de todos los gestores",
									"Actualización de un gestor", "Eliminación de un gestor" },
							null)
					.toString();
		} catch (Exception e) {
			accion = "Volver atrás";
		}

		switch (accion) {
		case "Inserción de un gestor" -> {
			dbGestor.addGestor(UIGestor.addGestor());
			JOptionPane.showMessageDialog(null, "Inserción realizada correctamente");
		}
		case "Inserción de varios gestores" -> {
			int n = Integer.parseInt(JOptionPane.showInputDialog("Inserte el número de gestores a añadir:"));
			for (int i=0; i<n; i++) dbGestor.addGestores(UIGestor.addGestores());
			JOptionPane.showMessageDialog(null, "Inserciones realizada correctamente");
		}
		case "Obtención de un gestor" -> dbGestor.getGestor(UIGestor.getGestor());
		case "Obtención de todos los gestores" -> dbGestor.getGestores();
		case "Actualización de un gestor" -> {
			updateGestor = UIGestor.comprobarGestor();
			if (dbGestor.existeGestor(updateGestor)) GestorUpdateController.switchUpdateGestor(updateGestor);
		}
		case "Eliminación de un gestor" -> {
			updateGestor = UIGestor.comprobarGestor();
			if (dbGestor.existeGestor(updateGestor)) {
				int confirmacion;
				try {
					confirmacion = JOptionPane.showConfirmDialog(null,
							"¿Seguro que quieres eliminar al gestor " + updateGestor.getId_gestor() + "?",
							"MENSAJE DE CONFIRMACIÓN", 0, 3, new ImageIcon("src/images/eliminar.png"));
				} catch (Exception e) {
					confirmacion = 1;
				}
				switch (confirmacion) {
				case 0 -> {
					dbGestor.deleteGestor(updateGestor);
					JOptionPane.showMessageDialog(null, "Eliminación realizada correctamente");
				}
				case 1 -> JOptionPane.showMessageDialog(null, "No se ha eliminado el gestor " + updateGestor.getId_gestor());
				}
			}
		}
		case "Volver atrás" -> MainMenu.opciones();
		}
		if (!accion.equals("Volver atrás")) switchGestor();
	}
}