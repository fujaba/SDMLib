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

import java.beans.PropertyChangeSupport;
import java.util.LinkedHashSet;

import org.sdmlib.examples.ludo.creators.FieldSet;
import org.sdmlib.examples.ludo.creators.PlayerSet;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.utils.PropertyChangeInterface;

public class Ludo implements PropertyChangeInterface
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

      if (PROPERTY_PLAYERS.equalsIgnoreCase(attrName))
      {
         return getPlayers();
      }

      if (PROPERTY_DICE.equalsIgnoreCase(attrName))
      {
         return getDice();
      }

      if (PROPERTY_FIELDS.equalsIgnoreCase(attrName))
      {
         return getFields();
      }
      
      return null;
   }

   
   //==========================================================================
   
   public boolean set(String attrName, Object value)
   {
      if (PROPERTY_PLAYERS.equalsIgnoreCase(attrName))
      {
         addToPlayers((Player) value);
         return true;
      }
      
      if ((PROPERTY_PLAYERS + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         removeFromPlayers((Player) value);
         return true;
      }

      if (PROPERTY_DICE.equalsIgnoreCase(attrName))
      {
         setDice((Dice) value);
         return true;
      }

      if (PROPERTY_FIELDS.equalsIgnoreCase(attrName))
      {
         addToFields((Field) value);
         return true;
      }
      
      if ((PROPERTY_FIELDS + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         removeFromFields((Field) value);
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
      removeAllFromPlayers();
      setDice(null);
      removeAllFromFields();
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   
   /********************************************************************
    * <pre>
    *              one                       many
    * Ludo ----------------------------------- Player
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
   
   public boolean addToPlayers(Player value)
   {
      boolean changed = false;
      
      if (value != null)
      {
         if (this.players == null)
         {
            this.players = new PlayerSet();
         }
         
         changed = this.players.add (value);
         
         if (changed)
         {
            value.withGame(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_PLAYERS, null, value);
         }
      }
         
      return changed;   
   }
   
   public boolean removeFromPlayers(Player value)
   {
      boolean changed = false;
      
      if ((this.players != null) && (value != null))
      {
         changed = this.players.remove (value);
         
         if (changed)
         {
            value.setGame(null);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_PLAYERS, value, null);
         }
      }
         
      return changed;   
   }
   
   public Ludo withPlayers(Player value)
   {
      addToPlayers(value);
      return this;
   } 
   
   public Ludo withoutPlayers(Player value)
   {
      removeFromPlayers(value);
      return this;
   } 
   
   public void removeAllFromPlayers()
   {
      LinkedHashSet<Player> tmpSet = new LinkedHashSet<Player>(this.getPlayers());
   
      for (Player value : tmpSet)
      {
         this.removeFromPlayers(value);
      }
   }
   
   public Player createPlayers()
   {
      Player value = new Player();
      withPlayers(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       one
    * Ludo ----------------------------------- Dice
    *              game                   dice
    * </pre>
    */
   
   public static final String PROPERTY_DICE = "dice";
   
   private Dice dice = null;
   
   public Dice getDice()
   {
      return this.dice;
   }
   
   public boolean setDice(Dice value)
   {
      boolean changed = false;
      
      if (this.dice != value)
      {
         Dice oldValue = this.dice;
         
         if (this.dice != null)
         {
            this.dice = null;
            oldValue.setGame(null);
         }
         
         this.dice = value;
         
         if (value != null)
         {
            value.withGame(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_DICE, oldValue, value);
         changed = true;
      }
      
      return changed;
   }
   
   public Ludo withDice(Dice value)
   {
      setDice(value);
      return this;
   } 
   
   public Dice createDice()
   {
      Dice value = new Dice();
      withDice(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       many
    * Ludo ----------------------------------- Field
    *              game                   fields
    * </pre>
    */
   
   public static final String PROPERTY_FIELDS = "fields";
   
   private FieldSet fields = null;
   
   public FieldSet getFields()
   {
      if (this.fields == null)
      {
         return Field.EMPTY_SET;
      }
   
      return this.fields;
   }
   
   public boolean addToFields(Field value)
   {
      boolean changed = false;
      
      if (value != null)
      {
         if (this.fields == null)
         {
            this.fields = new FieldSet();
         }
         
         changed = this.fields.add (value);
         
         if (changed)
         {
            value.withGame(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_FIELDS, null, value);
         }
      }
         
      return changed;   
   }
   
   public boolean removeFromFields(Field value)
   {
      boolean changed = false;
      
      if ((this.fields != null) && (value != null))
      {
         changed = this.fields.remove (value);
         
         if (changed)
         {
            value.setGame(null);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_FIELDS, value, null);
         }
      }
         
      return changed;   
   }
   
   public Ludo withFields(Field value)
   {
      addToFields(value);
      return this;
   } 
   
   public Ludo withoutFields(Field value)
   {
      removeFromFields(value);
      return this;
   } 
   
   public void removeAllFromFields()
   {
      LinkedHashSet<Field> tmpSet = new LinkedHashSet<Field>(this.getFields());
   
      for (Field value : tmpSet)
      {
         this.removeFromFields(value);
      }
   }
   
   public Field createFields()
   {
      Field value = new Field();
      withFields(value);
      return value;
   } 
}

