package com.axity.security.services.jwt.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.axity.security.commons.Constants;
import com.axity.security.services.jwt.ITokenService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;

@Service
public class TokenService implements ITokenService{

    private static final Logger LOG = LogManager.getLogger(TokenService.class);
    private static final String AUTHORIZATION_SCHEME = "Bearer";

    private Map<String, List<String>> createdTokens;
    private JWTVerifier verifier;

    TokenService() {
        super();
        createdTokens = new HashMap<>();
        Algorithm algorithm = Algorithm.HMAC256(Constants.SECRET_KEY);
        verifier = JWT.require(algorithm)
                .build();
    }

    public String createToken(String username, Integer secondsToLive) {


        Algorithm algorithm = Algorithm.HMAC256(Constants.SECRET_KEY);
        String token = JWT.create()
                .withClaim("user", username)
                .withIssuedAt(Date.from(Instant.now()))
                .withExpiresAt(Date.from(Instant.now().plusSeconds(secondsToLive)))
                .sign(algorithm);
        LOG.info("New token: " + token);

        if (createdTokens.containsKey(username)){
            List userTokens = createdTokens.get(username);
            userTokens.add(token);
            createdTokens.put(username, userTokens);
            LOG.info("User: " + username);
        }else{
            List userTokens = new ArrayList();
            userTokens.add(token);
            createdTokens.put(username,userTokens);
            LOG.info("New user: " + username);
        }


        return token;
    }

    public void deleteToken(String token){
        String user = extractTokenUser(token);
        List userTokens = createdTokens.get(user);
        userTokens.remove(token);
        createdTokens.put(user,userTokens);
        LOG.info("Token owner: " + user);
        LOG.info("Removed token: " + token);
    }

    public Boolean verifyToken(String token){

        String user = extractTokenUser(token);
        List userTokens = createdTokens.get(user);

        if (!createdTokens.containsKey(user) || !userTokens.contains(token)){
            LOG.error("Token hasn't been created");
            return false;
        }else {
            verifier.verify(token);
            LOG.info("User owner: " + user);
            LOG.info("Token verified: " + token);
            return true;
        }

    }

    public String extractHeaderToken(Map<String, String> headers) {

        String token;

        token = headers.get("authorization");
        token = token.substring(AUTHORIZATION_SCHEME.length()).trim();
        LOG.info("Extracted token: " + token);

        return token;
    }

    public String extractTokenUser(String token){
        try {
            DecodedJWT jwt = JWT.decode(token);
            Map<String, Claim> claims = jwt.getClaims();
            return claims.get("user").asString();
        } catch (JWTDecodeException exception){
            LOG.error("Token decode: " + exception);
            return null;
        }
    }
}
