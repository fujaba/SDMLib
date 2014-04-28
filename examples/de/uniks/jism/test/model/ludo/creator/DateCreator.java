package de.uniks.jism.test.model.ludo.creator;

import java.util.Date;

import org.sdmlib.serialization.interfaces.SendableEntityCreator;
import org.sdmlib.serialization.json.JsonIdMap;

public class DateCreator implements SendableEntityCreator
{
   private final String[] properties = new String[]
   {};

   public String[] getProperties()
   {
      return properties;
   }

   public Object getSendableInstance(boolean reference)
   {
      return new Date();
   }

   public Object getValue(Object target, String attrName)
   {
      return null;
   }

   public boolean setValue(Object target, String attrName, Object value,
         String type)
   {
      if (JsonIdMap.REMOVE.equals(type))
      {
         attrName = attrName + type;
      }
      return false;
   }
}
