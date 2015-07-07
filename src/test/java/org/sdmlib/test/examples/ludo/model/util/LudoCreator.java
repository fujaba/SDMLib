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
import org.sdmlib.test.examples.ludo.model.Player;

import de.uniks.networkparser.json.JsonIdMap;

public class LudoCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      Ludo.PROPERTY_DATE,
      Ludo.PROPERTY_PLAYERS,
      Ludo.PROPERTY_DICE,
      Ludo.PROPERTY_FIELDS,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new Ludo();
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

      if (Ludo.PROPERTY_DATE.equalsIgnoreCase(attribute))
      {
         return ((Ludo) target).getDate();
      }

      if (Ludo.PROPERTY_PLAYERS.equalsIgnoreCase(attribute))
      {
         return ((Ludo) target).getPlayers();
      }

      if (Ludo.PROPERTY_DICE.equalsIgnoreCase(attribute))
      {
         return ((Ludo) target).getDice();
      }

      if (Ludo.PROPERTY_FIELDS.equalsIgnoreCase(attribute))
      {
         return ((Ludo) target).getFields();
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

      if (Ludo.PROPERTY_DATE.equalsIgnoreCase(attrName))
      {
         ((Ludo) target).setDate((java.util.Date) value);
         return true;
      }

      if (Ludo.PROPERTY_PLAYERS.equalsIgnoreCase(attrName))
      {
         ((Ludo) target).addToPlayers((Player) value);
         return true;
      }
      
      if ((Ludo.PROPERTY_PLAYERS + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Ludo) target).removeFromPlayers((Player) value);
         return true;
      }

      if (Ludo.PROPERTY_DICE.equalsIgnoreCase(attrName))
      {
         ((Ludo) target).setDice((Dice) value);
         return true;
      }

      if (Ludo.PROPERTY_FIELDS.equalsIgnoreCase(attrName))
      {
         ((Ludo) target).addToFields((Field) value);
         return true;
      }
      
      if ((Ludo.PROPERTY_FIELDS + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Ludo) target).removeFromFields((Field) value);
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
      ((Ludo) entity).removeYou();
   }
}
