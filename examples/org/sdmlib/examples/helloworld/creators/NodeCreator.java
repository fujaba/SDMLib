package org.sdmlib.examples.helloworld.creators;

import org.sdmlib.examples.helloworld.creators.CreatorCreator;
import org.sdmlib.serialization.interfaces.SendableEntityCreator;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.examples.helloworld.Node;

public class NodeCreator implements SendableEntityCreator
{
   private final String[] properties = new String[]
   {
      Node.PROPERTY_NAME,
      Node.PROPERTY_GRAPH,
      Node.PROPERTY_OUTEDGES,
      Node.PROPERTY_INEDGES,
   };
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new Node();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((Node) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value)
   {
      return ((Node) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }
}

