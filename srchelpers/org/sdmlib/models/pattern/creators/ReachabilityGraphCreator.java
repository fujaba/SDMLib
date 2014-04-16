package org.sdmlib.models.pattern.creators;

import org.sdmlib.models.pattern.ReachabilityGraph;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;

public class ReachabilityGraphCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      ReachabilityGraph.PROPERTY_STATES,
      ReachabilityGraph.PROPERTY_TODO,
      // ReachabilityGraph.PROPERTY_RULES,
   };
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new ReachabilityGraph();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((ReachabilityGraph) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (JsonIdMap.REMOVE.equals(type))
      {
         attrName = attrName + type;
      }
      return ((ReachabilityGraph) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
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



