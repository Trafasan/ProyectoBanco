package com.project.models;

public class Cliente extends Persona{
	private int id_cliente;
	private int id_gestor;
	private double saldo;
	
	public Cliente(String nombre, String apellido, String dni, String usuario, String password, String correo, int id_cliente, int id_gestor, double saldo) {
		super(nombre, apellido, dni, usuario, password, correo);
		this.setId_cliente(id_cliente);
		this.setId_gestor(id_gestor);
		this.setSaldo(saldo);
	}
	
	public Cliente(int id_cliente) {
		this.id_cliente = id_cliente;
		this.id_gestor = 0;
		this.saldo = 1000;
	}

	public int getId_cliente() {return id_cliente;}
	public void setId_cliente(int id_cliente) {this.id_cliente = id_cliente;}

	public int getId_gestor() {return id_gestor;}
	public void setId_gestor(int id_gestor) {this.id_gestor = id_gestor;}

	public double getSaldo() {return saldo;}
	public void setSaldo(double saldo) {this.saldo = saldo;}

	@Override
	public String toString() {
		return "Datos del cliente "+id_cliente+"\n"
		+"Nombre: "+nombre+"\n"
		+"Apellido: "+apellido+"\n"
		+"DNI: "+dni+"\n"
		+"Usuario: "+usuario+"\n"
		+"Contraseña: "+password+"\n"
		+"Correo: "+correo+"\n"
		+"ID del cliente: "+id_cliente+"\n"
		+"ID del gestor: "+id_gestor+"\n"
		+"Saldo: "+saldo;
	}	
	
	
}
