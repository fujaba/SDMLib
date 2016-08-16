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
   
package org.sdmlib.test.examples.mancala.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import org.sdmlib.serialization.PropertyChangeInterface;
import org.sdmlib.test.examples.mancala.model.util.PitSet;
import de.uniks.networkparser.interfaces.SendableEntity;
import org.sdmlib.test.examples.mancala.model.Mancala;
import org.sdmlib.test.examples.mancala.model.Player;
   /**
    * 
    * @see <a href='../../../../../../../../../src/test/java/org/sdmlib/test/examples/mancala/MancalaModel.java'>MancalaModel.java</a>
*/
   public class Pit implements PropertyChangeInterface, SendableEntity
{

   
   //==========================================================================
   
   public void moveStones(  )
   {
      
   }

   
   //==========================================================================
   
   protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);
   
   @Override
   public PropertyChangeSupport getPropertyChangeSupport()
   {
      return listeners;
   }
   
   public boolean addPropertyChangeListener(PropertyChangeListener listener) 
   {
      getPropertyChangeSupport().addPropertyChangeListener(listener);
      return true;
   }
   
   public boolean addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
      getPropertyChangeSupport().addPropertyChangeListener(propertyName, listener);
      return true;
   }
   
	public boolean removePropertyChangeListener(PropertyChangeListener listener) {
		if (listeners != null) {
			listeners.removePropertyChangeListener(listener);
		}
		return true;
	}

	public boolean removePropertyChangeListener(String property,
			PropertyChangeListener listener) {
		if (listeners != null) {
			listeners.removePropertyChangeListener(property, listener);
		}
		return true;
	}
   
   //==========================================================================
   
   
   public void removeYou()
   {
      setGame(null);
      setPlayer(null);
      setNext(null);
      setPrevious(null);
      setCounterpart(null);
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   
   //==========================================================================
   
   public static final String PROPERTY_NR = "nr";
   
   private int nr;

   public int getNr()
   {
      return this.nr;
   }
   
   public void setNr(int value)
   {
      if (this.nr != value)
      {
         int oldValue = this.nr;
         this.nr = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_NR, oldValue, value);
      }
   }
   
   public Pit withNr(int value)
   {
      setNr(value);
      return this;
   } 


   @Override
   public String toString()
   {
      StringBuilder result = new StringBuilder();
      
      result.append(" ").append(this.getNr());
      return result.substring(1);
   }


   
   /********************************************************************
    * <pre>
    *              many                       one
    * Pit ----------------------------------- Mancala
    *              pits                   game
    * </pre>
    */
   
   public static final String PROPERTY_GAME = "game";

   private Mancala game = null;

   public Mancala getGame()
   {
      return this.game;
   }

   public boolean setGame(Mancala value)
   {
      boolean changed = false;
      
      if (this.game != value)
      {
         Mancala oldValue = this.game;
         
         if (this.game != null)
         {
            this.game = null;
            oldValue.withoutPits(this);
         }
         
         this.game = value;
         
         if (value != null)
         {
            value.withPits(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_GAME, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Pit withGame(Mancala value)
   {
      setGame(value);
      return this;
   } 

   public Mancala createGame()
   {
      Mancala value = new Mancala();
      withGame(value);
      return value;
   } 

   
   public static final PitSet EMPTY_SET = new PitSet().withFlag(PitSet.READONLY);

   
   /********************************************************************
    * <pre>
    *              many                       one
    * Pit ----------------------------------- Player
    *              pits                   player
    * </pre>
    */
   
   public static final String PROPERTY_PLAYER = "player";

   private Player player = null;

   public Player getPlayer()
   {
      return this.player;
   }

   public boolean setPlayer(Player value)
   {
      boolean changed = false;
      
      if (this.player != value)
      {
         Player oldValue = this.player;
         
         if (this.player != null)
         {
            this.player = null;
            oldValue.withoutPits(this);
         }
         
         this.player = value;
         
         if (value != null)
         {
            value.withPits(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_PLAYER, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Pit withPlayer(Player value)
   {
      setPlayer(value);
      return this;
   } 

   public Player createPlayer()
   {
      Player value = new Player();
      withPlayer(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       one
    * Pit ----------------------------------- Pit
    *              previous                   next
    * </pre>
    */
   
   public static final String PROPERTY_NEXT = "next";

   private Pit next = null;

   public Pit getNext()
   {
      return this.next;
   }
   public PitSet getNextTransitive()
   {
      PitSet result = new PitSet().with(this);
      return result.getNextTransitive();
   }


   public boolean setNext(Pit value)
   {
      boolean changed = false;
      
      if (this.next != value)
      {
         Pit oldValue = this.next;
         
         if (this.next != null)
         {
            this.next = null;
            oldValue.setPrevious(null);
         }
         
         this.next = value;
         
         if (value != null)
         {
            value.withPrevious(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_NEXT, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Pit withNext(Pit value)
   {
      setNext(value);
      return this;
   } 

   public Pit createNext()
   {
      Pit value = new Pit();
      withNext(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       one
    * Pit ----------------------------------- Pit
    *              next                   previous
    * </pre>
    */
   
   public static final String PROPERTY_PREVIOUS = "previous";

   private Pit previous = null;

   public Pit getPrevious()
   {
      return this.previous;
   }
   public PitSet getPreviousTransitive()
   {
      PitSet result = new PitSet().with(this);
      return result.getPreviousTransitive();
   }


   public boolean setPrevious(Pit value)
   {
      boolean changed = false;
      
      if (this.previous != value)
      {
         Pit oldValue = this.previous;
         
         if (this.previous != null)
         {
            this.previous = null;
            oldValue.setNext(null);
         }
         
         this.previous = value;
         
         if (value != null)
         {
            value.withNext(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_PREVIOUS, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Pit withPrevious(Pit value)
   {
      setPrevious(value);
      return this;
   } 

   public Pit createPrevious()
   {
      Pit value = new Pit();
      withPrevious(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       one
    * Pit ----------------------------------- Pit
    *              counterpart                   counterpart
    * </pre>
    */
   
   public static final String PROPERTY_COUNTERPART = "counterpart";

   private Pit counterpart = null;

   public Pit getCounterpart()
   {
      return this.counterpart;
   }
   public PitSet getCounterpartTransitive()
   {
      PitSet result = new PitSet().with(this);
      return result.getCounterpartTransitive();
   }


   public boolean setCounterpart(Pit value)
   {
      boolean changed = false;
      
      if (this.counterpart != value)
      {
         Pit oldValue = this.counterpart;
         
         if (this.counterpart != null)
         {
            this.counterpart = null;
            oldValue.setCounterpart(null);
         }
         
         this.counterpart = value;
         
         if (value != null)
         {
            value.withCounterpart(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_COUNTERPART, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Pit withCounterpart(Pit value)
   {
      setCounterpart(value);
      return this;
   } 

   public Pit createCounterpart()
   {
      Pit value = new Pit();
      withCounterpart(value);
      return value;
   } 

   public boolean firePropertyChange(String propertyName, Object oldValue, Object newValue)
   {
      if (listeners != null) {
   		listeners.firePropertyChange(propertyName, oldValue, newValue);
   		return true;
   	}
   	return false;
   }
   }
