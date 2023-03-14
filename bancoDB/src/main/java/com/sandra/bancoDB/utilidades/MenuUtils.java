package com.sandra.bancoDB.utilidades;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.sandra.bancoDB.entidades.Cliente;
import com.sandra.bancoDB.entidades.Gestor;

public class MenuUtils {
	private static ImageIcon preocupado = new ImageIcon("src/main/java/com/sandra/bancoDB/images/preocupado.png");
	private static String opcion;
	
	public static void mainMenu() {
	    try{
	    	opcion = JOptionPane.showInputDialog(null, "Inserte una opción: ", "MENÚ PRINCIPAL", JOptionPane.PLAIN_MESSAGE, null,
					new Object[] {"Gestores","Clientes", "Mensajes", "Transferencias", "Login", "Registro"}, null).toString();
	        switch (opcion){
	        case "Gestores" -> menuGestor();
	        case "Clientes" -> menuCliente();
	        case "Mensajes" -> menuMensaje();
	        case "Transferencias" -> menuTransferencia();
	        case "Login" -> menuLogin();
	        case "Registro" -> menuRegistro();
	        }
	    } catch (NullPointerException e){
	    	JOptionPane.showMessageDialog(null, "Gracias por usar este programa", "HASTA PRONTO", 1, new ImageIcon("src/main/java/com/sandra/bancoDB/images/gracias.png"));
	    }
	}
	
	private static void menuGestor() {
		try {
			opcion = JOptionPane
					.showInputDialog(null, "Seleccione una opción: ", "MENÚ GESTOR", JOptionPane.PLAIN_MESSAGE, null,
							new Object[] { "Inserción de un gestor", "Inserción de varios gestores",
									"Obtención de un gestor", "Obtención de todos los gestores",
									"Actualización de un gestor", "Eliminación de un gestor" }, null).toString();
		} catch (NullPointerException e) {
			opcion = "Volver atrás";
		}
		
		switch (opcion) {
		case "Inserción de un gestor" -> JOptionPane.showMessageDialog(null,
				(DatabaseUtils.addDato(GeneralUtils.addGestor())) ? "Inserción realizada correctamente":"No se ha podido insertar el gestor");
		case "Inserción de varios gestores" -> {
			int n = Integer.parseInt(JOptionPane.showInputDialog("Inserte el número de gestores a añadir:"));
			for (int i=0; i<n; i++) DatabaseUtils.addDato(new Gestor());
			JOptionPane.showMessageDialog(null, "Inserciones realizada correctamente");
		}
		case "Obtención de un gestor" -> GeneralUtils.imprimirDato(DatabaseUtils.datosGestores(), "l gestor");
		case "Obtención de todos los gestores" -> DatabaseUtils.getDatos(DatabaseUtils.datosGestores(), "Gestor");
		case "Actualización de un gestor" -> {
			int id_gestor = GeneralUtils.getId("Introduzca el ID del gestor que quieres modificar: ", "Input", 3);
			if (DatabaseUtils.getId(DatabaseUtils.datosGestores()).contains(id_gestor)) menuUpdateGestor(id_gestor);
		}
		case "Eliminación de un gestor" -> {
			int id_gestor = GeneralUtils.getId("Introduzca el ID del gestor que quieres eliminar: ", "Input", 3);
			if (DatabaseUtils.getId(DatabaseUtils.datosGestores()).contains(id_gestor)) GeneralUtils.delete("gestor", id_gestor);
		}
		case "Volver atrás" -> mainMenu();
		}
		if (!opcion.equals("Volver atrás")) menuGestor();
	}

	private static void menuUpdateGestor(int id) {
		try {
			opcion = JOptionPane.showInputDialog(null, "Seleccione el dato que quiere actualizar: ", "ACTUALIZACIÓN DEL GESTOR "+id,
					JOptionPane.PLAIN_MESSAGE, null,
					new Object[] {"Nombre", "Apellido(s)", "DNI", "Usuario", "Contraseña", "Correo"}, null).toString();
		} catch (NullPointerException e) {
			opcion = "Volver atrás";
		}

		switch (opcion) {
		case "Nombre" -> JOptionPane.showMessageDialog(null,
				(DatabaseUtils.updateDato(GeneralUtils.updateGestor("el nuevo nombre", id), "nombre")) ?
						"Nombre actualizado correctamente":"No se ha podido actualizar el nombre");
		case "Apellido(s)" -> JOptionPane.showMessageDialog(null,
				(DatabaseUtils.updateDato(GeneralUtils.updateGestor("el(los) nuevo(s) apellido(s)", id), "apellido")) ?
						"Apellido(s) actualizado(s) correctamente":"No se ha podido actualizar el(los) apellido(s)");
		case "DNI" -> JOptionPane.showMessageDialog(null,
				(DatabaseUtils.updateDato(GeneralUtils.updateGestor("el nuevo DNI", id), "dni")) ?
						"DNI actualizado correctamente":"No se ha podido actualizar el DNI");
		case "Usuario" -> JOptionPane.showMessageDialog(null,
				(DatabaseUtils.updateDato(GeneralUtils.updateGestor("el nuevo usuario", id), "usuario")) ?
						"Usuario actualizado correctamente":"No se ha podido actualizar el usuario");
		case "Contraseña" -> {
			String password = GeneralUtils.nuevoPassword("Introduzca la nueva contraseña", "ACTUALIZACIÓN DEL GESTOR " + id, 1);
			JOptionPane.showMessageDialog(null,
					(DatabaseUtils.updateDato(GeneralUtils.updatePasswordGestor(password, id), "password")) ?
							"Contraseña actualizada correctamente":"No se ha podido actualizar la contraseña");
		}
		case "Correo" -> JOptionPane.showMessageDialog(null,
				(DatabaseUtils.updateDato(GeneralUtils.updateGestor("el nuevo correo", id), "correo")) ?
						"Correo actualizado correctamente":"No se ha podido actualizar el correo");
		}
		if (!opcion.equals("Volver atrás")) menuUpdateGestor(id);
	}

	private static void menuCliente() {
		try {
			opcion = JOptionPane
					.showInputDialog(null, "Seleccione una opción: ", "MENÚ CLIENTE", JOptionPane.PLAIN_MESSAGE, null,
							new Object[] { "Inserción de un cliente", "Obtención de un cliente",
									"Obtención de todos los clientes", "Actualización de un cliente",
									"Eliminación de un cliente" }, null).toString();
		} catch (NullPointerException e) {
			opcion = "Volver atrás";
		}

		switch (opcion) {
		case "Inserción de un cliente" -> JOptionPane.showMessageDialog(null,(DatabaseUtils.addDato(
				GeneralUtils.addCliente())) ? "Inserción realizada correctamente":"No se ha podido insertar el cliente");
		case "Obtención de un cliente" -> GeneralUtils.imprimirDato(DatabaseUtils.datosClientes(), "l cliente");
		case "Obtención de todos los clientes" -> DatabaseUtils.getDatos(DatabaseUtils.datosClientes(), "Cliente");
		case "Actualización de un cliente" -> {
			int id_cliente = GeneralUtils.getId("Introduzca el ID del cliente que quieres modificar: ", "Input", 3);
			if (DatabaseUtils.getId(DatabaseUtils.datosClientes()).contains(id_cliente)) menuUpdateCliente(id_cliente);
		}
		case "Eliminación de un cliente" -> {
			int id_cliente = GeneralUtils.getId("Introduzca el ID del cliente que quieres eliminar: ", "Input", 3);
			if (DatabaseUtils.getId(DatabaseUtils.datosClientes()).contains(id_cliente)) GeneralUtils.delete("cliente", id_cliente);
		}
		case "Volver atrás" -> mainMenu();
		}
		if (!opcion.equals("Volver atrás")) menuCliente();
	}

	private static void menuUpdateCliente(int id) {
		try {
			opcion = JOptionPane.showInputDialog(null, "Seleccione el dato que quiere actualizar: ", "ACTUALIZACIÓN DEL CLIENTE "+id,
					JOptionPane.PLAIN_MESSAGE, null,
					new Object[] {"ID del gestor", "Nombre", "Apellido(s)", "DNI", "Usuario", "Contraseña", "Correo", "Saldo"}, null).toString();
		} catch (NullPointerException e) {
			opcion = "Volver atrás";
		}

		switch (opcion) {
		case "ID del gestor" -> {
			int id_gestor = GeneralUtils.getId("Introduzca el ID del nuevo gestor:", "ACTUALIZACIÓN DEL CLIENTE " + id, 1);
			if (DatabaseUtils.getId(DatabaseUtils.datosGestores()).contains(id_gestor))
				JOptionPane.showMessageDialog(null,
						(DatabaseUtils.updateDato(GeneralUtils.updateId_gestorCliente(id, id_gestor), "id_gestor")) ?
								"ID del gestor actualizado correctamente":"No se ha podido actualizar el ID del gestor");
			else JOptionPane.showMessageDialog(null, "No existe ningún gestor con ese ID\nSe le redigirá al menú Cliente", "ERROR", 0, preocupado);
		}
		case "Nombre" -> JOptionPane.showMessageDialog(null,
				(DatabaseUtils.updateDato(GeneralUtils.updateCliente("el nuevo nombre", id), "nombre")) ?
						"Nombre actualizado correctamente":"No se ha podido actualizar el nombre");
		case "Apellido(s)" -> JOptionPane.showMessageDialog(null,
				(DatabaseUtils.updateDato(GeneralUtils.updateCliente("el(los) nuevo(s) apellido(s)", id), "apellido")) ?
						"Apellido(s) actualizado(s) correctamente":"No se ha podido actualizar el(los) apellido(s)");
		case "DNI" -> JOptionPane.showMessageDialog(null,
				(DatabaseUtils.updateDato(GeneralUtils.updateCliente("el nuevo DNI", id), "dni")) ?
						"DNI actualizado correctamente":"No se ha podido actualizar el DNI");
		case "Usuario" -> JOptionPane.showMessageDialog(null,
				(DatabaseUtils.updateDato(GeneralUtils.updateCliente("el nuevo usuario", id), "usuario")) ?
						"Usuario actualizado correctamente":"No se ha podido actualizar el usuario");
		case "Contraseña" -> {
			String password = GeneralUtils.nuevoPassword("Introduzca la nueva contraseña", "ACTUALIZACIÓN DEL CLIENTE " + id, 1);
			JOptionPane.showMessageDialog(null,
					(DatabaseUtils.updateDato(GeneralUtils.updatePasswordCliente(id, password), "password")) ?
							"Contraseña actualizada correctamente":"No se ha podido actualizar la contraseña");
		}
		case "Correo" -> JOptionPane.showMessageDialog(null,
				(DatabaseUtils.updateDato(GeneralUtils.updateCliente("el nuevo correo", id), "correo")) ?
						"Correo actualizado correctamente":"No se ha podido actualizar el correo");
		case "Saldo" -> JOptionPane.showMessageDialog(null,
				(DatabaseUtils.updateDato(GeneralUtils.updateSaldoCliente(id), "saldo")) ?
						"Saldo actualizado correctamente":"No se ha podido actualizar el saldo");
		}
		if (!opcion.equals("Volver atrás")) menuUpdateCliente(id);
	}

	private static void menuMensaje() {
		try {
			opcion = JOptionPane.showInputDialog(null, "Seleccione una opción: ", "MENÚ MENSAJE",
					JOptionPane.PLAIN_MESSAGE, null, new Object[] { "Obtención de un mensaje",
							"Obtención de todos los mensajes", "Envío de un mensaje" }, null).toString();
		} catch (Exception e) {
			opcion = "Volver atrás";
		}

		switch (opcion) {
		case "Obtención de un mensaje" -> GeneralUtils.imprimirDato(DatabaseUtils.datosMensajes(), "l mensaje");
		case "Obtención de todos los mensajes" -> DatabaseUtils.getDatos(DatabaseUtils.datosMensajes(), "Mensaje");
		case "Envío de un mensaje" -> {
			int id_gestor, id_cliente;
			do id_gestor = GeneralUtils.getId("Introduzca el ID del gestor:", "ENVÍO DEL MENSAJE", 1);
			while(!DatabaseUtils.getId(DatabaseUtils.datosGestores()).contains(id_gestor));
			do id_cliente = GeneralUtils.getId("Introduzca el ID del cliente:", "ENVÍO DEL MENSAJE", 1);
			while(!DatabaseUtils.getId(DatabaseUtils.datosClientes()).contains(id_cliente));
			JOptionPane.showMessageDialog(null, (DatabaseUtils.addDato(GeneralUtils.addMensaje(id_gestor, id_cliente))) ?
							"Envío realizado correctamente":"No se ha podido realizar el envío");
		}
		case "Volver atrás" -> mainMenu();
		}
		if (!opcion.equals("Volver atrás")) menuMensaje();
	}
	
	private static void menuTransferencia() {
		try {
			opcion = JOptionPane.showInputDialog(null, "Seleccione una opción: ", "MENÚ TRANSFERENCIA",
					JOptionPane.PLAIN_MESSAGE, null, new Object[] { "Obtención de una transferencia",
							"Obtención de todas las transferencias", "Envío de una transferencia" }, null).toString();
		} catch (Exception e) {
			opcion = "Volver atrás";
		}

		switch (opcion) {
		case "Obtención de una transferencia" -> GeneralUtils.imprimirDato(DatabaseUtils.datosMensajes(), " la transferencia");
		case "Obtención de todas las transferencias" -> DatabaseUtils.getDatos(DatabaseUtils.datosTransferencias(), "Transferencia");
		case "Envío de una transferencia" -> {
			int id_ordenante, id_beneficiario;
			do {
				do id_ordenante = GeneralUtils.getId("Introduzca el ID del cliente ordenante:", "ENVÍO DE LA TRANSFERENCIA", 1);
				 while(!DatabaseUtils.getId(DatabaseUtils.datosClientes()).contains(id_ordenante));
				do id_beneficiario = GeneralUtils.getId("Introduzca el ID del cliente beneficiario:", "ENVÍO DE LA TRANSFERENCIA", 1);
				 while(!DatabaseUtils.getId(DatabaseUtils.datosClientes()).contains(id_beneficiario));
				if(id_ordenante == id_beneficiario)
					JOptionPane.showMessageDialog(null, "El cliente beneficiario no puede ser el cliente ordenante", "ERROR", 0, preocupado);
			} while(id_ordenante == id_beneficiario);
			double saldoOrdenante = DatabaseUtils.getSaldo(id_ordenante);
			double saldoBeneficiario = DatabaseUtils.getSaldo(id_beneficiario);
			double importe = Double.parseDouble(JOptionPane.showInputDialog(null, "Introduzca el importe de la nueva transferencia:",
					"ENVÍO DE LA TRANSFERENCIA", 1));
			if(Double.compare(importe, saldoOrdenante) > 0)
				JOptionPane.showMessageDialog(null, "El importe de la transferencia no puede ser mayor al saldo del cliente ordenante", "ERROR", 0, preocupado);
			else {
				saldoOrdenante-=importe;
				saldoBeneficiario+=importe;
				DatabaseUtils.addDato(GeneralUtils.addTransferencia(id_ordenante, id_beneficiario, importe));
				DatabaseUtils.updateDato(new Cliente(id_ordenante, saldoOrdenante), "saldo");
				DatabaseUtils.updateDato(new Cliente(id_beneficiario, saldoBeneficiario), "saldo");
			    JOptionPane.showMessageDialog(null, "Nuevo saldo del cliente ordenante (ID: "+id_ordenante+"): "+String.format("%.2f€", saldoOrdenante)
						+ "\nNuevo saldo del cliente beneficiario (ID: "+id_beneficiario+"): "+String.format("%.2f€", saldoBeneficiario),
						"TRANSFERENCIA REALIZADA CORRECTAMENTE", 1, new ImageIcon("src/main/java/com/sandra/bancoDB/images/dinero.png"));
			}
		}
		case "Volver atrás" -> mainMenu();
		}
		if (!opcion.equals("Volver atrás")) menuTransferencia();
	}

	private static void menuLogin() {
		try {
			opcion = JOptionPane.showInputDialog(null, "Seleccione su tipo de cuenta: ", "MENÚ LOGIN",
					JOptionPane.PLAIN_MESSAGE, null, new Object[] { "Gestor", "Cliente" }, null).toString();
		} catch (Exception e) {
			opcion = "Volver atrás";
		}

		switch (opcion) {
		case "Gestor" -> {
			Gestor gestor = DatabaseUtils.getPersona(DatabaseUtils.datosGestores(), JOptionPane.showInputDialog("Introduzca su usuario:"));
			if (gestor != null) {
				String password = GeneralUtils.nuevoPassword("Introduzca su contraseña:", "Input", 3);
				if(gestor.getPassword().equals(password)) {
					JOptionPane.showMessageDialog(null, "Bienvenid@, "+gestor.getNombre()+" "+gestor.getApellido());
					mainMenu();
				} else {
					JOptionPane.showMessageDialog(null, "Contraseña incorrecta", "ERROR", 0, preocupado);
					menuLogin();
				}
			}
			else menuLogin();
		}
		case "Cliente" -> {
			Cliente cliente = DatabaseUtils.getPersona(DatabaseUtils.datosClientes(), JOptionPane.showInputDialog("Introduzca su usuario:"));
			if (cliente != null) {
				String password = GeneralUtils.nuevoPassword("Introduzca su contraseña:", "Input", 3);
				if(cliente.getPassword().equals(password)) {
					JOptionPane.showMessageDialog(null, "Bienvenid@, "+cliente.getNombre()+" "+cliente.getApellido());
					mainMenu();
				} else {
					JOptionPane.showMessageDialog(null, "Contraseña incorrecta", "ERROR", 0, preocupado);
					menuLogin();
				}
			}
			else menuLogin();
		}
		case "Volver atrás" ->  mainMenu();
		}
	}
	
	private static void menuRegistro() {
		try {
			opcion = JOptionPane.showInputDialog(null, "Seleccione el tipo de cuenta que quiere crear: ", "MENÚ REGISTRO",
					JOptionPane.PLAIN_MESSAGE, null, new Object[] { "Gestor", "Cliente" }, null).toString();
		} catch (Exception e) {
			opcion = "Volver atrás";
		}
		
		switch (opcion) {
		case "Gestor" -> {
			String usuario = JOptionPane.showInputDialog("Introduzca su usuario:");
			if (!DatabaseUtils.getUsuarios(DatabaseUtils.datosGestores()).contains(usuario)) {
				String password, comprobarPassword;
				do {
					password = GeneralUtils.nuevoPassword("Introduzca la nueva contraseña:", "Input", 3);
					comprobarPassword = GeneralUtils.nuevoPassword("Inserte de nuevo la contraseña:", "Input", 3);
					if(comprobarPassword.equals(password)) {
						JOptionPane.showMessageDialog(null, "Ahora, inserte los datos restantes", "USUARIO CREADO CORRECTAMENTE", 1, null);
						DatabaseUtils.addDato(GeneralUtils.addGestor(usuario, password));
						mainMenu();
					}
					else JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden", "ERROR", 0, preocupado);
				}while (!comprobarPassword.equals(password));
			} else menuRegistro();
		}
		case "Cliente" -> {
			String usuario = JOptionPane.showInputDialog("Introduzca su usuario:");
			if (!DatabaseUtils.getUsuarios(DatabaseUtils.datosClientes()).contains(usuario)) {
				String password, comprobarPassword;
				do {
					password = GeneralUtils.nuevoPassword("Introduzca la nueva contraseña:", "Input", 3);
					comprobarPassword = GeneralUtils.nuevoPassword("Inserte de nuevo la contraseña:", "Input", 3);
					if(comprobarPassword.equals(password)) {
						int id_gestor = GeneralUtils.getRandomId_Gestor();
						JOptionPane.showMessageDialog(null, "Ahora, inserte los datos restantes", "USUARIO CREADO CORRECTAMENTE", 1, null);
						DatabaseUtils.addDato(GeneralUtils.addCliente(id_gestor, usuario, password));
						Gestor gestorAsignado = DatabaseUtils.getDato(DatabaseUtils.datosGestores(), id_gestor);
						JOptionPane.showMessageDialog(null, "Se le ha asignado el siguiente gestor aleatoriamente:"
								+"\n· Nombre: "+gestorAsignado.getNombre()+" "+gestorAsignado.getApellido()
								+"\n· Correo electrónico: "+gestorAsignado.getCorreo());
						mainMenu();
					}
					else JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden", "ERROR", 0, preocupado);
				} while (!comprobarPassword.equals(password));
			} else menuRegistro();
		}
		case "Volver atrás" ->  mainMenu();
		}
	}
}
