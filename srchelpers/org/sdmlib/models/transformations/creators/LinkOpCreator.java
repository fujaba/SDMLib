package org.sdmlib.models.transformations.creators;

import org.sdmlib.serialization.interfaces.SendableEntityCreator;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.models.transformations.LinkOp;

public class LinkOpCreator implements SendableEntityCreator
{
   private final String[] properties = new String[]
   {
      LinkOp.PROPERTY_SRCTEXT,
      LinkOp.PROPERTY_TGTTEXT,
      LinkOp.PROPERTY_TRANSFORMOP,
   };
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new LinkOp();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((LinkOp) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((LinkOp) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }
}





