package org.sdmlib.models.transformations.creators;

import org.sdmlib.models.transformations.creators.CreatorCreator;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.models.transformations.Statement;

public class StatementCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      Statement.PROPERTY_TEXT,
      Statement.PROPERTY_NEXT,
      Statement.PROPERTY_PREV,
      Statement.PROPERTY_OPERATIONOBJECTS,
      Statement.PROPERTY_TRANSFORMOP,
   };
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new Statement();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((Statement) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (JsonIdMap.REMOVE.equals(type))
      {
         attrName = attrName + type;
      }
      return ((Statement) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((Statement) entity).removeYou();
   }
}

