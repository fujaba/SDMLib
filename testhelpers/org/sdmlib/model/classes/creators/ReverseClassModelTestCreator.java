package org.sdmlib.model.classes.creators;

import org.sdmlib.model.classes.ReverseClassModelTest;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;

public class ReverseClassModelTestCreator implements EntityFactory
{
   private final String[] properties = new String[]
   {
   };
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new ReverseClassModelTest();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((ReverseClassModelTest) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((ReverseClassModelTest) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((ReverseClassModelTest) entity).removeYou();
   }
}


