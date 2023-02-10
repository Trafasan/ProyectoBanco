package com.project.controllers;

import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

import com.project.models.Cliente;
import com.project.models.Gestor;
import com.project.utils.UICliente;

public class ClienteUpdateController {

	public static void switchUpdateCliente(List<Gestor> gestores, List<Cliente> clientes, Scanner sc, int id) {
		String update;
		try {
			update = JOptionPane.showInputDialog(null, "Seleccione dato que quiere actualizar: ", "ACTUALIZACIÓN DEL CLIENTE "+(id+1),
					JOptionPane.PLAIN_MESSAGE, null,
					new Object[] {"Nombre", "Apellido(s)", "DNI", "Usuario", "Contraseña", "Correo", "ID del gestor", "Saldo"}, null).toString();
		} catch (Exception e) {
			update = "Volver atrás";
		}

		switch (update) {
		case "Nombre" -> UICliente.updateNombreCliente(clientes, sc, id);
		case "Apellido(s)" -> UICliente.updateApellidoCliente(clientes, sc, id);
		case "DNI" -> UICliente.updateDniCliente(clientes, sc, id);
		case "Usuario" -> UICliente.updateUsuarioCliente(clientes, sc, id);
		case "Contraseña" -> UICliente.updatePasswordCliente(clientes, sc, id);
		case "Correo" -> UICliente.updateCorreoCliente(clientes, sc, id);
		case "ID del gestor" -> UICliente.updateIdGestorCliente(gestores, clientes, sc, id);
		case "Saldo" -> UICliente.updateSaldoCliente(clientes, sc, id);
		}
		if (!update.equals("Volver atrás")  && !update.equals("ID del gestor")) System.out.println("Actualización realizada correctamente");
	}
}
