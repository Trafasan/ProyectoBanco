package com.sandra.bancoDB.menus;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.sandra.bancoDB.databases.DBCliente;
import com.sandra.bancoDB.entidades.Cliente;
import com.sandra.bancoDB.utilidades.UICliente;

public class ClienteUpdateMenu {

	public static void switchUpdateCliente(Cliente updateCliente) {
		DBCliente dbCliente = new DBCliente();
		int id = updateCliente.getId_cliente();
		String update;
		try {
			update = JOptionPane.showInputDialog(null, "Seleccione el dato que quiere actualizar: ", "ACTUALIZACIÓN DEL CLIENTE "+id,
					JOptionPane.PLAIN_MESSAGE, null,
					new Object[] {"ID del gestor", "Nombre", "Apellido(s)", "DNI", "Usuario", "Contraseña", "Correo", "Saldo"}, null).toString();
		} catch (NullPointerException e) {
			update = "Volver atrás";
		}

		switch (update) {
		case "ID del gestor" -> {
			int id_gestor = UICliente.setId_gestorUpdate(updateCliente);
			if (UICliente.existeIdGestorUpdate(updateCliente, DBCliente.getId_gestores(), id_gestor)) {
				dbCliente.updateDatoCilente(UICliente.updateId_gestorCliente(updateCliente, id_gestor), "id_gestor");
				JOptionPane.showMessageDialog(null, "ID del gestor actualizada correctamente");
			}
			else JOptionPane.showMessageDialog(null, "No existe ningún gestor con ese ID\nSe le redigirá al menú Cliente", "ERROR", 0, new ImageIcon("src/main/java/com/sandra/bancoDB/images/preocupado.png"));
		} // Me he quedado por aquí en mi revisión
		case "Nombre" -> dbCliente.updateDatoCilente(UICliente.updateNombreCliente(updateCliente), "nombre");
		case "Apellido(s)" -> dbCliente.updateDatoCilente(UICliente.updateApellidoCliente(updateCliente), "apellido");
		case "DNI" -> dbCliente.updateDatoCilente(UICliente.updateDniCliente(updateCliente), "dni");
		case "Usuario" -> dbCliente.updateDatoCilente(UICliente.updateUsuarioCliente(updateCliente), "usuario");
		case "Contraseña" -> dbCliente.updateDatoCilente(UICliente.updateUsuarioCliente(updateCliente), "password");
		case "Correo" -> dbCliente.updateDatoCilente(UICliente.updateCorreoCliente(updateCliente), "correo");
		case "Saldo" -> dbCliente.updateDatoCilente(UICliente.updateSaldoCliente(updateCliente), "saldo");
		}
		if (!update.equals("ID del gestor") && !update.equals("Contraseña") && !update.equals("Volver atrás")) JOptionPane.showMessageDialog(null, update+" actualizado"+((update.equals("Apellido(s)")) ? "(s)": "")+" correctamente");
		if (!update.equals("Volver atrás")) switchUpdateCliente(updateCliente);
	}
}