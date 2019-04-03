package presentacion.controlador;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JOptionPane;

import modelo.Localidad;
import modelo.TipoContacto;
import persistencia.dto.TipoContactoDTO;
import presentacion.vista.MensajesDeDialogo;
import presentacion.vista.VentanaTipoContacto;

public class ControladorTipoContacto {
	private Controlador controladorSuperior;
	private VentanaTipoContacto ventana;
	private List <TipoContacto> tipoContactos;
	
	public ControladorTipoContacto(Controlador controladorSuperior){
		this.controladorSuperior = controladorSuperior;
		this.ventana = VentanaTipoContacto.getInstance();	
	}

	public void cargarTabla(List <TipoContacto> tc) {
		this.tipoContactos = tc; 
		for (TipoContacto tipoContacto : tipoContactos){
			Object[] fila = {
					tipoContacto.getTipoContacto()
						};
			ventana.agregarTipoContactoATabla(fila);
		}
	}

	public void mostrarVentana() {
		ventana.mostrar();		
	}
	
	public void cerrarVentana() {
		ventana.cerrar();
	}
	
	private void cargarActionListeners() {		
		ventana.getBtnAgregarTipoContacto().addActionListener(a->reportarEvento("agregar"));
		ventana.getBtnEditarTipoContacto().addActionListener(a->reportarEvento("editar"));
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
		TipoContacto tipoContactoSeleccionado = obtenerSeleccionado();
		switch (evento) {
		case "agregar":
			if (ventana.getTxtTipoContacto() != "") {
				controladorSuperior.agregarLocalidadBD(ventana.getTxtTipoContacto());
				Object[] fila = {
						ventana.getTxtTipoContacto()
						};
				ventana.agregarTipoContactoATabla(fila);
			}else {
				MensajesDeDialogo.getInstance().msgSinCambios();
			}			
			break;
		case "editar":
			if (tipoContactoSeleccionado != null) {
				controladorSuperior.editarTipoContactoBD(tipoContactoSeleccionado, ventana.getTxtTipoContacto());
				int filaSeleccionada = ventana.obtenerFilaSeleccionada();
				Object[] fila = {
						ventana.getTxtTipoContacto()
						};
				ventana.editarTipoContactoATabla(fila, filaSeleccionada);
			}else {
				MensajesDeDialogo.getInstance().msgSinCambios();
			}
			break;
		case "borrar":
			if (tipoContactoSeleccionado != null) {
				controladorSuperior.borrarTipoContactoBD(tipoContactoSeleccionado);
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
	
	private TipoContacto obtenerSeleccionado() {
		int indexOf = ventana.obtenerFilaSeleccionada();
		if(indexOf == -1) {
			return null;
		}else {
			return tipoContactos.get(indexOf);
		}		
	}
}