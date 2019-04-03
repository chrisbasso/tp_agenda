package presentacion.controlador;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JOptionPane;

import modelo.Localidad;
import persistencia.dto.LocalidadDTO;
import presentacion.vista.MensajesDeDialogo;
import presentacion.vista.VentanaLocalidad;

public class ControladorLocalidad {
	private Controlador controladorSuperior;
	private VentanaLocalidad ventana;
	private List <Localidad> localidades;

	public ControladorLocalidad (Controlador controladorSuperior){
		this.controladorSuperior = controladorSuperior;
		ventana = VentanaLocalidad.getInstance();
		cargarActionListeners(); 
	}
	
	public void cargarTabla(List <Localidad> localidades) {		
		this.localidades = localidades; 
		for (Localidad localidad : localidades){
			Object[] fila = {
						localidad.getNombreLocalidad()
						};
			ventana.agregarLocalidadATabla(fila);
		}
	}
	
	public void mostrarVentana() {
		ventana.mostrar();		
	}
	
	public void cerrarVentana() {
		ventana.cerrar();
	}
	
	private void cargarActionListeners() {		
		ventana.getBtnAgregarLocalidad().addActionListener(a->reportarEvento("agregar"));
		ventana.getBtnEditarLocalidad().addActionListener(a->reportarEvento("editar"));
		ventana.getBtnBorrar().addActionListener(a->reportarEvento("borrar"));
		ventana.addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e) {
				int confirm = JOptionPane.showOptionDialog(
						null, "Estas seguro que quieres salir de la Agenda!?",
						"Confirmacion", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, null, null);
				if (confirm == 0) {
					reportarEvento("cerrar");
				}
			}
		});
	}	
	
	private void reportarEvento(String evento) {
		Localidad locSeleccionada = obtenerSeleccionado();
		switch (evento) {
		case "agregar":
			if (ventana.getTxtLocalidad() != "") {
				controladorSuperior.agregarLocalidadBD(ventana.getTxtLocalidad());
				Object[] fila = {
						ventana.getTxtLocalidad()
						};
				ventana.agregarLocalidadATabla(fila);
			}else {
				MensajesDeDialogo.getInstance().msgSinCambios();
			}			
			break;
		case "editar":
			if (locSeleccionada != null) {
				controladorSuperior.editarLocalidadBD(locSeleccionada, ventana.getTxtLocalidad());
				int filaSeleccionada = ventana.obtenerFilaSeleccionada();
				Object[] fila = {
						ventana.getTxtLocalidad()
						};
				ventana.editarLocalidadATabla(fila, filaSeleccionada);
			}else {
				MensajesDeDialogo.getInstance().msgSinCambios();
			}
			break;
		case "borrar":
			if (locSeleccionada != null) {
				controladorSuperior.borrarLocalidadBD(locSeleccionada);
			}else {
				MensajesDeDialogo.getInstance().msgSinCambios();
			}	
			break;
		case "cerrar":
			controladorSuperior.cerrarAplicacion();
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
	
}