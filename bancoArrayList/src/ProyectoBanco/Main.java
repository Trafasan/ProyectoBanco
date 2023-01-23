package ProyectoBanco;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) {
		Scanner Entrada = new Scanner(System.in);
		String nombre, apellido, dni, usuario, password, correo, texto, concepto;
		int opcionA, opcionB, opcionC, nGestoresNuevos, guardarId = 0;
		int id_gestor, id_cliente, id_mensaje, id_transferencia, id_ordenante, id_beneficiario;
		LocalDateTime fecha;
		double saldo, importe;

		List<Gestor> gestores = new ArrayList<Gestor>();
		Gestor g1 = new Gestor("Helena", "Salas", "12345678A", "Dossoles", "1357", "correoG1@gmail.com", 1);
		gestores.add(g1);
		Gestor g2 = new Gestor("Jordi", "Fontanarrosa", "98765432Z", "Lumen124", "2468", "correoG2@gmail.com", 2);
		gestores.add(g2);

		List<Cliente> clientes = new ArrayList<Cliente>();
		Cliente c1 = new Cliente("Alejandro", "Planelles", "24680135G", "Elysium", "4728", "correoC1@gmail.com", 1, 1,
				1500);
		clientes.add(c1);
		Cliente c2 = new Cliente("María", "Verdú", "13579024V", "Darks013", "5039", "correoC2@gmail.com", 2, 2, 2000);
		clientes.add(c2);

		List<Mensajes> mensajes = new ArrayList<Mensajes>();
		Mensajes m1 = new Mensajes(1);
		mensajes.add(m1);

		List<Transferencias> transferencias = new ArrayList<Transferencias>();
		Transferencias t1 = new Transferencias(1);
		transferencias.add(t1);

		do {
			opcionA = Integer.parseInt(JOptionPane.showInputDialog("MENÚ PRINCIPAL\n1. Gestores\n2. Clientes\n"
					+ "3. Mensajes\n4. Transferencias\n5. Login\n6. Registo\n7. Salir\nInserte una opción: "));
			switch (opcionA) {
			case 1:
				do {
					opcionB = Integer.parseInt(JOptionPane.showInputDialog("MENÚ GESTOR\n1. Inserción de un gestor"
							+ "\n2. Inserción masiva de gestores con datos aleatorios\n3. Obtención de un gestor"
							+ "\n4. Obtención de todos los gestores\n5. Actualización de un gestor"
							+ "\n6. Eliminación de un gestor\n7. Volver atrás\nInserte una opción: "));
					switch (opcionB) {
					case 1:
						System.out.print("Inserte el nombre del nuevo gestor: ");
						nombre = Entrada.next();
						System.out.print("Inserte el apellido del nuevo gestor: ");
						apellido = Entrada.next();
						System.out.print("Inserte el DNI del nuevo gestor: ");
						dni = Entrada.next();
						System.out.print("Inserte el usuario del nuevo gestor: ");
						usuario = Entrada.next();
						System.out.print("Inserte la contraseña del nuevo gestor: ");
						password = Entrada.next();
						System.out.print("Inserte el correo del nuevo gestor: ");
						correo = Entrada.next();
						Gestor.insertarUnGestor(gestores, nombre, apellido, dni, usuario, password, correo);
						break;
					case 2:
						System.out.print("Inserte el número de gestores a añadir: ");
						nGestoresNuevos = Entrada.nextInt();
						Gestor.insertarVariosGestores(gestores, nGestoresNuevos);
						break;
					case 3:
						if (gestores.size() == 0) {
							System.out.println("ERROR\nNo existe ningún gestor");
						} else {
							System.out.print("Inserte el ID del gestor a obtener: ");
							id_gestor = Entrada.nextInt();
							id_gestor--;
							Gestor.obtencionUnGestor(gestores, id_gestor);
						}
						break;
					case 4:
						if (gestores.size() == 0) {
							System.out.println("ERROR\nNo existe ningún gestor");
						} else {
							Gestor.obtencionVariosGestores(gestores);
						}
						break;
					case 5:
						if (gestores.size() == 0) {
							System.out.print("ERROR\nNo existe ningún gestor");
						} else {
							boolean existeGestor;
							do {
								existeGestor = false;
								System.out.print("Inserte el ID del gestor a actualizar: ");
								id_gestor = Entrada.nextInt();
								for (int x = 0; x < gestores.size(); x++) {
									int comprobarGestor = gestores.get(x).getId_gestor();
									if (id_gestor == comprobarGestor) {
										existeGestor = true;
									}
								}
								if (existeGestor == false) {
									System.out.println("ERROR\nNo se ha encontrado el gestor");
								}
							} while (existeGestor == false);
							id_gestor--;
							do {
								opcionC = Integer.parseInt(JOptionPane.showInputDialog("Gestor " + (id_gestor + 1)
										+ "\nEliga el dato que quiera actualizar"
										+ "\n1. Nombre\n2. Apellido\n3. DNI\n4. Usuario\n5. Contraseña\n6. Correo\n7. Salir"));
								switch (opcionC) {
								case 1:
									System.out.print("Inserte el nuevo nombre del gestor " + (id_gestor + 1) + ": ");
									nombre = Entrada.next();
									Gestor.actualizarNombreGestor(gestores, id_gestor, nombre);
									System.out.println("Actualización realizada correctamente");
									break;
								case 2:
									System.out.print("Inserte el nuevo apellido del gestor " + (id_gestor + 1) + ": ");
									apellido = Entrada.next();
									Gestor.actualizarApellidoGestor(gestores, id_gestor, apellido);
									System.out.println("Actualización realizada correctamente");
									break;
								case 3:
									System.out.print("Inserte el DNI del gestor " + (id_gestor + 1) + ": ");
									dni = Entrada.next();
									Gestor.actualizarDniGestor(gestores, id_gestor, dni);
									System.out.println("Actualización realizada correctamente");
									break;
								case 4:
									System.out.print("Inserte el usuario del gestor " + (id_gestor + 1) + ": ");
									usuario = Entrada.next();
									Gestor.actualizarUsuarioGestor(gestores, id_gestor, usuario);
									System.out.println("Actualización realizada correctamente");
									break;
								case 5:
									System.out.print("Inserte la contraseña del gestor " + (id_gestor + 1) + ": ");
									password = Entrada.next();
									Gestor.actualizarPasswordGestor(gestores, id_gestor, password);
									System.out.println("Actualización realizada correctamente");
									break;
								case 6:
									System.out.print("Inserte el correo del gestor " + (id_gestor + 1) + ": ");
									correo = Entrada.next();
									Gestor.actualizarCorreoGestor(gestores, id_gestor, correo);
									System.out.println("Actualización realizada correctamente");
									break;
								case 7:
									break;
								default:
									System.out.println("ERROR\nLa opción elegida no existe");
								}
							} while (opcionC != 7);
						}
						break;
					case 6:
						if (gestores.size() == 0) {
							System.out.println("ERROR\nNo existe ningún gestor");
						} else {
							System.out.print("Inserte el ID del gestor a eliminar: ");
							id_gestor = Entrada.nextInt();
							id_gestor--;
							Gestor.eliminarGestor(gestores, id_gestor);
						}
						break;
					case 7:
						break;
					default:
						System.out.println("ERROR\nLa opción elegida no existe");
					}
				} while (opcionB != 7);
				break;
			case 2:
				do {
					opcionB = Integer.parseInt(JOptionPane
							.showInputDialog("MENÚ CLIENTE" + "\n1. Inserción de un cliente\n2. Obtención de un cliente"
									+ "\n3. Obtención de todos los clientes\n4. Actualización de un cliente"
									+ "\n5. Eliminación de un cliente\n6. Volver atrás\nInserte una opción: "));

					switch (opcionB) {
					case 1:
						System.out.print("Inserte el nombre del nuevo cliente: ");
						nombre = Entrada.next();
						System.out.print("Inserte el apellido del nuevo cliente: ");
						apellido = Entrada.next();
						System.out.print("Inserte el DNI del nuevo cliente: ");
						dni = Entrada.next();
						System.out.print("Inserte el usuario del nuevo cliente: ");
						usuario = Entrada.next();
						System.out.print("Inserte la contraseña del nuevo cliente: ");
						password = Entrada.next();
						System.out.print("Inserte el correo del nuevo cliente: ");
						correo = Entrada.next();
						System.out.print("Inserte el ID del gestor del nuevo cliente: ");
						id_gestor = Entrada.nextInt();
						System.out.print("Inserte el saldo del nuevo cliente: ");
						saldo = Entrada.nextDouble();
						Cliente.insertarCliente(clientes, nombre, apellido, dni, usuario, password, correo, id_gestor,
								saldo);
						break;
					case 2:
						if (clientes.size() == 0) {
							System.out.println("ERROR\nNo existe ningún cliente");
						} else {
							System.out.print("Inserte el ID del cliente a obtener: ");
							id_cliente = Entrada.nextInt();
							id_cliente--;
							Cliente.obtencionUnCliente(clientes, id_cliente);
						}
						break;
					case 3:
						if (clientes.size() == 0) {
							System.out.println("ERROR\nNo existe ningún cliente");
						} else {
							Cliente.obtencionVariosClientes(clientes);
						}
						break;
					case 4:
						if (clientes.size() == 0) {
							System.out.println("ERROR\nNo existe ningún cliente");
						} else {
							boolean existeCliente;
							do {
								existeCliente = false;
								System.out.print("Inserte el ID del cliente a actualizar: ");
								id_cliente = Entrada.nextInt();
								for (int x = 0; x < clientes.size(); x++) {
									int comprobarCliente = clientes.get(x).getId_cliente();
									if (id_cliente == comprobarCliente) {
										existeCliente = true;
									}
								}
								if (existeCliente == false) {
									System.out.println("ERROR\nNo se ha encontrado el cliente");
								}
							} while (existeCliente == false);
							id_cliente--;
							do {
								opcionC = Integer.parseInt(JOptionPane.showInputDialog("Cliente " + (id_cliente + 1)
										+ "\nEliga el dato que quiera actualizar"
										+ "\n1. Nombre\n2. Apellido\n3. DNI\n4. Usuario\n5. Contraseña\n6. Correo\n7. Id del gestor\n8. Saldo\n9. Salir"));
								switch (opcionC) {
								case 1:
									System.out.print("Inserte el nuevo nombre del cliente " + (id_cliente + 1) + ": ");
									nombre = Entrada.next();
									Cliente.actualizarNombreCliente(clientes, id_cliente, nombre);
									System.out.println("Actualización realizada correctamente");
									break;
								case 2:
									System.out
											.print("Inserte el nuevo apellido del cliente " + (id_cliente + 1) + ": ");
									apellido = Entrada.next();
									Cliente.actualizarApellidoCliente(clientes, id_cliente, apellido);
									System.out.println("Actualización realizada correctamente");
									break;
								case 3:
									System.out.print("Inserte el DNI del cliente " + (id_cliente + 1) + ": ");
									dni = Entrada.next();
									Cliente.actualizarDniCliente(clientes, id_cliente, dni);
									System.out.println("Actualización realizada correctamente");
									break;
								case 4:
									System.out.print("Inserte el usuario del cliente " + (id_cliente + 1) + ": ");
									usuario = Entrada.next();
									Cliente.actualizarUsuarioCliente(clientes, id_cliente, usuario);
									System.out.println("Actualización realizada correctamente");
									break;
								case 5:
									System.out.print("Inserte la contraseña del cliente " + (id_cliente + 1) + ": ");
									password = Entrada.next();
									Cliente.actualizarPasswordCliente(clientes, id_cliente, password);
									System.out.println("Actualización realizada correctamente");
									break;
								case 6:
									System.out.print("Inserte el correo del cliente " + (id_cliente + 1) + ": ");
									correo = Entrada.next();
									Cliente.actualizarCorreoCliente(clientes, id_cliente, correo);
									System.out.println("Actualización realizada correctamente");
									break;
								case 7:
									System.out.print("Inserte el id del gestor del cliente " + (id_cliente + 1) + ": ");
									id_gestor = Entrada.nextInt();
									Cliente.actualizarIdGestorCliente(clientes, id_cliente, id_gestor);
									System.out.println("Actualización realizada correctamente");
									break;
								case 8:
									System.out.print("Inserte el saldo del cliente " + (id_cliente + 1) + ": ");
									saldo = Entrada.nextDouble();
									Cliente.actualizarSaldoCliente(clientes, id_cliente, saldo);
									System.out.println("Actualización realizada correctamente");
									break;
								case 9:
									break;
								default:
									System.out.println("ERROR\nLa opción elegida no existe");
								}
							} while (opcionC != 9);
						}
						break;
					case 5:
						if (clientes.size() == 0) {
							System.out.println("ERROR\nNo existe ningún cliente");
						} else {
							System.out.print("Inserte el id del cliente a eliminar: ");
							id_cliente = Entrada.nextInt();
							id_cliente--;
							Cliente.eliminarCliente(clientes, id_cliente);
						}
						break;
					case 6:
						break;
					default:
						System.out.println("ERROR\nLa opción elegida no existe");
					}
				} while (opcionB != 6);
				break;
			case 3:
				do {
					opcionB = Integer.parseInt(JOptionPane.showInputDialog(
							"MENÚ MENSAJES" + "\n1. Obtención de un mensaje" + "\n2. Obtención de todos los mensajes"
									+ "\n3. Envío de un mensaje" + "\n4. Volver atrás" + "\nInserte una opción: "));

					switch (opcionB) {
					case 1:
						System.out.print("Inserte el ID del mensaje a obtener: ");
						id_mensaje = Entrada.nextInt();
						id_mensaje--;
						Mensajes.obtencionUnMensaje(mensajes, id_mensaje);
						break;
					case 2:
						Mensajes.obtencionVariosClientes(mensajes);
						break;
					case 3:
						boolean existeGestor;
						do {
							existeGestor = false;
							System.out.print("Inserte el ID del gestor remitente: ");
							id_gestor = Entrada.nextInt();
							for (int x = 0; x < gestores.size(); x++) {
								int comprobarGestor = gestores.get(x).getId_gestor();
								if (id_gestor == comprobarGestor) {
									existeGestor = true;
								}
							}
							if (existeGestor == false) {
								System.out.println("ERROR\nNo se ha encontrado el gestor");
							}
						} while (existeGestor == false);

						boolean existeCliente;
						do {
							existeCliente = false;
							System.out.print("Inserte el ID del cliente destinatario: ");
							id_cliente = Entrada.nextInt();
							for (int x = 0; x < clientes.size(); x++) {
								int comprobarCliente = clientes.get(x).getId_cliente();
								if (id_cliente == comprobarCliente) {
									existeCliente = true;
								}
							}
							if (existeCliente == false) {
								System.out.println("ERROR\nNo se ha encontrado el cliente");
							}
						} while (existeCliente == false);

						System.out.print("Inserte el texto del mensaje: ");
						texto = Entrada.next();
						fecha = LocalDateTime.now();
						Mensajes.enviarMensaje(mensajes, id_gestor, id_cliente, texto, fecha);
						break;
					case 4:
						break;
					default:
						System.out.println("ERROR\nLa opción elegida no existe");
					}
				} while (opcionB != 4);
				break;
			case 4:
				do {
					opcionB = Integer.parseInt(JOptionPane.showInputDialog("MENÚ TRANSFERENCIAS"
							+ "\n1. Obtención de una transferencia" + "\n2. Obtención de todas las transferencias"
							+ "\n3. Envío de una transferencia" + "\n4. Volver atrás" + "\nInserte una opción: "));

					switch (opcionB) {
					case 1:
						System.out.print("Inserte el ID de la transferencia a obtener: ");
						id_transferencia = Entrada.nextInt();
						id_transferencia--;
						Transferencias.obtencionUnaTransferencia(transferencias, id_transferencia);
						break;
					case 2:
						Transferencias.obtencionVariasTransferencias(transferencias);
						break;
					case 3:
						boolean existeOrdenante;
						do {
							existeOrdenante = false;
							System.out.print("Inserte el ID del cliente ordenante: ");
							id_ordenante = Entrada.nextInt();
							for (int x = 0; x < clientes.size(); x++) {
								int comprobarOrdenante = clientes.get(x).getId_cliente();
								if (id_ordenante == comprobarOrdenante) {
									existeOrdenante = true;
								}
							}
							if (existeOrdenante == false) {
								System.out.println("ERROR\nNo se ha encontrado el cliente");
							}
						} while (existeOrdenante == false);

						boolean existeBeneficiario;
						do {
							existeBeneficiario = false;
							do {
								System.out.print("Inserte el ID del cliente beneficiario: ");
								id_beneficiario = Entrada.nextInt();
								if (id_beneficiario == id_ordenante) {
									System.out
											.println("ERROR\nNo se puede hacer la transferencia al cliente ordenante");
								}
							} while (id_beneficiario == id_ordenante);
							for (int x = 0; x < clientes.size(); x++) {
								int comprobarBeneficiario = clientes.get(x).getId_cliente();
								if (id_beneficiario == comprobarBeneficiario) {
									existeBeneficiario = true;
								}
							}
							if (existeBeneficiario == false) {
								System.out.println("ERROR\nNo se ha encontrado el cliente");
							}
						} while (existeBeneficiario == false);

						double importeMax = clientes.get(id_ordenante - 1).getSaldo();
						do {
							System.out.print("Inserte el importe: ");
							importe = Entrada.nextDouble();
							if (importe > importeMax) {
								System.out.println("ERROR\nEl importe no puede ser mayor a su saldo\nSu saldo es "
										+ importeMax + "€");
							}
						} while (importe > importeMax);

						System.out.print("Inserte el concepto: ");
						concepto = Entrada.next();
						fecha = LocalDateTime.now();
						Transferencias.enviarTransferencia(transferencias, id_ordenante, id_beneficiario, importe,
								concepto, fecha, clientes);
						break;
					case 4:
						break;
					default:
						System.out.println("ERROR\nLa opción elegida no existe");
					}
				} while (opcionB != 4);
				break;
			case 5:
				do {
					opcionC = Integer.parseInt(JOptionPane
							.showInputDialog("LOGIN\nEliga su tipo de cuenta:\n1. Gestor\n2. Cliente\n3. Salir"));
					boolean existeUsuario;
					switch (opcionC) {
					case 1:
						do {
							existeUsuario = false;
							System.out.print("Inserte su usuario: ");
							usuario = Entrada.next();
							for (int x = 0; x < gestores.size(); x++) {
								String comprobarUsuario = gestores.get(x).getUsuario();
								if (usuario.equals(comprobarUsuario)) {
									existeUsuario = true;
									guardarId = x;
								}
							}
							if (existeUsuario == false) {
								System.out.println("ERROR\nNo se ha encontrado el nombre de usuario");
							}
						} while (existeUsuario == false);

						do {
							System.out.print("Inserte su contraseña: ");
							password = Entrada.next();
							if (!password.equals(gestores.get(guardarId).getPassword())) {
								System.out.println("Contraseña incorrecta");
							}
						} while (!password.equals(gestores.get(guardarId).getPassword()));

						System.out.println("Bienvenid@ " + gestores.get(guardarId).getNombre());
						break;
					case 2:
						do {
							existeUsuario = false;
							System.out.print("Inserte su usuario: ");
							usuario = Entrada.next();
							for (int x = 0; x < clientes.size(); x++) {
								String comprobarUsuario = clientes.get(x).getUsuario();
								if (usuario.equals(comprobarUsuario)) {
									existeUsuario = true;
									guardarId = x;
								}
							}
							if (existeUsuario == false) {
								System.out.println("ERROR\nNo se ha encontrado el nombre de usuario");
							}
						} while (existeUsuario == false);

						do {
							System.out.print("Inserte su contraseña: ");
							password = Entrada.next();
							if (!password.equals(clientes.get(guardarId).getPassword())) {
								System.out.println("Contraseña incorrecta");
							}
						} while (!password.equals(clientes.get(guardarId).getPassword()));

						System.out.println("Bienvenid@, " + clientes.get(guardarId).getNombre());
						break;
					case 3:
						break;
					default:
						System.out.println("ERROR\nLa opción elegida no existe");
					}
				} while (opcionC != 3);
				break;
			case 6:
				do {
					opcionC = Integer.parseInt(JOptionPane.showInputDialog("REGISTRO"
							+ "\nEliga el tipo de cuenta que desea crear:\n1. Gestor\n2. Cliente\n3. Salir"));
					boolean existeUsuario, comprobarPassword;
					String passwordIgual;
					switch (opcionC) {
					case 1:
						System.out.println("GESTOR");
						System.out.print("Inserte su nombre: ");
						nombre = Entrada.next();
						System.out.print("Inserte su apellido: ");
						apellido = Entrada.next();
						System.out.print("Inserte su DNI: ");
						dni = Entrada.next();
						do {
							existeUsuario = false;
							System.out.print("Inserte un usuario: ");
							usuario = Entrada.next();
							for (int x = 0; x < gestores.size(); x++) {
								String comprobarUsuario = gestores.get(x).getUsuario();
								if (usuario.equals(comprobarUsuario)) {
									existeUsuario = true;
								}
							}
							if (existeUsuario == true) {
								System.out.println("ERROR\nYa existe ese nombre de usuario");
							}
						} while (existeUsuario == true);

						do {
							comprobarPassword = false;
							System.out.print("Inserte una contraseña: ");
							password = Entrada.next();
							System.out.print("Inserte de nuevo la contraseña: ");
							passwordIgual = Entrada.next();

							if (password.equals(passwordIgual)) {
								comprobarPassword = true;
							}
							if (comprobarPassword == false) {
								System.out.println("ERROR\nLas contraseñas no coinciden");
							}
						} while (comprobarPassword == false);

						System.out.print("Inserte su correo: ");
						correo = Entrada.next();
						Gestor.insertarUnGestor(gestores, nombre, apellido, dni, usuario, password, correo);

						System.out.println("Bienvenid@ " + nombre);
						break;
					case 2:
						System.out.println("CLIENTE");
						System.out.print("Inserte su nombre: ");
						nombre = Entrada.next();
						System.out.print("Inserte su apellido: ");
						apellido = Entrada.next();
						System.out.print("Inserte su DNI: ");
						dni = Entrada.next();
						do {
							existeUsuario = false;
							System.out.print("Inserte un usuario: ");
							usuario = Entrada.next();
							for (int x = 0; x < clientes.size(); x++) {
								String comprobarUsuario = clientes.get(x).getUsuario();
								if (usuario.equals(comprobarUsuario)) {
									existeUsuario = true;
								}
							}
							if (existeUsuario == true) {
								System.out.println("ERROR\nYa existe ese nombre de usuario");
							}
						} while (existeUsuario == true);

						do {
							comprobarPassword = false;
							System.out.print("Inserte una contraseña: ");
							password = Entrada.next();
							System.out.print("Inserte de nuevo la contraseña: ");
							passwordIgual = Entrada.next();

							if (password.equals(passwordIgual)) {
								comprobarPassword = true;
							}
							if (comprobarPassword == false) {
								System.out.println("ERROR\nLas contraseñas no coinciden");
							}
						} while (comprobarPassword == false);

						System.out.print("Inserte su correo: ");
						correo = Entrada.next();
						id_gestor = 1;
						saldo = 0;

						Cliente.insertarCliente(clientes, nombre, apellido, dni, usuario, password, correo, id_gestor,
								saldo);

						System.out.println("Bienvenid@ " + nombre);
						break;
					case 3:
						break;
					default:
						System.out.println("ERROR\nLa opción elegida no existe");
					}
				} while (opcionC != 3);
				break;
			case 7:
				break;
			default:
				System.out.println("ERROR\nLa opción elegida no existe");
			}
		} while (opcionA != 7);

		Entrada.close();
	}

}
