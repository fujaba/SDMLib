package de.uniks.jism.test.model.ludo.creator;

import org.sdmlib.serialization.interfaces.SendableEntityCreator;
import org.sdmlib.serialization.json.JsonIdMap;

import de.uniks.jism.test.model.ludo.Dice;

public class DiceCreator  implements SendableEntityCreator
{
   private final String[] properties = new String[]
   {
      Dice.PROPERTY_VALUE,
      Dice.PROPERTY_GAME,
      Dice.PROPERTY_PLAYER,
   };
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new Dice();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((Dice) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (JsonIdMap.REMOVE.equals(type))
      {
         attrName = attrName + type;
      }
      return ((Dice) target).set(attrName, value);
   }
}

