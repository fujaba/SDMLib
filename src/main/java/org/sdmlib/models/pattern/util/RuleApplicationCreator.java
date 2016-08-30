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
import org.sdmlib.models.pattern.RuleApplication;
import de.uniks.networkparser.IdMap;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.ReachableState;

public class RuleApplicationCreator implements SendableEntityCreator
{
   private final String[] properties = new String[]
   {
      RuleApplication.PROPERTY_DESCRIPTION,
      RuleApplication.PROPERTY_RULE,
      RuleApplication.PROPERTY_SRC,
      RuleApplication.PROPERTY_TGT,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new RuleApplication();
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

      if (RuleApplication.PROPERTY_DESCRIPTION.equalsIgnoreCase(attribute))
      {
         return ((RuleApplication) target).getDescription();
      }

      if (RuleApplication.PROPERTY_RULE.equalsIgnoreCase(attribute))
      {
         return ((RuleApplication) target).getRule();
      }

      if (RuleApplication.PROPERTY_SRC.equalsIgnoreCase(attribute))
      {
         return ((RuleApplication) target).getSrc();
      }

      if (RuleApplication.PROPERTY_TGT.equalsIgnoreCase(attribute))
      {
         return ((RuleApplication) target).getTgt();
      }
      
      return null;
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (RuleApplication.PROPERTY_DESCRIPTION.equalsIgnoreCase(attrName))
      {
         ((RuleApplication) target).setDescription((String) value);
         return true;
      }

      if (SendableEntityCreator.REMOVE.equals(type) && value != null)
      {
         attrName = attrName + type;
      }

      if (RuleApplication.PROPERTY_RULE.equalsIgnoreCase(attrName))
      {
         ((RuleApplication) target).setRule((Pattern) value);
         return true;
      }

      if (RuleApplication.PROPERTY_SRC.equalsIgnoreCase(attrName))
      {
         ((RuleApplication) target).setSrc((ReachableState) value);
         return true;
      }

      if (RuleApplication.PROPERTY_TGT.equalsIgnoreCase(attrName))
      {
         ((RuleApplication) target).setTgt((ReachableState) value);
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
      ((RuleApplication) entity).removeYou();
   }
}
