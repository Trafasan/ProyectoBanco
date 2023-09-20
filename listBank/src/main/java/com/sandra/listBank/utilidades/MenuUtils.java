package com.sandra.listBank.utilidades;

import java.util.Scanner;
import java.util.stream.Collectors;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.sandra.listBank.entidades.Cliente;
import com.sandra.listBank.entidades.Gestor;
import com.sandra.listBank.entidades.Persona;

public class MenuUtils {
	
	private static Scanner sc;
	private static ImageIcon seleccion = new ImageIcon("src/main/java/com/sandra/listBank/images/seleccion.png");
	
	public static void mainMenu() {
		try {
			String opcion = JOptionPane.showInputDialog(null, "Seleccione una opción: ", "MENÚ PRINCIPAL LISTBANK",
					JOptionPane.PLAIN_MESSAGE, null, new Object[] {"Consultar gestor(es)", "Login", "Registro"}, null).toString();
			switch (opcion) {
				case "Consultar gestor(es)" -> consultarGestores();
				case "Login" -> menuLoginRegistro("LOGIN", "su tipo de cuenta");
				case "Registro" -> menuLoginRegistro("REGISTRO", "el tipo de cuenta que quiere crear");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Gracias por usar este programa", "HASTA PRONTO", 1,
					new ImageIcon("src/main/java/com/sandra/listBank/images/gracias.png"));
		}
	}
	
	private static void consultarGestores() {
		int selector = JOptionPane.showOptionDialog(null, "Seleccione una consulta:", "CONSULTAR GESTOR(ES)", 0, 3,
				seleccion, new Object[] {"Un gestor específico", "Gestores de una misma oficina", "Todos los gestores"}, null);
		switch (selector) {
		case 0 -> GestorUtils.getGestor();
		case 1 -> GestorUtils.getGestoresOficina();
		case 2 -> Gestor.gestores.forEach(System.out::println);
		case -1 -> mainMenu();
		}
		if (selector != -1) consultarGestores();
	}
	
	private static void menuLoginRegistro(String menu, String texto) {
		int selector = JOptionPane.showOptionDialog(null, "Seleccione "+texto+": ", "MENÚ "+menu, 0, 3,
				seleccion, new Object[] {"Gestor", "Cliente"}, null);
		switch (selector) {
		case 0 -> {
			if (menu.equals("LOGIN")) AuthUtils.login(new Gestor(), Gestor.gestores);
			else if (menu.equals("REGISTRO")) AuthUtils.registro(new Gestor(), Gestor.gestores);
		}
		case 1 -> {
			if (menu.equals("LOGIN")) AuthUtils.login(new Cliente(), Cliente.clientes);
			else if (menu.equals("REGISTRO")) AuthUtils.registro(new Cliente(), Cliente.clientes);
		}
		case -1 -> mainMenu();
		}
	}
	
	public static void cuenta(Persona p) {
		String[] opciones = {"Ver tus datos", "Actualizar cuenta", null, null, null,"Borrar cuenta"};
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
			String opcion = JOptionPane.showInputDialog(null, "Seleccione una opción: ", String.format("PERFIL DE %s %s",
					p.getNombre().toUpperCase(), p.getApellido().toUpperCase()), JOptionPane.PLAIN_MESSAGE, null, opciones, null).toString();
			boolean cuenta_borrada = false;
			switch (opcion) {
				case "Ver tus datos" -> System.out.println(p);
				case "Actualizar cuenta" -> updatePersona(p);
				case "Borrar cuenta" -> cuenta_borrada = PersonaUtils.eliminarCuenta(p, tipo.equals("gestor") ? Gestor.gestores : Cliente.clientes);
				case "Ver lista de clientes" -> ClienteUtils.getClientesGestor((Gestor) p);
				case "Gestionar cliente" -> gestionarCliente((Gestor) p);
				case "Consultar mensajes enviados" -> p.getMensajes().forEach(System.out::println);
				case "Consultar mensajes" -> ClienteUtils.mensajesLeidos((Cliente) p);
				case "Gestionar cuentas" -> gestionarCuentas((Cliente) p);
				case "Gestionar transferencias" -> {
					if (((Cliente) p).getCuentas().size() != 0) gestionarTransferencias((Cliente) p);
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
					.showInputDialog(null, "Seleccione dato que quiere actualizar: ", String.format("PERFIL DE %s %s",
							p.getNombre().toUpperCase(), p.getApellido().toUpperCase()), JOptionPane.PLAIN_MESSAGE, null,
							opciones, null).toString();
		} catch (Exception e) {
			update = "Volver atrás";
		}
		boolean correct_update = false;
		switch (update) {
			case "Nombre" -> correct_update = PersonaUtils.updateDato(p, "o nombre");
			case "Apellido(s)" -> correct_update = PersonaUtils.updateDato(p, "o apellido");
			case "DNI" -> correct_update = PersonaUtils.updateDni(p, Gestor.gestores);
			case "Contraseña" -> correct_update = PersonaUtils.updateDato(p, "a contraseña");
			case "Correo" -> correct_update = PersonaUtils.updateDato(p, "o correo");
			case "Oficina" -> correct_update = GestorUtils.updateOficina((Gestor) p);
			case "Gestor" -> correct_update = ClienteUtils.updateGestor((Cliente) p);
		}
		if (!update.equals("Volver atrás") && correct_update) System.out.println("Actualización realizada correctamente");
	}
	
	private static void gestionarCliente(Gestor g) {
		sc = new Scanner (System.in);
		Cliente cliente = new Cliente();
		do {
			System.out.print("Introduzca el DNI del cliente: ");
			cliente.setDni(sc.nextLine());
		} while (cliente.getDni().isBlank());
		if (g.getClientes().stream().noneMatch(c -> c.equals(cliente)))
			System.err.println("No se ha encontrado ningún cliente con ese DNI en su lista");
		else {
			Cliente cliente_gestion = g.getClientes().stream().filter(c -> c.equals(cliente)).collect(Collectors.toList()).get(0);
			int selector = JOptionPane.showOptionDialog(null, String.format("Ha seleccionado a %s %s. ¿Qué acción desea realizar?",
					cliente_gestion.getNombre(), cliente_gestion.getApellido()), "GESTIONAR CLIENTE", 0, 3,
					seleccion, new Object[] {"Enviar un mensaje", "Dejar de ser su gestor"}, null);
			switch (selector) {
				case 0 -> GestorUtils.enviarMensaje(g, cliente_gestion);
				case 1 -> GestorUtils.eliminarCliente(g, cliente_gestion);
			}
		}
	}
	
	private static void gestionarCuentas(Cliente cliente) {
		int selector = JOptionPane.showOptionDialog(null, "¿Qué acción desea realizar?", "GESTIONAR CUENTAS", 0, 3,
				seleccion, new Object[] {"Ver todas tus cuentas", "Añadir cuenta", "Eliminar cuenta"}, null);
		switch (selector) {
			case 0 -> cliente.getCuentas().forEach(c -> System.out.printf("Cuenta nº%d (Saldo: %.2f€)\n", c.getId(), c.getSaldo()));
			case 1 -> ClienteUtils.addCuenta(cliente);
			case 2 -> {
				if (cliente.getCuentas().size() != 0) ClienteUtils.eliminarCuenta(cliente);
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
