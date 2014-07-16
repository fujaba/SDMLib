package org.sdmlib.examples.patternrewriteops.model.util;

import org.sdmlib.examples.patternrewriteops.model.SignalFlag;
import org.sdmlib.examples.patternrewriteops.model.Station;
import org.sdmlib.serialization.EntityFactory;

import de.uniks.networkparser.json.JsonIdMap;

public class SignalFlagCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      SignalFlag.PROPERTY_STATION,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new SignalFlag();
   }
   
   @Override
   public Object getValue(Object target, String attrName)
   {
      if (SignalFlag.PROPERTY_STATION.equalsIgnoreCase(attrName))
      {
         return ((SignalFlag) target).getStation();
      }

      return null;
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (JsonIdMap.REMOVE.equals(type) && value != null)
      {
         attrName = attrName + type;
      }

      if (SignalFlag.PROPERTY_STATION.equalsIgnoreCase(attrName))
      {
         ((SignalFlag) target).addToStation((Station) value);
         return true;
      }
      
      if ((SignalFlag.PROPERTY_STATION + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((SignalFlag) target).removeFromStation((Station) value);
         return true;
      }
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
      ((SignalFlag) entity).removeYou();
   }
}

