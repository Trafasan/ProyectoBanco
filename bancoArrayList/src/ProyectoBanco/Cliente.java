package ProyectoBanco;

import java.util.List;

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
	
	// 7. Inserción de un cliente
	public static void insertarCliente(List<Cliente> clientes, String nombre, String apellido, String dni, String usuario, String password, String correo, int id_gestor, double saldo) {
		int x = clientes.size();
		clientes.add(new Cliente(x+1));
		clientes.get(x).setNombre(nombre);
		clientes.get(x).setApellido(apellido);
		clientes.get(x).setDni(dni);
		clientes.get(x).setUsuario(usuario);
		clientes.get(x).setPassword(password);
		clientes.get(x).setCorreo(correo);
		clientes.get(x).setId_gestor(id_gestor);
		clientes.get(x).setSaldo(saldo);
		System.out.println("Inserción realizada correctamente");
	}
	
	// 8. Obtención de un cliente
	public static void obtencionUnCliente(List<Cliente> clientes, int x) {
		System.out.println("Datos del cliente "+(x+1));
		System.out.println("Nombre: "+clientes.get(x).getNombre());
		System.out.println("Apellido: "+clientes.get(x).getApellido());
		System.out.println("DNI: "+clientes.get(x).getDni());
		System.out.println("Usuario: "+clientes.get(x).getUsuario());
		System.out.println("Contraseña: "+clientes.get(x).getPassword());
		System.out.println("Correo: "+clientes.get(x).getCorreo());
		System.out.println("Id del cliente: "+clientes.get(x).getId_cliente());
		System.out.println("Id del gestor: "+clientes.get(x).getId_gestor());
		System.out.println("Saldo: "+clientes.get(x).getSaldo()+"€\n");
	}
	
	// 9. Obtención de todos los clientes
	public static void obtencionVariosClientes(List<Cliente> clientes) {
		for (int x=0; x<clientes.size(); x++) {
			System.out.println("Datos del cliente "+(x+1));
			System.out.println("Nombre: "+clientes.get(x).getNombre());
			System.out.println("Apellido: "+clientes.get(x).getApellido());
			System.out.println("DNI: "+clientes.get(x).getDni());
			System.out.println("Usuario: "+clientes.get(x).getUsuario());
			System.out.println("Contraseña: "+clientes.get(x).getPassword());
			System.out.println("Correo: "+clientes.get(x).getCorreo());
			System.out.println("Id del cliente: "+clientes.get(x).getId_cliente());
			System.out.println("Id del gestor: "+clientes.get(x).getId_gestor());
			System.out.println("Saldo: "+clientes.get(x).getSaldo()+"€\n");
		}
	}
	
	// 10. Actualización de un cliente (dato a dato)
	public static void actualizarNombreCliente(List<Cliente> clientes, int x, String nombre) {clientes.get(x).setNombre(nombre);}
	public static void actualizarApellidoCliente(List<Cliente> clientes, int x, String apellido) {clientes.get(x).setApellido(apellido);}
	public static void actualizarDniCliente(List<Cliente> clientes, int x, String dni) {clientes.get(x).setDni(dni);}
	public static void actualizarUsuarioCliente(List<Cliente> clientes, int x, String usuario) {clientes.get(x).setUsuario(usuario);}
	public static void actualizarPasswordCliente(List<Cliente> clientes, int x, String password) {clientes.get(x).setPassword(password);}
	public static void actualizarCorreoCliente(List<Cliente> clientes, int x, String correo) {clientes.get(x).setCorreo(correo);}
	public static void actualizarIdGestorCliente(List<Cliente> clientes, int x, int id_gestor) {clientes.get(x).setId_gestor(id_gestor);}
	public static void actualizarSaldoCliente(List<Cliente> clientes, int x, double saldo) {clientes.get(x).setSaldo(saldo);}

	// 11. Eliminación de un cliente
	public static void eliminarCliente(List<Cliente> clientes, int x) {
		clientes.remove(x);
		for (int i=x; i<clientes.size(); i++) {
			clientes.get(i).setId_gestor(i-1);
		}
		System.out.println("Eliminación realizada correctamente");
	}
	
}
