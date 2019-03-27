package dto;

import java.util.Date;

public class PersonaDTO {
	private int idPersona;
	private String nombre;
	private String telefono;
	private DomicilioDTO domicilio;
	private TipoContactoDTO tipoContacto;
	private String email;
	private Date fechaNacimiento;

	public PersonaDTO(String nombre, String telefono, DomicilioDTO domicilio, TipoContactoDTO tipoContacto, String email,Date fechaNacimiento)
	{
		this.nombre = nombre;
		this.telefono = telefono;
		this.domicilio = domicilio;
		this.tipoContacto = tipoContacto;
		this.email = email;
		this.fechaNacimiento = fechaNacimiento;
	}

	public PersonaDTO(int idPersona, String nombre, String telefono, DomicilioDTO domicilio, TipoContactoDTO tipoContacto,  String email,Date fechaNacimiento) {
		this.idPersona = idPersona;
		this.nombre = nombre;
		this.telefono = telefono;
		this.domicilio = domicilio;
		this.tipoContacto = tipoContacto;
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
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public TipoContactoDTO getTipoPersona() {
		return tipoContacto;
	}

	public DomicilioDTO getDomicilio() {
		return this.domicilio;
	}

	public void setDomicilio(DomicilioDTO domicilio) {
		this.domicilio = domicilio;
	}

	public void setTipoContacto(TipoContactoDTO tipoContacto) {
		this.tipoContacto = tipoContacto;
	}

	public TipoContactoDTO getTipo_Persona() {
		return tipoContacto;
	}

	public TipoContactoDTO getTipoContacto() {
		return tipoContacto;
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