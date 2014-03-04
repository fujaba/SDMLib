package de.uniks.jism.test.model.ludo.creator;

import org.sdmlib.serialization.interfaces.SendableEntityCreator;
import org.sdmlib.serialization.json.JsonIdMap;

import de.uniks.jism.test.model.ludo.Ludo;

public class LudoCreator implements SendableEntityCreator
{
   private final String[] properties = new String[]
   {
      Ludo.PROPERTY_DATE,
      Ludo.PROPERTY_PLAYERS,
      Ludo.PROPERTY_DICE,
      Ludo.PROPERTY_FIELDS,
   };
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new Ludo();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((Ludo) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (JsonIdMap.REMOVE.equals(type))
      {
         attrName = attrName + type;
      }
      return ((Ludo) target).set(attrName, value);
   }
}

