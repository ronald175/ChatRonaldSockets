package Ventana;

import Clases.Cliente;
import Clases.Encriptacion;
import Clases.Servidor;
import java.awt.BorderLayout;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Frm2 extends JFrame implements Observer {
    
    private JTextArea textArea;
    private JTextField textField;
    private Cliente cliente;

    public Frm2(Cliente cliente) {
        this.cliente = cliente;
        this.cliente.addObserver(this);

        setTitle("Chat - Ventana 2");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        textField = new JTextField(20);
        JButton sendButton = new JButton("Enviar");

        sendButton.addActionListener(e -> enviarMensaje());

        JPanel inputPanel = new JPanel();
        inputPanel.add(textField);
        inputPanel.add(sendButton);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(inputPanel, BorderLayout.SOUTH);

        add(panel);
    }

    private void enviarMensaje() {
        String mensaje = textField.getText().trim();
        if (!mensaje.isEmpty()) {
            cliente.enviarMensaje("Ventana 2: " + mensaje);
            textField.setText("");
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof String) {
            textArea.append((String) arg + "\n");
        }
    }
}