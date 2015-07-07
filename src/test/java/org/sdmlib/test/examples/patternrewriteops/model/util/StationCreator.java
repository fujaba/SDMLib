package org.sdmlib.test.examples.patternrewriteops.model.util;

import org.sdmlib.serialization.EntityFactory;
import org.sdmlib.test.examples.patternrewriteops.model.Person;
import org.sdmlib.test.examples.patternrewriteops.model.SignalFlag;
import org.sdmlib.test.examples.patternrewriteops.model.Station;
import org.sdmlib.test.examples.patternrewriteops.model.Train;

import de.uniks.networkparser.json.JsonIdMap;

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
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new Station();
   }
   
   @Override
   public Object getValue(Object target, String attrName)
   {
      if (Station.PROPERTY_TRAINS.equalsIgnoreCase(attrName))
      {
         return ((Station) target).getTrains();
      }

      if (Station.PROPERTY_NEXT.equalsIgnoreCase(attrName))
      {
         return ((Station) target).getNext();
      }

      if (Station.PROPERTY_PREV.equalsIgnoreCase(attrName))
      {
         return ((Station) target).getPrev();
      }

      if (Station.PROPERTY_PEOPLE.equalsIgnoreCase(attrName))
      {
         return ((Station) target).getPeople();
      }

      if (Station.PROPERTY_FLAG.equalsIgnoreCase(attrName))
      {
         return ((Station) target).getFlag();
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

      if (Station.PROPERTY_TRAINS.equalsIgnoreCase(attrName))
      {
         ((Station) target).addToTrains((Train) value);
         return true;
      }
      
      if ((Station.PROPERTY_TRAINS + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Station) target).removeFromTrains((Train) value);
         return true;
      }

      if (Station.PROPERTY_NEXT.equalsIgnoreCase(attrName))
      {
         ((Station) target).setNext((Station) value);
         return true;
      }

      if (Station.PROPERTY_PREV.equalsIgnoreCase(attrName))
      {
         ((Station) target).setPrev((Station) value);
         return true;
      }

      if (Station.PROPERTY_PEOPLE.equalsIgnoreCase(attrName))
      {
         ((Station) target).addToPeople((Person) value);
         return true;
      }
      
      if ((Station.PROPERTY_PEOPLE + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Station) target).removeFromPeople((Person) value);
         return true;
      }

      if (Station.PROPERTY_FLAG.equalsIgnoreCase(attrName))
      {
         ((Station) target).setFlag((SignalFlag) value);
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
      ((Station) entity).removeYou();
   }
}

