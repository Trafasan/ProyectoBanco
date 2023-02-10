package com.project.controllers;

import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

import com.project.functions.MainMenu;
import com.project.models.Cliente;
import com.project.models.Gestor;
import com.project.models.Mensaje;
import com.project.models.Transferencia;
import com.project.utils.UILogin;

public class LoginController {
	
	public static void switchLogin(List<Gestor> gestores, List<Cliente> clientes, List<Mensaje> mensajes, List<Transferencia> transferencias, Scanner sc) {
		final int MAX_INTENTOS = 3;
		String accion;
		try {
			accion = JOptionPane.showInputDialog(null, "Seleccione su tipo de cuenta: ", "MENÚ LOGIN",
					JOptionPane.PLAIN_MESSAGE, null, new Object[] { "Gestor", "Cliente" }, null).toString();
		} catch (Exception e) {
			accion = "Volver atrás";
		}
		switch (accion) {
		case "Gestor" -> {
			int idUsuario = UILogin.guardarIdGestor(gestores, sc);
			if(UILogin.comprobacionIdGestor(gestores, sc, idUsuario)) {
				for (int i=MAX_INTENTOS; i>0; i--) {
					boolean coincidencia = UILogin.coincidePasswordGestor(gestores, sc, idUsuario);
					if(coincidencia) {
						System.out.println("Bienvenid@ " + gestores.get(idUsuario).getNombre());
						break;
					}
					else System.err.println("Contraseña incorrecta. "+UILogin.mensajeError(i));
				}
			}
		}
		case "Cliente" -> {
			int idUsuario = UILogin.guardarIdCliente(clientes, sc);
			if(UILogin.comprobacionIdCliente(clientes, sc, idUsuario)) {
				for (int i=MAX_INTENTOS; i>0; i--) {
					boolean coincidencia = UILogin.coincidePasswordCliente(clientes, sc, idUsuario);
					if(coincidencia) {
						System.out.println("Bienvenid@ " + clientes.get(idUsuario).getNombre());
						break;
					}
					else System.err.println("Contraseña incorrecta. "+UILogin.mensajeError(i));
				}
			}
		}
		case "Volver atrás" -> MainMenu.opciones(gestores, clientes, mensajes, transferencias, sc);
		}
		if (!accion.equals("Volver atrás")) switchLogin(gestores, clientes, mensajes, transferencias, sc);
	}
}
