package com.sandra.bancoDB;

import com.sandra.bancoDB.utilidades.DatabaseUtils;
import com.sandra.bancoDB.utilidades.MenuUtils;

public class App {
	
    public static void main(String[] args) {
    	if (DatabaseUtils.conexion() != null) {
    		MenuUtils.mainMenu();
        	DatabaseUtils.desconexion();
    	}
    }
}
