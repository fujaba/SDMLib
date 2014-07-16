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
   
package org.sdmlib.examples.mancala.model.util;

import org.sdmlib.serialization.EntityFactory;
import de.uniks.networkparser.json.JsonIdMap;
import org.sdmlib.examples.mancala.model.Kalah;
import org.sdmlib.examples.mancala.model.Pit;
import org.sdmlib.examples.mancala.model.Mancala;
import org.sdmlib.examples.mancala.model.Player;

public class KalahCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      Pit.PROPERTY_NR,
      Pit.PROPERTY_GAME,
      Pit.PROPERTY_PLAYER,
      Pit.PROPERTY_NEXT,
      Pit.PROPERTY_PREVIOUS,
      Pit.PROPERTY_COUNTERPART,
      Kalah.PROPERTY_KALAHPLAYER,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new Kalah();
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

      if (Pit.PROPERTY_NR.equalsIgnoreCase(attribute))
      {
         return ((Pit) target).getNr();
      }

      if (Kalah.PROPERTY_GAME.equalsIgnoreCase(attribute))
      {
         return ((Kalah) target).getGame();
      }

      if (Kalah.PROPERTY_PLAYER.equalsIgnoreCase(attribute))
      {
         return ((Kalah) target).getPlayer();
      }

      if (Kalah.PROPERTY_NEXT.equalsIgnoreCase(attribute))
      {
         return ((Kalah) target).getNext();
      }

      if (Kalah.PROPERTY_PREVIOUS.equalsIgnoreCase(attribute))
      {
         return ((Kalah) target).getPrevious();
      }

      if (Kalah.PROPERTY_COUNTERPART.equalsIgnoreCase(attribute))
      {
         return ((Kalah) target).getCounterpart();
      }

      if (Kalah.PROPERTY_KALAHPLAYER.equalsIgnoreCase(attribute))
      {
         return ((Kalah) target).getKalahPlayer();
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

      if (Pit.PROPERTY_NR.equalsIgnoreCase(attrName))
      {
         ((Pit) target).withNr(Integer.parseInt(value.toString()));
         return true;
      }

      if (Kalah.PROPERTY_GAME.equalsIgnoreCase(attrName))
      {
         ((Kalah) target).setGame((Mancala) value);
         return true;
      }

      if (Kalah.PROPERTY_PLAYER.equalsIgnoreCase(attrName))
      {
         ((Kalah) target).setPlayer((Player) value);
         return true;
      }

      if (Kalah.PROPERTY_NEXT.equalsIgnoreCase(attrName))
      {
         ((Kalah) target).setNext((Pit) value);
         return true;
      }

      if (Kalah.PROPERTY_PREVIOUS.equalsIgnoreCase(attrName))
      {
         ((Kalah) target).setPrevious((Pit) value);
         return true;
      }

      if (Kalah.PROPERTY_COUNTERPART.equalsIgnoreCase(attrName))
      {
         ((Kalah) target).setCounterpart((Pit) value);
         return true;
      }

      if (Kalah.PROPERTY_KALAHPLAYER.equalsIgnoreCase(attrName))
      {
         ((Kalah) target).setKalahPlayer((Player) value);
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
      ((Kalah) entity).removeYou();
   }
}
