package fr.iut.ab.pkdxapi.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.iut.ab.pkdxapi.models.UserDTO;
import fr.iut.ab.pkdxapi.services.UserDataService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
public class UserController {

    private UserDataService service;

    public UserController(UserDataService service){
        this.service = service;
    }
    
    @PostMapping("/users/register")
    public ResponseEntity<String> register(@RequestBody UserDTO userDTO) {
        service.newUser(userDTO);
        return ResponseEntity.ok("Registration successful");
    }

    @GetMapping("/users/login")
    public ResponseEntity<String> login(@RequestParam String param) {
        return ResponseEntity.ok("User loged in successfully");
    }
    
}
