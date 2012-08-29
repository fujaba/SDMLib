package org.sdmlib.models.transformations.creators;

import org.sdmlib.models.transformations.TransformOp;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;

public class TransformOpCreator implements EntityFactory
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
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
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













