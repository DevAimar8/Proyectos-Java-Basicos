package PANELES;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import Conexion.ConexionMySQL;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import java.awt.Color;

public class Registro extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel FONDO;
	private JTextField CampoNombre;
	private JTextField CampoApellidos;
	private JTextField CampoEmail;
	private JPasswordField CampoContrasena;
	private JComboBox<String> comboBox;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Registro frame = new Registro();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Registro() {
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 464, 669);
		
		FONDO = new JPanel();
		FONDO.setBackground(new Color(255, 255, 255));
		FONDO.setForeground(new Color(0, 0, 0));
		FONDO.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(FONDO);
		FONDO.setLayout(null);
		
		
		comboBox = new JComboBox<String>();
		comboBox.setBounds(167, 424, 210, 21);
		comboBox.addItem("Funcionario");
		comboBox.addItem("Familiar");
		FONDO.add(comboBox);
       
		
		JButton BotonRegistro = new JButton("Registrarse");
		BotonRegistro.setBorder(new LineBorder(new Color(0, 255, 0), 2)); // Cambio el color del borde al mismo color del cuadro con un ancho de 2 píxeles
		BotonRegistro.setForeground(new Color(255, 255, 255));
		BotonRegistro.setBackground(new Color(0, 128, 0));
		BotonRegistro.setFont(new Font("Tahoma", Font.PLAIN, 18));
		BotonRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				registrarUsuario();
			}
		});
		BotonRegistro.setBounds(177, 455, 143, 56);
		FONDO.add(BotonRegistro);
		
		CampoNombre = new JTextField();
		CampoNombre.setBounds(167, 251, 210, 20);
		FONDO.add(CampoNombre);
		CampoNombre.setColumns(10);
		
		CampoApellidos = new JTextField();
		CampoApellidos.setColumns(10);
		CampoApellidos.setBounds(167, 297, 210, 20);
		FONDO.add(CampoApellidos);
		
		CampoEmail = new JTextField();
		CampoEmail.setColumns(10);
		CampoEmail.setBounds(167, 381, 210, 20);
		FONDO.add(CampoEmail);
		
		CampoContrasena = new JPasswordField();
		CampoContrasena.setBounds(167, 339, 210, 20);
		FONDO.add(CampoContrasena);
		
		JLabel EtiquetaNombre = new JLabel("Nombre");
		EtiquetaNombre.setForeground(new Color(0, 128, 0));
		EtiquetaNombre.setFont(new Font("Tahoma", Font.BOLD, 14));
		EtiquetaNombre.setBounds(59, 243, 106, 30);
		FONDO.add(EtiquetaNombre);
		
		JLabel EtiquetaApellidos = new JLabel("Apellidos");
		EtiquetaApellidos.setForeground(new Color(0, 128, 0));
		EtiquetaApellidos.setFont(new Font("Tahoma", Font.BOLD, 14));
		EtiquetaApellidos.setBounds(59, 289, 106, 30);
		FONDO.add(EtiquetaApellidos);
		
		JLabel EtiquetaContrasena = new JLabel("Contraseña");
		EtiquetaContrasena.setForeground(new Color(0, 128, 0));
		EtiquetaContrasena.setFont(new Font("Tahoma", Font.BOLD, 14));
		EtiquetaContrasena.setBounds(59, 331, 106, 30);
		FONDO.add(EtiquetaContrasena);
		
		JLabel EtiquetaEmail = new JLabel("Usuario");
		EtiquetaEmail.setForeground(new Color(0, 128, 0));
		EtiquetaEmail.setFont(new Font("Tahoma", Font.BOLD, 14));
		EtiquetaEmail.setBounds(59, 373, 106, 30);
		FONDO.add(EtiquetaEmail);
		
		JLabel CaracterUsuario2 = new JLabel("");
		CaracterUsuario2.setIcon(new ImageIcon("C:\\\\Users\\\\acer\\\\Desktop\\\\UNI AIMAR\\\\2º DAM\\\\Sistemas de gestión empresarial\\\\LACANASTA\\\\lgo-removebg-preview.png\\\\\\\\\\\\"));
		CaracterUsuario2.setBounds(22, 267, 39, 36);
		FONDO.add(CaracterUsuario2);
		
		JLabel EtiquetaTipoUsuario = new JLabel("Nivel ");
		EtiquetaTipoUsuario.setForeground(new Color(0, 128, 0));
		EtiquetaTipoUsuario.setFont(new Font("Tahoma", Font.BOLD, 14));
		EtiquetaTipoUsuario.setBounds(59, 413, 106, 38);
		FONDO.add(EtiquetaTipoUsuario);
		
	
		JLabel FOTO_FONDO = new JLabel("");
		FOTO_FONDO.setBackground(new Color(127, 255, 212));
		FOTO_FONDO.setFont(new Font("Tahoma", Font.PLAIN, 12));
		FOTO_FONDO.setIcon(new ImageIcon("C:\\Users\\acer\\Desktop\\UNI AIMAR\\2º DAM\\Desarrollo de interfaces\\LACANASTA\\fondo.png"));
		FOTO_FONDO.setBounds(-11, -204, 1390, 1137);
		FONDO.add(FOTO_FONDO);
		
	}
	
	private void registrarUsuario() {
		String Nombre_Usuario = CampoNombre.getText();
        String Apellidos_Usuario = CampoApellidos.getText();
        String Email_Usuario = CampoEmail.getText();
        String contrasena = new String(CampoContrasena.getPassword());
        String tipoUsuario = (String) comboBox.getSelectedItem();
        

        if (Nombre_Usuario.isEmpty() || Apellidos_Usuario.isEmpty() || Email_Usuario.isEmpty() || contrasena.isEmpty()) 
        {
            JOptionPane.showMessageDialog(this, "Por favor, completa todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
           return;
        }

        ConexionMySQL conexion = new ConexionMySQL("root","test","freedb_VAARI_APP");
        try {
			conexion.conectar();
			String consulta = "INSERT INTO Usuarios (Nombre_Usuario, Apellidos_Usuario, Email_Usuario, contrasena, tipoUsuario)" +
								"VALUES ('" + Nombre_Usuario + "', '" + Apellidos_Usuario + "',  '" + Email_Usuario + "', '" + contrasena + "', '" + tipoUsuario + "')";
			conexion.ejecutarInsertDeleteUpdate(consulta);
			JOptionPane.showMessageDialog(this, "Registro exitoso. ¡Bienvenido!", "Registro Exitoso", JOptionPane.INFORMATION_MESSAGE);
			InicioSesion menu = new InicioSesion();
            menu.setVisible(true);
            dispose();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
        	JOptionPane.showMessageDialog(this, "Error al registrar el usuario. Inténtalo de nuevo.", "Error", JOptionPane.ERROR_MESSAGE);

		}
        finally 
        {
        	try 
        	{
				conexion.desconectar();
			} 
        	catch (SQLException e) 
        	{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
	}
}