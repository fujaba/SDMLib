/*
   Copyright (c) 2017 zuendorf
   
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
   
package org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.util;

import de.uniks.networkparser.interfaces.AggregatedEntityCreator;
import org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.LBoat;
import de.uniks.networkparser.list.ObjectSet;
import de.uniks.networkparser.interfaces.SendableEntityCreator;
import de.uniks.networkparser.IdMap;
import org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.LBank;
import org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.LRiver;
import org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.LCargo;

public class LBoatCreator implements AggregatedEntityCreator
{
   public static final LBoatCreator it = new LBoatCreator();
   
   private final String[] properties = new String[]
   {
      LBoat.PROPERTY_BANK,
      LBoat.PROPERTY_RIVER,
      LBoat.PROPERTY_CARGO,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new LBoat();
   }
   
   @Override
   public void aggregate(ObjectSet graph, Object obj)
   {
      if (graph.contains(obj)) return;
      
      graph.add(obj);
      LBoat source = (LBoat) obj;
      LCargoCreator.it.aggregate(graph, source.getCargo());
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

      if (LBoat.PROPERTY_BANK.equalsIgnoreCase(attribute))
      {
         return ((LBoat) target).getBank();
      }

      if (LBoat.PROPERTY_RIVER.equalsIgnoreCase(attribute))
      {
         return ((LBoat) target).getRiver();
      }

      if (LBoat.PROPERTY_CARGO.equalsIgnoreCase(attribute))
      {
         return ((LBoat) target).getCargo();
      }
      
      return null;
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if(SendableEntityCreator.REMOVE_YOU.equals(type)) {
           ((LBoat)target).removeYou();
           return true;
      }
      if (SendableEntityCreator.REMOVE.equals(type) && value != null)
      {
         attrName = attrName + type;
      }

      if (LBoat.PROPERTY_BANK.equalsIgnoreCase(attrName))
      {
         ((LBoat) target).setBank((LBank) value);
         return true;
      }

      if (LBoat.PROPERTY_RIVER.equalsIgnoreCase(attrName))
      {
         ((LBoat) target).withRiver((LRiver) value);
         return true;
      }
      
      if ((LBoat.PROPERTY_RIVER + SendableEntityCreator.REMOVE).equalsIgnoreCase(attrName))
      {
         ((LBoat) target).withoutRiver((LRiver) value);
         return true;
      }

      if (LBoat.PROPERTY_CARGO.equalsIgnoreCase(attrName))
      {
         ((LBoat) target).setCargo((LCargo) value);
         return true;
      }
      
      return false;
   }
   public static IdMap createIdMap(String sessionID)
   {
      return org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.util.CreatorCreator.createIdMap(sessionID);
   }
   
   //==========================================================================
      public void removeObject(Object entity)
   {
      ((LBoat) entity).removeYou();
   }
}
