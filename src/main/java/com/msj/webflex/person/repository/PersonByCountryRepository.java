package com.msj.webflex.person.repository;

import com.msj.webflex.person.repository.entity.PersonByCountryEntity;
import com.msj.webflex.person.repository.entity.PersonByCountryKey;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface PersonByCountryRepository extends ReactiveMongoRepository<PersonByCountryEntity, PersonByCountryKey> {

  Flux<PersonByCountryEntity> findAllByKeyCountry(final String country);
}
