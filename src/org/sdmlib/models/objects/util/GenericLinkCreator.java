package org.sdmlib.models.objects.util;

import org.sdmlib.models.objects.GenericLink;
import org.sdmlib.serialization.interfaces.EntityFactory;

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
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new GenericLink();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((GenericLink) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((GenericLink) target).set(attrName, value);
   }
   
 
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((GenericLink) entity).removeYou();
   }
}


