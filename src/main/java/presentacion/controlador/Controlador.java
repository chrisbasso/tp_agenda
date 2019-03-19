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
import persistencia.dao.interfaz.PersonaDAO;
import presentacion.reportes.ReporteAgenda;
import presentacion.vista.MensajesDeDialogo;
import presentacion.vista.VentanaLocalidad;
import presentacion.vista.VentanaPersona;
import presentacion.vista.VentanaTipoContacto;
import presentacion.vista.Vista;

public class Controlador implements ActionListener
{
	private Vista vista;
	private List<PersonaDTO> personas;
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
		this.vista.getBtnAgregar().addActionListener(a->ventanaAgregarPersona());
		this.vista.getBtnEditar().addActionListener(e-> ventanaEditarPersona());
		this.vista.getBtnBorrar().addActionListener(s->borrarPersona(s));
		this.vista.getBtnReporte().addActionListener(r->mostrarReporte(r));
		this.vista.getBtnAbmTipoDeContacto().addActionListener(t->ventanaABMTipoContacto(t));
		this.vista.getBtnAbmLocalidad().addActionListener(t->ventanaABMLocalidad(t));
		
		this.ventanaPersona = VentanaPersona.getInstance();
		this.ventanaTipoContacto = VentanaTipoContacto.getInstance();
		this.ventanaLocalidad = VentanaLocalidad.getInstance();
		this.mensaje = MensajesDeDialogo.getInstance();

		this.ventanaPersona.getBtnAgregarPersona().addActionListener(e-> guardarContacto(getSelectItemTable()));
		this.ventanaLocalidad.getBtnAgregarLocalidad().addActionListener(e->guardarLocalidad(e));
		this.ventanaTipoContacto.getBtnAgregarTipoContacto().addActionListener(q->guardarTipoContacto(q));
		this.ventanaTipoContacto.getBtnBorrar().addActionListener(w-> borrarTipoContacto(w));
		this.ventanaTipoContacto.getBtnEditarTipoContacto().addActionListener(x-> editarTipoContacto(x));
		this.ventanaTipoContacto.getTablaTipoContactos().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				editarFilaSeleccionada();
			}
		});

		this.agenda = agenda;
		this.personas = null;
		this.tipoContactos = null;


		cargarComboLocalidades();
		cargarComboTipoContacto();
	}

	private PersonaDTO getSelectItemTable(){
		for (int i = 0; i < personas.size(); i++) {
			if(i == this.vista.getTablaPersonas().getSelectedRow()){
				return personas.get(i);
			}
		}
		return null;
	}

	private void cargarComboTipoContacto() {

		tipoContactos = agenda.obtenerTiposContactos();

		for(TipoContactoDTO tipo : tipoContactos){
			this.ventanaPersona.getComboTipoContacto().addItem(tipo.getTipoContacto());
		}

	}

	private void cargarComboLocalidades() {

		localidades = agenda.obtenerLocalidades();

		for(LocalidadDTO localidad : localidades){
			this.ventanaPersona.getComboLocalidad().addItem(localidad.getNombreLocalidad());
			
			this.ventanaLocalidad.getModelLocalidades().setRowCount(0); //Para vaciar la tabla
			this.ventanaLocalidad.getModelLocalidades().setColumnCount(0); 
			this.ventanaLocalidad.getModelLocalidades().setColumnIdentifiers(this.ventanaLocalidad.getNombreColumnas());
			
			Object[] fila = {localidad.getNombreLocalidad()};
			this.ventanaLocalidad.getModelLocalidades().addRow(fila);
		}
	}

	private void ventanaAgregarPersona() {
		this.ventanaPersona.setPersona(null);
		this.ventanaPersona.mostrarVentana();
	}

	private void ventanaEditarPersona(){
		PersonaDTO personaAEditar = getSelectItemTable();
		this.ventanaPersona.setPersona(personaAEditar);
		this.ventanaPersona.mostrarVentana();
	}

	private void guardarContacto(PersonaDTO persona) {

		if(persona != null){
			this.agenda.editarPersona(persona);
		}else{

		}

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
			this.agenda.borrarPersona(this.personas.get(fila));
		}

		this.llenarTabla();
	}

	private void ventanaABMTipoContacto(ActionEvent t) {
		this.ventanaTipoContacto.mostrarVentana();
		this.llenarTablaTipoContacto();
	}

	private void guardarTipoContacto(ActionEvent q) {
		TipoContactoDTO nuevoTipoContacto =  new TipoContactoDTO(0, ventanaTipoContacto.getTxtAgregarTipoContacto().getText());
		this.agenda.agregarTContacto(nuevoTipoContacto);
		this.llenarTablaTipoContacto();
		this.ventanaTipoContacto.getTxtAgregarTipoContacto().setText(null);
		this.mensaje.msgTipocontactoOK();
	}

	private void borrarTipoContacto(ActionEvent w) {
		int[] filas_seleccionadas = this.ventanaTipoContacto.getTablaTipoContactos().getSelectedRows();
		for(int fila: filas_seleccionadas) {
			this.agenda.eliminarTContacto(this.tipoContactos.get(fila));
		}
		this.llenarTablaTipoContacto();
		this.mensaje.msgTipocontactoBorrado();
	}

	private void editarTipoContacto(ActionEvent x) {
		int[] filas_seleccionadas = this.ventanaTipoContacto.getTablaTipoContactos().getSelectedRows();
		for(int fila: filas_seleccionadas) {
			this.tipoContactos.get(fila).setTipoContacto(this.ventanaTipoContacto.getTxtAgregarTipoContacto().getText());
			this.agenda.editarTContacto(tipoContactos.get(fila));
		}
		this.llenarTablaTipoContacto();
		this.mensaje.msgTipocontactoEditado();
		this.ventanaTipoContacto.getTxtAgregarTipoContacto().setText(null);
	}

	private void editarFilaSeleccionada() {
		int fila_seleccionada = this.ventanaTipoContacto.getTablaTipoContactos().getSelectedRow();
		String fila_seleccionada_deno = this.tipoContactos.get(fila_seleccionada).getTipoContacto();
		this.ventanaTipoContacto.getTxtAgregarTipoContacto().setText(fila_seleccionada_deno);
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

		this.personas = agenda.obtenerPersonas();
		for (int i = 0; i < this.personas.size(); i ++)
		{
			Object[] fila = {this.personas.get(i).getNombre(),
					this.personas.get(i).getTelefono(),
					this.personas.get(i).getDomicilio().getCalle(),
					this.personas.get(i).getDomicilio().getAltura(),
					this.personas.get(i).getDomicilio().getPiso(),
					this.personas.get(i).getDomicilio().getDepto(),
					this.personas.get(i).getDomicilio().getLocalidad().getNombreLocalidad(),
					this.personas.get(i).getTipoContacto().getTipoContacto()};
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
		String nombreLocalidad = this.ventanaLocalidad.getTxtAgregarLocalidad().getText();
		this.agenda.agregarLocalidad(nombreLocalidad);		
		this.ventanaLocalidad.cerrar();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}

}