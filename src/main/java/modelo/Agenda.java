package modelo;

import java.util.ArrayList;
import java.util.List;


public class Agenda{	
	private List <Persona> personas;
	private List <Localidad> localidades;
	private List <TipoContacto> tipoContactos;

	public Agenda()	{
		personas = new ArrayList<Persona>();
		localidades = new ArrayList<Localidad>();
		tipoContactos = new ArrayList<TipoContacto>();
	}

	public void agregarPersona(Persona persona) {		
		int posicion = existe(persona); 
		if(posicion == -1) {
			personas.add(persona);
		}
	}
	
	public boolean borrarPersona(Persona persona){
		int posicion = existe(persona); 
		if(posicion != -1) {
			personas.remove(posicion);
			return true;
		}
		return false;
	}
	
	public int existe(Persona persona){
		int indexOfPersona = 0;
		for(Persona per : personas) {
			if (per.equals(persona)) {
				return indexOfPersona;
			}
			indexOfPersona++;
		}
		return -1;
	}

	public void editarPersona(Persona personaOriginal, Persona personaModificada){
		if (borrarPersona(personaOriginal)) {
			agregarPersona(personaModificada);
		}		
	}

	public List<Persona> obtenerPersonas(){
		return personas;
	}
	
	public void agregarLocalidad(Localidad localidad) {		
		int posicion = existe(localidad); 
		if(posicion != -1) {
			localidades.add(localidad);
		}
	}
	
	public boolean borrarLocalidad(Localidad localidad){
		int posicion = existe(localidad); 
		if(posicion != -1) {
			localidades.remove(posicion);
			return true;
		}
		return false;
	}
	
	public int existe(Localidad localidad){
		int indexOfLocalidad = 0;
		for(Localidad loc : localidades) {
			if (loc.equals(localidad)) {
				return indexOfLocalidad;
			}
			indexOfLocalidad++;
		}
		return -1;
	}

	public void editarLocalidad(Localidad localidadOriginal, Localidad localidadModificada){
		if (borrarLocalidad(localidadOriginal)) {
			agregarLocalidad(localidadModificada);
		}		
	}

	public List<Localidad> obtenerLocalidades(){
		return localidades;
	}	
	
	public void agregarTipoContacto(TipoContacto tipoContacto) {		
		int posicion = existe(tipoContacto); 
		if(posicion != -1) {
			tipoContactos.add(tipoContacto);
		}
	}
	
	public boolean borrarTipoContacto(TipoContacto tipoContacto){
		int posicion = existe(tipoContacto); 
		if(posicion != -1) {
			tipoContactos.remove(posicion);
			return true;
		}
		return false;
	}
	
	public int existe(TipoContacto tipoContacto){
		int indexOfTipoContacto = 0;
		for(TipoContacto loc : tipoContactos) {
			if (loc.equals(tipoContacto)) {
				return indexOfTipoContacto;
			}
			indexOfTipoContacto++;
		}
		return -1;
	}

	public void editarTipoContacto(TipoContacto tipoContactoOriginal, TipoContacto tipoContactoModificada){
		if (borrarTipoContacto(tipoContactoOriginal)) {
			agregarTipoContacto(tipoContactoModificada);
		}		
	}

	public List<TipoContacto> obtenerTipoContactoes(){
		return tipoContactos;
	}
}