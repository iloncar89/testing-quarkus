package com.iloncar.service.impl;

import com.iloncar.dao.api.PersonDao;
import com.iloncar.exception.InternalServerErrorException;
import com.iloncar.exception.ResourceNotFoundException;
import com.iloncar.domain.Person;
import com.iloncar.model.PersonModel;
import com.iloncar.service.api.TestService;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Transactional
@ApplicationScoped
public class TestServiceImpl implements TestService {
  PersonDao personDao;

  EntityManager entityManager;

  public TestServiceImpl(PersonDao personDao, EntityManager entityManager) {
    this.personDao = personDao;
    this.entityManager = entityManager;
  }
  @Override
  public long calculateFibonacci(long n) {
    if (n <= 1) return n;
    return calculateFibonacci(n - 1) + calculateFibonacci(n - 2);
  }

  @Override
  public PersonModel createGetDeletePersonTestCase(PersonModel person)
      throws InternalServerErrorException, ResourceNotFoundException {
    long i = personDao.insertPerson(person);
    person = personDao.getPersonById(i);
    personDao.deletePersonById(person.getId());
    return person;
  }

  @Override public Person createGetDeletePersonORMTestCase(Person person) {
    long i = personDao.insertPersonORM(person);
    person = personDao.getPersonByIdORM(i);
    personDao.deletePersonByIdORM(person);
    return person;
  }
}
