/*
   Copyright (c) 2015 zasch 
   
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
   
package org.sdmlib.examples.gofpattern.strategy.util;

import org.sdmlib.serialization.EntityFactory;
import de.uniks.networkparser.json.JsonIdMap;
import org.sdmlib.examples.gofpattern.strategy.BombermanPlayer;

public class BombermanPlayerCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      BombermanPlayer.PROPERTY_XPOSITION,
      BombermanPlayer.PROPERTY_YPOSITION,
      BombermanPlayer.PROPERTY_NUMBEROFBOMBS,
      BombermanPlayer.PROPERTY_LASTKEY,
      BombermanPlayer.PROPERTY_SHORTTEST,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new BombermanPlayer();
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

      if (BombermanPlayer.PROPERTY_XPOSITION.equalsIgnoreCase(attribute))
      {
         return ((BombermanPlayer) target).getXPosition();
      }

      if (BombermanPlayer.PROPERTY_YPOSITION.equalsIgnoreCase(attribute))
      {
         return ((BombermanPlayer) target).getYPosition();
      }

      if (BombermanPlayer.PROPERTY_NUMBEROFBOMBS.equalsIgnoreCase(attribute))
      {
         return ((BombermanPlayer) target).getNumberOfBombs();
      }

      if (BombermanPlayer.PROPERTY_LASTKEY.equalsIgnoreCase(attribute))
      {
         return ((BombermanPlayer) target).getLastKey();
      }

      if (BombermanPlayer.PROPERTY_SHORTTEST.equalsIgnoreCase(attribute))
      {
         return ((BombermanPlayer) target).getShortTest();
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

      if (BombermanPlayer.PROPERTY_XPOSITION.equalsIgnoreCase(attrName))
      {
         ((BombermanPlayer) target).withXPosition(Integer.parseInt(value.toString()));
         return true;
      }

      if (BombermanPlayer.PROPERTY_YPOSITION.equalsIgnoreCase(attrName))
      {
         ((BombermanPlayer) target).withYPosition(Integer.parseInt(value.toString()));
         return true;
      }

      if (BombermanPlayer.PROPERTY_NUMBEROFBOMBS.equalsIgnoreCase(attrName))
      {
         ((BombermanPlayer) target).withNumberOfBombs(Integer.parseInt(value.toString()));
         return true;
      }

      if (BombermanPlayer.PROPERTY_LASTKEY.equalsIgnoreCase(attrName))
      {
         ((BombermanPlayer) target).withLastKey((char) value);
         return true;
      }

      if (BombermanPlayer.PROPERTY_SHORTTEST.equalsIgnoreCase(attrName))
      {
         ((BombermanPlayer) target).withShortTest((short) value);
         return true;
      }
      
      return false;
   }
   public static JsonIdMap createIdMap(String sessionID)
   {
      return org.sdmlib.examples.gofpattern.strategy.util.CreatorCreator.createIdMap(sessionID);
   }
   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((BombermanPlayer) entity).removeYou();
   }
}
