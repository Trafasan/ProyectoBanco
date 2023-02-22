package com.sandra.bancoArrayList.menus;

import java.util.List;

import javax.swing.JOptionPane;

import com.sandra.bancoArrayList.App;
import com.sandra.bancoArrayList.entidades.Cliente;
import com.sandra.bancoArrayList.entidades.Gestor;
import com.sandra.bancoArrayList.entidades.Mensaje;
import com.sandra.bancoArrayList.entidades.Transferencia;
import com.sandra.bancoArrayList.utilidades.GestorUtils;

public class GestorMenu {
	
	public static void switchGestor(List<Gestor> gestores, List<Cliente> clientes, List<Mensaje> mensajes, List<Transferencia> transferencias) {
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
			case "Inserción de un gestor" -> GestorUtils.addGestor(gestores);
			case "Inserción de varios gestores" -> GestorUtils.addGestores(gestores);
			case "Obtención de un gestor" -> {
				if (gestores.size() == 0) System.err.println("No existe ningún gestor. Se le redigirá al Menú Gestor.");
				else GestorUtils.getGestor(gestores);
			}
			case "Obtención de todos los gestores" -> {
				if (gestores.size() == 0) System.err.println("No existe ningún gestor. Se le redigirá al Menú Gestor.");
				else GestorUtils.getGestores(gestores);
			}
			case "Actualización de un gestor" -> {
				if (gestores.size() == 0) System.err.println("No existe ningún gestor. Se le redigirá al Menú Gestor.");
				int id = GestorUtils.comprobarIdGestor(gestores);
				if (GestorUtils.existeGestor(gestores, id, "Gestor")) GestorUpdateMenu.switchUpdateGestor(gestores, id);
			}
			case "Eliminación de un gestor" -> {
				if (gestores.size() == 0) System.err.println("No existe ningún gestor. Se le redigirá al Menú Gestor.");
				else GestorUtils.deleteGestor(gestores);
			}
			case "Volver atrás" -> App.mainMenu(gestores, clientes, mensajes, transferencias);
		}
		if (!accion.equals("Volver atrás")) switchGestor(gestores, clientes, mensajes, transferencias);
	}
}
