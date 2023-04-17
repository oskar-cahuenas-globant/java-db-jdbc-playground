package com.globant.aburravalley.database.mysql;

import com.globant.aburravalley.dto.Person;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Ejercicio:
 * - Método buscar Documento por id persona
 * - Método buscar lista direcciones por Id persona
 * - Completar el método personByEmail para que se traiga el registro de
 * persona "con todos los juguetes"
 */
public class DemoSelect {
  
  // query buscar persona por email
  // se observa que el WHERE tiene un parámetro con la ?
  private final String PERSON_BY_EMAIL_QUERY =
      "SELECT `people`.`id`," +
          "    `people`.`name`," +
          "    `people`.`last_name`," +
          "    `people`.`email`," +
          "    `people`.`birthday`" +
          "FROM `test_jdbc`.`people` " +
          "WHERE `people`.`email` = ?;";

  // query buscar todas las personas
  private final String ALL_PEOPLE_QUERY =
      "SELECT `people`.`id`," +
          "    `people`.`name`," +
          "    `people`.`last_name`," +
          "    `people`.`email`," +
          "    `people`.`birthday`" +
          "FROM `test_jdbc`.`people`;";
  
  private MySqlUtil util;
  
  public DemoSelect(MySqlUtil util) {
    this.util = util;
  }
  
  /**
   * Crea una instancia de persona a partir de la posición actual del
   * resultset
   *
   * @param rs Resultset a usar
   * @return instancia de Persona
   * @throws  SQLException si hay una falla en la cartga de datos del resultset
   */
  private Person personFromResultset(ResultSet rs) throws SQLException  {
    // armar el resultado de retorno
    int id = rs.getInt("id");
    String name = rs.getString("name");
    String lastName = rs.getString("last_name");
    String resultEmail = rs.getString("email");
    Date birthday = new Date(rs.getDate("birthday").getTime());
  
    // se usa el Builder de Lombok para crear la clase
    return Person
        .builder()
        .name(name)
        .lastName(lastName)
        .email(resultEmail)
        .birthday(birthday)
        .build();
  }
  
  /**
   * Busca en BD una persona por email
   * No llena los campos address ni documento
   *
   * @param email email de la persona
   * @return persona en BD o null si no existe
   */
  public Person personByEmail(String email) throws  Exception {
    Connection c = null;
    PreparedStatement statement = null;
    ResultSet rs = null;
    try {
      c = util.createConnection();
      statement = c.prepareStatement(PERSON_BY_EMAIL_QUERY);
      // agregar los parámetros de la query
      statement.setString(1, email);
      // ejecutar el query
      rs = statement.executeQuery();
      // si no viene nada en la consulta se retorna nulo
      if (!rs.next()) {
        return null;
      }
      // Ejercicio ¿Cómo completarían la carga de persona
      // con su documento y direcciones?
      return personFromResultset(rs);
    } finally {
      if (rs != null) {
        rs.close();
      }
      if (statement != null) {
        statement.close();
      }
      if (c != null) {
        c.close();
      }
    }
  }
  
  public List<Person> allPeople() throws  Exception {
    Connection c = null;
    Statement statement = null;
    ResultSet rs = null;
    try {
      c = util.createConnection();
      // statement sin parametros
      statement = c.createStatement();
      // agregar los parámetros de la query
      // ejecutar el query
      rs = statement.executeQuery(ALL_PEOPLE_QUERY);
      List<Person> people = new ArrayList<>();
      // se itera mientras hayan resultados en el Result set
      while (rs.next()) {
        people.add(personFromResultset(rs));
      }
      // Ejercicio ¿Cómo completarían la carga de persona
      // con su documento y direcciones?
      return people;
    } finally {
      if (rs != null) {
        rs.close();
      }
      if (statement != null) {
        statement.close();
      }
      if (c != null) {
        c.close();
      }
    }
  }
}
