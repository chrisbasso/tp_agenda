package persistencia.dao.interfaz;

import dto.TipoContactoDTO;

import java.util.List;

public interface TipoContactoDAO {
	public boolean insert(TipoContactoDTO tipoContacto);
	
	public boolean delete(TipoContactoDTO tipoContacto_a_eliminar);
	
	public boolean edit(TipoContactoDTO tipoContacto_a_editar);
	
	public List<TipoContactoDTO> readAll();

}
