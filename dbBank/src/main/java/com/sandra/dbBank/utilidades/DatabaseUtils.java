package com.sandra.dbBank.utilidades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.swing.JOptionPane;

import com.sandra.dbBank.entidades.Cliente;
import com.sandra.dbBank.entidades.Cuenta;
import com.sandra.dbBank.entidades.Gestor;
import com.sandra.dbBank.entidades.Mensaje;
import com.sandra.dbBank.entidades.Transferencia;

public class DatabaseUtils {
	
	private final static String URI = "jdbc:mysql://localhost:3306/wdbank";
	private final static String USER = "root";
	private final static String PASSWORD = "";
	
	private static Connection con;
	private static PreparedStatement ps;
	private static ResultSet rs;
	
	/**
	 * Método que conecta con la base de datos.
	 * @return {@code true} si se ha conectado la base de datos, {@code false} si falla la conexión.
	 */
	public static boolean conexion() {
		con = null;
    	try {
    		con = DriverManager.getConnection(URI, USER, PASSWORD);
    	} catch (SQLException e) {
    		JOptionPane.showMessageDialog(null, "No se ha podido conectar con la base de datos.", "ERROR", 0);
		}
    	return con != null;
    }
	
	/**
	 * Método que desconecta con la base de datos.
	 * @return {@code true} si desconecta correctamente, {@code false} si falla la desconexión
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
	 * Método que guarda todos los datos de una tabla de la base de datos.
	 * @param <T> Tipo genérico
	 * @param tabla Nombre de la tabla en la base de datos
	 * @return {@code List} de todos los datos de la tabla
	 */
	public static <T> List<T> getAll(String tabla) {
		List<T> datos = new ArrayList<>();
		rs = devolverRSPS(String.format("SELECT * FROM %s", tabla), new ArrayList<>());
		if (rs == null) return null;
		try {
			while (rs.next()) datos.add(createDato(tabla));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return datos;
	}
	
	/**
	 * Método que guarda los datos que cumplen las condiciones indicadas de una tabla de la base de datos.
	 * @param <T> Tipo genérico
	 * @param tabla Nombre de la tabla en la base de datos
	 * @param condicion {@code String} con las condiciones que deben cumplir los datos
	 * @param parametros {@code List} de parámetros del statement
	 * @return Datos de la tabla seleccionada que cumplen las condiciones indicadas
	 */
	public static <T> List<T> getWithCondition(String tabla, String condicion, List<Object> parametros) {
		List<T> datos = new ArrayList<>();
		rs = devolverRSPS(String.format("SELECT * FROM %s WHERE %s", tabla, condicion), parametros);
		if (rs == null) return null;
		try {
			while (rs.next()) datos.add(createDato(tabla));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return datos;
	}
	
	/**
	 * Método que guarda un solo dato de una tabla de la base de datos.
	 * @param <T> Tipo genérico
	 * @param tabla Nombre de la tabla en la base de datos
	 * @param id El ID del dato
	 * @return Dato de la tabla seleccionada
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getOne(String tabla, int id) {
		List<T> datos = new ArrayList<>();
		rs = devolverRSPS(String.format("SELECT * FROM %s WHERE id = ?", tabla), new ArrayList<>(Arrays.asList(id)));
		if (rs == null) return null;
		try {
			while (rs.next()) datos.add(createDato(tabla));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (datos.size() != 0) return (tabla.equals("cliente")) ? (T) ClienteUtils.getGestor((Cliente) datos.get(0)) : datos.get(0);
		return null;
	}
	
	/**
	 * Método que guarda el dato recogido mediante sus constructores
	 * @param <T> Tipo genérico
	 * @param tabla Nombre de la tabla en la base de datos
	 * @return Dato que se ha creado
	 */
	@SuppressWarnings("unchecked")
	private static <T> T createDato(String tabla) {
		T dato = null;
		try {
			switch (tabla) {
				case "gestor" -> dato = (T) new Gestor(rs.getInt("id"), rs.getString("nombre"), rs.getString("apellido"), rs.getString("dni"),
							rs.getString("password"), rs.getString("correo"), rs.getString("oficina"));
				case "cliente" -> dato = (T) new Cliente(rs.getInt("id"), rs.getString("nombre"), rs.getString("apellido"), rs.getString("dni"),
							rs.getString("password"), rs.getString("correo"), new Gestor(rs.getInt("gestor")));
				case "mensaje" -> dato = (T) new Mensaje(rs.getInt("id"), rs.getInt("gestor") == 0 ? null : new Gestor(rs.getInt("gestor")),
						new Cliente(rs.getInt("cliente")), rs.getString("concepto"), rs.getString("texto"),
						rs.getTimestamp("fecha").toLocalDateTime(), rs.getInt("leido") != 0);
				case "cuenta" -> dato = (T) new Cuenta(rs.getInt("id"), new Cliente(rs.getInt("cliente")), rs.getDouble("saldo"));
				case "transferencia" -> dato = (T) new Transferencia(rs.getInt("id"),
						rs.getInt("ordenante") == 0 ? null : new Cuenta(rs.getInt("ordenante")),
						rs.getInt("beneficiario") == 0 ? null : new Cuenta(rs.getInt("beneficiario")), rs.getDouble("importe"),
						rs.getString("concepto"), rs.getTimestamp("fecha").toLocalDateTime());
			}
		} catch (SQLException e) {
			return dato;
		}
		return dato;
	}
	
	/**
	 * Método que añade un dato a la base de datos.
	 * @param <T> Tipo genérico
	 * @param dato Dato de clase genérica que se quiere insertar en la base de datos
	 * @return {@code true} si el dato se ha añadido correctamente a la base de datos, {@code false} en caso contrario
	 */
	public static <T> boolean addDato(T dato) {
		String tipoDato = dato.getClass().getSimpleName().toLowerCase();
		String sql = String.format("INSERT INTO %s (", tipoDato);
		List<Object> parametros = new ArrayList<>();
		switch(tipoDato) {
			case "gestor" -> {
				sql += "nombre, apellido, dni, password, correo, oficina)VALUES(?, ?, ?, ?, ?, ?)";
				Collections.addAll(parametros, ((Gestor)dato).getNombre(), ((Gestor)dato).getApellido(),
						((Gestor)dato).getDni(), ((Gestor)dato).getPassword(), ((Gestor)dato).getCorreo(), ((Gestor)dato).getOficina());
			}
			case "cliente" -> {
				sql += "nombre, apellido, dni, password, correo, gestor)VALUES(?, ?, ?, ?, ?, ?)";
				Collections.addAll(parametros, ((Cliente)dato).getNombre(), ((Cliente)dato).getApellido(),
						((Cliente)dato).getDni(), ((Cliente)dato).getPassword(), ((Cliente)dato).getCorreo(), ((Cliente)dato).getGestor().getId());
			}
			case "mensaje" -> {
				sql += "gestor, cliente, concepto, texto)VALUES(?, ?, ?, ?)";
				Collections.addAll(parametros, ((Mensaje)dato).getGestor().getId(), ((Mensaje)dato).getCliente().getId(),
						((Mensaje)dato).getConcepto(), ((Mensaje)dato).getTexto());
			}
			case "cuenta" -> {
				sql += "cliente)VALUES(?)";
				parametros.add(((Cuenta)dato).getCliente().getId());
			}
		}
		return psDML(sql, parametros)!=-1;
	}
	
	/**
	 * Método que guarda todos los ID de los gestores registrados en la base de datos.
	 * @return {@code List} de todos los ID de los gestores registrados
	 */
	public static List<Integer> getIdsGestor() {
		List<Integer> datos = new ArrayList<>();
		rs = devolverRSPS("SELECT id FROM gestor", new ArrayList<>());
		if (rs == null) return null;
		try {
			while (rs.next()) datos.add(rs.getInt("id"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return datos;
	}
	
	/**
	 * Método que actualiza un tipo de dato determinado para cualquier tabla en la base de datos.
	 * @param <T> Tipo genérico
	 * @param dato Dato que se quiere actualizar
	 * @param atributo Atributo del dato que se quiere actualizar
	 * @param parametros {@code List} de parámetros del statement
	 * @return {@code true} si se ha actualizado correctamente, {@code false} en caso contrario
	 */
	public static <T> boolean updateDato(T dato, String atributo, List<Object> parametros) {
		String tipoDato = dato.getClass().getSimpleName().toLowerCase();
		return psDML(String.format("UPDATE %s SET %s = ? WHERE id = ?", tipoDato, atributo), parametros) != -1;
	}
	
	/**
	 * Método que elimina un dato de la tabla especificado cuyo ID coincida con el proporcionado.
	 * @param tabla Nombre de la tabla donde se encuentra el dato que se quiere eliminar.
	 * @param id El ID del dato que se quiere eliminar
	 * @return {@code true} si el dato se ha eliminado correctamente, {@code false} en caso contrario
	 */
	public static boolean deleteDato(String tabla, int id) {
		try {
			ps = con.prepareStatement(String.format("DELETE FROM %s WHERE id=?", tabla));
			ps.setInt(1, id);
			ps.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Método que cambia el gestor de un cliente y manda un mensaje
	 * @param c {@code Cliente} a actualizar
	 * @param m {@code Mensaje} que se va a insertar en la base de datos
	 * @return {@code true} si ambas acciones se han realizado correctamente, {@code false} en caso contrario
	 */
	public static boolean changeGestor(Cliente c, Mensaje m) {
		try {
			con.setAutoCommit(false);
			try {
				List<Object> param_update = new ArrayList<>(Arrays.asList(c.getGestor().getId(), c.getId()));
				if (psDML("UPDATE cliente SET gestor = ? WHERE id = ?", param_update) == -1) return false;
				String sql = "INSERT INTO mensaje (gestor, cliente, concepto, texto)VALUES(?, ?, ?, ?)";
				List<Object> param_insert = new ArrayList<>(Arrays.asList(m.getGestor().getId(), m.getCliente().getId(),
						m.getConcepto(), m.getTexto()));
				if (psDML(sql, param_insert) == -1) return false;
				con.commit();
			} catch (SQLException e) {
				con.rollback();
				return false;
			}
		} catch (SQLException e) {
			return false;
		}
		return true;
	}
	
	/**
	 * Método que realiza varias acciones relacionadas con el cliente:<br>
	 * 1. Se eliminan las transferencias relacionadas con el cliente si la otra cuenta es nula o las dos son del cliente<br>
	 * 2. Se eliminan todas las cuentas del cliente<br>
	 * 3. Se eliminan todos los mensajes recibidos del cliente<br>
	 * 4. Se elimina el cliente de la base de datos
	 * @param c {@code Cliente que se va a eliminar}
	 * @return {@code true} si todas las acciones se han realizado correctamente, {@code false} en caso contrario
	 */
	public static boolean deleteCliente(Cliente c) {
		try {
			con.setAutoCommit(false);
			try {
				List<Transferencia> transferencias = getWithCondition("transferencia", "ordenante IN (SELECT id FROM cuenta WHERE cliente = ?)"+
						" OR beneficiario IN (SELECT id FROM cuenta WHERE cliente = ?)", new ArrayList<>(Arrays.asList(c.getId(), c.getId())));
				transferencias.forEach(t -> {
					t = ClienteUtils.getTransferencia(t);
					Cuenta ordenante = t.getCuenta_ordenante(), beneficiaria = t.getCuenta_beneficiaria();
					boolean borrar = (ordenante == null || beneficiaria == null) ? true : ordenante.getCliente().equals(beneficiaria.getCliente());
					if (borrar) deleteDato("transferencia", t.getId());
				});
				List<Cuenta> cuentas = getWithCondition("cuenta", "cliente = ?", new ArrayList<>(Arrays.asList(c.getId())));
				for (Cuenta cuenta : cuentas) deleteDato("cuenta", cuenta.getId());
				// 3. Eliminar todos los mensajes
				List<Mensaje> mensajes = GeneralUtils.getMensajes(c);
				for (Mensaje m : mensajes) deleteDato("mensaje", m.getId());
				deleteDato("cliente", c.getId());
				con.commit();
			} catch (SQLException e) {
				con.rollback();
				return false;
			}
		} catch (SQLException e) {
			return false;
		}
		return true;
	}
	
	/**
	 * Método que añade una transferencia a la base de datos y actualiza las cuentas implicadas en ella
	 * @param t {@code Transferencia} que se quiere realizar
	 * @return {@code true} si todas las acciones se han realizado correctamente, {@code false} en caso contrario
	 */
	public static boolean sendTransferencia(Transferencia t) {
		try {
			con.setAutoCommit(false);
			try {
				Cuenta ordenante = t.getCuenta_ordenante(), beneficiaria = t.getCuenta_beneficiaria();
				String sql = "INSERT INTO transferencia (ordenante, beneficiario, importe, concepto)VALUES(?, ?, ?, ?)";
				List<Object> param_insert = new ArrayList<>(Arrays.asList(ordenante.getId(), beneficiaria.getId(), t.getImporte(), t.getConcepto()));
				if (psDML(sql, param_insert) == -1) return false;
				ordenante.setSaldo(ordenante.getSaldo() - t.getImporte());
				beneficiaria.setSaldo(beneficiaria.getSaldo() + t.getImporte());
				List<Object> param_up_ord = new ArrayList<>(Arrays.asList(ordenante.getSaldo(), ordenante.getId()));
				List<Object> param_up_ben = new ArrayList<>(Arrays.asList(beneficiaria.getSaldo(), beneficiaria.getId()));
				if (psDML("UPDATE cuenta SET saldo = ? WHERE id = ?", param_up_ord) == -1) return false;
				if (psDML("UPDATE cuenta SET saldo = ? WHERE id = ?", param_up_ben) == -1) return false;
				con.commit();
			} catch (SQLException e) {
				con.rollback();
				return false;
			}
		} catch (SQLException e) {
			return false;
		}
		return true;
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
			System.err.println(e.getMessage());
		}
		return -1;
	}
	
	private static int countMatches(String sql, char caracterBuscado) {
		return (int)sql.chars().filter(e->e==caracterBuscado).count();
	}
}
