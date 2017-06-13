/*
   Copyright (c) 2017 Stefan
   
   Permission is hereby granted, free of charge, to any person obtaining a copy of this software 
   and associated documentation files (the "Software"), to deal in the Software without restriction, 
   including without limitation the rights to use, copy, modify, merge, publish, distribute, 
   sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is 
   furnished to do so, subject to the following conditions: 
   
   The above copyright notice and this permission notice shall be included in all copies or 
   substantial portions of the Software. 
   
   The Software shall be used for Good, not Evil. 
   
   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING 
   BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND 
   NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, 
   DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, 
   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */
   
package org.sdmlib.test.examples.patternrewriteops.model.util;

import de.uniks.networkparser.interfaces.SendableEntityCreator;
import org.sdmlib.test.examples.patternrewriteops.model.Station;
import de.uniks.networkparser.IdMap;
import org.sdmlib.test.examples.patternrewriteops.model.Person;
import org.sdmlib.test.examples.patternrewriteops.model.SignalFlag;
import org.sdmlib.test.examples.patternrewriteops.model.Train;

public class StationCreator implements SendableEntityCreator
{
   private final String[] properties = new String[]
   {
      Station.PROPERTY_PREV,
      Station.PROPERTY_NEXT,
      Station.PROPERTY_PEOPLE,
      Station.PROPERTY_FLAG,
      Station.PROPERTY_TRAINS,
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
      int pos = attrName.indexOf('.');
      String attribute = attrName;
      
      if (pos > 0)
      {
         attribute = attrName.substring(0, pos);
      }

      if (Station.PROPERTY_PREV.equalsIgnoreCase(attribute))
      {
         return ((Station) target).getPrev();
      }

      if (Station.PROPERTY_NEXT.equalsIgnoreCase(attribute))
      {
         return ((Station) target).getNext();
      }

      if (Station.PROPERTY_PEOPLE.equalsIgnoreCase(attribute))
      {
         return ((Station) target).getPeople();
      }

      if (Station.PROPERTY_FLAG.equalsIgnoreCase(attribute))
      {
         return ((Station) target).getFlag();
      }

      if (Station.PROPERTY_TRAINS.equalsIgnoreCase(attribute))
      {
         return ((Station) target).getTrains();
      }
      
      return null;
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if(SendableEntityCreator.REMOVE_YOU.equals(type)) {
           ((Station)target).removeYou();
           return true;
      }
      if (SendableEntityCreator.REMOVE.equals(type) && value != null)
      {
         attrName = attrName + type;
      }

      if (Station.PROPERTY_PREV.equalsIgnoreCase(attrName))
      {
         ((Station) target).setPrev((Station) value);
         return true;
      }

      if (Station.PROPERTY_NEXT.equalsIgnoreCase(attrName))
      {
         ((Station) target).setNext((Station) value);
         return true;
      }

      if (Station.PROPERTY_PEOPLE.equalsIgnoreCase(attrName))
      {
         ((Station) target).withPeople((Person) value);
         return true;
      }
      
      if ((Station.PROPERTY_PEOPLE + SendableEntityCreator.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Station) target).withoutPeople((Person) value);
         return true;
      }

      if (Station.PROPERTY_FLAG.equalsIgnoreCase(attrName))
      {
         ((Station) target).setFlag((SignalFlag) value);
         return true;
      }

      if (Station.PROPERTY_TRAINS.equalsIgnoreCase(attrName))
      {
         ((Station) target).withTrains((Train) value);
         return true;
      }
      
      if ((Station.PROPERTY_TRAINS + SendableEntityCreator.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Station) target).withoutTrains((Train) value);
         return true;
      }
      
      return false;
   }
   public static IdMap createIdMap(String sessionID)
   {
      return org.sdmlib.test.examples.patternrewriteops.model.util.CreatorCreator.createIdMap(sessionID);
   }
   
   //==========================================================================
      public void removeObject(Object entity)
   {
      ((Station) entity).removeYou();
   }
}
