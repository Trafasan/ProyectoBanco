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
import com.sandra.dbBank.entidades.Persona;

public class GeneralUtils {
	private static Scanner sc;
	
	/**
	 * Método que imprime por consola todos los datos de una lista.<br>
	 * Si la lista está vacía, se imprime un mensaje de error.
	 * @param <T>
	 * @param lista {@code List} que puede ser de gestores, clientes, cuentas, mensajes o transferencias
	 * @param condicion Indica si se ha aplicado alguna condición a la selección de datos
	 */
	public static <T> void printDatos(List<T> lista, boolean condicion) {
		String mensaje = condicion ? "Ningún dato coincide con las condiciones indicadas" : "La tabla seleccionada está vacía";
		if (lista.size()==0) JOptionPane.showMessageDialog(null, mensaje, "ERROR", 2, null);
		else lista.forEach(System.out::println);
	}
	
	// Actualización de los diferentes atributos de las personas registradas
	public static boolean updateDato(Persona persona, String texto) {
		sc = new Scanner (System.in);
		String dato, atributo = "", tag = texto.substring(2).toLowerCase();
		do {
			System.out.printf("Introduzca su nuev%s (si no quiere actualizar introduzca 'Cancelar'): ", texto);
			dato = sc.nextLine();
			if (dato.equals("Cancelar")) break;
			else if (!dato.isBlank()) {
				switch (tag) {
					case "nombre" -> persona.setNombre(atributo = dato);
					case "apellido" -> persona.setApellido(atributo = dato);
					case "dni" -> persona.setDni(atributo = dato);
					case "contraseña" -> persona.setPassword(atributo = dato);
					case "correo" -> persona.setCorreo(atributo = dato);
				}
			}
		} while (dato.isBlank());
		if (!dato.equals("Cancelar")) return DatabaseUtils.updateDato(persona, tag, new ArrayList<>(Arrays.asList(atributo, persona.getId())));
		return false;
	}
	
	// Eliminar cuenta de gestor o cliente
	public static boolean eliminarCuenta(Persona persona) {
		sc = new Scanner (System.in);
		String tipo = persona.getClass().getSimpleName().toLowerCase();
		boolean borrado = false, confirmacion = JOptionPane.showConfirmDialog(null, String.format("¿Está seguro de eliminar su cuenta?\n%s",
				tipo.equals("gestor") ? "Toda su lista de clientes se traspasará a diferentes gestores" : "Perderás todo el dinero de tus cuentas"),
				"ELIMINAR CUENTA", 0, 0, new ImageIcon("src/main/java/com/sandra/dbBank/images/seleccion.png")) == 0;
		if (confirmacion) {
			if (tipo.equals("gestor")) {
				String concepto = "Cambio de gestor", texto = "Su gestor ha decidido traspasarle a otro gestor. Sentimos las molestias.";
				List<Integer> ids_gestores = DatabaseUtils.getIdsGestor().stream().filter(i -> i != persona.getId()).collect(Collectors.toList());
				for (Cliente c:ClienteUtils.getClientes((Gestor) persona)) {
					c.setGestor(DatabaseUtils.getOne("gestor", ids_gestores.get(new Random().nextInt(ids_gestores.size()))));
					String new_gestor = String.format("\nSu nuevo gestor es el siguiente:\n%s", c.getGestor());
					new_gestor = new_gestor.substring(0, new_gestor.lastIndexOf("\n"));
					if (DatabaseUtils.changeGestor(c, new Mensaje((Gestor) persona, c, concepto, texto+new_gestor)))
						borrado = DatabaseUtils.deleteDato(tipo, persona.getId());
				}
			}
			else if (tipo.equals("cliente")) borrado = DatabaseUtils.deleteCliente((Cliente) persona);
			if (borrado) System.out.println("Cuenta eliminada correctamente");
			return borrado;
		}
		return false;
	}
	
	// Consultar mensajes enviados
	public static List<Mensaje> getMensajes(Persona persona) {
		String tipo = persona.getClass().getSimpleName().toLowerCase();
		List<Mensaje> mensajes = DatabaseUtils.getWithCondition("mensaje", tipo+" = ?", new ArrayList<>(Arrays.asList(persona.getId())));
		for (Mensaje m:mensajes) {
			if (m.getGestor() != null) m.setGestor(tipo.equals("gestor") ? (Gestor) persona : DatabaseUtils.getOne("gestor", m.getGestor().getId()));
			m.setCliente(tipo.equals("cliente") ? (Cliente) persona : DatabaseUtils.getOne("cliente", m.getCliente().getId()));
		}
		return mensajes;
	}

}
