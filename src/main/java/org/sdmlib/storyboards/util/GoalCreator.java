/*
   Copyright (c) 2018 zuendorf
   
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
   
package org.sdmlib.storyboards.util;

import de.uniks.networkparser.interfaces.AggregatedEntityCreator;
import org.sdmlib.storyboards.Goal;
import de.uniks.networkparser.list.ObjectSet;
import de.uniks.networkparser.interfaces.SendableEntityCreator;
import de.uniks.networkparser.IdMap;

public class GoalCreator implements AggregatedEntityCreator
{
   public static final GoalCreator it = new GoalCreator();
   
   private final String[] properties = new String[]
   {
      Goal.PROPERTY_DESCRIPTION,
      Goal.PROPERTY_HOURSTODO,
      Goal.PROPERTY_PREGOALS,
      Goal.PROPERTY_HOURSDONE,
      Goal.PROPERTY_PARENTS,
   };
   
   private final String[] upProperties = new String[]
   {
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
      return new Goal();
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

      if (Goal.PROPERTY_DESCRIPTION.equalsIgnoreCase(attribute))
      {
         return ((Goal) target).getDescription();
      }

      if (Goal.PROPERTY_HOURSTODO.equalsIgnoreCase(attribute))
      {
         return ((Goal) target).getHoursTodo();
      }

      if (Goal.PROPERTY_PREGOALS.equalsIgnoreCase(attribute))
      {
         return ((Goal) target).getPreGoals();
      }

      if (Goal.PROPERTY_HOURSDONE.equalsIgnoreCase(attribute))
      {
         return ((Goal) target).getHoursDone();
      }

      if (Goal.PROPERTY_PARENTS.equalsIgnoreCase(attribute))
      {
         return ((Goal) target).getParents();
      }
      
      return null;
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (Goal.PROPERTY_HOURSDONE.equalsIgnoreCase(attrName))
      {
         ((Goal) target).setHoursDone(Double.parseDouble(value.toString()));
         return true;
      }

      if (Goal.PROPERTY_HOURSTODO.equalsIgnoreCase(attrName))
      {
         ((Goal) target).setHoursTodo(Double.parseDouble(value.toString()));
         return true;
      }

      if (Goal.PROPERTY_DESCRIPTION.equalsIgnoreCase(attrName))
      {
         ((Goal) target).setDescription((String) value);
         return true;
      }

      if(SendableEntityCreator.REMOVE_YOU.equals(type)) {
           ((Goal)target).removeYou();
           return true;
      }
      if (SendableEntityCreator.REMOVE.equals(type) && value != null)
      {
         attrName = attrName + type;
      }

      if (Goal.PROPERTY_PREGOALS.equalsIgnoreCase(attrName))
      {
         ((Goal) target).withPreGoals((Goal) value);
         return true;
      }
      
      if ((Goal.PROPERTY_PREGOALS + SendableEntityCreator.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Goal) target).withoutPreGoals((Goal) value);
         return true;
      }

      if (Goal.PROPERTY_PARENTS.equalsIgnoreCase(attrName))
      {
         ((Goal) target).withParents((Goal) value);
         return true;
      }
      
      if ((Goal.PROPERTY_PARENTS + SendableEntityCreator.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Goal) target).withoutParents((Goal) value);
         return true;
      }
      
      return false;
   }
   public static IdMap createIdMap(String sessionID)
   {
      return org.sdmlib.storyboards.util.CreatorCreator.createIdMap(sessionID);
   }
   
   //==========================================================================
      public void removeObject(Object entity)
   {
      ((Goal) entity).removeYou();
   }
}
