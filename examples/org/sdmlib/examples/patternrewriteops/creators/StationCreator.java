package org.sdmlib.examples.patternrewriteops.creators;

import org.sdmlib.examples.patternrewriteops.Station;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;

public class StationCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      Station.PROPERTY_TRAINS,
      Station.PROPERTY_NEXT,
      Station.PROPERTY_PREV,
      Station.PROPERTY_PEOPLE,
      Station.PROPERTY_FLAG,
   };
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new Station();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((Station) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((Station) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((Station) entity).removeYou();
   }
}


