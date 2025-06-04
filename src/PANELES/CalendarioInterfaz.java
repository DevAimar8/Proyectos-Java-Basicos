package PANELES;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalendarioInterfaz extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel calendarPanel;
    private JButton[][] dayButtons;
    private JTextArea noteTextArea;

    public CalendarioInterfaz() {
        setTitle("Calendario");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(new Color(0, 128, 0));
        JMenu ayudaMenu = new JMenu("Ayuda");
        JMenuItem sobreMenuItem = new JMenuItem("Sobre La Canasta");
        JMenuItem soporteMenuItem = new JMenuItem("Soporte");
        JMenuItem contactoMenuItem = new JMenuItem("Contacto");

        JMenu MenuLista = new JMenu("Menú");
		MenuLista.setForeground(new Color(0, 0, 0));
		MenuLista.setBackground(new Color(255, 255, 255));
		menuBar.add(MenuLista);
		
        sobreMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Mostrar información sobre VAARI
                JOptionPane.showMessageDialog(null, "La Canasta es tu supermercado de confianza, ahorras dinero y nos ayudas a crecer.");
            }
        });

        soporteMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Mostrar opciones de soporte
                JOptionPane.showMessageDialog(null, "Para obtener soporte técnico, por favor contacta a nuestro equipo de soporte a support@lacanasta.com");
            }
        });

        contactoMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Mostrar información de contacto
                JOptionPane.showMessageDialog(null, "Puedes contactarnos a support@lacanasta.com para cualquier consulta o comentario.");
            }
        });
		
		
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
				InicioSesion salir = new InicioSesion();
				salir.setVisible(true);
				dispose();
			}
		});
        
        ayudaMenu.add(sobreMenuItem);
        ayudaMenu.add(soporteMenuItem);
        ayudaMenu.add(contactoMenuItem);
        menuBar.add(ayudaMenu);
        setJMenuBar(menuBar);

        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(255, 255, 255));
        headerPanel.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Calendario");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerPanel.add(titleLabel, BorderLayout.CENTER);

        JPanel datePanel = new JPanel();
        datePanel.setBackground(new Color(240, 248, 255));
        datePanel.setLayout(new FlowLayout());

        JLabel dateLabel = new JLabel();
        dateLabel.setBackground(new Color(255, 0, 128));
        dateLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        datePanel.add(dateLabel);

        Thread dateThread = new Thread(() -> {
            while (true) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                String date = sdf.format(new Date());
                dateLabel.setText("Fecha y Hora Actual: " + date);
                try {
                    Thread.sleep(1000); // Actualizar cada segundo
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        dateThread.start();

        headerPanel.add(datePanel, BorderLayout.SOUTH);

        calendarPanel = new JPanel(new GridLayout(0, 7, 5, 5));
        calendarPanel.setBackground(new Color(0, 128, 0));
        dayButtons = new JButton[6][7];

        createCalendar(Calendar.getInstance());

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.add(headerPanel, BorderLayout.NORTH);
        contentPanel.add(calendarPanel, BorderLayout.CENTER);

        noteTextArea = new JTextArea(10, 30);
        JScrollPane scrollPane = new JScrollPane(noteTextArea);

        contentPanel.add(scrollPane, BorderLayout.SOUTH);

        getContentPane().add(contentPanel);
        setVisible(true);
    }

    private void createCalendar(Calendar calendar) {
        calendarPanel.removeAll();
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        int firstDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                JButton dayButton = new JButton();
                dayButton.setBorder(BorderFactory.createEmptyBorder());
                dayButton.setFocusPainted(false);
                dayButton.setContentAreaFilled(false);
                dayButton.setOpaque(true);
                dayButton.setHorizontalAlignment(SwingConstants.CENTER);
                if (i == 0 && j < firstDayOfWeek) {
                    dayButton.setText("");
                    dayButton.setEnabled(false);
                } else if ((i * 7 + j - firstDayOfWeek + 1) > daysInMonth) {
                    dayButton.setText("");
                    dayButton.setEnabled(false);
                } else {
                    int day = i * 7 + j - firstDayOfWeek + 1;
                    dayButton.setText(String.valueOf(day));
                    dayButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            // Obtener la nota para el día seleccionado
                            String nota = JOptionPane.showInputDialog(null, "Ingresa una nota para el día " + day, "Añadir Nota", JOptionPane.PLAIN_MESSAGE);
                            if (nota != null && !nota.isEmpty()) {
                                // Agregar la nota al área de texto
                                noteTextArea.append("Nota para el día " + day + ": " + nota + "\n");
                            }
                        }
                    });
                }

                // Agregar menú emergente
                JPopupMenu popupMenu = new JPopupMenu();
                JMenuItem menuItem = new JMenuItem("Opción de Menú");
                menuItem.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JOptionPane.showMessageDialog(null, "Haz hecho clic en la opción de menú para el día " );
                    }
                });
                popupMenu.add(menuItem);
                dayButton.setComponentPopupMenu(popupMenu);

                calendarPanel.add(dayButton);
                dayButtons[i][j] = dayButton;
            }
        }
        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CalendarioInterfaz::new);
    }
}
