package fr.iut.ab.pkdxapi.errors;

import org.springframework.http.HttpStatus;

public class PkmnNotFoundException extends APIException{
    public PkmnNotFoundException(String message){
        super(HttpStatus.NOT_FOUND, message);
    }
}
