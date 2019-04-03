package persistencia.dao.interfaz;

import java.util.List;

import persistencia.dto.DomicilioDTO;

public interface DomicilioDAO {

	public boolean insert(DomicilioDTO domicilio);

	public boolean delete(int idDomicilio);

	public boolean editar(DomicilioDTO domicilio);
	
	public int idDomicilio(DomicilioDTO domicilio);
	
	public DomicilioDTO obtenerPorId(int id);
	
	public List <DomicilioDTO> readAll();

}
