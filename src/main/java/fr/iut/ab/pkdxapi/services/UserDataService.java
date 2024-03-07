package fr.iut.ab.pkdxapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fr.iut.ab.pkdxapi.errors.UserAlreadyExistException;
import fr.iut.ab.pkdxapi.models.UserDTO;
import fr.iut.ab.pkdxapi.models.UserData;
import fr.iut.ab.pkdxapi.repositories.UserRepository;


@Service
public class UserDataService {
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public UserDataService(UserRepository repository){
        this.repository = repository;
    }

    public void newUser(UserDTO userDTO){
        if (usernameExist(userDTO.getLogin())) {
            throw new UserAlreadyExistException("User already exist");
        }else{
            String encryptedPassword = passwordEncoder.encode(userDTO.getPassword());
            UserData udata = new UserData(userDTO.getLogin(), encryptedPassword, userDTO.getIsAdmin());
            repository.insert(udata);
        }  
    }

    private boolean usernameExist(String username){
        return repository.findById(username).isPresent();
        
    }
}
