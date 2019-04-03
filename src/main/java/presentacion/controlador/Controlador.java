package presentacion.controlador;

import java.util.List;

import modelo.Agenda;
import modelo.Domicilio;
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
import persistencia.dto.DomicilioDTO;
import persistencia.dto.LocalidadDTO;
import persistencia.dto.PersonaDTO;
import persistencia.dto.TipoContactoDTO;
import presentacion.reportes.ReporteAgenda;
import presentacion.vista.MensajesDeDialogo;
import utils.ConfigFile;

public class Controlador {
	private Agenda agenda;
	private boolean conectable;
	private LocalidadDAO localidadDAO;
	private DomicilioDAO domicilioDAO;
	private TipoContactoDAO tipoContactoDAO;
	private PersonaDAO personaDAO;
	
	private List <LocalidadDTO> localidadesDTO;
	private List <TipoContactoDTO> tiposContactoDTO;
	private List <PersonaDTO> personasDTO;
	
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
			ventanaConexion();
		}		
		init();
	}
	
	private void ventanaConexion() {
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
		cargarLocalidadesEnAgenda();
		cargarTipoContactoEnAgenda();
		cargarPersonasEnAgenda();
	}
	
	private void cargarLocalidadesEnAgenda() {
		localidadesDTO = localidadDAO.readAll();
		for(LocalidadDTO localidadDTO : localidadesDTO) {
			Localidad localidad = HelperDTODAO.crearLocalidadConDTO(localidadDTO);
			agenda.agregarLocalidad(localidad);
		}
	}
	
	private void cargarTipoContactoEnAgenda() {
		tiposContactoDTO = tipoContactoDAO.readAll();
		for(TipoContactoDTO tipoContactoDTO : tiposContactoDTO) {
			TipoContacto tipoContacto = HelperDTODAO.crearTipoContactoConDTO(tipoContactoDTO);
			agenda.agregarTipoContacto(tipoContacto);
		}
	}	
	
	private void cargarPersonasEnAgenda() {		
		personasDTO = personaDAO.readAll();		
		for(PersonaDTO personaDTO : personasDTO) {			
			DomicilioDTO domicilioDTO = domicilioDAO.obtenerPorId(personaDTO.getIdDomicilio());
			LocalidadDTO localidadDTO = localidadDAO.obtenerPorId(domicilioDTO.getIdLocalidad());
			TipoContactoDTO tipoContactoDTO = tipoContactoDAO.obtenerPorId(personaDTO.getIdTipoContacto());
			Persona persona = HelperDTODAO.crearPersonaConDTO(personaDTO, domicilioDTO, localidadDTO, tipoContactoDTO);
			agenda.agregarPersona(persona);
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
	public void mostrarVentanaAgregarContacto() {
		controladorVentanaPersona.cargarPersona(null);
		cargarCombosVentanaPersona();
		controladorVentanaPersona.asignarModo("Agregar");
		controladorVentanaPersona.mostrarVentana();		
	}
	
	public void mostrarVentanaEditarContacto(Persona persona) {
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
		if(agenda.borrarPersona(persona)) {
			int idDomicilio = -1; 
			int idPersona = -1;
			boolean existeEnBD = false;
			for(PersonaDTO personaDTO : personasDTO) {
				if(HelperDTODAO.compararPersonaDTOconPersona(personaDTO, persona)) {
					existeEnBD = true;
					idDomicilio = personaDTO.getIdDomicilio();
					idPersona = personaDTO.getIdPersona();
				}
			}		
			
			if(existeEnBD) {
				domicilioDAO.delete(idDomicilio);
				personaDAO.delete(idPersona);
				personasDTO = personaDAO.readAll();
				MensajesDeDialogo.getInstance().msgBorrado();
				cargarVentanaPrincipal();
			}						
		}else {
			MensajesDeDialogo.getInstance().msgSinCambios();
		}		
	}

	public void mostrarVentanaABMLocalidad() {
		controladorVentanaLocalidad.mostrarVentana();
	}
	
	public void mostrarVentanaABMTipoContacto() {
		controladorVentanaTipoContacto.mostrarVentana();
	}
	
	public void mostrarReporte() {
		ReporteAgenda reporte = new ReporteAgenda (personasDTO); 
		reporte.mostrar();
	}
	
	public void mostrarVentanaBaseDeDatos() {
		controladorVentanaDatosDB = new ControladorDatosDB(this);
		controladorVentanaDatosDB.asignarModo("Información de la Base de Datos");			
		controladorVentanaDatosDB.mostrarVentana();		
	}	
	//-----------------Fin Eventos Menu principal----------------- //
	
	//-----------------Eventos de Persona----------------- //
	public void agregarContactoNuevo(Persona personaNueva) {
		agenda.agregarPersona(personaNueva);
		
		String localidadPersonaNueva = personaNueva.getDomicilio().getLocalidad().getNombreLocalidad();
		int idLocalidad = HelperDTODAO.obtenerIdLocalidad(localidadPersonaNueva, localidadesDTO);		
		DomicilioDTO domicilioNuevo = HelperDTODAO.crearDomicilioDTOConObjeto(personaNueva.getDomicilio(), idLocalidad); 
		domicilioDAO.insert(domicilioNuevo);		
		int idDomicilio = HelperDTODAO.obtenerIdDomicilio(personaNueva.getDomicilio(), domicilioDAO.readAll());
		
		int idTipoContacto = HelperDTODAO.obtenerIdTipoContacto(personaNueva.getTipoContacto(), tiposContactoDTO);
		PersonaDTO personaNuevaDTO = HelperDTODAO.crearPersonaDTOConObjeto(personaNueva, idDomicilio, idTipoContacto);
		personaDAO.insert(personaNuevaDTO);
		personasDTO.add(personaNuevaDTO);
	}
	
	public void editarContactoBD(Persona personaOriginal,Persona personaEditada) {
		agenda.editarPersona(personaOriginal, personaEditada);
		 
		int idDomicilio = -1; 
		if(!personaOriginal.getDomicilio().equals(personaEditada.getDomicilio())) {
			String localidadPersonaEditada = personaEditada.getDomicilio().getLocalidad().getNombreLocalidad();
			int idLocalidad = HelperDTODAO.obtenerIdLocalidad(localidadPersonaEditada, localidadesDTO);
			
			idDomicilio = HelperDTODAO.obtenerIdDomicilio(personaOriginal.getDomicilio(), domicilioDAO.readAll());
			DomicilioDTO domicilioDTO = domicilioDAO.obtenerPorId(idDomicilio);			
			Domicilio domicilio = personaEditada.getDomicilio();
			domicilioDTO.setAltura(domicilio.getAltura());
			domicilioDTO.setCalle(domicilio.getCalle());
			domicilioDTO.setDepto(domicilio.getDepto());
			domicilioDTO.setIdLocalidad(idLocalidad);
			domicilioDTO.setPiso(domicilio.getPiso());
			domicilioDAO.editar(domicilioDTO);
		}		
		
		int idTipoContacto = -1; 
		if(!personaOriginal.getTipoContacto().equals(personaEditada.getTipoContacto())) {
			idTipoContacto = HelperDTODAO.obtenerIdTipoContacto(personaEditada.getTipoContacto(), tiposContactoDTO);			
		}else {
			idTipoContacto = HelperDTODAO.obtenerIdTipoContacto(personaOriginal.getTipoContacto(), tiposContactoDTO);
		}
		
		PersonaDTO personaPorEditar = null;
		for (PersonaDTO personaDTO : personasDTO) {
			if(HelperDTODAO.compararPersonaDTOconPersona(personaDTO, personaEditada)) {
				personaPorEditar = personaDTO;
			}
		}		
		
		personaPorEditar.setIdDomicilio(idDomicilio);
		personaPorEditar.setIdTipoContacto(idTipoContacto);
		
		personaDAO.editar(personaPorEditar);
		personasDTO = personaDAO.readAll();
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
	public void agregarLocalidadBD(String nombreLocalidad) {
		Localidad localidadNueva = new Localidad(nombreLocalidad);
		agenda.agregarLocalidad(localidadNueva);
		LocalidadDTO localidadNuevaDTO = HelperDTODAO.crearLocalidadDTOConObjeto(localidadNueva);
		localidadDAO.insert(localidadNuevaDTO);
		localidadesDTO = localidadDAO.readAll();
	}

	public void editarLocalidadBD(Localidad localidad, String nuevoNombre) {
		Localidad localidadNueva = new Localidad (nuevoNombre);
		agenda.editarLocalidad(localidad, localidadNueva);
		
		int idLocalidad = HelperDTODAO.obtenerIdLocalidad(localidad.getNombreLocalidad(), localidadesDTO);		
		LocalidadDTO localidadAEditar = localidadDAO.obtenerPorId(idLocalidad);
		localidadAEditar.setNombreLocalidad(nuevoNombre);		
		localidadDAO.editar(localidadAEditar);
		localidadesDTO = localidadDAO.readAll(); 
		
		controladorVentanaLocalidad.cargarTabla(agenda.obtenerLocalidades());
	}
	
	public void borrarLocalidadBD(Localidad localidad) {
		agenda.borrarLocalidad(localidad);
		int idLocalidad = HelperDTODAO.obtenerIdLocalidad(localidad.getNombreLocalidad(), localidadesDTO);		
		localidadDAO.delete(idLocalidad);
		localidadesDTO = localidadDAO.readAll();
	}	
	//-----------------Fin Localidad----------------- //
	
	//-----------------Eventos de Tipo Contacto----------------- //
	public void agregarTipoContactoBD(TipoContacto tipoContacto) {
		agenda.agregarTipoContacto(tipoContacto);
		tipoContactoDAO.insert(HelperDTODAO.crearTipoContactoDTOConObjeto(tipoContacto));
		tiposContactoDTO = tipoContactoDAO.readAll(); 
	}
	
	public void editarTipoContactoBD(TipoContacto tipoContactoOriginal, String nuevoNombre) {
		TipoContacto tipoContactoNuevo = new TipoContacto(nuevoNombre);
		agenda.editarTipoContacto(tipoContactoOriginal, tipoContactoNuevo);
		
		TipoContactoDTO tipoContactoDTO = new TipoContactoDTO(nuevoNombre);
		int idTipoContacto = HelperDTODAO.obtenerIdTipoContacto(tipoContactoOriginal, tiposContactoDTO);
		tipoContactoDTO.setIdTipoContacto(idTipoContacto);
				
		tipoContactoDAO.edit(tipoContactoDTO);
		tiposContactoDTO = tipoContactoDAO.readAll(); 
	}
	
	public void borrarTipoContactoBD(TipoContacto tipoContacto) {
		agenda.borrarTipoContacto(tipoContacto);
		int idTipoContacto = HelperDTODAO.obtenerIdTipoContacto(tipoContacto, tiposContactoDTO);
		tipoContactoDAO.delete(idTipoContacto);
		tiposContactoDTO = tipoContactoDAO.readAll();
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