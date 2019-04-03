package persistencia.dao.interfaz;

import java.util.List;

import persistencia.dto.LocalidadDTO;

public interface LocalidadDAO {

	public boolean insert(LocalidadDTO localidad);

	public boolean delete(int idLocalidad);

	public boolean editar(LocalidadDTO localidad);

	public LocalidadDTO obtenerPorId(int id);
	
	public List<LocalidadDTO> readAll();
}
