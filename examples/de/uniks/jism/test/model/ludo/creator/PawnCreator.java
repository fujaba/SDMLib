package de.uniks.jism.test.model.ludo.creator;

import org.sdmlib.serialization.interfaces.SendableEntityCreator;
import org.sdmlib.serialization.json.JsonIdMap;

import de.uniks.jism.test.model.ludo.Pawn;

public class PawnCreator implements SendableEntityCreator
{
   private final String[] properties = new String[]
   { Pawn.PROPERTY_COLOR, Pawn.PROPERTY_X, Pawn.PROPERTY_Y,
         Pawn.PROPERTY_PLAYER, Pawn.PROPERTY_POS, };

   public String[] getProperties()
   {
      return properties;
   }

   public Object getSendableInstance(boolean reference)
   {
      return new Pawn();
   }

   public Object getValue(Object target, String attrName)
   {
      return ((Pawn) target).get(attrName);
   }

   public boolean setValue(Object target, String attrName, Object value,
         String type)
   {
      if (JsonIdMap.REMOVE.equals(type))
      {
         attrName = attrName + type;
      }
      return ((Pawn) target).set(attrName, value);
   }
}
