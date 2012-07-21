/*
   Copyright (c) 2012 zuendorf 
   
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
   
package org.sdmlib.examples.ludo;

import org.sdmlib.utils.PropertyChangeInterface;
import java.beans.PropertyChangeSupport;

public class Dice implements PropertyChangeInterface
{

   
   //==========================================================================
   
   public Object get(String attrName)
   {
      int pos = attrName.indexOf('.');
      String attribute = attrName;
      
      if (pos > 0)
      {
         attribute = attrName.substring(0, pos);
      }

      if (PROPERTY_GAME.equalsIgnoreCase(attrName))
      {
         return getGame();
      }

      if (PROPERTY_PLAYER.equalsIgnoreCase(attrName))
      {
         return getPlayer();
      }

      if (PROPERTY_VALUE.equalsIgnoreCase(attrName))
      {
         return getValue();
      }
      
      return null;
   }

   
   //==========================================================================
   
   public boolean set(String attrName, Object value)
   {
      if (PROPERTY_GAME.equalsIgnoreCase(attrName))
      {
         setGame((Ludo) value);
         return true;
      }

      if (PROPERTY_PLAYER.equalsIgnoreCase(attrName))
      {
         setPlayer((Player) value);
         return true;
      }

      if (PROPERTY_VALUE.equalsIgnoreCase(attrName))
      {
         setValue(Integer.parseInt(value.toString()));
         return true;
      }

      return false;
   }

   
   //==========================================================================
   
   protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);
   
   public PropertyChangeSupport getPropertyChangeSupport()
   {
      return listeners;
   }

   
   //==========================================================================
   
   public void removeYou()
   {
      setGame(null);
      setPlayer(null);
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   
   /********************************************************************
    * <pre>
    *              one                       one
    * Dice ----------------------------------- Ludo
    *              dice                   game
    * </pre>
    */
   
   public static final String PROPERTY_GAME = "game";
   
   private Ludo game = null;
   
   public Ludo getGame()
   {
      return this.game;
   }
   
   public boolean setGame(Ludo value)
   {
      boolean changed = false;
      
      if (this.game != value)
      {
         Ludo oldValue = this.game;
         
         if (this.game != null)
         {
            this.game = null;
            oldValue.setDice(null);
         }
         
         this.game = value;
         
         if (value != null)
         {
            value.withDice(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_GAME, oldValue, value);
         changed = true;
      }
      
      return changed;
   }
   
   public Dice withGame(Ludo value)
   {
      setGame(value);
      return this;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       one
    * Dice ----------------------------------- Player
    *              dice                   player
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
            oldValue.setDice(null);
         }
         
         this.player = value;
         
         if (value != null)
         {
            value.withDice(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_PLAYER, oldValue, value);
         changed = true;
      }
      
      return changed;
   }
   
   public Dice withPlayer(Player value)
   {
      setPlayer(value);
      return this;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_VALUE = "value";
   
   private int value;

   public int getValue()
   {
      return this.value;
   }
   
   public void setValue(int value)
   {
      if (this.value != value)
      {
         int oldValue = this.value;
         this.value = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_VALUE, oldValue, value);
      }
   }
   
   public Dice withValue(int value)
   {
      setValue(value);
      return this;
   } 
}

