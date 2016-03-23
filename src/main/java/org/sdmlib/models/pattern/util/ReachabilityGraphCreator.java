package org.sdmlib.models.pattern.util;

import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.ReachabilityGraph;
import org.sdmlib.models.pattern.ReachableState;
import org.sdmlib.serialization.EntityFactory;

import de.uniks.networkparser.IdMap;

public class ReachabilityGraphCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      ReachabilityGraph.PROPERTY_STATES,
      ReachabilityGraph.PROPERTY_TODO,
      // ReachabilityGraph.PROPERTY_RULES,
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
      if (ReachabilityGraph.PROPERTY_STATES.equalsIgnoreCase(attrName))
      {
         return ((ReachabilityGraph)target).getStates();
      }

      if (ReachabilityGraph.PROPERTY_TODO.equalsIgnoreCase(attrName))
      {
         return ((ReachabilityGraph)target).getTodo();
      }

      if (ReachabilityGraph.PROPERTY_RULES.equalsIgnoreCase(attrName))
      {
         return ((ReachabilityGraph)target).getRules();
      }
      return super.getValue(target, attrName);
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (ReachabilityGraph.PROPERTY_STATES.equalsIgnoreCase(attrName))
      {
         ((ReachabilityGraph)target).addToStates((ReachableState) value);
         return true;
      }

      if ((ReachabilityGraph.PROPERTY_STATES + IdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((ReachabilityGraph)target).removeFromStates((ReachableState) value);
         return true;
      }

      if (ReachabilityGraph.PROPERTY_TODO.equalsIgnoreCase(attrName))
      {
         ((ReachabilityGraph)target).addToTodo((ReachableState) value);
         return true;
      }

      if ((ReachabilityGraph.PROPERTY_TODO + IdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((ReachabilityGraph)target).removeFromTodo((ReachableState) value);
         return true;
      }

      if (ReachabilityGraph.PROPERTY_RULES.equalsIgnoreCase(attrName))
      {
         ((ReachabilityGraph)target).addToRules((Pattern<?>) value);
         return true;
      }

      if ((ReachabilityGraph.PROPERTY_RULES + IdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((ReachabilityGraph)target).removeFromRules((Pattern<?>) value);
         return true;
      }
      return super.setValue(target, attrName, value, type);
   }
   
   public static IdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((ReachabilityGraph) entity).removeYou();
   }
}



