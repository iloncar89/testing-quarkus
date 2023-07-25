package com.iloncar.utils;

import com.iloncar.domain.Person;
import com.iloncar.model.PersonModel;
import com.iloncar.payload.PersonRequest;
import com.iloncar.payload.PersonResponse;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class Mapper {
  public PersonModel mapPersonRequestToPersonModel(PersonRequest personRequest){
    return new PersonModel(0, personRequest.getFirstName(), personRequest.getLastName(), personRequest.getYearOfBirth());
  }
  public PersonResponse mapPersonModelToPersonResponse(PersonModel personModel){
    return new PersonResponse(personModel.getId(), personModel.getFirstName(), personModel.getLastName(), personModel.getYearOfBirth());
  }
  public Person mapPersonRequestToPerson(PersonRequest personRequest){
    return new Person(0, personRequest.getFirstName(), personRequest.getLastName(), personRequest.getYearOfBirth());
  }
  public PersonResponse mapPersonToPersonResponse(Person person){
    return new PersonResponse(person.getId(), person.getFirstName(), person.getLastName(), person.getYearOfBirth());
  }
}

