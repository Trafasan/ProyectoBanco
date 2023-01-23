package com.project.functions;

import javax.swing.*;

import com.project.controllers.ClienteController;
import com.project.controllers.GestorController;
import com.project.controllers.MensajeController;
import com.project.controllers.TransferenciaController;


public class MainMenu {

  public static void opciones() {

    try{
      String opcion = JOptionPane.showInputDialog(null, "Inserte una opción: ", "MENÚ PRINCIPAL", JOptionPane.PLAIN_MESSAGE, null,
				new Object[] {"Gestores","Clientes", "Mensajes", "Transferencias", "Login", "Registro"}, null).toString();

        switch (opcion){
        case "Gestores":
        	GestorController.switchGestor();
        	break;
        case "Clientes":
        	ClienteController.switchCliente();
        	break;
        case "Mensajes":
        	MensajeController.switchMensaje();
        	break;
        case "Transferencias":
        	TransferenciaController.switchTransferencia();
        	break;
        case "Login": break;
        case "Registro": break;
        }

    } catch (Exception e){
      ImageIcon icon = new ImageIcon("src/images/gracias.png");
      JOptionPane.showMessageDialog(null, "Gracias por usar este programa", "HASTA PRONTO", 1, icon);
    }

  }
  
}