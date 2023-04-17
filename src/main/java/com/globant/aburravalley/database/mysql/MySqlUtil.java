package com.globant.aburravalley.database.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Utilitarios para conexión mySQL
 */
public class MySqlUtil {
  private static final String DB_URL = "jdbc:mysql://localhost/test_jdbc";
  private static final String USER = "root";
  private static final String PASS = "Root.123";
  private static final String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
  private static final String LAST_ID_QUERY = "SELECT LAST_INSERT_ID() AS ID";
  
  
  /**
   * Crea una instancia de conexión
   *
   * @return nueva conexión a la instancia de mysql
   * @throws SQLException si falla la creacion de conexión
   * @throws ClassNotFoundException si hay un error en la carga de la
   * clase con el driver SQL
   */
  public Connection createConnection() throws SQLException, ClassNotFoundException {
    // truco para que el Loader de Java cargue la clase
    // driver de SQL antes del llamado de la conexión
    // En Java 17 no debería ser necesario
    // como ejercicio quitar esta línea y probar
    Class.forName(DRIVER_CLASS_NAME);
    return DriverManager.getConnection(DB_URL, USER, PASS);
  }
  
  /**
   * Cierra conexión SQL si no es nula
   *
   * @param c instancia de conexión a BD
   */
  public void closeConnection(Connection c) {
    if (c != null) {
      try {
        c.close();
      } catch (SQLException e) {
        // si hay un error de cierre se reporta
        e.printStackTrace();
      }
    }
  }
  
  /**
   * Devuelve el último auto incremental usando la consulta
   * SELECT LAST_INSERT_ID() AS id
   * ver https://dev.mysql.com/doc/refman/8.0/en/information-functions.html#function_last-insert-id
   *
   * @param c conexión mysql con la transacción actual
   * @throws SQLException si hay un error en la ejecución de la consulta
   */
  public int lastAutoIncrement(Connection c) throws SQLException {
    ResultSet rs = null;
    try {
      final Statement statement = c.createStatement();
      rs = statement.executeQuery(LAST_ID_QUERY);
      // el query siempre debe devolver resultado
      // pero de manera defensiva, si no vienen más resultados
      // se devuelve -1
      if (!rs.next()) {
        return -1;
      }
      // se saca la columna ID, pues ese fue el nombre
      // que se estableció en la query con la sentencia AS
      final int id = rs.getInt("ID");
      // el query no debería devolver nulo
      // pero de manera defensiva si el resultado es nulo
      // se devuelve -1
      return rs.wasNull() ? -1 : id;
    } finally {
      if (rs != null) {
        rs.close();
      }
    }
  }
}
