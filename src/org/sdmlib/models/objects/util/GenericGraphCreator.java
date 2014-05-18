package org.sdmlib.models.objects.util;

import org.sdmlib.models.objects.GenericGraph;
import org.sdmlib.serialization.EntityFactory;

public class GenericGraphCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      GenericGraph.PROPERTY_OBJECTS,
      GenericGraph.PROPERTY_LINKS,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new GenericGraph();
   }
   
   @Override
   public Object getValue(Object target, String attrName)
   {
      return ((GenericGraph) target).get(attrName);
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((GenericGraph) target).set(attrName, value);
   }
   
 
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((GenericGraph) entity).removeYou();
   }
}


