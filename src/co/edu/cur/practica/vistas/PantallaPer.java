package co.edu.cur.practica.vistas;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import co.edu.cur.practica.implementaciones.TransaccionMYSQL;
import co.edu.cur.practica.implementaciones.TransaccionMYSQL2;
import co.edu.cur.practica.interfaces.ITransaccion;



public class PantallaPer {

	private JFrame frame;
	private JTextField txtDocumento;
	private JTextField txtNombre;

	/**
	 * Launch the application.
	 */
	
	PreparedStatement ps = null;
	ResultSet rs = null;
	int retorno = 0;
	private JTextField txtDireccion;
	
/**	private void limpiarcuadrotxt() {
		txtDocumento.setText(null);
		txtNombre.setText(null);
	}*/
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PantallaPer window = new PantallaPer();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PantallaPer() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setForeground(Color.BLACK);
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.getContentPane().setFont(new Font("Tahoma", Font.BOLD, 12));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblConexionBdMysql = new JLabel("MAESTRO PROFESORES");
		lblConexionBdMysql.setForeground(Color.LIGHT_GRAY);
		lblConexionBdMysql.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblConexionBdMysql.setBounds(146, 11, 185, 14);
		frame.getContentPane().add(lblConexionBdMysql);
		
		JLabel lblDocumento = new JLabel("Codigo");
		lblDocumento.setForeground(Color.LIGHT_GRAY);
		lblDocumento.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDocumento.setBounds(34, 56, 83, 14);
		frame.getContentPane().add(lblDocumento);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setForeground(Color.LIGHT_GRAY);
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNombre.setBounds(34, 102, 76, 14);
		frame.getContentPane().add(lblNombre);
		
		txtDocumento = new JTextField();
		txtDocumento.setForeground(Color.RED);
		txtDocumento.setBounds(120, 54, 86, 20);
		frame.getContentPane().add(txtDocumento);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(120, 99, 225, 20);
		frame.getContentPane().add(txtNombre);
		txtNombre.setColumns(10);
		
		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					ITransaccion transaccion= new TransaccionMYSQL();
					Connection con = transaccion.conectar();
					String sql = "select nombre,direccion from maestros where cod_profesor = ?";
					ps = con.prepareStatement(sql);
					ps.setString(1, txtDocumento.getText());
					 ResultSet rs = ps.executeQuery();
					if(rs.next()) {
						txtNombre.setText(rs.getString ("nombre"));
						txtDireccion.setText(rs.getString ("direccion"));
					}else {
						rs.close();
						ps.close();
						con.close();
						 transaccion= new TransaccionMYSQL2();
						 con = transaccion.conectar();
						 sql = "select nombre,direccion from maestros where cod_profesor = ?";
						ps = con.prepareStatement(sql);
						ps.setString(1, txtDocumento.getText());
						 rs = ps.executeQuery();
						if(rs.next()) {
							txtNombre.setText(rs.getString ("nombre"));
							txtDireccion.setText(rs.getString ("direccion"));
						}else {
						
						JOptionPane.showMessageDialog(null, "Documento de Persona no Encontrado");
						}
						rs.close();
						ps.close();
						con.close();
					}
					con.close();
			}catch (SQLException e1){
				JOptionPane.showMessageDialog(null,"Error al Acceder a BD");
				e1.printStackTrace();
			}
			}	
		});
		btnConsultar.setBounds(242, 53, 89, 23);
		frame.getContentPane().add(btnConsultar);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setBackground(new Color(0, 153, 153));
		btnAgregar.setForeground(new Color(0, 51, 0));
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ITransaccion transaccionMySQL = new TransaccionMYSQL();
					Connection con = transaccionMySQL.conectar();
					String sql = "insert into maestros(cod_profesor,nombre,direccion)values(?,?,?)";
					ps = con.prepareStatement(sql);
					ps.setString(1, txtDocumento.getText());
					ps.setString(2, txtNombre.getText());
					ps.setString(3, txtDireccion.getText());
					retorno = ps.executeUpdate();
					if(retorno >0 ) {
						JOptionPane.showMessageDialog(null,"Registro Agregado Correctamente" );
						
					ps.close();
					con.close();
					} else {
						JOptionPane.showMessageDialog(null,"No se Pudo Agregar el Registro" );
					}
					ITransaccion transaccionMySQL2 = new TransaccionMYSQL2();
					Connection con2 = transaccionMySQL2.conectar();
					String sql2 = "insert into maestros(cod_profesor,nombre,direccion)values(?,?,?)";
					ps = con2.prepareStatement(sql2);
					ps.setString(1, txtDocumento.getText());
					ps.setString(2, txtNombre.getText());
					ps.setString(3, txtDireccion.getText());
					retorno = ps.executeUpdate();
					if(retorno >0 ) {
						JOptionPane.showMessageDialog(null,"Registro Agregado Correctamente" );
						
					ps.close();
					con.close();
					} else {
						JOptionPane.showMessageDialog(null,"No se Pudo Agregar el Registro" );
					}
				}
				catch (SQLException e1){
					JOptionPane.showMessageDialog(null,"Error al Acceder a BD");
					e1.printStackTrace();
				}
			}

			
		});
		btnAgregar.setBounds(34, 176, 89, 23);
		frame.getContentPane().add(btnAgregar);
		
		JButton btnBorrar = new JButton("Borrar");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ITransaccion transaccionMySQL = new TransaccionMYSQL();
					Connection con = transaccionMySQL.conectar();
					String sql = ("delete from maestros where cod_profesor = ?");
					ps = con.prepareStatement(sql);
					ps.setString(1, txtDocumento.getText());
					retorno = ps.executeUpdate();
					if(retorno >0 ) {
						JOptionPane.showMessageDialog(null,"Registro Borrado Correctamente en base 1" );
					
					ps.close();
					con.close();
					
					} else {
						JOptionPane.showMessageDialog(null,"No se Pudo Borrar el Registro" );
					}
				}
				catch (SQLException e1){
					JOptionPane.showMessageDialog(null,"Error al Acceder a BD");
					e1.printStackTrace();
				}
				
				try {
					ITransaccion transaccionMySQL2 = new TransaccionMYSQL2();
					Connection con2 = transaccionMySQL2.conectar();
					String sql2 = ("delete from maestros where cod_profesor = ?");
					ps = con2.prepareStatement(sql2);
					ps.setString(1, txtDocumento.getText());
					retorno = ps.executeUpdate();
					if(retorno >0 ) {
						JOptionPane.showMessageDialog(null,"Registro Borrado Correctamente en base 2" );
						
					ps.close();
					con2.close();
					
					} else {
						JOptionPane.showMessageDialog(null,"No se Pudo Borrar el Registro  en la base 2" );
					}
				}
				catch (SQLException e1){
					JOptionPane.showMessageDialog(null,"Error al Acceder a BD");
					e1.printStackTrace();
				}
				
			}
			
		});
		btnBorrar.setBounds(320, 176, 89, 23);
		frame.getContentPane().add(btnBorrar);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				System.exit(0);
			}
		});
		btnSalir.setBounds(172, 216, 89, 23);
		frame.getContentPane().add(btnSalir);
		
		JButton button = new JButton("Actualizar");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ITransaccion transaccionMySQL = new TransaccionMYSQL();
					Connection con = transaccionMySQL.conectar();
					String sql = ("update maestros set nombre = ?,direccion=? where cod_profesor = ?");
					ps = con.prepareStatement(sql);					
					ps.setString(1, txtNombre.getText());
					ps.setString(2, txtDireccion.getText());
					ps.setString(3, txtDocumento.getText());
					retorno = ps.executeUpdate();
					if(retorno >0 ) {
						JOptionPane.showMessageDialog(null,"Registro Actualizado Correctamente en base 1" );
						
					ps.close();
					con.close();
					} else {
						JOptionPane.showMessageDialog(null,"No se Pudo Actualizar el Registro" );
					}
					
					ITransaccion transaccionMySQL2 = new TransaccionMYSQL2();
					Connection con2 = transaccionMySQL2.conectar();
					String sql2 = ("update maestros set nombre = ?,direccion=? where cod_profesor = ?");
					ps = con2.prepareStatement(sql2);					
					ps.setString(1, txtNombre.getText());
					ps.setString(2, txtDireccion.getText());
					ps.setString(3, txtDocumento.getText());
					retorno = ps.executeUpdate();
					if(retorno >0 ) {
						JOptionPane.showMessageDialog(null,"Registro Actualizado Correctamente en base 2" );
						
					ps.close();
					con2.close();
					} else {
						JOptionPane.showMessageDialog(null,"No se Pudo Actualizar el Registro" );
					}
				}
				catch (SQLException e1){
					JOptionPane.showMessageDialog(null,"Error al Acceder a BD");
					e1.printStackTrace();
				}
			}
		});
		button.setBounds(146, 176, 122, 23);
		frame.getContentPane().add(button);
		
		JLabel lblDireccion = new JLabel("direccion");
		lblDireccion.setForeground(Color.LIGHT_GRAY);
		lblDireccion.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDireccion.setBounds(34, 148, 76, 14);
		frame.getContentPane().add(lblDireccion);
		
		txtDireccion = new JTextField();
		txtDireccion.setColumns(10);
		txtDireccion.setBounds(120, 145, 225, 20);
		frame.getContentPane().add(txtDireccion);
	}
}