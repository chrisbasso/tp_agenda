package presentacion.controlador;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import persistencia.conexion.Conexion;
import presentacion.vista.MensajesDeDialogo;
import presentacion.vista.VentanaDatosDB;
import utils.ConfigFile;

public class ControladorDatosDB {
	private static ControladorDatosDB INSTANCIA;
	private VentanaDatosDB ventana;
	

	public static ControladorDatosDB getInstance(){
		if(INSTANCIA == null)
			return new ControladorDatosDB();
		else
			return INSTANCIA;
	}
	
	private ControladorDatosDB(){		
		this.ventana = VentanaDatosDB.getInstance();
		cargarActionListeners();
	}
	
	public void mostrarVentana() {
		ventana.mostrarVentana();
	}
	
	public boolean ventanaCargada() {
		return ventana != null;
	}	

	public void cerrarVentana() {
		ventana.cerrar();
	}
	
	private void cargarActionListeners() {		
		ventana.getBtnAceptar().addActionListener(a->reportarEvento("aceptar"));		
		ventana.getBtnSalir().addActionListener(a->reportarEvento("salir"));
		ventana.addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e) {
				reportarEvento("salir");
			}
		});
	}	
	
	private void reportarEvento(String evento) {
		switch (evento) {
		case "aceptar":
			ConfigFile.getInstance().update(ventana.getURL(), ventana.getUSER(), ventana.getPWD(), "TRUE");
			MensajesDeDialogo.getInstance().msgDatosConexionBDModificados();
			System.exit(0);
			break;
		case "salir":
			ventana.cerrar();
			Conexion.getConexion().cerrarConexion();
			System.exit(0);
			break;
		default:
			break;
		}
		
	}

	public void asignarModo(String modo) {
		this.ventana.setTitle(modo);
	}	
}