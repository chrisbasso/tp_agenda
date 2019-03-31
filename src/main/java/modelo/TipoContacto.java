package modelo;

public class TipoContacto {
	private String tipoContacto;

	public TipoContacto(String tipoContacto) {
		this.tipoContacto = tipoContacto;
	}
	
	public TipoContacto() {		
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
		
        return (this.tipoContacto == comparable.tipoContacto);
	}

}
