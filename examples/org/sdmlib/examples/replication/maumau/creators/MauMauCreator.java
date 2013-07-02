package org.sdmlib.examples.replication.maumau.creators;

import org.sdmlib.examples.replication.maumau.creators.CreatorCreator;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.examples.replication.maumau.MauMau;

public class MauMauCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      MauMau.PROPERTY_CARDS,
      MauMau.PROPERTY_DECK,
      MauMau.PROPERTY_STACK,
      MauMau.PROPERTY_PLAYERS,
      MauMau.PROPERTY_CURRENTMOVE,
   };
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new MauMau();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((MauMau) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (JsonIdMap.REMOVE.equals(type))
      {
         attrName = attrName + type;
      }
      return ((MauMau) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((MauMau) entity).removeYou();
   }
}

