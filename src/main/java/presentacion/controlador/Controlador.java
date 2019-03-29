package presentacion.controlador;

import java.util.List;

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
		iniciarDAOs();		
		iniciarAgenda();
		cargarControladores();				
		cargarVentanaPrincipal();
	}

	private void iniciarDAOs() {
		DAOAbstractFactory factory = new DAOSQLFactory(); 
		this.personaDAO = factory.createPersonaDAO();
		this.localidadDAO = factory.createLocalidadDAO();
		this.tipoContactoDAO = factory.createTipoContactoDAO();
	}
	
	private void iniciarAgenda() {
		this.agenda = new Agenda();
		cargarPersonasEnAgenda();
		cargarLocalidadesEnAgenda();
		cargarTipoContactoEnAgenda();
	}
	
	private void cargarPersonasEnAgenda() {		
		List <PersonaDTO> personasDTO = personaDAO.readAll();
		for(PersonaDTO personaDTO : personasDTO) {
			Persona persona = ControladorPersona.getPersona(personaDTO);
			agenda.agregarPersona(persona);
		}
	}
	
	private void cargarLocalidadesEnAgenda() {
		List <LocalidadDTO> localidadesDTO = localidadDAO.readAll();
		for(LocalidadDTO localidadDTO : localidadesDTO) {
			Localidad localidad = ControladorLocalidad.getLocalidad(localidadDTO);
			agenda.agregarLocalidad(localidad);
		}
	}
	
	private void cargarTipoContactoEnAgenda() {
		List <TipoContactoDTO> tipoContactosDTO = tipoContactoDAO.readAll();
		for(TipoContactoDTO tipoContactoDTO : tipoContactosDTO) {
			TipoContacto tipoContacto = ControladorTipoContacto.getTipoContacto(tipoContactoDTO);
			agenda.agregarTipoContacto(tipoContacto);
		}
	}	
	
	private void cargarControladores() {
		controladorVentanaPrincipal = new ControladorMenuPrincipal(this);		
		controladorVentanaPersona = new ControladorPersona(this);
		controladorVentanaLocalidad = new ControladorLocalidad(this);
		controladorVentanaTipoContacto = new ControladorTipoContacto(this);
	}
	
	private void cargarVentanaPrincipal() {		
		controladorVentanaPrincipal.cargarTabla(agenda);
		controladorVentanaPrincipal.mostrarVentana();
	}

	//-----------------Llamadas de Menu principal----------------- //
	public void agregarContacto() {
		controladorVentanaPersona.cargarPersona(null);
		cargarCombos();
		controladorVentanaPersona.asignarModo("Agregar");
		controladorVentanaPersona.mostrarVentana();		
	}
	
	public void editarContacto(Persona persona) {
		controladorVentanaPersona.cargarPersona(persona);
		cargarCombos();
		controladorVentanaPersona.asignarModo("Editar");
		controladorVentanaPersona.mostrarVentana();		
	}
	
	private void cargarCombos() {
		for(Localidad localidad : agenda.obtenerLocalidades()) {
			controladorVentanaPersona.cargarComboLocalidades(localidad.getNombreLocalidad());
		}
		for(TipoContacto tipoContacto : agenda.obtenerTipoContactoes()) {
			controladorVentanaPersona.cargarComboTipoContacto(tipoContacto.getTipoContacto());
		}
	}

	public void borrarContacto(Persona persona) {
		agenda.borrarPersona(persona);
		personaDAO.delete(ControladorPersona.getPersonaDTO(persona));
		cargarVentanaPrincipal(); 
	}

	public void abmLocalidad() {
		controladorVentanaLocalidad.mostrarVentana();
	}
	
	public void abmTipoContacto() {
		controladorVentanaTipoContacto.mostrarVentana();
	}
	//-----------------Fin Menu principal----------------- //
	
	//-----------------Llamadas de Persona----------------- //
	public void agregarContacto(Persona personaNueva) {
		agenda.agregarPersona(personaNueva);
		personaDAO.insert(ControladorPersona.getPersonaDTO(personaNueva));
	}
	
	public void editarContacto(Persona personaOriginal,Persona personaEditada) {
		agenda.editarPersona(personaOriginal, personaEditada);
		personaDAO.editar(ControladorPersona.getPersonaDTO(personaEditada));
	}
	
	public Localidad localidadPorNombre(String localidad) {
		// TODO Auto-generated method stub
		return null;
	}

	public TipoContacto TipoContactoPorNombre(String tipoContacto) {
		// TODO Auto-generated method stub
		return null;
	}
	//-----------------Fin Persona----------------- //
	
	//-----------------Llamadas de Localidad----------------- //
	public void agregarLocalidad(Localidad localidad) {
		agenda.agregarLocalidad(localidad);
		localidadDAO.insert(localidad.getNombreLocalidad()); // Debería recibir el DTO
	}

	public void editarLocalidad(Localidad localidadAnterior, Localidad localidadNueva) {
		agenda.editarLocalidad(localidadAnterior, localidadNueva);
		localidadDAO.editar(ControladorLocalidad.getLocalidadDTO(localidadNueva));
	}
	
	public void borrarLocalidad(Localidad localidad) {
		agenda.borrarLocalidad(localidad);
		// TODO localidadDAO.delete(localidad.);
	}
	
	//-----------------Fin Localidad----------------- //
	
	//-----------------Llamadas de Tipo Contacto----------------- //
	public void agregarTipoContacto(TipoContacto tipoContacto) {
		agenda.agregarTipoContacto(tipoContacto);
	}
	
	public void editarTipoContacto(TipoContacto tipoContactoOriginal, TipoContacto tipoContactoModificado) {
		agenda.editarTipoContacto(tipoContactoOriginal, tipoContactoModificado);
	}
	
	public void borrarTipoContacto(TipoContacto tipoContacto) {
		agenda.borrarTipoContacto(tipoContacto);
	}

	//-----------------Fin Tipo Contacto----------------- //
	
	//-----------------Llamadas de Reporte----------------- //
	public void reporte() {
		// TODO Auto-generated method stub		
	}

	//-----------------Fin Reporte----------------- //
}