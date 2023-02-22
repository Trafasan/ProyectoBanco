package com.sandra.bancoArrayList;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.sandra.bancoArrayList.entidades.Cliente;
import com.sandra.bancoArrayList.entidades.Gestor;
import com.sandra.bancoArrayList.entidades.Mensaje;
import com.sandra.bancoArrayList.entidades.Transferencia;
import com.sandra.bancoArrayList.menus.ClienteMenu;
import com.sandra.bancoArrayList.menus.GestorMenu;
import com.sandra.bancoArrayList.menus.LoginMenu;
import com.sandra.bancoArrayList.menus.MensajeMenu;
import com.sandra.bancoArrayList.menus.RegistroMenu;
import com.sandra.bancoArrayList.menus.TransferenciaMenu;

public class App {
	private static Scanner sc;
	private static List<Gestor> gestores = new ArrayList<Gestor> (List.of(
			new Gestor("Helena", "Salas", "12345678A", "Dossoles", "1357", "correoG1@gmail.com", 1),
			new Gestor("Jordi", "Fontanarrosa", "98765432Z", "Lumen124", "2468", "correoG2@gmail.com", 2)));
	
	private static List<Cliente> clientes = new ArrayList<Cliente> (List.of(
			new Cliente("Alejandro", "Planelles", "24680135G", "Elysium", "4728", "correoC1@gmail.com", 1, 1, 1500),
			new Cliente("María", "Verdú", "13579024V", "Darks013", "5039", "correoC2@gmail.com", 2, 2, 2000)));
	
	private static List<Mensaje> mensajes = new ArrayList<Mensaje> (List.of(new Mensaje(1)));
	
	private static List<Transferencia> transferencias = new ArrayList<Transferencia> (List.of(new Transferencia(1)));
	
	public static void mainMenu(List<Gestor> gestores, List<Cliente> clientes, List<Mensaje> mensajes, List<Transferencia> transferencias) {
		try {
			String opcion = JOptionPane.showInputDialog(null, "Inserte una opción: ", "MENÚ PRINCIPAL ARRAYLIST",
					JOptionPane.PLAIN_MESSAGE, null,
					new Object[] { "Gestores", "Clientes", "Mensajes", "Transferencias", "Login", "Registro" }, null).toString();

			switch (opcion) {
			case "Gestores" -> GestorMenu.switchGestor(gestores, clientes, mensajes, transferencias);
			case "Clientes" -> ClienteMenu.switchCliente(gestores, clientes, mensajes, transferencias);
			case "Mensajes" -> MensajeMenu.switchMensaje(gestores, clientes, mensajes, transferencias);
			case "Transferencias" -> TransferenciaMenu.switchTransferencia(gestores, clientes, mensajes, transferencias);
			case "Login" -> LoginMenu.switchLogin(gestores, clientes, mensajes, transferencias);
			case "Registro" -> RegistroMenu.switchRegistro(gestores, clientes, mensajes, transferencias);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Gracias por usar este programa", "HASTA PRONTO", 1,
					new ImageIcon("src/main/java/com/sandra/bancoArrayList/images/gracias.png"));
		}
		
	}
	
    public static void main(String[] args) {
    	sc = new Scanner (System.in);
        mainMenu(gestores, clientes, mensajes, transferencias);
        sc.close();
    }
}
