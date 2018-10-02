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

import org.sdmlib.test.examples.reachabilitygraphs.sokoban.Maze;
import org.sdmlib.test.examples.reachabilitygraphs.sokoban.Tile;

import de.uniks.networkparser.IdMap;
import de.uniks.networkparser.interfaces.AggregatedEntityCreator;
import de.uniks.networkparser.interfaces.SendableEntityCreator;

public class MazeCreator implements AggregatedEntityCreator
{
   public static final MazeCreator it = new MazeCreator();
   
   private final String[] properties = new String[]
   {
      Maze.PROPERTY_HEIGHT,
      Maze.PROPERTY_WIDTH,
      Maze.PROPERTY_TILES,
   };
   
   private final String[] upProperties = new String[]
   {
   };
   
   private final String[] downProperties = new String[]
   {
      Maze.PROPERTY_TILES,
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
      return new Maze();
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

      if (Maze.PROPERTY_HEIGHT.equalsIgnoreCase(attribute))
      {
         return ((Maze) target).getHeight();
      }

      if (Maze.PROPERTY_WIDTH.equalsIgnoreCase(attribute))
      {
         return ((Maze) target).getWidth();
      }

      if (Maze.PROPERTY_TILES.equalsIgnoreCase(attribute))
      {
         return ((Maze) target).getTiles();
      }
      
      return null;
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (Maze.PROPERTY_WIDTH.equalsIgnoreCase(attrName))
      {
         ((Maze) target).setWidth(Integer.parseInt(value.toString()));
         return true;
      }

      if (Maze.PROPERTY_HEIGHT.equalsIgnoreCase(attrName))
      {
         ((Maze) target).setHeight(Integer.parseInt(value.toString()));
         return true;
      }

      if(SendableEntityCreator.REMOVE_YOU.equals(type)) {
           ((Maze)target).removeYou();
           return true;
      }
      if (SendableEntityCreator.REMOVE.equals(type) && value != null)
      {
         attrName = attrName + type;
      }

      if (Maze.PROPERTY_TILES.equalsIgnoreCase(attrName))
      {
         ((Maze) target).withTiles((Tile) value);
         return true;
      }
      
      if ((Maze.PROPERTY_TILES + SendableEntityCreator.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Maze) target).withoutTiles((Tile) value);
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
      ((Maze) entity).removeYou();
   }
}
