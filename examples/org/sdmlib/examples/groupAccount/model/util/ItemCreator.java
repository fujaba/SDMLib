package org.sdmlib.examples.groupAccount.model.util;

import org.sdmlib.examples.groupAccount.model.GroupAccount;
import org.sdmlib.examples.groupAccount.model.Item;
import org.sdmlib.examples.groupAccount.model.Person;
import org.sdmlib.serialization.EntityFactory;

import de.uniks.networkparser.json.JsonIdMap;

public class ItemCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      Item.PROPERTY_DESCRIPTION,
      Item.PROPERTY_VALUE,
      Item.PROPERTY_PARENT,
      Item.PROPERTY_BUYER,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new Item();
   }
   
   @Override
   public Object getValue(Object target, String attrName)
   {
      if (Item.PROPERTY_DESCRIPTION.equalsIgnoreCase(attrName))
      {
         return ((Item) target).getDescription();
      }

      if (Item.PROPERTY_VALUE.equalsIgnoreCase(attrName))
      {
         return ((Item) target).getValue();
      }

      if (Item.PROPERTY_PARENT.equalsIgnoreCase(attrName))
      {
         return ((Item) target).getParent();
      }

      if (Item.PROPERTY_BUYER.equalsIgnoreCase(attrName))
      {
         return ((Item) target).getBuyer();
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

      if (Item.PROPERTY_DESCRIPTION.equalsIgnoreCase(attrName))
      {
         ((Item) target).setDescription((String) value);
         return true;
      }

      if (Item.PROPERTY_VALUE.equalsIgnoreCase(attrName))
      {
         ((Item) target).setValue(Double.parseDouble(value.toString()));
         return true;
      }

      if (Item.PROPERTY_PARENT.equalsIgnoreCase(attrName))
      {
         ((Item) target).setParent((GroupAccount) value);
         return true;
      }

      if (Item.PROPERTY_BUYER.equalsIgnoreCase(attrName))
      {
         ((Item) target).setBuyer((Person) value);
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
      ((Item) entity).removeYou();
   }
}

