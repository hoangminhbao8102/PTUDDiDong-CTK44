package com.example.accountapplab15;

public class Account {
    String email;
    String pass;
    public String getEmail() {
        return email;
    }

    public String getPass() {
        return pass;
    }

    public Account(String email, String pass) {
        this.email = email;
        this.pass = pass;
    }
}
