package persistencia.dao.interfaz;

import dto.DomicilioDTO;
import modelo.Localidad;

public interface DomicilioDAO {

	public boolean insert(DomicilioDTO domicilio);

	public boolean delete(int idDomicilio);

	public boolean editar(DomicilioDTO domicilio);

}
