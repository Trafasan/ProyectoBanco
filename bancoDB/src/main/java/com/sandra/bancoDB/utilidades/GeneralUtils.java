package com.sandra.bancoDB.utilidades;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.sandra.bancoDB.entidades.Cliente;
import com.sandra.bancoDB.entidades.Gestor;
import com.sandra.bancoDB.entidades.Mensaje;
import com.sandra.bancoDB.entidades.Transferencia;

public class GeneralUtils {
	
	/**
	 * Método para obtener el ID que se pide en la ventana.
	 * @param message     Texto de la ventana
	 * @param title       Título de la ventana
	 * @param messageType Icono de la ventana
	 * @return {@code int} que representa el ID. Si no se introduce un entero, el método devuelve -1.
	 */
	public static int getId(String message, String title, int messageType) {
		try {
			return Integer.parseInt(JOptionPane.showInputDialog(null, message, title, messageType));
		} catch (NumberFormatException e) {
			return -1;
		}
	}
	
	/**
	 * Método que comprueba que la contraseña sea del formato correcto
	 * @param message     Texto de la ventana
	 * @param title       Título de la ventana
	 * @param messageType Icono de la ventana
	 * @return La contraseña validada según el formato establecido en el método
	 *         {@link com.sandra.bancoDB.utilidades.UIGestorCliente#passwordIncorrecto(String)}
	 */
	public static String nuevoPassword(String message, String title, int messageType) {
		String password;
		boolean passwordIncorrecto;
		do {
			password = JOptionPane.showInputDialog(null, message, title, messageType);
			passwordIncorrecto = passwordIncorrecto(password);
			if (password == null) return "";
			else if (passwordIncorrecto) System.err.println("La contraseña debe ser un número de 4 dígitos");
		} while (passwordIncorrecto);
		return password;
	}

	/**
	 * Método que establece el formato de la contraseña
	 * @param password La contraseña generada en el método
	 *                 {@link com.sandra.bancoDB.utilidades.UIGestorCliente#nuevoPassword(String, String, int)}
	 * @return {@code true} si el formato de la contraseña coindice con el establecido, {@code false} en caso contrario
	 */
	private static boolean passwordIncorrecto(String password) {
		final int LENGTH_PASSWORD = 4;
		try {
			Integer.parseInt(password); // Salta el error NumberFormatException si la contraseña no se puede convertir a entero
			if (password.length() != LENGTH_PASSWORD) return true;
			else return false;
		} catch (NumberFormatException e) {
			return true;
		}
	}

	/**
	 * Método que genera un gestor que se añadirá posteriormente a la base de datos
	 * @return {@code Gestor} con todos los datos establecidos excepto su ID
	 */
	public static Gestor addGestor() {
		String nombre = JOptionPane.showInputDialog("Introduzca el nombre del nuevo gestor: ");
		String apellido = JOptionPane.showInputDialog("Introduzca el apellido del nuevo gestor: ");
		String dni = JOptionPane.showInputDialog("Introduzca el DNI del nuevo gestor: ");
		String usuario = JOptionPane.showInputDialog("Introduzca el usuario del nuevo gestor: ");
		String password = nuevoPassword("Introduzca la contraseña del nuevo gestor", "Input", 3);
		String correo = JOptionPane.showInputDialog("Introduzca el correo del nuevo gestor: ");
		return new Gestor(nombre, apellido, dni, usuario, password, correo);
	}

	/**
	 * Método que genera un ID de un gestor aleatorio
	 * @param id_gestores {@code List} de los ID de todos los gestores en la base de datos
	 * @return {@code int} entre el ID mínimo y el ID máximo de la lista de IDs
	 */
	public static int getRandomId_Gestor() {
		List<Integer> listaIds = DatabaseUtils.getId(DatabaseUtils.datosGestores());
		int idMax = Collections.max(listaIds);
		int idMin = Collections.min(listaIds);
		int randomId;
		do randomId = new Random().nextInt(idMax-idMin+1)+idMin; while (!listaIds.contains(randomId));
		return randomId;
	}
	
	/**
	 * Método que genera un cliente que se añadirá posteriormente a la base de datos
	 * @param id_gestores {@code List} de los ID de todos los gestores en la base de datos
	 * @return {@code Cliente} con todos los datos establecidos excepto su ID
	 */
	public static Cliente addCliente() {
		int id_gestor = getRandomId_Gestor();
		String nombre = JOptionPane.showInputDialog("Inserte el nombre del nuevo cliente: ");
		String apellido = JOptionPane.showInputDialog("Inserte el apellido del nuevo cliente: ");
		String dni = JOptionPane.showInputDialog("Inserte el DNI del nuevo cliente: ");
		String usuario = JOptionPane.showInputDialog("Inserte el usuario del nuevo cliente: ");
		String password = nuevoPassword("Introduzca la contraseña del nuevo cliente", "Input", 3);
		String correo = JOptionPane.showInputDialog("Inserte el correo del nuevo cliente: ");
		return new Cliente(id_gestor, nombre, apellido, dni, usuario, password, correo, 0);
	}
	
	/**
	 * Método que genera un mensaje que se añadirá posteriormente a la base de datos
	 * @param id_gestor Id del gestor remitente del mensaje
	 * @param id_cliente Id del cliente destinatario del mensaje
	 * @return {@code Mensaje} con todos los datos establecidos excepto su ID
	 */
	public static Mensaje addMensaje(int id_gestor, int id_cliente) {
		return new Mensaje(id_gestor, id_cliente, JOptionPane.showInputDialog("Inserte el texto del nuevo mensaje: "),
				new Timestamp(System.currentTimeMillis()));
	}
	
	/**
	 * Método que genera una transferencia que se añadirá posteriormente a la base de datos
	 * @param id_ordenante Id del cliente ordenante de la transferencia
	 * @param id_beneficiario Id del cliente beneficiario de la transferencia
	 * @param importe Importe de la transferencia
	 * @return {@code Transferencia} con todos los datos establecidos excepto su ID
	 */
	public static Transferencia addTransferencia(int id_ordenante, int id_beneficiario, double importe) {
		String concepto = JOptionPane.showInputDialog("Inserte el texto de la nueva transferencia:");
		Timestamp fecha = new Timestamp(System.currentTimeMillis());
		return new Transferencia(id_ordenante, id_beneficiario, importe, concepto, fecha);
	}
	
	/**
	 * Método que genera un gestor en el menú registro que se añadirá posteriormente a la base de datos
	 * @param usuario Usuario que el gestor ha creado previamente
	 * @param password Contraseña que el gestor ha creado previamente
	 * @return {@code Gestor} con todos los datos establecidos excepto su ID
	 */
	public static Gestor addGestor(String usuario, String password) {
		String nombre = JOptionPane.showInputDialog("Introduzca su nombre: ");
		String apellido = JOptionPane.showInputDialog("Introduzca su(s) apellido(s): ");
		String dni = JOptionPane.showInputDialog("Introduzca su DNI: ");
		String correo = JOptionPane.showInputDialog("Introduzca su correo: ");
		return new Gestor(nombre, apellido, dni, usuario, password, correo);
	}
	
	/**
	 * Método que genera un cliente en el menú registro que se añadirá posteriormente a la base de datos
	 * @param id_gestor Id del gestor generado previamente
	 * @param usuario Usuario que el cliente ha creado previamente
	 * @param password Contraseña que el cliente ha creado previamente
	 * @return {@code Cliente} con todos los datos establecidos excepto su ID
	 */
	public static Cliente addCliente(int id_gestor, String usuario, String password) {
		String nombre = JOptionPane.showInputDialog("Introduzca su nombre: ");
		String apellido = JOptionPane.showInputDialog("Introduzca su(s) apellido(s): ");
		String dni = JOptionPane.showInputDialog("Introduzca su DNI: ");
		String correo = JOptionPane.showInputDialog("Introduzca su correo: ");
		return new Cliente(id_gestor, nombre, apellido, dni, usuario, password, correo, 0);
	}
	
	public static <T> void imprimirDato(List<T> lista, String texto) {
		T dato = DatabaseUtils.getDato(lista, GeneralUtils.getId("Introduzca el ID de"+texto+" a obtener: ", "Input", 3));
		System.out.println(dato != null ? dato : "No existen coincidencias con en ID introducido");
	}

	/**
	 * Método que actualiza un dato del gestor.
	 * @param texto Dato que se quiere actualizar
	 * @param id ID del gestor
	 * @return {@code Gestor} con los parámetros de ID y del dato actualizado.
	 */
	public static Gestor updateGestor(String texto, int id) {
		return new Gestor(id, JOptionPane.showInputDialog(null, "Introduzca " + texto + ":", "ACTUALIZACIÓN DEL GESTOR " + id, 1));
	}
	
	/**
	 * Método exclusivo para actualizar la contraseña del gestor
	 * @param password La contraseña generada en el método
	 *                 {@link com.sandra.bancoDB.utilidades.UIGestorCliente#nuevoPassword(String, String, int)}
	 * @param id ID del gestor
	 * @return {@code Gestor} con los parámetros de ID y de la contraseña actualizada.
	 */
	public static Gestor updatePasswordGestor(String password, int id) {
		return new Gestor(id, password);
	}
	
	/**
	 * Método exclusivo para actualizar el ID del gestor del cliente
	 * @param id_cliente ID del cliente
	 * @param id_gestor ID del gestor nuevo
	 * @return {@code Cliente} con los parámetros de ID y del ID del gestor actualizado.
	 */
	public static Cliente updateId_gestorCliente(int id_cliente, int id_gestor) {
		return new Cliente(id_cliente, id_gestor);
	}

	/**
	 * Método que actualiza un dato de tipo {@code String} del cliente.
	 * @param texto Dato de tipo {@code String} que se quiere actualizar
	 * @param id    ID del cliente
	 * @return {@code Cliente} con los parámetros de ID y del dato actualizado.
	 */
	public static Cliente updateCliente(String texto, int id) {
		return new Cliente(id, JOptionPane.showInputDialog(null, "Introduzca " + texto + ":", "ACTUALIZACIÓN DEL CLIENTE " + id, 1));
	}
	
	/**
	 * Método exclusivo para actualizar la contraseña del cliente
	 * @param password La contraseña generada en el método
	 *                 {@link com.sandra.bancoDB.utilidades.GeneralUtils#nuevoPassword(String, String, int)}
	 * @param id ID del cliente
	 * @return {@code Cliente} con los parámetros de ID y de la contraseña actualizada.
	 */
	public static Cliente updatePasswordCliente(int id, String password) {
		return new Cliente(id, password);
	}
	
	/**
	 * Método exclusivo para actualizar el saldo del cliente
	 * @param id ID del cliente
	 * @return {@code Cliente} con los parámetros de ID y del saldo actualizado.
	 */
	public static Cliente updateSaldoCliente(int id) {
		return new Cliente(id, Double.parseDouble(
				JOptionPane.showInputDialog(null, "Introduzca el nuevo saldo", "ACTUALIZACIÓN DEL CLIENTE " + id, 1)));
	}

	/**
	 * Método que elimina un gestor o un cliente
	 * @param persona Cuenta que se quiere eliminar. Puede ser un gestor o un cliente
	 * @param id El ID del gestor o del cliente que se quiere eliminar
	 */
	public static void delete(String persona, int id) {
		int confirmacion;
		try {
			confirmacion = JOptionPane.showConfirmDialog(null,
					"¿Seguro que quieres eliminar al "+persona+" " + id + "?",
					"MENSAJE DE CONFIRMACIÓN", 0, 3, new ImageIcon("src/main/java/com/sandra/bancoDB/images/eliminar.png"));
		} catch (Exception e) {
			confirmacion = 1;
		}
		switch (confirmacion) {
		case 0 -> {
			DatabaseUtils.delete(persona, id);
			JOptionPane.showMessageDialog(null, "Eliminación realizada correctamente");
		}
		case 1 -> JOptionPane.showMessageDialog(null, "No se ha eliminado el "+persona+" "+id);
		}
	}
}
