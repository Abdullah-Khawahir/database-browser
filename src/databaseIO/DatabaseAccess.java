package databaseIO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseAccess {
   static String name;
   String URL, username, password;
   static Connection DatabaseConnection;
   static boolean isInitilized;
   public static Database database;

   public DatabaseAccess(String url, String username, String password) throws SQLException {
      this.URL = url;
      this.username = username;
      this.password = password;
      setName();
      try {
         DatabaseAccess.DatabaseConnection = DriverManager.getConnection(url, username, password);
         DatabaseAccess.database = new Database();
         DatabaseAccess.isInitilized = true;

      } catch (SQLException e) {
         Query.SQLExceptionProc(e);

      }

   }

   private void setName() {
      int pointer = URL.lastIndexOf("/") + 1;
      String str = "";
      while (URL.length() != pointer) {
         str += URL.charAt(pointer);
         pointer++;
      }
      DatabaseAccess.name = str;
   }

   public static Connection getDatabaseConnection() {
      if (isInitilized)
         return DatabaseConnection;
      else
         return null;

   }

}
