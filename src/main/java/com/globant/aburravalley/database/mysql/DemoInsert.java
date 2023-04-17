package com.globant.aburravalley.database.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Date;

/**
 * EJERCICIO:
 * - Hacer métodos de insert de documents y addresses
 * - Hacer método que dado un objeto persona con sus direcciones y documentos
 * inserte todo.
 */
public class DemoInsert {
  
  // query base con parámetros insertar persona
  private static final String QUERY_INSERT_PERSON =
      "INSERT INTO `test_jdbc`.`people` " +
      "(`name`, " +
      "`last_name`, " +
      "`email`, " +
      "`birthday`) " +
      "VALUES " +
      "(?,?,?,?);";
  
  // query base con parámetros insertar documento
  private static final String QUERY_INSERT_DOCUMENT =
      "INSERT INTO `test_jdbc`.`addresses`\n" +
          "(`person_id`, " +
          "`address1`, " +
          "`address2`, " +
          "`address3`, " +
          "`city`, " +
          "`province`, " +
          "`country`) " +
          "VALUES " +
          "(?,?,?,?,?,?,?);";
  
  // query base con parámetros insertar direccion
  private static final String QUERY_INSERT_ADDRESS = "";
  
  private MySqlUtil util;
  
  public DemoInsert(MySqlUtil util) {
    this.util = util;
  }
  
  public int insertPersonRecord (
      String name,
      String lastName,
      String email,
      Date birthday) throws Exception {
    
    // se crea un statement preparado para setear parametros
    PreparedStatement statement = null;
    Connection c = null;
    try {
      c = util.createConnection();
      // se crea la sentencia preparada par insertar persoan
      statement = c.prepareStatement(QUERY_INSERT_PERSON);
      
      // se establecen parámetros para la sentencia
      // los parámetros se cuentan desde 1
      // y tal como vienen en el orden en la query
      statement.setString(1,name);
      statement.setString(2,lastName);
      statement.setString(3,email);
      // en el caso fecha
      // el tipo Date a insertar es distinto, pues es
      // sql.Date y no java.util.Date. Debe crearse
      statement.setDate(4, new java.sql.Date(birthday.getTime()));
      
      // ejecución de la sentencia
      // se ejecuta "tipo update" es decir, una consulta que no devuelve nada
      statement.executeUpdate();
      // se devuelve el último autoincremental
      return util.lastAutoIncrement(c);
      
    } finally {
      if (statement != null) {
        statement.close();
      }
      if (c != null) {
        c.close();
      }
    }
  }
  
  public int insertAddressRecord() {
    return 0;
  }
  
  public int insertDocumentRecord() {
    return 0;
  }
  
  
  
  
}
