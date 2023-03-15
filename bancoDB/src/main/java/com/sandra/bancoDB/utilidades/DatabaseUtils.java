package com.sandra.bancoDB.utilidades;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.sandra.bancoDB.entidades.Cliente;
import com.sandra.bancoDB.entidades.Gestor;
import com.sandra.bancoDB.entidades.Mensaje;
import com.sandra.bancoDB.entidades.Transferencia;

public class DatabaseUtils {
	
	private final static String URI = "jdbc:mysql://localhost:3306/banco";
	private final static String USER = "root";
	private final static String PASSWORD = "";
	
	private static ImageIcon preocupado = new ImageIcon("src/main/java/com/sandra/bancoDB/images/preocupado.png");
	
	private static Connection con;
	private static PreparedStatement ps;
	private static ResultSet rs;
	
	/**
	 * Método que conecta con la base de datos.
	 * @return La conexión a la base de datos.<br>
	 * 		   Si ha fallado la conexión, la conexión es nula.
	 */
	public static Connection conexion() {
		con = null;
    	try {
    		con = DriverManager.getConnection(URI, USER, PASSWORD);
    	} catch (SQLException e) {
    		System.err.println("No se ha podido conectar correctamente con la base de datos.");
		}
    	return con;
    }
	
	/**
	 * Método que desconecta con la base de datos.
	 * @return True si desconecta correctamente, False si falla la desconexión
	 */
	public static boolean desconexion() {
		try {
			if(!con.isClosed()) {
				con.close();
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * Método que añade un dato a la base de datos.
	 * @param <T>
	 * @param tipoDato Tabla donde se quiere insertar el dato
	 * @param dato Dato de clase genérica que se quiere insertar en la base de datos
	 * @return {@code true} si el dato se ha añadido correctamente a la base de datos, {@code false} en caso contrario
	 */
	public static <T> boolean addDato(T dato) {
		String tipoDato = dato.getClass().getSimpleName();
		String sql = "INSERT INTO "+tipoDato+" (id, ";
		List<Object> parametros = new ArrayList<>();
		switch(tipoDato) {
		case "gestor" -> {
			sql += "nombre, apellido, dni, usuario, password, correo)VALUES(?,?,?,?,?,?,?)";
			Collections.addAll(parametros, ((Gestor)dato).getId_gestor(), ((Gestor)dato).getNombre(), ((Gestor)dato).getApellido(),
					((Gestor)dato).getDni(), ((Gestor)dato).getUsuario(), ((Gestor)dato).getPassword(), ((Gestor)dato).getCorreo());
		}
		case "cliente" -> {
			sql += "id_gestor, nombre, apellido, dni, usuario, password, correo, saldo)VALUES(?,?,?,?,?,?,?,?,?)";
			Collections.addAll(parametros, ((Cliente)dato).getId_cliente(), ((Cliente)dato).getId_gestor(), ((Cliente)dato).getNombre(),
					((Cliente)dato).getApellido(), ((Cliente)dato).getDni(), ((Cliente)dato).getUsuario(), ((Cliente)dato).getPassword(),
					((Cliente)dato).getCorreo(), ((Cliente)dato).getSaldo());
		}
		case "mensaje" -> {
			sql += "id_gestor, id_cliente, texto, fecha)VALUES(?,?,?,?,?)";
			Collections.addAll(parametros, ((Mensaje)dato).getId_mensaje(), ((Mensaje)dato).getId_gestor(), ((Mensaje)dato).getId_cliente(),
					((Mensaje)dato).getTexto(), ((Mensaje)dato).getFecha());
		}
		case "transferencia" -> {
			sql += "id_ordenante, id_beneficiario, importe, concepto, fecha)VALUES(?,?,?,?,?,?)";
			Collections.addAll(parametros, ((Transferencia)dato).getId_transferencia(), ((Transferencia)dato).getId_ordenante(),
					((Transferencia)dato).getId_beneficiario(), ((Transferencia)dato).getImporte(), ((Transferencia)dato).getConcepto(),
					((Transferencia)dato).getFecha());
		}
		}
		return psDML(sql, parametros)!=-1;
	}
	
	/**
	 * Método que guarda los datos de todos los gestores que se encuentran en la base de datos.
	 * @return {@code List} de todos los gestores en la base de datos
	 */
	public static List<Gestor> datosGestores() {
		List<Gestor> gestores = new ArrayList<Gestor>();
		rs = devolverRSPS("SELECT * FROM gestor", new ArrayList<>());
		try {
			while (rs.next()) {
				gestores.add(new Gestor(rs.getInt("id"), rs.getString("nombre"),
						rs.getString("apellido"), rs.getString("dni"), rs.getString("usuario"),
						rs.getString("password"), rs.getString("correo")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return gestores;
	}

	/**
	 * Método que guarda los datos de todos los clientes que se encuentran en la base de datos.
	 * @return {@code List} de todos los clientes en la base de datos
	 */
	public static List<Cliente> datosClientes() {
		List<Cliente> clientes = new ArrayList<Cliente>();
		try {
			ps = con.prepareStatement("SELECT * FROM cliente");
			rs = ps.executeQuery();
			while (rs.next()) {
				clientes.add(new Cliente(rs.getInt("id"), rs.getInt("id_gestor"),
						rs.getString("nombre"), rs.getString("apellido"), rs.getString("dni"),
						rs.getString("usuario"), rs.getString("password"),
						rs.getString("correo"), rs.getDouble("saldo")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return clientes;
	}
	
	/**
	 * Método que guarda los datos de todos los mensajes que se encuentran en la base de datos.
	 * @return {@code List} de todos los mensajes en la base de datos
	 */
	public static List<Mensaje> datosMensajes() {
		List<Mensaje> mensajes = new ArrayList<Mensaje>();
		try {
			ps = con.prepareStatement("SELECT * FROM mensaje");
			rs = ps.executeQuery();
			while (rs.next()) {
				mensajes.add(new Mensaje(rs.getInt("id"), rs.getInt("id_gestor"),
						rs.getInt("id_cliente"), rs.getString("texto"),
						rs.getTimestamp("fecha")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mensajes;
	}
	
	/**
	 * Método que guarda los datos de todas las transferencias que se encuentran en la base de datos.
	 * @return {@code List} de todas las Transferencias en la base de datos
	 */
	public static List<Transferencia> datosTransferencias() {
		List<Transferencia> transferencias = new ArrayList<Transferencia>();
		try {
			ps = con.prepareStatement("SELECT * FROM transferencia");
			rs = ps.executeQuery();
			while (rs.next()) {
				transferencias.add(new Transferencia(rs.getInt("id"), rs.getInt("id_ordenante"),
						rs.getInt("id_beneficiario"), rs.getDouble("importe"),
						rs.getString("concepto"), rs.getTimestamp("fecha")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return transferencias;
	}
	
	/**
	 * Método que imprime por consola el dato cuyo ID coincida con el proporcionado.<br>
	 * Si no se encuentra ningún dato, devuelve un mensaje de error.
	 * @param <T>
	 * @param lista {@code List} de los datos que se va a filtrar
	 * @param id El ID del dato que se quiere obtener
	 * @param dato Dato de clase genérica que se quiere imprimir
	 */
	public static <T> T getDato(List<T> lista, int id) {
		String dato = lista.get(0).getClass().getSimpleName();
		List<T> listaFiltrada;
		switch (dato) {
		case "Gestor" -> listaFiltrada = lista.stream().filter(e->((Gestor)e).getId_gestor() == id).collect(Collectors.toList());
		case "Cliente" -> listaFiltrada = lista.stream().filter(e->((Cliente)e).getId_cliente() == id).collect(Collectors.toList());
		case "Mensaje" -> listaFiltrada = lista.stream().filter(e->((Mensaje)e).getId_mensaje() == id).collect(Collectors.toList());
		case "Transferencia" -> listaFiltrada = lista.stream().filter(e->((Transferencia)e).getId_transferencia() == id).collect(Collectors.toList());
		default -> listaFiltrada = new ArrayList<>();
		}
		return listaFiltrada.size() == 0 ? null : listaFiltrada.get(0);
		/*if (listaFiltrada.size() == 0) JOptionPane.showMessageDialog(null, "No existe ningún "+dato.toLowerCase()+" con ese ID"
				+ "\nSe le redigirá al menú "+dato, "ERROR", 2, preocupado);
		else listaFiltrada.forEach(System.out::println);*/
	}
	
	/**
	 * Método que imprime por consola todos los datos que se encuentran en una tabla de la base de datos.<br>
	 * Si la base de datos está vacía, devuelve un mensaje de error.
	 * @param <T> 
	 * @param lista {@code List} que puede ser de gestores, clientes, mensajes o transferencias
	 * @param dato Nombre del dato a elegir entre Gestor, ...
	 */
	public static <T> void getDatos(List<T> lista, String dato) {
		if (lista.size() == 0) JOptionPane.showMessageDialog(null,
				"No existe ningún "+dato.toLowerCase()+" en la base de datos\nSe le redigirá al menú "+dato, "ERROR", 2, preocupado);
		else lista.forEach(System.out::println);
	}
	
	/**
	 * Método que actualiza un tipo de dato determinado para un gestor o cliente especificado previamente.
	 * @param <T>
	 * @param dato Dato con los parámetros de ID y del dato actualizado.
	 * @param update Tipo de dato que se quiere actualizar.
	 * @return
	 */
	public static <T> boolean updateDato(T dato, String update) {
		String tipoDato = dato.getClass().getSimpleName();
		List<Object> parametros = new ArrayList<>();
		switch (tipoDato) {
		case "Gestor" -> Collections.addAll(parametros, ((Gestor)dato).getUpdateDato(), ((Gestor)dato).getId_gestor());
		case "Cliente" -> {
			switch (update) {
			case "id_gestor" -> parametros.add(((Cliente)dato).getId_gestor());
			case "saldo" -> parametros.add(((Cliente)dato).getSaldo());
			default -> {
				if (((Cliente)dato).getUpdateDato().isBlank()) {
					return false;
				}
				else parametros.add(((Cliente)dato).getUpdateDato());
			}
			}
			parametros.add(((Cliente)dato).getId_cliente());
		}
		}
		return psDML("UPDATE "+tipoDato.toLowerCase()+" SET "+update+"=? WHERE id=?", parametros) != -1;
	}
	
	/**
	 * Método que elimina el gestor o cliente cuyo ID coincida con el proporcionado.
	 * @param persona Cuenta que se quiere eliminar. Puede ser un gestor o un cliente
	 * @param id El ID del gestor o del cliente que se quiere eliminar
	 */
	public static void delete(String persona, int id) {
		try {
			ps = con.prepareStatement("DELETE FROM "+persona+" WHERE id=?");
			ps.setInt(1, id);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Guarda en una lista los ID de todas las personas de una clase de la base de datos
	 * @param <T>
	 * @return {@code List} con los ID de las personas de una clase
	 */
	public static <T> List<Integer> getId(List<T> lista) {
		if (lista.get(0).getClass().getSimpleName().equals("Gestor")) return lista.stream().mapToInt(e->((Gestor)e).getId_gestor()).boxed().collect(Collectors.toList());
		else return lista.stream().mapToInt(e->((Cliente)e).getId_cliente()).boxed().collect(Collectors.toList());
	  }

	/**
	 * Método para obtener el saldo de un cliente
	 * @param id_cliente ID del cliente del cual se quiere obtener el saldo
	 * @return {@code double} con el saldo del cliente
	 */
	public static double getSaldo(int id_cliente) {
		return DatabaseUtils.datosClientes().stream().filter(e->e.getId_cliente() == id_cliente).collect(Collectors.toList()).get(0).getSaldo();
	}
	
	/**
	 * Método que devuelve la persona cuyo usuario coincida con el proporcionado.
	 * @param <T>
	 * @param lista {@code List} de las personas que se va a filtrar
	 * @param usuario El nombre del usuario de la persona que se quiere obtener
	 * @return
	 */
	public static <T> T getPersona(List<T> lista, String usuario) {
		String dato = lista.get(0).getClass().getSimpleName();
		List<T> listaFiltrada;
		switch (dato) {
		case "Gestor" -> listaFiltrada = lista.stream().filter(e->((Gestor)e).getUsuario().equals(usuario)).collect(Collectors.toList());
		case "Cliente" -> listaFiltrada = lista.stream().filter(e->((Cliente)e).getUsuario().equals(usuario)).collect(Collectors.toList());
		default -> listaFiltrada = new ArrayList<>();
		}
		return listaFiltrada.size() == 0 ? null : listaFiltrada.get(0);
	}
	
	/**
	 * Guarda en una lista los usuarios de todas las personas de una clase de la base de datos
	 * @param <T>
	 * @return {@code List} con los usuarios de las personas de una clase
	 */
	public static <T> List<String> getUsuarios(List<T> lista) {
		if (lista.get(0).getClass().getSimpleName().equals("Gestor")) return lista.stream().map(e->((Gestor)e).getUsuario()).collect(Collectors.toList());
		else return lista.stream().map(e->((Cliente)e).getUsuario()).collect(Collectors.toList());
	  }
	
	/**
	 * Dada una sql y una lista de Objetos con sus parámetros a cambiar por las '?'
	 * nos devuelve el ResultSet de los resultados al ejecutar la Select
	 * @param sql String con la SQL con las '?' para que puedan bindeadas (enlazadas)
	 * @param parametros Lista con los parámetros
	 * @return ResultSet de la respuesta de la Select y null en caso de error
	 */
	private static ResultSet devolverRSPS(String sql, List<Object> parametros) {
		if(parametros.size()!=countMatches(sql, '?')) return null; // Esto significa que no coinciden los parámetros con el número de parámetros esperado
		try {
			ps = con.prepareStatement(sql);
			for(int i=1; i<=parametros.size(); i++) {
				ps.setObject(i, parametros.get(i-1));
			}
			return ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Dada una sql y una lista de Objetos con sus parámetros a cambiar por las '?'
	 * nos devuelve el ResultSet de los resultados al ejecutar la Select
	 * @param sql String con la SQL con las '?' para que puedan bindeadas (enlazadas)
	 * @param parametros Lista con los parámetros
	 * @return número de registros afectados, -1 en caso de error
	 */
	private static int psDML(String sql, List<Object> parametros) {
		if(parametros.size()!=countMatches(sql, '?')) return -1; // Esto significa que no coinciden los parámetros con el número de parámetros esperado
		try {
			ps = con.prepareStatement(sql);
			for(int i=1; i<=parametros.size(); i++) {
				ps.setObject(i, parametros.get(i-1));
			}
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	private static int countMatches(String sql, char caracterBuscado) {
		return (int)sql.chars().filter(e->e==caracterBuscado).count();
	}
	
}