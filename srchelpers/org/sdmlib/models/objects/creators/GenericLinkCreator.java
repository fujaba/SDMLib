package org.sdmlib.models.objects.creators;

import org.sdmlib.serialization.interfaces.SendableEntityCreator;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.models.objects.GenericLink;

public class GenericLinkCreator implements SendableEntityCreator
{
   private final String[] properties = new String[]
   {
      GenericLink.PROPERTY_TGTLABEL,
      GenericLink.PROPERTY_SRCLABEL,
      GenericLink.PROPERTY_SRC,
      GenericLink.PROPERTY_TGT,
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
   
   public boolean setValue(Object target, String attrName, Object value)
   {
      return ((GenericLink) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }
}


