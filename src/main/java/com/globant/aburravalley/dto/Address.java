package com.globant.aburravalley.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Objeto dirección para crear el Dao
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address {
  private String address1;
  private String address2;
  private String address3;
  private String city;
  private String province;
  private String country;
}
