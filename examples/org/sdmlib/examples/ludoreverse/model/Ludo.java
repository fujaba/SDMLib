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
   
package org.sdmlib.examples.ludoreverse.model;

import java.beans.PropertyChangeSupport;
import java.util.LinkedHashSet;

import org.sdmlib.StrUtil;
import org.sdmlib.examples.ludoreverse.model.creators.PlayerSet;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.serialization.util.PropertyChangeInterface;

import java.beans.PropertyChangeListener;

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

      if (PROPERTY_STYLE.equalsIgnoreCase(attribute))
      {
         return getStyle();
      }

      if (PROPERTY_AGE.equalsIgnoreCase(attribute))
      {
         return getAge();
      }

      if (PROPERTY_PLAYERS.equalsIgnoreCase(attrName))
      {
         return getPlayers();
      }
      
      return null;
   }

   
   //==========================================================================
   
   public boolean set(String attrName, Object value)
   {
      if (PROPERTY_STYLE.equalsIgnoreCase(attrName))
      {
         setStyle((String) value);
         return true;
      }

      if (PROPERTY_AGE.equalsIgnoreCase(attrName))
      {
         setAge(Integer.parseInt(value.toString()));
         return true;
      }

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
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   
   //==========================================================================
   
   public static final String PROPERTY_STYLE = "style";
   
   private String style;

   public String getStyle()
   {
      return this.style;
   }
   
   public void setStyle(String value)
   {
      if ( ! StrUtil.stringEquals(this.style, value))
      {
         String oldValue = this.style;
         this.style = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_STYLE, oldValue, value);
      }
   }
   
   public Ludo withStyle(String value)
   {
      setStyle(value);
      return this;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_AGE = "age";
   
   private int age;

   public int getAge()
   {
      return this.age;
   }
   
   public void setAge(int value)
   {
      if (this.age != value)
      {
         int oldValue = this.age;
         this.age = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_AGE, oldValue, value);
      }
   }
   
   public Ludo withAge(int value)
   {
      setAge(value);
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

   public String toString()
   {
      StringBuilder _ = new StringBuilder();
      
      _.append(" ").append(this.getStyle());
      _.append(" ").append(this.getAge());
      return _.substring(1);
   }


   public Ludo withPlayers(Player... value)
   {
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
}

