package com.sandra.listBank.entidades;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Mensaje {
	private static int numMensajes = 0;
	
	private int id;
	private Gestor gestor;
	private Cliente cliente;
	private String concepto;
	private String texto;
	private LocalDateTime fecha;
	private boolean leido;
	
	{
		numMensajes++;
	}

	public Mensaje(Gestor gestor, Cliente cliente, String concepto, String texto) {
		this.id = numMensajes;
		this.gestor = gestor;
		this.cliente = cliente;
		this.concepto = concepto;
		this.texto = texto;
		this.fecha = LocalDateTime.now();
		this.leido = false;
    	gestor.getMensajes().add(this);
    	cliente.getMensajes().add(this);
	}

	public Mensaje(Gestor gestor, Cliente cliente) {
		this.id = numMensajes;
		this.gestor = gestor;
		this.cliente = cliente;
		this.concepto = "Concepto generado automáticamente";
		this.texto = "Mensaje generado automáticamente";
		this.fecha = LocalDateTime.now();
		this.leido = false;
    	gestor.getMensajes().add(this);
    	cliente.getMensajes().add(this);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Mensaje other = (Mensaje) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return String.format("""
				Datos del mensaje %d (%seído)
				· Gestor: %s %s
				· Cliente: %s %s
				· Concepto: %s
				· Texto: %s
				· Fecha: %s
				""", id, leido ? "L" : "No l", gestor.getNombre(), gestor.getApellido(), cliente.getNombre(),
						cliente.getApellido(), concepto, texto, fecha.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
	}
}
