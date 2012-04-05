package org.sdmlib.models.transformations.creators;

import org.sdmlib.serialization.interfaces.SendableEntityCreator;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.models.transformations.OperationObject;

public class OperationObjectCreator implements SendableEntityCreator
{
   private final String[] properties = new String[]
   {
      OperationObject.PROPERTY_NAME,
      OperationObject.PROPERTY_TYPE,
      OperationObject.PROPERTY_TRANSFORMOP,
      OperationObject.PROPERTY_STATEMENTS,
      OperationObject.PROPERTY_SET,
      OperationObject.PROPERTY_ATTRIBUTEOPS,
   };
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new OperationObject();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((OperationObject) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value)
   {
      return ((OperationObject) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }
}







