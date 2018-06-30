package com.msj.webflex.person;

import com.msj.webflex.person.repository.PersonByCountryRepository;
import com.msj.webflex.person.repository.PersonRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

import static com.msj.webflex.person.PersonMapper.toPersonByCountryEntity;
import static com.msj.webflex.person.PersonMapper.toPersonEntity;

@Component
public class PersonManager {

  private final PersonRepository personRepository;
  private final PersonByCountryRepository personByCountryRepository;

  public PersonManager(
      PersonRepository personRepository, PersonByCountryRepository personByCountryRepository) {
    this.personRepository = personRepository;
    this.personByCountryRepository = personByCountryRepository;
  }

  public Flux<com.msj.webflex.person.Person> findAll() {
    return personByCountryRepository.findAll().map(com.msj.webflex.person.PersonMapper::toPerson);
  }

  public Flux<com.msj.webflex.person.Person> findAllByCountry(String country) {
    return personByCountryRepository.findAllByKeyCountry(country).map(
            com.msj.webflex.person.PersonMapper::toPerson);
  }

  public Mono<com.msj.webflex.person.Person> findById(final UUID id) {
    return personRepository.findById(id).map(com.msj.webflex.person.PersonMapper::toPerson).switchIfEmpty(Mono.empty());
  }

  public Mono<com.msj.webflex.person.Person> save(com.msj.webflex.person.Person person) {
    return Mono.fromSupplier(
        () -> {
          personRepository
              .save(toPersonEntity(person))
              .and(personByCountryRepository.save(toPersonByCountryEntity(person)))
              .subscribe();
          return person;
        });
  }

  public Mono<com.msj.webflex.person.Person> update(com.msj.webflex.person.Person old, com.msj.webflex.person.Person updated) {
    return Mono.fromSupplier(
        () -> {
          personRepository
              .save(toPersonEntity(updated))
              .and(personByCountryRepository.delete(toPersonByCountryEntity(old)))
              .and(personByCountryRepository.save(toPersonByCountryEntity(updated)))
              .subscribe();
          return updated;
        });
  }

  public Mono<Void> delete(com.msj.webflex.person.Person person) {
    return Mono.fromSupplier(
        () -> {
          personRepository
              .delete(toPersonEntity(person))
              .and(personByCountryRepository.delete(toPersonByCountryEntity(person)))
              .subscribe();
          return null;
        });
  }
}
