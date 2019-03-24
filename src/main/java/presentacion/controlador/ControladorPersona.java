package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;

import dto.LocalidadDTO;
import dto.TipoContactoDTO;
import modelo.Agenda;
import modelo.Persona;
import presentacion.vista.VentanaPersona;
import utils.EmailValidator;

public class ControladorPersona implements ActionListener{
	private VentanaPersona ventanaPersona;
	private Agenda agenda;
	private Persona persona;

	public ControladorPersona(VentanaPersona pantalla, Agenda agenda, Persona persona){
		this.ventanaPersona = pantalla;
		this.agenda = agenda;
		this.persona = persona;
		this.ventanaPersona.getBtnAgregarPersona().addActionListener(e-> validarProcesarGuardado());
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
	}

	private void cargarComboTipoContacto() {
		List <TipoContactoDTO> tipoContactos = agenda.obtenerTiposContactos();
		this.ventanaPersona.getComboTipoContacto().removeAllItems();
		for(TipoContactoDTO tipo : tipoContactos){
			this.ventanaPersona.getComboTipoContacto().addItem(tipo.getTipoContacto());
		}
	}

	private void cargarComboLocalidades() {
		List <LocalidadDTO> localidades = agenda.obtenerLocalidades();
		this.ventanaPersona.getComboLocalidad().removeAllItems();
		for(LocalidadDTO localidad : localidades){
			this.ventanaPersona.getComboLocalidad().addItem(localidad.getNombreLocalidad());
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}