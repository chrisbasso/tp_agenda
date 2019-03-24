package modelo;

import java.util.Date;


public class Persona {
	private int idPersona;
	private String nombre;
	private String telefono;
	private Domicilio domicilio;
	private TipoContacto tipoContacto;
	private String email;
	private Date fechaNacimiento;
	
	public Persona(int idPersona, String nombre, String telefono, Domicilio domicilio, TipoContacto tipoContacto,
			String email, Date fechaNacimiento) {
		this.idPersona = idPersona;
		this.nombre = nombre;
		this.telefono = telefono;
		this.domicilio = domicilio;
		this.tipoContacto = tipoContacto;
		this.email = email;
		this.fechaNacimiento = fechaNacimiento;
	}
	
	public Persona() {
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
	public Domicilio getDomicilio() {
		return domicilio;
	}
	public void setDomicilio(Domicilio domicilio) {
		this.domicilio = domicilio;
	}
	public TipoContacto getTipoContacto() {
		return tipoContacto;
	}
	public void setTipoContacto(TipoContacto tipoContacto) {
		this.tipoContacto = tipoContacto;
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
