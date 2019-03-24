package main;

import deployment.Mysql;
import modelo.Agenda;
import persistencia.dao.mysql.DAOSQLFactory;
import presentacion.controlador.Controlador;
import presentacion.vista.VentanaPrincipal;


public class Main 
{

	public static void main(String[] args) 
	{
		//Mysql.start();
		//Mysql.loadInitialData();
		VentanaPrincipal vista = new VentanaPrincipal();
		Agenda modelo = new Agenda();
		Controlador controlador = new Controlador(vista, modelo);
		
	
	}
}
