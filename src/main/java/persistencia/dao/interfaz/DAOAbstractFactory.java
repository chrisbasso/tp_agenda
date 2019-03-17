package persistencia.dao.interfaz;


public interface DAOAbstractFactory
{
	public ContactoDAO createPersonaDAO();

	public TipoContactoDAO createTipoContactoDAO();
}