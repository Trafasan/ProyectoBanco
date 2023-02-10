package com.project.utils;

import java.util.List;
import java.util.Scanner;

import com.project.models.Cliente;
import com.project.models.Gestor;

public class UILogin {
	/**
	 * Guarda la ID del gestor cuyo usuario sea el introducido o devuelve un ID erróneo en caso contrario.
	 * @param gestores Lista de gestores que se uiliza para la comprobación
	 * @param sc Scanner utilizado para poder introducir el usuario
	 * @return La ID del gestor que tiene el usuario introducido. En caso de que no se haya encontrado, devuelve -1.
	 */
	public static int guardarIdGestor(List<Gestor>gestores, Scanner sc) {
		System.out.print("Introduzca su usuario: ");
		String usuario = sc.nextLine();
		for (int i=0; i<gestores.size(); i++) if (usuario.equals(gestores.get(i).getUsuario())) return i;
		return -1;
	}
	/**
	 * Comprueba si la ID del gestor introducida es correcta o errónea.
	 * @param gestores Lista de gestores que se uiliza para la comprobación
	 * @param sc Scanner utilizado para poder introducir el usuario
	 * @param idUsuario La ID del gestor guardada con la función {@code guardarIdGestor(List<Gestor>gestores, Scanner sc)}
	 * @return true si la ID es correcta o false si es errónea
	 */
	public static boolean comprobacionIdGestor(List<Gestor> gestores, Scanner sc, int idUsuario) {
		try {
			gestores.get(idUsuario);
			return true;
		} catch (IndexOutOfBoundsException e) {
			System.err.println("No se ha encontrado el nombre de usuario. Se le redigirá al menú Login");
			return false;
		}
	}

	public static boolean coincidePasswordGestor(List<Gestor>gestores, Scanner sc, int idUsuario) {
		System.out.print("Inserte su contraseña: ");
		String password = sc.nextLine();
		return password.equals(gestores.get(idUsuario).getPassword());
	}
	
	/**
	 * Guarda la ID del cliente cuyo usuario sea el introducido o devuelve un ID erróneo en caso contrario.
	 * @param clientes Lista de clientes que se uiliza para la comprobación
	 * @param sc Scanner utilizado para poder introducir el usuario
	 * @return La ID del cliente que tiene el usuario introducido. En caso de que no se haya encontrado, devuelve -1.
	 */
	public static int guardarIdCliente(List<Cliente>clientes, Scanner sc) {
		System.out.print("Introduzca su usuario: ");
		String usuario = sc.nextLine();
		for (int i=0; i<clientes.size(); i++) if (usuario.equals(clientes.get(i).getUsuario())) return i;
		return -1;
	}
	/**
	 * Comprueba si la ID del cliente introducida es correcta o errónea.
	 * @param clientes Lista de clientes que se uiliza para la comprobación
	 * @param sc Scanner utilizado para poder introducir el usuario
	 * @param idUsuario La ID del cliente guardada con la función {@code guardarIdCliente(List<Cliente>clientes, Scanner sc)}
	 * @return true si la ID es correcta o false si es errónea
	 */
	public static boolean comprobacionIdCliente(List<Cliente> clientes, Scanner sc, int idUsuario) {
		try {
			clientes.get(idUsuario);
			return true;
		} catch (IndexOutOfBoundsException e) {
			System.err.println("No se ha encontrado el nombre de usuario. Se le redigirá al menú Login");
			return false;
		}
	}
	
	
	public static boolean coincidePasswordCliente(List<Cliente>clientes, Scanner sc, int idUsuario) {
		System.out.print("Inserte su contraseña: ");
		String password = sc.nextLine();
		return password.equals(clientes.get(idUsuario).getPassword());
	}
	
	public static String mensajeError(int nIntentos) {
		nIntentos--;
		if (nIntentos == 0) return "Ya no le quedan intentos. Se le redigirá al Menú Login";
		else if (nIntentos == 1) return "Le queda "+nIntentos+" intento.";
		else return "Le quedan "+nIntentos+" intentos.";
	}
}