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

import org.sdmlib.StrUtil;
import org.sdmlib.replication.Lane;
import org.sdmlib.test.examples.maumau.model.util.DutySet;
import org.sdmlib.test.examples.maumau.model.util.PlayerSet;
import org.sdmlib.test.examples.maumau.model.Card;
import org.sdmlib.test.examples.maumau.model.MauMau;
import org.sdmlib.test.examples.maumau.model.Duty;
   /**
    * 
    * @see <a href='../../../../../../../../../src/test/java/org/sdmlib/test/examples/maumau/GenModel.java'>GenModel.java</a>
* @see org.sdmlib.test.examples.maumau.GenModel#genModel
 */
   public  class Player extends Holder
{

   
   //==========================================================================
   
   @Override
   public void removeYou()
   {
   
      super.removeYou();

      withoutCards(this.getCards().toArray(new Card[this.getCards().size()]));
      setDeckOwner(null);
      setStackOwner(null);
      setGame(null);
      setWonGame(null);
      setLostGame(null);
      setNext(null);
      setPrev(null);
      withoutDuty(this.getDuty().toArray(new Duty[this.getDuty().size()]));
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
      if ( ! StrUtil.stringEquals(this.name, value)) {
      
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
      StringBuilder result = new StringBuilder();
      
      result.append(" ").append(this.getName());
      return result.substring(1);
   }


   
   //==========================================================================
   
   public static final String PROPERTY_LANE = "lane";
   
   private Lane lane;

   public Lane getLane()
   {
      return this.lane;
   }
   
   public void setLane(Lane value)
   {
      if (this.lane != value) {
      
         Lane oldValue = this.lane;
         this.lane = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_LANE, oldValue, value);
      }
   }
   
   public Player withLane(Lane value)
   {
      setLane(value);
      return this;
   } 

   
   /********************************************************************
    * <pre>
    *              many                       one
    * Player ----------------------------------- MauMau
    *              players                   game
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

   public Player withGame(MauMau value)
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
    *              one                       one
    * Player ----------------------------------- MauMau
    *              winner                   wonGame
    * </pre>
    */
   
   public static final String PROPERTY_WONGAME = "wonGame";

   private MauMau wonGame = null;

   public MauMau getWonGame()
   {
      return this.wonGame;
   }

   public boolean setWonGame(MauMau value)
   {
      boolean changed = false;
      
      if (this.wonGame != value)
      {
         MauMau oldValue = this.wonGame;
         
         if (this.wonGame != null)
         {
            this.wonGame = null;
            oldValue.setWinner(null);
         }
         
         this.wonGame = value;
         
         if (value != null)
         {
            value.withWinner(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_WONGAME, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Player withWonGame(MauMau value)
   {
      setWonGame(value);
      return this;
   } 

   public MauMau createWonGame()
   {
      MauMau value = new MauMau();
      withWonGame(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              many                       one
    * Player ----------------------------------- MauMau
    *              losers                   lostGame
    * </pre>
    */
   
   public static final String PROPERTY_LOSTGAME = "lostGame";

   private MauMau lostGame = null;

   public MauMau getLostGame()
   {
      return this.lostGame;
   }

   public boolean setLostGame(MauMau value)
   {
      boolean changed = false;
      
      if (this.lostGame != value)
      {
         MauMau oldValue = this.lostGame;
         
         if (this.lostGame != null)
         {
            this.lostGame = null;
            oldValue.withoutLosers(this);
         }
         
         this.lostGame = value;
         
         if (value != null)
         {
            value.withLosers(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_LOSTGAME, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Player withLostGame(MauMau value)
   {
      setLostGame(value);
      return this;
   } 

   public MauMau createLostGame()
   {
      MauMau value = new MauMau();
      withLostGame(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       one
    * Player ----------------------------------- Player
    *              prev                   next
    * </pre>
    */
   
   public static final String PROPERTY_NEXT = "next";

   private Player next = null;

   public Player getNext()
   {
      return this.next;
   }
   public PlayerSet getNextTransitive()
   {
      PlayerSet result = new PlayerSet().with(this);
      return result.getNextTransitive();
   }


   public boolean setNext(Player value)
   {
      boolean changed = false;
      
      if (this.next != value)
      {
         Player oldValue = this.next;
         
         if (this.next != null)
         {
            this.next = null;
            oldValue.setPrev(null);
         }
         
         this.next = value;
         
         if (value != null)
         {
            value.withPrev(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_NEXT, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Player withNext(Player value)
   {
      setNext(value);
      return this;
   } 

   public Player createNext()
   {
      Player value = new Player();
      withNext(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       one
    * Player ----------------------------------- Player
    *              next                   prev
    * </pre>
    */
   
   public static final String PROPERTY_PREV = "prev";

   private Player prev = null;

   public Player getPrev()
   {
      return this.prev;
   }
   public PlayerSet getPrevTransitive()
   {
      PlayerSet result = new PlayerSet().with(this);
      return result.getPrevTransitive();
   }


   public boolean setPrev(Player value)
   {
      boolean changed = false;
      
      if (this.prev != value)
      {
         Player oldValue = this.prev;
         
         if (this.prev != null)
         {
            this.prev = null;
            oldValue.setNext(null);
         }
         
         this.prev = value;
         
         if (value != null)
         {
            value.withNext(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_PREV, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Player withPrev(Player value)
   {
      setPrev(value);
      return this;
   } 

   public Player createPrev()
   {
      Player value = new Player();
      withPrev(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       many
    * Player ----------------------------------- Duty
    *              player                   duty
    * </pre>
    */
   
   public static final String PROPERTY_DUTY = "duty";

   private DutySet duty = null;
   
   public DutySet getDuty()
   {
      if (this.duty == null)
      {
         return DutySet.EMPTY_SET;
      }
   
      return this.duty;
   }

   public Player withDuty(Duty... value)
   {
      if(value==null){
         return this;
      }
      for (Duty item : value)
      {
         if (item != null)
         {
            if (this.duty == null)
            {
               this.duty = new DutySet();
            }
            
            boolean changed = this.duty.add (item);

            if (changed)
            {
               item.withPlayer(this);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_DUTY, null, item);
            }
         }
      }
      return this;
   } 

   public Player withoutDuty(Duty... value)
   {
      for (Duty item : value)
      {
         if ((this.duty != null) && (item != null))
         {
            if (this.duty.remove(item))
            {
               item.setPlayer(null);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_DUTY, item, null);
            }
         }
      }
      return this;
   }

   public Duty createDuty()
   {
      Duty value = new Duty();
      withDuty(value);
      return value;
   } 
}
