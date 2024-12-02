package Ventana;

import Clases.Cliente;
import Clases.Encriptacion;
import Clases.Servidor;
import java.awt.BorderLayout;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Frm1 extends JFrame implements Observer{
    
    private JTextArea textArea;
    private JTextField textField;
    private Cliente cliente;

    public Frm1(Cliente cliente) {
        this.cliente = cliente;
        this.cliente.addObserver(this);

        setTitle("Chat - Ventana 1");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        textField = new JTextField(20);
        JButton sendButton = new JButton("Enviar");
        JButton openFrame2Button = new JButton("Abrir Frame 2");

        sendButton.addActionListener(e -> enviarMensaje());
        openFrame2Button.addActionListener(e -> {
            Frm2 frame2 = new Frm2(cliente);
            frame2.setVisible(true);
        });

        JPanel inputPanel = new JPanel();
        inputPanel.add(textField);
        inputPanel.add(sendButton);
        inputPanel.add(openFrame2Button);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(inputPanel, BorderLayout.SOUTH);

        add(panel);

        setVisible(true);
    }

    private void enviarMensaje() {
        String mensaje = textField.getText().trim();
        if (!mensaje.isEmpty()) {
            cliente.enviarMensaje("Ventana 1: " + mensaje);
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