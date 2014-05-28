package org.sdmlib.examples.patternrewriteops.model.util;

import org.sdmlib.serialization.EntityFactory;
import de.uniks.networkparser.json.JsonIdMap;
import org.sdmlib.examples.patternrewriteops.model.Train;
import org.sdmlib.examples.patternrewriteops.model.Station;
import org.sdmlib.examples.patternrewriteops.model.Person;

public class TrainCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      Train.PROPERTY_STATION,
      Train.PROPERTY_PASSENGERS,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new Train();
   }
   
   @Override
   public Object getValue(Object target, String attrName)
   {
      if (Train.PROPERTY_STATION.equalsIgnoreCase(attrName))
      {
         return ((Train) target).getStation();
      }

      if (Train.PROPERTY_PASSENGERS.equalsIgnoreCase(attrName))
      {
         return ((Train) target).getPassengers();
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

      if (Train.PROPERTY_STATION.equalsIgnoreCase(attrName))
      {
         ((Train) target).setStation((Station) value);
         return true;
      }

      if (Train.PROPERTY_PASSENGERS.equalsIgnoreCase(attrName))
      {
         ((Train) target).addToPassengers((Person) value);
         return true;
      }
      
      if ((Train.PROPERTY_PASSENGERS + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Train) target).removeFromPassengers((Person) value);
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
      ((Train) entity).removeYou();
   }
}

