package modelo;

public class Localidad {
	private String prefijoLocalidad;
	private String nombreLocalidad;

	public Localidad(String localidad, String prefijo) {
		this.prefijoLocalidad = prefijo;
		this.nombreLocalidad = localidad;
	}
	
	public Localidad() {		
	}

	public String getPrefijo() {
		return prefijoLocalidad;
	}

	public void setPrefijo(String prefijo) {
		this.prefijoLocalidad = prefijo;
	}

	public String getNombreLocalidad() {
		return nombreLocalidad;
	}

	public void setNombreLocalidad(String localidad) {
		this.nombreLocalidad = localidad;
	}
	@Override
	public boolean equals(Object obj) {		
		if(this == obj) { 
            return true; 
		}
          
        if(obj == null || obj.getClass()!= this.getClass()) {
        	return false;
        }
        Localidad comparable = (Localidad)obj;
		
        return (this.prefijoLocalidad == comparable.prefijoLocalidad &&
        		this.nombreLocalidad == comparable.nombreLocalidad);
	}
}
