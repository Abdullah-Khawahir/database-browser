package databaseIO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.swing.JOptionPane;

public class Database {
   public static String name = DatabaseAccess.name;
   private final static String QUERY_GET_STATUS = "show table status from " + name;
   private static LinkedList<Table> tables;
   static int numberOfTables;

   public Database() {
      init();
   }

   private static void init() {
      Database.tables = new LinkedList<Table>();
      numberOfTables = 0;
      getTablesInfo(DatabaseAccess.DatabaseConnection);
      getEachColumnName();
      getEachColumnType();

   }

   private static void getEachColumnType() {
      for (Table table : tables) {
         ResultSet rs = Query.QUERY("desc " + table.name);
         try {
            while (rs.next()) {
               table.dataType.add(rs.getString(2));
            }
         } catch (SQLException e) {
            // TODO Auto-generated catch block
            JOptionPane.showMessageDialog(null, e.getErrorCode() + " : " + "username or password is not correct",
                  "SQL ERROR", 0);
            System.out.println(e.getErrorCode() + " : " + e.getMessage());
         }
      }
   }

   private static void getEachColumnName() {
      for (int i = 0; i < numberOfTables; i++) {
         ResultSet rs1 = Query.GET_TABLE_INFO(tables.get(i).name);

         try {
            while (rs1.next()) {
               Database.tables.get(i).columnNames.add(rs1.getString(1));
            }
         } catch (SQLException e) {
            // TO/DO Auto-generated catch block
            JOptionPane.showMessageDialog(null, e.getErrorCode() + " : " + e.getMessage(), "SQL ERROR", 0);
            System.out.println(e.getErrorCode() + " : " + e.getMessage());
         }

      }
   }

   private static void getTablesInfo(Connection connection) {
      try {
         ResultSet tablesStatus = connection.createStatement().executeQuery(QUERY_GET_STATUS);

         while (tablesStatus.next()) {
            Database.tables.add(new Table(tablesStatus.getString(1), tablesStatus.getInt(5)));
            numberOfTables++;
         }
      } catch (SQLException e) {
         Query.SQLExceptionProc(e);
      }
   }

   public String toString() {
      String str = "";
      for (Table table : tables) {
         str += table.toString();
      }
      return str;
   }

   public static LinkedList<Table> getTables() {

      return Database.tables;
   }

   public static void updateTables() {
      for (Table table : tables) {
         table.update();
      }
   }
}
