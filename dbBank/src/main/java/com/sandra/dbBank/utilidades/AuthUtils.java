package com.sandra.dbBank.utilidades;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JOptionPane;

import com.sandra.dbBank.entidades.Cliente;
import com.sandra.dbBank.entidades.Gestor;
import com.sandra.dbBank.entidades.Persona;

public class AuthUtils {
	
	private static Scanner sc;
	
	// Login genérico
	public static void login(Persona persona) {
		sc = new Scanner (System.in);
		String tipo = persona.getClass().getSimpleName().toLowerCase();
		do {
			System.out.print("Introduzca su DNI: ");
			persona.setDni(sc.nextLine());
		} while (persona.getDni().isBlank());
		do {
			System.out.print("Introduzca su contraseña: ");
			persona.setPassword(sc.nextLine());
		} while (persona.getPassword().isBlank());
		List<Persona> p_login_list = getPersona(tipo, persona.getDni(), persona.getPassword());
		if (p_login_list.size() == 0) {
			JOptionPane.showMessageDialog(null, "DNI y/o contraseña no válidos", "ERROR", 0, null);
			MenuUtils.mainMenu();
		}
		else MenuUtils.cuenta(p_login_list.get(0));
	}
	
	// Método necesario para el registro (sin él el ID de la persona sería nulo)
	private static List<Persona> getPersona(String tipo, String dni, String password) {
		List<Object> parametros = new ArrayList<>(Arrays.asList(dni, password));
		return DatabaseUtils.getWithCondition(tipo, "dni = ? AND password = ?", parametros);
	}
	
	// Registro genérico
	public static void registro(Persona persona) {
		sc = new Scanner (System.in);
		String tipo = persona.getClass().getSimpleName().toLowerCase();
		do {
			System.out.printf("Introduzca el DNI del nuevo %s: ", tipo);
			persona.setDni(sc.nextLine());
		} while (persona.getDni().isBlank());
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
			System.out.printf("Introduzca el apellido del nuevo %s (el apellido es opcional, pulse Enter si no quiere introducirlo): ", tipo);
			persona.setApellido(sc.nextLine());
		} while (persona.getApellido() != null && persona.getApellido().isBlank());
		do {
			System.out.printf("Introduzca el correo del nuevo %s: ", tipo);
			persona.setCorreo(sc.nextLine());
		} while (persona.getCorreo().isBlank());
		if (tipo.equals("gestor")) addGestor((Gestor) persona);
		else if (tipo.equals("cliente")) addCliente((Cliente) persona);
		boolean todo_correcto = DatabaseUtils.addDato(persona);
		if (todo_correcto) {
			System.out.println("Registro realizado correctamente.");
			MenuUtils.cuenta(getPersona(tipo, persona.getDni(), persona.getPassword()).get(0));
		}
		else {
			JOptionPane.showMessageDialog(null, "El DNI y/o el correo ya están registrados", "ERROR", 0, null);
			MenuUtils.mainMenu();
		}
	}
	
	// Registro de un gestor
	private static void addGestor(Gestor gestor) {
		do {
			System.out.print("Introduzca la oficina donde trabaja el nuevo gestor: ");
			gestor.setOficina(sc.nextLine());
		} while (gestor.getOficina().isBlank());
	}
	
	// Registro de un cliente
	private static void addCliente(Cliente cliente) {
		List<Integer> ids_gestores = DatabaseUtils.getIdsGestor();
		Gestor gestor = DatabaseUtils.getOne("gestor", ids_gestores.get(new Random().nextInt(ids_gestores.size())));
		System.out.printf("Se le ha asignado el siguiente gestor de manera aleatoria: %s %s\n", gestor.getNombre(), gestor.getApellido());
		cliente.setGestor(gestor);
	}
	
}
