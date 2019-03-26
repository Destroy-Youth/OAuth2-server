package com.axity.security.services.facade;

import com.sun.deploy.net.HttpResponse;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface IAuthorizationFacade {
    ResponseEntity validatePetition(Map<String, String> headers);
}
