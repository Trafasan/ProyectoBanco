package com.sandra.bancoDB.entidades;

import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;

public class Mensaje {
	private int id_mensaje;
	private int id_gestor;
	private int id_cliente;
	private String texto;
	private Timestamp fecha;

	public Mensaje(int id_mensaje, int id_gestor, int id_cliente, String texto, Timestamp fecha) {
		this.id_mensaje = id_mensaje;
		this.id_gestor = id_gestor;
		this.id_cliente = id_cliente;
		this.texto = texto;
		this.fecha = fecha;
	}
	
	public Mensaje(int id_gestor, int id_cliente, String texto, Timestamp fecha) {
		this.id_gestor = id_gestor;
		this.id_cliente = id_cliente;
		this.texto = texto;
		this.fecha = fecha;
	}
	
	public Mensaje(int id_mensaje) {
		this.id_mensaje = id_mensaje;
	}

	public int getId_mensaje() {return id_mensaje;}
	public void setId_mensaje(int id_mensaje) {this.id_mensaje = id_mensaje;}

	public int getId_gestor() {return id_gestor;}
	public void setId_gestor(int id_gestor) {this.id_gestor = id_gestor;}
	
	public int getId_cliente() {return id_cliente;}
	public void setId_cliente(int id_destino) {this.id_cliente = id_destino;}

	public String getTexto() {return texto;}
	public void setTexto(String texto) {this.texto = texto;}

	public Timestamp getFecha() {return fecha;}
	public void setFecha(Timestamp fecha) {this.fecha = fecha;}

	@Override
	public String toString() {
		return "Datos del mensaje "+id_mensaje+"\n"
				+"ID del mensaje: "+id_mensaje+"\n"
				+"ID del gestor: "+id_gestor+"\n"
				+"ID del cliente: "+id_cliente+"\n"
				+"Texto: "+texto+"\n"
				+"Fecha: "+fecha.toLocalDateTime().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
	}
}
