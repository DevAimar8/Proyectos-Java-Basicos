package PANELES;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalculadoraAvanzada extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTextField pantalla;
    private double operando1, operando2, resultado;
    private String operacion;

    public CalculadoraAvanzada() {
        setTitle("Calculadora Científica");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // menu superior
        JMenuBar menuBar = new JMenuBar();
        JMenu verMenu = new JMenu("Ver");
        JMenuItem estandarItem = new JMenuItem("Estandar");
        JMenuItem cientificaItem = new JMenuItem("Cientifica");
        JMenuItem programadorItem = new JMenuItem("Programador");

        verMenu.add(estandarItem);
        verMenu.add(cientificaItem);
        verMenu.add(programadorItem);
        menuBar.add(verMenu);
        setJMenuBar(menuBar);

        // pantalla de la calculadora
        pantalla = new JTextField();
        pantalla.setBackground(SystemColor.activeCaption);
        pantalla.setEditable(false);
        pantalla.setHorizontalAlignment(SwingConstants.RIGHT);
        pantalla.setPreferredSize(new Dimension(380, 50));

        // botones científicos (por defecto)
        JPanel panelBotones = crearPanelCientifico();

        // layout principal
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(pantalla, BorderLayout.NORTH);
        getContentPane().add(panelBotones, BorderLayout.CENTER);

        // accion para los modos
        estandarItem.addActionListener(e -> cambiarAModoEstandar());
        cientificaItem.addActionListener(e -> cambiarAModoCientifico());
        programadorItem.addActionListener(e -> JOptionPane.showMessageDialog(null, "Hecha por Aimar Esqueta Albert"));

        setVisible(true);
    }

    // cambiar a modo estándar
    private void cambiarAModoEstandar() {
        getContentPane().removeAll();
        JPanel panelEstandar = crearPanelEstandar();
        getContentPane().add(pantalla, BorderLayout.NORTH);
        getContentPane().add(panelEstandar, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    // cambiar a modo científico
    private void cambiarAModoCientifico() {
        getContentPane().removeAll();
        JPanel panelCientifico = crearPanelCientifico();
        getContentPane().add(pantalla, BorderLayout.NORTH);
        getContentPane().add(panelCientifico, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    // botones estándar
    private JPanel crearPanelEstandar() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4, 5, 5));
        String[] botones = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "+", "="
        };

        for (String texto : botones) {
            JButton boton = new JButton(texto);
            boton.addActionListener(new BotonListener());
            panel.add(boton);
            boton.setBackground(SystemColor.white);
        }

        return panel;
    }

    // botones científicos
    private JPanel crearPanelCientifico() {
        JPanel panel = new JPanel();
        panel.setBackground(SystemColor.scrollbar);
        panel.setLayout(new GridLayout(6, 5, 5, 5));
        String[] botones = {
            "sin", "cos", "tan", "log", "ln",
            "7", "8", "9", "/", "CE",
            "4", "5", "6", "*", "C",
            "1", "2", "3", "-", "=",
            "0", ".", "+", "x^y", "√"
        };

        for (String texto : botones) {
            JButton boton = new JButton(texto);
            boton.addActionListener(new BotonListener());
            panel.add(boton);
            boton.setBackground(SystemColor.white);
        }

        return panel;
    }

    // listener para los botones
    private class BotonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String comando = e.getActionCommand();
            if (comando.matches("[0-9]")) {
                pantalla.setText(pantalla.getText() + comando);
            } else if (comando.equals(".")) {
                if (!pantalla.getText().contains(".")) {
                    pantalla.setText(pantalla.getText() + ".");
                }
            } else if (comando.equals("C")) {
                pantalla.setText("");
                operando1 = operando2 = resultado = 0;
            } else if (comando.equals("CE")) {
                pantalla.setText("");
            } else if (comando.equals("=")) {
                realizarOperacion();
            } else if (comando.equals("√")) {
                double numero = Double.parseDouble(pantalla.getText());
                resultado = Math.sqrt(numero);
                pantalla.setText(String.valueOf(redondear(resultado, 4)));
            } else if (comando.equals("sin")) {
                double numero = Double.parseDouble(pantalla.getText());
                resultado = Math.sin(Math.toRadians(numero));
                pantalla.setText(String.valueOf(redondear(resultado, 4)));
            } else if (comando.equals("cos")) {
                double numero = Double.parseDouble(pantalla.getText());
                resultado = Math.cos(Math.toRadians(numero));
                pantalla.setText(String.valueOf(redondear(resultado, 4)));
            } else if (comando.equals("tan")) {
                double numero = Double.parseDouble(pantalla.getText());
                resultado = Math.tan(Math.toRadians(numero));
                pantalla.setText(String.valueOf(redondear(resultado, 4)));
            } else if (comando.equals("log")) {
                double numero = Double.parseDouble(pantalla.getText());
                resultado = Math.log10(numero);
                pantalla.setText(String.valueOf(redondear(resultado, 4)));
            } else if (comando.equals("ln")) {
                double numero = Double.parseDouble(pantalla.getText());
                resultado = Math.log(numero);
                pantalla.setText(String.valueOf(redondear(resultado, 4)));
            } else {
                operando1 = Double.parseDouble(pantalla.getText());
                operacion = comando;
                pantalla.setText("");
            }
        }
    }

    // realizar la operación seleccionada
    private void realizarOperacion() {
        operando2 = Double.parseDouble(pantalla.getText());

        switch (operacion) {
            case "+":
                resultado = operando1 + operando2;
                break;
            case "-":
                resultado = operando1 - operando2;
                break;
            case "*":
                resultado = operando1 * operando2;
                break;
            case "/":
                if (operando2 != 0) {
                    resultado = operando1 / operando2;
                } else {
                    pantalla.setText("Error");
                    return;
                }
                break;
            case "x^y":
                resultado = Math.pow(operando1, operando2);
                break;
        }

        pantalla.setText(String.valueOf(redondear(resultado, 4)));
    }

    // redondear el resultado a un número de decimales
    private double redondear(double valor, int decimales) {
        BigDecimal bd = new BigDecimal(Double.toString(valor));
        bd = bd.setScale(decimales, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CalculadoraAvanzada::new);
    }
}
