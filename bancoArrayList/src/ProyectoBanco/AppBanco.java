package ProyectoBanco;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.project.functions.MainMenu;
import com.project.models.Cliente;
import com.project.models.Gestor;
import com.project.models.Mensaje;
import com.project.models.Transferencia;

public class AppBanco {
	private static List<Gestor> gestores = new ArrayList<Gestor> (List.of(
			new Gestor("Helena", "Salas", "12345678A", "Dossoles", "1357", "correoG1@gmail.com", 1),
			new Gestor("Jordi", "Fontanarrosa", "98765432Z", "Lumen124", "2468", "correoG2@gmail.com", 2)));
	
	private static List<Cliente> clientes = new ArrayList<Cliente> (List.of(
			new Cliente("Alejandro", "Planelles", "24680135G", "Elysium", "4728", "correoC1@gmail.com", 1, 1, 1500),
			new Cliente("María", "Verdú", "13579024V", "Darks013", "5039", "correoC2@gmail.com", 2, 2, 2000)));
	
	private static List<Mensaje> mensajes = new ArrayList<Mensaje> (List.of(new Mensaje(1)));
	
	private static List<Transferencia> transferencias = new ArrayList<Transferencia> (List.of(new Transferencia(1)));

	public static void main(String[] args) {
		Scanner sc = new Scanner (System.in);
		MainMenu.opciones(gestores, clientes, mensajes, transferencias, sc);
		sc.close();
	}
}