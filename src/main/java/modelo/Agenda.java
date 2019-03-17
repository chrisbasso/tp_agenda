package modelo;

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

import java.util.List;


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

	public void eliminarLocalidad(LocalidadDTO localidadDTO) {
		localidades.delete(localidadDTO.getIdLocalidad());
	}

	public void eliminarTContacto (TipoContactoDTO tipoContactoDTO) {
		tipos_contactos.delete(tipoContactoDTO);
	}

	public boolean editarLocalidad(LocalidadDTO localidadDTO) {
		return localidades.editar(localidadDTO);

	}
	public boolean editarTContacto(TipoContactoDTO tipoContactoDTO) {
		return tipos_contactos.edit(tipoContactoDTO);
	}

	public boolean editarPersona(PersonaDTO nuevaPersona) {
		return persona.editar(nuevaPersona);

	}
}