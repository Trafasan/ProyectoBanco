package com.sandra.dbBank.entidades;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class Mensaje {

	private Integer id;
	private Gestor gestor;
	private Cliente cliente;
	private String concepto;
	private String texto;
	private LocalDateTime fecha;
	private boolean leido;

	public Mensaje(Gestor gestor, Cliente cliente, String concepto, String texto) {
		this.id = null;
		this.gestor = gestor;
		this.cliente = cliente;
		this.concepto = concepto;
		this.texto = texto;
		this.fecha = null;
		this.leido = false;
	}

	public Mensaje(Gestor gestor, Cliente cliente) {
		this.id = null;
		this.gestor = gestor;
		this.cliente = cliente;
		this.concepto = null;
		this.texto = "Mensaje generado automáticamente";
		this.fecha = null;
		this.leido = false;
	}

	public void setConcepto(String concepto) {
		if (concepto.isBlank()) this.concepto = null;
		else this.concepto = concepto;
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
				· Gestor: %s
				· Cliente: %s%s
				· Concepto: %s
				· Texto: %s
				· Fecha: %s
				""", id, leido ? "L" : "No l",
				gestor == null ? "(Gestor eliminado)" : gestor.getNombre() + (gestor.getApellido() != null ? " "+gestor.getApellido() : ""),
				cliente.getNombre(), cliente.getApellido() != null ? " "+cliente.getApellido() : "", concepto, texto,
				fecha.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
	}
}
