package org.sdmlib.examples.adamandeve.creators;

import org.sdmlib.examples.adamandeve.Eve;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;

public class EveCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
   };
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new Eve();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((Eve) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((Eve) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((Eve) entity).removeYou();
   }
}

