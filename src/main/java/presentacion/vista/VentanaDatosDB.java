package presentacion.vista;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import persistencia.conexion.Conexion;

public class VentanaDatosDB extends JDialog {
	private static final long serialVersionUID = 1L;
	private static VentanaDatosDB INSTANCIA;
	private JPanel contentPane;
	private JTextField textURL;
	private JTextField textUSER;	
	private JTextField textPWD;
	private JButton btnAceptar;
	private JButton btnSalir;
	
	public static VentanaDatosDB getInstance(){
		if(INSTANCIA == null)
			return new VentanaDatosDB();
		else
			return INSTANCIA;
	}
	
	public void setTituloVentana(String titulo) {
		setTitle(titulo); //setTitle("Agregar contacto");
	}

	private VentanaDatosDB(){		
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 404, 365);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 373, 310);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblURL = new JLabel("URL");
		lblURL.setBounds(10, 11, 113, 14);
		panel.add(lblURL);

		textURL = new JTextField();
		textURL.setBounds(149, 8, 164, 20);
		panel.add(textURL);
		textURL.setColumns(10);

		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(10, 52, 113, 14);
		panel.add(lblUsuario);

		textUSER = new JTextField();
		textUSER.setBounds(149, 49, 164, 20);
		panel.add(textUSER);
		textUSER.setColumns(10);

		JLabel lblPassword = new JLabel("Contraseña");
		lblPassword.setBounds(10, 91, 137, 14);
		panel.add(lblPassword);

		textPWD = new JTextField();
		textPWD.setBounds(149, 89, 164, 20);
		panel.add(textPWD);
		textPWD.setColumns(10);

		btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(170, 195, 89, 23);
		panel.add(btnAceptar);
		
		btnSalir = new JButton("Salir");
		btnSalir.setBounds(69, 195, 89, 23);
		panel.add(btnSalir);
		
		agregarValidaciones(); 
	}
	
	private void agregarValidaciones() {

	}
	
	public void mostrarVentana()	{
		setVisible(true);
	}
	
	public void cerrar() {
		dispose();
	}
	
	public String getURL()	{
		return textURL.getText();
	}

	public String getUSER()	{
		return textUSER.getText();
	}

	public String getPWD()	{
		return textPWD.getText();
	}
	
	public JButton getBtnAceptar(){
		return btnAceptar;
	}

	public JButton getBtnSalir(){
		return btnSalir;
	}

}