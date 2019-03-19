package presentacion.vista;

import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;
public class VentanaLocalidad extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static VentanaLocalidad INSTANCE;
	private DefaultTableModel modelLocalidades;
	private  String[] nombreColumnas = {"Localidad"};
	private JTable tablaLocalidades;
	private JTextField txtAgregarLocalidad;
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
		setTitle("ABM Localidades");
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
		
		JScrollPane spLocalidades = new JScrollPane();
		spLocalidades.setBounds(10, 11, 200, 151);
		panel.add(spLocalidades);

		modelLocalidades = new DefaultTableModel(null,nombreColumnas);
		tablaLocalidades = new JTable(modelLocalidades);

		tablaLocalidades.getColumnModel().getColumn(0).setPreferredWidth(103);
		tablaLocalidades.getColumnModel().getColumn(0).setResizable(false);

		spLocalidades.setViewportView(tablaLocalidades);
		
		btnAgregarLocalidad = new JButton("Agregar");
		btnAgregarLocalidad.setBounds(220, 44, 89, 23);
		panel.add(btnAgregarLocalidad);
		
		btnEditarLocalidad = new JButton("Editar");
		btnEditarLocalidad.setBounds(220, 112, 89, 23);
		panel.add(btnEditarLocalidad);
		
		btnBorrar = new JButton("Borrar");
		btnBorrar.setBounds(220, 78, 89, 23);
		panel.add(btnBorrar);
		
		txtAgregarLocalidad = new JTextField();
		txtAgregarLocalidad.setBounds(220, 15, 89, 20);
		panel.add(txtAgregarLocalidad);
		txtAgregarLocalidad.setColumns(10);
		
	}

	public void mostrarVentana()
	{
		this.setVisible(true);
	}

	public JTextField getTxtAgregarLocalidad() {
		return txtAgregarLocalidad;
	}

	public void setTxtAgregarLocalidad(JTextField txtAgregarTipoContacto) {
		this.txtAgregarLocalidad = txtAgregarTipoContacto;
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

	public DefaultTableModel getModelLocalidades() 
	{
		return modelLocalidades;
	}
	
	public JTable getTablaLocalidades()
	{
		return tablaLocalidades;
	}

	public String[] getNombreColumnas() 
	{
		return nombreColumnas;
	}
	
	public void cerrar()
	{
		this.txtAgregarLocalidad.setText(null);
		this.dispose();
	}
}
