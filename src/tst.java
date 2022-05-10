import java.util.LinkedList;
import java.util.stream.Collectors;

import databaseIO.Database;

public class tst {

   static String URL = "jdbc:mysql://localhost/xx";

   public static void main(String[] args) {

      int pointer = URL.lastIndexOf("/")+1;
      String str = "";
      while (URL.length() != pointer) {
         str += URL.charAt(pointer);
         pointer++;
      }
      System.out.println(str);

      // Query.QUERY("select * from students inner join on
      // students.id=account.id");

      // LinkedList<String> productsList = new LinkedList<>();
      // // Adding Products
      // productsList.add("asc");
      // productsList.add("asc");
      // productsList.add("asc");
      // productsList.add("asc");
      // productsList.add(new Product(2, "Dell Laptop", 30000f));
      // productsList.add(new Product(3, "Lenevo Laptop", 28000f));
      // productsList.add(new Product(4, "Sony Laptop", 28000f));
      // productsList.add(new Product(5, "Apple Laptop", 90000f));

      // System.out.println(productsList);

   }

}

class Product {
   int id;
   String name;
   float price;

   public Product(int id, String name, float price) {
      this.id = id;
      this.name = name;
      this.price = price;
   }
}