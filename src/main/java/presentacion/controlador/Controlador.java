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

public class Controlador {
	private ControladorMenuPrincipal controladorVentanaPrincipal;
	private ControladorPersona controladorVentanaPersona;
	private ControladorLocalidad controladorVentanaLocalidad;
	private ControladorTipoContacto controladorVentanaTipoContacto;

	public Controlador(VentanaPrincipal vista, Agenda agenda){
		controladorVentanaPrincipal = new ControladorMenuPrincipal(vista, agenda);
//		controladorVentanaPersona = new ControladorPersona();
//		controladorVentanaLocalidad = new ControladorLocalidad();
//		controladorVentanaTipoContacto = new ControladorTipoContacto();
	}	
}