package com.sandra.dbBank.utilidades;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.sandra.dbBank.entidades.Cliente;
import com.sandra.dbBank.entidades.Gestor;
import com.sandra.dbBank.entidades.Mensaje;

public class GestorUtils {
	
	private static Scanner sc;
	private static ImageIcon seleccion = new ImageIcon("src/main/java/com/sandra/dbBank/images/seleccion.png");
	
	// Consultar un gestor específico
	public static <T> void printGestor() {
		int id;
		try {
			id = Integer.parseInt(JOptionPane.showInputDialog(null, "Introduzca el ID del gestor%s a obtener: ", "SELECCIÓN DEL GESTOR", 3));
		} catch (NumberFormatException e) {
			id = -1;
		}
		T dato = DatabaseUtils.getOne("gestor", id);
		System.out.println(dato != null ? dato : "No existen coincidencias con el ID introducido");
	}
	
	public static List<Gestor> getGestoresOficina() {
		sc = new Scanner (System.in);
		String oficina;
		System.out.print("Introduzca la oficina para filtrar los gestores: ");
		oficina = sc.nextLine();
		return DatabaseUtils.getWithCondition("gestor", "oficina = ?", new ArrayList<>(Arrays.asList(oficina)));
	}
	
	// Actualización de los atributos específicos del gestor
	public static boolean updateOficina(Gestor gestor) {
		sc = new Scanner (System.in);
		String oficina;
		do {
			System.out.print("Introduzca su nueva oficina (si no quiere actualizar introduzca 'Cancelar'): ");
			oficina = sc.nextLine();
			if (oficina.equals("Cancelar")) break;
			else if (!oficina.isBlank()) gestor.setOficina(oficina);
		} while (oficina.isBlank());
		if (!oficina.equals("Cancelar"))
			return DatabaseUtils.updateDato(gestor, "oficina", new ArrayList<>(Arrays.asList(gestor.getOficina(), gestor.getId())));
		return false;
	}
	
	// Envío de un mensaje
	public static void enviarMensaje(Gestor gestor, Cliente cliente) {
		sc = new Scanner (System.in);
		Mensaje mensaje = new Mensaje(gestor, cliente);
		do {
			System.out.print("Introduzca el concepto del mensaje (el concepto es opcional, pulse Enter si no quiere introducirlo): ");
			mensaje.setConcepto(sc.nextLine());
		} while (mensaje.getConcepto() != null && mensaje.getConcepto().isBlank());
		do {
			System.out.print("Introduzca el texto del mensaje: ");
			mensaje.setTexto(sc.nextLine());
		} while (mensaje.getTexto().isBlank());
		if (DatabaseUtils.addDato(mensaje)) System.out.println("Mensaje enviado correctamente");
	}
	
	// Eliminar cliente de la lista del gestor
	public static void eliminarCliente(Gestor gestor, Cliente cliente) {
		sc = new Scanner (System.in);
		boolean confirmacion = JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar a este cliente de su lista personal?",
				"ELIMINAR CLIENTE", 0, 0, seleccion) == 0;
		if (confirmacion) {
			boolean mensaje_personalizado = JOptionPane.showConfirmDialog(null,
					"¿Quiere mandarle un mensaje personalizado al cliente para avisarle del cambio de gestor?\n"
					+ "Se le mandará uno automáticamente si no lo confirma", "ELIMINAR CLIENTE", 0, 0, seleccion) == 0;
			List<Integer> ids_gestores = DatabaseUtils.getIdsGestor().stream().filter(i -> i != gestor.getId()).collect(Collectors.toList());
			Gestor nuevo_gestor = DatabaseUtils.getOne("gestor", ids_gestores.get(new Random().nextInt(ids_gestores.size())));
			String concepto = "Cambio de gestor", texto = "Su gestor ha decidido traspasarle a otro gestor. Sentimos las molestias.";
			String new_gestor = String.format("\nSu nuevo gestor es el siguiente:\n%s", nuevo_gestor);
			new_gestor = new_gestor.substring(0, new_gestor.lastIndexOf("\n"));
			cliente.setGestor(nuevo_gestor);
			Mensaje mensaje = new Mensaje(gestor, cliente, concepto, texto+new_gestor);
			if (mensaje_personalizado) {
				do {
					System.out.print("Introduzca el concepto del mensaje: ");
					mensaje.setConcepto(sc.nextLine());
				} while (mensaje.getConcepto() != null && mensaje.getConcepto().isBlank());
				do {
					System.out.print("Introduzca el texto del mensaje: ");
					texto = sc.nextLine();
					mensaje.setTexto(texto+new_gestor);
				} while (texto.isBlank());
			}
			if (DatabaseUtils.changeGestor(cliente, mensaje)) System.out.println("Las acciones se han realizado correctamente");
		}
	}
	
}
