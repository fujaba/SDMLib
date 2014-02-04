package org.sdmlib.javafx;

import org.sdmlib.serialization.interfaces.SendableEntityCreator;
import org.sdmlib.storyboards.GenericIdMap;

import javafx.event.EventHandler;
import javafx.scene.control.TableColumn.CellEditEvent;

public class SDMEventHandler<ObjType, ValueType> implements EventHandler<CellEditEvent<ObjType, ValueType>>
{
   private GenericIdMap map = new GenericIdMap();
   
   private String attrName;
   
   public SDMEventHandler(String propertyName)
   {
      this.attrName = propertyName;
   }

   @Override
   public void handle(CellEditEvent<ObjType, ValueType> event)
   {
      ObjType obj = event.getTableView().getItems().get(event.getTablePosition().getRow());
      
      SendableEntityCreator creatorClass = map.getCreatorClass(obj);
      
      creatorClass.setValue(obj, attrName, event.getNewValue(), "");
   }
   
}
