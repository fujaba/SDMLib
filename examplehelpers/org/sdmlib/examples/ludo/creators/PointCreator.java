package org.sdmlib.examples.ludo.creators;

import org.sdmlib.examples.ludo.creators.CreatorCreator;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;
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
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new Point();
   }
   
   public Object getValue(Object target, String attrName)
   {
      if (PROPERTY_X.equalsIgnoreCase(attrName))
      {
         return ((Point) target).getX();
      }

      if (PROPERTY_Y.equalsIgnoreCase(attrName))
      {
         return ((Point) target).getY();
      }

      return null;
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (PROPERTY_X.equalsIgnoreCase(attrName))
      {
         ((Point) target).x = (Integer.parseInt(value.toString()));
         return true;
      }

      if (PROPERTY_Y.equalsIgnoreCase(attrName))
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








