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

import java.util.Collections;
import java.util.LinkedHashSet;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.examples.replication.maumau.creators.PlayerSet;
import org.sdmlib.examples.replication.maumau.creators.HolderSet;
import org.sdmlib.examples.replication.maumau.creators.MauMauSet;

public class MauMau implements PropertyChangeInterface
{

   
   //==========================================================================
   
   public Object get(String attrName)
   {
      if (PROPERTY_CARDS.equalsIgnoreCase(attrName))
      {
         return getCards();
      }

      if (PROPERTY_PLAYERS.equalsIgnoreCase(attrName))
      {
         return getPlayers();
      }

      if (PROPERTY_CURRENTMOVE.equalsIgnoreCase(attrName))
      {
         return getCurrentMove();
      }

      if (PROPERTY_DECK.equalsIgnoreCase(attrName))
      {
         return getDeck();
      }

      if (PROPERTY_STACK.equalsIgnoreCase(attrName))
      {
         return getStack();
      }

      return null;
   }

   
   //==========================================================================
   
   public boolean set(String attrName, Object value)
   {
      if (PROPERTY_CARDS.equalsIgnoreCase(attrName))
      {
         addToCards((Card) value);
         return true;
      }
      
      if ((PROPERTY_CARDS + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         removeFromCards((Card) value);
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

      if (PROPERTY_CURRENTMOVE.equalsIgnoreCase(attrName))
      {
         setCurrentMove((Player) value);
         return true;
      }

      if (PROPERTY_DECK.equalsIgnoreCase(attrName))
      {
         setDeck((Holder) value);
         return true;
      }

      if (PROPERTY_STACK.equalsIgnoreCase(attrName))
      {
         setStack((Holder) value);
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
      removeAllFromCards();
      removeAllFromPlayers();
      setCurrentMove(null);
      setDeck(null);
      setStack(null);
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   
   /********************************************************************
    * <pre>
    *              one                       many
    * MauMau ----------------------------------- Card
    *              game                   cards
    * </pre>
    */
   
   public static final String PROPERTY_CARDS = "cards";
   
   private CardSet cards = null;
   
   public CardSet getCards()
   {
      if (this.cards == null)
      {
         return Card.EMPTY_SET;
      }
   
      return this.cards;
   }
   
   public boolean addToCards(Card value)
   {
      boolean changed = false;
      
      if (value != null)
      {
         if (this.cards == null)
         {
            this.cards = new CardSet();
         }
         
         if ( ! this.cards.contains(value))
         {
            changed = this.cards.add (value);
            
            value.withGame(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_CARDS, null, value);
         }
      }
         
      return changed;   
   }
   
   public boolean removeFromCards(Card value)
   {
      boolean changed = false;
      
      if ((this.cards != null) && (value != null))
      {
         changed = this.cards.remove (value);
         
         if (changed)
         {
            value.setGame(null);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_CARDS, value, null);
         }
      }
         
      return changed;   
   }
   
   public MauMau withCards(Card value)
   {
      addToCards(value);
      return this;
   } 
   
   public MauMau withoutCards(Card value)
   {
      removeFromCards(value);
      return this;
   } 
   
   public void removeAllFromCards()
   {
      LinkedHashSet<Card> tmpSet = new LinkedHashSet<Card>(this.getCards());
   
      for (Card value : tmpSet)
      {
         this.removeFromCards(value);
      }
   }
   
   public Card createCards()
   {
      Card value = new Card();
      withCards(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       many
    * MauMau ----------------------------------- Player
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
   
   public MauMau withPlayers(Player value)
   {
      addToPlayers(value);
      return this;
   } 
   
   public MauMau withoutPlayers(Player value)
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
    * MauMau ----------------------------------- Player
    *              assignment                   currentMove
    * </pre>
    */
   
   public static final String PROPERTY_CURRENTMOVE = "currentMove";
   
   private Player currentMove = null;
   
   public Player getCurrentMove()
   {
      return this.currentMove;
   }
   
   public boolean setCurrentMove(Player value)
   {
      boolean changed = false;
      
      if (this.currentMove != value)
      {
         Player oldValue = this.currentMove;
         
         if (this.currentMove != null)
         {
            this.currentMove = null;
            oldValue.setAssignment(null);
         }
         
         this.currentMove = value;
         
         if (value != null)
         {
            value.withAssignment(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_CURRENTMOVE, oldValue, value);
         changed = true;
      }
      
      return changed;
   }
   
   public MauMau withCurrentMove(Player value)
   {
      setCurrentMove(value);
      return this;
   } 
   
   public Player createCurrentMove()
   {
      Player value = new Player();
      withCurrentMove(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       one
    * MauMau ----------------------------------- Holder
    *              deckOwner                   deck
    * </pre>
    */
   
   public static final String PROPERTY_DECK = "deck";
   
   private Holder deck = null;
   
   public Holder getDeck()
   {
      return this.deck;
   }
   
   public boolean setDeck(Holder value)
   {
      boolean changed = false;
      
      if (this.deck != value)
      {
         Holder oldValue = this.deck;
         
         if (this.deck != null)
         {
            this.deck = null;
            oldValue.setDeckOwner(null);
         }
         
         this.deck = value;
         
         if (value != null)
         {
            value.withDeckOwner(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_DECK, oldValue, value);
         changed = true;
      }
      
      return changed;
   }
   
   public MauMau withDeck(Holder value)
   {
      setDeck(value);
      return this;
   } 
   
   public Holder createDeck()
   {
      Holder value = new Holder();
      withDeck(value);
      return value;
   } 

   
   public static final MauMauSet EMPTY_SET = new MauMauSet();

   
   /********************************************************************
    * <pre>
    *              many                       one
    * MauMau ----------------------------------- Holder
    *              stackOwner                   stack
    * </pre>
    */
   
   public static final String PROPERTY_STACK = "stack";
   
   private Holder stack = null;
   
   public Holder getStack()
   {
      return this.stack;
   }
   
   public boolean setStack(Holder value)
   {
      boolean changed = false;
      
      if (this.stack != value)
      {
         Holder oldValue = this.stack;
         
         if (this.stack != null)
         {
            this.stack = null;
            oldValue.withoutStackOwner(this);
         }
         
         this.stack = value;
         
         if (value != null)
         {
            value.withStackOwner(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_STACK, oldValue, value);
         changed = true;
      }
      
      return changed;
   }
   
   public MauMau withStack(Holder value)
   {
      setStack(value);
      return this;
   } 
   
   public Holder createStack()
   {
      Holder value = new Holder();
      withStack(value);
      return value;
   }


   public void shuffleStackToDeck()
   {
      // put top card from stack aside. 
      Card lastCard = getStack().getCards().getLast();
      
      lastCard.setHolder(null);
      
      CardSet shuffle = new CardSet();
      
      shuffle.addAll(getStack().getCards());
      
      Collections.shuffle(shuffle);
      
      for (Card c : shuffle)
      {
         getDeck().addToCards(c);
      }
      
      getStack().addToCards(lastCard);
   } 
}

