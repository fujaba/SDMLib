package org.sdmlib.models.transformations.creators;

import org.sdmlib.serialization.interfaces.SendableEntityCreator;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.models.transformations.AttributeOp;

public class AttributeOpCreator implements SendableEntityCreator
{
   private final String[] properties = new String[]
   {
      AttributeOp.PROPERTY_TEXT,
      AttributeOp.PROPERTY_OPERATIONOBJECT,
   };
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new AttributeOp();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((AttributeOp) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value)
   {
      return ((AttributeOp) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }
}




