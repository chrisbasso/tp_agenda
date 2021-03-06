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

public class Vista
{
	private JFrame frame;
	private JTable tablaPersonas;
	private JButton btnAgregar;
	private JButton btnBorrar;
	private JButton btnReporte;
	private JButton btnEditar;
	private JButton btnAbmTipoDeContacto;
	private JButton btnAbmLocalidad;
	private JButton btnConexBaseDatos;
	private DefaultTableModel modelPersonas;
	private  String[] nombreColumnas = {"Nombre","Apellido", "Teléfono",
			"Calle","Altura","Piso","Depto","Localidad","Tipo Contacto", "Email","Fecha de Nacimiento"};

	public Vista()
	{
		super();
		initialize();
	}

	private void initialize()
	{
		frame = new JFrame();
		frame.setTitle("Agenda");
		frame.setResizable(false);
		frame.setBounds(100, 100, 860, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 844, 262);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JScrollPane spPersonas = new JScrollPane();
		spPersonas.setBounds(10, 11, 824, 182);
		panel.add(spPersonas);

		modelPersonas = new DefaultTableModel(null,nombreColumnas);
		tablaPersonas = new JTable(modelPersonas);

		tablaPersonas.getColumnModel().getColumn(0).setPreferredWidth(103);
		tablaPersonas.getColumnModel().getColumn(0).setResizable(false);
		tablaPersonas.getColumnModel().getColumn(1).setPreferredWidth(100);
		tablaPersonas.getColumnModel().getColumn(1).setResizable(false);

		spPersonas.setViewportView(tablaPersonas);

		btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(10, 228, 89, 23);
		panel.add(btnAgregar);

		btnEditar = new JButton("Editar");
		btnEditar.setBounds(109, 228, 89, 23);
		panel.add(btnEditar);

		btnBorrar = new JButton("Borrar");
		btnBorrar.setBounds(208, 228, 89, 23);
		panel.add(btnBorrar);

		btnReporte = new JButton("Reporte");
		btnReporte.setBounds(307, 228, 89, 23);
		panel.add(btnReporte);

		btnAbmTipoDeContacto = new JButton("ABM Tipo de Contacto");
		btnAbmTipoDeContacto.setBounds(406, 228, 139, 23);
		panel.add(btnAbmTipoDeContacto);

		btnAbmLocalidad = new JButton("ABM de Localidades");
		btnAbmLocalidad.setBounds(556, 228, 139, 23);
		panel.add(btnAbmLocalidad);
		
		btnConexBaseDatos = new JButton("Conexion BD");
		btnConexBaseDatos.setBounds(706, 228, 139, 23);
		panel.add(btnConexBaseDatos);
	}

	public void show()
	{
		this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.frame.addWindowListener(new WindowAdapter()
		{
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

	public JButton getBtnAgregar()
	{
		return btnAgregar;
	}

	public JButton getBtnEditar()
	{
		return btnEditar;
	}

	public JButton getBtnBorrar()
	{
		return btnBorrar;
	}

	public JButton getBtnReporte()
	{
		return btnReporte;
	}

	public JButton getBtnAbmTipoDeContacto() {
		return btnAbmTipoDeContacto;
	}

	public JButton getBtnAbmLocalidad() {
		return btnAbmLocalidad;
	}

	public DefaultTableModel getModelPersonas()
	{
		return modelPersonas;
	}

	public JTable getTablaPersonas()
	{
		return tablaPersonas;
	}

	public String[] getNombreColumnas()
	{
		return nombreColumnas;
	}

	public JButton getBtnConexBaseDatos() {
		return btnConexBaseDatos;
	}

}