package org.sdmlib.examples.groupAccount.model.util;

import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.serialization.json.SDMLibJsonIdMap;
import org.sdmlib.examples.groupAccount.model.GroupAccount;
import org.sdmlib.examples.groupAccount.model.Person;
import org.sdmlib.examples.groupAccount.model.Item;

public class GroupAccountCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      GroupAccount.PROPERTY_PERSONS,
      GroupAccount.PROPERTY_ITEMS,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new GroupAccount();
   }
   
   @Override
   public Object getValue(Object target, String attrName)
   {
      if (GroupAccount.PROPERTY_PERSONS.equalsIgnoreCase(attrName))
      {
         return ((GroupAccount) target).getPersons();
      }

      if (GroupAccount.PROPERTY_ITEMS.equalsIgnoreCase(attrName))
      {
         return ((GroupAccount) target).getItems();
      }

      return null;
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (JsonIdMap.REMOVE.equals(type) && value != null)
      {
         attrName = attrName + type;
      }

      if (GroupAccount.PROPERTY_PERSONS.equalsIgnoreCase(attrName))
      {
         ((GroupAccount) target).addToPersons((Person) value);
         return true;
      }
      
      if ((GroupAccount.PROPERTY_PERSONS + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((GroupAccount) target).removeFromPersons((Person) value);
         return true;
      }

      if (GroupAccount.PROPERTY_ITEMS.equalsIgnoreCase(attrName))
      {
         ((GroupAccount) target).addToItems((Item) value);
         return true;
      }
      
      if ((GroupAccount.PROPERTY_ITEMS + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((GroupAccount) target).removeFromItems((Item) value);
         return true;
      }
      return false;
   }
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }}

