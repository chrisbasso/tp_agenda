package presentacion.controlador;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;

import javax.swing.JOptionPane;

import modelo.Domicilio;
import modelo.Localidad;
import modelo.Persona;
import modelo.TipoContacto;
import persistencia.dto.DomicilioDTO;
import persistencia.dto.LocalidadDTO;
import persistencia.dto.PersonaDTO;
import persistencia.dto.TipoContactoDTO;
import presentacion.vista.MensajesDeDialogo;
import presentacion.vista.VentanaPersona;

public class ControladorPersona {
	private Controlador controladorSuperior;
	private VentanaPersona ventana;
	private Persona persona;

	public ControladorPersona(Controlador controladorSuperior){
		this.controladorSuperior = controladorSuperior;
		this.ventana = VentanaPersona.getInstance();
		cargarActionListeners();
	}
	
	public void cargarPersona(Persona persona) {
		if (persona != null) {
			Object[] registro = { 
						persona.getNombre(),
						persona.getApellido(),
						persona.getTelefono(),
						persona.getDomicilio().getCalle(),
						persona.getDomicilio().getAltura(),
						persona.getDomicilio().getPiso(),
						persona.getDomicilio().getDepto(),
						persona.getEmail(),
						persona.getFechaNacimiento(),
						persona.getTipoContacto().getTipoContacto(),
						persona.getDomicilio().getLocalidad().getNombreLocalidad()
			};
			ventana.setPersona(registro);
		}else {
			ventana.setPersona(null);
		}				
	}
	
	public void mostrarVentana() {
		ventana.mostrarVentana();
	}

	public void cerrarVentana() {
		ventana.cerrar();
	}
	
	private void cargarActionListeners() {		
		String accion = this.ventana.getBtnAccionPersona().getText();
		this.ventana.getBtnAccionPersona().addActionListener(a->reportarEvento(accion));
		ventana.addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e) {
				int confirm = JOptionPane.showOptionDialog(
						null, "Estas seguro que quieres salir de la Agenda!?",
						"Confirmacion", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, null, null);
				if (confirm == 0) {
					reportarEvento("cerrar");
				}
			}
		});
	}	
	
	private void reportarEvento(String evento) {
		switch (evento) {
		case "Agregar":
			Persona personaNueva = getPersonaDesdeVentana();	
			controladorSuperior.agregarContactoNuevo(personaNueva);
			break;
		case "Editar":
			Persona personaOriginal = this.persona;
			Persona personaEditada = getPersonaDesdeVentana();	
			if(personaOriginal.equals(personaEditada)) {
				MensajesDeDialogo.getInstance().msgSinCambios();
			}else {
				controladorSuperior.editarContactoBD(personaOriginal, personaEditada);
			}			
			break;
		case "cerrar":
			controladorSuperior.cerrarAplicacion();
			break;
		default:
			break;
		}
	}

	public void asignarModo(String modo) {
		this.ventana.setTitle(modo);
		this.ventana.getBtnAccionPersona().setText(modo);
	}
	
	public void cargarComboLocalidades(String localidad) {		
		this.ventana.agregarLocalidadAlCombo(localidad);		
	}
	
	public void cargarComboTipoContacto(String tipoContacto) {
		this.ventana.agregarTipoContactoAlCombo(tipoContacto);
	}
	
	private Persona getPersonaDesdeVentana() {		
		String nombre = this.ventana.getNombre();
		String apellido = this.ventana.getApellido();
		String telefono = this.ventana.getTelefono();
		String calle = this.ventana.getCalle();
		String altura = this.ventana.getAltura();
		String piso = this.ventana.getPiso();
		String depto = this.ventana.getDepto();				
		String email = this.ventana.getEmail();
		Date fechaNacimiento = this.ventana.getFechaNacimiento();		
		Localidad localidad = controladorSuperior.localidadPorNombre(this.ventana.getLocalidad());		
		
		Domicilio domicilio = new Domicilio(calle, altura, piso, depto, localidad);
		TipoContacto tipoContacto = controladorSuperior.TipoContactoPorNombre(this.ventana.getTipoContacto());
		return new Persona (nombre, apellido, telefono, domicilio, tipoContacto, email, fechaNacimiento);		
	}
}