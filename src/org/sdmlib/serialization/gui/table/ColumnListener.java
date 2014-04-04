package org.sdmlib.serialization.gui.table;

/*
 NetworkParser
 Copyright (c) 2011 - 2013, Stefan Lindel
 All rights reserved.

 Licensed under the EUPL, Version 1.1 or (as soon they
 will be approved by the European Commission) subsequent
 versions of the EUPL (the "Licence");
 You may not use this work except in compliance with the Licence.
 You may obtain a copy of the Licence at:

 http://ec.europa.eu/idabc/eupl5

 Unless required by applicable law or agreed to in
 writing, software distributed under the Licence is
 distributed on an "AS IS" basis,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 express or implied.
 See the Licence for the specific language governing
 permissions and limitations under the Licence.
 */
import org.sdmlib.serialization.IdMapEncoder;
import org.sdmlib.serialization.interfaces.SendableEntityCreator;

public class ColumnListener
{
   protected Column column;

   public ColumnListener withColumn(Column column)
   {
      this.column = column;
      return this;
   }

   public Object getValue(Object entity, SendableEntityCreator creator)
   {
      if (creator != null && column != null)
      {
         return creator.getValue(entity, column.getAttrName());
      }
      return null;
   }

   public boolean canEdit(Object entity, SendableEntityCreator creator)
   {
      return column.isEditable();
   }

   public void onSelection(Object entity, SendableEntityCreator creator, int x,
         int y)
   {
   }

   public CellEditorElement onEdit(Object entity, SendableEntityCreator creator)
   {
      return null;
   }

   public boolean isFinish()
   {
      return false;
   }

   public boolean setValue(Object controll, Object entity,
         SendableEntityCreator creator, Object value)
   {
      if (creator == null)
      {
         return false;
      }
      return creator.setValue(entity, column.getAttrName(), value,
         IdMapEncoder.UPDATE);
   }

   public void dispose()
   {

   }

   public boolean updateWidth(int oldWidth, int newWidth)
   {
      return true;
   }

   public void refresh(Object cell)
   {
   }

   public void startEdit(CellEditorElement editField)
   {
   }

}
