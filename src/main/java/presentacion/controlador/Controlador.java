package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Optional;

import modelo.Agenda;
import presentacion.reportes.ReporteAgenda;
import presentacion.vista.VentanaPersona;
import presentacion.vista.VentanaTipoContacto;
import presentacion.vista.Vista;
import dto.PersonaDTO;
import dto.TipoContactoDTO;

public class Controlador implements ActionListener
{
	private Vista vista;
	private List<PersonaDTO> personas_en_tabla;
	private List<TipoContactoDTO> tipoContactos_en_tabla;
	private VentanaPersona ventanaPersona;
	private VentanaTipoContacto ventanaTipoContacto;
	private Agenda agenda;

	public Controlador(Vista vista, Agenda agenda)
	{
		this.vista = vista;
		this.vista.getBtnAgregar().addActionListener(a->ventanaAgregarPersona(a));
		this.vista.getBtnBorrar().addActionListener(s->borrarPersona(s));
		this.vista.getBtnReporte().addActionListener(r->mostrarReporte(r));
		this.vista.getBtnAbmTipoDeContacto().addActionListener(t->ventanaABMTipoContacto(t));
		this.ventanaPersona = VentanaPersona.getInstance();
		this.ventanaTipoContacto = VentanaTipoContacto.getInstance();
		this.ventanaPersona.getBtnAgregarPersona().addActionListener(p->guardarPersona(p));
		this.ventanaTipoContacto.getBtnAgregarTipoContacto().addActionListener(q->guardarTipoContacto(q));;
		this.agenda = agenda;
		this.personas_en_tabla = null;
		this.tipoContactos_en_tabla = null;
	}

	private void ventanaAgregarPersona(ActionEvent a) {
		this.ventanaPersona.mostrarVentana();

	}

	private void guardarPersona(ActionEvent p) {
		PersonaDTO nuevaPersona = new PersonaDTO(0,
				ventanaPersona.getTxtNombre().getText(),
				ventanaPersona.getTxtTelefono().getText(),
				ventanaPersona.getTxtCalle().getText(),
				ventanaPersona.getTextAltura().getText(),
				ventanaPersona.getTextPiso().getText(),
				ventanaPersona.getTextDepto().getText(),
				Optional.ofNullable((String) ventanaPersona.getComboLocalidad().getSelectedItem()).orElse(""),
				Optional.ofNullable((String) ventanaPersona.getComboTipoContacto().getSelectedItem()).orElse(""));
		this.agenda.agregarPersona(nuevaPersona);
		this.llenarTabla();
		this.ventanaPersona.cerrar();
	}

	private void mostrarReporte(ActionEvent r) {
		ReporteAgenda reporte = new ReporteAgenda(agenda.obtenerPersonas());
		reporte.mostrar();
	}

	public void borrarPersona(ActionEvent s)
	{
		int[] filas_seleccionadas = this.vista.getTablaPersonas().getSelectedRows();
		for (int fila:filas_seleccionadas)
		{
			this.agenda.borrarPersona(this.personas_en_tabla.get(fila));
		}

		this.llenarTabla();
	}

	private void ventanaABMTipoContacto(ActionEvent t) {
		this.ventanaTipoContacto.mostrarVentana();
	}

	private void guardarTipoContacto(ActionEvent q) {
		TipoContactoDTO nuevoTipoContacto =  new TipoContactoDTO(0, ventanaTipoContacto.getTxtAgregarTipoContacto().getText());
		this.agenda.agregarTipoContacto(nuevoTipoContacto);
		this.llenarTablaTipoContacto();
		//mensaje que diga TipoContacto guardado
	}

	public void inicializar()
	{
		this.llenarTabla();
		this.vista.show();
	}

	private void llenarTabla()
	{
		this.vista.getModelPersonas().setRowCount(0); //Para vaciar la tabla
		this.vista.getModelPersonas().setColumnCount(0);
		this.vista.getModelPersonas().setColumnIdentifiers(this.vista.getNombreColumnas());

		this.personas_en_tabla = agenda.obtenerPersonas();
		for (int i = 0; i < this.personas_en_tabla.size(); i ++)
		{
			Object[] fila = {this.personas_en_tabla.get(i).getNombre(),
					this.personas_en_tabla.get(i).getTelefono(),
					this.personas_en_tabla.get(i).getCalle(),
					this.personas_en_tabla.get(i).getAltura(),
					this.personas_en_tabla.get(i).getPiso(),
					this.personas_en_tabla.get(i).getDepto(),
					this.personas_en_tabla.get(i).getLocalidad(),
					this.personas_en_tabla.get(i).getTipoContacto()};
			this.vista.getModelPersonas().addRow(fila);
		}
	}

	private void llenarTablaTipoContacto() {
		this.ventanaTipoContacto.getModelTipoContactos().setRowCount(0);
		this.ventanaTipoContacto.getModelTipoContactos().setColumnCount(0);
		this.ventanaTipoContacto.getModelTipoContactos().setColumnIdentifiers(this.ventanaTipoContacto.getNombreColumnas());

		this.tipoContactos_en_tabla = agenda.obtenerTipoContactos();//hacer readall en daosql
		for(int i = 0; i < this.tipoContactos_en_tabla.size(); i++) {
			Object[] fila = { this.tipoContactos_en_tabla.get(i).getTipo_contacto()};

			this.ventanaTipoContacto.getModelTipoContactos().addRow(fila);
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}

}