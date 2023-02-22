package com.sandra.bancoArrayList.utilidades;

import java.util.List;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.sandra.bancoArrayList.entidades.Cliente;
import com.sandra.bancoArrayList.entidades.Gestor;

public class ClienteUtils {
	private static Scanner sc;
	// Inserción de un cliente
	public static void addCliente(List<Gestor> gestores, List<Cliente> clientes) {
		sc = new Scanner (System.in);
		int id_cliente = clientes.size();
		clientes.add(new Cliente(id_cliente+1));
		System.out.print("Introduzca el nombre del nuevo cliente: ");
		clientes.get(id_cliente).setNombre(sc.nextLine());
		System.out.print("Introduzca el apellido del nuevo cliente: ");
		clientes.get(id_cliente).setApellido(sc.nextLine());
		System.out.print("Introduzca el DNI del nuevo cliente: ");
		clientes.get(id_cliente).setDni(sc.nextLine());
		System.out.print("Introduzca el usuario del nuevo cliente: ");
		clientes.get(id_cliente).setUsuario(sc.nextLine());
		System.out.print("Introduzca la contraseña del nuevo cliente: ");
		clientes.get(id_cliente).setPassword(sc.nextLine());
		System.out.print("Introduzca el correo del nuevo cliente: ");
		clientes.get(id_cliente).setCorreo(sc.nextLine());
		int id_gestor = GestorUtils.getIdGestorAleatorio(gestores);
		clientes.get(id_cliente).setId_gestor(id_gestor);
		clientes.get(id_cliente).setSaldo(0);
		System.out.println("Inserción realizada correctamente. Se le ha adjudicado un gestor aleatoriamente. Sus datos son los siguientes:");
		GestorUtils.getGestorAleatorio(gestores, id_gestor);
	}

	// Obtención de un cliente
	public static void getCliente(List<Cliente> clientes) {
		sc = new Scanner (System.in);
		System.out.print("Introduzca la ID del cliente que desea obtener: ");
		try {
			System.out.println(clientes.get(Integer.parseInt(sc.nextLine())-1));
		} catch (IndexOutOfBoundsException e) {
			System.err.println("No se ha encontrado al cliente con la ID introducida. Se le redigirá al Menú Cliente.");
		}
	}

	// Obtención de todos los clientes
	public static void getClientes(List<Cliente> clientes) {
		for (int i=0; i<clientes.size(); i++) System.out.println(clientes.get(i));
	}

	// Actualización de un cliente (dato a dato)

	// Comprobación previa de que existe el cliente con ese ID
	public static int comprobarIdCliente(List<Cliente> clientes) {
		sc = new Scanner (System.in);
		System.out.print("Proporciona la ID del cliente que quieres modificar: ");
		return Integer.parseInt(sc.nextLine())-1;
	}
	public static boolean existeCliente(List<Cliente> clientes, int id, String menu) {
		try {
			clientes.get(id);
			return true;
		} catch (IndexOutOfBoundsException e) {
			System.err.println("No se ha encontrado al cliente con la ID introducida. Se le redigirá al Menú "+menu+".");
			return false;
		}
	}

	public static void updateNombreCliente(List<Cliente> clientes, int id) {
		sc = new Scanner (System.in);
		System.out.print("Inserte el nuevo nombre del cliente " + (id+1) + ": ");
		clientes.get(id).setNombre(sc.nextLine());
	}

	public static void updateApellidoCliente(List<Cliente> clientes, int id) {
		sc = new Scanner (System.in);
		System.out .print("Inserte el nuevo apellido del cliente " + (id+1) + ": ");
		clientes.get(id).setApellido(sc.nextLine());
	}

	public static void updateDniCliente(List<Cliente> clientes, int id) {
		sc = new Scanner (System.in);
		System.out.print("Inserte el DNI del cliente " + (id+1) + ": ");
		clientes.get(id).setDni(sc.nextLine());
	}

	public static void updateUsuarioCliente(List<Cliente> clientes, int id) {
		sc = new Scanner (System.in);
		System.out.print("Inserte el usuario del cliente " + (id+1) + ": ");
		clientes.get(id).setUsuario(sc.nextLine());
	}

	public static void updatePasswordCliente(List<Cliente> clientes, int id) {
		sc = new Scanner (System.in);
		System.out.print("Inserte la contraseña del cliente " + (id+1) + ": ");
		clientes.get(id).setPassword(sc.nextLine());
	}

	public static void updateCorreoCliente(List<Cliente> clientes, int id) {
		sc = new Scanner (System.in);
		System.out.print("Inserte el correo del cliente " + (id+1) + ": ");
		clientes.get(id).setCorreo(sc.nextLine());
	}
	
	public static void updateIdGestorCliente(List<Gestor> gestores, List<Cliente> clientes, int id_cliente) {
		sc = new Scanner (System.in);
		System.out.print("Introduzca la nueva ID del gestor del cliente " + (id_cliente+1) + ": ");
		try {
			int id_gestor = Integer.parseInt(sc.nextLine());
			gestores.get(id_gestor);
			clientes.get(id_cliente).setId_gestor(id_gestor);
			System.out.println("Actualización realizada correctamente");
		} catch (IndexOutOfBoundsException e) {
			System.err.println("No se ha encontrado al gestor con el ID introducido.");
			System.err.println("No se pudo actualizar la ID del gestor del cliente "+(id_cliente+1)+".");
		}
	}

	public static void updateSaldoCliente(List<Cliente> clientes, int id) {
		sc = new Scanner (System.in);
		System.out.print("Inserte el saldo del cliente " + (id+1) + ": ");
		clientes.get(id).setSaldo(Double.parseDouble(sc.nextLine()));
	}

	// Eliminación de un cliente con confirmación de la acción
	public static void deleteCliente(List<Cliente> clientes) {
		System.out.print("Introduzca la ID del cliente a eliminar: ");
		int id = Integer.parseInt(sc.nextLine());
		int confirmacion;
		try {
			confirmacion = JOptionPane.showConfirmDialog(null,
					"¿Seguro que quieres eliminar al cliente " + id + "?",
					"MENSAJE DE CONFIRMACIÓN", 0, 3, new ImageIcon("src/main/java/com/sandra/bancoArrayList/images/eliminar.png"));
		} catch (Exception e) {
			confirmacion = 1;
		}
		switch (confirmacion) {
		case 0 -> {
			try {
				clientes.remove(--id);
				for (int i = id; i < clientes.size(); i++) clientes.get(i).setId_cliente(i+1);
				System.out.println("Eliminación realizada correctamente.");
			} catch (IndexOutOfBoundsException e) {
				System.err.println("No se ha encontrado al cliente con la ID introducida. Se le redigirá al Menú Cliente.");
			}
		}
		case 1 -> JOptionPane.showMessageDialog(null, "No se ha eliminado el cliente " + id);
		}
	}

}