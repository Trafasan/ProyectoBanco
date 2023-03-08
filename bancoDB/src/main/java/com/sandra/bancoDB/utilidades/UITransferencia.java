package com.sandra.bancoDB.utilidades;

import java.sql.Timestamp;

import javax.swing.JOptionPane;

import com.sandra.bancoDB.entidades.Cliente;
import com.sandra.bancoDB.entidades.Transferencia;
import com.sandra.bancoDB.utilidades.UITransferencia;

public class UITransferencia {
	// 15. Obtención de una transferencia
	public static Transferencia obtencionTransferencia() {
		int n = Integer.parseInt(JOptionPane.showInputDialog("Inserte el ID de la transferencia a obtener: "));
		Transferencia transferencia = new Transferencia(n);
		return transferencia;
	}

	// 17. Envío de una transferencia

	// Comprobación previa de que existe el cliente con ese ID (ordenante y beneficiario)
	public static Cliente comprobarId_ordenante() {
		int o = Integer.parseInt(JOptionPane.showInputDialog(null, "Introduzca el ID del cliente ordenante:",
				"ENVÍO DE LA TRANSFERENCIA", 1));
		Cliente cliente = new Cliente(o);
		return cliente;
	}
	
	public static Cliente comprobarId_beneficiario() {
		int b = Integer.parseInt(JOptionPane.showInputDialog(null, "Introduzca el ID del cliente beneficiario:",
				"ENVÍO DE LA TRANSFERENCIA", 1));
		Cliente cliente = new Cliente(b);
		return cliente;
	}
	
	public static Transferencia comprobarImporte() {
		double i = Double.parseDouble(JOptionPane.showInputDialog(null, "Introduzca el importe de la nueva transferencia:",
				"ENVÍO DE LA TRANSFERENCIA", 1));
		Transferencia transferencia = new Transferencia(i);
		return transferencia;
	}
	
	public static Transferencia envíoTransferencia(Cliente comprobacionId_ordenante, Cliente comprobacionId_beneficiario, double importe) {
		int id_ordenante = comprobacionId_ordenante.getId_cliente();
		int id_beneficiario = comprobacionId_beneficiario.getId_cliente();
		String concepto = JOptionPane.showInputDialog("Inserte el texto de la nueva transferencia:");
		Long datetime = System.currentTimeMillis();
		Timestamp fecha = new Timestamp(datetime);

		Transferencia transferencia = new Transferencia(id_ordenante, id_beneficiario, importe, concepto, fecha);

		return transferencia;

	}
}