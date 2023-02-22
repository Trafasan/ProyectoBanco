package com.sandra.bancoArrayList.entidades;

public class Gestor extends Persona {
	private int id_gestor;
	
	public Gestor(String nombre, String apellido, String dni, String usuario, String password, String correo,
			int id_gestor) {
		super(nombre, apellido, dni, usuario, password, correo);
		this.id_gestor = id_gestor;
	}

	public Gestor(int id_gestor) {
		this.id_gestor = id_gestor;
	}

	public int getId_gestor() {
		return id_gestor;
	}

	public void setId_gestor(int id_gestor) {
		this.id_gestor = id_gestor;
	}

	@Override
	public String toString() {
		return "Datos del gestor "+id_gestor+"\n"
				+"Nombre: "+nombre+"\n"
				+"Apellido: "+apellido+"\n"
				+"DNI: "+dni+"\n"
				+"Usuario: "+usuario+"\n"
				+"Contraseña: "+password+"\n"
				+"Correo: "+correo+"\n"
				+"ID del gestor: "+id_gestor+"\n";
	}
}