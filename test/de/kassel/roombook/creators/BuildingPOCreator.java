package de.kassel.roombook.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class BuildingPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new BuildingPO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((BuildingPO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((BuildingPO) target).set(attrName, value);
   }
}

