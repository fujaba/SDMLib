package org.sdmlib.models.pattern.util;

import org.sdmlib.models.pattern.ReachabilityGraph;
import org.sdmlib.models.pattern.ReachableState;
import org.sdmlib.models.pattern.RuleApplication;
import org.sdmlib.serialization.EntityFactory;

import de.uniks.networkparser.IdMap;

public class ReachableStateCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      ReachableState.PROPERTY_PARENT,
      ReachableState.PROPERTY_MASTER,
      ReachableState.PROPERTY_GRAPHROOT,
      ReachableState.PROPERTY_NUMBER,
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
      if (ReachableState.PROPERTY_PARENT.equalsIgnoreCase(attrName))
      {
         return ((ReachableState)target).getParent();
      }

      if (ReachableState.PROPERTY_MASTER.equalsIgnoreCase(attrName))
      {
         return ((ReachableState)target).getMaster();
      }

      if (ReachableState.PROPERTY_GRAPHROOT.equalsIgnoreCase(attrName))
      {
         return ((ReachableState)target).getGraphRoot();
      }

      if (ReachableState.PROPERTY_NUMBER.equalsIgnoreCase(attrName))
      {
         return ((ReachableState)target).getNumber();
      }

      if (ReachableState.PROPERTY_RULEAPPLICATIONS.equalsIgnoreCase(attrName))
      {
         return ((ReachableState)target).getRuleapplications();
      }

      if (ReachableState.PROPERTY_RESULTOF.equalsIgnoreCase(attrName))
      {
         return ((ReachableState)target).getResultOf();
      }
      return super.getValue(target, attrName);
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (ReachableState.PROPERTY_PARENT.equalsIgnoreCase(attrName))
      {
         ((ReachableState)target).setParent((ReachabilityGraph) value);
         return true;
      }

      if (ReachableState.PROPERTY_MASTER.equalsIgnoreCase(attrName))
      {
         ((ReachableState)target).setMaster((ReachabilityGraph) value);
         return true;
      }

      if (ReachableState.PROPERTY_GRAPHROOT.equalsIgnoreCase(attrName))
      {
         ((ReachableState)target).setGraphRoot((Object) value);
         return true;
      }

      if (ReachableState.PROPERTY_NUMBER.equalsIgnoreCase(attrName))
      {
         ((ReachableState)target).setNumber(Integer.parseInt(value.toString()));
         return true;
      }

      if (ReachableState.PROPERTY_RULEAPPLICATIONS.equalsIgnoreCase(attrName))
      {
         ((ReachableState)target).addToRuleapplications((RuleApplication) value);
         return true;
      }
      
      if ((ReachableState.PROPERTY_RULEAPPLICATIONS + IdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((ReachableState)target).removeFromRuleapplications((RuleApplication) value);
         return true;
      }

      if (ReachableState.PROPERTY_RESULTOF.equalsIgnoreCase(attrName))
      {
         ((ReachableState)target).addToResultOf((RuleApplication) value);
         return true;
      }
      
      if ((ReachableState.PROPERTY_RESULTOF + IdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((ReachableState)target).removeFromResultOf((RuleApplication) value);
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
      ((ReachableState) entity).removeYou();
   }
}





