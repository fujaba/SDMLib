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
   
package org.sdmlib.examples.ludo;

import org.sdmlib.utils.PropertyChangeInterface;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;
import org.sdmlib.utils.StrUtil;
import org.sdmlib.examples.ludo.creators.FieldSet;
import org.sdmlib.examples.ludo.creators.PawnSet;
import java.util.LinkedHashSet;
import org.sdmlib.serialization.json.JsonIdMap;

public class Field implements PropertyChangeInterface
{

   
   //==========================================================================
   
   public Object get(String attrName)
   {
      if (PROPERTY_COLOR.equalsIgnoreCase(attrName))
      {
         return getColor();
      }

      if (PROPERTY_KIND.equalsIgnoreCase(attrName))
      {
         return getKind();
      }

      if (PROPERTY_X.equalsIgnoreCase(attrName))
      {
         return getX();
      }

      if (PROPERTY_Y.equalsIgnoreCase(attrName))
      {
         return getY();
      }

      if (PROPERTY_POINT.equalsIgnoreCase(attrName))
      {
         return getPoint();
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

      if (PROPERTY_LANDING.equalsIgnoreCase(attrName))
      {
         return getLanding();
      }

      if (PROPERTY_ENTRY.equalsIgnoreCase(attrName))
      {
         return getEntry();
      }

      if (PROPERTY_STARTER.equalsIgnoreCase(attrName))
      {
         return getStarter();
      }

      if (PROPERTY_BASEOWNER.equalsIgnoreCase(attrName))
      {
         return getBaseowner();
      }

      if (PROPERTY_LANDER.equalsIgnoreCase(attrName))
      {
         return getLander();
      }

      if (PROPERTY_PAWNS.equalsIgnoreCase(attrName))
      {
         return getPawns();
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

      if (PROPERTY_KIND.equalsIgnoreCase(attrName))
      {
         setKind((String) value);
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

      if (PROPERTY_POINT.equalsIgnoreCase(attrName))
      {
         setPoint((java.awt.Point) value);
         return true;
      }

      if (PROPERTY_GAME.equalsIgnoreCase(attrName))
      {
         setGame((Ludo) value);
         return true;
      }

      if (PROPERTY_NEXT.equalsIgnoreCase(attrName))
      {
         setNext((Field) value);
         return true;
      }

      if (PROPERTY_PREV.equalsIgnoreCase(attrName))
      {
         setPrev((Field) value);
         return true;
      }

      if (PROPERTY_LANDING.equalsIgnoreCase(attrName))
      {
         setLanding((Field) value);
         return true;
      }

      if (PROPERTY_ENTRY.equalsIgnoreCase(attrName))
      {
         setEntry((Field) value);
         return true;
      }

      if (PROPERTY_STARTER.equalsIgnoreCase(attrName))
      {
         setStarter((Player) value);
         return true;
      }

      if (PROPERTY_BASEOWNER.equalsIgnoreCase(attrName))
      {
         setBaseowner((Player) value);
         return true;
      }

      if (PROPERTY_LANDER.equalsIgnoreCase(attrName))
      {
         setLander((Player) value);
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

      return false;
   }

   
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
      setGame(null);
      setNext(null);
      setPrev(null);
      setLanding(null);
      setEntry(null);
      setStarter(null);
      setBaseowner(null);
      setLander(null);
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
   
   public Field withColor(String value)
   {
      setColor(value);
      return this;
   } 

   public String toString()
   {
      StringBuilder _ = new StringBuilder();
      
      _.append(" ").append(this.getColor());
      _.append(" ").append(this.getKind());
      _.append(" ").append(this.getX());
      _.append(" ").append(this.getY());
      return _.substring(1);
   }


   
   //==========================================================================
   
   public static final String PROPERTY_KIND = "kind";
   
   private String kind;

   public String getKind()
   {
      return this.kind;
   }
   
   public void setKind(String value)
   {
      if ( ! StrUtil.stringEquals(this.kind, value))
      {
         String oldValue = this.kind;
         this.kind = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_KIND, oldValue, value);
      }
   }
   
   public Field withKind(String value)
   {
      setKind(value);
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
   
   public Field withX(int value)
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
   
   public Field withY(int value)
   {
      setY(value);
      return this;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_POINT = "point";
   
   private java.awt.Point point;

   public java.awt.Point getPoint()
   {
      return this.point;
   }
   
   public void setPoint(java.awt.Point value)
   {
      if (this.point != value)
      {
         java.awt.Point oldValue = this.point;
         this.point = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_POINT, oldValue, value);
      }
   }
   
   public Field withPoint(java.awt.Point value)
   {
      setPoint(value);
      return this;
   } 

   
   public static final FieldSet EMPTY_SET = new FieldSet();

   
   /********************************************************************
    * <pre>
    *              many                       one
    * Field ----------------------------------- Ludo
    *              fields                   game
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
            oldValue.withoutFields(this);
         }
         
         this.game = value;
         
         if (value != null)
         {
            value.withFields(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_GAME, oldValue, value);
         changed = true;
      }
      
      return changed;
   }
   
   public Field withGame(Ludo value)
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
    * Field ----------------------------------- Field
    *              prev                   next
    * </pre>
    */
   
   public static final String PROPERTY_NEXT = "next";
   
   private Field next = null;
   
   public Field getNext()
   {
      return this.next;
   }
   
   public boolean setNext(Field value)
   {
      boolean changed = false;
      
      if (this.next != value)
      {
         Field oldValue = this.next;
         
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
   
   public Field withNext(Field value)
   {
      setNext(value);
      return this;
   } 
   
   public Field createNext()
   {
      Field value = new Field();
      withNext(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       one
    * Field ----------------------------------- Field
    *              next                   prev
    * </pre>
    */
   
   public static final String PROPERTY_PREV = "prev";
   
   private Field prev = null;
   
   public Field getPrev()
   {
      return this.prev;
   }
   
   public boolean setPrev(Field value)
   {
      boolean changed = false;
      
      if (this.prev != value)
      {
         Field oldValue = this.prev;
         
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
   
   public Field withPrev(Field value)
   {
      setPrev(value);
      return this;
   } 
   
   public Field createPrev()
   {
      Field value = new Field();
      withPrev(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       one
    * Field ----------------------------------- Field
    *              entry                   landing
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
            oldValue.setEntry(null);
         }
         
         this.landing = value;
         
         if (value != null)
         {
            value.withEntry(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_LANDING, oldValue, value);
         changed = true;
      }
      
      return changed;
   }
   
   public Field withLanding(Field value)
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
    *              one                       one
    * Field ----------------------------------- Field
    *              landing                   entry
    * </pre>
    */
   
   public static final String PROPERTY_ENTRY = "entry";
   
   private Field entry = null;
   
   public Field getEntry()
   {
      return this.entry;
   }
   
   public boolean setEntry(Field value)
   {
      boolean changed = false;
      
      if (this.entry != value)
      {
         Field oldValue = this.entry;
         
         if (this.entry != null)
         {
            this.entry = null;
            oldValue.setLanding(null);
         }
         
         this.entry = value;
         
         if (value != null)
         {
            value.withLanding(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_ENTRY, oldValue, value);
         changed = true;
      }
      
      return changed;
   }
   
   public Field withEntry(Field value)
   {
      setEntry(value);
      return this;
   } 
   
   public Field createEntry()
   {
      Field value = new Field();
      withEntry(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       one
    * Field ----------------------------------- Player
    *              start                   starter
    * </pre>
    */
   
   public static final String PROPERTY_STARTER = "starter";
   
   private Player starter = null;
   
   public Player getStarter()
   {
      return this.starter;
   }
   
   public boolean setStarter(Player value)
   {
      boolean changed = false;
      
      if (this.starter != value)
      {
         Player oldValue = this.starter;
         
         if (this.starter != null)
         {
            this.starter = null;
            oldValue.setStart(null);
         }
         
         this.starter = value;
         
         if (value != null)
         {
            value.withStart(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_STARTER, oldValue, value);
         changed = true;
      }
      
      return changed;
   }
   
   public Field withStarter(Player value)
   {
      setStarter(value);
      return this;
   } 
   
   public Player createStarter()
   {
      Player value = new Player();
      withStarter(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       one
    * Field ----------------------------------- Player
    *              base                   baseowner
    * </pre>
    */
   
   public static final String PROPERTY_BASEOWNER = "baseowner";
   
   private Player baseowner = null;
   
   public Player getBaseowner()
   {
      return this.baseowner;
   }
   
   public boolean setBaseowner(Player value)
   {
      boolean changed = false;
      
      if (this.baseowner != value)
      {
         Player oldValue = this.baseowner;
         
         if (this.baseowner != null)
         {
            this.baseowner = null;
            oldValue.setBase(null);
         }
         
         this.baseowner = value;
         
         if (value != null)
         {
            value.withBase(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_BASEOWNER, oldValue, value);
         changed = true;
      }
      
      return changed;
   }
   
   public Field withBaseowner(Player value)
   {
      setBaseowner(value);
      return this;
   } 
   
   public Player createBaseowner()
   {
      Player value = new Player();
      withBaseowner(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       one
    * Field ----------------------------------- Player
    *              landing                   lander
    * </pre>
    */
   
   public static final String PROPERTY_LANDER = "lander";
   
   private Player lander = null;
   
   public Player getLander()
   {
      return this.lander;
   }
   
   public boolean setLander(Player value)
   {
      boolean changed = false;
      
      if (this.lander != value)
      {
         Player oldValue = this.lander;
         
         if (this.lander != null)
         {
            this.lander = null;
            oldValue.setLanding(null);
         }
         
         this.lander = value;
         
         if (value != null)
         {
            value.withLanding(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_LANDER, oldValue, value);
         changed = true;
      }
      
      return changed;
   }
   
   public Field withLander(Player value)
   {
      setLander(value);
      return this;
   } 
   
   public Player createLander()
   {
      Player value = new Player();
      withLander(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       many
    * Field ----------------------------------- Pawn
    *              pos                   pawns
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
            value.withPos(this);
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
            value.setPos(null);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_PAWNS, value, null);
         }
      }
         
      return changed;   
   }
   
   public Field withPawns(Pawn value)
   {
      addToPawns(value);
      return this;
   } 
   
   public Field withoutPawns(Pawn value)
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
}

