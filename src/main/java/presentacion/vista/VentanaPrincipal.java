package presentacion.vista;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;

import persistencia.conexion.Conexion;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class VentanaPrincipal{
	private JFrame frame;
	private JMenuBar menuBar;
	private JMenuItem mntmAgregar;
	private JMenuItem mntmEditar;	
	private JMenuItem mntmBorrar;	
	private JMenuItem mntmReporte;
	private JMenuItem mntmAbmLocalidad; 
	private JMenuItem mntmAbmTipoDeContacto;
	
	private JPanel panel;
	private JScrollPane scrollPersonas; 
	private JTable tablaPersonas;
	private DefaultTableModel modelPersonas;
	private  String[] nombreColumnas = {"Nombre y apellido","Teléfono",
			"Calle","Altura","Piso","Depto","Localidad","Tipo Contacto", "Email","Fecha de Nacimiento"};

	public VentanaPrincipal()	{
		initialize();
	}

	private void initialize(){
		frame = new JFrame();
		frame.setTitle("Agenda");
		frame.setResizable(false);
		frame.setBounds(100, 100, 860, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		panel = new JPanel();
		panel.setBounds(0, 0, 844, 262);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		scrollPersonas = new JScrollPane();
		scrollPersonas.setBounds(10, 11, 824, 182);
		panel.add(scrollPersonas);

		modelPersonas = new DefaultTableModel(null,nombreColumnas);
		tablaPersonas = new JTable(modelPersonas);

		tablaPersonas.getColumnModel().getColumn(0).setPreferredWidth(103);
		tablaPersonas.getColumnModel().getColumn(0).setResizable(false);
		tablaPersonas.getColumnModel().getColumn(1).setPreferredWidth(100);
		tablaPersonas.getColumnModel().getColumn(1).setResizable(false);

		scrollPersonas.setViewportView(tablaPersonas);
		
		initializeMenus();		
	}

	private void initializeMenus() {
		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnArchivo = new JMenu("Archivo");
		menuBar.add(mnArchivo);
		
		mntmAgregar = new JMenuItem("Agregar contacto");
		mnArchivo.add(mntmAgregar);		
		mntmEditar = new JMenuItem("Editar contacto");
		mnArchivo.add(mntmEditar);
		mntmBorrar = new JMenuItem("Borrar contacto");
		mnArchivo.add(mntmBorrar);
		mntmReporte = new JMenuItem("Reporte");
		mnArchivo.add(mntmReporte);
		
		JMenu mnConfiguracion = new JMenu("Configuración");
		menuBar.add(mnConfiguracion);
		
		mntmAbmTipoDeContacto = new JMenuItem("ABM Tipo de Contacto");
		mnConfiguracion.add(mntmAbmTipoDeContacto);
		
		mntmAbmLocalidad = new JMenuItem("ABM de Localidades");
		mnConfiguracion.add(mntmAbmLocalidad);
	}

	public void show(){
		this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.frame.addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e) {
				int confirm = JOptionPane.showOptionDialog(
						null, "Estas seguro que quieres salir de la Agenda!?",
						"Confirmacion", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, null, null);
				if (confirm == 0) {
					Conexion.getConexion().cerrarConexion();
					System.exit(0);
				}
			}
		});
		this.frame.setVisible(true);
	}

	public JTable getTablaPersonas() {
		return tablaPersonas;
	}

	public void setTablaPersonas(JTable tablaPersonas) {
		this.tablaPersonas = tablaPersonas;
	}

	public JMenuItem getMntmAgregar() {
		return mntmAgregar;
	}

	public void setMntmAgregar(JMenuItem mntmAgregar) {
		this.mntmAgregar = mntmAgregar;
	}

	public JMenuItem getMntmEditar() {
		return mntmEditar;
	}

	public void setMntmEditar(JMenuItem mntmEditar) {
		this.mntmEditar = mntmEditar;
	}

	public JMenuItem getMntmBorrar() {
		return mntmBorrar;
	}

	public void setMntmBorrar(JMenuItem mntmBorrar) {
		this.mntmBorrar = mntmBorrar;
	}

	public JMenuItem getMntmAbmLocalidad() {
		return mntmAbmLocalidad;
	}

	public void setMntmAbmLocalidad(JMenuItem mntmAbmLocalidad) {
		this.mntmAbmLocalidad = mntmAbmLocalidad;
	}

	public JMenuItem getMntmAbmTipoDeContacto() {
		return mntmAbmTipoDeContacto;
	}

	public void setMntmAbmTipoDeContacto(JMenuItem mntmAbmTipoDeContacto) {
		this.mntmAbmTipoDeContacto = mntmAbmTipoDeContacto;
	}

	public JMenuItem getMntmReporte() {
		return mntmReporte;
	}

	public void setMntmReporte(JMenuItem mntmReporte) {
		this.mntmReporte = mntmReporte;
	}
}