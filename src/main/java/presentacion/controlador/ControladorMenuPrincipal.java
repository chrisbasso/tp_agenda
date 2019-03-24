package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import modelo.Agenda;
import modelo.Localidad;
import modelo.Persona;
import modelo.TipoContacto;
import presentacion.vista.VentanaPrincipal;

public class ControladorMenuPrincipal implements ActionListener{
	private Controlador controladorSuperior;
	private VentanaPrincipal pantalla;
	private List<Persona> personas;
	private List<TipoContacto> tipoContactos;
	private List<Localidad> localidades;
	private Agenda agenda;

	public ControladorMenuPrincipal(VentanaPrincipal vista, Agenda agenda){
		this.pantalla = vista;
		this.agenda = agenda;
		this.personas = null;
		this.tipoContactos = null;
		actionListeners();
	}
	
	private void actionListeners() {
		this.pantalla.getMntmEditar().addActionListener(a->editarContacto());		
	}

	private void editarContacto() {
		controladorSuperior.evento();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {	
		
	}
	
	public void setControlador(Controlador controladorSuperior) {
		this.controladorSuperior = controladorSuperior;
	}
}