/**
 * 
 */
package persistencia.dao.mysql;

import persistencia.dao.interfaz.DAOAbstractFactory;
import persistencia.dao.interfaz.ContactoDAO;

import persistencia.dao.interfaz.TipoContactoDAO;

public class DAOSQLFactory implements DAOAbstractFactory
{
	/* (non-Javadoc)
	 * @see persistencia.dao.interfaz.DAOAbstractFactory#createPersonaDAO()
	 */
	public ContactoDAO createPersonaDAO()
	{
		return new ContactoDAOSQL();
	}

	@Override
	public TipoContactoDAO createTipoContactoDAO() {
		return new TipoContactoDAOSQL();
	}

}