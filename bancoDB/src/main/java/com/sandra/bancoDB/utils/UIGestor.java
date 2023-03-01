package com.sandra.bancoDB.utils;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.sandra.bancoDB.databases.DBGestor;
import com.sandra.bancoDB.models.Gestor;
import com.sandra.bancoDB.utils.UIGestor;

public class UIGestor {
	// Inserción de un gestor
	public static Gestor addGestor() {
		String nombre = JOptionPane.showInputDialog("Introduzca el nombre del nuevo gestor: ");
		String apellido = JOptionPane.showInputDialog("Introduzca el apellido del nuevo gestor: ");
		String dni = JOptionPane.showInputDialog("Introduzca el DNI del nuevo gestor: ");
		String usuario = JOptionPane.showInputDialog("Introduzca el usuario del nuevo gestor: ");
		String password = nuevoPassword("Introduzca la contraseña del nuevo gestor", "Input", 3);
		String correo = JOptionPane.showInputDialog("Introduzca el correo del nuevo gestor: ");
		return new Gestor(nombre, apellido, dni, usuario, password, correo);
	}
	
	public static String nuevoPassword(String message, String title, int messageType) {
		String password;
		boolean passwordIncorrecto;
		do {
			password = JOptionPane.showInputDialog(null, message, title, messageType);
			passwordIncorrecto = passwordIncorrecto(password);
			if (passwordIncorrecto && password != null) System.err.println("La contraseña debe ser un número de 4 dígitos");
		} while (password!=null && passwordIncorrecto);
		return password;
	}
	
	private static boolean passwordIncorrecto(String password) {
		final int LENGTH_PASSWORD = 4;
		try {
			Integer.parseInt(password); // Salta el error NumberFormatException si la contraseña no se puede convertir a entero
			if (password.length()!=LENGTH_PASSWORD) return true;
			else return false;
		} catch (NumberFormatException e) {
			return true;
		}
	}

	// Inserción masiva de gestores con datos aleatorios
	public static Gestor addGestores() {
		return new Gestor();
	}

	// Obtención de un gestor y comprobación previa de que existe el gestor con ese ID
	public static Gestor getGestor(String texto) {
		try {
			return new Gestor(Integer.parseInt(JOptionPane.showInputDialog("Inserte el ID del gestor "+texto+": ")));
		} catch (NumberFormatException e) {
			return new Gestor(null);
		}
	}

	// Actualización de un gestor (dato a dato)
	
	public static Gestor updateGestor(Gestor updateGestor, String texto, int id) {
		return new Gestor(id, JOptionPane.showInputDialog(null, "Introduzca "+texto+":", "ACTUALIZACIÓN DEL GESTOR " + id, 1));
	}
	
	// Se hace un método aparte para la contraseña ya que se debe comprobar que la contraseña sea válida
	public static Gestor updatePasswordGestor(Gestor updateGestor, String password, int id) {
        return new Gestor(id, password);
    }
	
	// Eliminación de un gestor con confirmación de la acción
		public static void deleteGestor(DBGestor dbGestor, Gestor updateGestor) {
			int confirmacion;
			try {
				confirmacion = JOptionPane.showConfirmDialog(null,
						"¿Seguro que quieres eliminar al gestor " + updateGestor.getId_gestor() + "?",
						"MENSAJE DE CONFIRMACIÓN", 0, 3, new ImageIcon("src/images/eliminar.png"));
			} catch (Exception e) {
				confirmacion = 1;
			}
			switch (confirmacion) {
			case 0 -> {
				dbGestor.deleteGestor(updateGestor);
				JOptionPane.showMessageDialog(null, "Eliminación realizada correctamente");
			}
			case 1 -> JOptionPane.showMessageDialog(null, "No se ha eliminado el gestor " + updateGestor.getId_gestor());
			}
		}
}