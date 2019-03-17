package modelo;

public class TipoContacto {
	private int    idTipoContacto;
	private String tipo_contacto;
	
	public TipoContacto() {
		
	}
	
	public TipoContacto(int idTipoContacto, String tipoContacto) {
		this.idTipoContacto = idTipoContacto;
		this.tipo_contacto 	= tipoContacto; 
	}

	public int getIdTipoContacto() {
		return idTipoContacto;
	}

	public void setIdTipoContacto(int idTipoContacto) {
		this.idTipoContacto = idTipoContacto;
	}

	public String getTipo_contacto() {
		return tipo_contacto;
	}

	public void setTipo_contacto(String tipo_contacto) {
		this.tipo_contacto = tipo_contacto;
	}

	
}
