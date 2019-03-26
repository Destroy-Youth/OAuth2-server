package com.axity.security.services.jwt;

import java.util.Map;

public interface ITokenService {
    String createToken(String username,Integer secondToLive);
    String extractHeaderToken(Map<String, String> headers);
    Boolean verifyToken(String token);
    void deleteToken(String token);
}
