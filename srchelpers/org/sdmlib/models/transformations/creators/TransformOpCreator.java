package org.sdmlib.models.transformations.creators;

import org.sdmlib.models.transformations.creators.CreatorCreator;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.models.transformations.TransformOp;

public class TransformOpCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      TransformOp.PROPERTY_NAME,
      TransformOp.PROPERTY_OPOBJECTS,
      TransformOp.PROPERTY_LINKOPS,
      TransformOp.PROPERTY_STATEMENTS,
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
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (JsonIdMap.REMOVE.equals(type))
      {
         attrName = attrName + type;
      }
      return ((TransformOp) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((TransformOp) entity).removeYou();
   }
}

