package fr.iut.ab.pkdxapi.errors;

import org.springframework.http.HttpStatus;


public class RegionAlreadyExistException extends APIException {
    
    public RegionAlreadyExistException(String message){
       super(HttpStatus.CONFLICT, message);
   }
}
