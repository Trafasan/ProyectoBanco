package com.sandra.listBank.entidades;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Cliente extends Persona {
	public static List<Cliente> clientes = new ArrayList<Cliente> ();
	
	private Gestor gestor;
	private List<Cuenta> cuentas = new ArrayList<Cuenta>();
	private Set<Transferencia> transferencias = new HashSet<Transferencia>();
	
	public Cliente() {
		super();
		this.gestor = null;
	}
	
	public Cliente(Gestor gestor) {
		super();
		this.gestor = gestor;
		gestor.getClientes().add(this);
	}
	
	public Cliente(String nombre, String apellido, String dni, String password, String correo, Gestor gestor) {
		super(nombre, apellido, dni, password, correo);
		this.gestor = gestor;
		gestor.getClientes().add(this);
	}

	public void setGestor(Gestor gestor) {
		this.gestor = gestor;
		gestor.getClientes().add(this);
	}

	@Override
	public String toString() {
		return super.toString()+String.format("""
				· Gestor: %s %s
				· Saldo total: %.2f€
				""", gestor.getNombre(), gestor.getApellido(), cuentas.stream().mapToDouble(c -> c.getSaldo()).sum());
	}

}
