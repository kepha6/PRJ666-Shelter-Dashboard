package com.example.prj666shelterdashboard.ui;

public class SignInRequest {
    private String email;
    private String password;

    public SignInRequest(String username, String password) {
        this.email = username;
        this.password = password;
    }
    // getters and setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String username) {
        this.email = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

