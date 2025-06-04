package PANELES;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ReproductorMusica extends JFrame {
    private static final long serialVersionUID = 1L;
    
    // componentes de la interfaz
    private JButton btnPlay, btnPause, btnStop, btnAbrir;
    private JLabel lblCancion;

    public ReproductorMusica() {
        setTitle("Reproductor de Música");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // crear el panel principal
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // crear los botones
        btnPlay = new JButton("Play");
        btnPlay.setBackground(new Color(0, 255, 0));
        btnPause = new JButton("Pause");
        btnPause.setBackground(new Color(255, 140, 0));
        btnStop = new JButton("Stop");
        btnStop.setBackground(new Color(255, 0, 0));
        btnAbrir = new JButton("Abrir");
        btnAbrir.setForeground(new Color(255, 255, 255));
        btnAbrir.setBackground(new Color(0, 0, 0));

        // los botones de control
        JPanel panelControles = new JPanel();
        panelControles.setBackground(new Color(0, 0, 0));
        panelControles.setLayout(new FlowLayout());
        panelControles.add(btnPlay);
        panelControles.add(btnPause);
        panelControles.add(btnStop);

        // nombre de la canción actual
        lblCancion = new JLabel("No hay ninguna canción seleccionada", SwingConstants.CENTER);
        lblCancion.setForeground(new Color(0, 0, 0));
        lblCancion.setBackground(new Color(0, 191, 255));
        
        // funcionalidad para abrir archivos de música
        btnAbrir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de Audio", "mp3", "wav");
                fileChooser.setFileFilter(filter);

                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    String nombreArchivo = fileChooser.getSelectedFile().getName();
                    lblCancion.setText("Canción: " + nombreArchivo);
                    // agregar la lógica para cargar y reproducir el archivo
                }
            }
        });

        // añadir componentes al panel principal
        panel.add(lblCancion, BorderLayout.NORTH);
        panel.add(panelControles, BorderLayout.CENTER);
        panel.add(btnAbrir, BorderLayout.SOUTH);

        // añadir el panel a la ventana
        getContentPane().add(panel);

        // accion de los botones 
        btnPlay.addActionListener(e -> JOptionPane.showMessageDialog(null, "Reproduciendo..."));
        btnPause.addActionListener(e -> JOptionPane.showMessageDialog(null, "Pausado..."));
        btnStop.addActionListener(e -> JOptionPane.showMessageDialog(null, "Detenido..."));

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ReproductorMusica::new);
    }
}