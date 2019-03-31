package presentacion.vista;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MensajesDeDialogo extends JFrame{

	private static final long serialVersionUID = 1L;
	private static MensajesDeDialogo INSTANCE ;

	public static MensajesDeDialogo getInstance(){
		if(INSTANCE == null)
			return new MensajesDeDialogo();
		else
			return INSTANCE;
	}
		
	public void msgAgregado() {
		JOptionPane.showMessageDialog(null, "Agregado correctamente.");	
	}
	
	public void msgBorrado() {
		JOptionPane.showMessageDialog(null, "Borrado correctamente.");	
	}
	
	public void msgEditado() {
		JOptionPane.showMessageDialog(null, "Editado correctamente.");	
	}
	
	public void msgSinCambios() {
		JOptionPane.showMessageDialog(null, "No se realizaron cambios.");
	}
	
	public void msgSeleccionarFila() {
		JOptionPane.showMessageDialog(null, "Debe Seleccionar una fila");	
	}
	
	public void msgCampoVacio() {
		JOptionPane.showMessageDialog(null, "Campo vacio no aceptado");	
	}
	
	public void msgDatosConexionBD() {
		JOptionPane.showMessageDialog(null, "No se pudo establecer una conexion a la base de datos");	
	}
	
	public void msgDatosConexionBDModificados() {
		JOptionPane.showMessageDialog(null, "Por favor, luego de este mensaje, vuelva a abrir la aplicación");	
	}
	
	public void msgCierreAplicacion() {
		JOptionPane.showMessageDialog(null, "Se cierra la aplicación");	
	}

}
