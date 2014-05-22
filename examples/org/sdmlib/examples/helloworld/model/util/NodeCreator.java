package org.sdmlib.examples.helloworld.model.util;

import org.sdmlib.serialization.EntityFactory;
import de.uniks.networkparser.json.JsonIdMap;
import org.sdmlib.serialization.SDMLibJsonIdMap;
import org.sdmlib.examples.helloworld.model.Node;

public class NodeCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      Node.PROPERTY_COPY,
      Node.PROPERTY_ORIG,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new Node();
   }
   
   @Override
   public Object getValue(Object target, String attrName)
   {
      if (Node.PROPERTY_COPY.equalsIgnoreCase(attrName))
      {
         return ((Node) target).getCopy();
      }

      if (Node.PROPERTY_ORIG.equalsIgnoreCase(attrName))
      {
         return ((Node) target).getOrig();
      }

      return null;
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (JsonIdMap.REMOVE.equals(type) && value != null)
      {
         attrName = attrName + type;
      }

      if (Node.PROPERTY_COPY.equalsIgnoreCase(attrName))
      {
         ((Node) target).setCopy((Node) value);
         return true;
      }

      if (Node.PROPERTY_ORIG.equalsIgnoreCase(attrName))
      {
         ((Node) target).setOrig((Node) value);
         return true;
      }
      return false;
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

