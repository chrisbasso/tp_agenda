package presentacion.vista;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MensajesDeDialogo extends JFrame{

	private static final long serialVersionUID = 1L;
	private static MensajesDeDialogo INSTANCE ;

	public static MensajesDeDialogo getInstance()
	{
		if(INSTANCE == null)
			return new MensajesDeDialogo();
		else
			return INSTANCE;
	}
		
	public void msgTipocontactoOK() {
		JOptionPane.showMessageDialog(null, "Tipo de Contacto agregado correctamente.");	
	}
	
	public void msgTipocontactoBorrado() {
		JOptionPane.showMessageDialog(null, "Tipo de Contacto borrado correctamente.");	
	}
	
	public void msgTipocontactoEditado() {
		JOptionPane.showMessageDialog(null, "Tipo de Contacto editado correctamente.");	
	}
}
