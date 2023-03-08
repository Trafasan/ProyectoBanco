package com.sandra.bancoDB.menus;

import javax.swing.JOptionPane;

import com.sandra.bancoDB.databases.DBGestor;
import com.sandra.bancoDB.entidades.Gestor;
import com.sandra.bancoDB.utilidades.UIGestor;

public class GestorMenu {

	public static void switchGestor() {
		DBGestor dbGestor = new DBGestor();
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
			dbGestor.addGestor(UIGestor.addGestor());
			JOptionPane.showMessageDialog(null, "Inserción realizada correctamente");
		}
		case "Inserción de varios gestores" -> {
			int n = Integer.parseInt(JOptionPane.showInputDialog("Inserte el número de gestores a añadir:"));
			for (int i=0; i<n; i++) dbGestor.addGestor(UIGestor.addGestores());
			JOptionPane.showMessageDialog(null, "Inserciones realizada correctamente");
		}
		case "Obtención de un gestor" -> dbGestor.getGestor(UIGestor.getGestor("a obtener"));
		case "Obtención de todos los gestores" -> dbGestor.getGestores();
		case "Actualización de un gestor" -> {
			Gestor updateGestor = UIGestor.getGestor("que quieres modificar");
			if (dbGestor.existeGestor(updateGestor) && updateGestor != null) GestorUpdateMenu.switchUpdateGestor(updateGestor);
		}
		case "Eliminación de un gestor" -> {
			Gestor updateGestor = UIGestor.getGestor("que quieres eliminar");
			if (dbGestor.existeGestor(updateGestor)) UIGestor.deleteGestor(dbGestor, updateGestor);
		}
		case "Volver atrás" -> MainMenu.opciones();
		}
		if (!accion.equals("Volver atrás")) switchGestor();
	}
}