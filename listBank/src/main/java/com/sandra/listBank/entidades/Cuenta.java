package com.sandra.listBank.entidades;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import lombok.Getter;

@Getter
public class Cuenta {
	public static List<Cuenta> cuentas = new ArrayList<Cuenta> ();
	private static int cuentasCreadas = 0;
	
	private int id;
	private Cliente cliente;
	private BigDecimal saldo;
	{
		cuentasCreadas++;
	}
	
	public Cuenta(Cliente cliente) {
		this.id = cuentasCreadas;
		this.cliente = cliente;
		this.saldo = new BigDecimal(1000.00);
		cliente.getCuentas().add(this);
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
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
		Cuenta other = (Cuenta) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return String.format("""
				Cuenta nº%d
				· Cliente: %s %s
				· Saldo: %.2f€
				""", id, cliente.getNombre(), cliente.getApellido(), saldo);
	}
	
}
