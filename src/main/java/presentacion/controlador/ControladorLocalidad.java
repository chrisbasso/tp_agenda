package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
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
import presentacion.vista.VentanaPrincipal;
import utils.EmailValidator;

import javax.swing.*;

public class ControladorLocalidad implements ActionListener
{
	private VentanaPrincipal vista;
	private List<PersonaDTO> personas;
	private List<TipoContactoDTO> tipoContactos;
	private List<LocalidadDTO> localidades;
	private VentanaPersona ventanaPersona;
	private VentanaTipoContacto ventanaTipoContacto;
	private VentanaLocalidad ventanaLocalidad;
	private Agenda agenda;
	private MensajesDeDialogo mensaje;

	public ControladorLocalidad(VentanaPrincipal vista, Agenda agenda){
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

		this.ventanaPersona.getBtnAgregarPersona().addActionListener(e-> validarProcesarGuardado());
		this.ventanaLocalidad.getBtnAgregarLocalidad().addActionListener(e->guardarLocalidad());
		this.ventanaLocalidad.getBtnEditarLocalidad().addActionListener(e->editarLocalidad());
		this.ventanaLocalidad.getBtnBorrar().addActionListener(e-> {
			try {
				borrarLocalidad();
			} catch (MySQLIntegrityConstraintViolationException e1) {
				JOptionPane.showMessageDialog(null, "La Localidad que intenta eliminar esta relacionada a un contacto, debe eliminar primero el contacto");
			}
		});
		this.ventanaLocalidad.getTablaLocalidades().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				editarFilaLocalidadSeleccionada();
			}
		});

		this.ventanaTipoContacto.getBtnAgregarTipoContacto().addActionListener(q->guardarTipoContacto(q));
		this.ventanaTipoContacto.getBtnBorrar().addActionListener(w-> {
			try {
				borrarTipoContacto(w);
			} catch (MySQLIntegrityConstraintViolationException e) {
				JOptionPane.showMessageDialog(null, "El tipo de contacto que intenta eliminar esta relacionado a un contacto, debe eliminar primero el contacto");
			}
		});
		this.ventanaTipoContacto.getBtnEditarTipoContacto().addActionListener(x-> editarTipoContacto(x));
		this.ventanaTipoContacto.getTablaTipoContactos().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				editarFilaTipoContactoSeleccionada();
			}
		});

		this.agenda = agenda;
		this.personas = null;
		this.tipoContactos = null;

		cargarComboLocalidades();
		cargarComboTipoContacto();
	}

	private void validarProcesarGuardado() {

		if(ventanaPersona.getTxtNombre().getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "Debe completar el nombre");
			return;
		}
		if(ventanaPersona.getTxtTelefono().getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "Debe completar el telefono");
			return;
		}
		if(ventanaPersona.getTxtEmail().getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "Debe completar el email");
			return;
		}

		if(!EmailValidator.validate(ventanaPersona.getTxtEmail().getText().trim())) {
			JOptionPane.showMessageDialog(null, "Email inválido");
			return;
		}

		try {
			guardarContacto(getSelectItemTable());
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	private void borrarLocalidad() throws MySQLIntegrityConstraintViolationException{

		int[] filas_seleccionadas = this.ventanaLocalidad.getTablaLocalidades().getSelectedRows();
		for(int fila: filas_seleccionadas) {
			this.agenda.borrarLocalidad(this.localidades.get(fila));
		}
		this.llenarTablaLocalidades();
		this.ventanaLocalidad.getTxtAgregarLocalidad().setText(null);
		this.cargarComboLocalidades();
	}

	private void editarLocalidad() {

		int[] filas_seleccionadas = this.ventanaLocalidad.getTablaLocalidades().getSelectedRows();
		String localidad = this.ventanaLocalidad.getTxtAgregarLocalidad().getText();
		if (!localidad.isEmpty()) {
			this.localidades.get(filas_seleccionadas[0]).setNombreLocalidad(localidad);

			this.agenda.editarLocalidad(localidades.get(filas_seleccionadas[0]));

			this.llenarTablaLocalidades();
			this.cargarComboLocalidades();
		}

		this.ventanaLocalidad.getTxtAgregarLocalidad().setText(null);
		this.llenarTabla();
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

		this.tipoContactos = agenda.obtenerTiposContactos();
		this.ventanaPersona.getComboTipoContacto().removeAllItems();

		for(TipoContactoDTO tipo : tipoContactos){
			this.ventanaPersona.getComboTipoContacto().addItem(tipo.getTipoContacto());
		}

	}

	private void cargarComboLocalidades() {

		localidades = agenda.obtenerLocalidades();

		this.ventanaPersona.getComboLocalidad().removeAllItems();

		for(LocalidadDTO localidad : localidades){
			this.ventanaPersona.getComboLocalidad().addItem(localidad.getNombreLocalidad());
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

	private void guardarContacto(PersonaDTO persona) throws SQLException {

		if(ventanaPersona.getPersona() != null){
			setPersonaValues(persona);
			this.agenda.editarPersona(persona);
		}else{
			PersonaDTO nuevaPersona = setNuevaPersonaValues();
			this.agenda.agregarPersona(nuevaPersona);
		}

		this.llenarTabla();
		this.ventanaPersona.cerrar();
	}

	private PersonaDTO setNuevaPersonaValues() {
		int idTipoContacto = getIdTipoContacto(this.ventanaPersona.getComboTipoContacto().getSelectedItem().toString());
		TipoContactoDTO tipoContactoDTO = new TipoContactoDTO(idTipoContacto, Optional.ofNullable((String) ventanaPersona.getComboTipoContacto().getSelectedItem()).orElse(""));

		int idLocalidad = getIdLocalidad(this.ventanaPersona.getComboLocalidad().getSelectedItem().toString());
		LocalidadDTO localidad = new LocalidadDTO(idLocalidad, Optional.ofNullable((String) ventanaPersona.getComboLocalidad().getSelectedItem()).orElse(""));

		DomicilioDTO nuevoDomicilio = new DomicilioDTO(0,
				ventanaPersona.getTxtCalle().getText(),
				ventanaPersona.getTextAltura().getText(),
				ventanaPersona.getTextPiso().getText(),
				ventanaPersona.getTextDepto().getText(),
				localidad);

		return new PersonaDTO(0,
				ventanaPersona.getTxtNombre().getText(),
				ventanaPersona.getTxtTelefono().getText(), nuevoDomicilio, tipoContactoDTO,
				ventanaPersona.getTxtEmail().getText(), ventanaPersona.getFechaNac().getDate());
	}

	private void setPersonaValues(PersonaDTO persona) {
		persona.setNombre(ventanaPersona.getTxtNombre().getText());
		persona.setTelefono(ventanaPersona.getTxtTelefono().getText());
		persona.getDomicilio().setCalle(ventanaPersona.getTxtCalle().getText());
		persona.getDomicilio().setAltura(ventanaPersona.getTextAltura().getText());
		persona.getDomicilio().setPiso(ventanaPersona.getTextPiso().getText());
		persona.getDomicilio().setDepto(ventanaPersona.getTextDepto().getText());
		persona.getDomicilio().getLocalidad().setIdLocalidad(getIdLocalidad(ventanaPersona.getComboLocalidad().getSelectedItem().toString()));
		persona.getTipoContacto().setIdTipoContacto(getIdTipoContacto(ventanaPersona.getComboTipoContacto().getSelectedItem().toString()));
		persona.setEmail(ventanaPersona.getTxtEmail().getText());
		persona.setFechaNacimiento(ventanaPersona.getFechaNac().getDate());
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
		this.ventanaTipoContacto.cerrar();
		this.ventanaTipoContacto.mostrarVentana();
		this.restablecerBotones();
		this.llenarTablaTipoContacto();
	}

	private void guardarTipoContacto(ActionEvent q) {
		String tipoContacto = this.ventanaTipoContacto.getTxtAgregarTipoContacto().getText();
		if (!tipoContacto.isEmpty()) {
			TipoContactoDTO nuevoTipoContacto =  new TipoContactoDTO(0, tipoContacto);
			this.agenda.agregarTipoDeContacto(nuevoTipoContacto);
			this.llenarTablaTipoContacto();
			this.ventanaTipoContacto.getTxtAgregarTipoContacto().setText(null);
			this.cargarComboTipoContacto();
		}
		else {
			this.mensaje.msgCampoVacio();
		}
	}

	private void borrarTipoContacto(ActionEvent w) throws MySQLIntegrityConstraintViolationException {
		int[] filas_seleccionadas = this.ventanaTipoContacto.getTablaTipoContactos().getSelectedRows();
		for(int fila: filas_seleccionadas) {
			this.agenda.borrarTipoDeContacto(this.tipoContactos.get(fila));
		}
		this.llenarTablaTipoContacto();
		//this.mensaje.msgBorrado();
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
		this.llenarTabla();
		
	}
	
	private void restablecerBotones() {
		this.ventanaTipoContacto.getBtnBorrar().setEnabled(false);
		this.ventanaTipoContacto.getBtnEditarTipoContacto().setEnabled(false);
		this.ventanaTipoContacto.getBtnAgregarTipoContacto().setEnabled(true);
	}

	private void editarFilaLocalidadSeleccionada() {
		int[] filas_seleccionadas = this.ventanaLocalidad.getTablaLocalidades().getSelectedRows();
		if (filas_seleccionadas.length == 1) {
			this.ventanaLocalidad.getBtnEditarLocalidad().setEnabled(true);
			this.ventanaLocalidad.getBtnBorrar().setEnabled(true);

			String fila_seleccionada_deno = this.localidades.get(filas_seleccionadas[0]).getNombreLocalidad();
			this.ventanaLocalidad.getTxtAgregarLocalidad().setText(fila_seleccionada_deno);

		}
		else if (filas_seleccionadas.length == 0) {
			this.ventanaLocalidad.getTxtAgregarLocalidad().setText(null);
		}
		else {
			this.ventanaLocalidad.getBtnBorrar().setEnabled(true);
			this.ventanaLocalidad.getBtnEditarLocalidad().setEnabled(false);
			this.ventanaLocalidad.getTxtAgregarLocalidad().setText(null);
		}
	}

	private void editarFilaTipoContactoSeleccionada() {
		int[] filas_seleccionadas = this.ventanaTipoContacto.getTablaTipoContactos().getSelectedRows();
		if (filas_seleccionadas.length == 1) {
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
					this.personas.get(i).getTipoContacto().getTipoContacto(),
					this.personas.get(i).getEmail(),
					this.personas.get(i).getFechaNacimiento()};
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
		this.llenarTablaLocalidades();
	}

	private void guardarLocalidad()  {

		String localidad = this.ventanaLocalidad.getTxtAgregarLocalidad().getText();
		LocalidadDTO nuevaLocalidad = new LocalidadDTO(0, localidad);
		this.agenda.agregarLocalidad(nuevaLocalidad);
		this.llenarTablaLocalidades();
		this.ventanaLocalidad.getTxtAgregarLocalidad().setText(null);
		this.cargarComboLocalidades();

	}

	private void llenarTablaLocalidades() {

		this.ventanaLocalidad.getModelLocalidades().setRowCount(0);
		this.ventanaLocalidad.getModelLocalidades().setColumnCount(0);
		this.ventanaLocalidad.getModelLocalidades().setColumnIdentifiers(this.ventanaLocalidad.getNombreColumnas());

		this.localidades = agenda.obtenerLocalidades();//hacer readall en daosql
		for(int i = 0; i < this.localidades.size(); i++) {
			Object[] fila = { this.localidades.get(i).getNombreLocalidad()};
			this.ventanaLocalidad.getModelLocalidades().addRow(fila);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}

}