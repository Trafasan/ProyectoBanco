package com.sandra.listBank.entidades;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

@Getter
public class Gestor extends Persona {
	public static List<Gestor> gestores = new ArrayList<Gestor> ();
	
	private String oficina;
	private List<Cliente> clientes = new ArrayList<Cliente>();
	
	public Gestor() {
		super();
		this.oficina = "";
	}
	
	public Gestor(String nombre, String apellido, String dni, String password, String correo, String oficina) {
		super(nombre, apellido, dni, password, correo);
		this.oficina = oficina;
	}

	public void setOficina(String oficina) {
		if (!oficina.matches("[A-Za-z ]+")) System.err.println("Este campo solo puede contener letras y espacios.");
		else if (oficina.length()<3) System.err.println("Este campo debe contener al menos 3 letras.");
		else this.oficina = oficina;
	}

	@Override
	public String toString() {
		return super.toString()+String.format("""
				· Oficina: %s
				· Número de clientes: %d
				""", oficina, clientes.size());
	}
}
