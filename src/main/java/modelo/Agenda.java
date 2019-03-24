package modelo;

import java.sql.SQLException;
import java.util.List;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import dto.LocalidadDTO;
import dto.PersonaDTO;
import dto.TipoContactoDTO;
import persistencia.dao.interfaz.LocalidadDAO;
import persistencia.dao.interfaz.PersonaDAO;
import persistencia.dao.interfaz.TipoContactoDAO;
import persistencia.dao.mysql.LocalidadDAOSQL;
import persistencia.dao.mysql.PersonaDAOSQL;
import persistencia.dao.mysql.TipoContactoDAOSQL;


public class Agenda{	
	private PersonaDAO personas;
	private LocalidadDAO localidades;
	private TipoContactoDAO tiposDeContactos;

	public Agenda()	{
		personas = new PersonaDAOSQL();
		localidades = new LocalidadDAOSQL();
		tiposDeContactos = new TipoContactoDAOSQL();
	}

	public void agregarPersona(PersonaDTO nuevaPersona) throws SQLException {
		personas.insert(nuevaPersona);
	}

	public void borrarPersona(PersonaDTO persona_a_eliminar){
		personas.delete(persona_a_eliminar);
	}

	public boolean editarPersona(PersonaDTO nuevaPersona) throws SQLException {
		return personas.editar(nuevaPersona);
	}

	public List<PersonaDTO> obtenerPersonas(){
		return personas.readAll();
	}
	
	public boolean agregarLocalidad(LocalidadDTO localidadDTO) {
		return localidades.insert(localidadDTO.getNombreLocalidad());
	}

	public void borrarLocalidad(LocalidadDTO localidadDTO) throws MySQLIntegrityConstraintViolationException{
		localidades.delete(localidadDTO.getIdLocalidad());
	}
	
	public boolean editarLocalidad(LocalidadDTO localidadDTO) {
		return localidades.editar(localidadDTO);
	}
	
	public List<LocalidadDTO> obtenerLocalidades(){
		return localidades.readAll();
	}

	public void agregarTipoDeContacto(TipoContactoDTO tipo) {
		tiposDeContactos.insert(tipo);
	}

	public void borrarTipoDeContacto (TipoContactoDTO tipoContactoDTO) throws MySQLIntegrityConstraintViolationException {
		tiposDeContactos.delete(tipoContactoDTO);
	}

	public boolean editarTContacto(TipoContactoDTO tipoContactoDTO) {
		return tiposDeContactos.edit(tipoContactoDTO);
	}

	public List<TipoContactoDTO> obtenerTiposContactos()	{
		return tiposDeContactos.readAll();
	}
}