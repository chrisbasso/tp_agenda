package modelo;

public class Localidad {
	private String nombreLocalidad;

	public Localidad(String nombre) {
		this.nombreLocalidad = nombre;
	}
	
	public Localidad() {		
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
		
        return (this.nombreLocalidad == comparable.nombreLocalidad);
	}
}
