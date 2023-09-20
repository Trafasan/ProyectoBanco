package com.sandra.dbBank.entidades;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Transferencia {
	
	private Integer id;
	private Cuenta cuenta_ordenante;
	private Cuenta cuenta_beneficiaria;
	private double importe;
	private String concepto;
	private LocalDateTime fecha;

	public Transferencia(Cuenta cuenta_ordenante, Cuenta cuenta_beneficiaria, double importe, String concepto) {
		this.id = null;
		this.cuenta_ordenante = cuenta_ordenante;
		this.cuenta_beneficiaria = cuenta_beneficiaria;
		this.importe = importe;
		this.concepto = concepto;
		this.fecha = LocalDateTime.now();
	}

	public Transferencia(Integer id, Cuenta cuenta_ordenante, Cuenta cuenta_beneficiaria, double importe, String concepto, LocalDateTime fecha) {
		this.id = id;
		this.cuenta_ordenante = cuenta_ordenante;
		this.cuenta_beneficiaria = cuenta_beneficiaria;
		this.importe = importe;
		this.concepto = concepto;
		this.fecha = fecha;
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
		Transferencia other = (Transferencia) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return String.format("""
				Datos de la transferencia %d
				· Ordenante: %s
				· Beneficiario: %s
				· Importe: %.2f€
				· Concepto: %s
				· Fecha: %s
				""", id, cuenta_ordenante == null ? "(Cuenta eliminada)" : datosCuenta(cuenta_ordenante),
				cuenta_beneficiaria == null ? "(Cuenta eliminada)" : datosCuenta(cuenta_beneficiaria), importe,
				concepto == null ? "(Sin concepto)" : concepto, fecha.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
	}
	
	private String datosCuenta(Cuenta cuenta) {
		return String.format("%s%s (Cuenta nº%d)", cuenta.getCliente().getNombre(),
				cuenta.getCliente().getApellido() != null ? " "+cuenta.getCliente().getApellido() : "", cuenta.getId());
	}
}
