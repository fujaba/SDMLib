package org.sdmlib.models.transformations.creators;

import org.sdmlib.models.transformations.creators.CreatorCreator;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.models.transformations.OperationObject;

public class OperationObjectCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      OperationObject.PROPERTY_NAME,
      OperationObject.PROPERTY_TYPE,
      OperationObject.PROPERTY_SET,
      OperationObject.PROPERTY_TRANSFORMOP,
      OperationObject.PROPERTY_ATTRIBUTEOPS,
      OperationObject.PROPERTY_OUTGOINGS,
      OperationObject.PROPERTY_INCOMINGS,
      OperationObject.PROPERTY_STATEMENTS,
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
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (JsonIdMap.REMOVE.equals(type))
      {
         attrName = attrName + type;
      }
      return ((OperationObject) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((OperationObject) entity).removeYou();
   }
}

