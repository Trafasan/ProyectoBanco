package com.sandra.listBank.utilidades;

import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.sandra.listBank.entidades.Cliente;
import com.sandra.listBank.entidades.Gestor;
import com.sandra.listBank.entidades.Persona;

public class AuthUtils {
	private static Scanner sc;
	
	// Login genérico
	public static void login(Persona persona, List<? extends Persona> personas) {
		sc = new Scanner (System.in);
		boolean seguir_login = true;
		String dni;
		do {
			System.out.print("Introduzca su DNI: ");
			dni = sc.nextLine();
			persona.setDni(dni);
			if (!dni.isBlank() && !persona.getDni().isBlank() && personas.stream().noneMatch(p -> p.equals(persona))) {
				seguir_login = JOptionPane.showConfirmDialog(null, "No se ha reconocido este DNI. ¿Se ha equivocado al introducirlo?", "ERROR",
						0, 0, new ImageIcon("src/main/java/com/sandra/listBank/images/seleccion.png")) == 0;
			}
			else if (!dni.isBlank() && !persona.getDni().isBlank()) break;
		} while (seguir_login);
		if (!seguir_login) System.out.println("Regístrese o compruebe si se ha equivocado al elegir su tipo de cuenta.");
		else {
			Persona p_login = personas.stream().filter(p -> p.equals(persona)).collect(Collectors.toList()).get(0);
			int intentos_restantes = 3;
			do {
				System.out.printf("Bienvenid@ %s, introduzca su contraseña para acceder a tu perfil: ", p_login.getNombre());
				persona.setPassword(sc.nextLine());
				if (personas.stream().noneMatch(p -> p.getPassword().equals(persona.getPassword()))) {
					JOptionPane.showMessageDialog(null, String.format("Contraseña incorrecta.\nLe queda%s %d intento%s.",
							intentos_restantes != 1 ? "n" : "", intentos_restantes-=1, intentos_restantes != 1 ? "s" : ""), "ERROR", 0, null);
				}
				else if (!persona.getPassword().isBlank()) break;
			} while (intentos_restantes>0);
			if (intentos_restantes == 0) {
				personas.stream().filter(p -> p.equals(persona)).forEach(p->
				System.out.printf("Si usted es %s %s, intente iniciar sesión más tarde.\n", p.getNombre(), p.getApellido()));
				MenuUtils.mainMenu();
			}
			else MenuUtils.cuenta(p_login);
		}
	}
	
	// Registro genérico
	public static void registro(Persona persona, List<? extends Persona> personas) {
		sc = new Scanner (System.in);
		String tipo = persona.getClass().getSimpleName().toLowerCase();
		boolean seguir_registro = true;
		String dni;
		do {
			System.out.printf("Introduzca el DNI del nuevo %s: ", tipo);
			dni = sc.nextLine();
			persona.setDni(dni);
			if (!dni.isBlank() && personas.stream().anyMatch(p -> p.equals(persona))) {
				seguir_registro = JOptionPane.showConfirmDialog(null, "Este DNI ya está registrado. ¿Se ha equivocado al introducirlo?", "ERROR",
						0, 0, new ImageIcon("src/main/java/com/sandra/listBank/images/seleccion.png")) == 0;
			}
			else if (!dni.isBlank() && !persona.getDni().isBlank()) break;
		} while (seguir_registro);
		if (!seguir_registro) personas.stream().filter(p -> p.equals(persona)).forEach(p->
			System.out.printf("Si usted es %s %s, ya está registrado con nosotros.\n", p.getNombre(), p.getApellido()));
		else {
			do {
				System.out.printf("Introduzca la contraseña del nuevo %s: ", tipo);
				persona.setPassword(sc.nextLine());
			} while (persona.getPassword().isBlank());
			System.out.println("Rellena ahora tus datos personales...");
			do {
				System.out.printf("Introduzca el nombre del nuevo %s: ", tipo);
				persona.setNombre(sc.nextLine());
			} while (persona.getNombre().isBlank());
			do {
				System.out.printf("Introduzca el apellido del nuevo %s: ", tipo);
				persona.setApellido(sc.nextLine());
			} while (persona.getApellido().isBlank());
			do {
				System.out.printf("Introduzca el correo del nuevo %s: ", tipo);
				persona.setCorreo(sc.nextLine());
				if (personas.stream().anyMatch(p -> p.getCorreo().equals(persona.getCorreo()))) {
					seguir_registro = JOptionPane.showConfirmDialog(null, "Este correo ya está registrado. ¿Se ha equivocado al introducirlo?",
							"ERROR", 0, 0, new ImageIcon("src/main/java/com/sandra/listBank/images/seleccion.png")) == 0;
				}
				else if (!persona.getCorreo().isBlank()) break;
			} while (seguir_registro);
			if (!seguir_registro) personas.stream().filter(p -> p.getCorreo().equals(persona.getCorreo())).forEach(p->
				System.out.printf("Si usted es %s %s, ya está registrado con nosotros.\n", p.getNombre(), p.getApellido()));
			else {
				if (tipo.equals("gestor")) addGestor((Gestor) persona);
				else addCliente((Cliente) persona);
				System.out.println("Registro realizado correctamente.");
				MenuUtils.cuenta(persona);
			}
		}
	}
	
	// Registro de un gestor
	private static void addGestor(Gestor gestor) {
		do {
			System.out.print("Introduzca la oficina donde trabaja el nuevo gestor: ");
			gestor.setOficina(sc.nextLine());
		} while (gestor.getOficina().isBlank());
		Gestor.gestores.add(gestor);
	}
	
	// Registro de un cliente
	private static void addCliente(Cliente cliente) {
		Gestor gestor = Gestor.gestores.get(new Random().nextInt(Gestor.gestores.size()));
		System.out.printf("Se le ha asignado el siguiente gestor de manera aleatoria: %s %s\n", gestor.getNombre(), gestor.getApellido());
		cliente.setGestor(gestor);
		gestor.getClientes().add(cliente);
		Cliente.clientes.add(cliente);
	}
	
}
