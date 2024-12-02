
package Clases;

import Ventana.Frm1;
import java.io.*;
import java.net.*;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author ronaldandrade
 */
public class Cliente extends Observable implements Runnable{

    private Socket socket;
    private PrintWriter output;
    private BufferedReader input;
    private String host = "127.0.0.1";
    private int port = 6001;
    private Encriptacion encriptador;

    public Cliente() {
        try {
            // Inicializamos la clave secreta AES (16 caracteres)
            encriptador = new Encriptacion("clave16caractere");

            socket = new Socket(host, port);
            output = new PrintWriter(socket.getOutputStream(), true);
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            new Thread(this).start();
            System.out.println("Cliente conectado al servidor.");
        } catch (Exception e) {
            System.err.println("Error al conectar al servidor: " + e.getMessage());
        }
    }

    public void enviarMensaje(String mensaje) {
        try {
            String mensajeEncriptado = encriptador.encriptar(mensaje);
            System.out.println("Mensaje enviado (encriptado): " + mensajeEncriptado);
            output.println(mensajeEncriptado);
        } catch (Exception e) {
            System.err.println("Error al encriptar el mensaje: " + e.getMessage());
        }
    }

    @Override
    public void run() {
        String mensajeEncriptado;
        try {
            while ((mensajeEncriptado = input.readLine()) != null) {
                try {
                    String mensajeDesencriptado = encriptador.desencriptar(mensajeEncriptado);
                    setChanged();
                    notifyObservers(mensajeDesencriptado);
                } catch (Exception e) {
                    System.err.println("Error al desencriptar el mensaje: " + e.getMessage());
                }
            }
        } catch (Exception e) {
            System.err.println("Error en la conexión con el servidor: " + e.getMessage());
        }
    }
}