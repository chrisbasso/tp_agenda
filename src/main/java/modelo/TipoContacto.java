package modelo;

public class TipoContacto {
	private String prefijo;
	private String tipoContacto;

	public TipoContacto(String tipoContacto, String prefijo) {
		this.prefijo = prefijo;
		this.tipoContacto = tipoContacto;
	}
	
	public TipoContacto() {		
	}

	public String getPrefijo() {
		return prefijo;
	}

	public void setPrefijo(String prefijo) {
		this.prefijo = prefijo;
	}

	public String getTipoContacto() {
		return tipoContacto;
	}

	public void setTipoContacto(String tipoContacto) {
		this.tipoContacto = tipoContacto;
	}
	
	@Override
	public boolean equals(Object obj) {		
		if(this == obj) { 
            return true; 
		}
          
        if(obj == null || obj.getClass()!= this.getClass()) {
        	return false;
        }
        TipoContacto comparable = (TipoContacto)obj;
		
        return (this.prefijo == comparable.prefijo &&
        		this.tipoContacto == comparable.tipoContacto);
	}

}
