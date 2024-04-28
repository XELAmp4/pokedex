package fr.iut.ab.pkdxapi.models;

import java.util.ArrayList;
import java.util.List;

public class UserDTO {

    private String login;
    private String password;
    private boolean isAdmin;
    private String username;
    private String imgURL;
    private List<String> pkmnCatch;
    private List<String> pkmnSeen;

    public UserDTO(String login, String password, boolean isAdmin){
        this.login = login;
        this.password = password;
        this.isAdmin = isAdmin;  
        this.username = login;
        this.imgURL = ""; // Valeur par défaut pour imgURL
        this.pkmnCatch = new ArrayList<>(); // Valeur par défaut pour pkmnCatch
        this.pkmnSeen = new ArrayList<>(); // Valeur par défaut pour pkmnSeen      
    }
    

    public boolean getIsAdmin(){
        return this.isAdmin;
    }

    public String getLogin(){
        return login;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword(){
        return password;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public List<String> getPkmnCatch() {
        return pkmnCatch;
    }

    public void setPkmnCatch(List<String> pkmnCatch) {
        this.pkmnCatch = pkmnCatch;
    }

    public List<String> getPkmnSeen() {
        return pkmnSeen;
    }

    public void setPkmnSeen(List<String> pkmnSeen) {
        this.pkmnSeen = pkmnSeen;
    }
}
