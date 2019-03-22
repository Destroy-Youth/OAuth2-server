package com.axity.security.services.jwt.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.axity.security.services.jwt.ITokenCreation;
import com.axity.security.services.jwt.IVerifyToken;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;

@Service
public class TokenService implements ITokenCreation,IVerifyToken {

    static final Logger LOG = LogManager.getLogger(TokenService.class);

    private Map<String,String> createdTokens;

    TokenService(){
        super();
        createdTokens = new HashMap<>();
    }

   public String createToken(String username,Integer secondsToLive){

       try {
           Algorithm algorithm = Algorithm.HMAC256("secret");
           String token = JWT.create()
                   .withClaim("user",username)
                   .withIssuedAt(Date.from(Instant.now()))
                   //.withExpiresAt(Date.from(Instant.now().plusSeconds(secondsToLive)))
                   .sign(algorithm);
           LOG.info("Token: " + token);
           createdTokens.put(username,token);
           return token;
       } catch (JWTCreationException exception){
           //Invalid Signing configuration / Couldn't convert Claims.
           LOG.error("Create token: " + exception);
       }
       return null;
   }

   public Boolean verifyToken(String token){

       try {

           if (!createdTokens.containsValue(token)) {
               throw new NoSuchElementException();
           }

           Algorithm algorithm = Algorithm.HMAC256("secret");
           JWTVerifier verifier = JWT.require(algorithm)
                   .build(); //Reusable verifier instance
           DecodedJWT jwt = verifier.verify(token);
           LOG.info("Token verified: " + jwt.getToken());
           return true;
       } catch (JWTVerificationException | NoSuchElementException exception){
           //Invalid signature/claims
           LOG.error("Validate token: " + exception);
       }

       return false;
   }
}
