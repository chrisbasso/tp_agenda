package presentacion.controlador;

import java.util.ArrayList;
import java.util.List;

import dto.LocalidadDTO;
import dto.PersonaDTO;
import dto.TipoContactoDTO;
import modelo.Agenda;
import modelo.Localidad;
import modelo.Persona;
import modelo.TipoContacto;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.DAOAbstractFactory;
import persistencia.dao.interfaz.DomicilioDAO;
import persistencia.dao.interfaz.LocalidadDAO;
import persistencia.dao.interfaz.PersonaDAO;
import persistencia.dao.interfaz.TipoContactoDAO;
import persistencia.dao.mysql.DAOSQLFactory;
import presentacion.reportes.ReporteAgenda;
import presentacion.vista.MensajesDeDialogo;
import utils.ConfigFile;

public class Controlador {
	private Agenda agenda;
	private boolean conectable;
	private PersonaDAO personaDAO;
	private DomicilioDAO domicilioDAO;
	private LocalidadDAO localidadDAO;
	private TipoContactoDAO tipoContactoDAO;
	
	private ControladorMenuPrincipal controladorVentanaMenuPrincipal;
	private ControladorPersona controladorVentanaPersona;
	private ControladorLocalidad controladorVentanaLocalidad;
	private ControladorTipoContacto controladorVentanaTipoContacto;
	private ControladorDatosDB controladorVentanaDatosDB;
	
	public static void main(String[] args) {
		Controlador main = new Controlador();
	}

	public Controlador(){
		conectable = Conexion.testConexion();
		if(!conectable) {
			MensajesDeDialogo.getInstance().msgDatosConexionBD();
			guiConexion();
		}		
		init();
	}
	
	private void guiConexion() {
		if(controladorVentanaDatosDB == null) {
			controladorVentanaDatosDB = new ControladorDatosDB(this);
			controladorVentanaDatosDB.asignarModo("Información de la Base de Datos");	
		}else {
			controladorVentanaDatosDB.cerrarVentana();
		}
					
		while(!conectable) {				
			controladorVentanaDatosDB.mostrarVentana();
			if (!Conexion.testConexion()) {
				while(controladorVentanaDatosDB.ventanaCargada()) {
					// wait for the user input
				}			
			}else {
				conectable = true;
			}
		}
	}	
	
	private void init() {
		iniciarDAOs();		
		iniciarAgenda();
		cargarControladores();				
		cargarVentanaPrincipal();
	}

	private void iniciarDAOs() {
		DAOAbstractFactory factory = new DAOSQLFactory(); 
		personaDAO = factory.createPersonaDAO();
		domicilioDAO = factory.createDomicilioDAO();
		localidadDAO = factory.createLocalidadDAO();
		tipoContactoDAO = factory.createTipoContactoDAO();
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
		controladorVentanaMenuPrincipal = new ControladorMenuPrincipal(this);		
		controladorVentanaPersona = new ControladorPersona(this);
		controladorVentanaLocalidad = new ControladorLocalidad(this);
		controladorVentanaTipoContacto = new ControladorTipoContacto(this);
	}
	
	private void cargarVentanaPrincipal() {		
		controladorVentanaMenuPrincipal.cargarTabla(agenda);
		controladorVentanaMenuPrincipal.mostrarVentana();
	}
	
	//-----------------Eventos de Menu principal----------------- //
	public void agregarContacto() {
		controladorVentanaPersona.cargarPersona(null);
		cargarCombosVentanaPersona();
		controladorVentanaPersona.asignarModo("Agregar");
		controladorVentanaPersona.mostrarVentana();		
	}
	
	public void editarContacto(Persona persona) {
		controladorVentanaPersona.cargarPersona(persona);
		cargarCombosVentanaPersona();
		controladorVentanaPersona.asignarModo("Editar");
		controladorVentanaPersona.mostrarVentana();		
	}
	
	private void cargarCombosVentanaPersona() {
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
		domicilioDAO.delete(ControladorPersona.getPersonaDTO(persona).getDomicilio().getIdDomicilio());
		cargarVentanaPrincipal(); 
	}

	public void abmLocalidad() {
		controladorVentanaLocalidad.mostrarVentana();
	}
	
	public void abmTipoContacto() {
		controladorVentanaTipoContacto.mostrarVentana();
	}
	
	public void reporte() {
		List <PersonaDTO> personasReporte = new ArrayList <PersonaDTO> ();
		for (Persona persona : agenda.obtenerPersonas()) {
			personasReporte.add(ControladorPersona.getPersonaDTO(persona));
		}
		
		ReporteAgenda reporte = new ReporteAgenda (personasReporte); 
		reporte.mostrar();
	}
	
	public void baseDeDatos() {
		controladorVentanaDatosDB = new ControladorDatosDB(this);
		controladorVentanaDatosDB.asignarModo("Información de la Base de Datos");			
		controladorVentanaDatosDB.mostrarVentana();		
	}	
	//-----------------Fin Eventos Menu principal----------------- //
	
	//-----------------Eventos de Persona----------------- //
	public void agregarContacto(Persona personaNueva) {
		agenda.agregarPersona(personaNueva);
		domicilioDAO.insert(ControladorPersona.getPersonaDTO(personaNueva).getDomicilio());
		personaDAO.insert(ControladorPersona.getPersonaDTO(personaNueva));
	}
	
	public void editarContacto(Persona personaOriginal,Persona personaEditada) {
		agenda.editarPersona(personaOriginal, personaEditada);
		domicilioDAO.editar(ControladorPersona.getPersonaDTO(personaEditada).getDomicilio());
		personaDAO.editar(ControladorPersona.getPersonaDTO(personaEditada));
	}	
	
	public Localidad localidadPorNombre(String localidad) {
		for(Localidad loc : agenda.obtenerLocalidades()) {
			if (loc.getNombreLocalidad().equals(localidad)) {
				return loc;
			}
		}
		return null;
	}

	public TipoContacto TipoContactoPorNombre(String tipoContacto) {
		for(TipoContacto tipoCon : agenda.obtenerTipoContactoes()) {
			if (tipoCon.getTipoContacto().equals(tipoContacto)) {
				return tipoCon;
			}
		}
		return null;
	}
	//-----------------Fin Eventos Persona----------------- //
	
	//-----------------Eventos de Localidad----------------- //
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
	
	//-----------------Eventos de Tipo Contacto----------------- //
	public void agregarTipoContacto(TipoContacto tipoContacto) {
		agenda.agregarTipoContacto(tipoContacto);
		tipoContactoDAO.insert(ControladorTipoContacto.getTipoContactoDTO(tipoContacto));
	}
	
	public void editarTipoContacto(TipoContacto tipoContactoOriginal, TipoContacto tipoContactoModificado) {
		agenda.editarTipoContacto(tipoContactoOriginal, tipoContactoModificado);
		tipoContactoDAO.edit(ControladorTipoContacto.getTipoContactoDTO(tipoContactoModificado));
	}
	
	public void borrarTipoContacto(TipoContacto tipoContacto) {
		agenda.borrarTipoContacto(tipoContacto);
		tipoContactoDAO.delete(ControladorTipoContacto.getTipoContactoDTO(tipoContacto));
	}
	//-----------------Fin Tipo Contacto----------------- //

	//-----------------Eventos de DatosDB----------------- //
	public void actualizarDatosDB(String URL, String USER, String PWD) {
		ConfigFile.getInstance().update(URL, USER, PWD);
		MensajesDeDialogo.getInstance().msgDatosConexionBDModificados();
		System.exit(0);
	}
	
	public void cerrarAplicacion() {
		Conexion.getConexion().cerrarConexion();
		System.exit(0);
	}	
	//-----------------Fin de DatosDB----------------- //
}