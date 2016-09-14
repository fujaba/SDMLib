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
   
package org.sdmlib.test.examples.ludoreverse.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import org.sdmlib.StrUtil;
import org.sdmlib.serialization.PropertyChangeInterface;
import org.sdmlib.test.examples.ludoreverse.model.util.LudoSet;
import de.uniks.networkparser.interfaces.SendableEntity;
import org.sdmlib.test.examples.ludoreverse.model.Player;
   /**
    * 
    * @see <a href='../../../../../../../../../src/test/java/org/sdmlib/test/examples/ludoreverse/LudoReverseModel.java'>LudoReverseModel.java</a>
*/
   public class Ludo implements PropertyChangeInterface, SendableEntity
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
      setGame(null);
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


   @Override
   public String toString()
   {
      StringBuilder s = new StringBuilder();
      
      s.append(" ").append(this.getStyle());
      s.append(" ").append(this.getAge());
      return s.substring(1);
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

   
   public static final LudoSet EMPTY_SET = new LudoSet();

   
   /********************************************************************
    * <pre>
    *              many                       one
    * Ludo ----------------------------------- Player
    *              players                   game
    * </pre>
    */
   
   public static final String PROPERTY_GAME = "game";

   private Player game = null;

   public Player getGame()
   {
      return this.game;
   }

   public boolean setGame(Player value)
   {
      boolean changed = false;
      
      if (this.game != value)
      {
         Player oldValue = this.game;
         
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

   public Ludo withGame(Player value)
   {
      setGame(value);
      return this;
   } 

   public Player createGame()
   {
      Player value = new Player();
      withGame(value);
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

