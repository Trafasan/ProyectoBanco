package ProyectoBanco;

import java.util.List;

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

	// 1. Inserción de un gestor
	public static void insertarUnGestor(List<Gestor> gestores, String nombre, String apellido, String dni, String usuario, String password, String correo) {
		int x = gestores.size();
		gestores.add(new Gestor(x+1));
		gestores.get(x).setNombre(nombre);
		gestores.get(x).setApellido(apellido);
		gestores.get(x).setDni(dni);
		gestores.get(x).setUsuario(usuario);
		gestores.get(x).setPassword(password);
		gestores.get(x).setCorreo(correo);
		System.out.println("Inserción realizada correctamente");
	}

	// 2. Inserción masiva de gestores con datos aleatorios
	public static void insertarVariosGestores(List<Gestor> gestores, int nGestoresNuevos) {
		int nGestores = gestores.size() + nGestoresNuevos;
		for (int x = (gestores.size()+1); x <= nGestores; x++) {
			gestores.add(new Gestor(x));
		}
		System.out.println("Inserciones realizadas correctamente");
	}
	
	// 3. Obtención de un gestor
	public static void obtencionUnGestor(List<Gestor> gestores, int x) {
		System.out.println("Datos del gestor "+(x+1));
		System.out.println("Nombre: "+gestores.get(x).getNombre());
		System.out.println("Apellido: "+gestores.get(x).getApellido());
		System.out.println("DNI: "+gestores.get(x).getDni());
		System.out.println("Usuario: "+gestores.get(x).getUsuario());
		System.out.println("Contraseña: "+gestores.get(x).getPassword());
		System.out.println("Correo: "+gestores.get(x).getCorreo());
		System.out.println("Id del gestor: "+gestores.get(x).getId_gestor()+"\n");
	}
	
	// 4. Obtención de todos los gestores
	public static void obtencionVariosGestores(List<Gestor> gestores) {
		for (int x=0; x<gestores.size(); x++) {
			System.out.println("Datos del gestor "+(x+1));
			System.out.println("Nombre: "+gestores.get(x).getNombre());
			System.out.println("Apellido: "+gestores.get(x).getApellido());
			System.out.println("DNI: "+gestores.get(x).getDni());
			System.out.println("Usuario: "+gestores.get(x).getUsuario());
			System.out.println("Contraseña: "+gestores.get(x).getPassword());
			System.out.println("Correo: "+gestores.get(x).getCorreo());
			System.out.println("Id del gestor: "+gestores.get(x).getId_gestor()+"\n");
		}
	}
	
	// 5. Actualización de un gestor (dato a dato)
	public static void actualizarNombreGestor(List<Gestor> gestores, int x, String nombre) {gestores.get(x).setNombre(nombre);}
	public static void actualizarApellidoGestor(List<Gestor> gestores, int x, String apellido) {gestores.get(x).setApellido(apellido);}
	public static void actualizarDniGestor(List<Gestor> gestores, int x, String dni) {gestores.get(x).setDni(dni);}
	public static void actualizarUsuarioGestor(List<Gestor> gestores, int x, String usuario) {gestores.get(x).setUsuario(usuario);}
	public static void actualizarPasswordGestor(List<Gestor> gestores, int x, String password) {gestores.get(x).setPassword(password);}
	public static void actualizarCorreoGestor(List<Gestor> gestores, int x, String correo) {gestores.get(x).setCorreo(correo);}

	// 6. Eliminación de un gestor
	public static void eliminarGestor(List<Gestor> gestores, int x) {
		gestores.remove(x);
		for (int i=x; i<gestores.size(); i++) {
			gestores.get(i).setId_gestor(i-1);
		}
		System.out.println("Eliminación realizada correctamente");
	}
}
