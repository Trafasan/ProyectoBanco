package com.sandra.listBank.entidades;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Transferencia {
	private static int numTransferencias = 0;
	
	private int id;
	private Cuenta cuenta_ordenante;
	private Cuenta cuenta_beneficiaria;
	private BigDecimal importe;
	private String concepto;
	private LocalDateTime fecha;
	
	{
		numTransferencias++;
	}

	public Transferencia(Cuenta cuenta_ordenante, Cuenta cuenta_beneficiaria, BigDecimal importe, String concepto) {
		this.id = numTransferencias;
		this.cuenta_ordenante = cuenta_ordenante;
		this.cuenta_beneficiaria = cuenta_beneficiaria;
		this.importe = importe;
		this.concepto = concepto;
		this.fecha = LocalDateTime.now();
		cuenta_ordenante.getCliente().getTransferencias().add(this);
		cuenta_beneficiaria.getCliente().getTransferencias().add(this);
		cuenta_ordenante.setSaldo(cuenta_ordenante.getSaldo().subtract(importe));
		cuenta_beneficiaria.setSaldo(cuenta_beneficiaria.getSaldo().add(importe));
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
				· Ordenante: %s %s (Cuenta nº%d)
				· Beneficiario: %s %s (Cuenta nº%d)
				· Importe: %.2f€
				· Concepto: %s
				· Fecha: %s
				""", id, cuenta_ordenante.getCliente().getNombre(), cuenta_ordenante.getCliente().getApellido(), cuenta_ordenante.getId(),
				cuenta_beneficiaria.getCliente().getNombre(), cuenta_beneficiaria.getCliente().getApellido(), cuenta_beneficiaria.getId(),
				importe, concepto == null ? "(Sin concepto)" : concepto, fecha.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
	}
}
