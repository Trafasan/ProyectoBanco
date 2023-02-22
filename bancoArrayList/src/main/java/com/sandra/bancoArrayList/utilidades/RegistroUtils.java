package com.sandra.bancoArrayList.utilidades;

import java.util.List;
import java.util.Scanner;

import com.sandra.bancoArrayList.entidades.Cliente;
import com.sandra.bancoArrayList.entidades.Gestor;

public class RegistroUtils {
	private static Scanner sc;
	public static String nuevoUsuario() {
		sc = new Scanner (System.in);
		System.out.print("Introduzca su usuario: ");
		return sc.nextLine();
	}
	
	public static boolean usuarioYaExistenteGestor(List<Gestor>gestores, String usuario) {
		for (int i=0; i<gestores.size(); i++) {
			if (usuario.equals(gestores.get(i).getUsuario())) {
				System.err.println("El nombre de usuario introducido ya existe.");
				return true;
			}
		}
		return false;
	}
	
	public static String nuevoPassword(String texto) {
		sc = new Scanner (System.in);
		String password;
		boolean passwordIncorrecto;
		do {
			System.out.print("Inserte "+texto+" contraseña (número de 4 dígitos): ");
			password = sc.nextLine();
			passwordIncorrecto = passwordIncorrecto(password);
			if (passwordIncorrecto) System.err.println("La contraseña debe ser un número de 4 dígitos");
		} while (passwordIncorrecto);
		return password;
	}
	
	private static boolean passwordIncorrecto(String password) {
		final int LENGTH_PASSWORD = 4;
		try {
			Integer.parseInt(password); // Salta el error NumberFormatException si la contraseña no se puede convertir a entero
			if (password.length()!=LENGTH_PASSWORD) return true;
			else return false;
		} catch (NumberFormatException e) {
			return true;
		}
	}
	
	public static boolean passwordsIguales(String password, String confirmPassword) {
		if (password.equals(confirmPassword)) return true;
		else {
			System.err.println("Las contraseñas no coinciden");
			return false;
		}
	}

	public static void addGestor(List<Gestor> gestores, String usuario, String password) {
		sc = new Scanner (System.in);
		System.out.println("Ahora introduzca los datos restantes:");
		int id = gestores.size();
		gestores.add(new Gestor(id+1));
		System.out.print("Nombre: ");
		String nombre = sc.nextLine();
		gestores.get(id).setNombre(nombre);
		System.out.print("Apellido(s): ");
		gestores.get(id).setApellido(sc.nextLine());
		System.out.print("DNI: ");
		gestores.get(id).setDni(sc.nextLine());
		gestores.get(id).setUsuario(usuario);
		gestores.get(id).setPassword(password);
		System.out.print("Correo: ");
		gestores.get(id).setCorreo(sc.nextLine());
		System.out.println("Bienvenid@ " + nombre);
	}
	
	public static boolean usuarioYaExistenteCliente(List<Cliente>clientes, String usuario) {
		for (int i=0; i<clientes.size(); i++) {
			if (usuario.equals(clientes.get(i).getUsuario())) {
				System.err.println("El nombre de usuario introducido ya existe.");
				return true;
			}
		}
		return false;
	}
	
	public static void addCliente(List<Gestor> gestores, List<Cliente> clientes, String usuario, String password) {
		sc = new Scanner (System.in);
		System.out.println("Ahora introduzca los datos restantes:");
		int id_cliente = clientes.size();
		clientes.add(new Cliente(id_cliente+1));
		System.out.print("Nombre: ");
		String nombre = sc.nextLine();
		clientes.get(id_cliente).setNombre(nombre);
		System.out.print("Apellido(s): ");
		clientes.get(id_cliente).setApellido(sc.nextLine());
		System.out.print("DNI: ");
		clientes.get(id_cliente).setDni(sc.nextLine());
		clientes.get(id_cliente).setUsuario(usuario);
		clientes.get(id_cliente).setPassword(password);
		System.out.print("Correo: ");
		clientes.get(id_cliente).setCorreo(sc.nextLine());
		int id_gestor = GestorUtils.getIdGestorAleatorio(gestores);
		clientes.get(id_cliente).setId_gestor(id_gestor);
		clientes.get(id_cliente).setSaldo(0);
		System.out.println("Bienvenid@ " + nombre);
		System.out.println("Se le ha adjudicado un gestor aleatoriamente. Sus datos son los siguientes:");
		GestorUtils.getGestorAleatorio(gestores, id_gestor);
	}

	
}