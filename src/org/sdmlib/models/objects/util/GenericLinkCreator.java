package org.sdmlib.models.objects.util;

import org.sdmlib.serialization.EntityFactory;
import de.uniks.networkparser.json.JsonIdMap;
import org.sdmlib.models.objects.GenericLink;
import org.sdmlib.models.objects.GenericObject;
import org.sdmlib.models.objects.GenericGraph;

public class GenericLinkCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      GenericLink.PROPERTY_TGTLABEL,
      GenericLink.PROPERTY_SRCLABEL,
      GenericLink.PROPERTY_SRC,
      GenericLink.PROPERTY_TGT,
      GenericLink.PROPERTY_GRAPH,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new GenericLink();
   }
   
   @Override
   public Object getValue(Object target, String attrName)
   {
      if (GenericLink.PROPERTY_TGTLABEL.equalsIgnoreCase(attrName))
      {
         return ((GenericLink) target).getTgtLabel();
      }

      if (GenericLink.PROPERTY_SRCLABEL.equalsIgnoreCase(attrName))
      {
         return ((GenericLink) target).getSrcLabel();
      }

      if (GenericLink.PROPERTY_SRC.equalsIgnoreCase(attrName))
      {
         return ((GenericLink) target).getSrc();
      }

      if (GenericLink.PROPERTY_TGT.equalsIgnoreCase(attrName))
      {
         return ((GenericLink) target).getTgt();
      }

      if (GenericLink.PROPERTY_GRAPH.equalsIgnoreCase(attrName))
      {
         return ((GenericLink) target).getGraph();
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

      if (GenericLink.PROPERTY_TGTLABEL.equalsIgnoreCase(attrName))
      {
         ((GenericLink) target).setTgtLabel((String) value);
         return true;
      }

      if (GenericLink.PROPERTY_SRCLABEL.equalsIgnoreCase(attrName))
      {
         ((GenericLink) target).setSrcLabel((String) value);
         return true;
      }

      if (GenericLink.PROPERTY_SRC.equalsIgnoreCase(attrName))
      {
         ((GenericLink) target).setSrc((GenericObject) value);
         return true;
      }

      if (GenericLink.PROPERTY_TGT.equalsIgnoreCase(attrName))
      {
         ((GenericLink) target).setTgt((GenericObject) value);
         return true;
      }

      if (GenericLink.PROPERTY_GRAPH.equalsIgnoreCase(attrName))
      {
         ((GenericLink) target).setGraph((GenericGraph) value);
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
      ((GenericLink) entity).removeYou();
   }
}
