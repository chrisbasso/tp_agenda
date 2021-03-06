package presentacion.vista;

import com.toedter.calendar.JDateChooser;
import dto.PersonaDTO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.util.Date;

public class VentanaPersona extends JFrame
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtTelefono;
	private JButton btnAgregarPersona;
	private JTextField textCalle;
	private JTextField textAltura;
	private JTextField textPiso;
	private JTextField textDepto;
	private JTextField textEmail;
	private JDateChooser fechaNac;
	private JComboBox<String> comboLocalidad;
	private JComboBox<String> comboTipoContacto;
	private static VentanaPersona INSTANCE;
	private PersonaDTO persona;

	public static VentanaPersona getInstance()
	{
		if(INSTANCE == null)
			return new VentanaPersona();
		else
			return INSTANCE;
	}

	private VentanaPersona()
	{
		super();
		setTitle("Agregar contacto");
		this.setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 509, 365);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 481, 310);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNombreYApellido = new JLabel("Nombre y apellido");
		lblNombreYApellido.setBounds(10, 11, 113, 14);
		panel.add(lblNombreYApellido);

		JLabel lblTelfono = new JLabel("Teléfono");
		lblTelfono.setBounds(10, 52, 113, 14);
		panel.add(lblTelfono);

		txtNombre = new JTextField();
		txtNombre.setToolTipText("Apellido");
		txtNombre.setBounds(317, 9, 164, 20);
		panel.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtApellido = new JTextField();
		txtApellido.setToolTipText("Nombre");
		txtApellido.setBounds(149, 8, 164, 20);
		panel.add(txtApellido);
		txtApellido.setColumns(10);

		txtTelefono = new JTextField();
		txtTelefono.setBounds(149, 49, 164, 20);
		panel.add(txtTelefono);
		txtTelefono.setColumns(10);

		btnAgregarPersona = new JButton("Agregar");
		btnAgregarPersona.setBounds(329, 236, 120, 23);
		panel.add(btnAgregarPersona);

		JLabel lblTipoDeContacto = new JLabel("Tipo de contacto");
		lblTipoDeContacto.setBounds(10, 200, 137, 14);
		panel.add(lblTipoDeContacto);

		JLabel lblDomicilio = new JLabel("DomicilioDTO");
		lblDomicilio.setBounds(10, 92, 72, 14);
		panel.add(lblDomicilio);

		JLabel lblFechaDeNacimiento = new JLabel("Fecha de nacimiento");
		lblFechaDeNacimiento.setBounds(10, 270, 150, 14);
		panel.add(lblFechaDeNacimiento);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(10, 240, 89, 14);
		panel.add(lblEmail);

		JLabel lblCalle = new JLabel("Calle");
		lblCalle.setBounds(114, 92, 46, 14);
		panel.add(lblCalle);

		JLabel lblAltura = new JLabel("Altura");
		lblAltura.setBounds(264, 92, 46, 14);
		panel.add(lblAltura);

		JLabel lblPiso = new JLabel("Piso");
		lblPiso.setBounds(114, 120, 46, 14);
		panel.add(lblPiso);

		JLabel lblDepto = new JLabel("Depto.");
		lblDepto.setBounds(264, 120, 89, 14);
		panel.add(lblDepto);

		JLabel lblLocalidad = new JLabel("Localidad");
		lblLocalidad.setBounds(10, 164, 89, 14);
		panel.add(lblLocalidad);

		fechaNac = new JDateChooser(new Date());
		fechaNac.setBounds(185, 270, 113, 20);
		panel.add(fechaNac);

		textEmail = new JTextField();
		textEmail.setBounds(149, 240, 89, 20);
		panel.add(textEmail);
		textEmail.setColumns(10);


		textCalle = new JTextField();
		textCalle.setBounds(149, 89, 89, 20);
		panel.add(textCalle);
		textCalle.setColumns(10);

		textAltura = new JTextField();
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
		textAltura.setBounds(346, 90, 46, 20);
		panel.add(textAltura);
		textAltura.setColumns(10);

		textPiso = new JTextField();
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
		textPiso.setBounds(149, 117, 89, 20);
		panel.add(textPiso);
		textPiso.setColumns(10);

		textDepto = new JTextField();
		textDepto.setBounds(356, 118, 46, 20);
		panel.add(textDepto);
		textDepto.setColumns(10);

		comboLocalidad = new JComboBox<>();
		comboLocalidad.setBounds(149, 160, 89, 20);
		panel.add(comboLocalidad);

		comboTipoContacto = new JComboBox<>();
		comboTipoContacto.setBounds(149, 200, 89, 20);
		panel.add(comboTipoContacto);

		this.setVisible(false);
	}

	public void mostrarVentana()
	{
		this.setVisible(true);
	}

	public JTextField getTxtNombre()
	{
		return txtNombre;
	}

	public JTextField getTxtApellido() {
		return txtApellido;
	}


	public JTextField getTxtTelefono()
	{
		return txtTelefono;
	}

	public JTextField getTxtCalle()
	{
		return textCalle;
	}

	public JTextField getTextCalle() {
		return textCalle;
	}

	public JTextField getTextAltura() {
		return textAltura;
	}

	public JTextField getTextPiso() {
		return textPiso;
	}

	public JTextField getTextDepto() {
		return textDepto;
	}

	public JComboBox<String> getComboLocalidad() {
		return comboLocalidad;
	}

	public JComboBox<String> getComboTipoContacto() {
		return comboTipoContacto;
	}

	public JButton getBtnAgregarPersona()
	{
		return btnAgregarPersona;
	}

	public PersonaDTO getPersona() {
		return persona;
	}

	public JTextField getTxtEmail()
	{
		return textEmail;
	}

	public JDateChooser getFechaNac()
	{
		return fechaNac;
	}

	public void setPersona(PersonaDTO persona) {
		this.persona = persona;
		if (persona != null) {
			this.txtNombre.setText(persona.getNombre());
			this.txtApellido.setText(persona.getApellido());
			this.txtTelefono.setText(persona.getTelefono());
			this.textCalle.setText(persona.getDomicilio().getCalle());
			this.textAltura.setText(persona.getDomicilio().getAltura());
			this.textPiso.setText(persona.getDomicilio().getPiso());
			this.textDepto.setText(persona.getDomicilio().getDepto());
			this.textEmail.setText(persona.getEmail());
			this.fechaNac.setDate(persona.getFechaNacimiento());
			btnAgregarPersona.setText("Editar");
		}else{
			this.txtNombre.setText("");
			this.txtApellido.setText("");
			this.txtTelefono.setText("");
			this.textCalle.setText("");
			this.textAltura.setText("");
			this.textPiso.setText("");
			this.textEmail.setText("");
			btnAgregarPersona.setText("Agregar");
		}
	}

	public void cerrar()
	{
		this.txtNombre.setText(null);
		this.txtTelefono.setText(null);
		this.textCalle.setText(null);
		this.textAltura.setText(null);
		this.textDepto.setText(null);
		this.textPiso.setText(null);
		this.textEmail.setText(null);
		this.dispose();
	}

}