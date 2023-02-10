package com.project.controllers;

import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

import com.project.functions.MainMenu;
import com.project.models.Cliente;
import com.project.models.Gestor;
import com.project.models.Mensaje;
import com.project.models.Transferencia;
import com.project.utils.UIRegistro;

public class RegistroController {
	
	public static void switchRegistro(List<Gestor> gestores, List<Cliente> clientes, List<Mensaje> mensajes, List<Transferencia> transferencias, Scanner sc) {
		String accion;
		try {
			accion = JOptionPane.showInputDialog(null, "Seleccione el tipo de cuenta que quiere crear: ", "MENÚ REGISTRO",
					JOptionPane.PLAIN_MESSAGE, null, new Object[] { "Gestor", "Cliente" }, null).toString();
		} catch (Exception e) {
			accion = "Volver atrás";
		}

		switch (accion) {
		case "Gestor" -> {
			String usuario;
			boolean usuarioYaExistente;
			do {
				usuario = UIRegistro.nuevoUsuario(sc);
				usuarioYaExistente = UIRegistro.usuarioYaExistenteGestor(gestores, usuario);
			} while (usuarioYaExistente);
			String password;
			boolean passwordsIguales;
			do {
				password = UIRegistro.nuevoPassword("una", sc);
				String confirmPassword = UIRegistro.nuevoPassword("de nuevo la", sc);
				passwordsIguales = UIRegistro.passwordsIguales(password, confirmPassword);
			} while (!passwordsIguales);
			UIRegistro.addGestor(gestores, sc, usuario, password);
		}
		case "Cliente" -> {
			String usuario;
			boolean usuarioYaExistente;
			do {
				usuario = UIRegistro.nuevoUsuario(sc);
				usuarioYaExistente = UIRegistro.usuarioYaExistenteCliente(clientes, usuario);
			} while (usuarioYaExistente);
			String password;
			boolean passwordsIguales;
			do {
				password = UIRegistro.nuevoPassword("una", sc);
				String confirmPassword = UIRegistro.nuevoPassword("de nuevo la", sc);
				passwordsIguales = UIRegistro.passwordsIguales(password, confirmPassword);
			} while (!passwordsIguales);
			UIRegistro.addCliente(gestores, clientes, sc, usuario, password);
		}
		case "Volver atrás" -> MainMenu.opciones(gestores, clientes, mensajes, transferencias, sc);
		}
		if (!accion.equals("Volver atrás")) switchRegistro(gestores, clientes, mensajes, transferencias, sc);
	}
}
