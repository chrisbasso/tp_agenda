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
import modelo.TipoContacto;
import presentacion.reportes.ReporteAgenda;
import presentacion.vista.MensajesDeDialogo;
import presentacion.vista.VentanaLocalidad;
import presentacion.vista.VentanaPersona;
import presentacion.vista.VentanaTipoContacto;
import presentacion.vista.VentanaPrincipal;
import utils.EmailValidator;

import javax.swing.*;

public class ControladorTipoContacto implements ActionListener{
	private Controlador controladorSuperior;
	private VentanaTipoContacto ventana;
	
	public ControladorTipoContacto() {
		ventana = VentanaTipoContacto.getInstance();	
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}

	public void setControladorSuperior(Controlador controladorSuperior) {
		this.controladorSuperior = controladorSuperior;
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