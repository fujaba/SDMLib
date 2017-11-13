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
import org.sdmlib.test.examples.reachabilitygraphs.sokoban.util.SokobanSet;
import org.sdmlib.test.examples.reachabilitygraphs.sokoban.Sokoban;
import org.sdmlib.test.examples.reachabilitygraphs.sokoban.util.TileSet;
import org.sdmlib.test.examples.reachabilitygraphs.sokoban.Tile;
/**
 * 
 * @see <a href='../../../../../../../../../src/test/java/org/sdmlib/test/examples/reachabilitygraphs/SokobanModel.java'>SokobanModel.java</a>
 */
public  class Maze implements SendableEntity
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
      withoutSokoban(this.getSokoban().toArray(new Sokoban[this.getSokoban().size()]));
      // for (Tile obj : new TileSet(this.getTiles())) { obj.removeYou(); }
      for (Tile obj : new TileSet(this.getTiles())) { this.withoutTiles(obj); }
      
      firePropertyChange("REMOVE_YOU", this, null);
   }


   /********************************************************************
    * <pre>
    *              one                       many
    * Maze ----------------------------------- Sokoban
    *              maze                   sokoban
    * </pre>
    */

   public static final String PROPERTY_SOKOBAN = "sokoban";

   private SokobanSet sokoban = null;

   public SokobanSet getSokoban()
   {
      if (this.sokoban == null)
      {
         return SokobanSet.EMPTY_SET;
      }

      return this.sokoban;
   }

   public Maze withSokoban(Sokoban... value)
   {
      if(value==null){
         return this;
      }
      for (Sokoban item : value)
      {
         if (item != null)
         {
            if (this.sokoban == null)
            {
               this.sokoban = new SokobanSet();
            }

            boolean changed = this.sokoban.add (item);

            if (changed)
            {
               item.withMaze(this);
               firePropertyChange(PROPERTY_SOKOBAN, null, item);
            }
         }
      }
      return this;
   } 

   public Maze withoutSokoban(Sokoban... value)
   {
      for (Sokoban item : value)
      {
         if ((this.sokoban != null) && (item != null))
         {
            if (this.sokoban.remove(item))
            {
               item.setMaze(null);
               firePropertyChange(PROPERTY_SOKOBAN, item, null);
            }
         }
      }
      return this;
   }

   public Sokoban createSokoban()
   {
      Sokoban value = new Sokoban();
      withSokoban(value);
      return value;
   } 


   /********************************************************************
    * <pre>
    *              many                       many
    * Maze ----------------------------------- Tile
    *              maze                   tiles
    * </pre>
    */

   public static final String PROPERTY_TILES = "tiles";

   private TileSet tiles = null;

   public TileSet getTiles()
   {
      if (this.tiles == null)
      {
         return TileSet.EMPTY_SET;
      }

      return this.tiles;
   }

   public Maze withTiles(Tile... value)
   {
      if(value==null){
         return this;
      }
      for (Tile item : value)
      {
         if (item != null)
         {
            if (this.tiles == null)
            {
               this.tiles = new TileSet();
            }

            boolean changed = this.tiles.add (item);

            if (changed)
            {
               item.withMaze(this);
               firePropertyChange(PROPERTY_TILES, null, item);
            }
         }
      }
      return this;
   } 

   public Maze withoutTiles(Tile... value)
   {
      for (Tile item : value)
      {
         if ((this.tiles != null) && (item != null))
         {
            if (this.tiles.remove(item))
            {
               item.withoutMaze(this);
               firePropertyChange(PROPERTY_TILES, item, null);
            }
         }
      }
      return this;
   }

   public Tile createTiles()
   {
      Tile value = new Tile();
      withTiles(value);
      return value;
   }

   public Tile getTile(int x, int y)
   {
      Tile result = this.getTiles().createXCondition(x).createYCondition(y).first();
      return result;
   } 


   //==========================================================================

   public static final String PROPERTY_HEIGHT = "height";

   private int height;

   public int getHeight()
   {
      return this.height;
   }

   public void setHeight(int value)
   {
      if (this.height != value) {

         int oldValue = this.height;
         this.height = value;
         this.firePropertyChange(PROPERTY_HEIGHT, oldValue, value);
      }
   }

   public Maze withHeight(int value)
   {
      setHeight(value);
      return this;
   } 


   @Override
   public String toString()
   {
      StringBuilder result = new StringBuilder();

      result.append(" ").append(this.getHeight());
      result.append(" ").append(this.getWidth());

      mazeString(result);

      return result.substring(1);
   }
   
   public String toString(Sokoban currentSokoban)
   {
      StringBuilder result = new StringBuilder();

      result.append(" ").append(this.getHeight());
      result.append(" ").append(this.getWidth());

      mazeString(result, currentSokoban);

      return result.substring(1);
   } 


   private void mazeString(StringBuilder result, Sokoban currentSokoban)
   {
      result.append('\n');
      for (int y = 0; y < height; y++)
      {
         for (int x = 0; x < width; x++)
         {
            Tile t = this.getTile(x, y);
            if (t == null)
            {
               result.append(' ');
            }
            else if (t.isWall())
            {
               result.append('w');
            }
            else if (currentSokoban.getBoxes().getTile().contains(t))
            {
               if (t.isGoal())
               {
                  result.append('B');                  
               }
               else
               {
                  result.append('b');
               }
            }
            else if (currentSokoban.getKarli().getTile() == t)
            {
               if (t.isGoal())
               {
                  result.append('K');                  
               }
               else
               {
                  result.append('k');
               }
            }
            else
            {
               if (t.isGoal())
               {
                  result.append('o');                  
               }
               else
               {
                  result.append('.');
               }
            }
         }

         result.append('\n');
      }
   }

   private void mazeString(StringBuilder result)
   {
      result.append('\n');
      for (int y = 0; y < height; y++)
      {
         for (int x = 0; x < width; x++)
         {
            Tile t = this.getTile(x, y);
            if (t == null)
            {
               result.append(' ');
            }
            else if (t.isWall())
            {
               result.append('w');
            }
            else if ( ! this.getSokoban().getBoxes().getTile().createXCondition(x).createYCondition(y).isEmpty())
            {
               if (t.isGoal())
               {
                  result.append('B');                  
               }
               else
               {
                  result.append('b');
               }
            }
            else if ( ! this.getSokoban().getKarli().getTile().createXCondition(x).createYCondition(y).isEmpty())
            {
               if (t.isGoal())
               {
                  result.append('K');                  
               }
               else
               {
                  result.append('k');
               }
            }
            else
            {
               if (t.isGoal())
               {
                  result.append('o');                  
               }
               else
               {
                  result.append('.');
               }
            }
         }

         result.append('\n');
      }
   }



   //==========================================================================

   public static final String PROPERTY_WIDTH = "width";

   private int width;

   public int getWidth()
   {
      return this.width;
   }

   public void setWidth(int value)
   {
      if (this.width != value) {

         int oldValue = this.width;
         this.width = value;
         this.firePropertyChange(PROPERTY_WIDTH, oldValue, value);
      }
   }

   public Maze withWidth(int value)
   {
      setWidth(value);
      return this;
   }

}
