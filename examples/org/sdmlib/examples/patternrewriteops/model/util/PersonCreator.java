package org.sdmlib.examples.patternrewriteops.model.util;

import org.sdmlib.serialization.EntityFactory;
import de.uniks.networkparser.json.JsonIdMap;
import org.sdmlib.serialization.SDMLibJsonIdMap;
import org.sdmlib.examples.patternrewriteops.model.Person;
import org.sdmlib.examples.patternrewriteops.model.Station;
import org.sdmlib.examples.patternrewriteops.model.Train;

public class PersonCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      Person.PROPERTY_STATION,
      Person.PROPERTY_TRAIN,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new Person();
   }
   
   @Override
   public Object getValue(Object target, String attrName)
   {
      if (Person.PROPERTY_STATION.equalsIgnoreCase(attrName))
      {
         return ((Person) target).getStation();
      }

      if (Person.PROPERTY_TRAIN.equalsIgnoreCase(attrName))
      {
         return ((Person) target).getTrain();
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

      if (Person.PROPERTY_STATION.equalsIgnoreCase(attrName))
      {
         ((Person) target).setStation((Station) value);
         return true;
      }

      if (Person.PROPERTY_TRAIN.equalsIgnoreCase(attrName))
      {
         ((Person) target).setTrain((Train) value);
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
      ((Person) entity).removeYou();
   }
}

