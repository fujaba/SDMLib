package org.sdmlib.examples.ludo.creators;

import org.sdmlib.examples.ludo.creators.CreatorCreator;
import org.sdmlib.serialization.interfaces.SendableEntityCreator;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.examples.ludo.Player;

public class PlayerCreator implements SendableEntityCreator
{
   private final String[] properties = new String[]
   {
      Player.PROPERTY_COLOR,
      Player.PROPERTY_NAME,
      Player.PROPERTY_X,
      Player.PROPERTY_Y,
      Player.PROPERTY_GAME,
      Player.PROPERTY_NEXT,
      Player.PROPERTY_PREV,
      Player.PROPERTY_DICE,
      Player.PROPERTY_START,
      Player.PROPERTY_PAWNS,
      Player.PROPERTY_BASE,
      Player.PROPERTY_LANDING,
   };
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new Player();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((Player) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value)
   {
      return ((Player) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }
}


