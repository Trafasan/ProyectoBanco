package com.sandra.bancoArrayList.menus;

import java.util.List;

import javax.swing.JOptionPane;

import com.sandra.bancoArrayList.App;
import com.sandra.bancoArrayList.entidades.Cliente;
import com.sandra.bancoArrayList.entidades.Gestor;
import com.sandra.bancoArrayList.entidades.Mensaje;
import com.sandra.bancoArrayList.entidades.Transferencia;
import com.sandra.bancoArrayList.utilidades.LoginUtils;

public class LoginMenu {
	
	public static void switchLogin(List<Gestor> gestores, List<Cliente> clientes, List<Mensaje> mensajes, List<Transferencia> transferencias) {
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
			int idUsuario = LoginUtils.guardarIdGestor(gestores);
			if(LoginUtils.comprobacionIdGestor(gestores, idUsuario)) {
				for (int i=MAX_INTENTOS; i>0; i--) {
					boolean coincidencia = LoginUtils.coincidePasswordGestor(gestores, idUsuario);
					if(coincidencia) {
						System.out.println("Bienvenid@ " + gestores.get(idUsuario).getNombre());
						break;
					}
					else System.err.println("Contraseña incorrecta. "+LoginUtils.mensajeError(i));
				}
			}
		}
		case "Cliente" -> {
			int idUsuario = LoginUtils.guardarIdCliente(clientes);
			if(LoginUtils.comprobacionIdCliente(clientes, idUsuario)) {
				for (int i=MAX_INTENTOS; i>0; i--) {
					boolean coincidencia = LoginUtils.coincidePasswordCliente(clientes, idUsuario);
					if(coincidencia) {
						System.out.println("Bienvenid@ " + clientes.get(idUsuario).getNombre());
						break;
					}
					else System.err.println("Contraseña incorrecta. "+LoginUtils.mensajeError(i));
				}
			}
		}
		case "Volver atrás" -> App.mainMenu(gestores, clientes, mensajes, transferencias);
		}
		if (!accion.equals("Volver atrás")) switchLogin(gestores, clientes, mensajes, transferencias);
	}
}
