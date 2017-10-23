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
import org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.Cargo;
import de.uniks.networkparser.list.ObjectSet;
import de.uniks.networkparser.interfaces.SendableEntityCreator;
import de.uniks.networkparser.IdMap;
import org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.LBoat;
import org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.LBank;

public class CargoCreator implements AggregatedEntityCreator
{
   public static CargoCreator it = new CargoCreator();
   
   private final String[] properties = new String[]
   {
      Cargo.PROPERTY_NAME,
      Cargo.PROPERTY_BOAT,
      Cargo.PROPERTY_BANK,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new Cargo();
   }
   
   @Override
   public void aggregate(ObjectSet graph, Object obj)
   {
      if (graph.contains(obj)) return;
      
      graph.add(obj);
      Cargo source = (Cargo) obj;
      return;
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

      if (Cargo.PROPERTY_NAME.equalsIgnoreCase(attribute))
      {
         return ((Cargo) target).getName();
      }

      if (Cargo.PROPERTY_BOAT.equalsIgnoreCase(attribute))
      {
         return ((Cargo) target).getBoat();
      }

      if (Cargo.PROPERTY_BANK.equalsIgnoreCase(attribute))
      {
         return ((Cargo) target).getBank();
      }
      
      return null;
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (Cargo.PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         ((Cargo) target).setName((String) value);
         return true;
      }

      if(SendableEntityCreator.REMOVE_YOU.equals(type)) {
           ((Cargo)target).removeYou();
           return true;
      }
      if (SendableEntityCreator.REMOVE.equals(type) && value != null)
      {
         attrName = attrName + type;
      }

      if (Cargo.PROPERTY_BOAT.equalsIgnoreCase(attrName))
      {
         ((Cargo) target).withBoat((LBoat) value);
         return true;
      }
      
      if ((Cargo.PROPERTY_BOAT + SendableEntityCreator.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Cargo) target).withoutBoat((LBoat) value);
         return true;
      }

      if (Cargo.PROPERTY_BANK.equalsIgnoreCase(attrName))
      {
         ((Cargo) target).withBank((LBank) value);
         return true;
      }
      
      if ((Cargo.PROPERTY_BANK + SendableEntityCreator.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Cargo) target).withoutBank((LBank) value);
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
      ((Cargo) entity).removeYou();
   }
}
