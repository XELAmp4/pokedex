package fr.iut.ab.pkdxapi.models;

public class UserDTO {

    private String login;
    private String password;
    private boolean isAdmin;

    public UserDTO(String login, String password, boolean isAdmin){
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
