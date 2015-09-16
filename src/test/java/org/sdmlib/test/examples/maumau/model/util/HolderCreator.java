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
import de.uniks.networkparser.json.JsonIdMap;
import org.sdmlib.test.examples.maumau.model.Holder;
import org.sdmlib.test.examples.maumau.model.Card;
import org.sdmlib.test.examples.maumau.model.MauMau;

public class HolderCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      Holder.PROPERTY_CARDS,
      Holder.PROPERTY_DECKOWNER,
      Holder.PROPERTY_STACKOWNER,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new Holder();
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

      if (Holder.PROPERTY_CARDS.equalsIgnoreCase(attribute))
      {
         return ((Holder) target).getCards();
      }

      if (Holder.PROPERTY_DECKOWNER.equalsIgnoreCase(attribute))
      {
         return ((Holder) target).getDeckOwner();
      }

      if (Holder.PROPERTY_STACKOWNER.equalsIgnoreCase(attribute))
      {
         return ((Holder) target).getStackOwner();
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

      if (Holder.PROPERTY_CARDS.equalsIgnoreCase(attrName))
      {
         ((Holder) target).withCards((Card) value);
         return true;
      }
      
      if ((Holder.PROPERTY_CARDS + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Holder) target).withoutCards((Card) value);
         return true;
      }

      if (Holder.PROPERTY_DECKOWNER.equalsIgnoreCase(attrName))
      {
         ((Holder) target).setDeckOwner((MauMau) value);
         return true;
      }

      if (Holder.PROPERTY_STACKOWNER.equalsIgnoreCase(attrName))
      {
         ((Holder) target).setStackOwner((MauMau) value);
         return true;
      }
      
      return false;
   }
   public static JsonIdMap createIdMap(String sessionID)
   {
      return org.sdmlib.test.examples.maumau.model.util.CreatorCreator.createIdMap(sessionID);
   }
   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((Holder) entity).removeYou();
   }
}
