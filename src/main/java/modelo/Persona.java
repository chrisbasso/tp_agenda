package modelo;

public class Persona {

	private int idPersona;
	private String nombre;
	private String telefono;

	public Persona(int idPersona, String nombre, String telefono) {
		this.idPersona = idPersona;
		this.nombre = nombre;
		this.telefono = telefono;
	}

	public int getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
}
