package org.sdmlib.examples.ludo.model.creators;

import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.examples.ludo.model.Pawn;

public class PawnCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      Pawn.PROPERTY_COLOR,
      Pawn.PROPERTY_X,
      Pawn.PROPERTY_Y,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new Pawn();
   }
   
   @Override
   public Object getValue(Object target, String attrName)
   {
      if (Pawn.PROPERTY_COLOR.equalsIgnoreCase(attrName))
      {
         return ((Pawn) target).getColor();
      }

      if (Pawn.PROPERTY_X.equalsIgnoreCase(attrName))
      {
         return ((Pawn) target).getX();
      }

      if (Pawn.PROPERTY_Y.equalsIgnoreCase(attrName))
      {
         return ((Pawn) target).getY();
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

      if (Pawn.PROPERTY_COLOR.equalsIgnoreCase(attrName))
      {
         ((Pawn) target).setColor((String) value);
         return true;
      }

      if (Pawn.PROPERTY_X.equalsIgnoreCase(attrName))
      {
         ((Pawn) target).setX(Integer.parseInt(value.toString()));
         return true;
      }

      if (Pawn.PROPERTY_Y.equalsIgnoreCase(attrName))
      {
         ((Pawn) target).setY(Integer.parseInt(value.toString()));
         return true;
      }
      return false;
   }

   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((Pawn) entity).removeYou();
   }
}

