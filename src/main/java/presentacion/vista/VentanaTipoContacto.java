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
	private  String[] nombreColumnas = {"Tipo de Contacto"};
	private JTable tablaTipoContactos;
	private JTextField txtTipoContacto;
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
		btnEditarTipoContacto.setEnabled(false);
		btnEditarTipoContacto.setBounds(220, 112, 89, 23);
		panel.add(btnEditarTipoContacto);
		
		btnBorrar = new JButton("Borrar");
		btnBorrar.setEnabled(false);
		btnBorrar.setBounds(220, 78, 89, 23);
		panel.add(btnBorrar);
		
		txtTipoContacto = new JTextField();
		txtTipoContacto.setBounds(220, 15, 89, 20);
		panel.add(txtTipoContacto);
		txtTipoContacto.setColumns(10);		
	}
	
	public void inicializarTabla() {
		modelTipoContactos.setRowCount(0); //Para vaciar la tabla
		modelTipoContactos.setColumnCount(0);
		modelTipoContactos.setColumnIdentifiers(nombreColumnas);
	}
	
	public void agregarTipoContactoATabla(Object[] fila) {
		modelTipoContactos.addRow(fila);
	}

	public void editarTipoContactoATabla(Object[] fila, int indice) {
		modelTipoContactos.setValueAt(fila, indice, 0);
	}
	public void quitarTipoContactoDeTabla(int numeroFila) {
		modelTipoContactos.removeRow(numeroFila);
	}

	public int obtenerFilaSeleccionada() {
		return tablaTipoContactos.getSelectedRow();		
	}

	public void cargarTipoContacto(String texto) {
		txtTipoContacto.setText(texto);
	}
	
	public void mostrar(){
		setVisible(true);
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
	
	public String getTxtTipoContacto() {
		return txtTipoContacto.getText();
	}	

	public void cerrar(){
		dispose();
	}
}