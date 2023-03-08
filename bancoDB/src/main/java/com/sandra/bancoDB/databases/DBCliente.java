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
	 * Guarda en una lista las ID de todos los gestores de la base de datos
	 * @return List con las ID de los gestores
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

	public static void addCliente(Cliente cliente) {
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

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

	public static void getCliente(Cliente cliente) {
		List<Cliente> clientes = datosClientes().stream().filter(e->e.getId_cliente()==cliente.getId_cliente()).collect(Collectors.toList());
		if (clientes.size() == 0) JOptionPane.showMessageDialog(null, "No existe ningún cliente con ese ID\nSe le redigirá al menú Cliente", "ERROR", 2, preocupado);
		else clientes.forEach(System.out::println);
	}

	public static void getClientes() {
		List<Cliente> clientes = datosClientes();
		if (clientes.size() == 0) JOptionPane.showMessageDialog(null, "No existe ningún cliente en la base de datos\nSe le redigirá al menú Cliente", "ERROR", 2, preocupado);
		else clientes.forEach(System.out::println);
	}

	public static boolean existeCliente(Cliente cliente) {
		List<Cliente> clientes = datosClientes().stream().filter(e->e.getId_cliente()==cliente.getId_cliente()).collect(Collectors.toList());
		if (clientes.size() == 0) JOptionPane.showMessageDialog(null, "No existe ningún cliente con ese ID\nSe le redigirá al menú Cliente", "ERROR", 0, preocupado);
		else {
			JOptionPane.showMessageDialog(null, "Se encontró el cliente", "BÚSQUEDA FINALIZADA", 1);
			return true;
			}
		return false;
	}
	
	public void updateDatoCilente(Cliente cliente, String dato) {
		try {
			ps = con.prepareStatement("UPDATE cliente SET "+dato+"=? WHERE id=?");
			switch (dato) {
				case "id_gestor" -> ps.setInt(1, cliente.getId_gestor());
				case "saldo" -> ps.setDouble(1, cliente.getSaldo());
				default -> ps.setString(1, cliente.getUpdateDato());
			}
			ps.setInt(2, cliente.getId_cliente());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void borrarCliente(Cliente cliente) {
		try {
			ps = con.prepareStatement("DELETE FROM cliente WHERE id=?");
			ps.setInt(1, cliente.getId_cliente());
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

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
}
