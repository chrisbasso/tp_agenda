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
		
		JMenuItem mntmAgregar = new JMenuItem("Agregar contacto");
		mnArchivo.add(mntmAgregar);		
		JMenuItem mntmEditar = new JMenuItem("Editar contacto");
		mnArchivo.add(mntmEditar);
		JMenuItem mntmBorrar = new JMenuItem("Borrar contacto");
		mnArchivo.add(mntmBorrar);
		JMenuItem mntmReporte = new JMenuItem("Reporte");
		mnArchivo.add(mntmReporte);
		
		JMenu mnConfiguracion = new JMenu("Configuración");
		menuBar.add(mnConfiguracion);
		
		JMenuItem mntmAbmTipoDeContacto = new JMenuItem("ABM Tipo de Contacto");
		mnConfiguracion.add(mntmAbmTipoDeContacto);
		
		JMenuItem mntmAbmLocalidad = new JMenuItem("ABM de Localidades");
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
	
}