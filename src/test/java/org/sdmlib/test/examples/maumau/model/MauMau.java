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
import org.sdmlib.test.examples.maumau.model.Player;
import org.sdmlib.test.examples.maumau.model.Suit;
import org.sdmlib.test.examples.maumau.model.util.CardSet;
import org.sdmlib.test.examples.maumau.model.util.PlayerSet;
   /**
    * 
    * @see <a href='../../../../../../../../../src/test/java/org/sdmlib/test/examples/maumau/GenModel.java'>GenModel.java</a>
*/
   @org.sdmlib.replication.ApplicationObject
public class MauMau implements PropertyChangeInterface
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
      setDeck(null);
      setStack(null);
      withoutPlayers(this.getPlayers().toArray(new Player[this.getPlayers().size()]));
      setWinner(null);
      withoutLosers(this.getLosers().toArray(new Player[this.getLosers().size()]));
      setDrawingStack(null);
      setOpenStack(null);
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   
   //==========================================================================
   
   public static final String PROPERTY_CURRENTPLAYER = "currentPlayer";
   
   private Player currentPlayer;

   public Player getCurrentPlayer()
   {
      return this.currentPlayer;
   }
   
   public void setCurrentPlayer(Player value)
   {
      if (this.currentPlayer != value) {
      
         Player oldValue = this.currentPlayer;
         this.currentPlayer = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_CURRENTPLAYER, oldValue, value);
      }
   }
   
   public MauMau withCurrentPlayer(Player value)
   {
      setCurrentPlayer(value);
      return this;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_CURRENTSUIT = "currentSuit";
   
   private Suit currentSuit;

   public Suit getCurrentSuit()
   {
      return this.currentSuit;
   }
   
   public void setCurrentSuit(Suit value)
   {
      if (this.currentSuit != value) {
      
         Suit oldValue = this.currentSuit;
         this.currentSuit = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_CURRENTSUIT, oldValue, value);
      }
   }
   
   public MauMau withCurrentSuit(Suit value)
   {
      setCurrentSuit(value);
      return this;
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
         return CardSet.EMPTY_SET;
      }
   
      return this.cards;
   }

   public MauMau withCards(Card... value)
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
               item.withGame(this);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_CARDS, null, item);
            }
         }
      }
      return this;
   } 

   public MauMau withoutCards(Card... value)
   {
      for (Card item : value)
      {
         if ((this.cards != null) && (item != null))
         {
            if (this.cards.remove(item))
            {
               item.setGame(null);
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

   
   /********************************************************************
    * <pre>
    *              one                       one
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
            oldValue.setStackOwner(null);
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
         return PlayerSet.EMPTY_SET;
      }
   
      return this.players;
   }

   public MauMau withPlayers(Player... value)
   {
      if(value==null){
         return this;
      }
      for (Player item : value)
      {
         if (item != null)
         {
            if (this.players == null)
            {
               this.players = new PlayerSet();
            }
            
            boolean changed = this.players.add (item);

            if (changed)
            {
               item.withGame(this);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_PLAYERS, null, item);
            }
         }
      }
      return this;
   } 

   public MauMau withoutPlayers(Player... value)
   {
      for (Player item : value)
      {
         if ((this.players != null) && (item != null))
         {
            if (this.players.remove(item))
            {
               item.setGame(null);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_PLAYERS, item, null);
            }
         }
      }
      return this;
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
    *              wonGame                   winner
    * </pre>
    */
   
   public static final String PROPERTY_WINNER = "winner";

   private Player winner = null;

   public Player getWinner()
   {
      return this.winner;
   }

   public boolean setWinner(Player value)
   {
      boolean changed = false;
      
      if (this.winner != value)
      {
         Player oldValue = this.winner;
         
         if (this.winner != null)
         {
            this.winner = null;
            oldValue.setWonGame(null);
         }
         
         this.winner = value;
         
         if (value != null)
         {
            value.withWonGame(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_WINNER, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public MauMau withWinner(Player value)
   {
      setWinner(value);
      return this;
   } 

   public Player createWinner()
   {
      Player value = new Player();
      withWinner(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       many
    * MauMau ----------------------------------- Player
    *              lostGame                   losers
    * </pre>
    */
   
   public static final String PROPERTY_LOSERS = "losers";

   private PlayerSet losers = null;
   
   public PlayerSet getLosers()
   {
      if (this.losers == null)
      {
         return PlayerSet.EMPTY_SET;
      }
   
      return this.losers;
   }

   public MauMau withLosers(Player... value)
   {
      if(value==null){
         return this;
      }
      for (Player item : value)
      {
         if (item != null)
         {
            if (this.losers == null)
            {
               this.losers = new PlayerSet();
            }
            
            boolean changed = this.losers.add (item);

            if (changed)
            {
               item.withLostGame(this);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_LOSERS, null, item);
            }
         }
      }
      return this;
   } 

   public MauMau withoutLosers(Player... value)
   {
      for (Player item : value)
      {
         if ((this.losers != null) && (item != null))
         {
            if (this.losers.remove(item))
            {
               item.setLostGame(null);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_LOSERS, item, null);
            }
         }
      }
      return this;
   }

   public Player createLosers()
   {
      Player value = new Player();
      withLosers(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       one
    * MauMau ----------------------------------- DrawingStack
    *              game                   drawingStack
    * </pre>
    */
   
   public static final String PROPERTY_DRAWINGSTACK = "drawingStack";

   private DrawingStack drawingStack = null;

   public DrawingStack getDrawingStack()
   {
      return this.drawingStack;
   }

   public boolean setDrawingStack(DrawingStack value)
   {
      boolean changed = false;
      
      if (this.drawingStack != value)
      {
         DrawingStack oldValue = this.drawingStack;
         
         if (this.drawingStack != null)
         {
            this.drawingStack = null;
            oldValue.setGame(null);
         }
         
         this.drawingStack = value;
         
         if (value != null)
         {
            value.withGame(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_DRAWINGSTACK, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public MauMau withDrawingStack(DrawingStack value)
   {
      setDrawingStack(value);
      return this;
   } 

   public DrawingStack createDrawingStack()
   {
      DrawingStack value = new DrawingStack();
      withDrawingStack(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       one
    * MauMau ----------------------------------- OpenStack
    *              game                   openStack
    * </pre>
    */
   
   public static final String PROPERTY_OPENSTACK = "openStack";

   private OpenStack openStack = null;

   public OpenStack getOpenStack()
   {
      return this.openStack;
   }

   public boolean setOpenStack(OpenStack value)
   {
      boolean changed = false;
      
      if (this.openStack != value)
      {
         OpenStack oldValue = this.openStack;
         
         if (this.openStack != null)
         {
            this.openStack = null;
            oldValue.setGame(null);
         }
         
         this.openStack = value;
         
         if (value != null)
         {
            value.withGame(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_OPENSTACK, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public MauMau withOpenStack(OpenStack value)
   {
      setOpenStack(value);
      return this;
   } 

   public OpenStack createOpenStack()
   {
      OpenStack value = new OpenStack();
      withOpenStack(value);
      return value;
   } 
}
