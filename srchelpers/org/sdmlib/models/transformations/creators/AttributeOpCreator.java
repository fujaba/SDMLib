package org.sdmlib.models.transformations.creators;

import org.sdmlib.models.transformations.creators.CreatorCreator;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.models.transformations.AttributeOp;

public class AttributeOpCreator extends EntityFactory
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
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (JsonIdMap.REMOVE.equals(type))
      {
         attrName = attrName + type;
      }
      return ((AttributeOp) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((AttributeOp) entity).removeYou();
   }
}

