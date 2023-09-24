package com.sandra.listBank;

import java.math.BigDecimal;
import java.util.Collections;

import com.sandra.listBank.entidades.Cliente;
import com.sandra.listBank.entidades.Cuenta;
import com.sandra.listBank.entidades.Gestor;
import com.sandra.listBank.entidades.Mensaje;
import com.sandra.listBank.entidades.Transferencia;
import com.sandra.listBank.utilidades.MenuUtils;

public class App {
	
	private static void datosIniciales() {
		// Añadir gestores
    	Collections.addAll(Gestor.gestores, new Gestor("Helena", "Salas", "12345678A", "1357", "correoG1@gmail.com", "Alicante"),
    			new Gestor("Jordi", "Fontanarrosa", "98765432Z", "2468", "correoG2@gmail.com", "Elche"));
    	// Añadir clientes (el cliente se añade automáticamente a la lista del gestor, pero no se elimina de la lista del anterior)
    	Collections.addAll(Cliente.clientes,
    			new Cliente("Alejandro", "Planelles", "24680135G", "1234", "correoC1@gmail.com", Gestor.gestores.get(0)),
    			new Cliente("María", "Verdú", "13579024V", "5678", "correoC2@gmail.com", Gestor.gestores.get(1)));
    	// Añadir cuentas (la cuenta se añade automáticamente a la lista del cliente)
    	Collections.addAll(Cuenta.cuentas, new Cuenta(Cliente.clientes.get(0)), new Cuenta(Cliente.clientes.get(0)),
    			new Cuenta(Cliente.clientes.get(1)));
    	Cuenta.cuentas.get(1).setSaldo(new BigDecimal(2500.00));
    	// Añadir mensajes (los mensajes se añaden automáticamente a la lista de mensajes del gestor y del cliente)
    	new Mensaje(Gestor.gestores.get(0), Cliente.clientes.get(0)).setLeido(true);
    	new Mensaje(Gestor.gestores.get(1), Cliente.clientes.get(1)).setLeido(true);
    	new Mensaje(Gestor.gestores.get(0), Cliente.clientes.get(0));
    	/* Añadir transferencias (las transferencias se añaden automáticamente a la lista de ambos clientes
    	 * (solo se añade una vez si el cliente es el mismo y se actualizan automáticamente los saldos de las cuentas) */
    	new Transferencia(Cuenta.cuentas.get(1), Cuenta.cuentas.get(0), new BigDecimal(500.00), "Prueba de transferencia");
    	new Transferencia(Cuenta.cuentas.get(1), Cuenta.cuentas.get(2), new BigDecimal(500.00), "Prueba de transferencia");
	}
	
    public static void main(String[] args) {
    	datosIniciales();
        MenuUtils.mainMenu();
    }
}
