package main;


import modelo.Agenda;
import persistencia.conexion.Conexion;
import persistencia.dao.mysql.DAOSQLFactory;
import presentacion.controlador.Controlador;
import presentacion.controlador.ControladorDatosDB;
import presentacion.vista.MensajesDeDialogo;
import presentacion.vista.Vista;


public class Main 
{

	public static void main(String[] args) 
	{
		boolean conectable = Conexion.testConexion();
		if(!conectable) {
			MensajesDeDialogo.getInstance().msgDatosConexionBD();
			ventanaConexion(conectable);
		}		
		
		Vista vista = new Vista();
		Agenda modelo = new Agenda(new DAOSQLFactory());
		Controlador controlador = new Controlador(vista, modelo);
		
		controlador.inicializar();
	
	}
	
	private static void ventanaConexion(boolean conectable) {
		ControladorDatosDB controladorVentanaDatosDB = ControladorDatosDB.getInstance();
		controladorVentanaDatosDB.asignarModo("Información de la Base de Datos");	
							
		while(!conectable) {				
			controladorVentanaDatosDB.mostrarVentana();
			if (!Conexion.testConexion()) {
				while(controladorVentanaDatosDB.ventanaCargada()) {
					// wait for the user input
				}			
			}else {
				conectable = true;
			}
		}
	}	

}
