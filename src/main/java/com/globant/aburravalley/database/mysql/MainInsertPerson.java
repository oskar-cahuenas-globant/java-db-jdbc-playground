package com.globant.aburravalley.database.mysql;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;

/**
 * Clase con el main para los demos de insertar persona
 */
public class MainInsertPerson {
  
  static final String DB_URL = "jdbc:mysql://localhost/test_jdbc";
  static final String USER = "root";
  static final String PASS = "Root.123";
  
  public static void main(String[] args) throws Exception {
    MySqlUtil util = new MySqlUtil();
    ZoneId defaultZoneId = ZoneId.systemDefault();
    DemoInsert demoInsert = new DemoInsert(util);
    
    // se inserta una persona pedro perez
    int id = demoInsert.insertPersonRecord(
        "pedro",
        "perez",
        "pedro@gmail.com",
        Date.from(LocalDate.of(1980,1,23)
            .atStartOfDay(defaultZoneId)
            .toInstant()));
    System.out.println("Se insertó el registro en la tabla `people` con id=" + id);
  
    // se inserta una persona maria rodriguez
    id = demoInsert.insertPersonRecord(
        "maria",
        "roriguez",
        "maria@gmail.com",
        Date.from(LocalDate.of(1995,12,18)
            .atStartOfDay(defaultZoneId)
            .toInstant()));
    System.out.println("Se insertó el registro en la tabla `people` con id=" + id);
  
  
  }
  
}