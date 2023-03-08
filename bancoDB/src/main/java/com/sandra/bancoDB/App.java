package com.sandra.bancoDB;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.sandra.bancoDB.connection.DBConnection;
import com.sandra.bancoDB.menus.ClienteMenu;
import com.sandra.bancoDB.menus.GestorMenu;
import com.sandra.bancoDB.menus.LoginMenu;
import com.sandra.bancoDB.menus.MensajeMenu;
import com.sandra.bancoDB.menus.RegistroMenu;
import com.sandra.bancoDB.menus.TransferenciaMenu;

public class App {
	
	public static void mainMenu() {
	    try{
	      String opcion = JOptionPane.showInputDialog(null, "Inserte una opción: ", "MENÚ PRINCIPAL", JOptionPane.PLAIN_MESSAGE, null,
					new Object[] {"Gestores","Clientes", "Mensajes", "Transferencias", "Login", "Registro"}, null).toString();
	        switch (opcion){
	        case "Gestores" -> GestorMenu.switchGestor();
	        case "Clientes" -> ClienteMenu.switchCliente();
	        case "Mensajes" -> MensajeMenu.switchMensaje();
	        case "Transferencias" -> TransferenciaMenu.switchTransferencia();
	        case "Login" -> LoginMenu.switchLogin();
	        case "Registro" -> RegistroMenu.switchRegistro();
	        }
	    } catch (NullPointerException e){
	    	JOptionPane.showMessageDialog(null, "Gracias por usar este programa", "HASTA PRONTO", 1, new ImageIcon("src/main/java/com/sandra/bancoDB/images/gracias.png"));
	    }
		
	}
	
    public static void main(String[] args) {
    	if (DBConnection.conexion() != null) {
    		mainMenu();
        	DBConnection.desconexion();
    	}
    }
}
