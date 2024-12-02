/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package ec.edu.ups.chatronaldsockets;

import Clases.Cliente;
import Clases.Servidor;
import Ventana.Frm1;
import Ventana.Frm2;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;

/**
 *
 * @author ronaldandrade
 */
public class ChatRonaldSockets {

    public static void main(String[] args) {
        // Inicia el servidor en un hilo separado
        Servidor servidor = new Servidor();
        Thread servidorThread = new Thread(servidor);
        servidorThread.start();

        // Inicia la interfaz del cliente
        SwingUtilities.invokeLater(() -> {
            Cliente cliente = new Cliente();
            new Frm1(cliente);
        });
    }
}
