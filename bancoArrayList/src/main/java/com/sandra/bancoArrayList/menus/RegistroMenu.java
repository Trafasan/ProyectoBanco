package com.sandra.bancoArrayList.menus;

import java.util.List;

import javax.swing.JOptionPane;

import com.sandra.bancoArrayList.App;
import com.sandra.bancoArrayList.entidades.Cliente;
import com.sandra.bancoArrayList.entidades.Gestor;
import com.sandra.bancoArrayList.entidades.Mensaje;
import com.sandra.bancoArrayList.entidades.Transferencia;
import com.sandra.bancoArrayList.utilidades.RegistroUtils;

public class RegistroMenu {
	
	public static void switchRegistro(List<Gestor> gestores, List<Cliente> clientes, List<Mensaje> mensajes, List<Transferencia> transferencias) {
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
				usuario = RegistroUtils.nuevoUsuario();
				usuarioYaExistente = RegistroUtils.usuarioYaExistenteGestor(gestores, usuario);
			} while (usuarioYaExistente);
			String password;
			boolean passwordsIguales;
			do {
				password = RegistroUtils.nuevoPassword("una");
				String confirmPassword = RegistroUtils.nuevoPassword("de nuevo la");
				passwordsIguales = RegistroUtils.passwordsIguales(password, confirmPassword);
			} while (!passwordsIguales);
			RegistroUtils.addGestor(gestores, usuario, password);
		}
		case "Cliente" -> {
			String usuario;
			boolean usuarioYaExistente;
			do {
				usuario = RegistroUtils.nuevoUsuario();
				usuarioYaExistente = RegistroUtils.usuarioYaExistenteCliente(clientes, usuario);
			} while (usuarioYaExistente);
			String password;
			boolean passwordsIguales;
			do {
				password = RegistroUtils.nuevoPassword("una");
				String confirmPassword = RegistroUtils.nuevoPassword("de nuevo la");
				passwordsIguales = RegistroUtils.passwordsIguales(password, confirmPassword);
			} while (!passwordsIguales);
			RegistroUtils.addCliente(gestores, clientes, usuario, password);
		}
		case "Volver atrás" -> App.mainMenu(gestores, clientes, mensajes, transferencias);
		}
		if (!accion.equals("Volver atrás")) switchRegistro(gestores, clientes, mensajes, transferencias);
	}
}
