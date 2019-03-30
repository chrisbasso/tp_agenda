package presentacion.controlador;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import presentacion.vista.VentanaDatosDB;

public class ControladorDatosDB {
	private Controlador controladorSuperior;
	private VentanaDatosDB ventana;

	public ControladorDatosDB(Controlador controladorSuperior){
		this.controladorSuperior = controladorSuperior;
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
			controladorSuperior.actualizarDatosDB(ventana.getURL(),ventana.getUSER(),ventana.getPWD());
			break;
		case "salir":
			ventana.cerrar();
			controladorSuperior.cerrarAplicacion();
			break;
		default:
			break;
		}
		
	}

	public void asignarModo(String modo) {
		this.ventana.setTitle(modo);
		this.ventana.getBtnAceptar().setText(modo);
	}	
}