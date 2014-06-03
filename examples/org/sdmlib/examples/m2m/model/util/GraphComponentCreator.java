package org.sdmlib.examples.m2m.model.util;

import org.sdmlib.serialization.EntityFactory;
import de.uniks.networkparser.json.JsonIdMap;
import org.sdmlib.examples.m2m.model.GraphComponent;
import org.sdmlib.examples.m2m.model.Graph;

public class GraphComponentCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
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
      return new GraphComponent();
   }
   
   @Override
   public Object getValue(Object target, String attrName)
   {
      if (GraphComponent.PROPERTY_TEXT.equalsIgnoreCase(attrName))
      {
         return ((GraphComponent) target).getText();
      }

      if (GraphComponent.PROPERTY_PARENT.equalsIgnoreCase(attrName))
      {
         return ((GraphComponent) target).getParent();
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

      if (GraphComponent.PROPERTY_TEXT.equalsIgnoreCase(attrName))
      {
         ((GraphComponent) target).setText((String) value);
         return true;
      }

      if (GraphComponent.PROPERTY_PARENT.equalsIgnoreCase(attrName))
      {
         ((GraphComponent) target).setParent((Graph) value);
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
      ((GraphComponent) entity).removeYou();
   }
}

