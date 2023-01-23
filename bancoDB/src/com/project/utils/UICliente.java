package com.project.utils;

import javax.swing.JOptionPane;

import com.project.models.Cliente;
import com.project.models.Gestor;
import com.project.utils.UICliente;

public class UICliente {
	// 7. Inserción de un cliente

	// Comprobación previa de que existe el cliente con ese ID
	public static Gestor comprobarId_gestorInsertar() {
		int n = Integer.parseInt(JOptionPane.showInputDialog("Inserte el ID del gestor del nuevo cliente: "));
		Gestor gestor = new Gestor(n);
		return gestor;
	}

	public static Cliente insertarCliente(Gestor comprobacionId_gestor) {
		int id_gestor = comprobacionId_gestor.getId_gestor();
		String nombre = JOptionPane.showInputDialog("Inserte el nombre del nuevo cliente: ");
		String apellido = JOptionPane.showInputDialog("Inserte el apellido del nuevo cliente: ");
		String dni = JOptionPane.showInputDialog("Inserte el DNI del nuevo cliente: ");
		String usuario = JOptionPane.showInputDialog("Inserte el usuario del nuevo cliente: ");
		String password = JOptionPane.showInputDialog("Inserte la contraseña del nuevo cliente: ");
		String correo = JOptionPane.showInputDialog("Inserte el correo del nuevo cliente: ");
		double saldo = Double.parseDouble(JOptionPane.showInputDialog("Inserte el saldo del nuevo cliente: "));

		Cliente cliente = new Cliente(id_gestor, nombre, apellido, dni, usuario, password, correo, saldo);

		return cliente;

	}

	// 8. Obtención de un cliente
	public static Cliente obtencionCliente() {
		int n = Integer.parseInt(JOptionPane.showInputDialog("Inserte el ID del cliente a obtener: "));
		Cliente cliente = new Cliente(n);
		return cliente;
	}

	// 10. Actualización de un cliente (dato a dato)

	// Comprobación previa de que existe el cliente con ese ID
	public static Cliente comprobarCliente() {
		int n = Integer.parseInt(JOptionPane.showInputDialog("Proporciona la ID del cliente que quieres modificar:"));
		Cliente cliente = new Cliente(n);
		return cliente;
	}

	// Comprobación previa de que existe el gestor con ese ID
	public static Gestor comprobarId_gestorUpdate(Cliente comprobacionCliente) {
		int id = comprobacionCliente.getId_cliente();
		int n = Integer.parseInt(JOptionPane.showInputDialog(null, "Introduzca el ID del nuevo gestor:",
				"ACTUALIZACIÓN DEL CLIENTE " + id, 1));
		Gestor gestor = new Gestor(n);
		return gestor;
	}

	public static Cliente updateId_gestorCliente(Cliente comprobacionCliente, Gestor comprobacionId_gestor) {
		int id = comprobacionCliente.getId_cliente();
		int id_gestor = comprobacionId_gestor.getId_gestor();
		Cliente cliente = new Cliente(id, id_gestor);
		return cliente;
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