package com.sandra.dbBank.utilidades;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.sandra.dbBank.entidades.Cliente;
import com.sandra.dbBank.entidades.Cuenta;
import com.sandra.dbBank.entidades.Gestor;
import com.sandra.dbBank.entidades.Persona;

public class MenuUtils {
	
	private static Scanner sc;
	private static ImageIcon seleccion = new ImageIcon("src/main/java/com/sandra/dbBank/images/seleccion.png");
	
	public static void mainMenu() {
		try {
			String opcion = JOptionPane.showInputDialog(null, "Seleccione una opción: ", "MENÚ PRINCIPAL DBBANK",
					JOptionPane.PLAIN_MESSAGE, null, new Object[] {"Consultar gestor(es)", "Login", "Registro"}, null).toString();
			switch (opcion) {
				case "Consultar gestor(es)" -> consultarGestores();
				case "Login" -> menuLoginRegistro("LOGIN", "su tipo de cuenta");
				case "Registro" -> menuLoginRegistro("REGISTRO", "el tipo de cuenta que quiere crear");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Gracias por usar este programa", "HASTA PRONTO", 1,
					new ImageIcon("src/main/java/com/sandra/dbBank/images/gracias.png"));
		}
	}
	
	private static void consultarGestores() {
		int selector = JOptionPane.showOptionDialog(null, "Seleccione una consulta:", "CONSULTAR GESTOR(ES)", 0, 3,
				new ImageIcon("src/main/java/com/sandra/dbBank/images/seleccion.png"), new Object[] {"Un gestor específico",
						"Gestores de una misma oficina", "Todos los gestores"}, null);
		switch (selector) {
		case 0 -> GestorUtils.printGestor();
		case 1 -> GeneralUtils.printDatos(GestorUtils.getGestoresOficina(), true);
		case 2 -> GeneralUtils.printDatos(DatabaseUtils.getAll("gestor"), false);
		case -1 -> mainMenu();
		}
		if (selector != -1) consultarGestores();
	}
	
	private static void menuLoginRegistro(String menu, String texto) {
		int selector = JOptionPane.showOptionDialog(null, "Seleccione "+texto+": ", "MENÚ "+menu, 0, 3, seleccion,
				new Object[] {"Gestor", "Cliente"}, null);
		switch (selector) {
		case 0 -> {
			if (menu.equals("LOGIN")) AuthUtils.login(new Gestor());
			else if (menu.equals("REGISTRO")) AuthUtils.registro(new Gestor());
		}
		case 1 -> {
			if (menu.equals("LOGIN")) AuthUtils.login(new Cliente());
			else if (menu.equals("REGISTRO")) AuthUtils.registro(new Cliente());
		}
		case -1 -> mainMenu();
		}
	}
	// Me he quedado por aquí durante la revisión (revisando el envio de una transferencia)
	public static void cuenta(Persona p) {
		String[] opciones = {"Ver tus datos", "Actualizar cuenta", null, null, null, "Borrar cuenta"};
		String tipo = p.getClass().getSimpleName().toLowerCase();
		if (tipo.equals("gestor")) {
			opciones[2] = "Ver lista de clientes";
			opciones[3] = "Gestionar cliente";
			opciones[4] = "Consultar mensajes enviados";
		}
		else if (tipo.equals("cliente")) {
			opciones[2] = "Consultar mensajes";
			opciones[3] = "Gestionar cuentas";
			opciones[4] = "Gestionar transferencias";
		}
		try {
			String opcion = JOptionPane.showInputDialog(null, "Seleccione una opción: ", String.format("PERFIL DE %s%s",
					p.getNombre().toUpperCase(), p.getApellido() != null ? " "+p.getApellido().toUpperCase() : ""), JOptionPane.PLAIN_MESSAGE,
					null, opciones, "Borrar cuenta").toString();
			boolean cuenta_borrada = false;
			switch (opcion) {
				case "Ver tus datos" -> System.out.println(p.toStringAllData());
				case "Actualizar cuenta" -> updatePersona(p);
				case "Borrar cuenta" -> cuenta_borrada = GeneralUtils.eliminarCuenta(p);
				case "Ver lista de clientes" -> GeneralUtils.printDatos(ClienteUtils.getClientes((Gestor) p), true);
				case "Gestionar cliente" -> gestionarCliente((Gestor) p);
				case "Consultar mensajes enviados" -> GeneralUtils.printDatos(GeneralUtils.getMensajes(p), true);
				case "Consultar mensajes" -> ClienteUtils.leerMensajes((Cliente) p);
				case "Gestionar cuentas" -> gestionarCuentas((Cliente) p);
				case "Gestionar transferencias" -> {
					List<Cuenta> cuentas = DatabaseUtils.getWithCondition("cuenta", "cliente = ?", new ArrayList<>(Arrays.asList(p.getId())));
					if (cuentas.size() != 0) gestionarTransferencias((Cliente) p);
					else System.err.println("No dispones de ninguna cuenta, crea una para gestionar tus transferencias");
				}
			}
			if (opcion.equals("Borrar cuenta") && cuenta_borrada) mainMenu();
			else cuenta(p);
		} catch (Exception e) {
			mainMenu();
		}
	}
	
	private static void updatePersona(Persona p) {
		String[] opciones = {"Nombre", "Apellido(s)", "DNI", "Contraseña", "Correo", null};
		String tipo = p.getClass().getSimpleName().toLowerCase();
		opciones[opciones.length-1] = tipo.equals("gestor") ? "Oficina" : "Gestor";
		String update;
		try {
			update = JOptionPane
					.showInputDialog(null, "Seleccione dato que quiere actualizar: ", String.format("PERFIL DE %s%s",
							p.getNombre().toUpperCase(), p.getApellido() != null ? " "+p.getApellido().toUpperCase() : ""),
							JOptionPane.PLAIN_MESSAGE, null, opciones, null).toString();
		} catch (Exception e) {
			update = "Volver atrás";
		}
		boolean correct_update = false;
		switch (update) {
			case "Nombre" -> correct_update = GeneralUtils.updateDato(p, "o nombre");
			case "Apellido(s)" -> correct_update = GeneralUtils.updateDato(p, "o apellido");
			case "DNI" -> correct_update = GeneralUtils.updateDato(p, "o DNI");
			case "Contraseña" -> correct_update = GeneralUtils.updateDato(p, "a contraseña");
			case "Correo" -> correct_update = GeneralUtils.updateDato(p, "o correo");
			case "Oficina" -> correct_update = GestorUtils.updateOficina((Gestor) p);
			case "Gestor" -> correct_update = ClienteUtils.updateGestor((Cliente) p);
		}
		if (!update.equals("Volver atrás") && correct_update) System.out.println("Actualización realizada correctamente");
	}
	
	private static void gestionarCliente(Gestor g) {
		sc = new Scanner (System.in);
		int id;
		try {
			System.out.print("Introduzca el ID del cliente: ");
			id = sc.nextInt();
			List<Object> parametros = new ArrayList<>(Arrays.asList(id, g.getId()));
			List<Cliente> cliente_list = DatabaseUtils.getWithCondition("cliente", "id = ? AND gestor = ?", parametros);
			if (cliente_list.size() != 0) {
				Cliente cliente = ClienteUtils.getGestor(cliente_list.get(0));
				int selector = JOptionPane.showOptionDialog(null, String.format("Ha seleccionado a %s%s. ¿Qué acción desea realizar?",
						cliente.getNombre(), cliente.getApellido() != null ? " "+cliente.getApellido().toUpperCase() : ""), "GESTIONAR CLIENTE",
						0, 3, seleccion, new Object[] {"Enviar un mensaje", "Dejar de ser su gestor"}, null);
				switch (selector) {
					case 0 -> GestorUtils.enviarMensaje(g, cliente);
					case 1 -> GestorUtils.eliminarCliente(g, cliente);
				}
			}
			else JOptionPane.showMessageDialog(null, "No existe un cliente con este ID en su lista de clientes", "ERROR", 0, null);
		} catch (InputMismatchException e) {
			JOptionPane.showMessageDialog(null, "El ID introducido no es un número", "ERROR", 0, null);
		}
	}
	
	private static void gestionarCuentas(Cliente cliente) {
		int selector = JOptionPane.showOptionDialog(null, "¿Qué acción desea realizar?", "GESTIONAR CUENTAS", 0, 3,
				seleccion, new Object[] {"Ver todas tus cuentas", "Añadir cuenta", "Eliminar cuenta"}, null);
		switch (selector) {
			case 0 -> GeneralUtils.printDatos(DatabaseUtils.getWithCondition("cuenta", "cliente = ?",
					new ArrayList<>(Arrays.asList(cliente.getId()))), true);
			case 1 -> ClienteUtils.addCuenta(cliente);
			case 2 -> {
				List<Cuenta> cuentas = DatabaseUtils.getWithCondition("cuenta", "cliente = ?", new ArrayList<>(Arrays.asList(cliente.getId())));
				if (cuentas.size() != 0) ClienteUtils.eliminarCuenta(cliente, cuentas);
				else System.err.println("No dispones de ninguna cuenta");
			}
		}
	}
	
	private static void gestionarTransferencias(Cliente cliente) {
		int selector = JOptionPane.showOptionDialog(null, "¿Qué acción desea realizar?", "GESTIONAR TRANSFERENCIAS", 0, 3,
				seleccion, new Object[] {"Ver historial de transferencias", "Enviar una transferencia"}, null);
		switch (selector) {
			case 0 -> ClienteUtils.getTransferencias(cliente);
			case 1 -> ClienteUtils.sendTransferencia(cliente);
		}
	}

}
