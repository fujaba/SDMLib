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
import org.sdmlib.test.examples.ludo.model.Dice;
import org.sdmlib.test.examples.ludo.model.Field;
import org.sdmlib.test.examples.ludo.model.Ludo;
import org.sdmlib.test.examples.ludo.model.Pawn;
import org.sdmlib.test.examples.ludo.model.Player;

import de.uniks.networkparser.json.JsonIdMap;

public class PlayerCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      Player.PROPERTY_COLOR,
      Player.PROPERTY_ENUMCOLOR,
      Player.PROPERTY_NAME,
      Player.PROPERTY_X,
      Player.PROPERTY_Y,
      Player.PROPERTY_GAME,
      Player.PROPERTY_NEXT,
      Player.PROPERTY_PREV,
      Player.PROPERTY_DICE,
      Player.PROPERTY_START,
      Player.PROPERTY_BASE,
      Player.PROPERTY_LANDING,
      Player.PROPERTY_PAWNS,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new Player();
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

      if (Player.PROPERTY_COLOR.equalsIgnoreCase(attribute))
      {
         return ((Player) target).getColor();
      }

      if (Player.PROPERTY_ENUMCOLOR.equalsIgnoreCase(attribute))
      {
         return ((Player) target).getEnumColor();
      }

      if (Player.PROPERTY_NAME.equalsIgnoreCase(attribute))
      {
         return ((Player) target).getName();
      }

      if (Player.PROPERTY_X.equalsIgnoreCase(attribute))
      {
         return ((Player) target).getX();
      }

      if (Player.PROPERTY_Y.equalsIgnoreCase(attribute))
      {
         return ((Player) target).getY();
      }

      if (Player.PROPERTY_GAME.equalsIgnoreCase(attribute))
      {
         return ((Player) target).getGame();
      }

      if (Player.PROPERTY_NEXT.equalsIgnoreCase(attribute))
      {
         return ((Player) target).getNext();
      }

      if (Player.PROPERTY_PREV.equalsIgnoreCase(attribute))
      {
         return ((Player) target).getPrev();
      }

      if (Player.PROPERTY_DICE.equalsIgnoreCase(attribute))
      {
         return ((Player) target).getDice();
      }

      if (Player.PROPERTY_START.equalsIgnoreCase(attribute))
      {
         return ((Player) target).getStart();
      }

      if (Player.PROPERTY_BASE.equalsIgnoreCase(attribute))
      {
         return ((Player) target).getBase();
      }

      if (Player.PROPERTY_LANDING.equalsIgnoreCase(attribute))
      {
         return ((Player) target).getLanding();
      }

      if (Player.PROPERTY_PAWNS.equalsIgnoreCase(attribute))
      {
         return ((Player) target).getPawns();
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

      if (Player.PROPERTY_COLOR.equalsIgnoreCase(attrName))
      {
         ((Player) target).setColor((String) value);
         return true;
      }

      if (Player.PROPERTY_ENUMCOLOR.equalsIgnoreCase(attrName))
      {
         ((Player) target).setEnumColor((org.sdmlib.test.examples.ludo.LudoModel.LudoColor) value);
         return true;
      }

      if (Player.PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         ((Player) target).setName((String) value);
         return true;
      }

      if (Player.PROPERTY_X.equalsIgnoreCase(attrName))
      {
         ((Player) target).setX(Integer.parseInt(value.toString()));
         return true;
      }

      if (Player.PROPERTY_Y.equalsIgnoreCase(attrName))
      {
         ((Player) target).setY(Integer.parseInt(value.toString()));
         return true;
      }

      if (Player.PROPERTY_GAME.equalsIgnoreCase(attrName))
      {
         ((Player) target).setGame((Ludo) value);
         return true;
      }

      if (Player.PROPERTY_NEXT.equalsIgnoreCase(attrName))
      {
         ((Player) target).setNext((Player) value);
         return true;
      }

      if (Player.PROPERTY_PREV.equalsIgnoreCase(attrName))
      {
         ((Player) target).setPrev((Player) value);
         return true;
      }

      if (Player.PROPERTY_DICE.equalsIgnoreCase(attrName))
      {
         ((Player) target).setDice((Dice) value);
         return true;
      }

      if (Player.PROPERTY_START.equalsIgnoreCase(attrName))
      {
         ((Player) target).setStart((Field) value);
         return true;
      }

      if (Player.PROPERTY_BASE.equalsIgnoreCase(attrName))
      {
         ((Player) target).setBase((Field) value);
         return true;
      }

      if (Player.PROPERTY_LANDING.equalsIgnoreCase(attrName))
      {
         ((Player) target).setLanding((Field) value);
         return true;
      }

      if (Player.PROPERTY_PAWNS.equalsIgnoreCase(attrName))
      {
         ((Player) target).addToPawns((Pawn) value);
         return true;
      }
      
      if ((Player.PROPERTY_PAWNS + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Player) target).removeFromPawns((Pawn) value);
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
      ((Player) entity).removeYou();
   }
}
