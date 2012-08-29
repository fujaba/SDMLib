package org.sdmlib.examples.helloworld.creators;

import org.sdmlib.examples.helloworld.GraphComponent;
import org.sdmlib.examples.helloworld.Node;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;

public class NodeCreator implements EntityFactory
{
   private final String[] properties = new String[]
   {
      Node.PROPERTY_NAME,
      Node.PROPERTY_GRAPH,
      Node.PROPERTY_OUTEDGES,
      Node.PROPERTY_INEDGES,
      Node.PROPERTY_ORIG,
      Node.PROPERTY_COPY,
      GraphComponent.PROPERTY_TEXT,
      Node.PROPERTY_LINKSTO,
      Node.PROPERTY_LINKSFROM,
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
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((Node) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((Node) entity).removeYou();
   }
}





