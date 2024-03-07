package fr.iut.ab.pkdxapi.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import fr.iut.ab.pkdxapi.models.PkmnType;

@Service
public class PkmnService {
    
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
}
