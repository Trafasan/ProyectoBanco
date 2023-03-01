package com.sandra.bancoDB.utils;

import javax.swing.JOptionPane;

import com.sandra.bancoDB.models.Cliente;
import com.sandra.bancoDB.models.Gestor;
import com.sandra.bancoDB.utils.UIRegistro;

public class UIRegistro {
	public static Gestor comprobarUsuarioG() {
		String usuario = JOptionPane.showInputDialog("Inserte el nuevo usuario:");
		Gestor gestor = new Gestor(usuario);
		return gestor;
	}
	
	public static Cliente comprobarUsuarioC() {
		String usuario = JOptionPane.showInputDialog("Inserte el nuevo usuario:");
		Cliente cliente = new Cliente(usuario);
		return cliente;
	}
	
	public static String crearPassword() {
		String password = JOptionPane.showInputDialog("Inserte la nueva contraseña:");
		return password;
	}
	
	public static String comprobarPassword() {
		String comprobarPassword = JOptionPane.showInputDialog("Inserte de nuevo la contraseña:");
		return comprobarPassword;
	}
	
	public static Gestor crearGestor(String usuario, String password) {

		String nombre = JOptionPane.showInputDialog("Inserte su nombre: ");
		String apellido = JOptionPane.showInputDialog("Inserte su(s) apellido(s): ");
		String dni = JOptionPane.showInputDialog("Inserte su DNI: ");
		String correo = JOptionPane.showInputDialog("Inserte su correo: ");

		Gestor gestor = new Gestor(nombre, apellido, dni, usuario, password, correo);

		return gestor;

	}
	
	public static Cliente crearCliente(int idMax, String usuario, String password) {
		idMax++;
		int id_gestor = (int) (Math.random() * (idMax - 1)) + 1;
		String nombre = JOptionPane.showInputDialog("Inserte su nombre: ");
		String apellido = JOptionPane.showInputDialog("Inserte su apellido: ");
		String dni = JOptionPane.showInputDialog("Inserte su DNI: ");
		String correo = JOptionPane.showInputDialog("Inserte su correo: ");
		double saldo = 0;

		Cliente cliente = new Cliente(id_gestor, nombre, apellido, dni, usuario, password, correo, saldo);

		return cliente;
	}
}