package org.sdmlib.javafx;

import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public class SDMColumnBinding<ObjType, ValueType>
{
   public void makeEditable(TableColumn<ObjType, ValueType> column,
         String propertyName)
   {
      column.setCellValueFactory(new PropertyValueFactory<ObjType, ValueType>(
            propertyName));
      column.setCellFactory(new SDMEditingCellFactory<ObjType, ValueType>());
      column.setOnEditCommit(new SDMEventHandler<ObjType, ValueType>(
            propertyName));
      column.setEditable(true);
   }
}
