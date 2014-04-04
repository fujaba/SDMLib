package org.sdmlib.examples.m2m.creators;

import org.sdmlib.examples.m2m.creators.CreatorCreator;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.examples.m2m.Graph;

public class GraphCreator extends EntityFactory
{
   private final String[] properties = new String[]
   { Graph.PROPERTY_PERSONS, Graph.PROPERTY_RELATIONS, Graph.PROPERTY_GCS, };

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

   public boolean setValue(Object target, String attrName, Object value,
         String type)
   {
      if (JsonIdMap.REMOVE.equals(type) && value != null)
      {
         attrName = attrName + type;
      }
      return ((Graph) target).set(attrName, value);
   }

   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   // ==========================================================================

   @Override
   public void removeObject(Object entity)
   {
      ((Graph) entity).removeYou();
   }
}
