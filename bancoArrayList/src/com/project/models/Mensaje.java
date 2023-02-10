package com.project.models;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;

public class Mensaje {
	private int id_mensaje;
	private int id_gestor;
	private int id_cliente;
	private String texto;
	private LocalDateTime fecha;

	public Mensaje(int id_mensaje, int id_gestor, int id_cliente, String texto, LocalDateTime fecha) {
		this.id_mensaje = id_mensaje;
		this.id_gestor = id_gestor;
		this.id_cliente = id_cliente;
		this.texto = texto;
		this.fecha = fecha;
	}

	public Mensaje(int id_mensaje) {
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

	@Override
	public String toString() {
		return "Datos del mensaje "+id_mensaje+"\n"
				+"ID del mensaje: "+id_mensaje+"\n"
				+"ID del gestor: "+id_gestor+"\n"
				+"ID del cliente: "+id_cliente+"\n"
				+"Texto: "+texto+"\n"
				+"Fecha: "+fecha.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
	}
	
}
