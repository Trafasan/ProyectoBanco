package com.sandra.bancoDB.controllers;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.sandra.bancoDB.databases.DBLogin;
import com.sandra.bancoDB.functions.MainMenu;
import com.sandra.bancoDB.models.Cliente;
import com.sandra.bancoDB.models.Gestor;
import com.sandra.bancoDB.utils.UILogin;

public class LoginController {

	public static void switchLogin() {
		DBLogin dbLogin = new DBLogin();
		ImageIcon preocupado = new ImageIcon("src/images/preocupado.png");
		String accion;
		try {
			accion = JOptionPane.showInputDialog(null, "Seleccione su tipo de cuenta: ", "MENÚ LOGIN",
					JOptionPane.PLAIN_MESSAGE, null, new Object[] { "Gestor", "Cliente" }, null).toString();
		} catch (Exception e) {
			accion = "Volver atrás";
		}

		switch (accion) {
		case "Gestor" -> {
			Gestor comprobarUsuarioG = UILogin.comprobarUsuarioG();
			boolean existeUsuarioG = dbLogin.comprobarUsuarioG(comprobarUsuarioG);
			if (existeUsuarioG == true) {
				String usuario = comprobarUsuarioG.getActualizar();
				String password = dbLogin.obtenerPasswordG(usuario);
				
				Gestor comprobarPassword = UILogin.comprobarPasswordG();
				if(comprobarPassword.getActualizar().equals(password)) {
					String nombre = dbLogin.obtenerNombreG(usuario);
					String apellido= dbLogin.obtenerApellidoG(usuario);
					JOptionPane.showMessageDialog(null, "Bienvenid@, "+nombre+" "+apellido);
					MainMenu.opciones();
				} else {
					JOptionPane.showMessageDialog(null, "Contraseña incorrecta", "ERROR", 0, preocupado);
					switchLogin();
				}
			}
			else switchLogin();
		}
		case "Cliente" -> {
			Cliente comprobarUsuarioC = UILogin.comprobarUsuarioC();
			boolean existeUsuarioC = dbLogin.comprobarUsuarioC(comprobarUsuarioC);
			if (existeUsuarioC == true) {
				String usuario = comprobarUsuarioC.getActualizar();
				String password = dbLogin.obtenerPasswordC(usuario);
				
				Cliente comprobarPassword = UILogin.comprobarPasswordC();
				if(comprobarPassword.getActualizar().equals(password)) {
					String nombre = dbLogin.obtenerNombreC(usuario);
					String apellido= dbLogin.obtenerApellidoC(usuario);
					JOptionPane.showMessageDialog(null, "Bienvenid@, "+nombre+" "+apellido);
				} else {
					JOptionPane.showMessageDialog(null, "Contraseña incorrecta", "ERROR", 0, preocupado);
				}
			}
			MainMenu.opciones();
		}
		case "Volver atrás" -> MainMenu.opciones();
		}
	}
}