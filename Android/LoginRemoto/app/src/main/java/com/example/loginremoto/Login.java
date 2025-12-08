package com.example.loginremoto;

import java.io.ObjectOutputStream;
import java.net.Socket;

import java.io.ObjectOutputStream;
import java.net.Socket;
import java.io.Serializable;

public class Login implements Serializable {
    private String usuario;
    private String password;

    public Login(String usuario, String password) {
        this.usuario = usuario;
        this.password = password;
    }

    public void enviarLogin(String ipServidor, int puerto) {
        new Thread(() -> {
            try {
                Socket socket = new Socket(ipServidor, puerto);

                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                oos.writeObject(this); // Enviamos este objeto Login

                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}
