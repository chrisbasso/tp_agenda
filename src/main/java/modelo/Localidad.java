package modelo;

public class Localidad {
	private String localidad;

	public Localidad(String localidad) {
		this.localidad = localidad;
	}
	
	public Localidad() {		
	}

	public String getTipoContacto() {
		return localidad;
	}

	public void setTipoContacto(String localidad) {
		this.localidad = localidad;
	}
}
