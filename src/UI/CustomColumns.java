package UI;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;

import databaseIO.Database;

/**
 * JoinUI.CustomCoumns
 */
public class CustomColumns extends JFrame {
   String tableName;
   public LinkedList<JCheckBox> columns;
   private JButton close, apply;

   public CustomColumns(int TableIndex, String tableName) {
      this.tableName = tableName;
      apply = new JButton("apply");
      close = new JButton("Close");
      this.columns = new LinkedList<JCheckBox>();
      this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
      this.setLayout(new GridLayout());

      Database.getTables().get(TableIndex).getColumnNames()
            .forEach(columnName -> {
               JCheckBox cb = new JCheckBox(columnName);
               if (this.columns.isEmpty()) {
                  cb.setLocation(30, 30);
               } else {
                  cb.setLocation(columns.getLast().getY() + 10, 30);
               }
               this.columns.add(cb);
               this.add(cb);
               cb.setVisible(true);
            });
      apply.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            applyActionPerformed(evt);
         }
      });

      close.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            closeActionPerformed(evt);
         }
      });

      this.add(apply);
      this.add(close);
      pack();
      this.setVisible(true);

   }

   private void applyActionPerformed(ActionEvent evt) {
      columns.forEach(checkBox -> {
         if (checkBox.isSelected()) {
            JoinUI.getSelectedColumns().add(this.tableName + "." + checkBox.getText());

         } else
            ;
      });
      JoinUI.getSelectedColumns().forEach(column -> System.out.println(column));
      dispose();
   }

   private void closeActionPerformed(ActionEvent evt) {
      dispose();
   }

}