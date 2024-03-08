package fr.iut.ab.pkdxapi.errors;

import org.springframework.http.HttpStatus;

public class PkmnAlreadyExistException extends APIException {
    
     public PkmnAlreadyExistException(String message){
        super(HttpStatus.CONFLICT, message);
    }
}
