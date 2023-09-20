package com.sandra.dbBank.entidades;

import java.util.Objects;

import lombok.Getter;

@Getter
public abstract class Persona {
	
	protected Integer id;
	protected String nombre;
	protected String apellido;
	protected String dni;
	protected String password;
	protected String correo;
	
	public Persona() {
		this.id = null;
		this.nombre = "";
		this.apellido = "";
		this.dni = "";
		this.password = "";
		this.correo = "";
	}
	
	public Persona(Integer id) {
		this.id = id;
		this.nombre = "";
		this.apellido = "";
		this.dni = "";
		this.password = "";
		this.correo = "";
	}

	public Persona(Integer id, String nombre, String apellido, String dni, String password, String correo) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.password = password;
		this.correo = correo;
	}

	public void setNombre(String nombre) {
		if (!nombre.matches("[^0-9]+")) System.err.println("El nombre no puede contener números.");
		else if (nombre.length()<3) System.err.println("El nombre debe contener al menos 3 letras.");
		else this.nombre = nombre;
	}

	public void setApellido(String apellido) {
		if (apellido.isBlank()) this.apellido = null;
		else if (!apellido.matches("[^0-9]+")) System.err.println("Este campo no puede contener números.");
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
		Persona other = (Persona) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return String.format("""
				%s%s (%s | ID %d)
				· Correo: %s
				""", nombre, apellido != null ? " "+apellido : "", getClass().getSimpleName(), id, correo);
	}
	
	public String toStringAllData() {
		return String.format("""
				%s%s (%s | ID %d)
				· DNI: %s
				· Contraseña: %s
				· Correo: %s
				""", nombre, apellido != null ? " "+apellido : "", getClass().getSimpleName(), id, dni, password, correo);
	}
}
