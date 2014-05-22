package org.sdmlib.examples.groupAccount.model.util;

import org.sdmlib.serialization.EntityFactory;
import de.uniks.networkparser.json.JsonIdMap;
import org.sdmlib.serialization.SDMLibJsonIdMap;
import org.sdmlib.examples.groupAccount.model.Person;
import org.sdmlib.examples.groupAccount.model.GroupAccount;
import org.sdmlib.examples.groupAccount.model.Item;

public class PersonCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      Person.PROPERTY_NAME,
      Person.PROPERTY_BALANCE,
      Person.PROPERTY_PARENT,
      Person.PROPERTY_ITEMS,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new Person();
   }
   
   @Override
   public Object getValue(Object target, String attrName)
   {
      if (Person.PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         return ((Person) target).getName();
      }

      if (Person.PROPERTY_BALANCE.equalsIgnoreCase(attrName))
      {
         return ((Person) target).getBalance();
      }

      if (Person.PROPERTY_PARENT.equalsIgnoreCase(attrName))
      {
         return ((Person) target).getParent();
      }

      if (Person.PROPERTY_ITEMS.equalsIgnoreCase(attrName))
      {
         return ((Person) target).getItems();
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

      if (Person.PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         ((Person) target).setName((String) value);
         return true;
      }

      if (Person.PROPERTY_BALANCE.equalsIgnoreCase(attrName))
      {
         ((Person) target).setBalance(Double.parseDouble(value.toString()));
         return true;
      }

      if (Person.PROPERTY_PARENT.equalsIgnoreCase(attrName))
      {
         ((Person) target).setParent((GroupAccount) value);
         return true;
      }

      if (Person.PROPERTY_ITEMS.equalsIgnoreCase(attrName))
      {
         ((Person) target).addToItems((Item) value);
         return true;
      }
      
      if ((Person.PROPERTY_ITEMS + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Person) target).removeFromItems((Item) value);
         return true;
      }
      return false;
   }
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }
   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((Person) entity).removeYou();
   }
}

