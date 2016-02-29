package org.sdmlib.serialization.util;

import org.sdmlib.serialization.EntityFactory;
import org.sdmlib.serialization.SDMLibJsonIdMap;

import de.uniks.networkparser.IdMap;

public class JsonIdMapCreator extends EntityFactory
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
      return new IdMap();
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
      return org.sdmlib.models.pattern.util.PatternCreator.createIdMap(sessionID);
   }

   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((SDMLibJsonIdMap) entity).removeYou();
   }
}

