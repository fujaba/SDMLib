package org.sdmlib.models.patterns.example.creators;

import org.sdmlib.models.patterns.example.creators.CreatorCreator;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.models.patterns.example.Node;

public class NodeCreator extends EntityFactory
{
   private final String[] properties = new String[]
   { Node.PROPERTY_NUM, Node.PROPERTY_GRAPH, Node.PROPERTY_NEXT,
         Node.PROPERTY_PREV, };

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

   public boolean setValue(Object target, String attrName, Object value,
         String type)
   {
      if (JsonIdMap.REMOVE.equals(type))
      {
         attrName = attrName + type;
      }
      return ((Node) target).set(attrName, value);
   }

   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   // ==========================================================================

   @Override
   public void removeObject(Object entity)
   {
      ((Node) entity).removeYou();
   }
}
