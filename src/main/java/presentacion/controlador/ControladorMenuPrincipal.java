package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JMenuItem;

import modelo.Agenda;
import modelo.Localidad;
import modelo.Persona;
import modelo.TipoContacto;
import presentacion.vista.VentanaPrincipal;

public class ControladorMenuPrincipal implements ActionListener{
	private Controlador controladorSuperior;
	private VentanaPrincipal ventana;

	public ControladorMenuPrincipal(){
		this.ventana = VentanaPrincipal.getInstance();
		actionListeners();
		this.ventana.show();
	}
	
	private void actionListeners() {
		this.ventana.getMntmAgregar().addActionListener(a->reportarEvento("agregar"));
		this.ventana.getMntmEditar().addActionListener(a->reportarEvento("editar"));					
		this.ventana.getMntmBorrar().addActionListener(a->reportarEvento("borrar"));
		this.ventana.getMntmReporte().addActionListener(a->reportarEvento("reporte"));
		this.ventana.getMntmAbmLocalidad().addActionListener(a->reportarEvento("abmLocalidad"));
		this.ventana.getMntmAbmTipoDeContacto().addActionListener(a->reportarEvento("abmTipoContacto"));
	}

	private void reportarEvento(String evento) {
		switch (evento) {
		case "agregar":
			controladorSuperior.agregarContacto();
			break;
		case "editar":			
			controladorSuperior.editarContacto(obtenerSeleccionado());
			break;
		case "borrar":
			controladorSuperior.borrarContacto(obtenerSeleccionado());
			break;
		case "reporte":
			controladorSuperior.reporte();
			break;
		case "abmLocalidad":			
			controladorSuperior.abmLocalidad();
			break;
		case "abmTipoContacto":			
			controladorSuperior.abmTipoContacto();
			break;
		default:
			break;
		}
	}
	
	private int obtenerSeleccionado() {
		int rowIndex = this.ventana.getTablaPersonas().getSelectedRow();
		int colIndex = 11;
		int idPersona = (int) this.ventana.getTablaPersonas().getValueAt(rowIndex, colIndex);
		
		return idPersona;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {	
		
	}
	
	public void setControladorSuperior(Controlador controladorSuperior) {
		this.controladorSuperior = controladorSuperior;
	}
}