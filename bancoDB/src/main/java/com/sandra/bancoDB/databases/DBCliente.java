package com.sandra.bancoDB.databases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.sandra.bancoDB.connection.DBConnection;
import com.sandra.bancoDB.entidades.Cliente;

public class DBCliente {
	public static ImageIcon preocupado = new ImageIcon("src/main/java/com/sandra/bancoDB/images/preocupado.png");
	public static Connection con = DBConnection.conexion();
	public static PreparedStatement ps;
	public static ResultSet rs;
	
	/**
	 * Guarda en una lista los ID de todos los gestores de la base de datos
	 * @return {@code List} con los ID de los gestores
	 */
	public static List<Integer> getId_gestores() {
  		List<Integer> id_gestores = new ArrayList<Integer>();
	  	try {
	  		ps = con.prepareStatement("SELECT * FROM gestor");
	  		rs = ps.executeQuery();
	  		while (rs.next()) id_gestores.add(rs.getInt("id"));
	  	} catch (SQLException e) {
	  		e.printStackTrace();
	  	}
	  return id_gestores;
	  }
	
	/**
	 * Método que añade un cliente a la base de datos.
	 * @param gestor {@code Cliente} obtenido con el método
	 * 				 {@link com.sandra.bancoDB.utilidades.UIGestorCliente#addCliente(List)} 
	 * @return {@code true} si el cliente se ha añadido correctamente a la base de datos, {@code false} en caso contrario
	 */
	public static boolean addCliente(Cliente cliente) {
		try {
			ps = con.prepareStatement("INSERT INTO cliente (id, id_gestor, nombre, apellido, dni, usuario, password, correo, saldo)VALUES(?,?,?,?,?,?,?,?,?)");
			ps.setInt(1, cliente.getId_cliente());
			ps.setInt(2, cliente.getId_gestor());
			ps.setString(3, cliente.getNombre());
			ps.setString(4, cliente.getApellido());
			ps.setString(5, cliente.getDni());
			ps.setString(6, cliente.getUsuario());
			ps.setString(7, cliente.getPassword());
			ps.setString(8, cliente.getCorreo());
			ps.setDouble(9, cliente.getSaldo());
			ps.execute();
			return true;
		} catch (SQLException e) {
			return false;
		}
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
	 * Método que imprime por consola el cliente cuyo ID coincida con el proporcionado.<br>
	 * Si no se encuentra ningún cliente, devuelve un mensaje de error.
	 * @param id_cliente El ID del cliente que se quiere obtener
	 */
	public static void getCliente(int id_cliente) {
		List<Cliente> clientes = datosClientes().stream().filter(e->e.getId_cliente()==id_cliente).collect(Collectors.toList());
		if (clientes.size() == 0) JOptionPane.showMessageDialog(null, "No existe ningún cliente con ese ID\nSe le redigirá al menú Cliente", "ERROR", 2, preocupado);
		else clientes.forEach(System.out::println);
	}

	/**
	 * Método que imprime por consola todos los clientes que se encuentran en la base de datos.<br>
	 * Si la base de datos está vacía, devuelve un mensaje de error.
	 */
	public static void getClientes() {
		List<Cliente> clientes = datosClientes();
		if (clientes.size() == 0) JOptionPane.showMessageDialog(null, "No existe ningún cliente en la base de datos\nSe le redigirá al menú Cliente", "ERROR", 2, preocupado);
		else clientes.forEach(System.out::println);
	}
	
	/**
	 * Método que comprueba si el cliente cuyo ID se proporciona se encuentra en la base de datos.
	 * @param id_cliente El ID del cliente que se quiere encontrar
	 * @return {@code true} si se encuentra el cliente, {@code false} en caso contrario
	 */
	public static boolean existeCliente(int id_cliente) {
		List<Cliente> clientes = datosClientes().stream().filter(e->e.getId_cliente()==id_cliente).collect(Collectors.toList());
		if (clientes.size() == 0) JOptionPane.showMessageDialog(null, "No existe ningún cliente con ese ID", "ERROR", 0, preocupado);
		else {
			JOptionPane.showMessageDialog(null, "Se encontró el cliente", "BÚSQUEDA FINALIZADA", 1);
			return true;
			}
		return false;
	}
	
	/**
	 * Método que actualiza un tipo de dato determinado para un cliente especificado previamente.
	 * @param cliente {@code Cliente} con los parámetros de ID y del dato actualizado.
	 * @param dato Tipo de dato que se quiere actualizar.
	 * @return {@code true} si el dato, que no puede estar vacío, se ha actualizado correctamente, {@code false} en caso contrario
	 */
	public static boolean updateDatoCilente(Cliente cliente, String dato) {
		try {
			ps = con.prepareStatement("UPDATE cliente SET "+dato+"=? WHERE id=?");
			switch (dato) {
				case "id_gestor" -> ps.setInt(1, cliente.getId_gestor());
				case "saldo" -> ps.setDouble(1, cliente.getSaldo());
				default -> ps.setString(1, cliente.getUpdateDato());
			}
			ps.setInt(2, cliente.getId_cliente());
			if (!cliente.getUpdateDato().isBlank()) ps.executeUpdate();
			return !cliente.getUpdateDato().isBlank();
		} catch (SQLException e) {
			return false;
		}
	}
	
	// Esto se usa en el menú Transferencias, sería ideal mover este método a UITransferencia más tarde
	public void updateSaldoTransferencia(Cliente cliente, double nuevoImporte) {
		try {
			ps = con.prepareStatement("UPDATE cliente SET saldo=? WHERE id=?");
			ps.setDouble(1, nuevoImporte);
			ps.setInt(2, cliente.getId_cliente());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Método que elimina el gestor cuyo ID coincida con el proporcionado.
	 * @param id_cliente El ID del cliente que se quiere eliminar
	 */
	public static void deleteCliente(int id_cliente) {
		try {
			ps = con.prepareStatement("DELETE FROM cliente WHERE id=?");
			ps.setInt(1, id_cliente);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
