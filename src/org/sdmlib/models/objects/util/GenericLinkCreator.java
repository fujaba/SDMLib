package org.sdmlib.models.objects.util;

import org.sdmlib.models.objects.GenericGraph;
import org.sdmlib.models.objects.GenericLink;
import org.sdmlib.models.objects.GenericObject;
import org.sdmlib.serialization.EntityFactory;

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

      int pos = attrName.indexOf('.');
      String attribute = attrName;
      
      if (pos > 0)
      {
         attribute = attrName.substring(0, pos);
      }

      if (GenericLink.PROPERTY_TGTLABEL.equalsIgnoreCase(attribute))
      {
         return ((GenericLink)target).getTgtLabel();
      }

      if (GenericLink.PROPERTY_SRCLABEL.equalsIgnoreCase(attribute))
      {
         return ((GenericLink)target).getSrcLabel();
      }

      if (GenericLink.PROPERTY_SRC.equalsIgnoreCase(attribute))
      {
         return ((GenericLink)target).getSrc();
      }

      if (GenericLink.PROPERTY_TGT.equalsIgnoreCase(attribute))
      {
         return ((GenericLink)target).getTgt();
      }

      if (GenericLink.PROPERTY_GRAPH.equalsIgnoreCase(attribute))
      {
         return ((GenericLink)target).getGraph();
      }
      return super.getValue(target, attribute);
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (GenericLink.PROPERTY_TGTLABEL.equalsIgnoreCase(attrName))
      {
         ((GenericLink)target).setTgtLabel((String) value);
         return true;
      }

      if (GenericLink.PROPERTY_SRCLABEL.equalsIgnoreCase(attrName))
      {
         ((GenericLink)target).setSrcLabel((String) value);
         return true;
      }

      if (GenericLink.PROPERTY_SRC.equalsIgnoreCase(attrName))
      {
         ((GenericLink)target).setSrc((GenericObject) value);
         return true;
      }

      if (GenericLink.PROPERTY_TGT.equalsIgnoreCase(attrName))
      {
         ((GenericLink)target).setTgt((GenericObject) value);
         return true;
      }

      if (GenericLink.PROPERTY_GRAPH.equalsIgnoreCase(attrName))
      {
         ((GenericLink)target).setGraph((GenericGraph) value);
         return true;
      }
      return super.setValue(target, attrName, value, type);
   }
 
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((GenericLink) entity).removeYou();
   }
}
