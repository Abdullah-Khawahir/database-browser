package luncher;

import java.sql.SQLException;

import javax.swing.JTextField;

import UI.MainUI;
import UI.login;
import databaseIO.DatabaseAccess;

// for standard JDBC programs

public class App {
   // private static String databaseName = "un";
   static String DB_URL = "jdbc:mysql://localhost/un";
   static String USSR = "root";

   static String PASS = "4484";
   private static MainUI mainUI;
   public static DatabaseAccess access;
   // static login log;

   public static void main(String[] args) throws SQLException {
      // log = new login();
      // log.run();

      new login();
      access = new DatabaseAccess(DB_URL, USSR, PASS);
      System.out.println("passed login");

      // mainUI = new MainUI();
      // mainUI.run();

   }

   public static MainUI getMainUI() {
      return mainUI;
   }

   public static void setURL(String url) {
      App.DB_URL = url;
   }

   public static String getURL() {
      return App.DB_URL;
   }

   public static String getUSER() {
      return USSR;
   }

   public static void setUSER(String USER) {
      USSR = USER;
   }

   public static String getPASS() {
      return PASS;
   }

   public static void setPASS(String pASS) {
      PASS = pASS;
   }
}
