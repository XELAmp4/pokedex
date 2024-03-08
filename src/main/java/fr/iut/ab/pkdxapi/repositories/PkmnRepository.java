package fr.iut.ab.pkdxapi.repositories;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import fr.iut.ab.pkdxapi.models.PkmnData;

@Repository
public interface PkmnRepository extends MongoRepository<PkmnData,ObjectId>{
    @Query("{name:'?0'}")
    Optional<PkmnData> findByName(String name);

    @Query("{name: { $regex: ?0, $options: 'i' }}")
    List<PkmnData> findByPartialName(String partialName);
}
