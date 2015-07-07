package org.sdmlib.test.examples.helloworld.model.util;

import org.sdmlib.serialization.EntityFactory;
import org.sdmlib.test.examples.helloworld.model.Edge;
import org.sdmlib.test.examples.helloworld.model.Graph;
import org.sdmlib.test.examples.helloworld.model.GraphComponent;
import org.sdmlib.test.examples.helloworld.model.Node;

import de.uniks.networkparser.json.JsonIdMap;

public class NodeCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      Node.PROPERTY_COPY,
      Node.PROPERTY_ORIG,
      Node.PROPERTY_NAME,
      Node.PROPERTY_GRAPH,
      Node.PROPERTY_OUTEDGES,
      Node.PROPERTY_INEDGES,
      GraphComponent.PROPERTY_TEXT,
      GraphComponent.PROPERTY_PARENT,
      Node.PROPERTY_LINKSTO,
      Node.PROPERTY_LINKSFROM,
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

      if (Node.PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         return ((Node) target).getName();
      }

      if (Node.PROPERTY_GRAPH.equalsIgnoreCase(attrName))
      {
         return ((Node) target).getGraph();
      }

      if (Node.PROPERTY_OUTEDGES.equalsIgnoreCase(attrName))
      {
         return ((Node) target).getOutEdges();
      }

      if (Node.PROPERTY_INEDGES.equalsIgnoreCase(attrName))
      {
         return ((Node) target).getInEdges();
      }

      if (GraphComponent.PROPERTY_TEXT.equalsIgnoreCase(attrName))
      {
         return ((GraphComponent) target).getText();
      }

      if (Node.PROPERTY_PARENT.equalsIgnoreCase(attrName))
      {
         return ((Node) target).getParent();
      }

      if (Node.PROPERTY_LINKSTO.equalsIgnoreCase(attrName))
      {
         return ((Node) target).getLinksTo();
      }

      if (Node.PROPERTY_LINKSFROM.equalsIgnoreCase(attrName))
      {
         return ((Node) target).getLinksFrom();
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

      if (Node.PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         ((Node) target).setName((String) value);
         return true;
      }

      if (Node.PROPERTY_GRAPH.equalsIgnoreCase(attrName))
      {
         ((Node) target).setGraph((Graph) value);
         return true;
      }

      if (Node.PROPERTY_OUTEDGES.equalsIgnoreCase(attrName))
      {
         ((Node) target).addToOutEdges((Edge) value);
         return true;
      }
      
      if ((Node.PROPERTY_OUTEDGES + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Node) target).removeFromOutEdges((Edge) value);
         return true;
      }

      if (Node.PROPERTY_INEDGES.equalsIgnoreCase(attrName))
      {
         ((Node) target).addToInEdges((Edge) value);
         return true;
      }
      
      if ((Node.PROPERTY_INEDGES + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Node) target).removeFromInEdges((Edge) value);
         return true;
      }

      if (GraphComponent.PROPERTY_TEXT.equalsIgnoreCase(attrName))
      {
         ((GraphComponent) target).setText((String) value);
         return true;
      }

      if (Node.PROPERTY_PARENT.equalsIgnoreCase(attrName))
      {
         ((Node) target).setParent((Graph) value);
         return true;
      }

      if (Node.PROPERTY_LINKSTO.equalsIgnoreCase(attrName))
      {
         ((Node) target).addToLinksTo((Node) value);
         return true;
      }
      
      if ((Node.PROPERTY_LINKSTO + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Node) target).removeFromLinksTo((Node) value);
         return true;
      }

      if (Node.PROPERTY_LINKSFROM.equalsIgnoreCase(attrName))
      {
         ((Node) target).addToLinksFrom((Node) value);
         return true;
      }
      
      if ((Node.PROPERTY_LINKSFROM + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Node) target).removeFromLinksFrom((Node) value);
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



