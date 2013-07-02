/*
   Copyright (c) 2013 zuendorf 
   
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
   
package org.sdmlib.examples.replication.maumau;

import org.sdmlib.utils.PropertyChangeInterface;
import java.beans.PropertyChangeSupport;
import org.sdmlib.examples.replication.maumau.creators.CardSet;

public class Card implements PropertyChangeInterface
{

   
   //==========================================================================
   
   public Object get(String attrName)
   {
      if (PROPERTY_SUIT.equalsIgnoreCase(attrName))
      {
         return getSuit();
      }

      if (PROPERTY_VALUE.equalsIgnoreCase(attrName))
      {
         return getValue();
      }

      if (PROPERTY_GAME.equalsIgnoreCase(attrName))
      {
         return getGame();
      }

      if (PROPERTY_HOLDER.equalsIgnoreCase(attrName))
      {
         return getHolder();
      }

      return null;
   }

   
   //==========================================================================
   
   public boolean set(String attrName, Object value)
   {
      if (PROPERTY_SUIT.equalsIgnoreCase(attrName))
      {
         String suitStr = (String)value;
         Suit valueOf = Suit.valueOf(suitStr);
         setSuit(valueOf);
         return true;
      }

      if (PROPERTY_VALUE.equalsIgnoreCase(attrName))
      {
         String valueStr = (String) value;
         
         try
         {
            int parseInt = Integer.parseInt(valueStr);
            valueStr = "_" + valueStr;
         }
         catch (NumberFormatException e)
         {
         }
         Value valueOf = Value.valueOf(valueStr);
         setValue(valueOf);
         return true;
      }

      if (PROPERTY_GAME.equalsIgnoreCase(attrName))
      {
         setGame((MauMau) value);
         return true;
      }

      if (PROPERTY_HOLDER.equalsIgnoreCase(attrName))
      {
         setHolder((Holder) value);
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
      if (this.suit != value)
      {
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
   
   private org.sdmlib.examples.replication.maumau.Value value;

   public org.sdmlib.examples.replication.maumau.Value getValue()
   {
      return this.value;
   }
   
   public void setValue(org.sdmlib.examples.replication.maumau.Value value)
   {
      if (this.value != value)
      {
         org.sdmlib.examples.replication.maumau.Value oldValue = this.value;
         this.value = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_VALUE, oldValue, value);
      }
   }
   
   public Card withValue(org.sdmlib.examples.replication.maumau.Value value)
   {
      setValue(value);
      return this;
   } 

   
   public static final CardSet EMPTY_SET = new CardSet();

   
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

   
   public boolean playToStack()
   {
      // check valid move
      Card lastCard = this.getGame().getStack().getCards().getLast();
      
      Player player = null; 
      
      if (this.getHolder() != null && this.getHolder() instanceof Player)
      {
         player = (Player) this.getHolder();

         if (player.getAssignment() != null)
         {
            if (this.getValue() == Value.Jack || lastCard.getSuit() == this.getSuit() 
                  || lastCard.getValue() == this.getValue())
            {
               this.getGame().setCurrentMove(player.getNext());

               this.getGame().getStack().addToCards(this);

               return true;
            }
         }
      }

      return false;
   } 
   
   public String toString()
   {
      return "" + this.getValue() + " " + this.getSuit();
   }


   public boolean draw(Player targetPlayer)
   {
      if (targetPlayer.getAssignment() != null && this.getHolder() == this.getGame().getDeck())
      {
         this.setHolder(targetPlayer);
         
         this.getGame().setCurrentMove(targetPlayer.getNext());
         
         return true;
      }
      return false;
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
}

