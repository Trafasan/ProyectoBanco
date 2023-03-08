package com.sandra.bancoDB.entidades;

public class Persona {
	protected String nombre;
	protected String apellido;
	protected String dni;
	protected String usuario;
	protected String password;
	protected String correo;
	protected String updateDato;
	
	public Persona(String nombre, String apellido, String dni, String usuario, String password, String correo) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.usuario = usuario;
		this.password = password;
		this.correo = correo;
	}
	
	public Persona(String updateDato) {
		this.updateDato = updateDato;
	}
	
	public Persona() {
		this.nombre = "Nombre";
		this.apellido = "Apellido";
		this.dni = "12345678A";
		this.usuario = "Usuario";
		this.password = "Contraseña";
		this.correo = "correo@gmail.com";
	}

	public String getNombre() {return nombre;}
	public void setNombre(String nombre) {this.nombre = nombre;}

	public String getApellido() {return apellido;}
	public void setApellido(String apellido) {this.apellido = apellido;}

	public String getDni() {return dni;}
	public void setDni(String dni) {this.dni = dni;}

	public String getUsuario() {return usuario;}
	public void setUsuario(String usuario) {this.usuario = usuario;}

	public String getPassword() {return password;}
	public void setPassword(String password) {this.password = password;}

	public String getCorreo() {return correo;}
	public void setCorreo(String correo) {this.correo = correo;}
	
	public String getUpdateDato() {return updateDato;}
	public void setUpdateDato(String actualizar) {this.updateDato = actualizar;}
	
	
	
}
