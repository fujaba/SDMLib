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
import java.util.LinkedHashSet;

import org.sdmlib.examples.ludo.creators.PawnSet;
import org.sdmlib.examples.ludo.creators.PlayerSet;
import org.sdmlib.serialization.json.JsonIdMap;
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

      if (PROPERTY_COLOR.equalsIgnoreCase(attribute))
      {
         return getColor();
      }

      if (PROPERTY_NAME.equalsIgnoreCase(attribute))
      {
         return getName();
      }

      if (PROPERTY_X.equalsIgnoreCase(attribute))
      {
         return getX();
      }

      if (PROPERTY_Y.equalsIgnoreCase(attribute))
      {
         return getY();
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

      if (PROPERTY_DICE.equalsIgnoreCase(attrName))
      {
         return getDice();
      }

      if (PROPERTY_START.equalsIgnoreCase(attrName))
      {
         return getStart();
      }

      if (PROPERTY_BASE.equalsIgnoreCase(attrName))
      {
         return getBase();
      }

      if (PROPERTY_LANDING.equalsIgnoreCase(attrName))
      {
         return getLanding();
      }

      if (PROPERTY_PAWNS.equalsIgnoreCase(attrName))
      {
         return getPawns();
      }

      if (PROPERTY_ENUMCOLOR.equalsIgnoreCase(attribute))
      {
         return getEnumColor();
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

      if (PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         setName((String) value);
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

      if (PROPERTY_GAME.equalsIgnoreCase(attrName))
      {
         setGame((Ludo) value);
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

      if (PROPERTY_DICE.equalsIgnoreCase(attrName))
      {
         setDice((Dice) value);
         return true;
      }

      if (PROPERTY_START.equalsIgnoreCase(attrName))
      {
         setStart((Field) value);
         return true;
      }

      if (PROPERTY_BASE.equalsIgnoreCase(attrName))
      {
         setBase((Field) value);
         return true;
      }

      if (PROPERTY_LANDING.equalsIgnoreCase(attrName))
      {
         setLanding((Field) value);
         return true;
      }

      if (PROPERTY_PAWNS.equalsIgnoreCase(attrName))
      {
         addToPawns((Pawn) value);
         return true;
      }
      
      if ((PROPERTY_PAWNS + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         removeFromPawns((Pawn) value);
         return true;
      }

      if (PROPERTY_ENUMCOLOR.equalsIgnoreCase(attrName))
      {
         setEnumColor((org.sdmlib.examples.ludo.LudoModel.LudoColor) value);
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
      setDice(null);
      setStart(null);
      setBase(null);
      setLanding(null);
      removeAllFromPawns();
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
   
   public Player withColor(String value)
   {
      setColor(value);
      return this;
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
   
   public Player withX(int value)
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
   
   public Player withY(int value)
   {
      setY(value);
      return this;
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
    * Player ----------------------------------- Dice
    *              player                   dice
    * </pre>
    */
   
   public static final String PROPERTY_DICE = "dice";
   
   private Dice dice = null;
   
   public Dice getDice()
   {
      return this.dice;
   }
   
   public boolean setDice(Dice value)
   {
      boolean changed = false;
      
      if (this.dice != value)
      {
         Dice oldValue = this.dice;
         
         if (this.dice != null)
         {
            this.dice = null;
            oldValue.setPlayer(null);
         }
         
         this.dice = value;
         
         if (value != null)
         {
            value.withPlayer(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_DICE, oldValue, value);
         changed = true;
      }
      
      return changed;
   }
   
   public Player withDice(Dice value)
   {
      setDice(value);
      return this;
   } 
   
   public Dice createDice()
   {
      Dice value = new Dice();
      withDice(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       one
    * Player ----------------------------------- Field
    *              starter                   start
    * </pre>
    */
   
   public static final String PROPERTY_START = "start";
   
   private Field start = null;
   
   public Field getStart()
   {
      return this.start;
   }
   
   public boolean setStart(Field value)
   {
      boolean changed = false;
      
      if (this.start != value)
      {
         Field oldValue = this.start;
         
         if (this.start != null)
         {
            this.start = null;
            oldValue.setStarter(null);
         }
         
         this.start = value;
         
         if (value != null)
         {
            value.withStarter(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_START, oldValue, value);
         changed = true;
      }
      
      return changed;
   }
   
   public Player withStart(Field value)
   {
      setStart(value);
      return this;
   } 
   
   public Field createStart()
   {
      Field value = new Field();
      withStart(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       one
    * Player ----------------------------------- Field
    *              baseowner                   base
    * </pre>
    */
   
   public static final String PROPERTY_BASE = "base";
   
   private Field base = null;
   
   public Field getBase()
   {
      return this.base;
   }
   
   public boolean setBase(Field value)
   {
      boolean changed = false;
      
      if (this.base != value)
      {
         Field oldValue = this.base;
         
         if (this.base != null)
         {
            this.base = null;
            oldValue.setBaseowner(null);
         }
         
         this.base = value;
         
         if (value != null)
         {
            value.withBaseowner(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_BASE, oldValue, value);
         changed = true;
      }
      
      return changed;
   }
   
   public Player withBase(Field value)
   {
      setBase(value);
      return this;
   } 
   
   public Field createBase()
   {
      Field value = new Field();
      withBase(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       one
    * Player ----------------------------------- Field
    *              lander                   landing
    * </pre>
    */
   
   public static final String PROPERTY_LANDING = "landing";
   
   private Field landing = null;
   
   public Field getLanding()
   {
      return this.landing;
   }
   
   public boolean setLanding(Field value)
   {
      boolean changed = false;
      
      if (this.landing != value)
      {
         Field oldValue = this.landing;
         
         if (this.landing != null)
         {
            this.landing = null;
            oldValue.setLander(null);
         }
         
         this.landing = value;
         
         if (value != null)
         {
            value.withLander(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_LANDING, oldValue, value);
         changed = true;
      }
      
      return changed;
   }
   
   public Player withLanding(Field value)
   {
      setLanding(value);
      return this;
   } 
   
   public Field createLanding()
   {
      Field value = new Field();
      withLanding(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       many
    * Player ----------------------------------- Pawn
    *              player                   pawns
    * </pre>
    */
   
   public static final String PROPERTY_PAWNS = "pawns";
   
   private PawnSet pawns = null;
   
   public PawnSet getPawns()
   {
      if (this.pawns == null)
      {
         return Pawn.EMPTY_SET;
      }
   
      return this.pawns;
   }
   
   public boolean addToPawns(Pawn value)
   {
      boolean changed = false;
      
      if (value != null)
      {
         if (this.pawns == null)
         {
            this.pawns = new PawnSet();
         }
         
         changed = this.pawns.add (value);
         
         if (changed)
         {
            value.withPlayer(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_PAWNS, null, value);
         }
      }
         
      return changed;   
   }
   
   public boolean removeFromPawns(Pawn value)
   {
      boolean changed = false;
      
      if ((this.pawns != null) && (value != null))
      {
         changed = this.pawns.remove (value);
         
         if (changed)
         {
            value.setPlayer(null);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_PAWNS, value, null);
         }
      }
         
      return changed;   
   }
   
   public Player withPawns(Pawn value)
   {
      addToPawns(value);
      return this;
   } 
   
   public Player withoutPawns(Pawn value)
   {
      removeFromPawns(value);
      return this;
   } 
   
   public void removeAllFromPawns()
   {
      LinkedHashSet<Pawn> tmpSet = new LinkedHashSet<Pawn>(this.getPawns());
   
      for (Pawn value : tmpSet)
      {
         this.removeFromPawns(value);
      }
   }
   
   public Pawn createPawns()
   {
      Pawn value = new Pawn();
      withPawns(value);
      return value;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_ENUMCOLOR = "enumColor";
   
   private org.sdmlib.examples.ludo.LudoModel.LudoColor enumColor;

   public org.sdmlib.examples.ludo.LudoModel.LudoColor getEnumColor()
   {
      return this.enumColor;
   }
   
   public void setEnumColor(org.sdmlib.examples.ludo.LudoModel.LudoColor value)
   {
      if (this.enumColor != value)
      {
         org.sdmlib.examples.ludo.LudoModel.LudoColor oldValue = this.enumColor;
         this.enumColor = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_ENUMCOLOR, oldValue, value);
      }
   }
   
   public Player withEnumColor(org.sdmlib.examples.ludo.LudoModel.LudoColor value)
   {
      setEnumColor(value);
      return this;
   } 
}

