package com.project.functions;

import java.util.List;
import java.util.Scanner;

import javax.swing.*;

import com.project.controllers.ClienteController;
import com.project.controllers.GestorController;
import com.project.controllers.LoginController;
import com.project.controllers.MensajeController;
import com.project.controllers.RegistroController;
import com.project.controllers.TransferenciaController;
import com.project.models.Cliente;
import com.project.models.Gestor;
import com.project.models.Mensaje;
import com.project.models.Transferencia;

public class MainMenu {
	public static void opciones(List<Gestor> gestores, List<Cliente> clientes, List<Mensaje> mensajes,
			List<Transferencia> transferencias, Scanner sc) {
		try {
			String opcion = JOptionPane.showInputDialog(null, "Inserte una opción: ", "MENÚ PRINCIPAL ARRAYLIST",
					JOptionPane.PLAIN_MESSAGE, null,
					new Object[] { "Gestores", "Clientes", "Mensajes", "Transferencias", "Login", "Registro" }, null).toString();

			switch (opcion) {
			case "Gestores" -> GestorController.switchGestor(gestores, clientes, mensajes, transferencias, sc);
			case "Clientes" -> ClienteController.switchCliente(gestores, clientes, mensajes, transferencias, sc);
			case "Mensajes" -> MensajeController.switchMensaje(gestores, clientes, mensajes, transferencias, sc);
			case "Transferencias" -> TransferenciaController.switchTransferencia(gestores, clientes, mensajes, transferencias, sc);
			case "Login" -> LoginController.switchLogin(gestores, clientes, mensajes, transferencias, sc);
			case "Registro" -> RegistroController.switchRegistro(gestores, clientes, mensajes, transferencias, sc); // Mejorando...
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Gracias por usar este programa", "HASTA PRONTO", 1,
					new ImageIcon("src/images/gracias.png"));
		}
	}
}