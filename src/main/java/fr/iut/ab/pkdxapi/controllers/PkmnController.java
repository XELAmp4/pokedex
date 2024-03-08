package fr.iut.ab.pkdxapi.controllers;

import org.springframework.web.bind.annotation.RestController;

import fr.iut.ab.pkdxapi.errors.BadParametersException;
import fr.iut.ab.pkdxapi.models.PkmnDTO;
import fr.iut.ab.pkdxapi.models.PkmnData;
import fr.iut.ab.pkdxapi.models.PkmnRegionRequest;
import fr.iut.ab.pkdxapi.services.PkmnService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @GetMapping("pkmn")
    public Optional<PkmnData> getMethodName(
        @RequestParam(required = false) ObjectId id,
        @RequestParam(required = false) String name) {

        if ((id!= null && name!= null)||(id== null && name== null)) {
            throw new BadParametersException("Only one parameter required, name or id");
        }if (id != null) {
            return service.findPokemonById(id);
        }else{
            return service.findPokemonByName(name);
        }       
    }

    @DeleteMapping("pkmn")
    public ResponseEntity<String> deletePokemon(@RequestParam ObjectId id){
        service.deletePokemon(id);
        return ResponseEntity.ok("deleted");
    } 

    @PutMapping("pkmn/update")
    public ResponseEntity<PkmnData> updatePkmn(
            @RequestParam ObjectId id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) String imgURL,
            @RequestParam(required = false) String typeOne,
            @RequestParam(required = false) String typeTwo) {

        PkmnData updatedPkmn = service.updatePkmn(id, name, description, imgURL, typeOne, typeTwo);
        return ResponseEntity.ok(updatedPkmn);
    }
}
