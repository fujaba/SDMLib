package org.sdmlib.models.objects.creators;

import org.sdmlib.models.objects.GenericGraph;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;

public class GenericGraphCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      GenericGraph.PROPERTY_OBJECTS,
      GenericGraph.PROPERTY_LINKS,
   };
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new GenericGraph();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((GenericGraph) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((GenericGraph) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((GenericGraph) entity).removeYou();
   }
}


