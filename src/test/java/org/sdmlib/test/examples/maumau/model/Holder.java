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

import org.sdmlib.serialization.PropertyChangeInterface;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;
import org.sdmlib.test.examples.maumau.model.util.CardSet;
   /**
    * 
    * @see <a href='../../../../../../../../../src/test/java/org/sdmlib/test/examples/maumau/GenModel.java'>GenModel.java</a>
*/
   public  class Holder implements PropertyChangeInterface
{

   
   //==========================================================================
   
   protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);
   
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
   
      withoutCards(this.getCards().toArray(new Card[this.getCards().size()]));
      setDeckOwner(null);
      setStackOwner(null);
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   
   /********************************************************************
    * <pre>
    *              one                       many
    * Holder ----------------------------------- Card
    *              holder                   cards
    * </pre>
    */
   
   public static final String PROPERTY_CARDS = "cards";

   private CardSet cards = null;
   
   public CardSet getCards()
   {
      if (this.cards == null)
      {
         return CardSet.EMPTY_SET;
      }
   
      return this.cards;
   }

   public Holder withCards(Card... value)
   {
      if(value==null){
         return this;
      }
      for (Card item : value)
      {
         if (item != null)
         {
            if (this.cards == null)
            {
               this.cards = new CardSet();
            }
            
            boolean changed = this.cards.add (item);

            if (changed)
            {
               item.withHolder(this);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_CARDS, null, item);
            }
         }
      }
      return this;
   } 

   public Holder withoutCards(Card... value)
   {
      for (Card item : value)
      {
         if ((this.cards != null) && (item != null))
         {
            if (this.cards.remove(item))
            {
               item.setHolder(null);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_CARDS, item, null);
            }
         }
      }
      return this;
   }

   public Card createCards()
   {
      Card value = new Card();
      withCards(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       one
    * Holder ----------------------------------- MauMau
    *              deck                   deckOwner
    * </pre>
    */
   
   public static final String PROPERTY_DECKOWNER = "deckOwner";

   private MauMau deckOwner = null;

   public MauMau getDeckOwner()
   {
      return this.deckOwner;
   }

   public boolean setDeckOwner(MauMau value)
   {
      boolean changed = false;
      
      if (this.deckOwner != value)
      {
         MauMau oldValue = this.deckOwner;
         
         if (this.deckOwner != null)
         {
            this.deckOwner = null;
            oldValue.setDeck(null);
         }
         
         this.deckOwner = value;
         
         if (value != null)
         {
            value.withDeck(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_DECKOWNER, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Holder withDeckOwner(MauMau value)
   {
      setDeckOwner(value);
      return this;
   } 

   public MauMau createDeckOwner()
   {
      MauMau value = new MauMau();
      withDeckOwner(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       one
    * Holder ----------------------------------- MauMau
    *              stack                   stackOwner
    * </pre>
    */
   
   public static final String PROPERTY_STACKOWNER = "stackOwner";

   private MauMau stackOwner = null;

   public MauMau getStackOwner()
   {
      return this.stackOwner;
   }

   public boolean setStackOwner(MauMau value)
   {
      boolean changed = false;
      
      if (this.stackOwner != value)
      {
         MauMau oldValue = this.stackOwner;
         
         if (this.stackOwner != null)
         {
            this.stackOwner = null;
            oldValue.setStack(null);
         }
         
         this.stackOwner = value;
         
         if (value != null)
         {
            value.withStack(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_STACKOWNER, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Holder withStackOwner(MauMau value)
   {
      setStackOwner(value);
      return this;
   } 

   public MauMau createStackOwner()
   {
      MauMau value = new MauMau();
      withStackOwner(value);
      return value;
   } 
}
