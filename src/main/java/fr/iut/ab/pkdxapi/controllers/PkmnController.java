package fr.iut.ab.pkdxapi.controllers;

import org.springframework.web.bind.annotation.RestController;

import fr.iut.ab.pkdxapi.models.PkmnDTO;
import fr.iut.ab.pkdxapi.models.PkmnData;
import fr.iut.ab.pkdxapi.models.PkmnRegionRequest;
import fr.iut.ab.pkdxapi.services.PkmnService;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;



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

    @PostMapping("pkmn/region")
    public PkmnData addRegion(@RequestBody PkmnRegionRequest pkmnRegionRequest) {
        return service.addRegion(pkmnRegionRequest);
    }


    @GetMapping("pkmn/search")
    public List<PkmnData> searchByPartialName(@RequestParam String partialName) {
        return service.findPokemonsByPartialName(partialName);
    }
    
}
