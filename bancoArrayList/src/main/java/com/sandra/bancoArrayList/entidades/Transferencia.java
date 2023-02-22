package com.sandra.bancoArrayList.entidades;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;

public class Transferencia {
	private int id_transferencia;
	private int id_ordenante;
	private int id_beneficiario;
	private double importe;
	private String concepto;
	private LocalDateTime fecha;

	public Transferencia(int id_transferencia, int id_ordenante, int id_beneficiario, double importe, String concepto,
			LocalDateTime fecha) {
		this.id_transferencia = id_transferencia;
		this.id_ordenante = id_ordenante;
		this.id_beneficiario = id_beneficiario;
		this.importe = importe;
		this.concepto = concepto;
		this.fecha = fecha;
	}

	public Transferencia(int id_transferencia) {
		this.id_transferencia = id_transferencia;
		this.id_ordenante = 0;
		this.id_beneficiario = 0;
		this.importe = 0.00;
		this.concepto = "Concepto generado automáticamente";
		this.fecha = LocalDateTime.of(2022, Month.NOVEMBER, 20, 0, 0, 0);
	}

	public int getId_transferencia() {
		return id_transferencia;
	}

	public void setId_transferencia(int id_transferencia) {
		this.id_transferencia = id_transferencia;
	}

	public int getId_ordenante() {
		return id_ordenante;
	}

	public void setId_ordenante(int id_ordenante) {
		this.id_ordenante = id_ordenante;
	}

	public int getId_beneficiario() {
		return id_beneficiario;
	}

	public void setId_beneficiario(int id_beneficiario) {
		this.id_beneficiario = id_beneficiario;
	}

	public double getImporte() {
		return importe;
	}

	public void setImporte(double importe) {
		this.importe = importe;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	@Override
	public String toString() {
		return "Datos de la transferencia "+id_transferencia+"\n"
				+"ID de la transferencia: "+id_transferencia+"\n"
				+"ID del ordenante: "+id_ordenante+"\n"
				+"ID del beneficiario: "+id_beneficiario+"\n"
				+"Importe: "+importe+"\n"
				+"Concepto: "+concepto+"\n"
				+"Fecha: "+fecha.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
	}
	
}
