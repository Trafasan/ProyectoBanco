package com.sandra.bancoDB.menus;

import javax.swing.JOptionPane;

import com.sandra.bancoDB.App;
import com.sandra.bancoDB.databases.DBGestor;
import com.sandra.bancoDB.utilidades.UIGestor;

public class GestorMenu {

	public static void switchGestor() {
		String accion;
		try {
			accion = JOptionPane
					.showInputDialog(null, "Seleccione una opción: ", "MENÚ GESTOR", JOptionPane.PLAIN_MESSAGE, null,
							new Object[] { "Inserción de un gestor", "Inserción de varios gestores",
									"Obtención de un gestor", "Obtención de todos los gestores",
									"Actualización de un gestor", "Eliminación de un gestor" }, null).toString();
		} catch (NullPointerException e) {
			accion = "Volver atrás";
		}

		switch (accion) {
		case "Inserción de un gestor" -> {
			DBGestor.addGestor(UIGestor.addGestor());
			JOptionPane.showMessageDialog(null, "Inserción realizada correctamente");
		}
		case "Inserción de varios gestores" -> {
			int n = Integer.parseInt(JOptionPane.showInputDialog("Inserte el número de gestores a añadir:"));
			for (int i=0; i<n; i++) DBGestor.addGestor(UIGestor.addGestores());
			JOptionPane.showMessageDialog(null, "Inserciones realizada correctamente");
		}
		case "Obtención de un gestor" -> DBGestor.getGestor(UIGestor.getId_gestor("a obtener"));
		case "Obtención de todos los gestores" -> DBGestor.getGestores();
		case "Actualización de un gestor" -> {
			int id_gestor = UIGestor.getId_gestor("que quieres modificar");
			if (DBGestor.existeGestor(id_gestor) && id_gestor != -1) GestorUpdateMenu.switchUpdateGestor(id_gestor);
		}
		case "Eliminación de un gestor" -> {
			int id_gestor = UIGestor.getId_gestor("que quieres eliminar");
			if (DBGestor.existeGestor(id_gestor)) UIGestor.deleteGestor(id_gestor);
		}
		case "Volver atrás" -> App.mainMenu();
		}
		if (!accion.equals("Volver atrás")) switchGestor();
	}
}