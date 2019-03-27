package modelo;

public class Domicilio {
	private String calle;
	private String altura;
	private String piso;
	private String depto;
	private Localidad localidad;
	
	public Domicilio(String calle, String altura, String piso, String depto, Localidad localidad) {
		this.calle = calle;
		this.altura = altura;
		this.piso = piso;
		this.depto = depto;
		this.localidad = localidad;
	}
	
	public Domicilio() {		
	}
	
	public String getCalle() {
		return calle;
	}
	
	public void setCalle(String calle) {
		this.calle = calle;
	}
	
	public String getAltura() {
		return altura;
	}
	
	public void setAltura(String altura) {
		this.altura = altura;
	}
	
	public String getPiso() {
		return piso;
	}
	
	public void setPiso(String piso) {
		this.piso = piso;
	}
	
	public String getDepto() {
		return depto;
	}
	
	public void setDepto(String depto) {
		this.depto = depto;
	}
	
	public Localidad getLocalidad() {
		return localidad;
	}

	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}
	
	@Override
	public boolean equals(Object obj) {		
		if(this == obj) { 
            return true; 
		}
          
        if(obj == null || obj.getClass()!= this.getClass()) {
        	return false;
        }
        Domicilio comparable = (Domicilio)obj;
		
        return (this.calle == comparable.calle &&
        		this.altura == comparable.altura &&
        		this.piso == comparable.piso &&
        		this.depto == comparable.depto &&
        		this.localidad == comparable.localidad);
	}
}
