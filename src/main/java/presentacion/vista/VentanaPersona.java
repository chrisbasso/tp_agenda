package presentacion.vista;

import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

public class VentanaPersona extends JFrame{
	private static final long serialVersionUID = 1L;
	private static VentanaPersona INSTANCIA;
	private JPanel contentPane;
	private JTextField textNombre;
	private JTextField textApellido;
	private JTextField textTelefono;	
	private JTextField textCalle;
	private JTextField textAltura;
	private JTextField textPiso;
	private JTextField textDepto;
	private JTextField textEmail;
	private JButton btnAccionPersona;
	private JDateChooser fechaNac;
	private JComboBox<String> comboLocalidad;
	private String localidadSeleccionada;
	private JComboBox<String> comboTipoContacto;
	private String tipoContactoSeleccionado;

	public static VentanaPersona getInstance(){
		if(INSTANCIA == null)
			return new VentanaPersona();
		else
			return INSTANCIA;
	}
	
	public void setTituloVentana(String titulo) {
		setTitle(titulo); //setTitle("Agregar contacto");
	}

	private VentanaPersona(){		
		this.setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 404, 395);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 373, 340);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 11, 113, 14);
		panel.add(lblNombre);

		textNombre = new JTextField();
		textNombre.setBounds(149, 8, 164, 20);
		panel.add(textNombre);
		textNombre.setColumns(10);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(10, 43, 113, 14);
		panel.add(lblApellido);
		
		textApellido = new JTextField();
		textApellido.setColumns(10);
		textApellido.setBounds(149, 40, 164, 20);
		panel.add(textApellido);

		JLabel lblTelfono = new JLabel("Teléfono");
		lblTelfono.setBounds(10, 72, 113, 14);
		panel.add(lblTelfono);

		textTelefono = new JTextField();
		textTelefono.setBounds(149, 69, 164, 20);
		panel.add(textTelefono);
		textTelefono.setColumns(10);

		JLabel lblTipoDeContacto = new JLabel("Tipo de contacto");
		lblTipoDeContacto.setBounds(10, 220, 137, 14);
		panel.add(lblTipoDeContacto);

		comboTipoContacto = new JComboBox<>();
		comboTipoContacto.setBounds(149, 220, 89, 20);
		panel.add(comboTipoContacto);
		
		JLabel lblFechaDeNacimiento = new JLabel("Fecha de nacimiento");
		lblFechaDeNacimiento.setBounds(10, 290, 137, 14);
		panel.add(lblFechaDeNacimiento);

		fechaNac = new JDateChooser(new Date());
		fechaNac.setBounds(149, 290, 89, 20);
		panel.add(fechaNac);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(10, 260, 89, 14);
		panel.add(lblEmail);

		textEmail = new JTextField();
		textEmail.setBounds(149, 260, 89, 20);
		panel.add(textEmail);
		textEmail.setColumns(10);

		JLabel lblDomicilio = new JLabel("Domicilio");
		lblDomicilio.setBounds(10, 112, 72, 14);
		panel.add(lblDomicilio);

		JLabel lblCalle = new JLabel("Calle");
		lblCalle.setBounds(114, 112, 46, 14);
		panel.add(lblCalle);

		textCalle = new JTextField();
		textCalle.setBounds(149, 109, 89, 20);
		panel.add(textCalle);
		textCalle.setColumns(10);

		JLabel lblAltura = new JLabel("Altura");
		lblAltura.setBounds(264, 112, 46, 14);
		panel.add(lblAltura);

		textAltura = new JTextField();
		textAltura.setBounds(305, 109, 46, 20);
		panel.add(textAltura);
		textAltura.setColumns(10);

		JLabel lblPiso = new JLabel("Piso");
		lblPiso.setBounds(114, 140, 46, 14);
		panel.add(lblPiso);

		textPiso = new JTextField();
		textPiso.setBounds(149, 137, 89, 20);
		panel.add(textPiso);
		textPiso.setColumns(10);

		JLabel lblDepto = new JLabel("Depto.");
		lblDepto.setBounds(264, 140, 46, 14);
		panel.add(lblDepto);

		textDepto = new JTextField();
		textDepto.setBounds(305, 137, 46, 20);
		panel.add(textDepto);
		textDepto.setColumns(10);

		JLabel lblLocalidad = new JLabel("Localidad");
		lblLocalidad.setBounds(10, 184, 89, 14);
		panel.add(lblLocalidad);

		comboLocalidad = new JComboBox<>();
		comboLocalidad.setBounds(149, 180, 89, 20);
		panel.add(comboLocalidad);

		btnAccionPersona = new JButton("");
		btnAccionPersona.setBounds(272, 290, 89, 23);
		panel.add(btnAccionPersona);
		
		agregarValidaciones(); 
	}
	
	private void agregarValidaciones() {
		textAltura.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				try {
					Long.parseLong(textAltura.getText());
				} catch (Exception e) {
					JOptionPane.showMessageDialog(rootPane, "Solo acepta numeros");
					textAltura.setText("");
				}
			}
		});
		
		textPiso.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				try {
					long number = Long.parseLong(textPiso.getText());
				} catch (Exception e) {
					JOptionPane.showMessageDialog(rootPane, "Solo Acepta Numeros");
					textPiso.setText("");
				}
			}
		});
	}
	
	public void agregarLocalidadAlCombo(String localidad) {
		comboLocalidad.addItem(localidad);
	}
	
	public void agregarTipoContactoAlCombo(String tipoContacto) {
		comboTipoContacto.addItem(tipoContacto);
	}

	public void mostrarVentana()	{
		setVisible(true);
	}
	
	public void cerrar() {
		dispose();
	}

	public void setPersona(Object [] persona) {
		if (persona != null && persona.length > 0) {
			this.textNombre.setText((String)persona[0]);
			this.textTelefono.setText((String)persona[1]);
			this.textCalle.setText((String)persona[2]);
			this.textAltura.setText((String)persona[3]);
			this.textPiso.setText((String)persona[4]);
			this.textDepto.setText((String)persona[5]);
			this.textEmail.setText((String)persona[6]);
			this.fechaNac.setDate((Date)persona[7]);
			btnAccionPersona.setText("Editar");
		}else{
			this.textNombre.setText("");
			this.textTelefono.setText("");
			this.textCalle.setText("");
			this.textAltura.setText("");
			this.textPiso.setText("");
			this.textEmail.setText("");
			btnAccionPersona.setText("Agregar");
		}
	}
	
	public String getNombre()	{
		return textNombre.getText();
	}

	public String getApellido()	{
		return textApellido.getText();
	}

	public String getTelefono()	{
		return textTelefono.getText();
	}

	public String getCalle()	{
		return textCalle.getText();
	}

	public String getAltura() {
		return textAltura.getText();
	}

	public String getPiso() {
		return textPiso.getText();
	}

	public String getDepto() {
		return textDepto.getText();
	}

	public String getEmail()	{
		return textEmail.getText();
	}

	public Date getFechaNacimiento()	{
		return fechaNac.getDate();
	}
	
	public String getLocalidad() {
		localidadSeleccionada = comboLocalidad.getSelectedItem().toString();
		return localidadSeleccionada;
	}

	public String getTipoContacto() {
		tipoContactoSeleccionado = comboTipoContacto.getSelectedItem().toString();
		return tipoContactoSeleccionado;
	}
	
	public JButton getBtnAccionPersona(){
		return btnAccionPersona;
	}
}