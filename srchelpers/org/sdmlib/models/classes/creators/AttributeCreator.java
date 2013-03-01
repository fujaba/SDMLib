package org.sdmlib.models.classes.creators;

import org.sdmlib.models.classes.Attribute;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;

public class AttributeCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      Attribute.PROPERTY_INITIALIZATION,
      Attribute.PROPERTY_CLAZZ,
   };
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new Attribute();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((Attribute) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((Attribute) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((Attribute) entity).removeYou();
   }
}



