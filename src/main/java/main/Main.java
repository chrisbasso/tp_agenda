package main;


import modelo.Agenda;
import persistencia.conexion.Conexion;
import persistencia.dao.mysql.DAOSQLFactory;
import persistencia.dao.mysql.DeploySQL;
import presentacion.controlador.Controlador;
import presentacion.controlador.ControladorDatosDB;
import presentacion.vista.MensajesDeDialogo;
import presentacion.vista.Vista;
import utils.ConfigFile;


public class Main 
{

	public static void main(String[] args) 
	{
		if(!ConfigFile.getInstance().getDEPLOYED().equals("TRUE")) {
			DeploySQL.executeCommand(DeploySQL.dropDB);
			DeploySQL.executeCommand(DeploySQL.create);
			DeploySQL.useDB();
			DeploySQL.executeCommand(DeploySQL.localidad);
			DeploySQL.executeCommand(DeploySQL.contacto); 
			DeploySQL.executeCommand(DeploySQL.domicilio);
			DeploySQL.executeCommand(DeploySQL.persona);
			
			ConfigFile.getInstance().update(ConfigFile.getInstance().getURL(), ConfigFile.getInstance().getUSR(), ConfigFile.getInstance().getPWD(), "TRUE");
		}
		
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
