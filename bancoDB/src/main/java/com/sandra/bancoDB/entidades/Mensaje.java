package com.sandra.bancoDB.entidades;

import java.sql.Timestamp;

public class Mensaje {
	private int id_mensaje;
	private int id_origen;
	private int id_destino;
	private String texto;
	private Timestamp fecha;

	public Mensaje(int id_mensaje, int id_origen, int id_destino, String texto, Timestamp fecha) {
		this.id_mensaje = id_mensaje;
		this.id_origen = id_origen;
		this.id_destino = id_destino;
		this.texto = texto;
		this.fecha = fecha;
	}
	
	public Mensaje(int id_origen, int id_destino, String texto, Timestamp fecha) {
		this.id_origen = id_origen;
		this.id_destino = id_destino;
		this.texto = texto;
		this.fecha = fecha;
	}
	
	public Mensaje(int id_mensaje) {
		this.id_mensaje = id_mensaje;
	}

	public int getId_mensaje() {return id_mensaje;}
	public void setId_mensaje(int id_mensaje) {this.id_mensaje = id_mensaje;}

	public int getId_origen() {return id_origen;}
	public void setId_origen(int id_origen) {this.id_origen = id_origen;}
	
	public int getId_destino() {return id_destino;}
	public void setId_destino(int id_destino) {this.id_destino = id_destino;}

	public String getTexto() {return texto;}
	public void setTexto(String texto) {this.texto = texto;}

	public Timestamp getFecha() {return fecha;}
	public void setFecha(Timestamp fecha) {this.fecha = fecha;}
}
