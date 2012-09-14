package org.sdmlib.examples.ludo.creators;

import org.sdmlib.examples.ludo.Pawn;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;

public class PawnCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      Pawn.PROPERTY_COLOR,
      Pawn.PROPERTY_X,
      Pawn.PROPERTY_Y,
      Pawn.PROPERTY_PLAYER,
      Pawn.PROPERTY_POS,
   };
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new Pawn();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((Pawn) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((Pawn) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((Pawn) entity).removeYou();
   }
}


