package org.sdmlib.examples.helloworld.creators;

import org.sdmlib.examples.helloworld.creators.CreatorCreator;
import org.sdmlib.serialization.interfaces.SendableEntityCreator;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.examples.helloworld.Edge;

public class EdgeCreator implements SendableEntityCreator
{
   private final String[] properties = new String[]
   {
      Edge.PROPERTY_GRAPH,
      Edge.PROPERTY_SRC,
      Edge.PROPERTY_TGT,
      Edge.PROPERTY_NAME,
   };
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new Edge();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((Edge) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((Edge) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }
}


