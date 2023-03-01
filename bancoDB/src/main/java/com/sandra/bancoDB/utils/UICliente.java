package com.sandra.bancoDB.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javax.swing.JOptionPane;

import com.sandra.bancoDB.models.Cliente;
import com.sandra.bancoDB.utils.UICliente;

public class UICliente {
	// Inserción de un cliente

	// Comprobación previa de que existe el cliente con ese ID (tres métodos privados)
	
	private static int idMaxGestor(ArrayList<Integer> id_gestores) {
		id_gestores.sort(Collections.reverseOrder());
		return id_gestores.get(0);
	}
	private static int getIdGestorAleatorio(ArrayList<Integer> id_gestores) {
		return new Random().nextInt(idMaxGestor(id_gestores));
	}
	private static boolean existeIdGestor(ArrayList<Integer> id_gestores, int idGestorAleatorio) {
		for (int id:id_gestores) if (id == idGestorAleatorio) return true;
		return false;
	}
	
	public static Cliente addCliente(ArrayList<Integer> id_gestores) {
		int id_gestor;
		boolean existeGestor;
		do {
			id_gestor = getIdGestorAleatorio(id_gestores);
			existeGestor = existeIdGestor(id_gestores, id_gestor);
		} while (!existeGestor);
		String nombre = JOptionPane.showInputDialog("Inserte el nombre del nuevo cliente: ");
		String apellido = JOptionPane.showInputDialog("Inserte el apellido del nuevo cliente: ");
		String dni = JOptionPane.showInputDialog("Inserte el DNI del nuevo cliente: ");
		String usuario = JOptionPane.showInputDialog("Inserte el usuario del nuevo cliente: ");
		String password = UIGestor.nuevoPassword("Introduzca la contraseña del nuevo cliente", "Input", 3);
		String correo = JOptionPane.showInputDialog("Inserte el correo del nuevo cliente: ");
		Cliente cliente = new Cliente(id_gestor, nombre, apellido, dni, usuario, password, correo, 0);
		return cliente;
	}

	// Obtención de un cliente
	public static Cliente getCliente(String texto) {
		try {
			return new Cliente(Integer.parseInt(JOptionPane.showInputDialog("Inserte el ID del cliente "+texto+": ")));
		} catch (NumberFormatException e) {
			return new Cliente(null);
		}
	}

	// Actualización de un cliente (dato a dato)

	// Comprobación previa de que existe el cliente con ese ID (solo para eliminar)
	public static Cliente comprobarCliente() {
		int n = Integer.parseInt(JOptionPane.showInputDialog("Proporciona la ID del cliente que quieres modificar:"));
		Cliente cliente = new Cliente(n);
		return cliente;
	}

	// Comprobación previa de que existe el gestor con ese ID
	public static int setId_gestorUpdate(Cliente updateCliente) {
		int id = updateCliente.getId_cliente();
		try {
			return Integer.parseInt(JOptionPane.showInputDialog(null, "Introduzca el ID del nuevo gestor:",
					"ACTUALIZACIÓN DEL CLIENTE " + id, 1));
		} catch (NumberFormatException e) {
			return 0;
		}
	}
	public static boolean existeIdGestorUpdate(Cliente updateCliente, ArrayList<Integer> id_gestores, int id_gestor) {
		for (int id:id_gestores) if (id == id_gestor) return true;
		return false;
	}

	public static Cliente updateId_gestorCliente(Cliente updateCliente, int id_gestor) {
		return new Cliente(updateCliente.getId_cliente(), id_gestor);
	}
	
	public static Cliente updateCliente(Cliente updateCliente, String texto, int id) {
		return new Cliente(id, JOptionPane.showInputDialog(null, "Introduzca "+texto+":",
				"ACTUALIZACIÓN DEL CLIENTE "+id, 1));
	}

	public static Cliente updateNombreCliente(Cliente comprobacionCliente) {
		int id = comprobacionCliente.getId_cliente();
		String nombre = JOptionPane.showInputDialog(null, "Introduzca el nuevo nombre:",
				"ACTUALIZACIÓN DEL CLIENTE " + id, 1);
		Cliente cliente = new Cliente(id, nombre);
		return cliente;
	}

	public static Cliente updateApellidoCliente(Cliente comprobacionCliente) {
		int id = comprobacionCliente.getId_cliente();
		String apellido = JOptionPane.showInputDialog(null, "Introduzca el(los) nuevo(s) apellido(s):",
				"ACTUALIZACIÓN DEL CLIENTE " + id, 1);
		Cliente cliente = new Cliente(id, apellido);
		return cliente;
	}

	public static Cliente updateDniCliente(Cliente comprobacionCliente) {
		int id = comprobacionCliente.getId_cliente();
		String dni = JOptionPane.showInputDialog(null, "Introduzca el nuevo DNI", "ACTUALIZACIÓN DEL CLIENTE " + id, 1);
		Cliente cliente = new Cliente(id, dni);
		return cliente;
	}

	public static Cliente updateUsuarioCliente(Cliente comprobacionCliente) {
		int id = comprobacionCliente.getId_cliente();
		String usuario = JOptionPane.showInputDialog(null, "Introduzca el nuevo usuario",
				"ACTUALIZACIÓN DEL CLIENTE " + id, 1);
		Cliente cliente = new Cliente(id, usuario);
		return cliente;
	}

	public static Cliente updatePasswordCliente(Cliente comprobacionCliente) {
		int id = comprobacionCliente.getId_cliente();
		String password = JOptionPane.showInputDialog(null, "Introduzca la nueva contraseña",
				"ACTUALIZACIÓN DEL CLIENTE " + id, 1);
		Cliente cliente = new Cliente(id, password);
		return cliente;
	}

	public static Cliente updateCorreoCliente(Cliente comprobacionCliente) {
		int id = comprobacionCliente.getId_cliente();
		String correo = JOptionPane.showInputDialog(null, "Introduzca el nuevo correo",
				"ACTUALIZACIÓN DEL CLIENTE " + id, 1);
		Cliente cliente = new Cliente(id, correo);
		return cliente;
	}

	public static Cliente updateSaldoCliente(Cliente comprobacionCliente) {
		int id = comprobacionCliente.getId_cliente();
		double saldo = Double.parseDouble(
				JOptionPane.showInputDialog(null, "Introduzca el nuevo saldo", "ACTUALIZACIÓN DEL CLIENTE " + id, 1));
		Cliente cliente = new Cliente(id, saldo);
		return cliente;
	}
}