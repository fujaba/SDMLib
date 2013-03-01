package org.sdmlib.examples.groupAccount.creators;

import org.sdmlib.examples.groupAccount.Item;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;

public class ItemCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      Item.PROPERTY_DESCRIPTION,
      Item.PROPERTY_VALUE,
      Item.PROPERTY_PARENT,
      Item.PROPERTY_BUYER,
   };
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new Item();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((Item) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((Item) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((Item) entity).removeYou();
   }
}







