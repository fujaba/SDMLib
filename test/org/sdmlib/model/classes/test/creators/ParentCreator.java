package org.sdmlib.model.classes.test.creators;

import org.sdmlib.model.classes.test.creators.CreatorCreator;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.model.classes.test.Parent;

public class ParentCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      Parent.PROPERTY_NAME,
      Parent.PROPERTY_UNCLE,
   };
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new Parent();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((Parent) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((Parent) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((Parent) entity).removeYou();
   }
}

