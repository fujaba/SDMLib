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
import org.sdmlib.utils.StrUtil;
import org.sdmlib.examples.replication.maumau.creators.PlayerSet;
import org.sdmlib.examples.replication.maumau.creators.CardSet;
import java.util.LinkedHashSet;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.examples.replication.maumau.Holder;

public class Player extends Holder implements PropertyChangeInterface
{

   
   //==========================================================================
   
   public Object get(String attrName)
   {
      if (PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         return getName();
      }

      if (PROPERTY_GAME.equalsIgnoreCase(attrName))
      {
         return getGame();
      }

      if (PROPERTY_NEXT.equalsIgnoreCase(attrName))
      {
         return getNext();
      }

      if (PROPERTY_PREV.equalsIgnoreCase(attrName))
      {
         return getPrev();
      }

      if (PROPERTY_ASSIGNMENT.equalsIgnoreCase(attrName))
      {
         return getAssignment();
      }

      if (PROPERTY_DECKOWNER.equalsIgnoreCase(attrName))
      {
         return getDeckOwner();
      }

      if (PROPERTY_CARDS.equalsIgnoreCase(attrName))
      {
         return getCards();
      }

      if (PROPERTY_STACKOWNER.equalsIgnoreCase(attrName))
      {
         return getStackOwner();
      }

      return null;
   }

   
   //==========================================================================
   
   public boolean set(String attrName, Object value)
   {
      if (PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         setName((String) value);
         return true;
      }

      if (PROPERTY_GAME.equalsIgnoreCase(attrName))
      {
         setGame((MauMau) value);
         return true;
      }

      if (PROPERTY_NEXT.equalsIgnoreCase(attrName))
      {
         setNext((Player) value);
         return true;
      }

      if (PROPERTY_PREV.equalsIgnoreCase(attrName))
      {
         setPrev((Player) value);
         return true;
      }

      if (PROPERTY_ASSIGNMENT.equalsIgnoreCase(attrName))
      {
         setAssignment((MauMau) value);
         return true;
      }

      if (PROPERTY_DECKOWNER.equalsIgnoreCase(attrName))
      {
         setDeckOwner((MauMau) value);
         return true;
      }

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

      if (PROPERTY_STACKOWNER.equalsIgnoreCase(attrName))
      {
         addToStackOwner((MauMau) value);
         return true;
      }
      
      if ((PROPERTY_STACKOWNER + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         removeFromStackOwner((MauMau) value);
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
      setNext(null);
      setPrev(null);
      setAssignment(null);
      setDeckOwner(null);
      removeAllFromCards();
      removeAllFromStackOwner();
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

   public String toString()
   {
      StringBuilder _ = new StringBuilder();
      
      _.append(" ").append(this.getName());
      return _.substring(1);
   }


   
   public static final PlayerSet EMPTY_SET = new PlayerSet();

   
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
    *              one                       one
    * Player ----------------------------------- MauMau
    *              currentMove                   assignment
    * </pre>
    */
   
   public static final String PROPERTY_ASSIGNMENT = "assignment";
   
   private MauMau assignment = null;
   
   public MauMau getAssignment()
   {
      return this.assignment;
   }
   
   public boolean setAssignment(MauMau value)
   {
      boolean changed = false;
      
      if (this.assignment != value)
      {
         MauMau oldValue = this.assignment;
         
         if (this.assignment != null)
         {
            this.assignment = null;
            oldValue.setCurrentMove(null);
         }
         
         this.assignment = value;
         
         if (value != null)
         {
            value.withCurrentMove(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_ASSIGNMENT, oldValue, value);
         changed = true;
      }
      
      return changed;
   }
   
   public Player withAssignment(MauMau value)
   {
      setAssignment(value);
      return this;
   } 
   
   public MauMau createAssignment()
   {
      MauMau value = new MauMau();
      withAssignment(value);
      return value;
   } 
}

