package com.project.utils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.project.models.Cliente;
import com.project.models.Transferencia;

public class UITransferencia {
	// Obtención de una transferencia
	public static void getTransferencia(List<Transferencia> transferencias, Scanner sc) {
		System.out.print("Inserte el ID de la transferencia a obtener: ");
		try {
			System.out.println(transferencias.get(Integer.parseInt(sc.nextLine()) - 1));
		} catch (IndexOutOfBoundsException e) {
			System.err.println(
					"No se ha encontrado la transferencia con la ID introducida. Se le redigirá al Menú Transferencias.");
		}
	}

	// Obtención de todas las transferencias
	public static void getTransferencias(List<Transferencia> transferencias) {
		for (int i = 0; i < transferencias.size(); i++)
			System.out.println(transferencias.get(i));
	}

	// Envío de una transferencia

	// Comprobación previa de que existe el cliente con ese ID (ordenante y
	// beneficiario)
	public static int comprobarId(List<Cliente> clientes, Scanner sc, String tipo) {
		System.out.print("Proporciona la ID del cliente " + tipo + ": ");
		return Integer.parseInt(sc.nextLine()) - 1;
	}

	// Envío de la transferencia con la actualización de los saldos
	public static void addTransferencia(List<Transferencia> transferencias, List<Cliente> clientes, int id_ordenante,
			int id_beneficiario, Scanner sc) {
		int id = transferencias.size();
		transferencias.add(new Transferencia(id + 1));
		transferencias.get(id).setId_ordenante(++id_ordenante);
		transferencias.get(id).setId_beneficiario(++id_beneficiario);
		if(id_ordenante == id_beneficiario) {
			System.err.println("El cliente beneficiario no puede ser el cliente ordenante");
			System.err.println("No se pudo completar la transferencia. Se le redigirá al Menú Transferencias.");
			transferencias.remove(id);
		}
		else {
			System.out.print("Introduzca el importe: ");
			double importe = Double.parseDouble(sc.nextLine());
			if (superaImporteMaximo(clientes, id_ordenante, importe, sc)) {
				System.err.println("No se pudo completar la transferencia. Se le redigirá al Menú Transferencias.");
				transferencias.remove(id);
			} else {
				transferencias.get(id).setImporte(importe);
				System.out.print("Introduzca el concepto: ");
				transferencias.get(id).setConcepto(sc.nextLine());
				transferencias.get(id).setFecha(LocalDateTime.now());
				cambiarSaldosClientes(clientes, id_ordenante, id_beneficiario, importe);
			}
		}

	}

	private static boolean superaImporteMaximo(List<Cliente> clientes, int id_ordenante, double importe, Scanner sc) {
		double importeMax = clientes.get(id_ordenante).getSaldo();
		if (importe > importeMax)
			System.err.printf("El importe no puede ser mayor a su saldo. Su saldo es %.2f€", importeMax);
		return (importe > importeMax);
	}
	
	private static void cambiarSaldosClientes (List<Cliente> clientes, int id_ordenante, int id_beneficiario, double importe) {
		double saldoO = clientes.get(--id_ordenante).getSaldo();
		clientes.get(id_ordenante).setSaldo(saldoO-=importe);
		double saldoB = clientes.get(--id_beneficiario).getSaldo();
		clientes.get(id_beneficiario).setSaldo(saldoB+=importe);
		JOptionPane.showMessageDialog(null, "Nuevo saldo del cliente ordenante (ID: "+(++id_ordenante)+"): "+saldoO+"€"
				+ "\nNuevo saldo del cliente beneficiario (ID: "+(++id_beneficiario)+"): "+saldoB+"€", "TRANSFERENCIA REALIZADA CORRECTAMENTE", 1, new ImageIcon("src/images/dinero.png"));
		
	}
}