package org.sdmlib.serialization.util;

import org.sdmlib.serialization.EntityFactory;
import org.sdmlib.serialization.SDMLibJsonIdMap;

import de.uniks.networkparser.json.JsonIdMap;

public class SDMLibJsonIdMapCreator extends EntityFactory
{
   @Override
   public String[] getProperties()
   {
      return new String[]{};
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new SDMLibJsonIdMap();
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
   
   public static JsonIdMap createIdMap(String sessionID)
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
