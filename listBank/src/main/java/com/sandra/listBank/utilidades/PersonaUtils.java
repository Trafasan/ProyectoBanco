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
import com.sandra.listBank.entidades.Persona;

public class PersonaUtils {
	private static Scanner sc;
	
	// Actualización de los diferentes atributos de las personas registradas
	public static boolean updateDato(Persona persona, String texto) {
		sc = new Scanner (System.in);
		String dato;
		do {
			System.out.printf("Introduzca su nuev%s (si no quiere actualizar introduzca 'Cancelar'): ", texto);
			dato = sc.nextLine();
			if (dato.equals("Cancelar")) break;
			else if (!dato.isBlank()) {
				switch (texto.substring(2)) {
					case "nombre" -> persona.setNombre(dato);
					case "apellido" -> persona.setApellido(dato);
					case "contraseña" -> persona.setPassword(dato);
					case "correo" -> persona.setCorreo(dato);
				}
			}
		} while (dato.isBlank());
		return !dato.equals("Cancelar");
	}

	public static boolean updateDni(Persona persona, List<? extends Persona> personas) {
		sc = new Scanner (System.in);
		String dni;
		final String dni_actual = persona.getDni();
		do {
			System.out.print("Introduzca su nuevo DNI (si no quiere actualizar introduzca 'Cancelar'): ");
			dni = sc.nextLine();
			if (dni.equals("Cancelar")) break;
			else if (samePerson(persona, personas, dni_actual, dni))
				System.err.println("El DNI introducido ya está registrado en otra cuenta, por lo que no se puede actualizar.");
			else if (!dni.isBlank()) persona.setDni(dni);
		} while (persona.getDni().equals(dni_actual) || dni.isBlank());
		return !dni.equals("Cancelar");
	}

	private static boolean samePerson(Persona persona, List<? extends Persona> personas, String dni_actual, String dni) {
		return personas.stream().filter(p -> !p.getDni().equals(dni_actual)).anyMatch(p -> p.getDni().equals(dni));
	}
	
	// Eliminar cuenta de gestor o cliente
	public static boolean eliminarCuenta(Persona persona, List<? extends Persona> personas) {
		sc = new Scanner (System.in);
		String tipo = persona.getClass().getSimpleName().toLowerCase();
		boolean confirmacion = JOptionPane.showConfirmDialog(null, String.format("¿Está seguro de eliminar su cuenta?\n%s", tipo.equals("gestor") ?
				"Toda su lista de clientes se traspasará a diferentes gestores" : "Perderás todo el dinero de tus cuentas"),
				"ELIMINAR CUENTA", 0, 0, new ImageIcon("src/main/java/com/sandra/listBank/images/seleccion.png")) == 0;
		if (confirmacion) {
			if (tipo.equals("gestor")) {
				String concepto = "Cambio de gestor", texto = "Su gestor ha decidido traspasarle a otro gestor. Sentimos las molestias.";
				List<Gestor> gestores_restantes = Gestor.gestores.stream().filter(g -> !g.equals(persona)).collect(Collectors.toList());
				((Gestor)persona).getClientes().forEach(c -> {
					c.setGestor(gestores_restantes.get(new Random().nextInt(gestores_restantes.size())));
					String new_gestor = String.format("\nSu nuevo gestor es el siguiente:\n%s", c.getGestor());
					c.getMensajes().add(new Mensaje((Gestor) persona, c, concepto, texto+new_gestor));
				});
			}
			else if (tipo.equals("cliente")) ((Cliente) persona).getGestor().getClientes().remove(persona);
			personas.remove(persona);
			System.out.println("Cuenta eliminada correctamente");
			return true;
		}
		return false;
	}

}
