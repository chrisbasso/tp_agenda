package modelo;

import java.util.List;

import dto.LocalidadDTO;
import dto.PersonaDTO;
import dto.TipoContactoDTO;
import persistencia.dao.interfaz.LocalidadDAO;
import persistencia.dao.interfaz.PersonaDAO;
import persistencia.dao.interfaz.TipoContactoDAO;
import persistencia.dao.mysql.DAOSQLFactory;
import persistencia.dao.mysql.LocalidadDAOSQL;
import persistencia.dao.mysql.PersonaDAOSQL;
import persistencia.dao.mysql.TipoContactoDAOSQL;


public class Agenda
{
	private PersonaDAO persona;
	private LocalidadDAO localidades;
	private TipoContactoDAO tipos_contactos;

	public Agenda(DAOSQLFactory daosqlFactory)
	{
		persona = new PersonaDAOSQL();
		localidades = new LocalidadDAOSQL();
		tipos_contactos = new TipoContactoDAOSQL();

	}

	public void agregarPersona(PersonaDTO nuevaPersona)
	{
		persona.insert(nuevaPersona);
	}

	public void borrarPersona(PersonaDTO persona_a_eliminar)
	{
		persona.delete(persona_a_eliminar);
	}

	public List<PersonaDTO> obtenerPersonas()
	{
		return persona.readAll();
	}


	public List<LocalidadDTO> obtenerLocalidades()
	{
		return localidades.readAll();
	}

	public List<TipoContactoDTO> obtenerTiposContactos()
	{
		return tipos_contactos.readAll();
	}

	public void agregarTContacto(TipoContactoDTO tipo) {
		tipos_contactos.insert(tipo);
	}

	public void eliminarTContacto (TipoContactoDTO tipoContactoDTO) {
		tipos_contactos.delete(tipoContactoDTO);
	}

	public boolean editarTContacto(TipoContactoDTO tipoContactoDTO) {
		return tipos_contactos.edit(tipoContactoDTO);
	}

	public boolean editarPersona(PersonaDTO nuevaPersona) {
		return persona.editar(nuevaPersona);
	}
	
	public boolean agregarLocalidad(String nombreLocalidad) {
		return localidades.insert(nombreLocalidad);
	}
	
	public boolean editarLocalidad(LocalidadDTO localidadDTO) {
		return localidades.editar(localidadDTO);
	}
	
	public void eliminarLocalidad(LocalidadDTO localidadDTO) {
		localidades.delete(localidadDTO.getIdLocalidad());
	}
}