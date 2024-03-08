package fr.iut.ab.pkdxapi.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.iut.ab.pkdxapi.errors.PkmnAlreadyExistException;
import fr.iut.ab.pkdxapi.models.PkmnDTO;
import fr.iut.ab.pkdxapi.models.PkmnData;
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

    private boolean pkmnExist(String name){
        return repository.findByName(name).isPresent();
    }
}
