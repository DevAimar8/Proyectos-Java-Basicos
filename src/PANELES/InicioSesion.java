package PANELES;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import Conexion.ConexionMySQL;

public class InicioSesion extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel FONDO;
    private JTextField Campo_Email;
    private JPasswordField CampoContrasena;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    InicioSesion frame = new InicioSesion();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public InicioSesion() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 513, 669);

        FONDO = new JPanel();
        FONDO.setBackground(new Color(152, 251, 152));
        FONDO.setForeground(new Color(0, 0, 0));
        FONDO.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(FONDO);
        FONDO.setLayout(null);

        JButton BotonRegistro = new JButton("Iniciar Sesión");
        BotonRegistro.setBorder(new LineBorder(Color.WHITE, 2)); 
        BotonRegistro.setForeground(new Color(255, 255, 255));
        BotonRegistro.setBackground(new Color(192, 192, 192));
        BotonRegistro.setFont(new Font("Tahoma", Font.PLAIN, 18));
        BotonRegistro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) 
            {
            	ConexionMySQL conexion = new ConexionMySQL("root","test","freedb_VAARI_APP");
                try 
                {
                	conexion.conectar();
                    String consulta = "SELECT Email_Usuario, contrasena FROM Usuarios WHERE Email_Usuario = '" + 
                    					Campo_Email.getText() +  "' AND contrasena = '" + new String(CampoContrasena.getPassword()) + "'";
                    ResultSet rset = conexion.ejecutarSelect(consulta);
                    
                    
                    if (rset.next())
                    {
                        // Se encontró un usuario con las credenciales proporcionadas
                        JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso. ¡Bienvenido!", "Inicio de Sesión Exitoso", JOptionPane.INFORMATION_MESSAGE);
                        MenuPrincipal menu = new MenuPrincipal();
                        menu.setVisible(true);
                        dispose(); // Cierra la ventana de inicio de sesión
                    }
                    else
                    {
                        // No se encontró un usuario con las credenciales proporcionadas
                        JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos.", "Error de Inicio de Sesión", JOptionPane.ERROR_MESSAGE);
                    }
                } 
                catch (SQLException ex)
                {
                    // Error al ejecutar la consulta SQL
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al iniciar sesión. Inténtalo de nuevo.", "Error de Inicio de Sesión", JOptionPane.ERROR_MESSAGE);
                } 
                finally 
                {
                    // Desconectar la conexión a la base de datos
                    try
                    {
                        conexion.desconectar();
                    } 
                    catch (SQLException ex)
                    {
                        ex.printStackTrace();
                    }
                }
            }
        });
        
        JButton BOTON_Registro = new JButton("Crear cuenta");
        BOTON_Registro.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) 
        	{
        		Registro registronuevo = new Registro();
				registronuevo.setVisible(true);
				dispose();
        	}
        });
        BOTON_Registro.setForeground(new Color(0, 128, 64));
        BOTON_Registro.setBorder(new LineBorder(Color.WHITE, 2));
        BOTON_Registro.setBackground(new Color(192, 192, 192));
        BOTON_Registro.setBounds(183, 436, 188, 23);
        FONDO.add(BOTON_Registro);
        BotonRegistro.setBounds(209, 471, 143, 56);
        FONDO.add(BotonRegistro);

        Campo_Email = new JTextField();
        Campo_Email.setBounds(183, 376, 210, 20);
        FONDO.add(Campo_Email);
        Campo_Email.setColumns(10);

        CampoContrasena = new JPasswordField();
        CampoContrasena.setBounds(183, 406, 210, 20);
        FONDO.add(CampoContrasena);

        JLabel Etiqueta_Email = new JLabel("Email");
        Etiqueta_Email.setForeground(new Color(0, 128, 64));
        Etiqueta_Email.setFont(new Font("Tahoma", Font.BOLD, 14));
        Etiqueta_Email.setBounds(82, 376, 106, 30);
        FONDO.add(Etiqueta_Email);

        JLabel Etiqueta_Contrasena = new JLabel("Contraseña");
        Etiqueta_Contrasena.setForeground(new Color(0, 128, 64));
        Etiqueta_Contrasena.setFont(new Font("Tahoma", Font.BOLD, 14));
        Etiqueta_Contrasena.setBounds(82, 406, 106, 30);
        FONDO.add(Etiqueta_Contrasena);

        JLabel FOTO_FONDO = new JLabel("");
        FOTO_FONDO.setBackground(new Color(255, 255, 255));
        FOTO_FONDO.setFont(new Font("Tahoma", Font.PLAIN, 12));
        FOTO_FONDO.setIcon(new ImageIcon("C:\\Users\\acer\\Desktop\\UNI AIMAR\\2º DAM\\Desarrollo de interfaces\\LACANASTA\\lgo-removebg-preview.png"));
        FOTO_FONDO.setBounds(-52, 26, 632, 292);
        FONDO.add(FOTO_FONDO);
    }
}
