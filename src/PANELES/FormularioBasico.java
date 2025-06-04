package PANELES;

import java.awt.*;
import java.awt.event.*;

public class FormularioBasico extends Frame implements ActionListener {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// atributos de la interfaz
    private TextField nombreField;
    private TextField apellidosField;
    private TextField idField;
    private TextField edadField;
    private Button btnEnviar;

    public FormularioBasico() {
        // config de la ventana
        setTitle("Supermercados La Canasta");
        setSize(350, 250);
        setLayout(new GridLayout(5, 1));
        setBackground(Color.GREEN); // Fondo verde

        // componentes
        Label nombreLabel = new Label("Nombre:");
        nombreField = new TextField(20);
        
        Label apellidosLabel = new Label("Apellidos:");
        apellidosField = new TextField(20);
        
        Label idLabel = new Label("ID:");
        idField = new TextField(10);
        
        Label edadLabel = new Label("Edad:");
        edadField = new TextField(3);
        
        btnEnviar = new Button("Enviar");

        // add componentes a la ventana
        add(nombreLabel);
        add(nombreField);
        add(apellidosLabel);
        add(apellidosField);
        add(idLabel);
        add(idField);
        add(edadLabel);
        add(edadField);
        add(btnEnviar);

        // add listener al botón
        btnEnviar.addActionListener(this);

        // cierre de la ventana
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) 
            {
                dispose();
            }
        });

        // hacer visible la ventana
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // guardar datos ingresados
        String nombre = nombreField.getText().trim();
        String apellidos = apellidosField.getText().trim();
        String id = idField.getText().trim();
        String edad = edadField.getText().trim();

        // validacion de campos
        if (nombre.isEmpty() || apellidos.isEmpty() || id.isEmpty() || edad.isEmpty()) 
        {
            // mensaje de advertencia
            Dialog dialog = new Dialog(this, "Advertencia", true);
            dialog.add(new Label("Por favor, complete todos los campos."));
            Button okButton = new Button("Aceptar");
            okButton.addActionListener(event -> dialog.dispose());
            dialog.add(okButton);
            dialog.setSize(250, 100);
            dialog.setLayout(new FlowLayout());
            dialog.setVisible(true);
        } 
        else
        {
            // mensaje de bienvenida
            String mensaje = "¡Bienvenido a Supermercados La Canasta, " + nombre + " " + apellidos + "! Tu ID es " + id + ".";
            Dialog dialog = new Dialog(this, "Bienvenida", true);
            dialog.add(new Label(mensaje));
            Button okButton = new Button("Aceptar");
            okButton.addActionListener(event -> dialog.dispose());
            dialog.add(okButton);
            dialog.setSize(400, 100);
            dialog.setLayout(new FlowLayout());
            dialog.setVisible(true);
        }
    }

    public static void main(String[] args) {
        new FormularioBasico();
    }
}
