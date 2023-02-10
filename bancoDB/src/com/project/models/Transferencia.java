package com.project.models;

import java.sql.Timestamp;

public class Transferencia {
	private int id_transferencia;
	private int id_ordenante;
	private int id_beneficiario;
	private double importe;
	private String concepto;
	private Timestamp fecha;
	
	public Transferencia(int id_transferencia, int id_ordenante, int id_beneficiario, double importe, String concepto,
			Timestamp fecha) {
		this.id_transferencia = id_transferencia;
		this.id_ordenante = id_ordenante;
		this.id_beneficiario = id_beneficiario;
		this.importe = importe;
		this.concepto = concepto;
		this.fecha = fecha;
	}
	
	public Transferencia(int id_ordenante, int id_beneficiario, double importe, String concepto,
			Timestamp fecha) {
		this.id_ordenante = id_ordenante;
		this.id_beneficiario = id_beneficiario;
		this.importe = importe;
		this.concepto = concepto;
		this.fecha = fecha;
	}
	
	public Transferencia(double importe) {
		this.importe = importe;
	}
	
	public Transferencia(int id_transferencia) {
		this.id_transferencia = id_transferencia;
	}
	
	public int getId_transferencia() {return id_transferencia;}
	public void setId_transferencia(int id_transferencia) {this.id_transferencia = id_transferencia;}

	public int getId_ordenante() {return id_ordenante;}
	public void setId_ordenante(int id_ordenante) {this.id_ordenante = id_ordenante;}

	public int getId_beneficiario() {return id_beneficiario;}
	public void setId_beneficiario(int id_beneficiario) {this.id_beneficiario = id_beneficiario;}

	public double getImporte() {return importe;}
	public void setImporte(double importe) {this.importe = importe;}

	public String getConcepto() {return concepto;}
	public void setConcepto(String concepto) {this.concepto = concepto;}

	public Timestamp getFecha() {return fecha;}
	public void setFecha(Timestamp fecha) {this.fecha = fecha;}
}
