package fr.iut.ab.pkdxapi.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.iut.ab.pkdxapi.models.UserDTO;
import fr.iut.ab.pkdxapi.models.patchDTO.ImgURLPatchDTO;
import fr.iut.ab.pkdxapi.models.patchDTO.PasswordPatchDTO;
import fr.iut.ab.pkdxapi.models.patchDTO.PkmnCatchPatchDTO;
import fr.iut.ab.pkdxapi.models.patchDTO.PkmnSeenPatchDTO;
import fr.iut.ab.pkdxapi.models.patchDTO.UsernamePatchDTO;
import fr.iut.ab.pkdxapi.services.UserDataService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
        return ResponseEntity.ok("User logged in successfully");
    }

    @PatchMapping("/users/update/username")
    public ResponseEntity<String> updateUsername(@RequestBody UsernamePatchDTO usernamePatchDTO) {
        service.updateUsername(usernamePatchDTO.getUsername(), usernamePatchDTO.getNewUsername());
        return ResponseEntity.ok("Username updated successfully");
    }

    @PatchMapping("/users/update/password")
    public ResponseEntity<String> updatePassword(@RequestBody PasswordPatchDTO passwordPatchDTO) {
        service.updatePassword(passwordPatchDTO.getUsername(), passwordPatchDTO.getNewPassword());
        return ResponseEntity.ok("Password updated successfully");
    }

    @PatchMapping("/users/update/imgURL")
    public ResponseEntity<String> updateImgURL(@RequestBody ImgURLPatchDTO imgURLPatchDTO) {
        service.updateImgURL(imgURLPatchDTO.getUsername(), imgURLPatchDTO.getNewImgURL());
        return ResponseEntity.ok("ImgURL updated successfully");
    }

    @PatchMapping("/users/update/pkmnCatch")
    public ResponseEntity<String> updatePkmnCatch(@RequestBody PkmnCatchPatchDTO pkmnCatchPatchDTO) {
        service.updatePkmnCatch(pkmnCatchPatchDTO.getUsername(), pkmnCatchPatchDTO.getPkmnId());
        return ResponseEntity.ok("PkmnCatch updated successfully");
    }

    @PatchMapping("/users/update/pkmnSeen")
    public ResponseEntity<String> updatePkmnSeen(@RequestBody PkmnSeenPatchDTO pkmnSeenPatchDTO) {
        service.updatePkmnSeen(pkmnSeenPatchDTO.getUsername(), pkmnSeenPatchDTO.getPkmnId());
        return ResponseEntity.ok("PkmnSeen updated successfully");
    }

    @PatchMapping("/users/update/remove/pkmnCatch")
    public ResponseEntity<String> removeFromPkmnCatch(@RequestBody PkmnCatchPatchDTO pkmnCatchPatchDTO) {
        service.removeFromPkmnCatch(pkmnCatchPatchDTO.getUsername(), pkmnCatchPatchDTO.getPkmnId());
        return ResponseEntity.ok("Pkmn removed from pkmnCatch successfully");
    }

    @PatchMapping("/users/update/remove/pkmnSeen")
    public ResponseEntity<String> removeFromPkmnSeen(@RequestBody PkmnSeenPatchDTO pkmnSeenPatchDTO) {
        service.removeFromPkmnSeen(pkmnSeenPatchDTO.getUsername(), pkmnSeenPatchDTO.getPkmnId());
        return ResponseEntity.ok("Pkmn removed from pkmnSeen successfully");
    }
}
