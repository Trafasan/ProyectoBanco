package com.project.utils;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.project.models.Gestor;

public class UIGestor{
	// Inserción de un gestor
	public static void addGestor(List<Gestor> gestores, Scanner sc) {
		int id = gestores.size();
		gestores.add(new Gestor(id+1));
		System.out.print("Introduzca el nombre del nuevo gestor: ");
		gestores.get(id).setNombre(sc.nextLine());
		System.out.print("Introduzca el apellido del nuevo gestor: ");
		gestores.get(id).setApellido(sc.nextLine());
		System.out.print("Introduzca el DNI del nuevo gestor: ");
		gestores.get(id).setDni(sc.nextLine());
		System.out.print("Introduzca el usuario del nuevo gestor: ");
		gestores.get(id).setUsuario(sc.nextLine());
		System.out.print("Introduzca la contraseña del nuevo gestor: ");
		gestores.get(id).setPassword(sc.nextLine());
		System.out.print("Introduzca el correo del nuevo gestor: ");
		gestores.get(id).setCorreo(sc.nextLine());
		System.out.println("Inserción realizada correctamente.");
	}

	// Inserción de varios gestores con datos por defecto
	public static void addGestores(List<Gestor> gestores, Scanner sc) {
		System.out.print("Introduzca el número de gestores que desea añadir: ");
		int nGestores = gestores.size()+Integer.parseInt(sc.nextLine());
		for (int i=(gestores.size()+1); i<=nGestores; i++) gestores.add(new Gestor(i));
		System.out.println("Inserciones realizadas correctamente.");
	}

	// Obtención de un gestor
	public static void getGestor(List<Gestor> gestores, Scanner sc) {
		System.out.print("Introduzca la ID del gestor que desea obtener: ");
		try {
			System.out.println(gestores.get(Integer.parseInt(sc.nextLine())-1));
		} catch (IndexOutOfBoundsException e) {
			System.err.println("No se ha encontrado al gestor con la ID introducida. Se le redigirá al Menú Gestor.");
		}
	}

	// Obtención de todos los gestores
	public static void getGestores(List<Gestor> gestores) {
		for (int i=0; i<gestores.size(); i++) System.out.println(gestores.get(i));
	}

	// Actualización de un gestor (dato a dato)

	// Comprobación previa de que existe el gestor con ese ID
	public static int comprobarIdGestor(List<Gestor> gestores, Scanner sc) {
		System.out.print("Proporciona la ID del gestor que quieres modificar: ");
		return Integer.parseInt(sc.nextLine())-1;
	}
	public static boolean existeGestor(List<Gestor> gestores, int id, String menu) {
		try {
			gestores.get(id);
			return true;
		} catch (IndexOutOfBoundsException e) {
			System.err.println("No se ha encontrado al gestor con la ID introducida. Se le redigirá al Menú "+menu+".");
			return false;
		}
	}

	public static void updateNombreGestor(List<Gestor> gestores, Scanner sc, int id) {
		System.out.print("Inserte el nuevo nombre del gestor " + (id+1) + ": ");
		gestores.get(id).setNombre(sc.nextLine());
	}

	public static void updateApellidoGestor(List<Gestor> gestores, Scanner sc, int id) {
		System.out.print("Inserte el nuevo apellido del gestor " + (id+1) + ": ");
		gestores.get(id).setApellido(sc.nextLine());
	}

	public static void updateDniGestor(List<Gestor> gestores, Scanner sc, int id) {
		System.out.print("Inserte el DNI del gestor " + (id+1) + ": ");
		gestores.get(id).setDni(sc.nextLine());
	}

	public static void updateUsuarioGestor(List<Gestor> gestores, Scanner sc, int id) {
		System.out.print("Inserte el nuevo usuario del gestor " + (id+1) + ": ");
		gestores.get(id).setUsuario(sc.nextLine());
	}

	public static void updatePasswordGestor(List<Gestor> gestores, Scanner sc, int id) {
		System.out.print("Inserte la nueva contraseña del gestor " + (id+1) + ": ");
		gestores.get(id).setPassword(sc.nextLine());
	}

	public static void updateCorreoGestor(List<Gestor> gestores, Scanner sc, int id) {
		System.out.print("Inserte el nuevo correo del gestor " + (id+1) + ": ");
		gestores.get(id).setCorreo(sc.nextLine());
	}

	// Eliminación de un gestor con confirmación de la acción
	public static void deleteGestor(List<Gestor> gestores, Scanner sc) {
		System.out.print("Introduzca la ID del gestor a eliminar: ");
		int id = Integer.parseInt(sc.nextLine());
		int confirmacion;
		try {
			confirmacion = JOptionPane.showConfirmDialog(null,
					"¿Seguro que quieres eliminar al gestor " + id + "?",
					"MENSAJE DE CONFIRMACIÓN", 0, 3, new ImageIcon("src/images/eliminar.png"));
		} catch (Exception e) {
			confirmacion = 1;
		}
		switch (confirmacion) {
		case 0 -> {
			try {
				gestores.remove(--id);
				for (int i = id; i < gestores.size(); i++) gestores.get(i).setId_gestor(i+1);
				System.out.println("Eliminación realizada correctamente.");
			} catch (IndexOutOfBoundsException e) {
				System.err.println("No se ha encontrado al gestor con la ID introducida. Se le redigirá al Menú Gestor.");
			}
		}
		case 1 -> JOptionPane.showMessageDialog(null, "No se ha eliminado el gestor " + id);
		}
	}
	
	/**
	 * Elige aleatoriamente un gestor de entre todos los presentes en una lista.
	 * @param gestores Lista de gestores cuyo tamaño coincide con el ID máximo.
	 * @return ID del gestor elegido
	 */
	public static int getIdGestorAleatorio(List<Gestor> gestores) {
		return new Random().nextInt(gestores.size());
	}
	
	public static void getGestorAleatorio(List<Gestor> gestores, int id) {
		System.out.println(gestores.get(id));
	}
}