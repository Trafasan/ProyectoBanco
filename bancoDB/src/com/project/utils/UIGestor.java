package com.project.utils;

import javax.swing.JOptionPane;

import com.project.models.Gestor;
import com.project.utils.UIGestor;

public class UIGestor {
	// Inserción de un gestor
	public static Gestor addGestor() {
		String nombre = JOptionPane.showInputDialog("Introduzca el nombre del nuevo gestor: ");
		String apellido = JOptionPane.showInputDialog("Introduzca el apellido del nuevo gestor: ");
		String dni = JOptionPane.showInputDialog("Introduzca el DNI del nuevo gestor: ");
		String usuario = JOptionPane.showInputDialog("Introduzca el usuario del nuevo gestor: ");
		String password = JOptionPane.showInputDialog("Introduzca la contraseña del nuevo gestor: ");
		String correo = JOptionPane.showInputDialog("Introduzca el correo del nuevo gestor: ");
		return new Gestor(nombre, apellido, dni, usuario, password, correo);

	}

	// Inserción masiva de gestores con datos aleatorios
	public static Gestor addGestores() {
		return new Gestor();
	}

	// Obtención de un gestor
	public static Gestor getGestor() {
		return new Gestor(Integer.parseInt(JOptionPane.showInputDialog("Inserte el ID del gestor a obtener: ")));
	}

	// Actualización de un gestor (dato a dato)
	
	//Comprobación previa de que existe el gestor con ese ID
	public static Gestor comprobarGestor() {
		return new Gestor(Integer.parseInt(JOptionPane.showInputDialog("Proporciona la ID del gestor que quieres modificar:")));
	}
	
	public static Gestor updateGestor(Gestor updateGestor, String dato) { // Sirve para el nombre, DNI, usuario y correo
		int id = updateGestor.getId_gestor();
		return new Gestor(id, JOptionPane.showInputDialog(null, "Introduzca el nuevo "+dato+":", "ACTUALIZACIÓN DEL GESTOR " + id, 1));
	}
	
	public static Gestor updateApellidoGestor(Gestor updateGestor) {
        int id = updateGestor.getId_gestor();
        return new Gestor(id, JOptionPane.showInputDialog(null, "Introduzca el(los) nuevo(s) apellido(s):", "ACTUALIZACIÓN DEL GESTOR " + id, 1));
    }
	
	public static Gestor updatePasswordGestor(Gestor updateGestor) {
        int id = updateGestor.getId_gestor();
        return new Gestor(id, JOptionPane.showInputDialog(null, "Introduzca la nueva contraseña", "ACTUALIZACIÓN DEL GESTOR " + id, 1));
    }
}