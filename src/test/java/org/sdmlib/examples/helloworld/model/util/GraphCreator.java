package org.sdmlib.examples.helloworld.model.util;

import org.sdmlib.examples.helloworld.model.Edge;
import org.sdmlib.examples.helloworld.model.Graph;
import org.sdmlib.examples.helloworld.model.GraphComponent;
import org.sdmlib.examples.helloworld.model.Node;
import org.sdmlib.serialization.EntityFactory;

import de.uniks.networkparser.json.JsonIdMap;

public class GraphCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      Graph.PROPERTY_NODES,
      Graph.PROPERTY_EDGES,
      Graph.PROPERTY_GCS,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new Graph();
   }
   
   @Override
   public Object getValue(Object target, String attrName)
   {
      if (Graph.PROPERTY_NODES.equalsIgnoreCase(attrName))
      {
         return ((Graph) target).getNodes();
      }

      if (Graph.PROPERTY_EDGES.equalsIgnoreCase(attrName))
      {
         return ((Graph) target).getEdges();
      }

      if (Graph.PROPERTY_GCS.equalsIgnoreCase(attrName))
      {
         return ((Graph) target).getGcs();
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

      if (Graph.PROPERTY_NODES.equalsIgnoreCase(attrName))
      {
         ((Graph) target).addToNodes((Node) value);
         return true;
      }
      
      if ((Graph.PROPERTY_NODES + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Graph) target).removeFromNodes((Node) value);
         return true;
      }

      if (Graph.PROPERTY_EDGES.equalsIgnoreCase(attrName))
      {
         ((Graph) target).addToEdges((Edge) value);
         return true;
      }
      
      if ((Graph.PROPERTY_EDGES + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Graph) target).removeFromEdges((Edge) value);
         return true;
      }

      if (Graph.PROPERTY_GCS.equalsIgnoreCase(attrName))
      {
         ((Graph) target).addToGcs((GraphComponent) value);
         return true;
      }
      
      if ((Graph.PROPERTY_GCS + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Graph) target).removeFromGcs((GraphComponent) value);
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
      ((Graph) entity).removeYou();
   }
}


