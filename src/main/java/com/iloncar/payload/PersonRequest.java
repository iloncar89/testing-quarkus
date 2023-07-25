package com.iloncar.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonRequest {
  private String firstName;
  private String lastName;
  private int yearOfBirth;
}
