package persistencia.dao.interfaz;

public interface DAOAbstractFactory
{
	public PersonaDAO createPersonaDAO();

	public TipoContactoDAO createTipoContactoDAO();

	public LocalidadDAO createLocalidadDAO();
	
	public DomicilioDAO createDomicilioDAO();
}