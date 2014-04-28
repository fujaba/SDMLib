package org.sdmlib.examples.ludo.creators;

import org.sdmlib.examples.ludo.creators.CreatorCreator;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.examples.ludo.Field;

public class FieldCreator extends EntityFactory
{
   private final String[] properties = new String[]
   { Field.PROPERTY_COLOR, Field.PROPERTY_KIND, Field.PROPERTY_X,
         Field.PROPERTY_Y, Field.PROPERTY_POINT, Field.PROPERTY_GAME,
         Field.PROPERTY_NEXT, Field.PROPERTY_PREV, Field.PROPERTY_LANDING,
         Field.PROPERTY_ENTRY, Field.PROPERTY_STARTER,
         Field.PROPERTY_BASEOWNER, Field.PROPERTY_LANDER, Field.PROPERTY_PAWNS, };

   public String[] getProperties()
   {
      return properties;
   }

   public Object getSendableInstance(boolean reference)
   {
      return new Field();
   }

   public Object getValue(Object target, String attrName)
   {
      return ((Field) target).get(attrName);
   }

   public boolean setValue(Object target, String attrName, Object value,
         String type)
   {
      if (JsonIdMap.REMOVE.equals(type))
      {
         attrName = attrName + type;
      }
      return ((Field) target).set(attrName, value);
   }

   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   // ==========================================================================

   @Override
   public void removeObject(Object entity)
   {
      ((Field) entity).removeYou();
   }
}
