package org.sdmlib.models.objects.util;

import org.sdmlib.serialization.EntityFactory;
import de.uniks.networkparser.json.JsonIdMap;
import org.sdmlib.models.objects.GenericObject;
import org.sdmlib.models.objects.GenericGraph;
import org.sdmlib.models.objects.GenericAttribute;
import org.sdmlib.models.objects.GenericLink;

public class GenericObjectCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      GenericObject.PROPERTY_NAME,
      GenericObject.PROPERTY_TYPE,
      GenericObject.PROPERTY_ICON,
      GenericObject.PROPERTY_GRAPH,
      GenericObject.PROPERTY_ATTRS,
      GenericObject.PROPERTY_OUTGOINGLINKS,
      GenericObject.PROPERTY_INCOMMINGLINKS,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new GenericObject();
   }
   
   @Override
   public Object getValue(Object target, String attrName)
   {
      if (GenericObject.PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         return ((GenericObject) target).getName();
      }

      if (GenericObject.PROPERTY_TYPE.equalsIgnoreCase(attrName))
      {
         return ((GenericObject) target).getType();
      }

      if (GenericObject.PROPERTY_ICON.equalsIgnoreCase(attrName))
      {
         return ((GenericObject) target).getIcon();
      }

      if (GenericObject.PROPERTY_GRAPH.equalsIgnoreCase(attrName))
      {
         return ((GenericObject) target).getGraph();
      }

      if (GenericObject.PROPERTY_ATTRS.equalsIgnoreCase(attrName))
      {
         return ((GenericObject) target).getAttrs();
      }

      if (GenericObject.PROPERTY_OUTGOINGLINKS.equalsIgnoreCase(attrName))
      {
         return ((GenericObject) target).getOutgoingLinks();
      }

      if (GenericObject.PROPERTY_INCOMMINGLINKS.equalsIgnoreCase(attrName))
      {
         return ((GenericObject) target).getIncommingLinks();
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

      if (GenericObject.PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         ((GenericObject) target).setName((String) value);
         return true;
      }

      if (GenericObject.PROPERTY_TYPE.equalsIgnoreCase(attrName))
      {
         ((GenericObject) target).setType((String) value);
         return true;
      }

      if (GenericObject.PROPERTY_ICON.equalsIgnoreCase(attrName))
      {
         ((GenericObject) target).setIcon((String) value);
         return true;
      }

      if (GenericObject.PROPERTY_GRAPH.equalsIgnoreCase(attrName))
      {
         ((GenericObject) target).setGraph((GenericGraph) value);
         return true;
      }

      if (GenericObject.PROPERTY_ATTRS.equalsIgnoreCase(attrName))
      {
         ((GenericObject) target).addToAttrs((GenericAttribute) value);
         return true;
      }
      
      if ((GenericObject.PROPERTY_ATTRS + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((GenericObject) target).removeFromAttrs((GenericAttribute) value);
         return true;
      }

      if (GenericObject.PROPERTY_OUTGOINGLINKS.equalsIgnoreCase(attrName))
      {
         ((GenericObject) target).addToOutgoingLinks((GenericLink) value);
         return true;
      }
      
      if ((GenericObject.PROPERTY_OUTGOINGLINKS + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((GenericObject) target).removeFromOutgoingLinks((GenericLink) value);
         return true;
      }

      if (GenericObject.PROPERTY_INCOMMINGLINKS.equalsIgnoreCase(attrName))
      {
         ((GenericObject) target).addToIncommingLinks((GenericLink) value);
         return true;
      }
      
      if ((GenericObject.PROPERTY_INCOMMINGLINKS + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((GenericObject) target).removeFromIncommingLinks((GenericLink) value);
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
      ((GenericObject) entity).removeYou();
   }
}
