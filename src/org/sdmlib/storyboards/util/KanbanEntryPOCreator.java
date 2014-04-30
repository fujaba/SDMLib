package org.sdmlib.storyboards.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;

public class KanbanEntryPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new KanbanEntryPO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((KanbanEntryPO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value)
   {
      return ((KanbanEntryPO) target).set(attrName, value);
   }
}
