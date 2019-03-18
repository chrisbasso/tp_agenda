package presentacion.vista;

import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;
public class VentanaLocalidad extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static VentanaLocalidad INSTANCE;
	private DefaultTableModel modelTipoContactos;
	private  String[] nombreColumnas = {"Nombre de Tipo de Contacto"};
	private JTable tablaTipoContactos;
	private JTextField txtAgregarTipoContacto;
	private JButton btnAgregarLocalidad;
	private JButton btnEditarLocalidad;
	private JButton btnBorrar;
	public static VentanaLocalidad getInstance()
	{
		if(INSTANCE == null)
			return new VentanaLocalidad();
		else
			return INSTANCE;
	}

	private VentanaLocalidad() {
		super();
		setTitle("ABM Tipo de Contacto");
		this.setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 364, 236);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 325, 173);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JScrollPane spTipoContactos = new JScrollPane();
		spTipoContactos.setBounds(10, 11, 200, 151);
		panel.add(spTipoContactos);

		modelTipoContactos = new DefaultTableModel(null,nombreColumnas);
		tablaTipoContactos = new JTable(modelTipoContactos);

		tablaTipoContactos.getColumnModel().getColumn(0).setPreferredWidth(103);
		tablaTipoContactos.getColumnModel().getColumn(0).setResizable(false);

		spTipoContactos.setViewportView(tablaTipoContactos);
		
		btnAgregarLocalidad = new JButton("Agregar");
		btnAgregarLocalidad.setBounds(220, 44, 89, 23);
		panel.add(btnAgregarLocalidad);
		
		btnEditarLocalidad = new JButton("Editar");
		btnEditarLocalidad.setBounds(220, 112, 89, 23);
		panel.add(btnEditarLocalidad);
		
		btnBorrar = new JButton("Borrar");
		btnBorrar.setBounds(220, 78, 89, 23);
		panel.add(btnBorrar);
		
		txtAgregarTipoContacto = new JTextField();
		txtAgregarTipoContacto.setBounds(220, 15, 89, 20);
		panel.add(txtAgregarTipoContacto);
		txtAgregarTipoContacto.setColumns(10);
		
	}

	public void mostrarVentana()
	{
		this.setVisible(true);
	}

	public JTextField getTxtAgregarLocalidad() {
		return txtAgregarTipoContacto;
	}

	public void setTxtAgregarLocalidad(JTextField txtAgregarTipoContacto) {
		this.txtAgregarTipoContacto = txtAgregarTipoContacto;
	}

	public JButton getBtnAgregarLocalidad() {
		return btnAgregarLocalidad;
	}

	public JButton getBtnEditarLocalidad() {
		return btnEditarLocalidad;
	}

	public JButton getBtnBorrar() {
		return btnBorrar;
	}

	public DefaultTableModel getModelTipoContactos() 
	{
		return modelTipoContactos;
	}
	
	public JTable getTablaTipoContactos()
	{
		return tablaTipoContactos;
	}

	public String[] getNombreColumnas() 
	{
		return nombreColumnas;
	}
	
	public void cerrar()
	{
		this.txtAgregarTipoContacto.setText(null);
		this.dispose();
	}
}
