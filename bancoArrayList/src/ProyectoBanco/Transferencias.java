package ProyectoBanco;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Transferencias {
	private int id_transferencia;
	private int id_ordenante;
	private int id_beneficiario;
	private double importe;
	private String concepto;
	private LocalDateTime fecha;
	
	public Transferencias(int id_transferencia, int id_ordenante, int id_beneficiario, double importe, String concepto,
			LocalDateTime fecha) {
		this.id_transferencia = id_transferencia;
		this.id_ordenante = id_ordenante;
		this.id_beneficiario = id_beneficiario;
		this.importe = importe;
		this.concepto = concepto;
		this.fecha = fecha;
	}
	
	public Transferencias(int id_transferencia) {
		this.id_transferencia = id_transferencia;
		this.id_ordenante = 0;
		this.id_beneficiario = 0;
		this.importe = 0.00;
		this.concepto = "Concepto generado automáticamente";
		this.fecha = LocalDateTime.of(2022, Month.NOVEMBER, 20, 0, 0, 0);
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

	public LocalDateTime getFecha() {return fecha;}
	public void setFecha(LocalDateTime fecha) {this.fecha = fecha;}

		// 15. Obtención de una transferencia
		public static void obtencionUnaTransferencia(List<Transferencias> transferencias, int x) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
			String fecha = transferencias.get(x).getFecha().format(formatter);
			System.out.println("Datos de la transferencia " + (x + 1));
			System.out.println("ID de la transferencia: " + transferencias.get(x).getId_transferencia());
			System.out.println("ID del ordenante: " + transferencias.get(x).getId_ordenante());
			System.out.println("ID del beneficiario: " + transferencias.get(x).getId_beneficiario());
			System.out.println("Importe: " + transferencias.get(x).getImporte());
			System.out.println("Concepto: " + transferencias.get(x).getConcepto());
			System.out.println("Fecha: " + fecha + "\n");
		}

		// 16. Obtención de todas las transferencias
		public static void obtencionVariasTransferencias(List<Transferencias> transferencias) {
				for (int x=0; x<transferencias.size(); x++) {
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
					String fecha = transferencias.get(x).getFecha().format(formatter);
					System.out.println("Datos de la transferencia " + (x + 1));
					System.out.println("ID de la transferencia: " + transferencias.get(x).getId_transferencia());
					System.out.println("ID del ordenante: " + transferencias.get(x).getId_ordenante());
					System.out.println("ID del beneficiario: " + transferencias.get(x).getId_beneficiario());
					System.out.println("Importe: " + transferencias.get(x).getImporte());
					System.out.println("Concepto: " + transferencias.get(x).getConcepto());
					System.out.println("Fecha: " + fecha + "\n");
				}
		}
		
		// 17. Envío de una transferencia
		public static void enviarTransferencia(List<Transferencias> transferencias, int id_ordenante, int id_beneficiario, double importe, String concepto,
				LocalDateTime fecha, List<Cliente> clientes) {
			int x = transferencias.size();
			transferencias.add(new Transferencias(x+1));
			transferencias.get(x).setId_ordenante(id_ordenante);
			transferencias.get(x).setId_beneficiario(id_beneficiario);
			transferencias.get(x).setImporte(importe);
			transferencias.get(x).setConcepto(concepto);
			transferencias.get(x).setFecha(fecha);
			
			double saldoO = clientes.get(id_ordenante-1).getSaldo();
			saldoO-=importe;
			clientes.get(id_ordenante-1).setSaldo(saldoO);
			
			double saldoB = clientes.get(id_beneficiario-1).getSaldo();
			saldoB+=importe;
			clientes.get(id_beneficiario-1).setSaldo(saldoB);
			
			System.out.println("Envío realizado correctamente");
		}
}
