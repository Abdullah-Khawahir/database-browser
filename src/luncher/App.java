package luncher;

import java.sql.SQLException;

import UI.MainUI;
import databaseIO.DatabaseAccess;

// for standard JDBC programs

public class App {
   private static String databaseName = "un";
   static final String DB_URL = "jdbc:mysql://localhost/" + databaseName;
   static final String USSR = "root";
   static final String PASS = "4484";
   private static MainUI mainUI;
   static DatabaseAccess access;
   // static login log;

   public static void main(String[] args) throws SQLException {
      // log = new login();
      // log.run();
      access = new DatabaseAccess(DB_URL, USSR, PASS);
      mainUI = new MainUI();
      mainUI.run();

   }

   public static MainUI getMainUI() {
      return mainUI;
   }
}
