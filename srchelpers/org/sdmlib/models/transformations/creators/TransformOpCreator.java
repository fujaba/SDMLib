package org.sdmlib.models.transformations.creators;

import org.sdmlib.serialization.interfaces.SendableEntityCreator;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.models.transformations.TransformOp;

public class TransformOpCreator implements SendableEntityCreator
{
   private final String[] properties = new String[]
   {
      TransformOp.PROPERTY_NAME,
      TransformOp.PROPERTY_OPOBJECTS,
      TransformOp.PROPERTY_STATEMENTS,
      TransformOp.PROPERTY_LINKOPS,
   };
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new TransformOp();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((TransformOp) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value)
   {
      return ((TransformOp) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }
}












