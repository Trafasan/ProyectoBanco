package com.sandra.dbBank.entidades;

import lombok.Getter;

@Getter
public class Gestor extends Persona {
	
	private String oficina;
	
	public Gestor() {
		super();
		this.oficina = "";
	}
	
	public Gestor(Integer id) {
		super(id);
		this.oficina = "";
	}
	
	public Gestor(Integer id, String nombre, String apellido, String dni, String password, String correo, String oficina) {
		super(id, nombre, apellido, dni, password, correo);
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
				""", oficina);
	}

	@Override
	public String toStringAllData() {
		return super.toStringAllData()+String.format("""
				· Oficina: %s
				""", oficina);
	}
}
