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

import org.sdmlib.examples.ludoreverse.model.creators.PlayerSet;
import org.sdmlib.utils.PropertyChangeInterface;
import org.sdmlib.utils.StrUtil;

public class Player implements PropertyChangeInterface
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

      if (PROPERTY_NAME.equalsIgnoreCase(attribute))
      {
         return getName();
      }

      if (PROPERTY_COLOR.equalsIgnoreCase(attribute))
      {
         return getColor();
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

      if (PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         setName((String) value);
         return true;
      }

      if (PROPERTY_COLOR.equalsIgnoreCase(attrName))
      {
         setColor((String) value);
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
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   
   public static final PlayerSet EMPTY_SET = new PlayerSet();

   
   /********************************************************************
    * <pre>
    *              many                       one
    * Player ----------------------------------- Ludo
    *              players                   game
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
   
   public Player withGame(Ludo value)
   {
      setGame(value);
      return this;
   } 
   
   public Ludo createGame()
   {
      Ludo value = new Ludo();
      withGame(value);
      return value;
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

   public String toString()
   {
      StringBuilder _ = new StringBuilder();
      
      _.append(" ").append(this.getName());
      _.append(" ").append(this.getColor());
      return _.substring(1);
   }

}

