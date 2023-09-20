package com.sandra.dbBank.utilidades;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.sandra.dbBank.entidades.Cliente;
import com.sandra.dbBank.entidades.Cuenta;
import com.sandra.dbBank.entidades.Gestor;
import com.sandra.dbBank.entidades.Mensaje;
import com.sandra.dbBank.entidades.Transferencia;

public class ClienteUtils {
	
	private static Scanner sc;
	private static ImageIcon seleccion = new ImageIcon("src/main/java/com/sandra/dbBank/images/seleccion.png");
	
	// Obtención del gestor de un cliente (no se hace en el mismo método para no sobreescribir el ResultSet)
	public static Cliente getGestor(Cliente cliente) {
		cliente.setGestor(DatabaseUtils.getOne("gestor", cliente.getGestor().getId()));
		return cliente;
	}
	
	// Obtención de los clientes de un gestor
	public static List<Cliente> getClientes(Gestor g) {
		List<Cliente> clientes =  DatabaseUtils.getWithCondition("cliente", "gestor = ?", new ArrayList<>(Arrays.asList(g.getId())));
		return clientes.stream().peek(c -> getGestor(c)).collect(Collectors.toList());
	}
	
	// Actualización de los atributos específicos del cliente
	public static boolean updateGestor(Cliente cliente) {
		sc = new Scanner (System.in);
		final Gestor gestor_actual = cliente.getGestor();
		List<Gestor> gestores_restantes = DatabaseUtils.getWithCondition("gestor", "id <> ?", new ArrayList<>(Arrays.asList(gestor_actual.getId())));
		int id_gestor;
		System.out.println("Aquí tiene la lista de gestores:");
		gestores_restantes.forEach(g -> System.out.printf("%d. %s %s\n", g.getId(), g.getNombre(), g.getApellido()));
		do {
			System.out.print("Introduzca el ID de su nuevo gestor (si no quiere actualizar introduzca '0'): ");
			id_gestor = sc.nextInt();
			Gestor gestor = new Gestor(id_gestor);
			if (id_gestor == 0) break;
			else if (gestores_restantes.stream().noneMatch(g -> g.equals(gestor))) System.err.println("Ese gestor no existe");
			else {
				cliente.setGestor(gestores_restantes.stream().filter(g -> g.equals(gestor)).collect(Collectors.toList()).get(0));
				return DatabaseUtils.updateDato(cliente, "gestor", new ArrayList<>(Arrays.asList(cliente.getGestor().getId(), cliente.getId())));
			}
		} while (cliente.getGestor().equals(gestor_actual));
		return false;
	}
	
	// Consultar y actualizar mensajes
	public static void leerMensajes(Cliente cliente) {
		sc = new Scanner (System.in);
		List<Mensaje> mensajes = GeneralUtils.getMensajes(cliente);
		System.out.println("Estos son sus mensajes: ");
		if (mensajes.size() != 0) {
			mensajes.stream().sorted(Comparator.comparing(Mensaje::getFecha).reversed()) .forEach(m -> System.out.printf("%d. %s (%seído)\n",
					m.getId(), m.getConcepto() == null ? "(sin concepto)" : m.getConcepto(), m.isLeido() ? "L" : "No l"));
			boolean confirmacion = JOptionPane.showConfirmDialog(null, "¿Quiere consultar algún mensaje en específico?",
					"MENSAJES LEÍDOS", 0, 0, seleccion) == 0;
			if (confirmacion) {
				System.out.print("Introduzca el ID del mensaje: ");
				int id = sc.nextInt();
				if (mensajes.stream().noneMatch(m ->m.getId() == id)) System.err.println("No se ha reconocido el ID del mensaje");
				else mensajes.stream().filter(m ->m.getId() == id).forEach(m -> {
					if (!m.isLeido()) {
						m.setLeido(true);
						DatabaseUtils.updateDato(m, "leido", new ArrayList<>(Arrays.asList(m.isLeido() ? 1 : 0, m.getId())));
					}
					System.out.println(m);
				});
			}
		}
		else JOptionPane.showMessageDialog(null, "Todavía no le han enviado ningún mensaje", "SIN MENSAJES", 2);
	}
	
	// Crear cuenta
	public static void addCuenta(Cliente cliente) {
		Cuenta cuenta = new Cuenta(cliente);
		JOptionPane.showMessageDialog(null, """
				Tenga en cuenta que, al ser una aplicación de prueba, las cuentas presentan estas características:
				· Se crean con un saldo inicial de 1000€
				· Solo se podrá mover el dinero mediante transferencias""", "CUENTA CREADA", 1);
		if (DatabaseUtils.addDato(cuenta)) System.out.printf("Cuenta creada correctamente:\n%s\n",
				DatabaseUtils.getWithCondition("cuenta", "id = (SELECT MAX(id) FROM cuenta)", new ArrayList<>()).get(0));
	}
	
	// Eliminar cuenta
	public static void eliminarCuenta(Cliente cliente, List<Cuenta> cuentas) {
		sc = new Scanner (System.in);
		System.out.println("Sus cuentas son las siguientes:");
		cuentas.forEach(System.out::println);
		System.out.print("Seleccione la cuenta que desea eliminar: ");
		int id = sc.nextInt();
		if (cuentas.stream().noneMatch(c ->c.getId() == id)) System.err.println("No se ha reconocido el ID de la cuenta");
		else if (DatabaseUtils.deleteDato("cuenta", id)) System.out.println("Cuenta eliminada correctamente");
	}
	
	// Consultar transferencias
	public static void getTransferencias(Cliente cliente) {
		String subsel = "(SELECT id FROM cuenta WHERE cliente = ?)";
		List<Transferencia> transferencias = DatabaseUtils.getWithCondition("transferencia", "ordenante IN "+subsel+" OR beneficiario IN "+subsel,
				new ArrayList<>(Arrays.asList(cliente.getId(), cliente.getId())));
		sc = new Scanner (System.in);
		if (transferencias.size() != 0) {
			transferencias.stream().sorted(Comparator.comparing(Transferencia::getFecha).reversed()).forEach(t -> printTransferencia(t));
			boolean confirmacion = JOptionPane.showConfirmDialog(null, "¿Quiere consultar alguna transferencia en específico?",
					"MENSAJES LEÍDOS", 0, 0, seleccion) == 0;
			if (confirmacion) {
				System.out.print("Introduzca el ID de la transferencia: ");
				int id = sc.nextInt();
				if (transferencias.stream().noneMatch(t ->t.getId() == id)) System.err.println("No se ha reconocido el ID de la transferencia");
				else transferencias.stream().filter(t ->t.getId() == id).forEach(System.out::println);
			}
		}
		else JOptionPane.showMessageDialog(null, "Todavía no ha realizado ninguna transferencia", "SIN TRANSFERENCIAS", 2);
	}
	
	// Imprimir datos de la transferencia
	private static void printTransferencia(Transferencia t) {
		t = getTransferencia(t);
		Cuenta ordenante = t.getCuenta_ordenante(), beneficiario = t.getCuenta_beneficiaria();
		if (ordenante != null && beneficiario != null && ordenante.getCliente().equals(beneficiario.getCliente()))
			 System.out.printf("· Transferencia nº%d (entre tus cuentas): Cuenta nº%d -> %.2f€ -> Cuenta nº%d\n", t.getId(), ordenante.getId(),
					 t.getImporte(), beneficiario.getId());
		 else {
			 System.out.printf("· Transferencia nº%d: %s -> %.2f€ -> %s\n",
				t.getId(), ordenante == null ? "(Cuenta eliminada)" : datosCuenta(ordenante), t.getImporte(),
						beneficiario == null ? "(Cuenta eliminada)" : datosCuenta(beneficiario));
		 }
	}
	
	// Completar datos de la transferencia (retocar para que no dé error con tanto nulo)
	public static Transferencia getTransferencia(Transferencia t) {
		if (t.getCuenta_ordenante() != null) t.setCuenta_ordenante(getCuenta(t.getCuenta_ordenante()));
		if (t.getCuenta_beneficiaria() != null) t.setCuenta_beneficiaria(getCuenta(t.getCuenta_beneficiaria()));
		return t;
	}
	
	private static Cuenta getCuenta(Cuenta c) {
		c = DatabaseUtils.getOne("cuenta", c.getId());
		c.setCliente(DatabaseUtils.getOne("cliente", c.getCliente().getId()));
		return c;
	}
	
	private static String datosCuenta(Cuenta c) {
		return String.format("Cuenta nº%d (%s%s)", c.getId(), c.getCliente().getNombre(),
				c.getCliente().getApellido() != null ? " "+c.getCliente().getApellido() : "");
	}
	
	// Enviar una transferencia
	public static void sendTransferencia(Cliente cliente) {
		sc = new Scanner (System.in);
		List<Cuenta> cuentas_cliente = DatabaseUtils.getWithCondition("cuenta", "cliente = ?", new ArrayList<>(Arrays.asList(cliente.getId())));
		System.out.println("Estas son todas sus cuentas:");
		cuentas_cliente.forEach(System.out::println);
		System.out.print("Introduzca el ID de la cuenta ordenante: ");
		int id_ord = sc.nextInt();
		sc.nextLine();
		if (cuentas_cliente.stream().noneMatch(c -> c.getId() == id_ord))
			System.err.println("Ninguna de tus cuentas coincide con ese ID, por lo que se va a cancelar esta transferencia");
		else {
			List<Cuenta> resto_cuentas = DatabaseUtils.getWithCondition("cuenta", "id <> ?", new ArrayList<>(Arrays.asList(id_ord)));
			System.out.print("Introduzca el ID de la cuenta beneficiaria: ");
			int id_ben = sc.nextInt();
			sc.nextLine();
			if (id_ord == id_ben) System.err.println("No puedes hacer una transferencia a la misma cuenta.");
			else if (resto_cuentas.stream().noneMatch(c -> c.getId() == id_ben))
				System.err.println("No se ha encontrado ninguna cuenta con ese ID, por lo que se va a cancelar esta transferencia");
			else {
				Cuenta cuenta_ord = cuentas_cliente.stream().filter(c -> c.getId() == id_ord).collect(Collectors.toList()).get(0),
						cuenta_ben = resto_cuentas.stream().filter(c -> c.getId() == id_ben).collect(Collectors.toList()).get(0);
				System.out.print("Introduzca el importe de la transferencia: ");
				double importe = sc.nextDouble();
				sc.nextLine();
				importe = Math.round(importe * 100) / 100;
				if (importe > cuenta_ord.getSaldo())
					System.err.println("No se pudo realizar la transferencia ya que el importe es mayor que el saldo de la cuenta seleccionada");
				else {
					boolean confirmacion = JOptionPane.showConfirmDialog(null, "¿Quiere introducir un concepto a la transferencia?",
							"CONCEPTO DE LA TRANSFERENCIA", 0, 0, seleccion) == 0;
					String concepto = null;
					if (confirmacion) {
						System.out.print("Introduzca el concepto de la transferencia: ");
						concepto = sc.nextLine();
					}
					if (DatabaseUtils.sendTransferencia(new Transferencia(cuenta_ord, cuenta_ben, importe, concepto))) {
						String mensaje = cuenta_ord.getCliente().equals(cuenta_ben.getCliente()) ?
								String.format("""
										· El saldo de su cuenta ordenante es ahora de %.2f€
										· El saldo de su cuenta beneficiaria es ahora de %.2f€""", cuenta_ord.getSaldo(), cuenta_ben.getSaldo()) :
									String.format("El saldo de su cuenta es ahora de %.2f€", cuenta_ord.getSaldo());
						JOptionPane.showMessageDialog(null, mensaje, "Transferencia realizada correctamente", 1,
								new ImageIcon("src/main/java/com/sandra/dbBank/images/dinero.png"));
					}
				}
			}
		}
	}
}
