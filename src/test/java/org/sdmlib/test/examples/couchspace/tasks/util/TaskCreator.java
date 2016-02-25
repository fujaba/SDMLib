/*
   Copyright (c) 2016 zuendorf
   
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
   
package org.sdmlib.test.examples.couchspace.tasks.util;

import de.uniks.networkparser.interfaces.SendableEntityCreator;
import de.uniks.networkparser.json.JsonIdMap;
import org.sdmlib.test.examples.couchspace.tasks.Task;
import org.sdmlib.test.examples.couchspace.tasks.TaskFlow;
import org.sdmlib.test.examples.couchspace.tasks.UserGroup;
import org.sdmlib.test.examples.couchspace.tasks.User;

public class TaskCreator implements SendableEntityCreator
{
   private final String[] properties = new String[]
   {
      Task.PROPERTY_TITLE,
      Task.PROPERTY_TRANSITIONCONDITION,
      Task.PROPERTY_COPYSDMLIBID,
      Task.PROPERTY_TASKFLOW,
      Task.PROPERTY_TASKFLOWFIRST,
      Task.PROPERTY_TRANSITIONSOURCE,
      Task.PROPERTY_RESPONSIBLES,
      Task.PROPERTY_HANDLEDBY,
      Task.PROPERTY_TRANSITIONTARGETS,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new Task();
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

      if (Task.PROPERTY_TITLE.equalsIgnoreCase(attribute))
      {
         return ((Task) target).getTitle();
      }

      if (Task.PROPERTY_TRANSITIONCONDITION.equalsIgnoreCase(attribute))
      {
         return ((Task) target).getTransitionCondition();
      }

      if (Task.PROPERTY_COPYSDMLIBID.equalsIgnoreCase(attribute))
      {
         return ((Task) target).getCopySdmLibId();
      }

      if (Task.PROPERTY_TASKFLOW.equalsIgnoreCase(attribute))
      {
         return ((Task) target).getTaskFlow();
      }

      if (Task.PROPERTY_TASKFLOWFIRST.equalsIgnoreCase(attribute))
      {
         return ((Task) target).getTaskFlowFirst();
      }

      if (Task.PROPERTY_TRANSITIONSOURCE.equalsIgnoreCase(attribute))
      {
         return ((Task) target).getTransitionSource();
      }

      if (Task.PROPERTY_RESPONSIBLES.equalsIgnoreCase(attribute))
      {
         return ((Task) target).getResponsibles();
      }

      if (Task.PROPERTY_HANDLEDBY.equalsIgnoreCase(attribute))
      {
         return ((Task) target).getHandledBy();
      }

      if (Task.PROPERTY_TRANSITIONTARGETS.equalsIgnoreCase(attribute))
      {
         return ((Task) target).getTransitionTargets();
      }
      
      return null;
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (Task.PROPERTY_COPYSDMLIBID.equalsIgnoreCase(attrName))
      {
         ((Task) target).withCopySdmLibId((String) value);
         return true;
      }

      if (Task.PROPERTY_TRANSITIONCONDITION.equalsIgnoreCase(attrName))
      {
         ((Task) target).withTransitionCondition((String) value);
         return true;
      }

      if (Task.PROPERTY_TITLE.equalsIgnoreCase(attrName))
      {
         ((Task) target).withTitle((String) value);
         return true;
      }

      if (JsonIdMap.REMOVE.equals(type) && value != null)
      {
         attrName = attrName + type;
      }

      if (Task.PROPERTY_TASKFLOW.equalsIgnoreCase(attrName))
      {
         ((Task) target).setTaskFlow((TaskFlow) value);
         return true;
      }

      if (Task.PROPERTY_TASKFLOWFIRST.equalsIgnoreCase(attrName))
      {
         ((Task) target).setTaskFlowFirst((TaskFlow) value);
         return true;
      }

      if (Task.PROPERTY_TRANSITIONSOURCE.equalsIgnoreCase(attrName))
      {
         ((Task) target).setTransitionSource((Task) value);
         return true;
      }

      if (Task.PROPERTY_RESPONSIBLES.equalsIgnoreCase(attrName))
      {
         ((Task) target).withResponsibles((UserGroup) value);
         return true;
      }
      
      if ((Task.PROPERTY_RESPONSIBLES + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Task) target).withoutResponsibles((UserGroup) value);
         return true;
      }

      if (Task.PROPERTY_HANDLEDBY.equalsIgnoreCase(attrName))
      {
         ((Task) target).setHandledBy((User) value);
         return true;
      }

      if (Task.PROPERTY_TRANSITIONTARGETS.equalsIgnoreCase(attrName))
      {
         ((Task) target).withTransitionTargets((Task) value);
         return true;
      }
      
      if ((Task.PROPERTY_TRANSITIONTARGETS + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Task) target).withoutTransitionTargets((Task) value);
         return true;
      }
      
      return false;
   }
   public static JsonIdMap createIdMap(String sessionID)
   {
      return org.sdmlib.test.examples.couchspace.tasks.util.CreatorCreator.createIdMap(sessionID);
   }
   
   //==========================================================================
      public void removeObject(Object entity)
   {
      ((Task) entity).removeYou();
   }
}
