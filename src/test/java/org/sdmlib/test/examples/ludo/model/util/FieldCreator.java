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
   
package org.sdmlib.test.examples.ludo.model.util;

import org.sdmlib.serialization.EntityFactory;
import org.sdmlib.test.examples.ludo.model.Field;
import org.sdmlib.test.examples.ludo.model.Ludo;
import org.sdmlib.test.examples.ludo.model.Pawn;
import org.sdmlib.test.examples.ludo.model.Player;

import de.uniks.networkparser.IdMap;

public class FieldCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      Field.PROPERTY_COLOR,
      Field.PROPERTY_KIND,
      Field.PROPERTY_X,
      Field.PROPERTY_Y,
      Field.PROPERTY_POINT,
      Field.PROPERTY_GAME,
      Field.PROPERTY_NEXT,
      Field.PROPERTY_PREV,
      Field.PROPERTY_LANDING,
      Field.PROPERTY_ENTRY,
      Field.PROPERTY_STARTER,
      Field.PROPERTY_BASEOWNER,
      Field.PROPERTY_LANDER,
      Field.PROPERTY_PAWNS,
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
      int pos = attrName.indexOf('.');
      String attribute = attrName;
      
      if (pos > 0)
      {
         attribute = attrName.substring(0, pos);
      }

      if (Field.PROPERTY_COLOR.equalsIgnoreCase(attribute))
      {
         return ((Field) target).getColor();
      }

      if (Field.PROPERTY_KIND.equalsIgnoreCase(attribute))
      {
         return ((Field) target).getKind();
      }

      if (Field.PROPERTY_X.equalsIgnoreCase(attribute))
      {
         return ((Field) target).getX();
      }

      if (Field.PROPERTY_Y.equalsIgnoreCase(attribute))
      {
         return ((Field) target).getY();
      }

      if (Field.PROPERTY_POINT.equalsIgnoreCase(attribute))
      {
         return ((Field) target).getPoint();
      }

      if (Field.PROPERTY_GAME.equalsIgnoreCase(attribute))
      {
         return ((Field) target).getGame();
      }

      if (Field.PROPERTY_NEXT.equalsIgnoreCase(attribute))
      {
         return ((Field) target).getNext();
      }

      if (Field.PROPERTY_PREV.equalsIgnoreCase(attribute))
      {
         return ((Field) target).getPrev();
      }

      if (Field.PROPERTY_LANDING.equalsIgnoreCase(attribute))
      {
         return ((Field) target).getLanding();
      }

      if (Field.PROPERTY_ENTRY.equalsIgnoreCase(attribute))
      {
         return ((Field) target).getEntry();
      }

      if (Field.PROPERTY_STARTER.equalsIgnoreCase(attribute))
      {
         return ((Field) target).getStarter();
      }

      if (Field.PROPERTY_BASEOWNER.equalsIgnoreCase(attribute))
      {
         return ((Field) target).getBaseowner();
      }

      if (Field.PROPERTY_LANDER.equalsIgnoreCase(attribute))
      {
         return ((Field) target).getLander();
      }

      if (Field.PROPERTY_PAWNS.equalsIgnoreCase(attribute))
      {
         return ((Field) target).getPawns();
      }
      
      return null;
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (REMOVE.equals(type) && value != null)
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

      if (Field.PROPERTY_GAME.equalsIgnoreCase(attrName))
      {
         ((Field) target).setGame((Ludo) value);
         return true;
      }

      if (Field.PROPERTY_NEXT.equalsIgnoreCase(attrName))
      {
         ((Field) target).setNext((Field) value);
         return true;
      }

      if (Field.PROPERTY_PREV.equalsIgnoreCase(attrName))
      {
         ((Field) target).setPrev((Field) value);
         return true;
      }

      if (Field.PROPERTY_LANDING.equalsIgnoreCase(attrName))
      {
         ((Field) target).setLanding((Field) value);
         return true;
      }

      if (Field.PROPERTY_ENTRY.equalsIgnoreCase(attrName))
      {
         ((Field) target).setEntry((Field) value);
         return true;
      }

      if (Field.PROPERTY_STARTER.equalsIgnoreCase(attrName))
      {
         ((Field) target).setStarter((Player) value);
         return true;
      }

      if (Field.PROPERTY_BASEOWNER.equalsIgnoreCase(attrName))
      {
         ((Field) target).setBaseowner((Player) value);
         return true;
      }

      if (Field.PROPERTY_LANDER.equalsIgnoreCase(attrName))
      {
         ((Field) target).setLander((Player) value);
         return true;
      }

      if (Field.PROPERTY_PAWNS.equalsIgnoreCase(attrName))
      {
         ((Field) target).addToPawns((Pawn) value);
         return true;
      }
      
      if ((Field.PROPERTY_PAWNS + REMOVE).equalsIgnoreCase(attrName))
      {
         ((Field) target).removeFromPawns((Pawn) value);
         return true;
      }
      
      return false;
   }
   public static IdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }
   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((Field) entity).removeYou();
   }
}
