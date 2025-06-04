package PANELES;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.BorderLayout;

public class MenuPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuPrincipal frame = new MenuPrincipal();
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
	public MenuPrincipal() {
		getContentPane().setBackground(new Color(0, 255, 127));
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\acer\\Desktop\\UNI AIMAR\\2º DAM\\Desarrollo de interfaces\\LACANASTA\\lgo-removebg-preview.png"));
		getContentPane().add(lblNewLabel, BorderLayout.CENTER);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 649, 603);
		
		JMenuBar menuBar = new JMenuBar();
		
		menuBar.setBackground(new Color(0, 128, 0));
		menuBar.setBorder(new LineBorder(Color.WHITE, 0));
		setJMenuBar(menuBar);
		
		JMenu MenuLista = new JMenu("Menú");
		MenuLista.setForeground(new Color(255, 255, 255));
		MenuLista.setBackground(new Color(255, 255, 255));
		menuBar.add(MenuLista);
		
		
		
		JLabel HoraMenu = new JLabel("Hora");
		HoraMenu.setBackground(new Color(255, 255, 255));
		HoraMenu.setFont(new Font("Tahoma", Font.PLAIN, 20));
		MenuLista.add(HoraMenu);
//		lblNewLabel_1.setBackground(Color.WHITE); // Cambia Color.white por el color deseado
//		lblNewLabel_1.setOpaque(false); // Asegura que el JLabel sea opaco para mostrar el color de fondo
		Thread te = new Thread(() -> {
            while (true) 
            {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String fechaActual = sdf.format(new Date());
                HoraMenu.setText("Fecha: " + fechaActual);
                try {
                    Thread.sleep(1000); // Esperar 1 segundo antes de actualizar la fecha
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        te.start();
        
        JMenuItem IrMenuPrincipal = new JMenuItem("Menú");
		IrMenuPrincipal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				MenuPrincipal MenuPrincipal = new MenuPrincipal();
				MenuPrincipal.setVisible(true);
				dispose();
			}
		});
		IrMenuPrincipal.setBackground(new Color(255, 255, 255));
		MenuLista.add(IrMenuPrincipal);

		JMenuItem IrCalendario = new JMenuItem("Calendario");
		IrCalendario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				CalendarioInterfaz irCalendario = new CalendarioInterfaz();
				irCalendario.setVisible(true);
				dispose();
			}
		});
		IrCalendario.setBackground(new Color(255, 255, 255));
		MenuLista.add(IrCalendario);
		
		JMenuItem Salir = new JMenuItem("Salir");
		Salir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				dispose();
			}
		});
		Salir.setBackground(new Color(255, 255, 255));
		MenuLista.add(Salir);
	}
}
