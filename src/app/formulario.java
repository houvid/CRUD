package app;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.awt.event.ActionEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class formulario {

	private JFrame frame;
	private JTextField txtClave;
	private JTextField txtNombre;
	private JTextField txtTelefono;
	private JTextField txtDomicilio;
	private JLabel label;
	private JLabel lblTelefono;
	private JTextField txtGenero;
	private JTextField txtCorreo;
	private JLabel lblCorreo;
	private JLabel lblGenero;
	private JTextField txtId;
	

	/**
	 * Launch the application.
	 */
	PreparedStatement ps;
	ResultSet rs;
	private JButton btnLimpiar;
public static Connection getConnection() {
	Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con= (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/escuela", "root", "");
			
		} catch(Exception e){
			System.out.println(e);
		}
		return con;
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					formulario window = new formulario();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private void limpiarCajas() {
		
		txtClave.setText(null);
		txtNombre.setText(null);
		txtDomicilio.setText(null);
		txtTelefono.setText(null);
		txtCorreo.setText(null);
		txtGenero.setText(null);
		
		
	}

	/**
	 * Create the application.
	 */
	public formulario() {
		initialize();
		txtId.setVisible(false);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 783, 420);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblClave = new JLabel("Clave");
		lblClave.setBounds(212, 85, 46, 14);
		frame.getContentPane().add(lblClave);	
		txtClave = new JTextField();
		txtClave.setBounds(282, 82, 249, 20);
		txtClave.setText("clave");
		frame.getContentPane().add(txtClave);
		txtClave.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(212, 118, 37, 14);
		frame.getContentPane().add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(282, 115, 358, 20);
		txtNombre.setText("nombre");
		frame.getContentPane().add(txtNombre);
		txtNombre.setColumns(10);
		
		txtTelefono = new JTextField();
		txtTelefono.setBounds(282, 172, 358, 20);
		txtTelefono.setText("telefono");
		txtTelefono.setColumns(10);
		frame.getContentPane().add(txtTelefono);
		
		txtDomicilio = new JTextField();
		txtDomicilio.setBounds(282, 141, 358, 20);
		txtDomicilio.setText("domicilio");
		txtDomicilio.setColumns(10);
		frame.getContentPane().add(txtDomicilio);
		
		label = new JLabel("Domicilio");
		label.setBounds(218, 144, 40, 14);
		frame.getContentPane().add(label);
		
		lblTelefono = new JLabel("Telefono");
		lblTelefono.setBounds(212, 175, 42, 14);
		frame.getContentPane().add(lblTelefono);
		
		txtGenero = new JTextField();
		txtGenero.setBounds(282, 234, 358, 20);
		txtGenero.setText("genero");
		txtGenero.setColumns(10);
		frame.getContentPane().add(txtGenero);
		
		txtCorreo = new JTextField();
		txtCorreo.setBounds(282, 203, 358, 20);
		txtCorreo.setText("correo");
		txtCorreo.setColumns(10);
		frame.getContentPane().add(txtCorreo);
		
		lblCorreo = new JLabel("correo");
		lblCorreo.setBounds(212, 206, 60, 14);
		frame.getContentPane().add(lblCorreo);
		
		lblGenero = new JLabel("genero");
		lblGenero.setBounds(212, 237, 34, 14);
		frame.getContentPane().add(lblGenero);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(212, 281, 89, 23);
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection con = null;
				try {
					con=getConnection();
					ps = con.prepareStatement("INSERT INTO PERSONAS (CLAVE,NOMBRE,DOMICILIO,TELEFONO,CORREO_ELECTRONICO,GENERO)VALUES(?,?,?,?,?,?);");
					ps.setString(1, txtClave.getText());
					ps.setString(2, txtNombre.getText());
					ps.setString(3, txtDomicilio.getText());
					ps.setString(4, txtTelefono.getText());
					ps.setString(5, txtCorreo.getText());
					ps.setString(6, txtGenero.getText());
					int res= ps.executeUpdate();
					
					if (res> 0) {
						JOptionPane.showMessageDialog(null,"Persona Guardada");
						limpiarCajas();
					}else{
						JOptionPane.showMessageDialog(null,"error al Guardar");
						limpiarCajas();
					}
					con.close();
				} catch (Exception e) {
					System.out.println(e);
				}
			}
		});
		frame.getContentPane().add(btnGuardar);
		
		JButton btnModificar = new JButton("modificar");
		btnModificar.setBounds(335, 281, 89, 23);
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection con = null;
				try {
					con=getConnection();
					ps = con.prepareStatement("UPDATE PERSONAS SET CLAVE=?,NOMBRE=?,DOMICILIO=?,TELEFONO=?,CORREO_ELECTRONICO=?,GENERO=? WHERE ID=?;");
					ps.setString(1, txtClave.getText());
					ps.setString(2, txtNombre.getText());
					ps.setString(3, txtDomicilio.getText());
					ps.setString(4, txtTelefono.getText());
					ps.setString(5, txtCorreo.getText());
					ps.setString(6, txtGenero.getText());
					ps.setString(7, txtId.getText());
					int res= ps.executeUpdate();
					
					if (res> 0) {
						JOptionPane.showMessageDialog(null,"Persona modificada");
						limpiarCajas();
					}else{
						JOptionPane.showMessageDialog(null,"error al modificada");
						limpiarCajas();
					}
					con.close();
				} catch (Exception e) {
					System.out.println(e);
				}
			}
		});
		frame.getContentPane().add(btnModificar);
		
		JButton btnEliminar = new JButton("eliminar");
		btnEliminar.setBounds(434, 281, 107, 23);
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection con = null;
				try {
					con=getConnection();
					ps = con.prepareStatement("DELETE FROM PERSONAS WHERE ID=?;");					
					ps.setString(1, txtId.getText());
					int res= ps.executeUpdate();
					
					if (res> 0) {
						JOptionPane.showMessageDialog(null,"Persona ELIMINADA");
						limpiarCajas();
					}else{
						JOptionPane.showMessageDialog(null,"error al ELIMINAR");
						limpiarCajas();
					}
					con.close();
				} catch (Exception e1) {
					System.out.println(e1);
				}
			}
		});
		frame.getContentPane().add(btnEliminar);
		
		txtId = new JTextField();
		txtId.setBounds(424, 331, 86, 20);
		txtId.setEnabled(false);
		txtId.setText("id");
		
		JButton btnConsular1 = new JButton("consular");
		btnConsular1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection con = null;
				try {
					con=getConnection();
					ps=con.prepareStatement("SELECT * FROM PERSONAS WHERE CLAVE=? ");
					ps.setString(1, txtClave.getText());
					
					rs = ps.executeQuery();
					if (rs.next()) {
						txtId.setText(rs.getString("id"));
						txtNombre.setText(rs.getString("nombre"));
						txtDomicilio.setText(rs.getString("domicilio"));
						txtTelefono.setText(rs.getString("telefono"));
						txtCorreo.setText(rs.getString("correo_electronico"));
						txtGenero.setText(rs.getString("genero"));
					}else {
						JOptionPane.showMessageDialog(null,"No existe registro con esta clave");
						limpiarCajas();
					}
					
				} catch (Exception e) {
					System.out.println(e);
				}
				
			}
		});
		btnConsular1.setBounds(555, 80, 73, 23);
		frame.getContentPane().add(btnConsular1);
		
		txtId = new JTextField();
		txtId.setEnabled(false);
		txtId.setText("id");
		txtId.setBounds(376, 51, 86, 20);
		frame.getContentPane().add(txtId);
		txtId.setColumns(10);
		
		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					limpiarCajas();
				} catch (Exception e2) {
					
				}
			}
		});
		btnLimpiar.setBounds(551, 281, 89, 23);
		frame.getContentPane().add(btnLimpiar);
	}
}
