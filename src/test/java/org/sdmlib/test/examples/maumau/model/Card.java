/*
   Copyright (c) 2015 Stefan
   
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
   
package org.sdmlib.test.examples.maumau.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import org.sdmlib.serialization.PropertyChangeInterface;

import de.uniks.networkparser.interfaces.SendableEntity;
   /**
    * 
    * @see <a href='../../../../../../../../../src/test/java/org/sdmlib/test/examples/maumau/GenModel.java'>GenModel.java</a>
*/
   public  class Card implements PropertyChangeInterface, SendableEntity
{

   
   //==========================================================================
   
   protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);
   
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
      setHolder(null);
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   
   //==========================================================================
   
   public static final String PROPERTY_SUIT = "suit";
   
   private Suit suit;

   public Suit getSuit()
   {
      return this.suit;
   }
   
   public void setSuit(Suit value)
   {
      if (this.suit != value) {
      
         Suit oldValue = this.suit;
         this.suit = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_SUIT, oldValue, value);
      }
   }
   
   public Card withSuit(Suit value)
   {
      setSuit(value);
      return this;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_VALUE = "value";
   
   private Value value;

   public Value getValue()
   {
      return this.value;
   }
   
   public void setValue(Value value)
   {
      if (this.value != value) {
      
         Value oldValue = this.value;
         this.value = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_VALUE, oldValue, value);
      }
   }
   
   public Card withValue(Value value)
   {
      setValue(value);
      return this;
   } 

   
   /********************************************************************
    * <pre>
    *              many                       one
    * Card ----------------------------------- MauMau
    *              cards                   game
    * </pre>
    */
   
   public static final String PROPERTY_GAME = "game";

   private MauMau game = null;

   public MauMau getGame()
   {
      return this.game;
   }

   public boolean setGame(MauMau value)
   {
      boolean changed = false;
      
      if (this.game != value)
      {
         MauMau oldValue = this.game;
         
         if (this.game != null)
         {
            this.game = null;
            oldValue.withoutCards(this);
         }
         
         this.game = value;
         
         if (value != null)
         {
            value.withCards(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_GAME, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Card withGame(MauMau value)
   {
      setGame(value);
      return this;
   } 

   public MauMau createGame()
   {
      MauMau value = new MauMau();
      withGame(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              many                       one
    * Card ----------------------------------- Holder
    *              cards                   holder
    * </pre>
    */
   
   public static final String PROPERTY_HOLDER = "holder";

   private Holder holder = null;

   public Holder getHolder()
   {
      return this.holder;
   }

   public boolean setHolder(Holder value)
   {
      boolean changed = false;
      
      if (this.holder != value)
      {
         Holder oldValue = this.holder;
         
         if (this.holder != null)
         {
            this.holder = null;
            oldValue.withoutCards(this);
         }
         
         this.holder = value;
         
         if (value != null)
         {
            value.withCards(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_HOLDER, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Card withHolder(Holder value)
   {
      setHolder(value);
      return this;
   } 

   public Holder createHolder()
   {
      Holder value = new Holder();
      withHolder(value);
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
