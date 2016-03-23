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
import org.sdmlib.test.examples.maumau.model.OpenStack;
import org.sdmlib.test.examples.maumau.model.Card;
import org.sdmlib.test.examples.maumau.model.Holder;
import org.sdmlib.test.examples.maumau.model.MauMau;

public class OpenStackCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      Holder.PROPERTY_CARDS,
      Holder.PROPERTY_DECKOWNER,
      Holder.PROPERTY_STACKOWNER,
      OpenStack.PROPERTY_GAME,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new OpenStack();
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

      if (OpenStack.PROPERTY_CARDS.equalsIgnoreCase(attribute))
      {
         return ((OpenStack) target).getCards();
      }

      if (OpenStack.PROPERTY_DECKOWNER.equalsIgnoreCase(attribute))
      {
         return ((OpenStack) target).getDeckOwner();
      }

      if (OpenStack.PROPERTY_STACKOWNER.equalsIgnoreCase(attribute))
      {
         return ((OpenStack) target).getStackOwner();
      }

      if (OpenStack.PROPERTY_GAME.equalsIgnoreCase(attribute))
      {
         return ((OpenStack) target).getGame();
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

      if (OpenStack.PROPERTY_CARDS.equalsIgnoreCase(attrName))
      {
         ((OpenStack) target).withCards((Card) value);
         return true;
      }
      
      if ((OpenStack.PROPERTY_CARDS + IdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((OpenStack) target).withoutCards((Card) value);
         return true;
      }

      if (OpenStack.PROPERTY_DECKOWNER.equalsIgnoreCase(attrName))
      {
         ((OpenStack) target).setDeckOwner((MauMau) value);
         return true;
      }

      if (OpenStack.PROPERTY_STACKOWNER.equalsIgnoreCase(attrName))
      {
         ((OpenStack) target).setStackOwner((MauMau) value);
         return true;
      }

      if (OpenStack.PROPERTY_GAME.equalsIgnoreCase(attrName))
      {
         ((OpenStack) target).setGame((MauMau) value);
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
      ((OpenStack) entity).removeYou();
   }
}
