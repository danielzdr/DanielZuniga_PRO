package org.example.login;

import org.example.login.model.Login;
import org.example.login.model.Persona;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorTCP {
    public static void iniciarServidor() {
        new Thread(() -> {

            try {
                ServerSocket server = new ServerSocket(5000);
                System.out.println("Servidor esperando clientes...");

                Socket socket = server.accept();
                System.out.println("Cliente conectado");

                // Recibir login
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                Login login = (Login) ois.readObject();

                System.out.println("Usuario recibido: " + login.usuario);

                // Aquí JavaFX busca datos de la persona (ejemplo)
                Persona persona = new Persona("Juan Pérez", 30, "juan@example.com");

                // Enviar objeto Persona
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                oos.writeObject(persona);

                socket.close();
                server.close();

            } catch (Exception e) {
                e.printStackTrace();
            }

        }).start();
    }
}
