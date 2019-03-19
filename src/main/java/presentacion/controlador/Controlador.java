package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Optional;

import dto.DomicilioDTO;
import dto.LocalidadDTO;
import dto.PersonaDTO;
import dto.TipoContactoDTO;
import modelo.Agenda;
import presentacion.reportes.ReporteAgenda;
import presentacion.vista.MensajesDeDialogo;
import presentacion.vista.VentanaLocalidad;
import presentacion.vista.VentanaPersona;
import presentacion.vista.VentanaTipoContacto;
import presentacion.vista.Vista;

public class Controlador implements ActionListener
{
	private Vista vista;
	private List<PersonaDTO> personas_en_tabla;
	private List<TipoContactoDTO> tipoContactos;
	private List<LocalidadDTO> localidades;
	private VentanaPersona ventanaPersona;
	private VentanaTipoContacto ventanaTipoContacto;
	private VentanaLocalidad ventanaLocalidad;
	private Agenda agenda;
	private MensajesDeDialogo mensaje;

	public Controlador(Vista vista, Agenda agenda)
	{
		this.vista = vista;
		this.vista.getBtnAgregar().addActionListener(a->ventanaAgregarPersona(a));
		this.vista.getBtnBorrar().addActionListener(s->borrarPersona(s));
		this.vista.getBtnReporte().addActionListener(r->mostrarReporte(r));
		this.vista.getBtnAbmTipoDeContacto().addActionListener(t->ventanaABMTipoContacto(t));
		this.vista.getBtnAbmLocalidad().addActionListener(t->ventanaABMLocalidad(t));
		
		this.ventanaPersona = VentanaPersona.getInstance();
		this.ventanaTipoContacto = VentanaTipoContacto.getInstance();
		this.ventanaLocalidad = VentanaLocalidad.getInstance();
		this.mensaje = MensajesDeDialogo.getInstance();
		
		this.ventanaPersona.getBtnAgregarPersona().addActionListener(p-> guardarContacto(p));
		this.ventanaTipoContacto.getBtnAgregarTipoContacto().addActionListener(q->guardarTipoContacto(q));
		this.ventanaLocalidad.getBtnAgregarLocalidad().addActionListener(z->guardarLocalidad(z));
		this.ventanaTipoContacto.getBtnBorrar().addActionListener(w-> borrarTipoContacto(w));
		this.ventanaTipoContacto.getBtnEditarTipoContacto().addActionListener(x-> editarTipoContacto(x));
		this.ventanaTipoContacto.getTablaTipoContactos().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				editarFilaSeleccionada();
			}
		});
		
		this.agenda = agenda;
		this.personas_en_tabla = null;
		this.tipoContactos = null;
		

		cargarComboLocalidades();
		cargarComboTipoContacto();
	}

	private void cargarComboTipoContacto() {
		this.tipoContactos = null; // se agrego esto para cuando vayan haciendo modificaciones en el ABM de tipode contacto no se sobrecargue y no actualize cuando se borra
		this.tipoContactos = agenda.obtenerTiposContactos();
		this.ventanaPersona.getComboTipoContacto().removeAllItems();

		for(TipoContactoDTO tipo : tipoContactos){
			this.ventanaPersona.getComboTipoContacto().addItem(tipo.getTipoContacto());
		}

	}

	private void cargarComboLocalidades() {

		localidades = agenda.obtenerLocalidades();

		for(LocalidadDTO localidad : localidades){
			this.ventanaPersona.getComboLocalidad().addItem(localidad.getNombreLocalidad());
		}
	}

	private void ventanaAgregarPersona(ActionEvent a) {
		this.ventanaPersona.mostrarVentana();

	}

	private void guardarContacto(ActionEvent p) {


		int idTipoContacto = getIdTipoContacto(this.ventanaPersona.getComboTipoContacto().getSelectedItem().toString());
		TipoContactoDTO tipoContactoDTO = new TipoContactoDTO(idTipoContacto, Optional.ofNullable((String) ventanaPersona.getComboTipoContacto().getSelectedItem()).orElse(""));

		int idLocalidad = getIdLocalidad(this.ventanaPersona.getComboTipoContacto().getSelectedItem().toString());
		LocalidadDTO localidad = new LocalidadDTO(idLocalidad, Optional.ofNullable((String) ventanaPersona.getComboLocalidad().getSelectedItem()).orElse(""));

		DomicilioDTO nuevoDomicilio = new DomicilioDTO(0,
				ventanaPersona.getTxtCalle().getText(),
				ventanaPersona.getTextAltura().getText(),
				ventanaPersona.getTextPiso().getText(),
				ventanaPersona.getTextDepto().getText(),
				localidad);

		PersonaDTO nuevaPersona = new PersonaDTO(0,
				ventanaPersona.getTxtNombre().getText(),
				ventanaPersona.getTxtTelefono().getText(),nuevoDomicilio, tipoContactoDTO);


		this.agenda.agregarPersona(nuevaPersona);
		this.llenarTabla();
		this.ventanaPersona.cerrar();
	}

	private int getIdLocalidad(String nombreLocalidad) {

		for(LocalidadDTO localidad : localidades){
			if(localidad.getNombreLocalidad().equals(nombreLocalidad)){
				return localidad.getIdLocalidad();
			}
		}
		return -1;
	}

	private int getIdTipoContacto(String nombreTipo) {

		for(TipoContactoDTO tipo : tipoContactos){
			if(tipo.getTipoContacto().equals(nombreTipo)){
				return tipo.getIdTipoContacto();
			}
		}
		return -1;

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
		this.ventanaTipoContacto.cerrar();
		this.ventanaTipoContacto.mostrarVentana();
		this.restablecerBotones();
		this.llenarTablaTipoContacto();
	}

	private void guardarTipoContacto(ActionEvent q) {
		String tipoContacto = this.ventanaTipoContacto.getTxtAgregarTipoContacto().getText();
		if (!tipoContacto.isEmpty()) {
			TipoContactoDTO nuevoTipoContacto =  new TipoContactoDTO(0, tipoContacto);
			this.agenda.agregarTContacto(nuevoTipoContacto);
			this.llenarTablaTipoContacto();
			this.ventanaTipoContacto.getTxtAgregarTipoContacto().setText(null);
			this.mensaje.msgAgregado();
			this.cargarComboTipoContacto();
		}
		else {
			this.mensaje.msgCampoVacio();
		}
	}

	private void borrarTipoContacto(ActionEvent w) {
		int[] filas_seleccionadas = this.ventanaTipoContacto.getTablaTipoContactos().getSelectedRows();
		for(int fila: filas_seleccionadas) {
			this.agenda.eliminarTContacto(this.tipoContactos.get(fila));
		}
		this.llenarTablaTipoContacto();
		this.mensaje.msgBorrado();
		this.ventanaTipoContacto.getTxtAgregarTipoContacto().setText(null);
		this.cargarComboTipoContacto();
		this.restablecerBotones();
	}
	
	private void editarTipoContacto(ActionEvent x) {
		int[] filas_seleccionadas = this.ventanaTipoContacto.getTablaTipoContactos().getSelectedRows();
		if(filas_seleccionadas.length == 1) {
			String tipoContacto = this.ventanaTipoContacto.getTxtAgregarTipoContacto().getText();
			if (!tipoContacto.isEmpty()) {
				this.tipoContactos.get(filas_seleccionadas[0]).setTipoContacto(tipoContacto);
				
				this.agenda.editarTContacto(tipoContactos.get(filas_seleccionadas[0]));
				
				this.llenarTablaTipoContacto();
				this.mensaje.msgEditado();
				this.cargarComboTipoContacto();
				this.restablecerBotones();
			}
			else {
				this.mensaje.msgCampoVacio();
			}
		}
		else {
			this.mensaje.msgSeleccionarFila();
		}
		this.ventanaTipoContacto.getTxtAgregarTipoContacto().setText(null);
		
	}
	
	private void restablecerBotones() {
		this.ventanaTipoContacto.getBtnBorrar().setEnabled(false);
		this.ventanaTipoContacto.getBtnEditarTipoContacto().setEnabled(false);
		this.ventanaTipoContacto.getBtnAgregarTipoContacto().setEnabled(true);
	}

	private void editarFilaSeleccionada() {
		int[] filas_seleccionadas = this.ventanaTipoContacto.getTablaTipoContactos().getSelectedRows();
		if (filas_seleccionadas.length == 1) {
			this.ventanaTipoContacto.getBtnAgregarTipoContacto().setEnabled(false);
			this.ventanaTipoContacto.getBtnEditarTipoContacto().setEnabled(true);
			this.ventanaTipoContacto.getBtnBorrar().setEnabled(true);
			
			String fila_seleccionada_deno = this.tipoContactos.get(filas_seleccionadas[0]).getTipoContacto();
			this.ventanaTipoContacto.getTxtAgregarTipoContacto().setText(fila_seleccionada_deno);
			
		}
		else if (filas_seleccionadas.length == 0) {
			this.restablecerBotones();
			this.ventanaTipoContacto.getTxtAgregarTipoContacto().setText(null);
		}
		else {
			this.ventanaTipoContacto.getBtnAgregarTipoContacto().setEnabled(false);
			this.ventanaTipoContacto.getBtnBorrar().setEnabled(true);
			this.ventanaTipoContacto.getBtnEditarTipoContacto().setEnabled(false);
			this.ventanaTipoContacto.getTxtAgregarTipoContacto().setText(null);
		}
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
					this.personas_en_tabla.get(i).getDomicilio().getCalle(),
					this.personas_en_tabla.get(i).getDomicilio().getAltura(),
					this.personas_en_tabla.get(i).getDomicilio().getPiso(),
					this.personas_en_tabla.get(i).getDomicilio().getDepto(),
					this.personas_en_tabla.get(i).getDomicilio().getLocalidad().getNombreLocalidad(),
					this.personas_en_tabla.get(i).getTipoContacto().getTipoContacto()};
			this.vista.getModelPersonas().addRow(fila);
		}
	}

	private void llenarTablaTipoContacto() {
		this.ventanaTipoContacto.getModelTipoContactos().setRowCount(0);
		this.ventanaTipoContacto.getModelTipoContactos().setColumnCount(0);
		this.ventanaTipoContacto.getModelTipoContactos().setColumnIdentifiers(this.ventanaTipoContacto.getNombreColumnas());

		this.tipoContactos = agenda.obtenerTiposContactos();//hacer readall en daosql
		for(int i = 0; i < this.tipoContactos.size(); i++) {
			Object[] fila = { this.tipoContactos.get(i).getTipoContacto()};
			this.ventanaTipoContacto.getModelTipoContactos().addRow(fila);
		}

	}
	
	private void ventanaABMLocalidad(ActionEvent t) {
		this.ventanaLocalidad.mostrarVentana();
	}

	private void guardarLocalidad(ActionEvent z) {
		int idTipoContacto = getIdTipoContacto(this.ventanaPersona.getComboTipoContacto().getSelectedItem().toString());
		TipoContactoDTO tipoContactoDTO = new TipoContactoDTO(idTipoContacto, Optional.ofNullable((String) ventanaPersona.getComboTipoContacto().getSelectedItem()).orElse(""));

		int idLocalidad = getIdLocalidad(this.ventanaPersona.getComboTipoContacto().getSelectedItem().toString());
		LocalidadDTO localidad = new LocalidadDTO(idLocalidad, Optional.ofNullable((String) ventanaPersona.getComboLocalidad().getSelectedItem()).orElse(""));

		DomicilioDTO nuevoDomicilio = new DomicilioDTO(0,
				ventanaPersona.getTxtCalle().getText(),
				ventanaPersona.getTextAltura().getText(),
				ventanaPersona.getTextPiso().getText(),
				ventanaPersona.getTextDepto().getText(),
				localidad);

		PersonaDTO nuevaPersona = new PersonaDTO(0,
				ventanaPersona.getTxtNombre().getText(),
				ventanaPersona.getTxtTelefono().getText(),nuevoDomicilio, tipoContactoDTO);


		this.agenda.agregarPersona(nuevaPersona);
		this.llenarTabla();
		this.ventanaPersona.cerrar();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}

}