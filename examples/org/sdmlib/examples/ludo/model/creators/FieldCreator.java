package org.sdmlib.examples.ludo.model.creators;

import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.serialization.json.SDMLibJsonIdMap;
import org.sdmlib.examples.ludo.model.Field;

public class FieldCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      Field.PROPERTY_COLOR,
      Field.PROPERTY_KIND,
      Field.PROPERTY_X,
      Field.PROPERTY_Y,
      Field.PROPERTY_POINT,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new Field();
   }
   
   @Override
   public Object getValue(Object target, String attrName)
   {
      if (Field.PROPERTY_COLOR.equalsIgnoreCase(attrName))
      {
         return ((Field) target).getColor();
      }

      if (Field.PROPERTY_KIND.equalsIgnoreCase(attrName))
      {
         return ((Field) target).getKind();
      }

      if (Field.PROPERTY_X.equalsIgnoreCase(attrName))
      {
         return ((Field) target).getX();
      }

      if (Field.PROPERTY_Y.equalsIgnoreCase(attrName))
      {
         return ((Field) target).getY();
      }

      if (Field.PROPERTY_POINT.equalsIgnoreCase(attrName))
      {
         return ((Field) target).getPoint();
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

      if (Field.PROPERTY_COLOR.equalsIgnoreCase(attrName))
      {
         ((Field) target).setColor((String) value);
         return true;
      }

      if (Field.PROPERTY_KIND.equalsIgnoreCase(attrName))
      {
         ((Field) target).setKind((String) value);
         return true;
      }

      if (Field.PROPERTY_X.equalsIgnoreCase(attrName))
      {
         ((Field) target).setX(Integer.parseInt(value.toString()));
         return true;
      }

      if (Field.PROPERTY_Y.equalsIgnoreCase(attrName))
      {
         ((Field) target).setY(Integer.parseInt(value.toString()));
         return true;
      }

      if (Field.PROPERTY_POINT.equalsIgnoreCase(attrName))
      {
         ((Field) target).setPoint((java.awt.Point) value);
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
      ((Field) entity).removeYou();
   }
}

