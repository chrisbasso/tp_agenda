package presentacion.controlador;

import java.util.Date;
import java.util.List;

import modelo.Domicilio;
import modelo.Localidad;
import modelo.Persona;
import modelo.TipoContacto;
import persistencia.dto.DomicilioDTO;
import persistencia.dto.LocalidadDTO;
import persistencia.dto.PersonaDTO;
import persistencia.dto.TipoContactoDTO;

public class HelperDTODAO {
	//-----------------Conversiones DAO-DTO----------------- //
	public static PersonaDTO crearPersonaDTOConObjeto(Persona persona, int idDomicilio, int idTipoContacto) {
		String nombre = persona.getNombre();
		String apellido = persona.getApellido();
		String telefono = persona.getTelefono();		
		String email = persona.getEmail();
		Date fechaNacimiento = persona.getFechaNacimiento();		
		return new PersonaDTO(nombre, apellido, telefono, idDomicilio, idTipoContacto, email, fechaNacimiento); 
	}
	
	public static Persona crearPersonaConDTO(PersonaDTO personaDTO, DomicilioDTO domicilioDTO, 
			LocalidadDTO localidadDTO, TipoContactoDTO tipoContactoDTO) {
		String nombre = personaDTO.getNombre();
		String apellido = personaDTO.getApellido();
		String telefono = personaDTO.getTelefono();
		String calle = domicilioDTO.getCalle();
		String altura = domicilioDTO.getAltura();
		String piso = domicilioDTO.getPiso();
		String depto = domicilioDTO.getDepto();
		Localidad localidad = crearLocalidadConDTO(localidadDTO);
		Domicilio domicilio = new Domicilio(calle, altura, piso, depto, localidad);
		String email = personaDTO.getEmail();
		Date fechaNacimiento = personaDTO.getFechaNacimiento();		
		TipoContacto tipoContacto = crearTipoContactoConDTO(tipoContactoDTO);
		return new Persona (nombre, apellido, telefono, domicilio, tipoContacto, email, fechaNacimiento); 
	}	

	public static LocalidadDTO crearLocalidadDTOConObjeto(Localidad localidad) {
		return new LocalidadDTO(localidad.getNombreLocalidad());		
	}

	public static Localidad crearLocalidadConDTO(LocalidadDTO localidadDTO) {
		return new Localidad(localidadDTO.getNombreLocalidad());
	}

	public static TipoContactoDTO crearTipoContactoDTOConObjeto(TipoContacto tipoContacto) {
		return new TipoContactoDTO (tipoContacto.getTipoContacto());
	}

	public static TipoContacto crearTipoContactoConDTO(TipoContactoDTO tipoContacto) {
		return new TipoContacto(tipoContacto.getTipoContacto());
	}
	
	public static DomicilioDTO crearDomicilioDTOConObjeto(Domicilio domicilio, int idLocalidad) {
		String calle = domicilio.getCalle();
		String altura = domicilio.getAltura();
		String piso = domicilio.getPiso();
		String depto = domicilio.getDepto();		
		return new DomicilioDTO (calle,altura,piso,depto,idLocalidad);
	}

	public static Domicilio crearDomicilioConDTO(DomicilioDTO domicilioDTO, LocalidadDTO localidadDTO) {
		String calle = domicilioDTO.getCalle();
		String altura = domicilioDTO.getAltura();
		String piso = domicilioDTO.getPiso();
		String depto = domicilioDTO.getDepto();
		Localidad localidad = crearLocalidadConDTO(localidadDTO);
		return new Domicilio(calle,altura,piso,depto,localidad);
	}
	
	public static boolean compararPersonaDTOconPersona(PersonaDTO personaDTO, Persona persona) {
		boolean ret;
		ret = personaDTO.getNombre().equals(persona.getNombre()) 		&&
			  personaDTO.getApellido().equals(persona.getApellido()) 	&&		
			  personaDTO.getTelefono().equals(persona.getTelefono())	&&		
			  personaDTO.getEmail().equals(persona.getEmail())			&&
			  personaDTO.getFechaNacimiento().equals(persona.getFechaNacimiento());	
				
		return ret;
	}
	
	public static boolean compararDomicilioDTOconDomicilio(DomicilioDTO domicilioDTO, String localidadDelDTO, Domicilio domicilio) {
		boolean ret;
		ret = domicilioDTO.getAltura().equals(domicilio.getAltura()) 		&&
			  domicilioDTO.getCalle().equals(domicilio.getCalle()) 	&&		
			  domicilioDTO.getDepto().equals(domicilio.getDepto())	&&
			  localidadDelDTO.equals(domicilio.getLocalidad().getNombreLocalidad());
		return ret;
	}
	
	public static int obtenerIdLocalidad (String nombreLocalidad, List <LocalidadDTO> localidadesDTO) {
		for(LocalidadDTO localidadDTO : localidadesDTO) {			
			if(nombreLocalidad.equals(localidadDTO.getNombreLocalidad())){
				return localidadDTO.getIdLocalidad();
			}
		}
		return -1;
	}
	
	public static int obtenerIdDomicilio (Domicilio domicilio, List <DomicilioDTO> domiciliosDTO) {
		for (DomicilioDTO domicilioDTO : domiciliosDTO) {			
			if(compararDomicilioDTOconDomicilio(domicilioDTO, domicilio.getLocalidad().getNombreLocalidad(), domicilio)) {
				return domicilioDTO.getIdDomicilio();
			}
		}	
		return -1;
	}
	
	public static int obtenerIdTipoContacto (TipoContacto tipoContacto, List <TipoContactoDTO> tiposContactoDTO) {
		for (TipoContactoDTO tipoContactoDTO : tiposContactoDTO) {			
			if(tipoContactoDTO.getTipoContacto().equals(tipoContacto.getTipoContacto())) {
				return tipoContactoDTO.getIdTipoContacto();
			}
		}	
		return -1;
	}	
}
