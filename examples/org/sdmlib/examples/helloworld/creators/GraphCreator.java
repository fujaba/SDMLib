package org.sdmlib.examples.helloworld.creators;

import org.sdmlib.examples.helloworld.Graph;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;

public class GraphCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      Graph.PROPERTY_NODES,
      Graph.PROPERTY_EDGES,
      Graph.PROPERTY_GCS,
   };
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new Graph();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((Graph) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((Graph) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((Graph) entity).removeYou();
   }
}



