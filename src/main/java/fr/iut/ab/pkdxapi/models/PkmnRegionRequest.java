package fr.iut.ab.pkdxapi.models;

import org.bson.types.ObjectId;

public class PkmnRegionRequest {
    private String regionName;
    private String regionNumber;
    private ObjectId pokemonId;

    public PkmnRegionRequest(String regionName, String regionNumber, ObjectId pokemonId){
        this.regionName=regionName;
        this.regionNumber=regionNumber;
        this.pokemonId=pokemonId;
    }

    public String getRegionName(){
        return regionName;
    }

    public String getRegionNumber(){
        return regionNumber;
    }

    public ObjectId getPokemonId(){
        return pokemonId;
    }
}
