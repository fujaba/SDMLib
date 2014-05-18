package org.sdmlib.models.pattern.util;

import org.sdmlib.serialization.EntityFactory;

import de.uniks.networkparser.json.JsonIdMap;

public class StringBuilderCreator extends EntityFactory
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
      return new StringBuilder();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return null;
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return false;
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      // wrapped object has no removeYou method
   }
}

