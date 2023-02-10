package com.project.utils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import com.project.models.Gestor;
import com.project.models.Mensaje;

public class UIMensaje {
	// Obtención de un mensaje
	public static void getMensaje(List<Mensaje> mensajes, Scanner sc) {
		System.out.print("Introduzca la ID del mensaje a obtener: ");
		try {
			System.out.println(mensajes.get(Integer.parseInt(sc.nextLine()) - 1));
		} catch (IndexOutOfBoundsException e) {
			System.err
					.println("No se ha encontrado el mensaje con la ID introducida. Se le redigirá al Menú Mensajes.");
		}
	}

	// Obtención de todos los mensajes
	public static void getMensajes(List<Mensaje> mensajes) {
		for (int i = 0; i < mensajes.size(); i++)
			System.out.println(mensajes.get(i));
	}

	// Envío de un mensaje

	// Comprobación previa de que existe el gestor con ese ID (origen y destino)
	public static int comprobarId(List<Gestor> gestores, Scanner sc, String tipo) {
		System.out.print("Proporciona la ID del gestor " + tipo + ": ");
		return Integer.parseInt(sc.nextLine()) - 1;
	}

	public static void addMensaje(List<Mensaje> mensajes, int id_origen, int id_destino, Scanner sc) {
		int id = mensajes.size();
		mensajes.add(new Mensaje(id + 1));
		mensajes.get(id).setId_gestor(++id_origen);
		mensajes.get(id).setId_cliente(++id_destino);
		System.out.print("Introduzca el texto del mensaje: ");
		mensajes.get(id).setTexto(sc.nextLine());
		mensajes.get(id).setFecha(LocalDateTime.now());
		System.out.println("Envío realizado correctamente");
	}

}