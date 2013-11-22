package org.sdmlib.examples.clickcounter.creators;

import org.sdmlib.examples.clickcounter.creators.CreatorCreator;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.examples.clickcounter.Data;

public class DataCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      Data.PROPERTY_NUM,
      Data.PROPERTY_FXNUM,
   };
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new Data();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((Data) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (JsonIdMap.REMOVE.equals(type) && value != null)
      {
         attrName = attrName + type;
      }
      return ((Data) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((Data) entity).removeYou();
   }
}


