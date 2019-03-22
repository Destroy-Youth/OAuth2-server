package com.axity.security.commons.aspects;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.axity.security.commons.to.ErrorTO;
import java.util.NoSuchElementException;

@Aspect
@Configuration
public class RequestValidatorAspect{

    static final Logger LOG= LogManager.getLogger(RequestValidatorAspect.class);

    @Around(value = "execution(* com.axity.security.*.*(..))  && args(..)")
    public ResponseEntity execute(ProceedingJoinPoint joinPoint){
        ResponseEntity result;
        try{
            LOG.info("Access");
            LOG.info(String.format("Execution: %s", joinPoint.getSignature()));
            result = (ResponseEntity) joinPoint.proceed();
            return result;
        }
        catch (Throwable e){
            LOG.info("Exception Ocurred");
            LOG.info("Execution: {}", joinPoint.getSignature());
            LOG.info("Exception: {}", e.getMessage());
            ErrorTO errorTO= new ErrorTO();
            if(e instanceof NoSuchElementException){
                LOG.info("Informacion no encontrada");
                errorTO.setErrorMessage("Informacion no encontrada");
                errorTO.setErrorCode(500);
                return new ResponseEntity<>(errorTO, HttpStatus.NOT_FOUND);
            }
            else{
                errorTO.setErrorMessage("Error desconocido");
                errorTO.setErrorCode(500);
                return new ResponseEntity<>(errorTO,HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }
}
