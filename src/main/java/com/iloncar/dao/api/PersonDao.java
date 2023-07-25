package com.iloncar.dao.api;

import com.iloncar.domain.Person;
import com.iloncar.exception.InternalServerErrorException;
import com.iloncar.exception.ResourceNotFoundException;
import com.iloncar.model.PersonModel;

public interface PersonDao {
  long insertPerson(PersonModel person) throws InternalServerErrorException;

  PersonModel getPersonById(long id) throws InternalServerErrorException, ResourceNotFoundException;

  void deletePersonById(long id) throws InternalServerErrorException;

  long insertPersonORM(Person person);

  Person getPersonByIdORM(long id);

  void deletePersonByIdORM(Person person);
}
