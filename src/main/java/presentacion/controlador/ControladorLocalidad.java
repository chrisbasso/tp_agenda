package presentacion.controlador;

import java.util.List;

import dto.LocalidadDTO;
import modelo.Agenda;
import modelo.Localidad;
import modelo.Persona;
import presentacion.vista.VentanaLocalidad;

public class ControladorLocalidad {
	private Controlador controladorSuperior;
	private VentanaLocalidad ventana;
	private List <Localidad> localidades;

	public ControladorLocalidad (Controlador controladorSuperior){
		this.controladorSuperior = controladorSuperior;
		this.ventana = VentanaLocalidad.getInstance();
		cargarActionListeners(); 
	}
	
	public void cargarTabla(List <Localidad> loc) {		
		this.localidades = loc; 
		for (Localidad localidad : localidades){
			Object[] fila = {
						localidad.getNombreLocalidad()
						};
			this.ventana.agregarLocalidadATabla(fila);
		}
	}
	
	public void mostrarVentana() {
		this.ventana.show();		
	}
	
	public void cerrarVentana() {
		ventana.hide();
	}
	
	private void cargarActionListeners() {		
		this.ventana.getBtnAgregarLocalidad().addActionListener(a->reportarEvento("agregar"));
		this.ventana.getBtnEditarLocalidad().addActionListener(a->reportarEvento("editar"));
		this.ventana.getBtnBorrar().addActionListener(a->reportarEvento("borrar"));
	}	
	
	private void reportarEvento(String evento) {
		Localidad loc = obtenerSeleccionado();
		switch (evento) {
		case "agregar":
			controladorSuperior.agregarLocalidad(loc);
			break;
		case "editar":
			controladorSuperior.editarLocalidad(loc,loc);
			break;
		case "borrar":
			controladorSuperior.borrarLocalidad(loc);
			break;
		default:
			break;
		}
	}
	
	private Localidad obtenerSeleccionado() {
		int indexOf = ventana.obtenerFilaSeleccionada();
		if(indexOf == -1) {
			return null;
		}else {
			return localidades.get(indexOf);
		}		
	}
	
	public static LocalidadDTO getLocalidadDTO(Localidad localidad) {		
		return new LocalidadDTO(localidad.getNombreLocalidad());		
	}

	public static Localidad getLocalidad(LocalidadDTO localidadDTO) {
		return new Localidad (localidadDTO.getNombreLocalidad());
	}
}