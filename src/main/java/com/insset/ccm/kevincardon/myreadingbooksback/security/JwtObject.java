package com.insset.ccm.kevincardon.myreadingbooksback.security;

public class JwtObject {

    private String token;

    public JwtObject() {
    }

    public JwtObject(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
