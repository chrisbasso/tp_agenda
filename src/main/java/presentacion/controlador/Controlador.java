package presentacion.controlador;

import java.util.List;

import dto.DomicilioDTO;
import dto.LocalidadDTO;
import dto.PersonaDTO;
import dto.TipoContactoDTO;
import modelo.Agenda;
import modelo.Localidad;
import modelo.Persona;
import modelo.TipoContacto;
import persistencia.dao.interfaz.DAOAbstractFactory;
import persistencia.dao.interfaz.LocalidadDAO;
import persistencia.dao.interfaz.PersonaDAO;
import persistencia.dao.interfaz.TipoContactoDAO;
import persistencia.dao.mysql.DAOSQLFactory;

public class Controlador {
	private Agenda agenda;
	private PersonaDAO personaDAO;
	private LocalidadDAO localidadDAO;
	private TipoContactoDAO tipoContactoDAO;
	
	private ControladorMenuPrincipal controladorVentanaPrincipal;
	private ControladorPersona controladorVentanaPersona;
	private ControladorLocalidad controladorVentanaLocalidad;
	private ControladorTipoContacto controladorVentanaTipoContacto;
	
	public Controlador(){
		DAOAbstractFactory factory = new DAOSQLFactory(); 
		this.personaDAO = factory.createPersonaDAO();
		this.localidadDAO = factory.createLocalidadDAO();
		this.tipoContactoDAO = factory.createTipoContactoDAO();
		
		this.agenda = new Agenda();
		cargarPersonas();
		cargarLocalidades();
		cargarTipoContacto();
		cargarControladores();				
	}
	
	private void cargarPersonas() {		
		List <PersonaDTO> personasDTO = personaDAO.readAll();
		for(PersonaDTO personaDTO : personasDTO) {
			Persona persona = ControladorPersona.getPersona(personaDTO);
			agenda.agregarPersona(persona);
		}
	}
	
	private void cargarLocalidades() {
		List <LocalidadDTO> localidadesDTO = localidadDAO.readAll();
		for(LocalidadDTO localidadDTO : localidadesDTO) {
			Localidad localidad = ControladorLocalidad.getLocalidad(localidadDTO);
			agenda.agregarLocalidad(localidad);
		}
	}
	
	private void cargarTipoContacto() {
		List <TipoContactoDTO> tipoContactosDTO = tipoContactoDAO.readAll();
		for(TipoContactoDTO tipoContactoDTO : tipoContactosDTO) {
			TipoContacto tipoContacto = ControladorTipoContacto.getTipoContacto(tipoContactoDTO);
			agenda.agregarTipoContacto(tipoContacto);
		}
	}	
	
	private void cargarControladores() {
		controladorVentanaPrincipal = new ControladorMenuPrincipal();
		controladorVentanaPrincipal.setControladorSuperior(this);
		
		controladorVentanaPersona = new ControladorPersona();
		controladorVentanaPersona.setControladorSuperior(this);
		
		controladorVentanaLocalidad = new ControladorLocalidad();
		controladorVentanaLocalidad.setControladorSuperior(this);
		
		controladorVentanaTipoContacto = new ControladorTipoContacto();
		controladorVentanaTipoContacto.setControladorSuperior(this);
	}
	
	public void agregarContacto() {
		controladorVentanaPersona.agregarPersona();
	}
	
	public void editarContacto(int idPersona) {		
		controladorVentanaPersona.editarPersona(idPersona);
	}

	public void borrarContacto(int idPersona) {
		controladorVentanaPersona.borrarPersona(idPersona);
	}		
	
	public void reporte() {
		// TODO Auto-generated method stub		
	}

	public void abmLocalidad() {
		// TODO Auto-generated method stub
		
	}

	public void abmTipoContacto() {
		// TODO Auto-generated method stub
	}	
}