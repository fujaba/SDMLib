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

import de.uniks.networkparser.interfaces.SendableEntityCreator;
import org.sdmlib.models.pattern.ReachableState;
import de.uniks.networkparser.IdMap;
import org.sdmlib.models.pattern.ReachabilityGraph;
import org.sdmlib.models.pattern.RuleApplication;

public class ReachableStateCreator implements SendableEntityCreator
{
   private final String[] properties = new String[]
   {
      ReachableState.PROPERTY_NUMBER,
      ReachableState.PROPERTY_METRICVALUE,
      ReachableState.PROPERTY_GRAPHROOT,
      ReachableState.PROPERTY_PARENT,
      ReachableState.PROPERTY_RULEAPPLICATIONS,
      ReachableState.PROPERTY_RESULTOF,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new ReachableState();
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

      if (ReachableState.PROPERTY_NUMBER.equalsIgnoreCase(attribute))
      {
         return ((ReachableState) target).getNumber();
      }

      if (ReachableState.PROPERTY_METRICVALUE.equalsIgnoreCase(attribute))
      {
         return ((ReachableState) target).getMetricValue();
      }

      if (ReachableState.PROPERTY_GRAPHROOT.equalsIgnoreCase(attribute))
      {
         return ((ReachableState) target).getGraphRoot();
      }

      if (ReachableState.PROPERTY_PARENT.equalsIgnoreCase(attribute))
      {
         return ((ReachableState) target).getParent();
      }

      if (ReachableState.PROPERTY_RULEAPPLICATIONS.equalsIgnoreCase(attribute))
      {
         return ((ReachableState) target).getRuleapplications();
      }

      if (ReachableState.PROPERTY_RESULTOF.equalsIgnoreCase(attribute))
      {
         return ((ReachableState) target).getResultOf();
      }
      
      return null;
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (ReachableState.PROPERTY_GRAPHROOT.equalsIgnoreCase(attrName))
      {
         ((ReachableState) target).setGraphRoot((Object) value);
         return true;
      }

      if (ReachableState.PROPERTY_METRICVALUE.equalsIgnoreCase(attrName))
      {
         ((ReachableState) target).setMetricValue(Double.parseDouble(value.toString()));
         return true;
      }

      if (ReachableState.PROPERTY_NUMBER.equalsIgnoreCase(attrName))
      {
         ((ReachableState) target).setNumber(Long.parseLong(value.toString()));
         return true;
      }

      if (SendableEntityCreator.REMOVE.equals(type) && value != null)
      {
         attrName = attrName + type;
      }

      if (ReachableState.PROPERTY_PARENT.equalsIgnoreCase(attrName))
      {
         ((ReachableState) target).setParent((ReachabilityGraph) value);
         return true;
      }

      if (ReachableState.PROPERTY_RULEAPPLICATIONS.equalsIgnoreCase(attrName))
      {
         ((ReachableState) target).withRuleapplications((RuleApplication) value);
         return true;
      }
      
      if ((ReachableState.PROPERTY_RULEAPPLICATIONS + SendableEntityCreator.REMOVE).equalsIgnoreCase(attrName))
      {
         ((ReachableState) target).withoutRuleapplications((RuleApplication) value);
         return true;
      }

      if (ReachableState.PROPERTY_RESULTOF.equalsIgnoreCase(attrName))
      {
         ((ReachableState) target).withResultOf((RuleApplication) value);
         return true;
      }
      
      if ((ReachableState.PROPERTY_RESULTOF + SendableEntityCreator.REMOVE).equalsIgnoreCase(attrName))
      {
         ((ReachableState) target).withoutResultOf((RuleApplication) value);
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
      ((ReachableState) entity).removeYou();
   }
}
