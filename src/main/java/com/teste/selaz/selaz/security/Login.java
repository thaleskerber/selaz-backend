package com.teste.selaz.selaz.security;

public class Login {

    private String email;

    private String password;
  
    private String nivel;
  
    private String token;

    public Login() {}

    public Login(String email, String password) {
        this.email = email;
        this.password = password;
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
  
    public String getToken() {
        return token;
    }
  
    public void setToken(String token) {
        this.token = token;
    }
  
    public String getNivel() {
        return nivel;
    }
  
    public void setNivel(String nivel) {
        this.nivel = nivel;
    }
      
  }