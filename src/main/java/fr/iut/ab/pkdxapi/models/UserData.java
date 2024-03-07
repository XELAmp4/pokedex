package fr.iut.ab.pkdxapi.models;

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

   
    public UserData(String login, String password, boolean isAdmin){
        this.login = login;
        this.password = password;
        this.isAdmin = isAdmin;        
    }

    public boolean getIsAdmin(){
        return this.isAdmin;
    }

    public String getLogin(){
        return login;
    }

    public String getPassword(){
        return password;
    }
}
