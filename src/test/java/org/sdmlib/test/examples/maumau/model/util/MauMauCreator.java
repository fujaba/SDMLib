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
import org.sdmlib.test.examples.maumau.model.MauMau;
import org.sdmlib.test.examples.maumau.model.Player;
import org.sdmlib.test.examples.maumau.model.Suit;
import org.sdmlib.test.examples.maumau.model.Card;
import org.sdmlib.test.examples.maumau.model.Holder;
import org.sdmlib.test.examples.maumau.model.DrawingStack;
import org.sdmlib.test.examples.maumau.model.OpenStack;

public class MauMauCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      MauMau.PROPERTY_CURRENTPLAYER,
      MauMau.PROPERTY_CURRENTSUIT,
      MauMau.PROPERTY_CARDS,
      MauMau.PROPERTY_DECK,
      MauMau.PROPERTY_STACK,
      MauMau.PROPERTY_PLAYERS,
      MauMau.PROPERTY_WINNER,
      MauMau.PROPERTY_LOSERS,
      MauMau.PROPERTY_DRAWINGSTACK,
      MauMau.PROPERTY_OPENSTACK,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new MauMau();
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

      if (MauMau.PROPERTY_CURRENTPLAYER.equalsIgnoreCase(attribute))
      {
         return ((MauMau) target).getCurrentPlayer();
      }

      if (MauMau.PROPERTY_CURRENTSUIT.equalsIgnoreCase(attribute))
      {
         return ((MauMau) target).getCurrentSuit();
      }

      if (MauMau.PROPERTY_CARDS.equalsIgnoreCase(attribute))
      {
         return ((MauMau) target).getCards();
      }

      if (MauMau.PROPERTY_DECK.equalsIgnoreCase(attribute))
      {
         return ((MauMau) target).getDeck();
      }

      if (MauMau.PROPERTY_STACK.equalsIgnoreCase(attribute))
      {
         return ((MauMau) target).getStack();
      }

      if (MauMau.PROPERTY_PLAYERS.equalsIgnoreCase(attribute))
      {
         return ((MauMau) target).getPlayers();
      }

      if (MauMau.PROPERTY_WINNER.equalsIgnoreCase(attribute))
      {
         return ((MauMau) target).getWinner();
      }

      if (MauMau.PROPERTY_LOSERS.equalsIgnoreCase(attribute))
      {
         return ((MauMau) target).getLosers();
      }

      if (MauMau.PROPERTY_DRAWINGSTACK.equalsIgnoreCase(attribute))
      {
         return ((MauMau) target).getDrawingStack();
      }

      if (MauMau.PROPERTY_OPENSTACK.equalsIgnoreCase(attribute))
      {
         return ((MauMau) target).getOpenStack();
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

      if (MauMau.PROPERTY_CURRENTPLAYER.equalsIgnoreCase(attrName))
      {
         ((MauMau) target).withCurrentPlayer((Player) value);
         return true;
      }

      if (MauMau.PROPERTY_CURRENTSUIT.equalsIgnoreCase(attrName))
      {
         ((MauMau) target).withCurrentSuit(Suit.valueOf((String) value));
         return true;
      }

      if (MauMau.PROPERTY_CARDS.equalsIgnoreCase(attrName))
      {
         ((MauMau) target).withCards((Card) value);
         return true;
      }
      
      if ((MauMau.PROPERTY_CARDS + IdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((MauMau) target).withoutCards((Card) value);
         return true;
      }

      if (MauMau.PROPERTY_DECK.equalsIgnoreCase(attrName))
      {
         ((MauMau) target).setDeck((Holder) value);
         return true;
      }

      if (MauMau.PROPERTY_STACK.equalsIgnoreCase(attrName))
      {
         ((MauMau) target).setStack((Holder) value);
         return true;
      }

      if (MauMau.PROPERTY_PLAYERS.equalsIgnoreCase(attrName))
      {
         ((MauMau) target).withPlayers((Player) value);
         return true;
      }
      
      if ((MauMau.PROPERTY_PLAYERS + IdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((MauMau) target).withoutPlayers((Player) value);
         return true;
      }

      if (MauMau.PROPERTY_WINNER.equalsIgnoreCase(attrName))
      {
         ((MauMau) target).setWinner((Player) value);
         return true;
      }

      if (MauMau.PROPERTY_LOSERS.equalsIgnoreCase(attrName))
      {
         ((MauMau) target).withLosers((Player) value);
         return true;
      }
      
      if ((MauMau.PROPERTY_LOSERS + IdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((MauMau) target).withoutLosers((Player) value);
         return true;
      }

      if (MauMau.PROPERTY_DRAWINGSTACK.equalsIgnoreCase(attrName))
      {
         ((MauMau) target).setDrawingStack((DrawingStack) value);
         return true;
      }

      if (MauMau.PROPERTY_OPENSTACK.equalsIgnoreCase(attrName))
      {
         ((MauMau) target).setOpenStack((OpenStack) value);
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
      ((MauMau) entity).removeYou();
   }
}
