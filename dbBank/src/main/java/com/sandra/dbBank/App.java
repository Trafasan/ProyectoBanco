package com.sandra.dbBank;

import com.sandra.dbBank.utilidades.DatabaseUtils;
import com.sandra.dbBank.utilidades.MenuUtils;

public class App {
	
    public static void main(String[] args)  {
    	if (DatabaseUtils.conexion()) {
    		MenuUtils.mainMenu();
        	DatabaseUtils.desconexion();
    	}
    }
}
