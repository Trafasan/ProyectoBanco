package ProyectoBanco;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Mensajes {
	private int id_mensaje;
	private int id_gestor;
	private int id_cliente;
	private String texto;
	private LocalDateTime fecha;

	public Mensajes(int id_mensaje, int id_gestor, int id_cliente, String texto, LocalDateTime fecha) {
		this.id_mensaje = id_mensaje;
		this.id_gestor = id_gestor;
		this.id_cliente = id_cliente;
		this.texto = texto;
		this.fecha = fecha;
	}

	public Mensajes(int id_mensaje) {
		this.id_mensaje = id_mensaje;
		this.id_gestor = 0;
		this.id_cliente = 0;
		this.texto = "Mensaje generado automáticamente";
		this.fecha = LocalDateTime.of(2022, Month.NOVEMBER, 20, 0, 0, 0);
	}

	public int getId_mensaje() {return id_mensaje;}
	public void setId_mensaje(int id_mensaje) {this.id_mensaje = id_mensaje;}

	public int getId_gestor() {return id_gestor;}
	public void setId_gestor(int id_gestor) {this.id_gestor = id_gestor;}
	
	public int getId_cliente() {return id_cliente;}
	public void setId_cliente(int id_cliente) {this.id_cliente = id_cliente;}

	public String getTexto() {return texto;}
	public void setTexto(String texto) {this.texto = texto;}

	public LocalDateTime getFecha() {return fecha;}
	public void setFecha(LocalDateTime fecha) {this.fecha = fecha;}

	// 12. Obtención de un mensaje
	public static void obtencionUnMensaje(List<Mensajes> mensajes, int x) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		String fecha = mensajes.get(x).getFecha().format(formatter);
		System.out.println("Datos del mensaje " + (x + 1));
		System.out.println("ID del mensaje: " + mensajes.get(x).getId_mensaje());
		System.out.println("ID del gestor: " + mensajes.get(x).getId_gestor());
		System.out.println("ID del cliente: " + mensajes.get(x).getId_cliente());
		System.out.println("Texto: " + mensajes.get(x).getTexto());
		System.out.println("Fecha: " + fecha + "\n");
	}

	// 13. Obtención de todos los mensajes
	public static void obtencionVariosClientes(List<Mensajes> mensajes) {
			for (int x=0; x<mensajes.size(); x++) {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
				String fecha = mensajes.get(x).getFecha().format(formatter);
				System.out.println("Datos del mensaje "+(x+1));
				System.out.println("ID del mensaje: " + mensajes.get(x).getId_mensaje());
				System.out.println("ID del gestor: " + mensajes.get(x).getId_gestor());
				System.out.println("ID del cliente: " + mensajes.get(x).getId_cliente());
				System.out.println("Texto: " + mensajes.get(x).getTexto());
				System.out.println("Fecha: " + fecha + "\n");
			}
	}
	
	// 14. Envío de un mensaje
	public static void enviarMensaje(List<Mensajes> mensajes, int id_gestor, int id_cliente, String texto, LocalDateTime fecha) {
		int x = mensajes.size();
		mensajes.add(new Mensajes(x+1));
		mensajes.get(x).setId_gestor(id_gestor);
		mensajes.get(x).setId_cliente(id_cliente);
		mensajes.get(x).setTexto(texto);
		mensajes.get(x).setFecha(fecha);
		System.out.println("Envío realizado correctamente");
	}
}
