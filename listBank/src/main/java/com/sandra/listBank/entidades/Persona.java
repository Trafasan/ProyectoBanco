package com.sandra.listBank.entidades;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import lombok.Getter;

@Getter
public abstract class Persona {
	protected String nombre;
	protected String apellido;
	protected String dni;
	protected String password;
	protected String correo;
	private List<Mensaje> mensajes = new ArrayList<Mensaje>();
	
	public Persona() {
		this.nombre = "";
		this.apellido = "";
		this.dni = "";
		this.password = "";
		this.correo = "";
	}

	public Persona(String nombre, String apellido, String dni, String password, String correo) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.password = password;
		this.correo = correo;
	}

	public void setNombre(String nombre) {
		if (!nombre.matches("[^0-9 ]+")) System.err.println("El nombre no puede contener números.");
		else if (nombre.length()<3) System.err.println("El nombre debe contener al menos 3 letras.");
		else this.nombre = nombre;
	}

	public void setApellido(String apellido) {
		if (!apellido.matches("[^0-9]+")) System.err.println("Este campo no puede contener números.");
		else if (apellido.length()<3) System.err.println("Este campo debe contener al menos 3 letras.");
		else this.apellido = apellido;
	}

	public void setDni(String dni) {
		if (!dni.matches("\\d{8}[A-Z]{1}")) System.err.println("El DNI no tiene el formato correcto.");
		else this.dni = dni;
	}

	public void setPassword(String password) {
		if (!password.matches("\\d{4}")) System.err.println("La contraseña debe ser una serie de cuatro números.");
		else this.password = password;
	}

	public void setCorreo(String correo) {
		correo = correo.toLowerCase();
		if (!correo.matches("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$")) System.err.println("El correo no tiene el formato correcto.");
		else this.correo = correo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dni);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Persona other = (Persona) obj;
		return Objects.equals(dni, other.dni);
	}

	@Override
	public String toString() {
		return String.format("""
				%s %s (%s)
				· DNI: %s
				· Correo: %s
				""", nombre, apellido, getClass().getSimpleName(), dni, correo);
	}
}
