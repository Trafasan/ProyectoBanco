package com.sandra.bancoDB.utilidades;

import javax.swing.JOptionPane;

import com.sandra.bancoDB.entidades.Cliente;
import com.sandra.bancoDB.entidades.Gestor;
import com.sandra.bancoDB.utilidades.UILogin;

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