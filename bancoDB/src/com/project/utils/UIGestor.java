package com.project.utils;

import javax.swing.JOptionPane;

import com.project.models.Gestor;
import com.project.utils.UIGestor;

public class UIGestor {
	// 1. Inserción de un gestor
	public static Gestor insertarGestor() {

		String nombre = JOptionPane.showInputDialog("Inserte el nombre del nuevo gestor: ");
		String apellido = JOptionPane.showInputDialog("Inserte el apellido del nuevo gestor: ");
		String dni = JOptionPane.showInputDialog("Inserte el DNI del nuevo gestor: ");
		String usuario = JOptionPane.showInputDialog("Inserte el usuario del nuevo gestor: ");
		String password = JOptionPane.showInputDialog("Inserte la contraseña del nuevo gestor: ");
		String correo = JOptionPane.showInputDialog("Inserte el correo del nuevo gestor: ");

		Gestor gestor = new Gestor(nombre, apellido, dni, usuario, password, correo);

		return gestor;

	}

	// 2. Inserción masiva de gestores con datos aleatorios
	public static Gestor insGestorAleatorio() {
		Gestor gestor = new Gestor();
		return gestor;
	}

	// 3. Obtención de un gestor
	public static Gestor obtencionGestor() {
		int n = Integer.parseInt(JOptionPane.showInputDialog("Inserte el ID del gestor a obtener: "));
		Gestor gestor = new Gestor(n);
		return gestor;
	}

	// 5. Actualización de un gestor (dato a dato)
	
	//Comprobación previa de que existe el gestor con ese ID
	public static Gestor comprobarGestor() {
		int n = Integer.parseInt(JOptionPane.showInputDialog("Proporciona la ID del gestor que quieres modificar:"));
		Gestor gestor = new Gestor(n);
		return gestor;
	}
	
	public static Gestor updateNombreGestor(Gestor comprobacionGestor) {
        int id = comprobacionGestor.getId_gestor();
        String nombre = JOptionPane.showInputDialog(null, "Introduzca el nuevo nombre:", "ACTUALIZACIÓN DEL GESTOR " + id, 1);
        Gestor gestor = new Gestor(id, nombre);
        return gestor;
    }
	
	public static Gestor updateApellidoGestor(Gestor comprobacionGestor) {
        int id = comprobacionGestor.getId_gestor();
        String apellido = JOptionPane.showInputDialog(null, "Introduzca el(los) nuevo(s) apellido(s):", "ACTUALIZACIÓN DEL GESTOR " + id, 1);
        Gestor gestor = new Gestor(id, apellido);
        return gestor;
    }
	
	public static Gestor updateDniGestor(Gestor comprobacionGestor) {
        int id = comprobacionGestor.getId_gestor();
        String dni = JOptionPane.showInputDialog(null, "Introduzca el nuevo DNI", "ACTUALIZACIÓN DEL GESTOR " + id, 1);
        Gestor gestor = new Gestor(id, dni);
        return gestor;
    }
	
	public static Gestor updateUsuarioGestor(Gestor comprobacionGestor) {
        int id = comprobacionGestor.getId_gestor();
        String usuario = JOptionPane.showInputDialog(null, "Introduzca el nuevo usuario", "ACTUALIZACIÓN DEL GESTOR " + id, 1);
        Gestor gestor = new Gestor(id, usuario);
        return gestor;
    }
	
	public static Gestor updatePasswordGestor(Gestor comprobacionGestor) {
        int id = comprobacionGestor.getId_gestor();
        String password = JOptionPane.showInputDialog(null, "Introduzca la nueva contraseña", "ACTUALIZACIÓN DEL GESTOR " + id, 1);
        Gestor gestor = new Gestor(id, password);
        return gestor;
    }
	
	public static Gestor updateCorreoGestor(Gestor comprobacionGestor) {
        int id = comprobacionGestor.getId_gestor();
        String correo = JOptionPane.showInputDialog(null, "Introduzca el nuevo correo", "ACTUALIZACIÓN DEL GESTOR " + id, 1);
        Gestor gestor = new Gestor(id, correo);
        return gestor;
    }
}