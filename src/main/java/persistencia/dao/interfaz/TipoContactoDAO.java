package persistencia.dao.interfaz;

import java.util.List;

import persistencia.dto.TipoContactoDTO;

public interface TipoContactoDAO {
	public boolean insert(TipoContactoDTO tipoContacto);
	
	public boolean delete(int idTipoContacto);
	
	public boolean edit(TipoContactoDTO tipoContacto_a_editar);
	
	public List<TipoContactoDTO> readAll();
	
	public TipoContactoDTO obtenerPorId(int id);

}
