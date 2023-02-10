package com.project.databases;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.project.environments.DBConnection;
import com.project.models.Cliente;
import com.project.models.Transferencia;

public class DBTransferencia {
	DBConnection connection = new DBConnection();

	ImageIcon preocupado = new ImageIcon("src/images/preocupado.png");

	ArrayList<Cliente> ordenante = new ArrayList<Cliente>();
	ArrayList<Cliente> beneficiario = new ArrayList<Cliente>();
	ArrayList<Transferencia> transferencias = new ArrayList<Transferencia>();

	public void leerUnaTransferencia(Transferencia transferencia) {
		try {
			PreparedStatement statement = connection.getConnection()
					.prepareStatement("SELECT * FROM transferencia WHERE id=?");
			statement.setInt(1, transferencia.getId_transferencia());

			ResultSet resultados = statement.executeQuery();
			while (resultados.next()) {
				transferencias.add(new Transferencia(resultados.getInt("id"), resultados.getInt("id_ordenante"),
						resultados.getInt("id_beneficiario"), resultados.getDouble("importe"),
						resultados.getString("concepto"), resultados.getTimestamp("fecha")));
			}

			if (transferencias.size() == 0) {
				JOptionPane.showMessageDialog(null, "No existe ninguna transferencia con ese ID", "ERROR", 2,
						preocupado);
			} else {
				for (int x = 0; x < transferencias.size(); x++) {
					System.out.println("Datos de la transferencia " + transferencias.get(x).getId_transferencia());
					System.out.println("ID del cliente ordenante: " + transferencias.get(x).getId_ordenante());
					System.out.println("ID del cliente beneficiario: " + transferencias.get(x).getId_beneficiario());
					System.out.println("Importe: " + transferencias.get(x).getImporte() + "€");
					System.out.println("Concepto: " + transferencias.get(x).getConcepto());
					System.out.println("Fecha: " + transferencias.get(x).getFecha() + "\n");
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void leerTransferencias() {
		try {
			PreparedStatement statement = connection.getConnection().prepareStatement("SELECT * FROM transferencia");
			ResultSet resultados = statement.executeQuery();

			while (resultados.next()) {
				System.out.println("Datos de la transferencia " + resultados.getInt("id"));
				System.out.println("ID del cliente ordenante: " + resultados.getInt("id_ordenante"));
				System.out.println("ID del cliente beneficiario: " + resultados.getInt("id_beneficiario"));
				System.out.println("Importe: " + resultados.getDouble("importe") + "€");
				System.out.println("Concepto: " + resultados.getString("concepto"));
				System.out.println("Fecha: " + resultados.getTimestamp("fecha") + "\n");
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public boolean comprobarId_ordenante(Cliente cliente) {
		boolean existeId_ordenante = false;
		try {
			PreparedStatement statement = connection.getConnection()
					.prepareStatement("SELECT * FROM cliente WHERE id=?");
			statement.setInt(1, cliente.getId_cliente());

			ResultSet resultados = statement.executeQuery();
			while (resultados.next()) {
				ordenante.add(new Cliente(resultados.getInt("id"), resultados.getInt("id_gestor"),
						resultados.getString("nombre"), resultados.getString("apellido"), resultados.getString("dni"),
						resultados.getString("usuario"), resultados.getString("password"),
						resultados.getString("correo"), resultados.getDouble("saldo")));
			}

			if (ordenante.size() == 0) {
				JOptionPane.showMessageDialog(null, "No existe ningún cliente con ese ID", "ERROR", 0, preocupado);
			} else {
				JOptionPane.showMessageDialog(null, "Se encontró el cliente", "BÚSQUEDA FINALIZADA", 1);
				existeId_ordenante = true;

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return existeId_ordenante;
	}

	public boolean comprobarId_beneficiario(Cliente cliente) {
		boolean existeId_beneficiario = false;
		try {
			PreparedStatement statement = connection.getConnection()
					.prepareStatement("SELECT * FROM cliente WHERE id=?");
			statement.setInt(1, cliente.getId_cliente());

			ResultSet resultados = statement.executeQuery();
			while (resultados.next()) {
				beneficiario.add(new Cliente(resultados.getInt("id"), resultados.getInt("id_gestor"),
						resultados.getString("nombre"), resultados.getString("apellido"), resultados.getString("dni"),
						resultados.getString("usuario"), resultados.getString("password"),
						resultados.getString("correo"), resultados.getDouble("saldo")));
			}

			if (beneficiario.size() == 0) {
				JOptionPane.showMessageDialog(null, "No existe ningún cliente con ese ID", "ERROR", 0, preocupado);
			} else {
				JOptionPane.showMessageDialog(null, "Se encontró el cliente", "BÚSQUEDA FINALIZADA", 1);
				existeId_beneficiario = true;

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return existeId_beneficiario;
	}

	// 17. Envío de una transferencia (actualizando los saldos de los clientes
	// involucrados)
	public void enviarTransferencia(Transferencia transferencia) {
		try {
			PreparedStatement statement = connection.getConnection().prepareStatement(
					"INSERT INTO transferencia (id, id_ordenante, id_beneficiario, importe, concepto, fecha)VALUES(?,?,?,?,?,?)");
			statement.setInt(1, transferencia.getId_transferencia());
			statement.setInt(2, transferencia.getId_ordenante());
			statement.setInt(3, transferencia.getId_beneficiario());
			statement.setDouble(4, transferencia.getImporte());
			statement.setString(5, transferencia.getConcepto());
			statement.setTimestamp(6, transferencia.getFecha());
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public double leerSaldoOrdenante(Cliente cliente) {
		double saldoOrdenante = 0;
		try {
			PreparedStatement statement = connection.getConnection()
					.prepareStatement("SELECT * FROM cliente WHERE id=?");
			statement.setInt(1, cliente.getId_gestor());

			ResultSet resultados = statement.executeQuery();
			while (resultados.next()) {
				ordenante.add(new Cliente(resultados.getInt("id"), resultados.getInt("id_gestor"),
						resultados.getString("nombre"), resultados.getString("apellido"), resultados.getString("dni"),
						resultados.getString("usuario"), resultados.getString("password"),
						resultados.getString("correo"), resultados.getDouble("saldo")));
			}

			if (ordenante.size() == 0) {
			} else {
				saldoOrdenante = ordenante.get(0).getSaldo();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return saldoOrdenante;
	}

	public double leerSaldoBeneficiario(Cliente cliente) {
		double saldoBeneficiario = 0;
		try {
			PreparedStatement statement = connection.getConnection()
					.prepareStatement("SELECT * FROM cliente WHERE id=?");
			statement.setInt(1, cliente.getId_gestor());

			ResultSet resultados = statement.executeQuery();
			while (resultados.next()) {
				beneficiario.add(new Cliente(resultados.getInt("id"), resultados.getInt("id_gestor"),
						resultados.getString("nombre"), resultados.getString("apellido"), resultados.getString("dni"),
						resultados.getString("usuario"), resultados.getString("password"),
						resultados.getString("correo"), resultados.getDouble("saldo")));
			}

			if (beneficiario.size() == 0) {
			} else {
				saldoBeneficiario = beneficiario.get(0).getSaldo();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return saldoBeneficiario;
	}
}
