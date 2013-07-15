package org.sdmlib.models.patterns.example.creators;

import org.sdmlib.models.patterns.example.creators.CreatorCreator;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.models.patterns.example.SimpleState;

public class SimpleStateCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      SimpleState.PROPERTY_NODES,
   };
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new SimpleState();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((SimpleState) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (JsonIdMap.REMOVE.equals(type))
      {
         attrName = attrName + type;
      }
      return ((SimpleState) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((SimpleState) entity).removeYou();
   }
}

