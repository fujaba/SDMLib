package org.sdmlib.examples.groupAccount.creators;

import org.sdmlib.examples.groupAccount.GroupAccount;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;

public class GroupAccountCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      GroupAccount.PROPERTY_PERSONS,
      GroupAccount.PROPERTY_ITEMS,
   };
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new GroupAccount();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((GroupAccount) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((GroupAccount) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((GroupAccount) entity).removeYou();
   }
}







