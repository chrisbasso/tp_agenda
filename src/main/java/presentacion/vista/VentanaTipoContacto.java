package presentacion.vista;

import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class VentanaTipoContacto extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static VentanaTipoContacto INSTANCE;
	private DefaultTableModel modelTipoContactos;
	private  String[] nombreColumnas = {"Nombre de Tipo de Contacto"};
	private JTable tablaTipoContactos;
	private JTextField txtAgregarTipoContacto;
	private JButton btnAgregarTipoContacto;
	private JButton btnEditarTipoContacto;
	private JButton btnBorrar;
	public static VentanaTipoContacto getInstance()
	{
		if(INSTANCE == null)
			return new VentanaTipoContacto();
		else
			return INSTANCE;
	}

	private VentanaTipoContacto() {
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
		
		btnAgregarTipoContacto = new JButton("Agregar");
		btnAgregarTipoContacto.setBounds(220, 44, 89, 23);
		panel.add(btnAgregarTipoContacto);
		
		btnEditarTipoContacto = new JButton("Editar");
		btnEditarTipoContacto.setBounds(220, 112, 89, 23);
		panel.add(btnEditarTipoContacto);
		
		btnBorrar = new JButton("Borrar");
		btnBorrar.setBounds(220, 78, 89, 23);
		panel.add(btnBorrar);
		
		txtAgregarTipoContacto = new JTextField();
		txtAgregarTipoContacto.setBounds(220, 15, 89, 20);
		panel.add(txtAgregarTipoContacto);
		txtAgregarTipoContacto.setColumns(10);
		
	}

	public void mostrarVentana(){
		this.setVisible(true);
	}

	public JTextField getTxtAgregarTipoContacto() {
		return txtAgregarTipoContacto;
	}

	public void setTxtAgregarTipoContacto(JTextField txtAgregarTipoContacto) {
		this.txtAgregarTipoContacto = txtAgregarTipoContacto;
	}

	public JButton getBtnAgregarTipoContacto() {
		return btnAgregarTipoContacto;
	}

	public JButton getBtnEditarTipoContacto() {
		return btnEditarTipoContacto;
	}

	public JButton getBtnBorrar() {
		return btnBorrar;
	}

	public DefaultTableModel getModelTipoContactos() {
		return modelTipoContactos;
	}
	
	public JTable getTablaTipoContactos(){
		return tablaTipoContactos;
	}

	public String[] getNombreColumnas() {
		return nombreColumnas;
	}
	
	public void cerrar() {
		this.txtAgregarTipoContacto.setText(null);
		this.dispose();
	}
	
	/*
	 * public void centrarVentana( JFrame ventanaPadre) {
	 * this.setLocationRelativeTo(ventanaPadre); }
	 */
}
