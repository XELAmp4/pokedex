package fr.iut.ab.pkdxapi.controllers;

import org.springframework.web.bind.annotation.RestController;

import fr.iut.ab.pkdxapi.models.PkmnDTO;
import fr.iut.ab.pkdxapi.models.PkmnData;
import fr.iut.ab.pkdxapi.services.PkmnService;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



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

    @PostMapping("pkmn")
    public PkmnData addPokemon(@RequestBody PkmnDTO pkmnDTO) {
        return service.addPokemon(pkmnDTO);
    
    }
    
    
}
