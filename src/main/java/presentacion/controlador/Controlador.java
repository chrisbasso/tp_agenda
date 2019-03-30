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
import persistencia.dao.interfaz.LocalidadDAO;
import persistencia.dao.interfaz.PersonaDAO;
import persistencia.dao.interfaz.TipoContactoDAO;
import persistencia.dao.mysql.DAOSQLFactory;
import presentacion.reportes.ReporteAgenda;
import utils.ConfigFile;

public class Controlador {
	private Agenda agenda;
	private boolean conectable;
	private PersonaDAO personaDAO;
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
		controladorVentanaDatosDB = new ControladorDatosDB(this);
		controladorVentanaDatosDB.asignarModo("Información de la Base de Datos");
		while(!conectable) {
			controladorVentanaDatosDB.mostrarVentana();
			testConexion();
		}
		init();
	}
	
	private void testConexion() {
		if (!Conexion.testConexion()) {
			while(controladorVentanaDatosDB.ventanaCargada()) {
				// wait
			}			
		}else {
			conectable = true;
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
		controladorVentanaMenuPrincipal = new ControladorMenuPrincipal(this);		
		controladorVentanaPersona = new ControladorPersona(this);
		controladorVentanaLocalidad = new ControladorLocalidad(this);
		controladorVentanaTipoContacto = new ControladorTipoContacto(this);
	}
	
	private void cargarVentanaPrincipal() {		
		controladorVentanaMenuPrincipal.cargarTabla(agenda);
		controladorVentanaMenuPrincipal.mostrarVentana();
	}
	
	//-----------------Eventos de DatosDB----------------- //
	public void actualizarDatosDB(String URL, String USER, String PWD) {
		ConfigFile.getInstance().update(URL, USER, PWD);
	}
	
	public void cerrarAplicacion() {
		System.exit(0);
	}	
	//-----------------Fin de DatosDB----------------- //
	
	//-----------------Eventos de Menu principal----------------- //
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
	
	public void reporte() {
		List <PersonaDTO> personasReporte = new ArrayList <PersonaDTO> ();
		for (Persona persona : agenda.obtenerPersonas()) {
			personasReporte.add(ControladorPersona.getPersonaDTO(persona));
		}
		
		ReporteAgenda reporte = new ReporteAgenda (personasReporte); 
		reporte.mostrar();
	}
	
	public void baseDeDatos() {
		
	}	
	//-----------------Fin Eventos Menu principal----------------- //
	
	//-----------------Eventos de Persona----------------- //
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
	
	//-----------------Llamadas de Reporte----------------- //

	//-----------------Fin Reporte----------------- //
}