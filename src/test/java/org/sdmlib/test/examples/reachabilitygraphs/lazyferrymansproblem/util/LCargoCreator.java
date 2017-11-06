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
import org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.LCargo;
import de.uniks.networkparser.list.ObjectSet;
import de.uniks.networkparser.interfaces.SendableEntityCreator;
import de.uniks.networkparser.IdMap;
import org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.LBoat;
import org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.LBank;

public class LCargoCreator implements AggregatedEntityCreator
{
   public static final LCargoCreator it = new LCargoCreator();
   
   private final String[] properties = new String[]
   {
      LCargo.PROPERTY_NAME,
      LCargo.PROPERTY_BOAT,
      LCargo.PROPERTY_BANK,
   };
   
   private final String[] upProperties = new String[]
   {
      LCargo.PROPERTY_BOAT,
      LCargo.PROPERTY_BANK,
   };
   
   private final String[] downProperties = new String[]
   {
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public String[] getUpProperties()
   {
      return upProperties;
   }
   
   @Override
   public String[] getDownProperties()
   {
      return downProperties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new LCargo();
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

      if (LCargo.PROPERTY_NAME.equalsIgnoreCase(attribute))
      {
         return ((LCargo) target).getName();
      }

      if (LCargo.PROPERTY_BOAT.equalsIgnoreCase(attribute))
      {
         return ((LCargo) target).getBoat();
      }

      if (LCargo.PROPERTY_BANK.equalsIgnoreCase(attribute))
      {
         return ((LCargo) target).getBank();
      }
      
      return null;
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (LCargo.PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         ((LCargo) target).setName((String) value);
         return true;
      }

      if(SendableEntityCreator.REMOVE_YOU.equals(type)) {
           ((LCargo)target).removeYou();
           return true;
      }
      if (SendableEntityCreator.REMOVE.equals(type) && value != null)
      {
         attrName = attrName + type;
      }

      if (LCargo.PROPERTY_BOAT.equalsIgnoreCase(attrName))
      {
         ((LCargo) target).withBoat((LBoat) value);
         return true;
      }
      
      if ((LCargo.PROPERTY_BOAT + SendableEntityCreator.REMOVE).equalsIgnoreCase(attrName))
      {
         ((LCargo) target).withoutBoat((LBoat) value);
         return true;
      }

      if (LCargo.PROPERTY_BANK.equalsIgnoreCase(attrName))
      {
         ((LCargo) target).withBank((LBank) value);
         return true;
      }
      
      if ((LCargo.PROPERTY_BANK + SendableEntityCreator.REMOVE).equalsIgnoreCase(attrName))
      {
         ((LCargo) target).withoutBank((LBank) value);
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
      ((LCargo) entity).removeYou();
   }
}
