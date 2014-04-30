package org.sdmlib.examples.ludo.model.util;

import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.serialization.json.SDMLibJsonIdMap;
import java.awt.Point;

public class PointCreator extends EntityFactory
{
   public static final String PROPERTY_X = "x";
   public static final String PROPERTY_Y = "y";
   private final String[] properties = new String[]
   {
      PROPERTY_X,
      PROPERTY_Y,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new Point();
   }
   
   @Override
   public Object getValue(Object target, String attrName)
   {
      if (PointCreator.PROPERTY_X.equalsIgnoreCase(attrName))
      {
         return ((Point) target).getX();
      }

      if (PointCreator.PROPERTY_Y.equalsIgnoreCase(attrName))
      {
         return ((Point) target).getY();
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

      if (PointCreator.PROPERTY_X.equalsIgnoreCase(attrName))
      {
         ((Point) target).x = (Integer.parseInt(value.toString()));
         return true;
      }

      if (PointCreator.PROPERTY_Y.equalsIgnoreCase(attrName))
      {
         ((Point) target).y = (Integer.parseInt(value.toString()));
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
      // wrapped object has no removeYou method
   }
}

