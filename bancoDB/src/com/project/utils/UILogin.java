package com.project.utils;

import javax.swing.JOptionPane;

import com.project.models.Cliente;
import com.project.models.Gestor;
import com.project.utils.UILogin;

public class UILogin {
	public static Gestor comprobarUsuarioG() {
		String usuario = JOptionPane.showInputDialog("Inserte su usuario:");
		Gestor gestor = new Gestor(usuario);
		return gestor;
	}
	
	public static Gestor comprobarPasswordG() {
		String password = JOptionPane.showInputDialog("Inserte su contraseña:");
		Gestor gestor = new Gestor(password);
		return gestor;
	}
	
	public static Cliente comprobarUsuarioC() {
		String usuario = JOptionPane.showInputDialog("Inserte su usuario:");
		Cliente cliente = new Cliente(usuario);
		return cliente;
	}
	
	public static Cliente comprobarPasswordC() {
		String password = JOptionPane.showInputDialog("Inserte su contraseña:");
		Cliente cliente = new Cliente(password);
		return cliente;
	}
}