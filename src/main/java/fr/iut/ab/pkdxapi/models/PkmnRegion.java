package fr.iut.ab.pkdxapi.models;

public class PkmnRegion {
    private String regionName;
    private Integer regionNumber;


    public PkmnRegion(String regionName, Integer regionNumber){
        this.regionName=regionName;
        this.regionNumber=regionNumber;
    }

    public String getRegionName(){
        return regionName;
    }

    public Integer getRegionNumber(){
        return regionNumber;
    }
}
