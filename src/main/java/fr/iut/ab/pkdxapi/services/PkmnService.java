package fr.iut.ab.pkdxapi.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.iut.ab.pkdxapi.errors.PkmnAlreadyExistException;
import fr.iut.ab.pkdxapi.errors.PkmnNotFoundException;
import fr.iut.ab.pkdxapi.errors.RegionAlreadyExistException;
import fr.iut.ab.pkdxapi.models.PkmnDTO;
import fr.iut.ab.pkdxapi.models.PkmnData;
import fr.iut.ab.pkdxapi.models.PkmnRegion;
import fr.iut.ab.pkdxapi.models.PkmnRegionRequest;
import fr.iut.ab.pkdxapi.models.PkmnType;
import fr.iut.ab.pkdxapi.repositories.PkmnRepository;

@Service
public class PkmnService {

    @Autowired
    private PkmnRepository repository;
    
    public Map<String, Object> getAllPkmnTypes(){
        Map<String, Object> result = new HashMap<>();
        PkmnType[] types = PkmnType.values();
        String[] typeNames = new String[types.length];
        
        for (int i = 0; i < types.length; i++) {
            typeNames[i] = types[i].toString();
        }
        
        result.put("data", typeNames);
        result.put("count", typeNames.length);
        
        return result;
    }


    public PkmnData addPokemon(PkmnDTO pkmnDTO) {
        if (pkmnExist(pkmnDTO.getName())) {
            throw new PkmnAlreadyExistException("This pokemon is already in database");
        }else{
            PkmnData pkmnData = new PkmnData(pkmnDTO.getName(), pkmnDTO.getImage(), pkmnDTO.getDescription(), pkmnDTO.getTypes(), pkmnDTO.getRegions());
            repository.insert(pkmnData);
            return pkmnData;
        }
        
    }

    public PkmnData addRegion(PkmnRegionRequest pkmnRegionRequest){
        Optional<PkmnData> pokemonOptional = repository.findById(pkmnRegionRequest.getPokemonId());
        if (pokemonOptional.isPresent()) {
            PkmnData pokemon = pokemonOptional.get();
            int regionNumber = Integer.parseInt(pkmnRegionRequest.getRegionNumber());
            PkmnRegion newRegion = new PkmnRegion(pkmnRegionRequest.getRegionName(), regionNumber);

            // Vérifie si la région existe déjà
            boolean regionExists = pokemon.getRegions().stream()
                    .anyMatch(region -> region.getRegionName().equals(newRegion.getRegionName()));

            if (!regionExists) {
                pokemon.getRegions().add(newRegion);
                repository.save(pokemon);
                return pokemon;
            } else {
                throw new RegionAlreadyExistException("Region already exists for this Pokemon");
            }
        }
        throw new PkmnNotFoundException("This pokemon isn't in database");
    }

    public List<PkmnData> findPokemonsByPartialName(String partialName) {
        return repository.findByPartialName(partialName);
    }

    public Optional<PkmnData> findPokemonByName(String name) {
        return repository.findByName(name);
    }

    public Optional<PkmnData> findPokemonById(ObjectId id) {
        return repository.findById(id);
    }

    public void deletePokemon(ObjectId id) {
        if (findPokemonById(id).isPresent()) {
            repository.deleteById(id);
        }else{
            throw new PkmnNotFoundException("This pokemon isn't in database");
        }  
    }

    private boolean pkmnExist(String name){
        return repository.findByName(name).isPresent();
    }
}
