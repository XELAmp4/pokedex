package fr.iut.ab.pkdxapi.errors;

import org.springframework.http.HttpStatus;

public class BadParametersException extends APIException {
    
    public BadParametersException(String message){
       super(HttpStatus.BAD_REQUEST, message);
   }
}
