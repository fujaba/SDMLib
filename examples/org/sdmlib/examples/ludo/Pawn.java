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

import org.sdmlib.examples.ludo.creators.PawnSet;
import org.sdmlib.utils.PropertyChangeInterface;
import org.sdmlib.utils.StrUtil;

public class Pawn implements PropertyChangeInterface
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

      if (PROPERTY_COLOR.equalsIgnoreCase(attribute))
      {
         return getColor();
      }

      if (PROPERTY_X.equalsIgnoreCase(attribute))
      {
         return getX();
      }

      if (PROPERTY_Y.equalsIgnoreCase(attribute))
      {
         return getY();
      }

      if (PROPERTY_PLAYER.equalsIgnoreCase(attrName))
      {
         return getPlayer();
      }

      if (PROPERTY_POS.equalsIgnoreCase(attrName))
      {
         return getPos();
      }
      
      return null;
   }

   
   //==========================================================================
   
   public boolean set(String attrName, Object value)
   {
      if (PROPERTY_COLOR.equalsIgnoreCase(attrName))
      {
         setColor((String) value);
         return true;
      }

      if (PROPERTY_X.equalsIgnoreCase(attrName))
      {
         setX(Integer.parseInt(value.toString()));
         return true;
      }

      if (PROPERTY_Y.equalsIgnoreCase(attrName))
      {
         setY(Integer.parseInt(value.toString()));
         return true;
      }

      if (PROPERTY_PLAYER.equalsIgnoreCase(attrName))
      {
         setPlayer((Player) value);
         return true;
      }

      if (PROPERTY_POS.equalsIgnoreCase(attrName))
      {
         setPos((Field) value);
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
      setPlayer(null);
      setPos(null);
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
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
   
   public Pawn withColor(String value)
   {
      setColor(value);
      return this;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_X = "x";
   
   private int x;

   public int getX()
   {
      return this.x;
   }
   
   public void setX(int value)
   {
      if (this.x != value)
      {
         int oldValue = this.x;
         this.x = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_X, oldValue, value);
      }
   }
   
   public Pawn withX(int value)
   {
      setX(value);
      return this;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_Y = "y";
   
   private int y;

   public int getY()
   {
      return this.y;
   }
   
   public void setY(int value)
   {
      if (this.y != value)
      {
         int oldValue = this.y;
         this.y = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_Y, oldValue, value);
      }
   }
   
   public Pawn withY(int value)
   {
      setY(value);
      return this;
   } 

   
   public static final PawnSet EMPTY_SET = new PawnSet();

   
   /********************************************************************
    * <pre>
    *              many                       one
    * Pawn ----------------------------------- Player
    *              pawns                   player
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
            oldValue.withoutPawns(this);
         }
         
         this.player = value;
         
         if (value != null)
         {
            value.withPawns(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_PLAYER, oldValue, value);
         changed = true;
      }
      
      return changed;
   }
   
   public Pawn withPlayer(Player value)
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
    *              many                       one
    * Pawn ----------------------------------- Field
    *              pawns                   pos
    * </pre>
    */
   
   public static final String PROPERTY_POS = "pos";
   
   private Field pos = null;
   
   public Field getPos()
   {
      return this.pos;
   }
   
   public boolean setPos(Field value)
   {
      boolean changed = false;
      
      if (this.pos != value)
      {
         Field oldValue = this.pos;
         
         if (this.pos != null)
         {
            this.pos = null;
            oldValue.withoutPawns(this);
         }
         
         this.pos = value;
         
         if (value != null)
         {
            value.withPawns(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_POS, oldValue, value);
         changed = true;
      }
      
      return changed;
   }
   
   public Pawn withPos(Field value)
   {
      setPos(value);
      return this;
   } 
   
   public Field createPos()
   {
      Field value = new Field();
      withPos(value);
      return value;
   } 
}

