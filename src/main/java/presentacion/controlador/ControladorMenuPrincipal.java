package presentacion.controlador;

import java.util.List;

import modelo.Agenda;
import modelo.Persona;
import presentacion.vista.MensajesDeDialogo;
import presentacion.vista.VentanaPrincipal;

public class ControladorMenuPrincipal{
	private Controlador controladorSuperior;
	private VentanaPrincipal ventana;
	private List <Persona> personas; 

	public ControladorMenuPrincipal(Controlador controladorSuperior){
		this.controladorSuperior = controladorSuperior;
		this.ventana = VentanaPrincipal.getInstance();
		cargarActionListeners();
	}
	
	public void cargarTabla(Agenda agenda) {
		this.ventana.inicializarTabla();		
		this.personas = agenda.obtenerPersonas(); 
		for (Persona persona : personas){
			Object[] fila = {
						persona.getNombre(),
						persona.getTelefono(),
						persona.getDomicilio().getCalle(),
						persona.getDomicilio().getAltura(),
						persona.getDomicilio().getPiso(),
						persona.getDomicilio().getDepto(),
						persona.getDomicilio().getLocalidad().getNombreLocalidad(),
						persona.getTipoContacto().getTipoContacto(),
						persona.getEmail(),
						persona.getFechaNacimiento()
						};
			this.ventana.agregarContactoATabla(fila);
		}
	}
	
	public void mostrarVentana() {
		this.ventana.show();
	}

	public void ocultarVentana() {
		this.ventana.hide();
	}

	private void cargarActionListeners() {
		this.ventana.getMntmAgregar().addActionListener(a->reportarEvento("agregar"));
		this.ventana.getMntmEditar().addActionListener(a->reportarEvento("editar"));					
		this.ventana.getMntmBorrar().addActionListener(a->reportarEvento("borrar"));
		this.ventana.getMntmReporte().addActionListener(a->reportarEvento("reporte"));
		this.ventana.getMntmAbmLocalidad().addActionListener(a->reportarEvento("abmLocalidad"));
		this.ventana.getMntmAbmTipoDeContacto().addActionListener(a->reportarEvento("abmTipoContacto"));
	}	
	
	private void reportarEvento(String evento) {
		switch (evento) {
		case "agregar":
			ocultarVentana();
			controladorSuperior.agregarContacto();
			break;
		case "editar":
			Persona personaAEditar = obtenerSeleccionado(); 
			if( personaAEditar == null){
				MensajesDeDialogo.getInstance().msgSeleccionarFila();
			}else {
				ocultarVentana();
				controladorSuperior.editarContacto(personaAEditar);
			}			
			break;
		case "borrar":
			Persona personaABorrar = obtenerSeleccionado();
			if( personaABorrar == null){
				MensajesDeDialogo.getInstance().msgSeleccionarFila(); 
			}else {
				controladorSuperior.borrarContacto(personaABorrar);
			}			
			break;		
		case "abmLocalidad":	
			ocultarVentana();
			controladorSuperior.abmLocalidad();
			break;
		case "abmTipoContacto":	
			ocultarVentana();
			controladorSuperior.abmTipoContacto();
			break;
		case "reporte":
			ocultarVentana();
			controladorSuperior.reporte();
			break;
		default:
			break;
		}
	}
	
	private Persona obtenerSeleccionado() {
		int indexOf = ventana.obtenerFilaSeleccionada();
		if(indexOf == -1) {
			return null;
		}else {
			return personas.get(indexOf);
		}		
	}
}