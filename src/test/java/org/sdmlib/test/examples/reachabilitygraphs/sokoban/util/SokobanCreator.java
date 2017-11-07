/*
   Copyright (c) 2017 zuendorf
   
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
   
package org.sdmlib.test.examples.reachabilitygraphs.sokoban.util;

import de.uniks.networkparser.interfaces.AggregatedEntityCreator;
import org.sdmlib.test.examples.reachabilitygraphs.sokoban.Sokoban;
import de.uniks.networkparser.list.ObjectSet;
import de.uniks.networkparser.interfaces.SendableEntityCreator;
import de.uniks.networkparser.IdMap;
import org.sdmlib.test.examples.reachabilitygraphs.sokoban.Maze;
import org.sdmlib.test.examples.reachabilitygraphs.sokoban.Box;
import org.sdmlib.test.examples.reachabilitygraphs.sokoban.Karli;

public class SokobanCreator implements AggregatedEntityCreator
{
   public static final SokobanCreator it = new SokobanCreator();
   
   private final String[] properties = new String[]
   {
      Sokoban.PROPERTY_MAZE,
      Sokoban.PROPERTY_BOXES,
      Sokoban.PROPERTY_KARLI,
   };
   
   private final String[] upProperties = new String[]
   {
   };
   
   private final String[] downProperties = new String[]
   {
      Sokoban.PROPERTY_MAZE,
      Sokoban.PROPERTY_BOXES,
      Sokoban.PROPERTY_KARLI,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public String[] getUpProperties()
   {
      return upProperties;
   }
   
   @Override
   public String[] getDownProperties()
   {
      return downProperties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new Sokoban();
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

      if (Sokoban.PROPERTY_MAZE.equalsIgnoreCase(attribute))
      {
         return ((Sokoban) target).getMaze();
      }

      if (Sokoban.PROPERTY_BOXES.equalsIgnoreCase(attribute))
      {
         return ((Sokoban) target).getBoxes();
      }

      if (Sokoban.PROPERTY_KARLI.equalsIgnoreCase(attribute))
      {
         return ((Sokoban) target).getKarli();
      }
      
      return null;
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if(SendableEntityCreator.REMOVE_YOU.equals(type)) {
           ((Sokoban)target).removeYou();
           return true;
      }
      if (SendableEntityCreator.REMOVE.equals(type) && value != null)
      {
         attrName = attrName + type;
      }

      if (Sokoban.PROPERTY_MAZE.equalsIgnoreCase(attrName))
      {
         ((Sokoban) target).setMaze((Maze) value);
         return true;
      }

      if (Sokoban.PROPERTY_BOXES.equalsIgnoreCase(attrName))
      {
         ((Sokoban) target).withBoxes((Box) value);
         return true;
      }
      
      if ((Sokoban.PROPERTY_BOXES + SendableEntityCreator.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Sokoban) target).withoutBoxes((Box) value);
         return true;
      }

      if (Sokoban.PROPERTY_KARLI.equalsIgnoreCase(attrName))
      {
         ((Sokoban) target).setKarli((Karli) value);
         return true;
      }
      
      return false;
   }
   public static IdMap createIdMap(String sessionID)
   {
      return org.sdmlib.test.examples.reachabilitygraphs.sokoban.util.CreatorCreator.createIdMap(sessionID);
   }
   
   //==========================================================================
      public void removeObject(Object entity)
   {
      ((Sokoban) entity).removeYou();
   }
}
