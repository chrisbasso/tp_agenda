package persistencia.dto;

import java.util.Date;

public class PersonaDTO {
	private int idPersona;
	private String nombre;
	private String apellido;
	private String telefono;
	private int idDomicilio;
	private int idTipoContacto;
	private String email;
	private Date fechaNacimiento;

	public PersonaDTO(String nombre, String apellido, String telefono, int idDomicilio, int idTipoContacto, String email,Date fechaNacimiento)
	{
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
		this.idDomicilio = idDomicilio;
		this.idTipoContacto = idTipoContacto;
		this.email = email;
		this.fechaNacimiento = fechaNacimiento;
	}

	public PersonaDTO(int idPersona, String nombre, String apellido, String telefono, int idDomicilio, int idTipoContacto, String email,Date fechaNacimiento) {
		this.idPersona = idPersona;
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
		this.idDomicilio = idDomicilio;
		this.idTipoContacto = idTipoContacto;
		this.email = email;
		this.fechaNacimiento = fechaNacimiento;
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
	
	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public int getIdTipoContacto() {
		return idTipoContacto;
	}

	public int getIdDomicilio() {
		return idDomicilio;
	}

	public void setIdDomicilio(int domicilio) {
		idDomicilio = domicilio;
	}

	public void setIdTipoContacto(int tipoContacto) {
		idTipoContacto = tipoContacto;
	}

	public int getTipoContacto() {
		return idTipoContacto;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
}