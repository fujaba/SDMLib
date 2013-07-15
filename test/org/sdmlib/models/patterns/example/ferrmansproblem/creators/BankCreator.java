package org.sdmlib.models.patterns.example.ferrmansproblem.creators;

import org.sdmlib.models.patterns.example.ferrmansproblem.creators.CreatorCreator;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.models.patterns.example.ferrmansproblem.Bank;

public class BankCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      Bank.PROPERTY_NAME,
      Bank.PROPERTY_AGE,
      Bank.PROPERTY_BOAT,
      Bank.PROPERTY_RIVER,
      Bank.PROPERTY_CARGOS,
   };
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new Bank();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((Bank) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (JsonIdMap.REMOVE.equals(type))
      {
         attrName = attrName + type;
      }
      return ((Bank) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((Bank) entity).removeYou();
   }
}

