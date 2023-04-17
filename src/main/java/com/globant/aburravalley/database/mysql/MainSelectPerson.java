package com.globant.aburravalley.database.mysql;

import com.globant.aburravalley.dto.Person;

/**
 * Clase con el main para los demos de insertar persona
 */
public class MainSelectPerson {
 
  public static void main(String[] args) throws Exception {
    MySqlUtil util = new MySqlUtil();
    DemoSelect demoSelect = new DemoSelect(util);
    
    // bucar persona por email
    Person p = demoSelect.personByEmail("pedro@gmail.com");
    
    if (p == null) {
      System.out.println("No se encontró ninguna persona con el email: pedro@gmail.com" );
    } else {
      System.out.println("Persona encontrada: " + p);
    }
   
  }
  
}