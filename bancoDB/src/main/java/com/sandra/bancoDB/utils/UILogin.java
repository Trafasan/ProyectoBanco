package com.sandra.bancoDB.utils;

import javax.swing.JOptionPane;

import com.sandra.bancoDB.models.Cliente;
import com.sandra.bancoDB.models.Gestor;
import com.sandra.bancoDB.utils.UILogin;

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