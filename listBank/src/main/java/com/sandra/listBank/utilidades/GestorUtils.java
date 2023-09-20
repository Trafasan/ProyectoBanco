package com.sandra.listBank.utilidades;

import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.sandra.listBank.entidades.Cliente;
import com.sandra.listBank.entidades.Gestor;
import com.sandra.listBank.entidades.Mensaje;

public class GestorUtils {
	private static Scanner sc;
	
	// Obtención de uno o varios gestores
	public static void getGestor() {
		sc = new Scanner (System.in);
		Gestor gestor = new Gestor();
		do {
			System.out.print("Introduzca el DNI del gestor: ");
			gestor.setDni(sc.nextLine());
		} while (gestor.getDni().isBlank());
		if (Gestor.gestores.stream().noneMatch(g -> g.equals(gestor))) System.err.println("No se ha encontrado ningún gestor con ese DNI");
		else Gestor.gestores.stream().filter(g -> g.equals(gestor)).forEach(System.out::println);
	}
	
	public static void getGestoresOficina() {
		sc = new Scanner (System.in);
		String oficina;
		System.out.print("Introduzca la oficina para filtrar los gestores: ");
		oficina = sc.nextLine();
		if (Gestor.gestores.stream().noneMatch(g -> g.getOficina().equals(oficina)))
			System.err.println("No se ha encontrado ningún gestor trabajando en esa oficina");
		else Gestor.gestores.stream().filter(g -> g.getOficina().equals(oficina)).forEach(System.out::println);
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
		return !oficina.equals("Cancelar");
	}
	
	// Envío de un mensaje
	public static void enviarMensaje(Gestor gestor, Cliente cliente) {
		sc = new Scanner (System.in);
		Mensaje mensaje = new Mensaje(gestor, cliente);
		do {
			System.out.print("Introduzca el concepto del mensaje: ");
			mensaje.setConcepto(sc.nextLine());
		} while (mensaje.getConcepto().isBlank());
		do {
			System.out.print("Introduzca el texto del mensaje: ");
			mensaje.setTexto(sc.nextLine());
		} while (mensaje.getTexto().isBlank());
		System.out.println("Mensaje enviado correctamente");
	}
	
	// Eliminar cliente de la lista del gestor
	public static void eliminarCliente(Gestor gestor, Cliente cliente) {
		sc = new Scanner (System.in);
		boolean confirmacion = JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar a este cliente de su lista personal?",
				"ELIMINAR CLIENTE", 0, 0, new ImageIcon("src/main/java/com/sandra/listBank/images/seleccion.png")) == 0;
		if (confirmacion) {
			boolean mensaje_personalizado = JOptionPane.showConfirmDialog(null,
					"¿Quiere mandarle un mensaje personalizado al cliente para avisarle del cambio de gestor?\n"
					+ "Se le mandará uno automáticamente si no lo confirma", "ELIMINAR CLIENTE", 0, 0,
					new ImageIcon("src/main/java/com/sandra/listBank/images/seleccion.png")) == 0;
			List<Gestor> gestores_restantes = Gestor.gestores.stream().filter(g -> !g.equals(gestor)).collect(Collectors.toList());
			Gestor nuevo_gestor = gestores_restantes.get(new Random().nextInt(gestores_restantes.size()));
			String concepto = "Cambio de gestor", texto = "Su gestor ha decidido traspasarle a otro gestor. Sentimos las molestias.";
			String new_gestor = String.format("\nSu nuevo gestor es el siguiente:\n%s", nuevo_gestor);
			gestor.getClientes().remove(cliente);
			cliente.setGestor(nuevo_gestor);
			Mensaje mensaje = new Mensaje(gestor, cliente, concepto, texto+new_gestor);
			if (mensaje_personalizado) {
				do {
					System.out.print("Introduzca el concepto del mensaje: ");
					concepto = sc.nextLine();
					if (!concepto.isBlank()) mensaje.setConcepto(concepto);
				} while (concepto.isBlank());
				do {
					System.out.print("Introduzca el texto del mensaje: ");
					texto = sc.nextLine();
					if (!texto.isBlank()) mensaje.setTexto(texto+new_gestor);
				} while (texto.isBlank());
			}
			System.out.println("Mensaje enviado correctamente");
		}
	}
}
