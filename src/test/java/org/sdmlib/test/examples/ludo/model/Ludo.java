/*
   Copyright (c) 2014 zuendorf 
   
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
   
package org.sdmlib.test.examples.ludo.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Date;
import java.util.LinkedHashSet;

import org.sdmlib.serialization.PropertyChangeInterface;
import org.sdmlib.test.examples.ludo.model.util.FieldSet;
import org.sdmlib.test.examples.ludo.model.util.PlayerSet;
   /**
    * 
    * @see <a href='../../../../../../../../../src/test/java/org/sdmlib/test/examples/ludo/LudoStoryboard.java'>LudoStoryboard.java</a>
*/
   public class Ludo implements PropertyChangeInterface
{

   
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
      removeAllFromPlayers();
      setDice(null);
      removeAllFromFields();
      withoutPlayers(this.getPlayers().toArray(new Player[this.getPlayers().size()]));
      withoutFields(this.getFields().toArray(new Field[this.getFields().size()]));
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   
   //==========================================================================
   
   public static final String PROPERTY_DATE = "date";
   
   private Date date;

   public Date getDate()
   {
      return this.date;
   }
   
   public void setDate(Date value)
   {
      if (this.date != value)
      {
         Date oldValue = this.date;
         this.date = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_DATE, oldValue, value);
      }
   }
   
   public Ludo withDate(Date value)
   {
      setDate(value);
      return this;
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
         changed = this.players.remove(value);
         
         if (changed)
         {
            value.setGame(null);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_PLAYERS, value, null);
         }
      }
         
      return changed;   
   }

   public Ludo withPlayers(Player... value)
   {
      if(value==null){
         return this;
      }
      for (Player item : value)
      {
         addToPlayers(item);
      }
      return this;
   } 

   public Ludo withoutPlayers(Player... value)
   {
      for (Player item : value)
      {
         removeFromPlayers(item);
      }
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

     /**
    * 
    * @see <a href='../../../../../../../../../src/test/java/org/sdmlib/test/examples/ludo/LudoStoryboard.java'>LudoStoryboard.java</a>
*/
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
         changed = this.fields.remove(value);
         
         if (changed)
         {
            value.setGame(null);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_FIELDS, value, null);
         }
      }
         
      return changed;   
   }

   public Ludo withFields(Field... value)
   {
      if(value==null){
         return this;
      }
      for (Field item : value)
      {
         addToFields(item);
      }
      return this;
   } 

   public Ludo withoutFields(Field... value)
   {
      for (Field item : value)
      {
         removeFromFields(item);
      }
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

