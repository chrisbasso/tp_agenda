package persistencia.dao.interfaz;


public interface DAOAbstractFactory
{
	public PersonaDAO listarContactos();

	public PersonaDAO createPersonaDAO();

	public TipoContactoDAO createTipoContactoDAO();
}