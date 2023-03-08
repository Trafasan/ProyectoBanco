package com.sandra.bancoDB.menus;

import java.util.Collections;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.sandra.bancoDB.App;
import com.sandra.bancoDB.databases.DBCliente;
import com.sandra.bancoDB.databases.DBRegistro;
import com.sandra.bancoDB.entidades.Cliente;
import com.sandra.bancoDB.entidades.Gestor;
import com.sandra.bancoDB.utilidades.UIRegistro;

public class RegistroMenu {

	public static void switchRegistro() {
		DBRegistro dbRegistro = new DBRegistro();
		ImageIcon preocupado = new ImageIcon("src/main/java/com/sandra/bancoDB/images/preocupado.png");
		String accion;
		try {
			accion = JOptionPane.showInputDialog(null, "Seleccione el tipo de cuenta que quiere crear: ", "MENÚ REGISTRO",
					JOptionPane.PLAIN_MESSAGE, null, new Object[] { "Gestor", "Cliente" }, null).toString();
		} catch (Exception e) {
			accion = "Volver atrás";
		}

		switch (accion) {
		case "Gestor" -> {
			Gestor comprobarUsuarioG = UIRegistro.comprobarUsuarioG();
			boolean existeUsuarioG = dbRegistro.comprobarUsuarioG(comprobarUsuarioG);
			if (!existeUsuarioG) {
				String usuario = comprobarUsuarioG.getUpdateDato();
				String password, comprobarPassword;
				do {
					password = UIRegistro.crearPassword();
					comprobarPassword = UIRegistro.comprobarPassword();
					if(comprobarPassword.equals(password)) {
						JOptionPane.showMessageDialog(null, "Ahora, inserte los datos restantes", "USUARIO CREADO CORRECTAMENTE", 1, null);
						dbRegistro.crearGestor(UIRegistro.crearGestor(usuario, password));
						App.mainMenu();
					} else {
						JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden", "ERROR", 0, preocupado);
					}
				}while (!comprobarPassword.equals(password));
			} else switchRegistro();
		}
		case "Cliente" -> {
			Cliente comprobarUsuarioC = UIRegistro.comprobarUsuarioC();
			boolean existeUsuarioC = dbRegistro.comprobarUsuarioC(comprobarUsuarioC);
			if (!existeUsuarioC) {
				String usuario = comprobarUsuarioC.getUpdateDato();
				String password, comprobarPassword;
				do {
					password = UIRegistro.crearPassword();
					comprobarPassword = UIRegistro.comprobarPassword();
					if(comprobarPassword.equals(password)) {
						int idMax = Collections.max(DBCliente.getId_gestores());
						JOptionPane.showMessageDialog(null, "Ahora, inserte los datos restantes", "USUARIO CREADO CORRECTAMENTE", 1, null);
						Cliente nuevoCliente = UIRegistro.crearCliente(idMax, usuario, password);
						dbRegistro.crearCliente(nuevoCliente);
						dbRegistro.leerGestores();
						JOptionPane.showMessageDialog(null, "Se le ha asignado al gestor "+nuevoCliente.getId_gestor()+" automáticamente.\nPara cambiarlo, contacte con uno de nuestros gestores.", "ADVERTENCIA", 2, null);
						App.mainMenu();
					} else {
						JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden", "ERROR", 0, preocupado);
					}
				} while (!comprobarPassword.equals(password));
			} else switchRegistro();
		}
		case "Volver atrás" ->  App.mainMenu();
		}
	}
}