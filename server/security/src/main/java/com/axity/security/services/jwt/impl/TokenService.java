package com.axity.security.services.jwt.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.axity.security.commons.Constants;
import com.axity.security.services.jwt.ITokenCreation;
import com.axity.security.services.jwt.IVerifyToken;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import sun.rmi.runtime.Log;

import javax.naming.directory.NoSuchAttributeException;
import java.rmi.NoSuchObjectException;
import java.time.Instant;
import java.util.*;

@Service
public class TokenService implements ITokenCreation, IVerifyToken {

    private static final Logger LOG = LogManager.getLogger(TokenService.class);
    private static final String AUTHORIZATION_SCHEME = "Bearer";

    private Map<String, String> createdTokens;
    private JWTVerifier verifier;
    private Algorithm algorithm;

    TokenService() {
        super();
        createdTokens = new HashMap<>();
        algorithm = Algorithm.HMAC256(Constants.SECRET_KEY);
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
        LOG.info("Token: " + token);
        createdTokens.put(username, token);
        return token;
    }

    public void deleteToken(String token){
        DecodedJWT jwt = verifier.verify(token);
        Map<String, Claim> claims = jwt.getClaims();
        String user = claims.get("user").asString();

        createdTokens.remove(user);
        LOG.info("Token owner: " + user);
        LOG.info("Removed token: " + token);
    }

    public Boolean verifyToken(String token){

        if (!createdTokens.containsValue(token)){
            LOG.error("Token hasn't been created");
            return false;
        }else {
            DecodedJWT jwt = verifier.verify(token);

            Map<String, Claim> claims = jwt.getClaims();
            String user = claims.get("user").asString();

            LOG.info("User owner: " + user);
            LOG.info("Token verified: " + jwt.getToken());
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
}
