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
import org.sdmlib.test.examples.reachabilitygraphs.sokoban.Tile;
   /**
    * 
    * @see <a href='../../../../../../../../../src/test/java/org/sdmlib/test/examples/reachabilitygraphs/SokobanModel.java'>SokobanModel.java</a>
 * @see org.sdmlib.test.examples.reachabilitygraphs.SokobanModel#SokobanModelGen
 */
   public  class AKarli implements SendableEntity
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
      withoutTiles(this.getTiles().toArray(new Tile[this.getTiles().size()]));
      firePropertyChange("REMOVE_YOU", this, null);
   }

   
   /********************************************************************
    * <pre>
    *              one                       many
    * AKarli ----------------------------------- Tile
    *              akarli                   tiles
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

     /**
    * 
    * @see <a href='../../../../../../../../../src/test/java/org/sdmlib/test/examples/reachabilitygraphs/SokobanLevels.java'>SokobanLevels.java</a>
 */
   public AKarli withTiles(Tile... value)
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
               firePropertyChange(PROPERTY_TILES, null, item);
            }
         }
      }
      return this;
   } 

   public AKarli withoutTiles(Tile... value)
   {
      for (Tile item : value)
      {
         if ((this.tiles != null) && (item != null))
         {
            if (this.tiles.remove(item))
            {
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

   public boolean clearPos()
   {
      this.getTiles().clear();
      
      return true;
   } 
}
