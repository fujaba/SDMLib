package org.sdmlib.examples.helloworld.creators;

import org.sdmlib.examples.helloworld.creators.CreatorCreator;
import org.sdmlib.serialization.interfaces.SendableEntityCreator;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.examples.helloworld.Graph;

public class GraphCreator implements SendableEntityCreator
{
   private final String[] properties = new String[]
   {
      Graph.PROPERTY_NODES,
      Graph.PROPERTY_EDGES,
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
   
   public boolean setValue(Object target, String attrName, Object value)
   {
      return ((Graph) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }
}

