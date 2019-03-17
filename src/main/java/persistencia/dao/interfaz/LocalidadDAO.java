package persistencia.dao.interfaz;

import dto.LocalidadDTO;

import java.util.List;

public interface LocalidadDAO {

	public boolean insert(String localidad);

	public boolean delete(int idLocalidad);

	public boolean editar(LocalidadDTO localidad);

	public List<LocalidadDTO> readAll();
}
