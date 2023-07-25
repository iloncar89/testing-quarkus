package com.iloncar.service.api;

import com.iloncar.exception.InternalServerErrorException;
import com.iloncar.exception.ResourceNotFoundException;
import com.iloncar.domain.Person;
import com.iloncar.model.PersonModel;

public interface TestService {
  long calculateFibonacci(long n);

  PersonModel createGetDeletePersonTestCase(PersonModel person)
      throws InternalServerErrorException, ResourceNotFoundException;

  Person createGetDeletePersonORMTestCase(Person person) throws ResourceNotFoundException;
}
