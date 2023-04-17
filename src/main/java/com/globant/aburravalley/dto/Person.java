package com.globant.aburravalley.dto;

import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Objeto persona para crear el Dao
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Person {
  private String name;
  private String lastName;
  private Date birthday;
  private String email;
  
  // una persona tiene un documento
  private Document identityDocument;
  
  // una persona tiene varias direcciones
  private List<Address> addresses;
}
