/*
   Copyright (c) 2015 Stefan
   
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
   
package org.sdmlib.test.examples.maumau.model.util;

import org.sdmlib.serialization.EntityFactory;
import de.uniks.networkparser.IdMap;
import org.sdmlib.test.examples.maumau.model.Player;
import org.sdmlib.replication.Lane;
import org.sdmlib.test.examples.maumau.model.Card;
import org.sdmlib.test.examples.maumau.model.Holder;
import org.sdmlib.test.examples.maumau.model.MauMau;
import org.sdmlib.test.examples.maumau.model.Duty;

public class PlayerCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      Player.PROPERTY_NAME,
      Player.PROPERTY_LANE,
      Holder.PROPERTY_CARDS,
      Holder.PROPERTY_DECKOWNER,
      Holder.PROPERTY_STACKOWNER,
      Player.PROPERTY_GAME,
      Player.PROPERTY_WONGAME,
      Player.PROPERTY_LOSTGAME,
      Player.PROPERTY_NEXT,
      Player.PROPERTY_PREV,
      Player.PROPERTY_DUTY,
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

      if (Player.PROPERTY_LANE.equalsIgnoreCase(attribute))
      {
         return ((Player) target).getLane();
      }

      if (Player.PROPERTY_CARDS.equalsIgnoreCase(attribute))
      {
         return ((Player) target).getCards();
      }

      if (Player.PROPERTY_DECKOWNER.equalsIgnoreCase(attribute))
      {
         return ((Player) target).getDeckOwner();
      }

      if (Player.PROPERTY_STACKOWNER.equalsIgnoreCase(attribute))
      {
         return ((Player) target).getStackOwner();
      }

      if (Player.PROPERTY_GAME.equalsIgnoreCase(attribute))
      {
         return ((Player) target).getGame();
      }

      if (Player.PROPERTY_WONGAME.equalsIgnoreCase(attribute))
      {
         return ((Player) target).getWonGame();
      }

      if (Player.PROPERTY_LOSTGAME.equalsIgnoreCase(attribute))
      {
         return ((Player) target).getLostGame();
      }

      if (Player.PROPERTY_NEXT.equalsIgnoreCase(attribute))
      {
         return ((Player) target).getNext();
      }

      if (Player.PROPERTY_PREV.equalsIgnoreCase(attribute))
      {
         return ((Player) target).getPrev();
      }

      if (Player.PROPERTY_DUTY.equalsIgnoreCase(attribute))
      {
         return ((Player) target).getDuty();
      }
      
      return null;
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (IdMap.REMOVE.equals(type) && value != null)
      {
         attrName = attrName + type;
      }

      if (Player.PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         ((Player) target).withName((String) value);
         return true;
      }

      if (Player.PROPERTY_LANE.equalsIgnoreCase(attrName))
      {
         ((Player) target).withLane((Lane) value);
         return true;
      }

      if (Player.PROPERTY_CARDS.equalsIgnoreCase(attrName))
      {
         ((Player) target).withCards((Card) value);
         return true;
      }
      
      if ((Player.PROPERTY_CARDS + IdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Player) target).withoutCards((Card) value);
         return true;
      }

      if (Player.PROPERTY_DECKOWNER.equalsIgnoreCase(attrName))
      {
         ((Player) target).setDeckOwner((MauMau) value);
         return true;
      }

      if (Player.PROPERTY_STACKOWNER.equalsIgnoreCase(attrName))
      {
         ((Player) target).setStackOwner((MauMau) value);
         return true;
      }

      if (Player.PROPERTY_GAME.equalsIgnoreCase(attrName))
      {
         ((Player) target).setGame((MauMau) value);
         return true;
      }

      if (Player.PROPERTY_WONGAME.equalsIgnoreCase(attrName))
      {
         ((Player) target).setWonGame((MauMau) value);
         return true;
      }

      if (Player.PROPERTY_LOSTGAME.equalsIgnoreCase(attrName))
      {
         ((Player) target).setLostGame((MauMau) value);
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

      if (Player.PROPERTY_DUTY.equalsIgnoreCase(attrName))
      {
         ((Player) target).withDuty((Duty) value);
         return true;
      }
      
      if ((Player.PROPERTY_DUTY + IdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Player) target).withoutDuty((Duty) value);
         return true;
      }
      
      return false;
   }
   public static IdMap createIdMap(String sessionID)
   {
      return org.sdmlib.test.examples.maumau.model.util.CreatorCreator.createIdMap(sessionID);
   }
   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((Player) entity).removeYou();
   }
}
