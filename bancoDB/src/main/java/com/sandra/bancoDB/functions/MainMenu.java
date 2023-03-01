package com.sandra.bancoDB.functions;

import javax.swing.*;

import com.sandra.bancoDB.controllers.ClienteController;
import com.sandra.bancoDB.controllers.GestorController;
import com.sandra.bancoDB.controllers.LoginController;
import com.sandra.bancoDB.controllers.MensajeController;
import com.sandra.bancoDB.controllers.RegistroController;
import com.sandra.bancoDB.controllers.TransferenciaController;


public class MainMenu {

  public static void opciones() {

    try{
      String opcion = JOptionPane.showInputDialog(null, "Inserte una opción: ", "MENÚ PRINCIPAL", JOptionPane.PLAIN_MESSAGE, null,
				new Object[] {"Gestores","Clientes", "Mensajes", "Transferencias", "Login", "Registro"}, null).toString();

        switch (opcion){
        case "Gestores" -> GestorController.switchGestor();
        case "Clientes" -> ClienteController.switchCliente();
        case "Mensajes" -> MensajeController.switchMensaje();
        case "Transferencias" -> TransferenciaController.switchTransferencia();
        case "Login" -> LoginController.switchLogin();
        case "Registro" -> RegistroController.switchRegistro();
        }

    } catch (NullPointerException e){
    	JOptionPane.showMessageDialog(null, "Gracias por usar este programa", "HASTA PRONTO", 1, new ImageIcon("src/images/gracias.png"));
    }

  }
  
}