package com.project.utils;

import java.sql.Timestamp;

import javax.swing.JOptionPane;

import com.project.models.Cliente;
import com.project.models.Transferencia;
import com.project.utils.UITransferencia;

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
	// Debe retocarse para que se cambien los saldos de los clientes
	public static Transferencia envíoTransferencia(Cliente comprobacionId_ordenante, Cliente comprobacionId_beneficiario) {
		int id_ordenante = comprobacionId_ordenante.getId_cliente();
		int id_beneficiario = comprobacionId_beneficiario.getId_cliente();
		Double importe = Double.parseDouble(JOptionPane.showInputDialog("Inserte el importe de la nueva transferencia:"));
		String concepto = JOptionPane.showInputDialog("Inserte el texto de la nueva transferencia:");
		Long datetime = System.currentTimeMillis();
		Timestamp fecha = new Timestamp(datetime);

		Transferencia transferencia = new Transferencia(id_ordenante, id_beneficiario, importe, concepto, fecha);

		return transferencia;

	}
}