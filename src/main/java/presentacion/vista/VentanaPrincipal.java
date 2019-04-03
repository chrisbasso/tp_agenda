package presentacion.vista;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class VentanaPrincipal extends JFrame{
	private static final long serialVersionUID = 1L;
	private static VentanaPrincipal INSTANCIA;
	private JMenuBar menuBar;
	private JMenuItem mntmAgregar;
	private JMenuItem mntmEditar;	
	private JMenuItem mntmBorrar;	
	private JMenuItem mntmReporte;
	private JMenuItem mntmAbmLocalidad; 
	private JMenuItem mntmAbmTipoDeContacto;
	private JMenuItem mntmBaseDeDatos;
	private JPanel panel;
	private JScrollPane scrollPersonas; 
	private JTable tablaPersonas;
	private DefaultTableModel modelPersonas;
	private  String[] nombreColumnas = {"Nombre","Apellido", "Teléfono",
			"Calle","Altura","Piso","Depto","Localidad","Tipo Contacto", "Email","Fecha de Nacimiento"};

	public static VentanaPrincipal getInstance(){
		if(INSTANCIA == null) {
			INSTANCIA = new VentanaPrincipal(); 
			return INSTANCIA;
		}else {
			return INSTANCIA;
		}
	}
	
	private VentanaPrincipal()	{		
		setTitle("Agenda");
		setResizable(false);
		setBounds(100, 100, 860, 300);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		getContentPane().setLayout(null);

		panel = new JPanel();
		panel.setBounds(0, 0, 844, 262);
		getContentPane().add(panel);
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
		cargarMenues();		
	}

	private void cargarMenues() {
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnArchivo = new JMenu("Archivo");
		menuBar.add(mnArchivo);
		
		mntmAgregar = new JMenuItem("Agregar contacto");
		mnArchivo.add(mntmAgregar);		
		mntmEditar = new JMenuItem("Editar contacto");
		mnArchivo.add(mntmEditar);
		mntmBorrar = new JMenuItem("Borrar contacto");
		mnArchivo.add(mntmBorrar);
				
		JMenu mnReportes = new JMenu("Reportes");
		menuBar.add(mnReportes);
		
		mntmReporte = new JMenuItem("Por apellido y dominio email");
		mnReportes.add(mntmReporte);
		
		JMenu mnConfiguracion = new JMenu("Configuración");
		menuBar.add(mnConfiguracion);
		
		mntmAbmTipoDeContacto = new JMenuItem("ABM Tipo de Contacto");
		mnConfiguracion.add(mntmAbmTipoDeContacto);
		
		mntmAbmLocalidad = new JMenuItem("ABM de Localidades");
		mnConfiguracion.add(mntmAbmLocalidad);
		
		mntmBaseDeDatos = new JMenuItem("Configurar conexion a la base de datos");
		mnConfiguracion.add(mntmBaseDeDatos);

	}
	
	public void inicializarTabla() {
		modelPersonas.setRowCount(0); //Para vaciar la tabla
		modelPersonas.setColumnCount(0);
		modelPersonas.setColumnIdentifiers(nombreColumnas);
	}
	
	public void agregarContactoATabla(Object[] fila) {
		modelPersonas.addRow(fila);
	}
	
	public void quitarContactoDeTabla(int numeroFila) {
		modelPersonas.removeRow(numeroFila);
	}

	public int obtenerFilaSeleccionada() {
		return tablaPersonas.getSelectedRow();		
	}
	
	public void mostrar(){		
		setVisible(true);
	}
	
	public void cerrar(){
		dispose();
	}

	public JMenuItem getMntmAgregar() {
		return mntmAgregar;
	}

	public JMenuItem getMntmEditar() {
		return mntmEditar;
	}

	public JMenuItem getMntmBorrar() {
		return mntmBorrar;
	}

	public JMenuItem getMntmAbmLocalidad() {
		return mntmAbmLocalidad;
	}

	public JMenuItem getMntmAbmTipoDeContacto() {
		return mntmAbmTipoDeContacto;
	}

	public JMenuItem getMntmReporte() {		
		return mntmReporte;
	}
	
	public JMenuItem getMntmBaseDeDatos() {
		return mntmBaseDeDatos;
	}
}