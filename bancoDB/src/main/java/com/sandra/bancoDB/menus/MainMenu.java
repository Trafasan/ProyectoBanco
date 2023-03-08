package com.sandra.bancoDB.menus;

import javax.swing.*;


public class MainMenu {

  public static void opciones() {

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
  
}