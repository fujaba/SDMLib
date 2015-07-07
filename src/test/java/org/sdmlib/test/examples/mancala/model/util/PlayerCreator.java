/*
   Copyright (c) 2014 NeTH 
   
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
   
package org.sdmlib.test.examples.mancala.model.util;

import org.sdmlib.serialization.EntityFactory;
import org.sdmlib.test.examples.mancala.model.Kalah;
import org.sdmlib.test.examples.mancala.model.Mancala;
import org.sdmlib.test.examples.mancala.model.Pit;
import org.sdmlib.test.examples.mancala.model.Player;
import org.sdmlib.test.examples.mancala.model.PlayerState;
import org.sdmlib.test.examples.mancala.referencemodel.Color;

import de.uniks.networkparser.json.JsonIdMap;

public class PlayerCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      Player.PROPERTY_NAME,
      Player.PROPERTY_STATE,
      Player.PROPERTY_COLOR,
      Player.PROPERTY_ACTIVEGAME,
      Player.PROPERTY_GAME,
      Player.PROPERTY_PITS,
      Player.PROPERTY_KALAH,
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

      if (Player.PROPERTY_NAME.equalsIgnoreCase(attribute))
      {
         return ((Player) target).getName();
      }

      if (Player.PROPERTY_STATE.equalsIgnoreCase(attribute))
      {
         return ((Player) target).getState();
      }

      if (Player.PROPERTY_COLOR.equalsIgnoreCase(attribute))
      {
         return ((Player) target).getColor();
      }

      if (Player.PROPERTY_ACTIVEGAME.equalsIgnoreCase(attribute))
      {
         return ((Player) target).getActiveGame();
      }

      if (Player.PROPERTY_GAME.equalsIgnoreCase(attribute))
      {
         return ((Player) target).getGame();
      }

      if (Player.PROPERTY_PITS.equalsIgnoreCase(attribute))
      {
         return ((Player) target).getPits();
      }

      if (Player.PROPERTY_KALAH.equalsIgnoreCase(attribute))
      {
         return ((Player) target).getKalah();
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

      if (Player.PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         ((Player) target).withName((String) value);
         return true;
      }

      if (Player.PROPERTY_STATE.equalsIgnoreCase(attrName))
      {
         ((Player) target).withState(PlayerState.valueOf((String) value));
         return true;
      }

      if (Player.PROPERTY_COLOR.equalsIgnoreCase(attrName))
      {
         ((Player) target).withColor((Color) value);
         return true;
      }

      if (Player.PROPERTY_ACTIVEGAME.equalsIgnoreCase(attrName))
      {
         ((Player) target).setActiveGame((Mancala) value);
         return true;
      }

      if (Player.PROPERTY_GAME.equalsIgnoreCase(attrName))
      {
         ((Player) target).setGame((Mancala) value);
         return true;
      }

      if (Player.PROPERTY_PITS.equalsIgnoreCase(attrName))
      {
         ((Player) target).withPits((Pit) value);
         return true;
      }
      
      if ((Player.PROPERTY_PITS + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Player) target).withoutPits((Pit) value);
         return true;
      }

      if (Player.PROPERTY_KALAH.equalsIgnoreCase(attrName))
      {
         ((Player) target).setKalah((Kalah) value);
         return true;
      }
      
      return false;
   }
   public static JsonIdMap createIdMap(String sessionID)
   {
      return org.sdmlib.test.examples.mancala.model.util.CreatorCreator.createIdMap(sessionID);
   }
   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((Player) entity).removeYou();
   }
}
