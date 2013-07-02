package org.sdmlib.examples.replication.maumau.creators;

import org.sdmlib.examples.replication.maumau.creators.CreatorCreator;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.examples.replication.maumau.Player;
import org.sdmlib.examples.replication.maumau.Holder;

public class PlayerCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      Player.PROPERTY_NAME,
      Holder.PROPERTY_DECKOWNER,
      Holder.PROPERTY_CARDS,
      Holder.PROPERTY_STACKOWNER,
      Player.PROPERTY_GAME,
      Player.PROPERTY_NEXT,
      Player.PROPERTY_PREV,
      Player.PROPERTY_ASSIGNMENT,
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
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (JsonIdMap.REMOVE.equals(type))
      {
         attrName = attrName + type;
      }
      return ((Player) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((Player) entity).removeYou();
   }
}

