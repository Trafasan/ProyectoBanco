package com.project.databases;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.project.environments.DBConnection;
import com.project.models.Cliente;
import com.project.models.Gestor;

public class DBCliente {
	DBConnection connection = new DBConnection();

	ImageIcon preocupado = new ImageIcon("src/images/preocupado.png");

	ArrayList<Gestor> gestores = new ArrayList<Gestor>();
	ArrayList<Cliente> clientes = new ArrayList<Cliente>();

	public boolean comprobarId_gestorInsertar(Gestor gestor) {
		boolean existeId_gestor = false;
		try {
			PreparedStatement statement = connection.getConnection()
					.prepareStatement("SELECT * FROM gestor WHERE id=?");
			statement.setInt(1, gestor.getId_gestor());

			ResultSet resultados = statement.executeQuery();
			while (resultados.next()) {
				gestores.add(new Gestor(resultados.getInt("id"), resultados.getString("nombre"),
						resultados.getString("apellido"), resultados.getString("dni"), resultados.getString("usuario"),
						resultados.getString("password"), resultados.getString("correo")));
			}

			if (gestores.size() == 0) {
				JOptionPane.showMessageDialog(null, "No existe ningún gestor con ese ID", "ERROR", 0, preocupado);
			} else {
				JOptionPane.showMessageDialog(null, "Se encontró el gestor", "BÚSQUEDA FINALIZADA", 1);
				existeId_gestor = true;

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return existeId_gestor;
	}

	public void crearUnCliente(Cliente cliente) {
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

	public void leerUnCliente(Cliente cliente) {
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

			if (clientes.size() == 0) {
				JOptionPane.showMessageDialog(null, "No existe ningún cliente con ese ID", "ERROR", 2, preocupado);
			} else {
				for (int x = 0; x < clientes.size(); x++) {
					System.out.println("Datos del cliente " + clientes.get(x).getId_cliente());
					System.out.println("ID del gestor: " + clientes.get(x).getId_gestor());
					System.out.println("Nombre: " + clientes.get(x).getNombre());
					System.out.println("Apellido: " + clientes.get(x).getApellido());
					System.out.println("DNI: " + clientes.get(x).getDni());
					System.out.println("Usuario: " + clientes.get(x).getUsuario());
					System.out.println("Contraseña: " + clientes.get(x).getPassword());
					System.out.println("Correo: " + clientes.get(x).getCorreo());
					System.out.println("Saldo: " + clientes.get(x).getSaldo() + "€\n");
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void leerClientes() {
		try {
			PreparedStatement statement = connection.getConnection().prepareStatement("SELECT * FROM cliente");
			ResultSet resultados = statement.executeQuery();

			while (resultados.next()) {
				System.out.println("Datos del cliente " + resultados.getInt("id"));
				System.out.println("ID del gestor: " + resultados.getInt("id_gestor"));
				System.out.println("Nombre: " + resultados.getString("nombre"));
				System.out.println("Apellido: " + resultados.getString("apellido"));
				System.out.println("DNI: " + resultados.getString("dni"));
				System.out.println("Usuario: " + resultados.getString("usuario"));
				System.out.println("Contraseña: " + resultados.getString("password"));
				System.out.println("Correo: " + resultados.getString("correo"));
				System.out.println("Saldo: " + resultados.getDouble("saldo") + "€\n");
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public boolean comprobarCliente(Cliente cliente) {
		boolean existeCliente = false;
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

			if (clientes.size() == 0) {
				JOptionPane.showMessageDialog(null, "No existe ningún cliente con ese ID", "ERROR", 0, preocupado);
			} else {
				JOptionPane.showMessageDialog(null, "Se encontró el cliente", "BÚSQUEDA FINALIZADA", 1);
				existeCliente = true;

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return existeCliente;
	}

	public boolean comprobarId_gestorUpdate(Gestor gestor, Cliente comprobacionCliente) {
		boolean existeId_gestor = false;
		try {
			PreparedStatement statement = connection.getConnection()
					.prepareStatement("SELECT * FROM gestor WHERE id=?");
			statement.setInt(1, gestor.getId_gestor());

			ResultSet resultados = statement.executeQuery();
			while (resultados.next()) {
				gestores.add(new Gestor(resultados.getInt("id"), resultados.getString("nombre"),
						resultados.getString("apellido"), resultados.getString("dni"), resultados.getString("usuario"),
						resultados.getString("password"), resultados.getString("correo")));
			}

			if (gestores.size() == 0) {
				JOptionPane.showMessageDialog(null, "No existe ningún gestor con ese ID", "ERROR", 0, preocupado);
			} else {
				existeId_gestor = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return existeId_gestor;
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
