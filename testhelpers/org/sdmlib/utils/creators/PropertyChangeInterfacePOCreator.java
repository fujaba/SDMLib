package org.sdmlib.utils.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class PropertyChangeInterfacePOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new PropertyChangeInterfacePO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((PropertyChangeInterfacePO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((PropertyChangeInterfacePO) target).set(attrName, value);
   }
}

