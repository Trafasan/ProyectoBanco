package com.sandra.dbBank.entidades;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Cliente extends Persona {
	
	private Gestor gestor;
	
	public Cliente() {
		super();
		this.gestor = null;
	}
	
	public Cliente(Integer id) {
		super(id);
		this.gestor = null;
	}
	
	public Cliente(Integer id, String nombre, String apellido, String dni, String password, String correo, Gestor gestor) {
		super(id, nombre, apellido, dni, password, correo);
		this.gestor = gestor;
	}

	public void setGestor(Gestor gestor) {
		this.gestor = gestor;
	}

	@Override
	public String toString() {
		return super.toString()+String.format("""
				· Gestor: %s%s
				""", gestor.getNombre(), gestor.getApellido() != null ? " "+gestor.getApellido() : "");
	}

	@Override
	public String toStringAllData() {
		return super.toStringAllData()+String.format("""
				· Gestor: %s%s
				""", gestor.getNombre(), gestor.getApellido() != null ? " "+gestor.getApellido() : "");
	}

}
