package databaseIO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.swing.JOptionPane;

public class Query {

   public static ResultSet QUERY(String sql) {
      return getResultSet(sql);
   }

   public static ResultSet GET_TABLE_CONTENT(String tableName) {
      return getResultSet("Select * From " + tableName);
   }

   public static ResultSet GET_DATABASE_STATUS(String databaseName) {
      return getResultSet("show table status from " + databaseName);
   }

   public static ResultSet GET_TABLE_INFO(String tableName) {
      return getResultSet("DESC " + tableName);
   }

   public static int GET_NUMBER_OF_ROWS(String tableName) {
      ResultSet rs = Query.GET_TABLE_CONTENT(tableName);
      int size = 0;
      try {
         while (rs.next()) {
            size++;
         }

      } catch (SQLException e) {
         SQLExceptionProc(e);
      }
      return size;
   }

   public static int getRowCount(String tableName) {

      ResultSet rs = QUERY("Select Count(*) as RowCount from " + tableName);

      try {
         rs.next();
         return rs.getInt(1);
      } catch (SQLException e) {
         SQLExceptionProc(e);
         return -1;
      }

   }

   private static ResultSet getResultSet(String sql) {
      try {

         ResultSet resultSet = DatabaseAccess.DatabaseConnection.createStatement().executeQuery(sql);

         return resultSet;
      } catch (SQLException e) {
         SQLExceptionProc(e, sql);
         return null;
      }

   }

   public static boolean executeUpdate(String sql) {
      try {
         return DatabaseAccess.DatabaseConnection.prepareStatement(sql).execute();
      } catch (SQLException e) {
         SQLExceptionProc(e, sql);
         return false;
      }

   }

   public static String QueryToString(ResultSet resultSet, int tableColumns) {
      String string = "";
      try {
         while (resultSet.next()) {
            for (int i = 1; i <= tableColumns; i++) {
               string += resultSet.getString(i) + "\t";
            }
            string += "\n";
         }
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         SQLExceptionProc(e);
      }
      return string;
   }

   public static String listToInsertSQL(LinkedList<String> list, LinkedList<String> DataTypes) {
      String str = "(";
      for (int i = 0; i < list.size(); i++) {
         if (DataTypes.get(i).contains("int")) {
            if (i != list.size() - 1)
               str += list.get(i) + " , ";
            else {
               str += list.get(i);
            }
         } else {
            if (i != list.size() - 1)
               str += " '" + list.get(i) + "' , ";
            else {
               str += " '" + list.get(i) + "' ";
            }
         }
      }
      str += ")";
      return str;
   }

   public static void SQLExceptionProc(SQLException e, String sql) {
      JOptionPane.showMessageDialog(null, e.getErrorCode() + " : " + e.getMessage(), "SQL ERROR", 0);
      System.err.println("Query ERROR WITH : " + sql);
      System.out.println(e.getErrorCode() + " : " + e.getMessage());
   }

   public static void SQLExceptionProc(SQLException e) {
      JOptionPane.showMessageDialog(null, e.getErrorCode() + " : " + e.getMessage(), "SQL ERROR", 0);
      System.err.println("Query ERROR WITH : " + e.getMessage());
      System.out.println(e.getErrorCode() + " : " + e.getMessage());
   }

}