package persistencia.dao.interfaz;

import java.util.List;

import dto.ContactoDTO;

public interface ContactoDAO
{
	public List<ContactoDTO> readAll();
}
