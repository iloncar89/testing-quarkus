package com.iloncar.dao.impl;

import com.iloncar.dao.api.PersonDao;
import com.iloncar.domain.Person;
import com.iloncar.exception.InternalServerErrorException;
import com.iloncar.exception.ResourceNotFoundException;
import com.iloncar.model.PersonModel;
import io.agroal.api.AgroalDataSource;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@ApplicationScoped
public class PersonDaoImpl implements PersonDao {
  AgroalDataSource dataSource;

  EntityManager entityManager;

  public PersonDaoImpl(AgroalDataSource dataSource, EntityManager entityManager) {
    this.dataSource = dataSource;
    this.entityManager = entityManager;
  }
  @Override public long insertPerson(final PersonModel person) throws InternalServerErrorException {
      try(Connection connection = dataSource.getConnection()) {
        final String insertPersonSql = "INSERT INTO person (first_name, last_name, year_of_birth) VALUES(?,?,?) returning id";
        PreparedStatement preparedStatement = connection.prepareStatement(insertPersonSql, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, person.getFirstName());
        preparedStatement.setString(2, person.getLastName());
        preparedStatement.setInt(3, person.getYearOfBirth());

        int affectedRows = preparedStatement.executeUpdate();

        if (affectedRows == 0) {
          throw new SQLException("Creating user failed, no rows affected.");
        }

        try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
          if (generatedKeys.next()) {
            return generatedKeys.getLong(1);
          }
        }
      } catch (SQLException e) {
        throw new InternalServerErrorException(e.getMessage());
      }
      throw new InternalServerErrorException("Could not get DB connection");
    }
  @Override public PersonModel getPersonById(final long id) throws InternalServerErrorException,
      ResourceNotFoundException {
    try(Connection connection = dataSource.getConnection()) {
      final String getPersonByIdSql = "SELECT * FROM person WHERE id=?";
      PreparedStatement preparedStatement = connection.prepareStatement(getPersonByIdSql);
      preparedStatement.setLong(1, id);
      ResultSet rs = preparedStatement.executeQuery();

      if (rs.next()){
        return new PersonModel(rs.getLong("id"),rs.getString("first_name"), rs.getString("last_name"), rs.getInt("year_of_birth"));
      }
    } catch (SQLException e){
      throw new InternalServerErrorException(e.getMessage());
    }
    throw new ResourceNotFoundException("Could not found resource");
  }
  @Override public void deletePersonById(final long id) throws InternalServerErrorException {
    try(Connection connection = dataSource.getConnection()) {
      String deletePersonByIdSql = "Delete from person where id = ?;";
      PreparedStatement preparedStatement = connection.prepareStatement(deletePersonByIdSql);
      preparedStatement.setLong(1, id);
      int result = preparedStatement.executeUpdate();
      if (result <= 0) {
        throw new InternalServerErrorException("Could not delete from database");
      }
    } catch (SQLException e){
      throw new InternalServerErrorException("Could not delete from database");
    }
  }

  @Override public long insertPersonORM(Person person) {
    entityManager.persist(person);
    return person.getId();
  }

  @Override public Person getPersonByIdORM(final long id) {
    return entityManager.find(Person.class, id);
  }

  @Override public void deletePersonByIdORM(final Person person) {
    entityManager.remove(person);
  }
}
