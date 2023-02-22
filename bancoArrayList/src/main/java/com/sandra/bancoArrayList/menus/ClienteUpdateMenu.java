package com.sandra.bancoArrayList.menus;

import java.util.List;

import javax.swing.JOptionPane;

import com.sandra.bancoArrayList.entidades.Cliente;
import com.sandra.bancoArrayList.entidades.Gestor;
import com.sandra.bancoArrayList.utilidades.ClienteUtils;

public class ClienteUpdateMenu {

	public static void switchUpdateCliente(List<Gestor> gestores, List<Cliente> clientes, int id) {
		String update;
		try {
			update = JOptionPane.showInputDialog(null, "Seleccione dato que quiere actualizar: ", "ACTUALIZACIÓN DEL CLIENTE "+(id+1),
					JOptionPane.PLAIN_MESSAGE, null,
					new Object[] {"Nombre", "Apellido(s)", "DNI", "Usuario", "Contraseña", "Correo", "ID del gestor", "Saldo"}, null).toString();
		} catch (Exception e) {
			update = "Volver atrás";
		}

		switch (update) {
		case "Nombre" -> ClienteUtils.updateNombreCliente(clientes, id);
		case "Apellido(s)" -> ClienteUtils.updateApellidoCliente(clientes, id);
		case "DNI" -> ClienteUtils.updateDniCliente(clientes, id);
		case "Usuario" -> ClienteUtils.updateUsuarioCliente(clientes, id);
		case "Contraseña" -> ClienteUtils.updatePasswordCliente(clientes, id);
		case "Correo" -> ClienteUtils.updateCorreoCliente(clientes, id);
		case "ID del gestor" -> ClienteUtils.updateIdGestorCliente(gestores, clientes, id);
		case "Saldo" -> ClienteUtils.updateSaldoCliente(clientes, id);
		}
		if (!update.equals("Volver atrás")  && !update.equals("ID del gestor")) System.out.println("Actualización realizada correctamente");
	}
}
