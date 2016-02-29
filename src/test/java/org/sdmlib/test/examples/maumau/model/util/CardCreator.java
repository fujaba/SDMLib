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
import org.sdmlib.test.examples.maumau.model.Card;
import org.sdmlib.test.examples.maumau.model.Suit;
import org.sdmlib.test.examples.maumau.model.Value;
import org.sdmlib.test.examples.maumau.model.MauMau;
import org.sdmlib.test.examples.maumau.model.Holder;

public class CardCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      Card.PROPERTY_SUIT,
      Card.PROPERTY_VALUE,
      Card.PROPERTY_GAME,
      Card.PROPERTY_HOLDER,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new Card();
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

      if (Card.PROPERTY_SUIT.equalsIgnoreCase(attribute))
      {
         return ((Card) target).getSuit();
      }

      if (Card.PROPERTY_VALUE.equalsIgnoreCase(attribute))
      {
         return ((Card) target).getValue();
      }

      if (Card.PROPERTY_GAME.equalsIgnoreCase(attribute))
      {
         return ((Card) target).getGame();
      }

      if (Card.PROPERTY_HOLDER.equalsIgnoreCase(attribute))
      {
         return ((Card) target).getHolder();
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

      if (Card.PROPERTY_SUIT.equalsIgnoreCase(attrName))
      {
         ((Card) target).withSuit(Suit.valueOf((String) value));
         return true;
      }

      if (Card.PROPERTY_VALUE.equalsIgnoreCase(attrName))
      {
         ((Card) target).withValue(Value.valueOf((String) value));
         return true;
      }

      if (Card.PROPERTY_GAME.equalsIgnoreCase(attrName))
      {
         ((Card) target).setGame((MauMau) value);
         return true;
      }

      if (Card.PROPERTY_HOLDER.equalsIgnoreCase(attrName))
      {
         ((Card) target).setHolder((Holder) value);
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
      ((Card) entity).removeYou();
   }
}
