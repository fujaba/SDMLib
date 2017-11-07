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
import org.sdmlib.test.examples.reachabilitygraphs.sokoban.Tile;
import org.sdmlib.test.examples.reachabilitygraphs.sokoban.util.SokobanSet;
import org.sdmlib.test.examples.reachabilitygraphs.sokoban.Sokoban;
   /**
    * 
    * @see <a href='../../../../../../../../../src/test/java/org/sdmlib/test/examples/reachabilitygraphs/SokobanModel.java'>SokobanModel.java</a>
 */
   public  class Box implements SendableEntity
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
      setTile(null);
      withoutSokoban(this.getSokoban().toArray(new Sokoban[this.getSokoban().size()]));
      firePropertyChange("REMOVE_YOU", this, null);
   }

   
   /********************************************************************
    * <pre>
    *              many                       one
    * Box ----------------------------------- Tile
    *              boxes                   tile
    * </pre>
    */
   
   public static final String PROPERTY_TILE = "tile";

   private Tile tile = null;

   public Tile getTile()
   {
      return this.tile;
   }

   public boolean setTile(Tile value)
   {
      boolean changed = false;
      
      if (this.tile != value)
      {
         Tile oldValue = this.tile;
         
         if (this.tile != null)
         {
            this.tile = null;
            oldValue.withoutBoxes(this);
         }
         
         this.tile = value;
         
         if (value != null)
         {
            value.withBoxes(this);
         }
         
         firePropertyChange(PROPERTY_TILE, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Box withTile(Tile value)
   {
      setTile(value);
      return this;
   } 

   public Tile createTile()
   {
      Tile value = new Tile();
      withTile(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              many                       many
    * Box ----------------------------------- Sokoban
    *              boxes                   sokoban
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

   public Box withSokoban(Sokoban... value)
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
               item.withBoxes(this);
               firePropertyChange(PROPERTY_SOKOBAN, null, item);
            }
         }
      }
      return this;
   } 

   public Box withoutSokoban(Sokoban... value)
   {
      for (Sokoban item : value)
      {
         if ((this.sokoban != null) && (item != null))
         {
            if (this.sokoban.remove(item))
            {
               item.withoutBoxes(this);
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
}
