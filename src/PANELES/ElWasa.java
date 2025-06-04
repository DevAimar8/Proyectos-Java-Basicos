package PANELES;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;

public class ElWasa extends JFrame {

    private static final long serialVersionUID = 1L;

    // componenetes
    private JList<String> listaChats;
    private DefaultListModel<String> modeloListaChats;
    private JTextArea areaMensajes;
    private JTextField campoTexto;
    private HashMap<String, StringBuilder> conversaciones;  // almacena las conversaciones por chat
    private String chatSeleccionado;

    public ElWasa() {
        getContentPane().setBackground(Color.ORANGE);
        setUndecorated(false);  
        setOpacity(1f);  // opacidad
        setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\acer\\Desktop\\icono-whatsapp-naranja-1.jpg"));
        
        // config de la ventana principal
        setTitle("WHATSAPP FAKE");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        getContentPane().setLayout(new BorderLayout());

        // crea el almacenamiento para las conversaciones de los chats
        conversaciones = new HashMap<>();
        conversaciones.put("PAPA", new StringBuilder());
        conversaciones.put("MAMA", new StringBuilder());
        conversaciones.put("OHANA", new StringBuilder());

        // crea la lista de chats en el lado izquierdo
        modeloListaChats = new DefaultListModel<>();
        modeloListaChats.addElement("PAPA");
        modeloListaChats.addElement("MAMA");
        modeloListaChats.addElement("OHANA");

        listaChats = new JList<>(modeloListaChats);
        listaChats.setBackground(new Color(255, 140, 0));
        listaChats.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // accion al seleccionar un chat
        listaChats.addListSelectionListener(e -> mostrarMensajesDeChatSeleccionado());

        JScrollPane scrollListaChats = new JScrollPane(listaChats);
        scrollListaChats.setPreferredSize(new Dimension(200, 0));

        // área de mensajes en el centro
        areaMensajes = new JTextArea();
        areaMensajes.setEditable(false);
        areaMensajes.setLineWrap(true);
        areaMensajes.setWrapStyleWord(true);
        areaMensajes.setBackground(Color.ORANGE);  // fondo naranja
        JScrollPane scrollAreaMensajes = new JScrollPane(areaMensajes);

        // campo de texto y el botn de enviar
        JPanel panelEnvio = new JPanel();
        panelEnvio.setBackground(new Color(255, 204, 102));
        panelEnvio.setLayout(new BorderLayout());

        campoTexto = new JTextField();
        JButton botonEnviar = new JButton("Enviar");
        botonEnviar.setBackground(new Color(255, 153, 0));

        // enviar el mensaje cuando se hace clic en el botón Enviar
        botonEnviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enviarMensaje();
            }
        });

        // enviar mensaje al presionar tecla Enter
        campoTexto.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    enviarMensaje();
                }
            }
        });

        // añade campo de texto y botón al panel
        panelEnvio.add(campoTexto, BorderLayout.CENTER);
        panelEnvio.add(botonEnviar, BorderLayout.EAST);

        // añade los componentes al layout principal
        getContentPane().add(scrollListaChats, BorderLayout.WEST);
        getContentPane().add(scrollAreaMensajes, BorderLayout.CENTER);
        getContentPane().add(panelEnvio, BorderLayout.SOUTH);

        // selecciona del primer chat al inicio
        listaChats.setSelectedIndex(0);
        chatSeleccionado = "PAPA";
        mostrarMensajesDeChatSeleccionado();
    }

    // muestra los mensajes del chat seleccionado
    private void mostrarMensajesDeChatSeleccionado() {
        chatSeleccionado = listaChats.getSelectedValue();
        if (chatSeleccionado != null) {
            areaMensajes.setText(conversaciones.get(chatSeleccionado).toString());
        }
    }

    // envia mensajes
    private void enviarMensaje() {
        String mensaje = campoTexto.getText().trim();
        if (!mensaje.isEmpty() && chatSeleccionado != null) {
            // añade el mensaje a la conversación
            conversaciones.get(chatSeleccionado).append("Yo: ").append(mensaje).append("\n");
            areaMensajes.setText(conversaciones.get(chatSeleccionado).toString());
            campoTexto.setText("");  // Limpiar el campo de texto

            // simula una respuesta automática
            SwingUtilities.invokeLater(() -> respuestaAutomatica(chatSeleccionado));
        }
    }

    // respuesta automática
    private void respuestaAutomatica(String chat) {
        try {
            // pause un segundo antes de la respuesta
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // genera una respuesta según el chat
        String respuesta = "";
        switch (chat) {
            case "PAPA":
                respuesta = "papa: ¡Hola champion!?\n";
                break;
            case "MAMA":
                respuesta = "mama: ¡Hola hijo! ¿Cómo estás?\n";
                break;
            case "OHANA":
                respuesta = "oscar: Aquí por la uni. Que tal por Zaragoza.\n";
                respuesta += "ivan: Cuando vienes perro.\n";
                respuesta += "nené: Calla papi ivi\n";
                break;
        }

        // añade la respuesta a la conversación y actualizar
        conversaciones.get(chat).append(respuesta);
        areaMensajes.setText(conversaciones.get(chat).toString());

        // simula una notificación visual
        modeloListaChats.setElementAt(chat + " (Nuevo mensaje)", listaChats.getSelectedIndex());
    }

    // metodo para iniciar la interfaz
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ElWasa simulacion = new ElWasa();
            simulacion.setVisible(true);
        });
    }
}
