package com.axity.security.services.jwt;

public interface ITokenCreation {
    String createToken(String username,Integer secondToLive);
}
