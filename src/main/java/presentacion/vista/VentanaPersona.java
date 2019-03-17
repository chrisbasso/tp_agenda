package presentacion.vista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class VentanaPersona extends JFrame
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtTelefono;
	private JButton btnAgregarPersona;
	private JTextField textCalle;
	private JTextField textAltura;
	private JTextField textPiso;
	private JTextField textDepto;
	private JComboBox<String> comboLocalidad;
	private JComboBox<String> comboTipoContacto;
	private static VentanaPersona INSTANCE;

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
		setBounds(100, 100, 404, 365);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 373, 310);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNombreYApellido = new JLabel("Nombre y apellido");
		lblNombreYApellido.setBounds(10, 11, 113, 14);
		panel.add(lblNombreYApellido);

		JLabel lblTelfono = new JLabel("Teléfono");
		lblTelfono.setBounds(10, 52, 113, 14);
		panel.add(lblTelfono);

		txtNombre = new JTextField();
		txtNombre.setBounds(149, 8, 164, 20);
		panel.add(txtNombre);
		txtNombre.setColumns(10);

		txtTelefono = new JTextField();
		txtTelefono.setBounds(149, 49, 164, 20);
		panel.add(txtTelefono);
		txtTelefono.setColumns(10);

		btnAgregarPersona = new JButton("Agregar");
		btnAgregarPersona.setBounds(272, 270, 89, 23);
		panel.add(btnAgregarPersona);

		JLabel lblTipoDeContacto = new JLabel("Tipo de contacto");
		lblTipoDeContacto.setBounds(10, 200, 137, 14);
		panel.add(lblTipoDeContacto);

		JLabel lblDomicilio = new JLabel("Domicilio");
		lblDomicilio.setBounds(10, 92, 72, 14);
		panel.add(lblDomicilio);

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
		lblDepto.setBounds(264, 120, 46, 14);
		panel.add(lblDepto);

		JLabel lblLocalidad = new JLabel("Localidad");
		lblLocalidad.setBounds(10, 164, 89, 14);
		panel.add(lblLocalidad);

		textCalle = new JTextField();
		textCalle.setBounds(149, 89, 89, 20);
		panel.add(textCalle);
		textCalle.setColumns(10);

		textAltura = new JTextField();

		textAltura.setBounds(305, 89, 46, 20);
		panel.add(textAltura);
		textAltura.setColumns(10);

		textPiso = new JTextField();

		textPiso.setBounds(149, 117, 89, 20);
		panel.add(textPiso);
		textPiso.setColumns(10);

		textDepto = new JTextField();
		textDepto.setBounds(305, 117, 46, 20);
		panel.add(textDepto);
		textDepto.setColumns(10);

		comboLocalidad = new JComboBox<>();
		comboLocalidad.setBounds(149, 161, 89, 20);
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

	public void cerrar()
	{
		this.txtNombre.setText(null);
		this.txtTelefono.setText(null);
		this.textCalle.setText(null);
		this.textAltura.setText(null);
		this.textDepto.setText(null);
		this.textPiso.setText(null);
		this.dispose();
	}

}