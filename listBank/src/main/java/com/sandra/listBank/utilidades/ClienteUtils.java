package com.sandra.listBank.utilidades;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.sandra.listBank.entidades.Cliente;
import com.sandra.listBank.entidades.Cuenta;
import com.sandra.listBank.entidades.Gestor;
import com.sandra.listBank.entidades.Mensaje;
import com.sandra.listBank.entidades.Transferencia;

public class ClienteUtils {
	private static Scanner sc;
	
	// Obtención de los clientes de un gestor
	public static void getClientesGestor(Gestor g) {
		if (Cliente.clientes.stream().noneMatch(c -> c.getGestor().equals(g))) System.err.println("Tu lista de cliestes está vacía");
		else Cliente.clientes.stream().filter(c -> c.getGestor().equals(g)).forEach(System.out::println);
	}
	
	// Actualización de los atributos específicos del cliente
	public static boolean updateGestor(Cliente cliente) {
		sc = new Scanner (System.in);
		List<Gestor> gestores_restantes = Gestor.gestores.stream().filter(g -> !g.equals(cliente.getGestor())).collect(Collectors.toList());
		final Gestor gestor_actual = cliente.getGestor();
		int n_gestor;
		int contador = 1;
		System.out.println("Aquí tiene la lista de gestores:");
		gestores_restantes.forEach(g -> System.out.printf("%d. %s %s\n", contador, g.getNombre(), g.getApellido()));
		do {
			System.out.print("Introduzca el número de su nuevo gestor (si no quiere actualizar introduzca '0'): ");
			n_gestor = sc.nextInt();
			if (n_gestor == 0) break;
			else if (n_gestor > gestores_restantes.size()) System.err.println("Ese gestor no existe");
			else {
				cliente.setGestor(gestores_restantes.get(n_gestor-1));
				gestores_restantes.get(n_gestor-1).getClientes().add(cliente);
				gestor_actual.getClientes().remove(cliente);
			}
		} while (cliente.getGestor().equals(gestor_actual));
		return n_gestor != 0;
	}
	
	// Consultar y actualizar mensajes
	public static void mensajesLeidos(Cliente cliente) {
		sc = new Scanner (System.in);
		System.out.println("Estos son sus mensajes: ");
		cliente.getMensajes().stream().sorted(Comparator.comparing(Mensaje::getFecha).reversed())
		.forEach(m -> System.out.printf("%d. %s (%seído)\n", m.getId(), m.getConcepto(), m.isLeido() ? "L" : "No l"));
		if (cliente.getMensajes().size() != 0) {
			boolean confirmacion = JOptionPane.showConfirmDialog(null, "¿Quiere consultar algún mensaje en específico?",
					"MENSAJES LEÍDOS", 0, 0, new ImageIcon("src/main/java/com/sandra/listBank/images/seleccion.png")) == 0;
			if (confirmacion) {
				System.out.print("Introduzca el ID del mensaje: ");
				int id = sc.nextInt();
				if (cliente.getMensajes().stream().noneMatch(m ->m.getId() == id)) System.err.println("No se ha reconocido el ID del mensaje");
				else {
					cliente.getMensajes().stream().filter(m ->m.getId() == id).forEach(m ->{
						m.setLeido(true);
						System.out.println(m);
					});
				}
			}
		}
	}
	
	// Crear cuenta
	public static void addCuenta(Cliente cliente) {
		Cuenta cuenta = new Cuenta(cliente);
		JOptionPane.showMessageDialog(null, """
				Tenga en cuenta que, al ser una aplicación de prueba, las cuentas presentan estas características:
				· Se crean con un saldo inicial de 1000€
				· Solo se podrá mover el dinero mediante transferencias""", "CUENTA CREADA", 1);
		System.out.printf("Cuenta creada correctamente.\n%s\n", cuenta);
	}
	
	// Eliminar cuenta
	public static void eliminarCuenta(Cliente cliente) {
		sc = new Scanner (System.in);
		System.out.println("Sus cuentas son las siguientes:");
		cliente.getCuentas().forEach(c -> System.out.printf("Cuenta nº%d (Saldo: %.2f€)\n", c.getId(), c.getSaldo()));
		System.out.print("Seleccione la cuenta que desea eliminar: ");
		int id = sc.nextInt();
		if (cliente.getCuentas().stream().noneMatch(c ->c.getId() == id)) System.err.println("No se ha reconocido el ID de la cuenta");
		else cliente.setCuentas(cliente.getCuentas().stream().filter(c -> c.getId() != id).collect(Collectors.toList()));
	}
	
	// Consultar transferencias
	public static void getTransferencias(Cliente cliente) {
		sc = new Scanner (System.in);
		cliente.getTransferencias().stream().sorted(Comparator.comparing(Transferencia::getFecha).reversed()).forEach(t -> {
			 if (t.getCuenta_ordenante().getCliente().equals(t.getCuenta_beneficiaria().getCliente()))
				 System.out.printf("Nº%d: Cuenta nº%d -> %.2f€ -> Cuenta nº%d\n",
					t.getId(), t.getCuenta_ordenante().getId(), t.getImporte(), t.getCuenta_beneficiaria().getId());
			 else {
				 Cliente cliente_ordenante = t.getCuenta_ordenante().getCliente(), cliente_beneficiario = t.getCuenta_beneficiaria().getCliente();
				 System.out.printf("Nº%d: Cuenta nº%d (%s %s) -> %.2f€ -> Cuenta nº%d (%s %s)\n",
					t.getId(), t.getCuenta_ordenante().getId(), cliente_ordenante.getNombre(), cliente_ordenante.getApellido(),
					t.getImporte(), t.getCuenta_beneficiaria().getId(), cliente_beneficiario.getNombre(), cliente_beneficiario.getApellido());
			 }
		});
		boolean confirmacion = JOptionPane.showConfirmDialog(null, "¿Quiere consultar alguna transferencia en específico?",
				"MENSAJES LEÍDOS", 0, 0, new ImageIcon("src/main/java/com/sandra/listBank/images/seleccion.png")) == 0;
		if (confirmacion) {
			System.out.print("Introduzca el ID de la transferencia: ");
			int id = sc.nextInt();
			if (cliente.getTransferencias().stream().noneMatch(t ->t.getId() == id))
				System.err.println("No se ha reconocido el ID de la transferencia");
			else cliente.getTransferencias().stream().filter(t ->t.getId() == id).forEach(System.out::println);
		}
	}
	
	// Enviar una transferencia
	public static void sendTransferencia(Cliente cliente) {
		sc = new Scanner (System.in);
		System.out.print("Introduzca el ID de la cuenta ordenante: ");
		int id_ord = sc.nextInt();
		sc.nextLine();
		if (cliente.getCuentas().stream().noneMatch(c -> c.getId() == id_ord))
			System.err.println("Ninguna de tus cuentas coincide con ese ID, por lo que se va a cancelar esta transferencia");
		else {
			System.out.print("Introduzca el ID de la cuenta beneficiaria: ");
			int id_ben = sc.nextInt();
			sc.nextLine();
			if (id_ord == id_ben) System.err.println("No puedes hacer una transferencia a la misma cuenta.");
			else if (Cuenta.cuentas.stream().noneMatch(c -> c.getId() == id_ben))
				System.err.println("No se ha encontrado ninguna cuenta con ese ID, por lo que se va a cancelar esta transferencia");
			else {
				Cuenta cuenta_ord = cliente.getCuentas().stream().filter(c -> c.getId() == id_ord).collect(Collectors.toList()).get(0),
						cuenta_ben = Cuenta.cuentas.stream().filter(c -> c.getId() == id_ben).collect(Collectors.toList()).get(0);
				System.out.print("Introduzca el importe de la transferencia: ");
				double importe = sc.nextDouble();
				sc.nextLine();
				importe = Math.round(importe * 100) / 100;
				if (importe > cuenta_ord.getSaldo())
					System.err.println("No se pudo realizar la transferencia ya que el importe es mayor que el saldo de la cuenta seleccionada");
				else {
					boolean confirmacion = JOptionPane.showConfirmDialog(null, "¿Quiere introducir un concepto a la transferencia?",
							"CONCEPTO DE LA TRANSFERENCIA", 0, 0, new ImageIcon("src/main/java/com/sandra/listBank/images/seleccion.png")) == 0;
					String concepto = null;
					if (confirmacion) {
						System.out.print("Introduzca el concepto de la transferencia: ");
						concepto = sc.nextLine();
					}
					new Transferencia(cuenta_ord, cuenta_ben, importe, concepto);
					String mensaje = cuenta_ord.getCliente().equals(cuenta_ben.getCliente()) ?
							String.format("""
									· El saldo de su cuenta ordenante es ahora de %.2f€
									· El saldo de su cuenta beneficiaria es ahora de %.2f€""", cuenta_ord.getSaldo(), cuenta_ben.getSaldo()) :
								String.format("El saldo de su cuenta es ahora de %.2f€", cuenta_ord.getSaldo());
					JOptionPane.showMessageDialog(null, mensaje, "Transferencia realizada correctamente", 1,
							new ImageIcon("src/main/java/com/sandra/listBank/images/dinero.png"));
				}
			}
		}
	}
}
