package presentacion.controlador;

import java.util.Date;
import java.util.List;

import dto.DomicilioDTO;
import dto.LocalidadDTO;
import dto.PersonaDTO;
import dto.TipoContactoDTO;
import modelo.Domicilio;
import modelo.Localidad;
import modelo.Persona;
import modelo.TipoContacto;
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
	
	public void asignarModo(String modo) {
		this.ventana.setTitle(modo);
		this.ventana.getBtnAccionPersona().setText(modo);
	}
	
	public void mostrarVentana() {
		ventana.mostrarVentana();
	}
	
	public void cargarPersona(Persona persona) {
		Object[] registro = { 
					persona.getNombre(),
					persona.getTelefono(),
					persona.getDomicilio().getCalle(),
					persona.getDomicilio().getAltura(),
					persona.getDomicilio().getPiso(),
					persona.getDomicilio().getDepto(),
					persona.getDomicilio().getLocalidad(),		
					persona.getEmail(),
					persona.getFechaNacimiento()
		};
		this.ventana.setPersona(registro);		
	}
	
	public void cargarComboLocalidades(String localidad) {		
		this.ventana.agregarLocalidadAlCombo(localidad);		
	}
	
	public void cargarComboTipoContacto(String tipoContacto) {
		this.ventana.agregarTipoContactoAlCombo(tipoContacto);
	}
	
	private void cargarActionListeners() {		
		String accion = this.ventana.getBtnAccionPersona().getText();
		this.ventana.getBtnAccionPersona().addActionListener(a->reportarEvento(accion));		
	}	
	
	private void reportarEvento(String evento) {
		switch (evento) {
		case "Agregar":
			Persona personaNueva = getPersonaDesdeVentana();	
			controladorSuperior.agregarContacto(personaNueva);
			break;
		case "Editar":
			Persona personaOriginal = this.persona;
			Persona personaEditada = getPersonaDesdeVentana();	
			controladorSuperior.editarContacto(personaOriginal, personaEditada);
			break;
		default:
			break;
		}
	}

	private Persona getPersonaDesdeVentana() {		
		String nombre = this.ventana.getNombre();
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
		return new Persona (nombre, telefono, domicilio, tipoContacto, email, fechaNacimiento);		
	}
	
	public static PersonaDTO getPersonaDTO(Persona persona) {
		String nombre = persona.getNombre();
		String telefono = persona.getTelefono();
		String calle = persona.getDomicilio().getCalle();
		String altura = persona.getDomicilio().getAltura();
		String piso = persona.getDomicilio().getPiso();
		String depto = persona.getDomicilio().getDepto();
		Localidad localidad = persona.getDomicilio().getLocalidad();		
		String email = persona.getEmail();
		Date fechaNacimiento = persona.getFechaNacimiento();
		TipoContacto tipoContacto = persona.getTipoContacto();
		
		LocalidadDTO localidadDTO = ControladorLocalidad.getLocalidadDTO(localidad);
		DomicilioDTO domicilioDTO = new DomicilioDTO(0, calle, altura, piso, depto, localidadDTO);
		TipoContactoDTO tipoContactoDTO = ControladorTipoContacto.getTipoContactoDTO(tipoContacto);
		PersonaDTO personaDTO = new PersonaDTO(nombre, telefono, domicilioDTO, tipoContactoDTO, email, fechaNacimiento);
		return personaDTO; 
	}
	
	public static Persona getPersona(PersonaDTO personaDTO) {
		String nombre = personaDTO.getNombre();
		String telefono = personaDTO.getTelefono();
		String calle = personaDTO.getDomicilio().getCalle();
		String altura = personaDTO.getDomicilio().getAltura();
		String piso = personaDTO.getDomicilio().getPiso();
		String depto = personaDTO.getDomicilio().getDepto();				
		String email = personaDTO.getEmail();
		Date fechaNacimiento = personaDTO.getFechaNacimiento();
		
		Localidad localidad = ControladorLocalidad.getLocalidad(personaDTO.getDomicilio().getLocalidad());
		Domicilio domicilio = new Domicilio(calle, altura, piso, depto, localidad);
		TipoContacto tipoContacto = ControladorTipoContacto.getTipoContacto(personaDTO.getTipoContacto());
		Persona persona = new Persona (nombre, telefono, domicilio, tipoContacto, email, fechaNacimiento);
		return persona; 
	}	
}