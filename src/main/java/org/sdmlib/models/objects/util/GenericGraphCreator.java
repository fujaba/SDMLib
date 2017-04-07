package org.sdmlib.models.objects.util;

import org.sdmlib.models.objects.GenericGraph;
import org.sdmlib.models.objects.GenericLink;
import org.sdmlib.models.objects.GenericObject;
import org.sdmlib.serialization.EntityFactory;

import de.uniks.networkparser.IdMap;

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
      if (GenericGraph.PROPERTY_OBJECTS.equalsIgnoreCase(attrName))
      {
         return ((GenericGraph) target).getObjects();
      }

      if (GenericGraph.PROPERTY_LINKS.equalsIgnoreCase(attrName))
      {
         return ((GenericGraph) target).getLinks();
      }

      return null;
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (REMOVE.equals(type) && value != null)
      {
         attrName = attrName + type;
      }

      if (GenericGraph.PROPERTY_OBJECTS.equalsIgnoreCase(attrName))
      {
         ((GenericGraph) target).addToObjects((GenericObject) value);
         return true;
      }
      
      if ((GenericGraph.PROPERTY_OBJECTS + REMOVE).equalsIgnoreCase(attrName))
      {
         ((GenericGraph) target).removeFromObjects((GenericObject) value);
         return true;
      }

      if (GenericGraph.PROPERTY_LINKS.equalsIgnoreCase(attrName))
      {
         ((GenericGraph) target).addToLinks((GenericLink) value);
         return true;
      }
      
      if ((GenericGraph.PROPERTY_LINKS + REMOVE).equalsIgnoreCase(attrName))
      {
         ((GenericGraph) target).removeFromLinks((GenericLink) value);
         return true;
      }
      return false;
   }
   public static IdMap createIdMap(String sessionID)
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
