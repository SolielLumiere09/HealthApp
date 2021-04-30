package com.google.firebase.codelab.labelScannerUABC.Class;

import androidx.annotation.NonNull;

public class User {
    private String id;
    private String name;
    private String lastname;
    private String email;
    private String password;
    private String gen;
    private String estatura;
    private String edad;
    private String peso;

    public User(String id, String name, String lastname, String email, String password) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
    }

    public User(String estatura, String edad, String peso){
        this.estatura = estatura;
        this.edad = edad;
        this.peso = peso;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGen() { return gen; }

    public void setGen(String gender) { this.gen = gen; }

    public void setEstatura(String estatura) { this.estatura = estatura; }

    public void setEdad(String edad) { this.edad = edad; }

    public void setPeso(String peso) { this.peso = peso; }

    public String getEstatura() { return estatura; }

    public String getEdad() { return edad; }


    public String getPeso() { return peso; }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", gen='" + gen + '\'' +
                ", estatura='" + estatura + '\'' +
                ", edad='" + edad + '\'' +
                ", peso='" + peso + '\'' +
                '}';
    }
}
