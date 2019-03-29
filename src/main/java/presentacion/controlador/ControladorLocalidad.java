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
	
	public void cargarTabla(Agenda agenda) {
		this.ventana.inicializarTabla();		
		this.localidades = agenda.obtenerLocalidades(); 
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
	
	public void ocultarVentana() {
		ventana.hide();
	}
	
	private void cargarActionListeners() {		
		this.ventana.getBtnAgregarLocalidad().addActionListener(a->reportarEvento("agregar"));
		this.ventana.getBtnEditarLocalidad().addActionListener(a->reportarEvento("editar"));
		this.ventana.getBtnBorrar().addActionListener(a->reportarEvento("borrar"));
	}	
	
	private void reportarEvento(String evento) {
		switch (evento) {
		case "agregar":
			// TODO
			/*
			 * acciones al agregar una localidad, pasarle el nombre 
			 *  de localidad al controlador principal 
			 */
			break;
		case "editar":
			// TODO
			/*
			 *  acciones al editar una localidad, pasarle el nombre 
			 *  de localidad al controlador principal, con el cambio 
			 */			
			break;
		case "borrar":
			// TODO
			/*
			 *  acciones al editar una localidad, pasarle el nombre 
			 *  de localidad al controlador principal, con el cambio 
			 */					
			break;
		default:
			break;
		}
	}

	public static LocalidadDTO getLocalidadDTO(Localidad localidad) {
		// TODO Auto-generated method stub
		return null;
	}

	public static Localidad getLocalidad(LocalidadDTO localidadDTO) {
		// TODO Auto-generated method stub
		return null;
	}
}