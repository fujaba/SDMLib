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
import org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.LBank;
import de.uniks.networkparser.list.ObjectSet;
import de.uniks.networkparser.interfaces.SendableEntityCreator;
import de.uniks.networkparser.IdMap;
import org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.LBoat;
import org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.Cargo;
import org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.LRiver;

public class LBankCreator implements AggregatedEntityCreator
{
   public static LBankCreator it = new LBankCreator();
   
   private final String[] properties = new String[]
   {
      LBank.PROPERTY_AGE,
      LBank.PROPERTY_NAME,
      LBank.PROPERTY_BOAT,
      LBank.PROPERTY_CARGOS,
      LBank.PROPERTY_RIVER,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new LBank();
   }
   
   @Override
   public void aggregate(ObjectSet graph, Object obj)
   {
      if (graph.contains(obj)) return;
      
      graph.add(obj);
      LBank source = (LBank) obj;
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

      if (LBank.PROPERTY_AGE.equalsIgnoreCase(attribute))
      {
         return ((LBank) target).getAge();
      }

      if (LBank.PROPERTY_NAME.equalsIgnoreCase(attribute))
      {
         return ((LBank) target).getName();
      }

      if (LBank.PROPERTY_BOAT.equalsIgnoreCase(attribute))
      {
         return ((LBank) target).getBoat();
      }

      if (LBank.PROPERTY_CARGOS.equalsIgnoreCase(attribute))
      {
         return ((LBank) target).getCargos();
      }

      if (LBank.PROPERTY_RIVER.equalsIgnoreCase(attribute))
      {
         return ((LBank) target).getRiver();
      }
      
      return null;
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (LBank.PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         ((LBank) target).setName((String) value);
         return true;
      }

      if (LBank.PROPERTY_AGE.equalsIgnoreCase(attrName))
      {
         ((LBank) target).setAge(Integer.parseInt(value.toString()));
         return true;
      }

      if(SendableEntityCreator.REMOVE_YOU.equals(type)) {
           ((LBank)target).removeYou();
           return true;
      }
      if (SendableEntityCreator.REMOVE.equals(type) && value != null)
      {
         attrName = attrName + type;
      }

      if (LBank.PROPERTY_BOAT.equalsIgnoreCase(attrName))
      {
         ((LBank) target).withBoat((LBoat) value);
         return true;
      }
      
      if ((LBank.PROPERTY_BOAT + SendableEntityCreator.REMOVE).equalsIgnoreCase(attrName))
      {
         ((LBank) target).withoutBoat((LBoat) value);
         return true;
      }

      if (LBank.PROPERTY_CARGOS.equalsIgnoreCase(attrName))
      {
         ((LBank) target).withCargos((Cargo) value);
         return true;
      }
      
      if ((LBank.PROPERTY_CARGOS + SendableEntityCreator.REMOVE).equalsIgnoreCase(attrName))
      {
         ((LBank) target).withoutCargos((Cargo) value);
         return true;
      }

      if (LBank.PROPERTY_RIVER.equalsIgnoreCase(attrName))
      {
         ((LBank) target).withRiver((LRiver) value);
         return true;
      }
      
      if ((LBank.PROPERTY_RIVER + SendableEntityCreator.REMOVE).equalsIgnoreCase(attrName))
      {
         ((LBank) target).withoutRiver((LRiver) value);
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
      ((LBank) entity).removeYou();
   }
}
