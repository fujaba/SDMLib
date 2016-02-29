package org.sdmlib.models.pattern.util;

import org.sdmlib.serialization.EntityFactory;

import de.uniks.networkparser.IdMap;

public class StringBuilderCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new StringBuilder();
   }
   
   @Override
   public Object getValue(Object target, String attrName)
   {
      return null;
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return false;
   }
   
   public static IdMap createIdMap(String sessionID)
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

