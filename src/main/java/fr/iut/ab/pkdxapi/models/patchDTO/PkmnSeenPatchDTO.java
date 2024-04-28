package fr.iut.ab.pkdxapi.models.patchDTO;

public class PkmnSeenPatchDTO {
    private String username;
    private String pkmnId;

    // Getters et Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPkmnId() {
        return pkmnId;
    }

    public void setPkmnId(String pkmnId) {
        this.pkmnId = pkmnId;
    }
}
