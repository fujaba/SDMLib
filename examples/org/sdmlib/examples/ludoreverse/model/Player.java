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
   
package org.sdmlib.examples.ludoreverse.model;

import org.sdmlib.serialization.PropertyChangeInterface;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;
import org.sdmlib.StrUtil;
import org.sdmlib.examples.ludoreverse.model.util.LudoSet;
import java.util.LinkedHashSet;

public class Player implements PropertyChangeInterface
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
      StringBuilder _ = new StringBuilder();
      
      _.append(" ").append(this.getName());
      _.append(" ").append(this.getColor());
      return _.substring(1);
   }


   
   //==========================================================================
   
   public static final String PROPERTY_COLOR = "color";
   
   private String color;

   public String getColor()
   {
      return this.color;
   }
   
   public void setColor(String value)
   {
      if ( ! StrUtil.stringEquals(this.color, value))
      {
         String oldValue = this.color;
         this.color = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_COLOR, oldValue, value);
      }
   }
   
   public Player withColor(String value)
   {
      setColor(value);
      return this;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       many
    * Player ----------------------------------- Ludo
    *              game                   players
    * </pre>
    */
   
   public static final String PROPERTY_PLAYERS = "players";

   private LudoSet players = null;
   
   public LudoSet getPlayers()
   {
      if (this.players == null)
      {
         return Ludo.EMPTY_SET;
      }
   
      return this.players;
   }

   public boolean addToPlayers(Ludo value)
   {
      boolean changed = false;
      
      if (value != null)
      {
         if (this.players == null)
         {
            this.players = new LudoSet();
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

   public boolean removeFromPlayers(Ludo value)
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

   public Player withPlayers(Ludo... value)
   {
      if(value==null){
         return this;
      }
      for (Ludo item : value)
      {
         addToPlayers(item);
      }
      return this;
   } 

   public Player withoutPlayers(Ludo... value)
   {
      for (Ludo item : value)
      {
         removeFromPlayers(item);
      }
      return this;
   }

   public void removeAllFromPlayers()
   {
      LinkedHashSet<Ludo> tmpSet = new LinkedHashSet<Ludo>(this.getPlayers());
   
      for (Ludo value : tmpSet)
      {
         this.removeFromPlayers(value);
      }
   }

   public Ludo createPlayers()
   {
      Ludo value = new Ludo();
      withPlayers(value);
      return value;
   } 
}
