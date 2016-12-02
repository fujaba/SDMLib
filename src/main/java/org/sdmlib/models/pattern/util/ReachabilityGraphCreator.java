/*
   Copyright (c) 2016 christoph
   
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
   
package org.sdmlib.models.pattern.util;

import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.ReachabilityGraph;
import org.sdmlib.models.pattern.ReachableState;

import de.uniks.networkparser.IdMap;
import de.uniks.networkparser.interfaces.SendableEntityCreator;

public class ReachabilityGraphCreator implements SendableEntityCreator
{
   private final String[] properties = new String[]
   {
      ReachabilityGraph.PROPERTY_RULES,
      ReachabilityGraph.PROPERTY_FINALSTATES,
      ReachabilityGraph.PROPERTY_STATES,
      ReachabilityGraph.PROPERTY_TODO,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new ReachabilityGraph();
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

      if (ReachabilityGraph.PROPERTY_RULES.equalsIgnoreCase(attribute))
      {
         return ((ReachabilityGraph) target).getRules();
      }

      if (ReachabilityGraph.PROPERTY_FINALSTATES.equalsIgnoreCase(attribute))
      {
         return ((ReachabilityGraph) target).getFinalStates();
      }

      if (ReachabilityGraph.PROPERTY_STATES.equalsIgnoreCase(attribute))
      {
         return ((ReachabilityGraph) target).getStates();
      }

      if (ReachabilityGraph.PROPERTY_TODO.equalsIgnoreCase(attribute))
      {
         return ((ReachabilityGraph) target).getTodo();
      }
      
      return null;
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (SendableEntityCreator.REMOVE.equals(type) && value != null)
      {
         attrName = attrName + type;
      }

      if (ReachabilityGraph.PROPERTY_RULES.equalsIgnoreCase(attrName))
      {
         ((ReachabilityGraph) target).withRules((Pattern) value);
         return true;
      }
      
      if ((ReachabilityGraph.PROPERTY_RULES + SendableEntityCreator.REMOVE).equalsIgnoreCase(attrName))
      {
         ((ReachabilityGraph) target).withoutRules((Pattern) value);
         return true;
      }

      if (ReachabilityGraph.PROPERTY_FINALSTATES.equalsIgnoreCase(attrName))
      {
         ((ReachabilityGraph) target).withFinalStates((ReachableState) value);
         return true;
      }
      
      if ((ReachabilityGraph.PROPERTY_FINALSTATES + SendableEntityCreator.REMOVE).equalsIgnoreCase(attrName))
      {
         ((ReachabilityGraph) target).withoutFinalStates((ReachableState) value);
         return true;
      }

      if (ReachabilityGraph.PROPERTY_STATES.equalsIgnoreCase(attrName))
      {
         ((ReachabilityGraph) target).withStates((ReachableState) value);
         return true;
      }
      
      if ((ReachabilityGraph.PROPERTY_STATES + SendableEntityCreator.REMOVE).equalsIgnoreCase(attrName))
      {
         ((ReachabilityGraph) target).withoutStates((ReachableState) value);
         return true;
      }

      if (ReachabilityGraph.PROPERTY_TODO.equalsIgnoreCase(attrName))
      {
         ((ReachabilityGraph) target).withTodo((ReachableState) value);
         return true;
      }
      
      if ((ReachabilityGraph.PROPERTY_TODO + SendableEntityCreator.REMOVE).equalsIgnoreCase(attrName))
      {
         ((ReachabilityGraph) target).withoutTodo((ReachableState) value);
         return true;
      }
      
      return false;
   }
   public static IdMap createIdMap(String sessionID)
   {
      return org.sdmlib.models.pattern.util.CreatorCreator.createIdMap(sessionID);
   }
   
   //==========================================================================
      public void removeObject(Object entity)
   {
      ((ReachabilityGraph) entity).removeYou();
   }
}
