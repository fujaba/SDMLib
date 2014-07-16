/*
   Copyright (c) 2014 zuendorf 
   
   Permission is hereby granted, free of charge, to any person obtaining a copy of this software 
   and associated documentation files (the "Software"), to deal in the Software without restriction, 
   including without limitation the rights to use, copy, modify, merge, publish, distribute, 
   sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is 
   furnished to do so, subject to the following conditions: 
   
   The above copyright notice and this permission notice shall be included in all copies or 
   substantial portions of the Software. 
   
   The Software shall be used for Good, not Evil. 
   
   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING 
   BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND 
   NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, 
   DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, 
   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */
   
package org.sdmlib.examples.ludo.model.util;

import org.sdmlib.examples.ludo.model.Field;
import org.sdmlib.examples.ludo.model.Pawn;
import org.sdmlib.examples.ludo.model.Player;
import org.sdmlib.serialization.EntityFactory;

import de.uniks.networkparser.json.JsonIdMap;

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
      int pos = attrName.indexOf('.');
      String attribute = attrName;
      
      if (pos > 0)
      {
         attribute = attrName.substring(0, pos);
      }

      if (Pawn.PROPERTY_COLOR.equalsIgnoreCase(attribute))
      {
         return ((Pawn) target).getColor();
      }

      if (Pawn.PROPERTY_X.equalsIgnoreCase(attribute))
      {
         return ((Pawn) target).getX();
      }

      if (Pawn.PROPERTY_Y.equalsIgnoreCase(attribute))
      {
         return ((Pawn) target).getY();
      }

      if (Pawn.PROPERTY_PLAYER.equalsIgnoreCase(attribute))
      {
         return ((Pawn) target).getPlayer();
      }

      if (Pawn.PROPERTY_POS.equalsIgnoreCase(attribute))
      {
         return ((Pawn) target).getPos();
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

      if (Pawn.PROPERTY_PLAYER.equalsIgnoreCase(attrName))
      {
         ((Pawn) target).setPlayer((Player) value);
         return true;
      }

      if (Pawn.PROPERTY_POS.equalsIgnoreCase(attrName))
      {
         ((Pawn) target).setPos((Field) value);
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
      ((Pawn) entity).removeYou();
   }
}
