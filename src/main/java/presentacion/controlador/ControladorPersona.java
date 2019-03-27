package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import dto.DomicilioDTO;
import dto.LocalidadDTO;
import dto.PersonaDTO;
import dto.TipoContactoDTO;
import modelo.Domicilio;
import modelo.Localidad;
import modelo.Persona;
import modelo.TipoContacto;
import presentacion.vista.VentanaPersona;

public class ControladorPersona implements ActionListener{
	private Controlador controladorSuperior;
	private VentanaPersona ventana;

	public ControladorPersona(){
		this.ventana = VentanaPersona.getInstance();		
	}

	public void agregarPersona() {
		ventana.setVisible(true);
		// do something
		ventana.cerrar();
	}
	
	public void editarPersona(int idPersona) {
		
	}

	public void borrarPersona(int idPersona) {
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub		
	}
	
	public void setControladorSuperior(Controlador controladorSuperior) {
		this.controladorSuperior = controladorSuperior;
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