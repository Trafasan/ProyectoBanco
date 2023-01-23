package com.project.controllers;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.project.databases.DBRegistro;
import com.project.functions.MainMenu;
import com.project.models.Cliente;
import com.project.models.Gestor;
import com.project.utils.UIRegistro;

public class RegistroController {
	
	public static int guardarIdMax() {
		DBRegistro dbRegistro = new DBRegistro();
		ArrayList<Gestor> id_gestores = dbRegistro.obtenerId_gestor();
		int idMax = id_gestores.get(0).getId_gestor();
		for (int x=1; x<id_gestores.size(); x++) {
			if (idMax>id_gestores.get(x).getId_gestor()) {
				idMax = id_gestores.get(x).getId_gestor();
			}
		}
		return idMax;
	}

	public static void switchRegistro() {
		DBRegistro dbRegistro = new DBRegistro();
		ImageIcon preocupado = new ImageIcon("src/images/preocupado.png");
		String nuevaOpcion;
		try {
			nuevaOpcion = JOptionPane.showInputDialog(null, "Seleccione el tipo de cuenta que quiere crear: ", "MENÚ REGISTRO",
					JOptionPane.PLAIN_MESSAGE, null, new Object[] { "Gestor", "Cliente" }, null).toString();
		} catch (Exception e) {
			nuevaOpcion = "Volver atrás";
		}

		switch (nuevaOpcion) {
		case "Gestor":
			Gestor comprobarUsuarioG = UIRegistro.comprobarUsuarioG();
			boolean existeUsuarioG = dbRegistro.comprobarUsuarioG(comprobarUsuarioG);
			if (existeUsuarioG == false) {
				String usuario = comprobarUsuarioG.getActualizar();
				String password, comprobarPassword;
				do {
					password = UIRegistro.crearPassword();
					comprobarPassword = UIRegistro.comprobarPassword();
					if(comprobarPassword.equals(password)) {
						JOptionPane.showMessageDialog(null, "Ahora, inserte los datos restantes", "USUARIO CREADO CORRECTAMENTE", 1, null);
						dbRegistro.crearGestor(UIRegistro.crearGestor(usuario, password));
						MainMenu.opciones();
					} else {
						JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden", "ERROR", 0, preocupado);
					}
				}while (!comprobarPassword.equals(password));
			} else {switchRegistro();}
			break;
		case "Cliente":
			Cliente comprobarUsuarioC = UIRegistro.comprobarUsuarioC();
			boolean existeUsuarioC = dbRegistro.comprobarUsuarioC(comprobarUsuarioC);
			if (existeUsuarioC == false) {
				String usuario = comprobarUsuarioC.getActualizar();
				String password, comprobarPassword;
				do {
					password = UIRegistro.crearPassword();
					comprobarPassword = UIRegistro.comprobarPassword();
					dbRegistro.obtenerId_gestor();
					if(comprobarPassword.equals(password)) {
						int idMax = guardarIdMax();
						JOptionPane.showMessageDialog(null, "Ahora, inserte los datos restantes", "USUARIO CREADO CORRECTAMENTE", 1, null);
						Cliente nuevoCliente = UIRegistro.crearCliente(idMax, usuario, password);
						dbRegistro.crearCliente(nuevoCliente);
						dbRegistro.leerGestores();
						JOptionPane.showMessageDialog(null, "Se le ha asignado al gestor "+nuevoCliente.getId_gestor()+" automáticamente.\nPara cambiarlo, contacte con uno de nuestros gestores.", "ADVERTENCIA", 2, null);
						MainMenu.opciones();
					} else {
						JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden", "ERROR", 0, preocupado);
					}
				}while (!comprobarPassword.equals(password));
			} else {switchRegistro();}
			break;
		case "Volver atrás":
			MainMenu.opciones();
		}
	}
}