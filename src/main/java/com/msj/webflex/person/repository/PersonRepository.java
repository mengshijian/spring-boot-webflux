package com.msj.webflex.person.repository;

import com.msj.webflex.person.repository.entity.PersonEntity;
import java.util.UUID;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends ReactiveMongoRepository<PersonEntity, UUID> {
}
