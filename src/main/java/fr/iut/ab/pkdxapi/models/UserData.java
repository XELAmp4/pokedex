package fr.iut.ab.pkdxapi.models;


import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("User")
@TypeAlias("UserData")
public class UserData {
    @Id
    private String login;
    private String password;
    private boolean isAdmin;
    private String username;
    private String imgURL;
    private List<String> pkmnCatch;
    private List<String> pkmnSeen;

   
    public UserData(String login, String password, boolean isAdmin){
        this.login = login;
        this.password = password;
        this.isAdmin = isAdmin;   
        this.username = login;
        this.imgURL = "https://www.bybatisol.com/app/themes/bybatisol/img/default-pp.jpg"; // Valeur par défaut pour imgURL
        this.pkmnCatch = new ArrayList<>(); // Valeur par défaut pour pkmnCatch
        this.pkmnSeen = new ArrayList<>(); // Valeur par défaut pour pkmnSeen     
    }

    public boolean getIsAdmin(){
        return this.isAdmin;
    }

    public String getLogin(){
        return login;
    }

    public void setLogin(String login){
        this.login = login;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
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
