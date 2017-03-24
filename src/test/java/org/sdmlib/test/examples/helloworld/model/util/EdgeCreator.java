package org.sdmlib.test.examples.helloworld.model.util;

import org.sdmlib.serialization.EntityFactory;
import org.sdmlib.test.examples.helloworld.model.Edge;
import org.sdmlib.test.examples.helloworld.model.Graph;
import org.sdmlib.test.examples.helloworld.model.GraphComponent;
import org.sdmlib.test.examples.helloworld.model.Node;

import de.uniks.networkparser.IdMap;

public class EdgeCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      Edge.PROPERTY_NAME,
      Edge.PROPERTY_GRAPH,
      Edge.PROPERTY_SRC,
      Edge.PROPERTY_TGT,
      GraphComponent.PROPERTY_TEXT,
      GraphComponent.PROPERTY_PARENT,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new Edge();
   }
   
   @Override
   public Object getValue(Object target, String attrName)
   {
      if (Edge.PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         return ((Edge) target).getName();
      }

      if (Edge.PROPERTY_GRAPH.equalsIgnoreCase(attrName))
      {
         return ((Edge) target).getGraph();
      }

      if (Edge.PROPERTY_SRC.equalsIgnoreCase(attrName))
      {
         return ((Edge) target).getSrc();
      }

      if (Edge.PROPERTY_TGT.equalsIgnoreCase(attrName))
      {
         return ((Edge) target).getTgt();
      }

      if (GraphComponent.PROPERTY_TEXT.equalsIgnoreCase(attrName))
      {
         return ((GraphComponent) target).getText();
      }

      if (Edge.PROPERTY_PARENT.equalsIgnoreCase(attrName))
      {
         return ((Edge) target).getParent();
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

      if (Edge.PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         ((Edge) target).setName((String) value);
         return true;
      }

      if (Edge.PROPERTY_GRAPH.equalsIgnoreCase(attrName))
      {
         ((Edge) target).setGraph((Graph) value);
         return true;
      }

      if (Edge.PROPERTY_SRC.equalsIgnoreCase(attrName))
      {
         ((Edge) target).setSrc((Node) value);
         return true;
      }

      if (Edge.PROPERTY_TGT.equalsIgnoreCase(attrName))
      {
         ((Edge) target).setTgt((Node) value);
         return true;
      }

      if (GraphComponent.PROPERTY_TEXT.equalsIgnoreCase(attrName))
      {
         ((GraphComponent) target).setText((String) value);
         return true;
      }

      if (Edge.PROPERTY_PARENT.equalsIgnoreCase(attrName))
      {
         ((Edge) target).setParent((Graph) value);
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
      ((Edge) entity).removeYou();
   }
}


