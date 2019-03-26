package com.axity.security.commons.to;

import java.io.Serializable;
public class TokenTO implements Serializable{
    private String token;

    public TokenTO() {
    }

    public TokenTO(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
