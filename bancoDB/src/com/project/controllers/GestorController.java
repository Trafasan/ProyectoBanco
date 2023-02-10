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
		boolean existeGestor;
		Gestor comprobacionGestor;
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
			dbGestor.crearUnGestor(UIGestor.insertarGestor());
			JOptionPane.showMessageDialog(null, "Inserción realizada correctamente");
			switchGestor();
		}
		case "Inserción de varios gestores" -> {
			int numero = Integer.parseInt(JOptionPane.showInputDialog("Inserte el número de gestores a añadir:"));
			for (int x = 0; x < numero; x++) {
				dbGestor.crearGestorAleatorio(UIGestor.insGestorAleatorio());
			}
			JOptionPane.showMessageDialog(null, "Inserciones realizada correctamente");
			switchGestor();
		}
		case "Obtención de un gestor" -> {
			dbGestor.leerUnGestor(UIGestor.obtencionGestor());
			switchGestor();
		}
		case "Obtención de todos los gestores" -> {
			dbGestor.leerGestores();
			switchGestor();
		}
		case "Actualización de un gestor" -> {
			do {
				comprobacionGestor = UIGestor.comprobarGestor();
				existeGestor = dbGestor.comprobarGestor(comprobacionGestor);
			} while (existeGestor == false);
			GestorUpdateController.switchUpdateGestor(comprobacionGestor);
			switchGestor();
		}
		case "Eliminación de un gestor" -> {
			do {
				comprobacionGestor = UIGestor.comprobarGestor();
				existeGestor = dbGestor.comprobarGestor(comprobacionGestor);
			} while (existeGestor == false);
			int confirmacion;
			try {
				confirmacion = JOptionPane.showConfirmDialog(null,
						"¿Seguro que quieres eliminar al gestor " + comprobacionGestor.getId_gestor() + "?",
						"MENSAJE DE CONFIRMACIÓN", 0, 3, new ImageIcon("src/images/eliminar.png"));
			} catch (Exception e) {
				confirmacion = 1;
			}
			switch (confirmacion) {
			case 0:
				dbGestor.borrarGestor(comprobacionGestor);
				JOptionPane.showMessageDialog(null, "Eliminación realizada correctamente");
				break;
			case 1:
				JOptionPane.showMessageDialog(null,
						"No se ha eliminado el gestor " + comprobacionGestor.getId_gestor());
				break;
			}

			switchGestor();
		}
		case "Volver atrás" -> MainMenu.opciones();
		}
	}
}