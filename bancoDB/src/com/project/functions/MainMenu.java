package com.project.functions;

import javax.swing.*;

import com.project.controllers.ClienteController;
import com.project.controllers.GestorController;
import com.project.controllers.LoginController;
import com.project.controllers.MensajeController;
import com.project.controllers.RegistroController;
import com.project.controllers.TransferenciaController;


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

    } catch (Exception e){
      JOptionPane.showMessageDialog(null, "Gracias por usar este programa", "HASTA PRONTO", 1, new ImageIcon("src/images/gracias.png"));
    }

  }
  
}