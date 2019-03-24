package presentacion.controlador;

import modelo.Agenda;
import modelo.Persona;
import presentacion.vista.VentanaPersona;
import presentacion.vista.VentanaPrincipal;

public class Controlador {
	private Agenda agenda;
	private ControladorMenuPrincipal controladorVentanaPrincipal;
	private ControladorPersona controladorVentanaPersona;
	private ControladorLocalidad controladorVentanaLocalidad;
	private ControladorTipoContacto controladorVentanaTipoContacto;

	public Controlador(VentanaPrincipal vista, Agenda agenda){
		this.agenda = agenda;
		controladorVentanaPrincipal = new ControladorMenuPrincipal(vista, agenda);
		controladorVentanaPrincipal.setControlador(this);
	}
	
	public void otros() {
		Persona persona = new Persona();
		controladorVentanaPersona = new ControladorPersona(VentanaPersona.getInstance(), agenda, persona);
//		controladorVentanaLocalidad = new ControladorLocalidad();
//		controladorVentanaTipoContacto = new ControladorTipoContacto();

	}
	
	public void evento() {
		
	}
}