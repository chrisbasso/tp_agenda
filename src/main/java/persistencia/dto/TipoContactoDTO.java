package persistencia.dto;

public class TipoContactoDTO {
	private int idTipoContacto;
	private String tipoContacto;

	public TipoContactoDTO(int idTipoContacto, String tipoContacto) {
		this.idTipoContacto = idTipoContacto;
		this.tipoContacto = tipoContacto;
	}

	public TipoContactoDTO(String tipoContacto) {
		this.tipoContacto = tipoContacto;
	}
	
	public int getIdTipoContacto() {
		return idTipoContacto;
	}

	public void setIdTipoContacto(int idTipoContacto) {
		this.idTipoContacto = idTipoContacto;
	}

	public String getTipoContacto() {
		return tipoContacto;
	}

	public void setTipoContacto(String tipoContacto) {
		this.tipoContacto = tipoContacto;
	}

}
