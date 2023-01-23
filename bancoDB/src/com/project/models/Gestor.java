package com.project.models;

public class Gestor extends Persona {
	private int id_gestor;

	public Gestor(int id_gestor, String nombre, String apellido, String dni, String usuario, String password,
			String correo) {
		super(nombre, apellido, dni, usuario, password, correo);
		this.id_gestor = id_gestor;
	}

	public Gestor(String nombre, String apellido, String dni, String usuario, String password, String correo) {
		super(nombre, apellido, dni, usuario, password, correo);
	}
	
	public Gestor(int id_gestor, String actualizar) {
		super(actualizar);
		this.id_gestor = id_gestor;
	}

	public Gestor(int id_gestor) {
		this.id_gestor = id_gestor;
	}
	
	public Gestor(String usuario) {
		super(usuario);
	}
	
	public Gestor() {

	}

	public int getId_gestor() {
		return id_gestor;
	}

	public void setId_gestor(int id_gestor) {
		this.id_gestor = id_gestor;
	}
}
