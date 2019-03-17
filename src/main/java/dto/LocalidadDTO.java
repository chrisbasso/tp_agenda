package dto;

public class LocalidadDTO {
	private int idLocalidad;
	private String nombreLocalidad;


	public LocalidadDTO(int idLocalidad, String nombreLocalidad) {
		this.idLocalidad = idLocalidad;
		this.nombreLocalidad = nombreLocalidad;
	}

	public int getIdLocalidad() {
		return idLocalidad;
	}
	public void setIdLocalidad(int idLocalidad) {
		this.idLocalidad = idLocalidad;
	}
	public String getNombreLocalidad() {
		return nombreLocalidad;
	}
	public void setNombreLocalidad(String nombreLocalidad) {
		this.nombreLocalidad = nombreLocalidad;
	}

	@Override public String toString()
	{
		return nombreLocalidad;
	}
}
