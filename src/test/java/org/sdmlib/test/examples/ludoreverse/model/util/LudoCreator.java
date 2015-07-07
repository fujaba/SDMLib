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
   
package org.sdmlib.test.examples.ludoreverse.model.util;

import org.sdmlib.serialization.EntityFactory;
import org.sdmlib.test.examples.ludoreverse.model.Ludo;
import org.sdmlib.test.examples.ludoreverse.model.Player;

import de.uniks.networkparser.json.JsonIdMap;

public class LudoCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      Ludo.PROPERTY_STYLE,
      Ludo.PROPERTY_AGE,
      Ludo.PROPERTY_GAME,
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

      if (Ludo.PROPERTY_STYLE.equalsIgnoreCase(attribute))
      {
         return ((Ludo) target).getStyle();
      }

      if (Ludo.PROPERTY_AGE.equalsIgnoreCase(attribute))
      {
         return ((Ludo) target).getAge();
      }

      if (Ludo.PROPERTY_GAME.equalsIgnoreCase(attribute))
      {
         return ((Ludo) target).getGame();
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

      if (Ludo.PROPERTY_STYLE.equalsIgnoreCase(attrName))
      {
         ((Ludo) target).setStyle((String) value);
         return true;
      }

      if (Ludo.PROPERTY_AGE.equalsIgnoreCase(attrName))
      {
         ((Ludo) target).setAge(Integer.parseInt(value.toString()));
         return true;
      }

      if (Ludo.PROPERTY_GAME.equalsIgnoreCase(attrName))
      {
         ((Ludo) target).setGame((Player) value);
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
