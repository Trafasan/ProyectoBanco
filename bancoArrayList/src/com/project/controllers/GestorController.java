package com.project.controllers;

import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

import com.project.functions.MainMenu;
import com.project.models.Cliente;
import com.project.models.Gestor;
import com.project.models.Mensaje;
import com.project.models.Transferencia;
import com.project.utils.UIGestor;

public class GestorController {
	
	public static void switchGestor(List<Gestor> gestores, List<Cliente> clientes, List<Mensaje> mensajes, List<Transferencia> transferencias, Scanner sc) {
		String accion;
		try {
			accion = JOptionPane
					.showInputDialog(null, "Inserte una opción: ", "MENÚ GESTOR", JOptionPane.PLAIN_MESSAGE, null,
							new Object[] { "Inserción de un gestor", "Inserción de varios gestores",
									"Obtención de un gestor", "Obtención de todos los gestores",
									"Actualización de un gestor", "Eliminación de un gestor" }, null).toString();
		} catch (Exception e) {
			accion = "Volver atrás";
		}
		
		switch (accion) {
			case "Inserción de un gestor" -> UIGestor.addGestor(gestores, sc);
			case "Inserción de varios gestores" -> UIGestor.addGestores(gestores, sc);
			case "Obtención de un gestor" -> {
				if (gestores.size() == 0) System.err.println("No existe ningún gestor. Se le redigirá al Menú Gestor.");
				else UIGestor.getGestor(gestores, sc);
			}
			case "Obtención de todos los gestores" -> {
				if (gestores.size() == 0) System.err.println("No existe ningún gestor. Se le redigirá al Menú Gestor.");
				else UIGestor.getGestores(gestores);
			}
			case "Actualización de un gestor" -> {
				if (gestores.size() == 0) System.err.println("No existe ningún gestor. Se le redigirá al Menú Gestor.");
				int id = UIGestor.comprobarIdGestor(gestores, sc);
				if (UIGestor.existeGestor(gestores, id, "Gestor")) GestorUpdateController.switchUpdateGestor(gestores, sc, id);
			}
			case "Eliminación de un gestor" -> {
				if (gestores.size() == 0) System.err.println("No existe ningún gestor. Se le redigirá al Menú Gestor.");
				else UIGestor.deleteGestor(gestores, sc);
			}
			case "Volver atrás" -> MainMenu.opciones(gestores, clientes, mensajes, transferencias, sc);
		}
		if (!accion.equals("Volver atrás")) switchGestor(gestores, clientes, mensajes, transferencias, sc);
	}
}
