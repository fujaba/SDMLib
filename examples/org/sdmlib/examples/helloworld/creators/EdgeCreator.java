package org.sdmlib.examples.helloworld.creators;

import org.sdmlib.examples.helloworld.Edge;
import org.sdmlib.examples.helloworld.GraphComponent;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;

public class EdgeCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      Edge.PROPERTY_GRAPH,
      Edge.PROPERTY_SRC,
      Edge.PROPERTY_TGT,
      Edge.PROPERTY_NAME,
      GraphComponent.PROPERTY_TEXT,
      GraphComponent.PROPERTY_PARENT,
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

   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((Edge) entity).removeYou();
   }
}




