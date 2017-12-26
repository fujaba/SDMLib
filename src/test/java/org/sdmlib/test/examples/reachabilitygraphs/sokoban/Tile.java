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
   
package org.sdmlib.test.examples.reachabilitygraphs.sokoban;

import de.uniks.networkparser.interfaces.SendableEntity;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;
import org.sdmlib.test.examples.reachabilitygraphs.sokoban.util.TileSet;
import org.sdmlib.test.examples.reachabilitygraphs.sokoban.util.MazeSet;
import org.sdmlib.test.examples.reachabilitygraphs.sokoban.Maze;
   /**
    * 
    * @see <a href='../../../../../../../../../src/test/java/org/sdmlib/test/examples/reachabilitygraphs/SokobanModel.java'>SokobanModel.java</a>
 */
   public  class Tile implements SendableEntity
{

   
   //==========================================================================
   
   protected PropertyChangeSupport listeners = null;
   
   public boolean firePropertyChange(String propertyName, Object oldValue, Object newValue)
   {
      if (listeners != null) {
   		listeners.firePropertyChange(propertyName, oldValue, newValue);
   		return true;
   	}
   	return false;
   }
   
   public boolean addPropertyChangeListener(PropertyChangeListener listener) 
   {
   	if (listeners == null) {
   		listeners = new PropertyChangeSupport(this);
   	}
   	listeners.addPropertyChangeListener(listener);
   	return true;
   }
   
   public boolean addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
   	if (listeners == null) {
   		listeners = new PropertyChangeSupport(this);
   	}
   	listeners.addPropertyChangeListener(propertyName, listener);
   	return true;
   }
   
   public boolean removePropertyChangeListener(PropertyChangeListener listener) {
   	if (listeners == null) {
   		listeners.removePropertyChangeListener(listener);
   	}
   	listeners.removePropertyChangeListener(listener);
   	return true;
   }

   public boolean removePropertyChangeListener(String propertyName,PropertyChangeListener listener) {
   	if (listeners != null) {
   		listeners.removePropertyChangeListener(propertyName, listener);
   	}
   	return true;
   }

   
   //==========================================================================
   
   
   public void removeYou()
   {
      withoutNeighbors(this.getNeighbors().toArray(new Tile[this.getNeighbors().size()]));
      withoutMaze(this.getMaze().toArray(new Maze[this.getMaze().size()]));
      firePropertyChange("REMOVE_YOU", this, null);
   }

   
   //==========================================================================
   
   public static final String PROPERTY_GOAL = "goal";
   
   private boolean goal;

   public boolean isGoal()
   {
      return this.goal;
   }
   
   public void setGoal(boolean value)
   {
      if (this.goal != value) {
      
         boolean oldValue = this.goal;
         this.goal = value;
         this.firePropertyChange(PROPERTY_GOAL, oldValue, value);
      }
   }
   
   public Tile withGoal(boolean value)
   {
      setGoal(value);
      return this;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_WALL = "wall";
   
   private boolean wall;

   public boolean isWall()
   {
      return this.wall;
   }
   
   public void setWall(boolean value)
   {
      if (this.wall != value) {
      
         boolean oldValue = this.wall;
         this.wall = value;
         this.firePropertyChange(PROPERTY_WALL, oldValue, value);
      }
   }
   
   public Tile withWall(boolean value)
   {
      setWall(value);
      return this;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_X = "x";
   
   private int x;

   public int getX()
   {
      return this.x;
   }
   
   public void setX(int value)
   {
      if (this.x != value) {
      
         int oldValue = this.x;
         this.x = value;
         this.firePropertyChange(PROPERTY_X, oldValue, value);
      }
   }
   
   public Tile withX(int value)
   {
      setX(value);
      return this;
   } 


   @Override
   public String toString()
   {
      StringBuilder result = new StringBuilder();
      
      result.append(" ").append(this.getX());
      result.append(" ").append(this.getY());
      return result.substring(1);
   }


   
   //==========================================================================
   
   public static final String PROPERTY_Y = "y";
   
   private int y;

   public int getY()
   {
      return this.y;
   }
   
   public void setY(int value)
   {
      if (this.y != value) {
      
         int oldValue = this.y;
         this.y = value;
         this.firePropertyChange(PROPERTY_Y, oldValue, value);
      }
   }
   
   public Tile withY(int value)
   {
      setY(value);
      return this;
   } 

   
   /********************************************************************
    * <pre>
    *              many                       many
    * Tile ----------------------------------- Tile
    *              neighbors                   neighbors
    * </pre>
    */
   
   public static final String PROPERTY_NEIGHBORS = "neighbors";

   private TileSet neighbors = null;
   
   public TileSet getNeighbors()
   {
      if (this.neighbors == null)
      {
         return TileSet.EMPTY_SET;
      }
   
      return this.neighbors;
   }
   public TileSet getNeighborsTransitive()
   {
      TileSet result = new TileSet().with(this);
      return result.getNeighborsTransitive();
   }


   public Tile withNeighbors(Tile... value)
   {
      if(value==null){
         return this;
      }
      for (Tile item : value)
      {
         if (item != null)
         {
            if (this.neighbors == null)
            {
               this.neighbors = new TileSet();
            }
            
            boolean changed = this.neighbors.add (item);

            if (changed)
            {
               item.withNeighbors(this);
               firePropertyChange(PROPERTY_NEIGHBORS, null, item);
            }
         }
      }
      return this;
   } 

   public Tile withoutNeighbors(Tile... value)
   {
      for (Tile item : value)
      {
         if ((this.neighbors != null) && (item != null))
         {
            if (this.neighbors.remove(item))
            {
               item.withoutNeighbors(this);
               firePropertyChange(PROPERTY_NEIGHBORS, item, null);
            }
         }
      }
      return this;
   }

   public Tile createNeighbors()
   {
      Tile value = new Tile();
      withNeighbors(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              many                       many
    * Tile ----------------------------------- Maze
    *              tiles                   maze
    * </pre>
    */
   
   public static final String PROPERTY_MAZE = "maze";

   private MazeSet maze = null;
   
   public MazeSet getMaze()
   {
      if (this.maze == null)
      {
         return MazeSet.EMPTY_SET;
      }
   
      return this.maze;
   }

   public Tile withMaze(Maze... value)
   {
      if(value==null){
         return this;
      }
      for (Maze item : value)
      {
         if (item != null)
         {
            if (this.maze == null)
            {
               this.maze = new MazeSet();
            }
            
            boolean changed = this.maze.add (item);

            if (changed)
            {
               item.withTiles(this);
               firePropertyChange(PROPERTY_MAZE, null, item);
            }
         }
      }
      return this;
   } 

   public Tile withoutMaze(Maze... value)
   {
      for (Maze item : value)
      {
         if ((this.maze != null) && (item != null))
         {
            if (this.maze.remove(item))
            {
               item.withoutTiles(this);
               firePropertyChange(PROPERTY_MAZE, item, null);
            }
         }
      }
      return this;
   }

   public Maze createMaze()
   {
      Maze value = new Maze();
      withMaze(value);
      return value;
   } 
}
