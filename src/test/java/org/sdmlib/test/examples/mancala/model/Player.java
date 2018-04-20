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

import org.sdmlib.StrUtil;
import org.sdmlib.serialization.PropertyChangeInterface;
import org.sdmlib.test.examples.mancala.model.util.PitSet;
import org.sdmlib.test.examples.mancala.model.util.PlayerSet;
import org.sdmlib.test.examples.mancala.referencemodel.Color;

import de.uniks.networkparser.interfaces.SendableEntity;
import org.sdmlib.test.examples.mancala.model.PlayerState;
import org.sdmlib.test.examples.mancala.model.Mancala;
import org.sdmlib.test.examples.mancala.model.Kalah;
import org.sdmlib.test.examples.mancala.model.Pit;
import org.sdmlib.test.examples.mancala.model.Stone;
   /**
    * 
    * @see <a href='../../../../../../../../../src/test/java/org/sdmlib/test/examples/mancala/MancalaModel.java'>MancalaModel.java</a>
* @see org.sdmlib.test.examples.mancala.MancalaModel#MancalaModelCreation
 */
   public class Player implements PropertyChangeInterface, SendableEntity
{

   
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

	public boolean removePropertyChangeListener(String property, PropertyChangeListener listener) {
		if (listeners != null) {
			listeners.removePropertyChangeListener(property, listener);
		}
		return true;
	}

   
   //==========================================================================
   
   
   public void removeYou()
   {
      setActiveGame(null);
      setGame(null);
      withoutPits(this.getPits().toArray(new Pit[this.getPits().size()]));
      setKalah(null);
      setStone(null);
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   
   //==========================================================================
   
   public static final String PROPERTY_NAME = "name";
   
   private String name;

   public String getName()
   {
      return this.name;
   }
   
   public void setName(String value)
   {
      if ( ! StrUtil.stringEquals(this.name, value))
      {
         String oldValue = this.name;
         this.name = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_NAME, oldValue, value);
      }
   }
   
   public Player withName(String value)
   {
      setName(value);
      return this;
   } 


   @Override
   public String toString()
   {
      StringBuilder result = new StringBuilder();
      
      result.append(" ").append(this.getName());
      return result.substring(1);
   }


   
   //==========================================================================
   
   public static final String PROPERTY_STATE = "state";
   
   private PlayerState state;

   public PlayerState getState()
   {
      return this.state;
   }
   
   public void setState(PlayerState value)
   {
      if (this.state != value)
      {
         PlayerState oldValue = this.state;
         this.state = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_STATE, oldValue, value);
      }
   }
   
   public Player withState(PlayerState value)
   {
      setState(value);
      return this;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_COLOR = "color";
   
   private Color color;

   public Color getColor()
   {
      return this.color;
   }
   
   public void setColor(Color value)
   {
      if (this.color != value)
      {
         Color oldValue = this.color;
         this.color = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_COLOR, oldValue, value);
      }
   }
   
   public Player withColor(Color value)
   {
      setColor(value);
      return this;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       one
    * Player ----------------------------------- Mancala
    *              activePlayer                   activeGame
    * </pre>
    */
   
   public static final String PROPERTY_ACTIVEGAME = "activeGame";

   private Mancala activeGame = null;

   public Mancala getActiveGame()
   {
      return this.activeGame;
   }

   public boolean setActiveGame(Mancala value)
   {
      boolean changed = false;
      
      if (this.activeGame != value)
      {
         Mancala oldValue = this.activeGame;
         
         if (this.activeGame != null)
         {
            this.activeGame = null;
            oldValue.setActivePlayer(null);
         }
         
         this.activeGame = value;
         
         if (value != null)
         {
            value.withActivePlayer(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_ACTIVEGAME, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Player withActiveGame(Mancala value)
   {
      setActiveGame(value);
      return this;
   } 

   public Mancala createActiveGame()
   {
      Mancala value = new Mancala();
      withActiveGame(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              many                       one
    * Player ----------------------------------- Mancala
    *              players                   game
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
            oldValue.withoutPlayers(this);
         }
         
         this.game = value;
         
         if (value != null)
         {
            value.withPlayers(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_GAME, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Player withGame(Mancala value)
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

   
   public static final PlayerSet EMPTY_SET = new PlayerSet().withFlag(PlayerSet.READONLY);

   
   /********************************************************************
    * <pre>
    *              one                       many
    * Player ----------------------------------- Pit
    *              player                   pits
    * </pre>
    */
   
   public static final String PROPERTY_PITS = "pits";

   private PitSet pits = null;
   
   public PitSet getPits()
   {
      if (this.pits == null)
      {
         return Pit.EMPTY_SET;
      }
   
      return this.pits;
   }

   public Player withPits(Pit... value)
   {
      if(value==null){
         return this;
      }
      for (Pit item : value)
      {
         if (item != null)
         {
            if (this.pits == null)
            {
               this.pits = new PitSet();
            }
            
            boolean changed = this.pits.add (item);

            if (changed)
            {
               item.withPlayer(this);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_PITS, null, item);
            }
         }
      }
      return this;
   } 

   public Player withoutPits(Pit... value)
   {
      for (Pit item : value)
      {
         if ((this.pits != null) && (item != null))
         {
            if (this.pits.remove(item))
            {
               item.setPlayer(null);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_PITS, item, null);
            }
         }
      }
      return this;
   }

   public Pit createPits()
   {
      Pit value = new Pit();
      withPits(value);
      return value;
   } 

   public Kalah createPitsKalah()
   {
      Kalah value = new Kalah();
      withPits(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       one
    * Player ----------------------------------- Kalah
    *              kalahPlayer                   kalah
    * </pre>
    */
   
   public static final String PROPERTY_KALAH = "kalah";

   private Kalah kalah = null;

   public Kalah getKalah()
   {
      return this.kalah;
   }

   public boolean setKalah(Kalah value)
   {
      boolean changed = false;
      
      if (this.kalah != value)
      {
         Kalah oldValue = this.kalah;
         
         if (this.kalah != null)
         {
            this.kalah = null;
            oldValue.setKalahPlayer(null);
         }
         
         this.kalah = value;
         
         if (value != null)
         {
            value.withKalahPlayer(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_KALAH, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Player withKalah(Kalah value)
   {
      setKalah(value);
      return this;
   } 

   public Kalah createKalah()
   {
      Kalah value = new Kalah();
      withKalah(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       one
    * Player ----------------------------------- Stone
    *              player                   stone
    * </pre>
    */
   
   public static final String PROPERTY_STONE = "stone";

   private Stone stone = null;

   public Stone getStone()
   {
      return this.stone;
   }

   public boolean setStone(Stone value)
   {
      boolean changed = false;
      
      if (this.stone != value)
      {
         Stone oldValue = this.stone;
         
         if (this.stone != null)
         {
            this.stone = null;
            oldValue.setPlayer(null);
         }
         
         this.stone = value;
         
         if (value != null)
         {
            value.withPlayer(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_STONE, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Player withStone(Stone value)
   {
      setStone(value);
      return this;
   } 

   public Stone createStone()
   {
      Stone value = new Stone();
      withStone(value);
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
