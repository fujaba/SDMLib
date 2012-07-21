package org.sdmlib.examples.ludo.creators;

import org.sdmlib.examples.ludo.creators.CreatorCreator;
import org.sdmlib.serialization.interfaces.SendableEntityCreator;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.examples.ludo.Pawn;

public class PawnCreator implements SendableEntityCreator
{
   private final String[] properties = new String[]
   {
      Pawn.PROPERTY_COLOR,
      Pawn.PROPERTY_X,
      Pawn.PROPERTY_Y,
      Pawn.PROPERTY_PLAYER,
      Pawn.PROPERTY_POS,
   };
   
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
   
   public boolean setValue(Object target, String attrName, Object value)
   {
      return ((Pawn) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }
}

