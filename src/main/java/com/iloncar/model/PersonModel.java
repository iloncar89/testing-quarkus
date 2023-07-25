package com.iloncar.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonModel {
  private long id;
  private String firstName;
  private String lastName;
  private int yearOfBirth;
}
