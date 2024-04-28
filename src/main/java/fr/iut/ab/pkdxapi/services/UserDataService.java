package fr.iut.ab.pkdxapi.services;

import java.util.NoSuchElementException;

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

    public void updateUsername(String username, String newUsername){
        UserData existingUser = repository.findById(username)
            .orElseThrow(() -> new NoSuchElementException("User not found"));
    
        if (usernameExist(newUsername)) {
            throw new UserAlreadyExistException("Username already exists");
        }
    
        // Mettre à jour le nom d'utilisateur
        existingUser.setUsername(newUsername);
    
        // Enregistrer les modifications dans la base de données
        repository.save(existingUser);
    }

    public void updatePassword(String username, String newPassword){
        UserData existingUser = repository.findById(username)
            .orElseThrow(() -> new NoSuchElementException("User not found"));

        existingUser.setPassword(passwordEncoder.encode(newPassword));

        repository.save(existingUser);
    }

    public void updateImgURL(String username, String newImgURL){
        UserData existingUser = repository.findById(username)
            .orElseThrow(() -> new NoSuchElementException("User not found"));

        existingUser.setImgURL(newImgURL);

        repository.save(existingUser);
    }

    public void updatePkmnCatch(String username, String pkmnId){
        UserData existingUser = repository.findById(username)
            .orElseThrow(() -> new NoSuchElementException("User not found"));

        existingUser.getPkmnCatch().add(pkmnId);

        repository.save(existingUser);
    }

    public void updatePkmnSeen(String username, String pkmnId){
        UserData existingUser = repository.findById(username)
            .orElseThrow(() -> new NoSuchElementException("User not found"));

        existingUser.getPkmnSeen().add(pkmnId);

        repository.save(existingUser);
    }

    public void removeFromPkmnSeen(String username, String pkmnId) {
        UserData existingUser = repository.findById(username)
            .orElseThrow(() -> new NoSuchElementException("User not found"));
    
        // Retirer l'identifiant de Pokémon de la liste pkmnSeen
        existingUser.getPkmnSeen().remove(pkmnId);
    
        // Enregistrer les modifications dans la base de données
        repository.save(existingUser);
    }
    
    public void removeFromPkmnCatch(String username, String pkmnId) {
        UserData existingUser = repository.findById(username)
            .orElseThrow(() -> new NoSuchElementException("User not found"));
    
        // Retirer l'identifiant de Pokémon de la liste pkmnCatch
        existingUser.getPkmnCatch().remove(pkmnId);
    
        // Enregistrer les modifications dans la base de données
        repository.save(existingUser);
    }
    

    private boolean usernameExist(String username){
        return repository.findById(username).isPresent();
        
    }
}
