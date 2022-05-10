package databaseIO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class Table {
   public static final String Name = null;
   public String name;
   int numberOfRaws;
   byte primaryKeyIndex;
   LinkedList<String> foreignKeys;
   LinkedList<String> columnNames;
   LinkedList<String> dataType;

   public Table(String name, int size) {
      this.name = name;
      this.numberOfRaws = Query.GET_NUMBER_OF_ROWS(this.name);
      columnNames = new LinkedList<String>();
      dataType = new LinkedList<String>();
      foreignKeys = new LinkedList<String>();
   }

   public void update() {

      // TODO : databaseNmae needed;
      ResultSet rs = Query.GET_TABLE_CONTENT(this.name);
      int size = 1;
      byte indexCounter = 0;
      try {
         while (rs.next()) {

            if (rs.getString(4).contains("PRI")) {
               this.primaryKeyIndex = indexCounter;
            }
            if (rs.getString(4).contains("MUL")) {
               this.foreignKeys.add(columnNames.get(indexCounter));
            }
            indexCounter++;
            size++;
         }
         this.numberOfRaws = size;

      } catch (SQLException e) {
         // TODO Auto-genesrated catch block
         Query.SQLExceptionProc(e);
      }
   }

   public String toString() {

      String tableINFO = "";
      tableINFO += "name = " + this.name + "\nsize = " + this.numberOfRaws + "\n";
      int i = 1;
      for (String colunm : columnNames) {
         tableINFO += "\t" + i + ":" + colunm + "\n";
         i++;
      }
      return tableINFO;
   }

   public String getName() {
      return name;
   }

   public int getNumberOfRaws() {
      return numberOfRaws;
   }

   public LinkedList<String> getColumnNames() {
      return columnNames;
   }

   public LinkedList<String> getDataType() {
      return dataType;
   }

   public String getColumnToInsertSQL() {

      String str = "(";
      for (int i = 0; i < this.columnNames.size(); i++) {

         if (i != this.columnNames.size() - 1)
            str += this.getColumnNames().get(i) + " , ";
         else {
            str += this.getColumnNames().get(i);
         }

      }
      str += ")";
      return str;
   }

}
