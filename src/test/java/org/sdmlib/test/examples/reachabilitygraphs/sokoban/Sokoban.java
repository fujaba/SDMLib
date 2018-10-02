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

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import org.sdmlib.test.examples.reachabilitygraphs.sokoban.util.BoxSet;

import de.uniks.networkparser.interfaces.SendableEntity;
   /**
    * 
    * @see <a href='../../../../../../../../../src/test/java/org/sdmlib/test/examples/reachabilitygraphs/SokobanModel.java'>SokobanModel.java</a>
 * @see org.sdmlib.test.examples.reachabilitygraphs.SokobanModel#SokobanModelGen
 */
   public  class Sokoban implements SendableEntity
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

   

   
   @Override
   public String toString()
   {
      StringBuilder result = new StringBuilder();

      result.append(this.getMaze() != null ? this.getMaze().toString(this) : " ");
      
      return result.substring(1);
   }



   
   //==========================================================================
   
   
   public void removeYou()
   {
      for (Box obj : new BoxSet(this.getBoxes())) { obj.removeYou(); }
      if (getKarli() != null) { getKarli().removeYou(); }
      if (getMaze() != null) { getMaze().removeYou(); }
      setAkarli(null);
      firePropertyChange("REMOVE_YOU", this, null);
   }

   
   /********************************************************************
    * <pre>
    *              one                       many
    * Sokoban ----------------------------------- Box
    *              sokoban                   boxes
    * </pre>
    */
   
   public static final String PROPERTY_BOXES = "boxes";

   private BoxSet boxes = null;
   
   public BoxSet getBoxes()
   {
      if (this.boxes == null)
      {
         return BoxSet.EMPTY_SET;
      }
   
      return this.boxes;
   }

   public Sokoban withBoxes(Box... value)
   {
      if(value==null){
         return this;
      }
      for (Box item : value)
      {
         if (item != null)
         {
            if (this.boxes == null)
            {
               this.boxes = new BoxSet();
            }
            
            boolean changed = this.boxes.add (item);

            if (changed)
            {
               firePropertyChange(PROPERTY_BOXES, null, item);
            }
         }
      }
      return this;
   } 

   public Sokoban withoutBoxes(Box... value)
   {
      for (Box item : value)
      {
         if ((this.boxes != null) && (item != null))
         {
            if (this.boxes.remove(item))
            {
               firePropertyChange(PROPERTY_BOXES, item, null);
            }
         }
      }
      return this;
   }

   public Box createBoxes()
   {
      Box value = new Box();
      withBoxes(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       one
    * Sokoban ----------------------------------- Karli
    *              sokoban                   karli
    * </pre>
    */
   
   public static final String PROPERTY_KARLI = "karli";

   private Karli karli = null;

     /**
    * 
    * @see <a href='../../../../../../../../../src/test/java/org/sdmlib/test/examples/reachabilitygraphs/SokobanLevels.java'>SokobanLevels.java</a>
 */
   public Karli getKarli()
   {
      return this.karli;
   }

     /**
    * 
    * @see <a href='../../../../../../../../../src/test/java/org/sdmlib/test/examples/reachabilitygraphs/SokobanLevels.java'>SokobanLevels.java</a>
 */
   public boolean setKarli(Karli value)
   {
      boolean changed = false;
      
      if (this.karli != value)
      {
         Karli oldValue = this.karli;
         
         this.karli = value;
         
         firePropertyChange(PROPERTY_KARLI, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Sokoban withKarli(Karli value)
   {
      setKarli(value);
      return this;
   } 

   public Karli createKarli()
   {
      Karli value = new Karli();
      withKarli(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       one
    * Sokoban ----------------------------------- Maze
    *              sokoban                   maze
    * </pre>
    */
   
   public static final String PROPERTY_MAZE = "maze";

   private Maze maze = null;

   public Maze getMaze()
   {
      return this.maze;
   }

   public boolean setMaze(Maze value)
   {
      boolean changed = false;
      
      if (this.maze != value)
      {
         Maze oldValue = this.maze;
         
         this.maze = value;
         
         firePropertyChange(PROPERTY_MAZE, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Sokoban withMaze(Maze value)
   {
      setMaze(value);
      return this;
   } 

   public Maze createMaze()
   {
      Maze value = new Maze();
      withMaze(value);
      return value;
   } 


   
   /********************************************************************
    * <pre>
    *              one                       one
    * Sokoban ----------------------------------- AKarli
    *              sokoban                   akarli
    * </pre>
    */
   
   public static final String PROPERTY_AKARLI = "akarli";

   private AKarli akarli = null;

   public AKarli getAkarli()
   {
      return this.akarli;
   }

   public boolean setAkarli(AKarli value)
   {
      boolean changed = false;
      
      if (this.akarli != value)
      {
         AKarli oldValue = this.akarli;
         
         this.akarli = value;
         
         firePropertyChange(PROPERTY_AKARLI, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Sokoban withAkarli(AKarli value)
   {
      setAkarli(value);
      return this;
   } 

     /**
    * 
    * @see <a href='../../../../../../../../../src/test/java/org/sdmlib/test/examples/reachabilitygraphs/SokobanLevels.java'>SokobanLevels.java</a>
 */
   public AKarli createAkarli()
   {
      AKarli value = new AKarli();
      withAkarli(value);
      return value;
   } 
}
