package fr.iut.ab.pkdxapi.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import fr.iut.ab.pkdxapi.models.UserData;

public interface UserRepository extends MongoRepository<UserData, String> {
    @Query("{username:'?0'}")
    List<UserData> findByUsername(String username);
}
