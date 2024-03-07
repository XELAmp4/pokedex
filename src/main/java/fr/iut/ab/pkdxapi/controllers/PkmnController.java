package fr.iut.ab.pkdxapi.controllers;

import org.springframework.web.bind.annotation.RestController;

import fr.iut.ab.pkdxapi.services.PkmnService;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class PkmnController {

    private PkmnService service;

    public PkmnController(PkmnService service){
        this.service = service;
    }
    
    @GetMapping("/pkmn/types")
    public Map<String, Object> getListingType() {
        return service.getAllPkmnTypes();
    }
    
}
