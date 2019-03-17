package modelo;

import java.util.List;
import dto.ContactoDTO;
import dto.TipoContactoDTO;
import persistencia.dao.interfaz.DAOAbstractFactory;
import persistencia.dao.interfaz.ContactoDAO;
import persistencia.dao.interfaz.TipoContactoDAO;


public class Agenda
{
	private ContactoDAO persona;
	private TipoContactoDAO tipoContacto;

	public Agenda(DAOAbstractFactory metodo_persistencia)
	{
		this.persona = metodo_persistencia.createPersonaDAO();
		this.tipoContacto = metodo_persistencia.createTipoContactoDAO();
	}

	public void agregarPersona(ContactoDTO nuevaPersona)
	{
		this.persona.insert(nuevaPersona);
	}

	public void borrarPersona(ContactoDTO persona_a_eliminar)
	{
		this.persona.delete(persona_a_eliminar);
	}

	public List<ContactoDTO> obtenerPersonas()
	{
		return this.persona.readAll();
	}

	public void agregarTipoContacto(TipoContactoDTO nuevotipoContacto) {
		this.tipoContacto.insert(nuevotipoContacto);
	}

	public void borrarTipoContacto(TipoContactoDTO tipoContacto_a_eliminar) {
		this.tipoContacto.delete(tipoContacto_a_eliminar);
	}

	public void editarTipoContacto(TipoContactoDTO tipoContacto_a_editar) {
		this.tipoContacto.edit(tipoContacto_a_editar);
	}

	public List<TipoContactoDTO> obtenerTipoContactos(){
		return this.tipoContacto.readAll();
	}
}