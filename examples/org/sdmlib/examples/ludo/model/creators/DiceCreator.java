package org.sdmlib.examples.ludo.model.creators;

import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.serialization.json.SDMLibJsonIdMap;
import org.sdmlib.examples.ludo.model.Dice;

public class DiceCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      Dice.PROPERTY_VALUE,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new Dice();
   }
   
   @Override
   public Object getValue(Object target, String attrName)
   {
      if (Dice.PROPERTY_VALUE.equalsIgnoreCase(attrName))
      {
         return ((Dice) target).getValue();
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

      if (Dice.PROPERTY_VALUE.equalsIgnoreCase(attrName))
      {
         ((Dice) target).setValue(Integer.parseInt(value.toString()));
         return true;
      }
      return false;
   }
   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = (JsonIdMap) new SDMLibJsonIdMap().withSessionId(sessionID);
      
               jsonIdMap.withCreator(new org.sdmlib.examples.ludo.model.creators.LudoCreator());
         jsonIdMap.withCreator(new org.sdmlib.examples.ludo.model.creators.LudoPOCreator());
         jsonIdMap.withCreator(new org.sdmlib.examples.ludo.model.creators.PlayerCreator());
         jsonIdMap.withCreator(new org.sdmlib.examples.ludo.model.creators.PlayerPOCreator());
         jsonIdMap.withCreator(new org.sdmlib.examples.ludo.model.creators.DiceCreator());
         jsonIdMap.withCreator(new org.sdmlib.examples.ludo.model.creators.DicePOCreator());
         jsonIdMap.withCreator(new org.sdmlib.examples.ludo.model.creators.FieldCreator());
         jsonIdMap.withCreator(new org.sdmlib.examples.ludo.model.creators.FieldPOCreator());
         jsonIdMap.withCreator(new org.sdmlib.examples.ludo.model.creators.PawnCreator());
         jsonIdMap.withCreator(new org.sdmlib.examples.ludo.model.creators.PawnPOCreator());

      return jsonIdMap;
   }
   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((Dice) entity).removeYou();
   }
}

