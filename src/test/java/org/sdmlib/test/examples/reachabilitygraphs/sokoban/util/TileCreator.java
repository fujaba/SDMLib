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

public class TileCreator implements AggregatedEntityCreator
{
   public static final TileCreator it = new TileCreator();
   
   private final String[] properties = new String[]
   {
      Tile.PROPERTY_GOAL,
      Tile.PROPERTY_WALL,
      Tile.PROPERTY_X,
      Tile.PROPERTY_Y,
      Tile.PROPERTY_NEIGHBORS,
      Tile.PROPERTY_MAZE,
   };
   
   private final String[] upProperties = new String[]
   {
      Tile.PROPERTY_MAZE,
   };
   
   private final String[] downProperties = new String[]
   {
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
      return new Tile();
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

      if (Tile.PROPERTY_GOAL.equalsIgnoreCase(attribute))
      {
         return ((Tile) target).isGoal();
      }

      if (Tile.PROPERTY_WALL.equalsIgnoreCase(attribute))
      {
         return ((Tile) target).isWall();
      }

      if (Tile.PROPERTY_X.equalsIgnoreCase(attribute))
      {
         return ((Tile) target).getX();
      }

      if (Tile.PROPERTY_Y.equalsIgnoreCase(attribute))
      {
         return ((Tile) target).getY();
      }

      if (Tile.PROPERTY_NEIGHBORS.equalsIgnoreCase(attribute))
      {
         return ((Tile) target).getNeighbors();
      }

      if (Tile.PROPERTY_MAZE.equalsIgnoreCase(attribute))
      {
         return ((Tile) target).getMaze();
      }
      
      return null;
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (Tile.PROPERTY_Y.equalsIgnoreCase(attrName))
      {
         ((Tile) target).setY(Integer.parseInt(value.toString()));
         return true;
      }

      if (Tile.PROPERTY_X.equalsIgnoreCase(attrName))
      {
         ((Tile) target).setX(Integer.parseInt(value.toString()));
         return true;
      }

      if (Tile.PROPERTY_WALL.equalsIgnoreCase(attrName))
      {
         ((Tile) target).setWall(Boolean.valueOf(value.toString()));
         return true;
      }

      if (Tile.PROPERTY_GOAL.equalsIgnoreCase(attrName))
      {
         ((Tile) target).setGoal(Boolean.valueOf(value.toString()));
         return true;
      }

      if(SendableEntityCreator.REMOVE_YOU.equals(type)) {
           ((Tile)target).removeYou();
           return true;
      }
      if (SendableEntityCreator.REMOVE.equals(type) && value != null)
      {
         attrName = attrName + type;
      }

      if (Tile.PROPERTY_NEIGHBORS.equalsIgnoreCase(attrName))
      {
         ((Tile) target).withNeighbors((Tile) value);
         return true;
      }
      
      if ((Tile.PROPERTY_NEIGHBORS + SendableEntityCreator.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Tile) target).withoutNeighbors((Tile) value);
         return true;
      }

      if (Tile.PROPERTY_MAZE.equalsIgnoreCase(attrName))
      {
         ((Tile) target).withMaze((Maze) value);
         return true;
      }
      
      if ((Tile.PROPERTY_MAZE + SendableEntityCreator.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Tile) target).withoutMaze((Maze) value);
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
      ((Tile) entity).removeYou();
   }
}
