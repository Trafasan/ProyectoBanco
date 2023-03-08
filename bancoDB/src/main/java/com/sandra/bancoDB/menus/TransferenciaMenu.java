package com.sandra.bancoDB.menus;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.sandra.bancoDB.databases.DBCliente;
import com.sandra.bancoDB.databases.DBTransferencia;
import com.sandra.bancoDB.entidades.Cliente;
import com.sandra.bancoDB.utilidades.UITransferencia;

public class TransferenciaMenu {

	public static void switchTransferencia() {
		DBCliente dbCliente = new DBCliente();
		DBTransferencia dbTransferencia = new DBTransferencia();
		
		ImageIcon preocupado = new ImageIcon("src/main/java/com/sandra/bancoDB/images/preocupado.png");
		
		String accion;
		try {
			accion = JOptionPane.showInputDialog(null, "Seleccione una opción: ", "MENÚ TRANSFERENCIAS",
					JOptionPane.PLAIN_MESSAGE, null, new Object[] { "Obtención de una transferencia",
							"Obtención de todas las transferencias", "Envío de una transferencia" }, null).toString();
		} catch (Exception e) {
			accion = "Volver atrás";
		}

		switch (accion) {
		case "Obtención de una transferencia" -> {
			dbTransferencia.leerUnaTransferencia(UITransferencia.obtencionTransferencia());
			switchTransferencia();
		}
		case "Obtención de todas las transferencias" -> {
			dbTransferencia.leerTransferencias();
			switchTransferencia();
		}
		case "Envío de una transferencia" -> {
			boolean existeId_ordenante, existeId_beneficiario;
			Cliente comprobacionId_ordenante, comprobacionId_beneficiario;
			int id_ordenante, id_beneficiario;
			do {
				do{
					comprobacionId_ordenante = UITransferencia.comprobarId_ordenante();
					id_ordenante = comprobacionId_ordenante.getId_cliente();
					existeId_ordenante = dbTransferencia.comprobarId_ordenante(comprobacionId_ordenante);
				}while(existeId_ordenante == false);
				
				do{
					comprobacionId_beneficiario = UITransferencia.comprobarId_beneficiario();
					id_beneficiario = comprobacionId_beneficiario.getId_cliente();
					existeId_beneficiario = dbTransferencia.comprobarId_beneficiario(comprobacionId_beneficiario);
				}while(existeId_beneficiario == false);
				
				if(id_ordenante == id_beneficiario) {
					JOptionPane.showMessageDialog(null, "El cliente beneficiario no puede ser el cliente ordenante", "ERROR", 0, preocupado);
				}
			}while(id_ordenante == id_beneficiario);
			double saldoOrdenante = dbTransferencia.leerSaldoOrdenante(comprobacionId_ordenante);
			double saldoBeneficiario = dbTransferencia.leerSaldoBeneficiario(comprobacionId_beneficiario);
			double importe = UITransferencia.comprobarImporte().getImporte();
			
			if(Double.compare(importe, saldoOrdenante) > 0) {
				JOptionPane.showMessageDialog(null, "El importe de la transferencia no puede ser mayor al saldo del cliente ordenante", "ERROR", 0, preocupado);
			} else {
				saldoOrdenante-=importe;
				saldoBeneficiario+=importe;
				dbTransferencia.enviarTransferencia(UITransferencia.envíoTransferencia(comprobacionId_ordenante, comprobacionId_beneficiario, importe));
				dbCliente.updateSaldoTransferencia(comprobacionId_ordenante, saldoOrdenante);
				dbCliente.updateSaldoTransferencia(comprobacionId_beneficiario, saldoBeneficiario);
				
				ImageIcon dinero = new ImageIcon("src/main/java/com/sandra/bancoDB/images/dinero.png");
			    JOptionPane.showMessageDialog(null, "Nuevo saldo del cliente ordenante (ID: "+id_ordenante+"): "+saldoOrdenante+"€"
						+ "\nNuevo saldo del cliente beneficiario (ID: "+id_beneficiario+"): "+saldoBeneficiario+"€", "TRANSFERENCIA REALIZADA CORRECTAMENTE", 1, dinero);
			}
			switchTransferencia();
		}
		case "Volver atrás" -> MainMenu.opciones();
		}
	}
}