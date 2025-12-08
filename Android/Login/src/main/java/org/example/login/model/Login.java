package org.example.login.model;

import java.io.Serializable;

public class Login implements Serializable {
    public String usuario;
    public String password;

    public Login(String usuario , String password) {
        this.usuario = usuario;
        this.password = password;
    }
}
