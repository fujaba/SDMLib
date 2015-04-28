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
   
package org.sdmlib.examples.mancala.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import org.sdmlib.examples.mancala.model.util.PitSet;
import org.sdmlib.examples.mancala.model.util.PlayerSet;
import org.sdmlib.serialization.PropertyChangeInterface;

public class Mancala implements PropertyChangeInterface
{

   
   //==========================================================================
   
   public void checkEnd(  )
   {
      
   }

   
   //==========================================================================
   
   public void initGame( String firstPlayerName, String secondPlayerName )
   {
      
   }

   
   //==========================================================================
   
   protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);
   
   @Override
   public PropertyChangeSupport getPropertyChangeSupport()
   {
      return listeners;
   }
   
   public void addPropertyChangeListener(PropertyChangeListener listener) 
   {
      getPropertyChangeSupport().addPropertyChangeListener(listener);
   }

   
   //==========================================================================
   
   
   public void removeYou()
   {
      setActivePlayer(null);
      withoutPlayers(this.getPlayers().toArray(new Player[this.getPlayers().size()]));
      withoutPits(this.getPits().toArray(new Pit[this.getPits().size()]));
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   
   /********************************************************************
    * <pre>
    *              one                       one
    * Mancala ----------------------------------- Player
    *              activeGame                   activePlayer
    * </pre>
    */
   
   public static final String PROPERTY_ACTIVEPLAYER = "activePlayer";

   private Player activePlayer = null;

   public Player getActivePlayer()
   {
      return this.activePlayer;
   }

   public boolean setActivePlayer(Player value)
   {
      boolean changed = false;
      
      if (this.activePlayer != value)
      {
         Player oldValue = this.activePlayer;
         
         if (this.activePlayer != null)
         {
            this.activePlayer = null;
            oldValue.setActiveGame(null);
         }
         
         this.activePlayer = value;
         
         if (value != null)
         {
            value.withActiveGame(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_ACTIVEPLAYER, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Mancala withActivePlayer(Player value)
   {
      setActivePlayer(value);
      return this;
   } 

   public Player createActivePlayer()
   {
      Player value = new Player();
      withActivePlayer(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       many
    * Mancala ----------------------------------- Player
    *              game                   players
    * </pre>
    */
   
   public static final String PROPERTY_PLAYERS = "players";

   private PlayerSet players = null;
   
   public PlayerSet getPlayers()
   {
      if (this.players == null)
      {
         return Player.EMPTY_SET;
      }
   
      return this.players;
   }

   public Mancala withPlayers(Player... value)
   {
      if(value==null){
         return this;
      }
      for (Player item : value)
      {
         if (item != null)
         {
            if (this.players == null)
            {
               this.players = new PlayerSet();
            }
            
            boolean changed = this.players.add (item);

            if (changed)
            {
               item.withGame(this);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_PLAYERS, null, item);
            }
         }
      }
      return this;
   } 

   public Mancala withoutPlayers(Player... value)
   {
      for (Player item : value)
      {
         if ((this.players != null) && (item != null))
         {
            if (this.players.remove(item))
            {
               item.setGame(null);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_PLAYERS, item, null);
            }
         }
      }
      return this;
   }

   public Player createPlayers()
   {
      Player value = new Player();
      withPlayers(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       many
    * Mancala ----------------------------------- Pit
    *              game                   pits
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

   public Mancala withPits(Pit... value)
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
               item.withGame(this);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_PITS, null, item);
            }
         }
      }
      return this;
   } 

   public Mancala withoutPits(Pit... value)
   {
      for (Pit item : value)
      {
         if ((this.pits != null) && (item != null))
         {
            if (this.pits.remove(item))
            {
               item.setGame(null);
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
}
