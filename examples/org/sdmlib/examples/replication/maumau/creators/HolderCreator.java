package org.sdmlib.examples.replication.maumau.creators;

import org.sdmlib.examples.replication.maumau.creators.CreatorCreator;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.examples.replication.maumau.Holder;

public class HolderCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      Holder.PROPERTY_DECKOWNER,
      Holder.PROPERTY_CARDS,
      Holder.PROPERTY_STACKOWNER,
   };
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new Holder();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((Holder) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (JsonIdMap.REMOVE.equals(type))
      {
         attrName = attrName + type;
      }
      return ((Holder) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((Holder) entity).removeYou();
   }
}

