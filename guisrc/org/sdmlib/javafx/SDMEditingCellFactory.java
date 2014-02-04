package org.sdmlib.javafx;

import javafx.event.EventHandler;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Callback;

public class SDMEditingCellFactory<ObjType, ValueType> implements Callback<TableColumn<ObjType, ValueType>, TableCell<ObjType, ValueType>>
{

   @Override
   public TableCell call(TableColumn param)
   {
      return new EditingCell<ObjType, ValueType>();
   }

   public class EditingCell<ObjType, ValueType> extends TableCell<ObjType, ValueType> 
   {
      private TextField textField;

      public EditingCell() 
      {

      }

      @Override public void startEdit() 
      {
         super.startEdit();

         if (textField == null) 
         {
            createTextField();
         }

         setText(null);

         setGraphic(textField);

         textField.selectAll();
      }

      @Override public void cancelEdit() {

         super.cancelEdit();

         setText((String) getItem());

         setGraphic(null);
      }


      @Override public void updateItem(ValueType item, boolean empty) 
      {
         super.updateItem(item, empty);

         if (empty) 
         {
            setText(null);

            setGraphic(null);
         } 
         else 
         {
            if (isEditing()) 
            {
               if (textField != null) 
               {
                  textField.setText(getString());
               }

               setText(null);

               setGraphic(textField);
            } 
            else 
            {
               setText(getString());

               setGraphic(null);
            }
         }
      }

      private void createTextField() 
      {
         textField = new TextField(getString());

         textField.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);

         textField.setOnKeyReleased(new EventHandler<KeyEvent>() {

            @Override public void handle(KeyEvent t) 
            {
               if (t.getCode() == KeyCode.ENTER) 
               {
                  commitEdit((ValueType) textField.getText());
               } 
               else if (t.getCode() == KeyCode.ESCAPE) 
               {
                  cancelEdit();
               }
            }
         });
      }

      private String getString() 
      {
         return getItem() == null ? "" : getItem().toString();
      }
   } 

}
