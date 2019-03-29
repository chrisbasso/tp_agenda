package persistencia.dao.interfaz;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import dto.LocalidadDTO;

import java.util.List;

public interface DomicilioDAO {

	public boolean insert(String localidad);

	public boolean delete(int idLocalidad) throws MySQLIntegrityConstraintViolationException;

	public boolean editar(LocalidadDTO localidad);

	public List<LocalidadDTO> readAll();
}
