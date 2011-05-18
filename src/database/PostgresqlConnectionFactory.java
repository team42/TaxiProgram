package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 
 * Create connection to the local database.
 * 
 * @author Lasse
 *
 */
public class PostgresqlConnectionFactory {
   /*
	private static final String URL = "jdbc:postgresql://192.168.1.10/nicolai3sf11";
   	private static final String USERNAME = "nicolai3sf11";
   	private static final String PASSWORD = "ihk100050eit";
    */
   private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
   private static final String USERNAME = "postgres";
   private static final String PASSWORD = "gulle321";
   
   /**
    * Returns a connection defined by URL, Username and Password
    * 
    * @return
    * @throws SQLException
    */
   public static Connection createConnection() throws SQLException {
      return DriverManager.getConnection(URL, USERNAME, PASSWORD);
   }

}
