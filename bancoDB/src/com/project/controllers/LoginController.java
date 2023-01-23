package com.project.controllers;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.project.databases.DBLogin;
import com.project.functions.MainMenu;
import com.project.models.Cliente;
import com.project.models.Gestor;
import com.project.utils.UILogin;

public class LoginController {

	public static void switchLogin() {
		DBLogin dbLogin = new DBLogin();
		ImageIcon preocupado = new ImageIcon("src/images/preocupado.png");
		String nuevaOpcion;
		try {
			nuevaOpcion = JOptionPane.showInputDialog(null, "Seleccione su tipo de cuenta: ", "MENÚ LOGIN",
					JOptionPane.PLAIN_MESSAGE, null, new Object[] { "Gestor", "Cliente" }, null).toString();
		} catch (Exception e) {
			nuevaOpcion = "Volver atrás";
		}

		switch (nuevaOpcion) {
		case "Gestor":
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
			else {switchLogin();}
			break;
		case "Cliente":
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
			break;
		case "Volver atrás":
			MainMenu.opciones();
		}
	}
}