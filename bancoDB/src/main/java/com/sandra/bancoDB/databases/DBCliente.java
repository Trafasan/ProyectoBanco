package com.sandra.bancoDB.databases;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.sandra.bancoDB.connection.DBConnection;
import com.sandra.bancoDB.models.Cliente;
import com.sandra.bancoDB.models.Gestor;

public class DBCliente {
	DBConnection connection = new DBConnection();

	ImageIcon preocupado = new ImageIcon("src/images/preocupado.png");

	ArrayList<Gestor> gestores = new ArrayList<Gestor>();
	ArrayList<Cliente> clientes = new ArrayList<Cliente>();
	
	/**
	 * Guarda en una ArrayList las ID de todos los gestores de la base de datos
	 * @return ArrayList de las ID de los gestores
	 */
	public ArrayList<Integer> getGestores() {
  		ArrayList<Integer> id_gestores = new ArrayList<Integer>();
	  	try {
	  		PreparedStatement statement = connection.getConnection() .prepareStatement("SELECT * FROM cliente");
	  		ResultSet resultados = statement.executeQuery();
	  		while (resultados.next()) gestores.add(new Gestor(resultados.getInt("id")));
			for (Gestor gestor:gestores) id_gestores.add(gestor.getId_gestor());
	  	} catch (SQLException e) {
	  		e.printStackTrace();
	  	}
	  return id_gestores;
	  }

	public void addCliente(Cliente cliente) {
		try {
			PreparedStatement statement = connection.getConnection().prepareStatement(
					"INSERT INTO cliente (id, id_gestor, nombre, apellido, dni, usuario, password, correo, saldo)VALUES(?,?,?,?,?,?,?,?,?)");
			statement.setInt(1, cliente.getId_cliente());
			statement.setInt(2, cliente.getId_gestor());
			statement.setString(3, cliente.getNombre());
			statement.setString(4, cliente.getApellido());
			statement.setString(5, cliente.getDni());
			statement.setString(6, cliente.getUsuario());
			statement.setString(7, cliente.getPassword());
			statement.setString(8, cliente.getCorreo());
			statement.setDouble(9, cliente.getSaldo());
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void getCliente(Cliente cliente) {
		try {
			PreparedStatement statement = connection.getConnection()
					.prepareStatement("SELECT * FROM cliente WHERE id=?");
			statement.setInt(1, cliente.getId_cliente());

			ResultSet resultados = statement.executeQuery();
			while (resultados.next()) {
				clientes.add(new Cliente(resultados.getInt("id"), resultados.getInt("id_gestor"),
						resultados.getString("nombre"), resultados.getString("apellido"), resultados.getString("dni"),
						resultados.getString("usuario"), resultados.getString("password"),
						resultados.getString("correo"), resultados.getDouble("saldo")));
			}
			if (clientes.size() == 0) JOptionPane.showMessageDialog(null, "No existe ningún cliente con ese ID\nSe le redigirá al menú Cliente", "ERROR", 2, preocupado);
			else for (int i=0; i<clientes.size(); i++) System.out.println(clientes.get(i));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void getClientes() {
		try {
			PreparedStatement statement = connection.getConnection().prepareStatement("SELECT * FROM cliente");
			ResultSet resultados = statement.executeQuery();
			while (resultados.next()) {
				clientes.add(new Cliente(resultados.getInt("id"), resultados.getInt("id_gestor"),
						resultados.getString("nombre"), resultados.getString("apellido"), resultados.getString("dni"),
						resultados.getString("usuario"), resultados.getString("password"),
						resultados.getString("correo"), resultados.getDouble("saldo")));
			}

			if (clientes.size() == 0) JOptionPane.showMessageDialog(null, "No existe ningún cliente en la base de datos\nSe le redigirá al menú Cliente", "ERROR", 2, preocupado);
			else for (int i=0; i<clientes.size(); i++) System.out.println(clientes.get(i));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean existeCliente(Cliente cliente) {
		try {
			PreparedStatement statement = connection.getConnection()
					.prepareStatement("SELECT * FROM cliente WHERE id=?");
			statement.setInt(1, cliente.getId_cliente());

			ResultSet resultados = statement.executeQuery();
			while (resultados.next()) {
				clientes.add(new Cliente(resultados.getInt("id"), resultados.getInt("id_gestor"),
						resultados.getString("nombre"), resultados.getString("apellido"), resultados.getString("dni"),
						resultados.getString("usuario"), resultados.getString("password"),
						resultados.getString("correo"), resultados.getDouble("saldo")));
			}
			if (clientes.size() == 0) JOptionPane.showMessageDialog(null, "No existe ningún cliente con ese ID\nSe le redigirá al menú Cliente", "ERROR", 0, preocupado);
			else {
				JOptionPane.showMessageDialog(null, "Se encontró el cliente", "BÚSQUEDA FINALIZADA", 1);
				return true;

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public void updateId_gestorCliente(Cliente cliente) {
		try {
			PreparedStatement statement = connection.getConnection()
					.prepareStatement("UPDATE cliente SET id_gestor=? WHERE id=?");
			statement.setInt(1, cliente.getId_gestor());
			statement.setInt(2, cliente.getId_cliente());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateNombreCliente(Cliente cliente) {
		try {
			PreparedStatement statement = connection.getConnection()
					.prepareStatement("UPDATE cliente SET nombre=? WHERE id=?");
			statement.setString(1, cliente.getActualizar());
			statement.setInt(2, cliente.getId_cliente());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateApellidoCliente(Cliente cliente) {
		try {
			PreparedStatement statement = connection.getConnection()
					.prepareStatement("UPDATE cliente SET apellido=? WHERE id=?");
			statement.setString(1, cliente.getActualizar());
			statement.setInt(2, cliente.getId_cliente());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateDniCliente(Cliente cliente) {
		try {
			PreparedStatement statement = connection.getConnection()
					.prepareStatement("UPDATE cliente SET dni=? WHERE id=?");
			statement.setString(1, cliente.getActualizar());
			statement.setInt(2, cliente.getId_cliente());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateUsuarioCliente(Cliente cliente) {
		try {
			PreparedStatement statement = connection.getConnection()
					.prepareStatement("UPDATE cliente SET usuario=? WHERE id=?");
			statement.setString(1, cliente.getActualizar());
			statement.setInt(2, cliente.getId_gestor());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updatePasswordCliente(Cliente cliente) {
		try {
			PreparedStatement statement = connection.getConnection()
					.prepareStatement("UPDATE cliente SET password=? WHERE id=?");
			statement.setString(1, cliente.getActualizar());
			statement.setInt(2, cliente.getId_cliente());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateCorreoCliente(Cliente cliente) {
		try {
			PreparedStatement statement = connection.getConnection()
					.prepareStatement("UPDATE cliente SET correo=? WHERE id=?");
			statement.setString(1, cliente.getActualizar());
			statement.setInt(2, cliente.getId_cliente());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateSaldoCliente(Cliente cliente) {
		try {
			PreparedStatement statement = connection.getConnection()
					.prepareStatement("UPDATE cliente SET saldo=? WHERE id=?");
			statement.setDouble(1, cliente.getSaldo());
			statement.setInt(2, cliente.getId_cliente());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void borrarCliente(Cliente cliente) {
		try {
			PreparedStatement statement = connection.getConnection().prepareStatement("DELETE FROM cliente WHERE id=?");
			statement.setInt(1, cliente.getId_cliente());
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateSaldoTransferencia(Cliente cliente, double nuevoImporte) {
		try {
			PreparedStatement statement = connection.getConnection()
					.prepareStatement("UPDATE cliente SET saldo=? WHERE id=?");
			statement.setDouble(1, nuevoImporte);
			statement.setInt(2, cliente.getId_cliente());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
