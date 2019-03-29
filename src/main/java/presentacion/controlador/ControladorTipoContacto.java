package presentacion.controlador;

import java.util.List;

import dto.TipoContactoDTO;
import modelo.Agenda;
import modelo.Localidad;
import modelo.TipoContacto;
import presentacion.vista.VentanaTipoContacto;

public class ControladorTipoContacto {
	private Controlador controladorSuperior;
	private VentanaTipoContacto ventana;
	private List <TipoContacto> tipoContactos;
	
	public ControladorTipoContacto(Controlador controladorSuperior){
		this.controladorSuperior = controladorSuperior;
		this.ventana = VentanaTipoContacto.getInstance();	
	}

	public void cargarTabla(Agenda agenda) {
		// TODO
	}

	public void mostrarVentana() {
		// TODO		
	}
	
	private void cargarActionListeners() {		
		// TODO
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

	public static TipoContactoDTO getTipoContactoDTO(TipoContacto tipoContacto) {
		// TODO Auto-generated method stub
		return null;
	}

	public static TipoContacto getTipoContacto(TipoContactoDTO tipoContacto) {
		// TODO Auto-generated method stub
		return null;
	}
}