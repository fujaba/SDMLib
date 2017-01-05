/*
   Copyright (c) 2014 zuendorf 
   
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
   
package org.sdmlib.test.examples.ludo.model.util;

import java.util.Collection;
import java.util.Collections;

import org.sdmlib.models.modelsets.intList;
import org.sdmlib.test.examples.ludo.LudoModel.LudoColor;
import org.sdmlib.test.examples.ludo.model.Dice;
import org.sdmlib.test.examples.ludo.model.Field;
import org.sdmlib.test.examples.ludo.model.Ludo;
import org.sdmlib.test.examples.ludo.model.Pawn;
import org.sdmlib.test.examples.ludo.model.Player;

import de.uniks.networkparser.interfaces.Condition;
import de.uniks.networkparser.list.ObjectSet;
import de.uniks.networkparser.list.SimpleSet;
import de.uniks.networkparser.list.StringList;

public class PlayerSet extends SimpleSet<Player>
{


   public PlayerPO hasPlayerPO()
   {
      return new PlayerPO(this.toArray(new Player[this.size()]));
   }

   @SuppressWarnings("unchecked")
   public PlayerSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Player>)value);
      }
      else if (value != null)
      {
         this.add((Player) value);
      }
      
      return this;
   }
   
   public PlayerSet without(Player value)
   {
      this.remove(value);
      return this;
   }

   public StringList getColor()
   {
      StringList result = new StringList();
      
      for (Player obj : this)
      {
         result.add(obj.getColor());
      }
      
      return result;
   }

   public PlayerSet hasColor(String value)
   {
      PlayerSet result = new PlayerSet();
      
      for (Player obj : this)
      {
         if (value.equals(obj.getColor()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public PlayerSet withColor(String value)
   {
      for (Player obj : this)
      {
         obj.setColor(value);
      }
      
      return this;
   }

   public LudoColorSet getEnumColor()
   {
      LudoColorSet result = new LudoColorSet();
      
      for (Player obj : this)
      {
         result.add(obj.getEnumColor());
      }
      
      return result;
   }

   public PlayerSet withEnumColor(LudoColor value)
   {
      for (Player obj : this)
      {
         obj.setEnumColor(value);
      }
      
      return this;
   }

   public StringList getName()
   {
      StringList result = new StringList();
      
      for (Player obj : this)
      {
         result.add(obj.getName());
      }
      
      return result;
   }

   public PlayerSet hasName(String value)
   {
      PlayerSet result = new PlayerSet();
      
      for (Player obj : this)
      {
         if (value.equals(obj.getName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public PlayerSet withName(String value)
   {
      for (Player obj : this)
      {
         obj.setName(value);
      }
      
      return this;
   }

   public intList getX()
   {
      intList result = new intList();
      
      for (Player obj : this)
      {
         result.add(obj.getX());
      }
      
      return result;
   }

   public PlayerSet hasX(int value)
   {
      PlayerSet result = new PlayerSet();
      
      for (Player obj : this)
      {
         if (value == obj.getX())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public PlayerSet withX(int value)
   {
      for (Player obj : this)
      {
         obj.setX(value);
      }
      
      return this;
   }

   public intList getY()
   {
      intList result = new intList();
      
      for (Player obj : this)
      {
         result.add(obj.getY());
      }
      
      return result;
   }

   public PlayerSet hasY(int value)
   {
      PlayerSet result = new PlayerSet();
      
      for (Player obj : this)
      {
         if (value == obj.getY())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public PlayerSet withY(int value)
   {
      for (Player obj : this)
      {
         obj.setY(value);
      }
      
      return this;
   }

   public LudoSet getGame()
   {
      LudoSet result = new LudoSet();
      
      for (Player obj : this)
      {
         result.add(obj.getGame());
      }
      
      return result;
   }

   public PlayerSet hasGame(Object value)
   {
      ObjectSet neighbors = new ObjectSet();

      if (value instanceof Collection)
      {
         neighbors.addAll((Collection<?>) value);
      }
      else
      {
         neighbors.add(value);
      }
      
      PlayerSet answer = new PlayerSet();
      
      for (Player obj : this)
      {
         if (neighbors.contains(obj.getGame()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public PlayerSet withGame(Ludo value)
   {
      for (Player obj : this)
      {
         obj.withGame(value);
      }
      
      return this;
   }

   public PlayerSet getNext()
   {
      PlayerSet result = new PlayerSet();
      
      for (Player obj : this)
      {
         result.add(obj.getNext());
      }
      
      return result;
   }

   public PlayerSet hasNext(Object value)
   {
      ObjectSet neighbors = new ObjectSet();

      if (value instanceof Collection)
      {
         neighbors.addAll((Collection<?>) value);
      }
      else
      {
         neighbors.add(value);
      }
      
      PlayerSet answer = new PlayerSet();
      
      for (Player obj : this)
      {
         if (neighbors.contains(obj.getNext()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }


   public PlayerSet getNextTransitive()
   {
      PlayerSet todo = new PlayerSet().with(this);
      
      PlayerSet result = new PlayerSet();
      
      while ( ! todo.isEmpty())
      {
         Player current = todo.first();
         
         todo.remove(current);
         
         if ( ! result.contains(current))
         {
            result.add(current);
            
            if ( ! result.contains(current.getNext()))
            {
               todo.with(current.getNext());
            }
         }
      }
      
      return result;
   }

   public PlayerSet withNext(Player value)
   {
      for (Player obj : this)
      {
         obj.withNext(value);
      }
      
      return this;
   }

   public PlayerSet getPrev()
   {
      PlayerSet result = new PlayerSet();
      
      for (Player obj : this)
      {
         result.add(obj.getPrev());
      }
      
      return result;
   }

   public PlayerSet hasPrev(Object value)
   {
      ObjectSet neighbors = new ObjectSet();

      if (value instanceof Collection)
      {
         neighbors.addAll((Collection<?>) value);
      }
      else
      {
         neighbors.add(value);
      }
      
      PlayerSet answer = new PlayerSet();
      
      for (Player obj : this)
      {
         if (neighbors.contains(obj.getPrev()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }


   public PlayerSet getPrevTransitive()
   {
      PlayerSet todo = new PlayerSet().with(this);
      
      PlayerSet result = new PlayerSet();
      
      while ( ! todo.isEmpty())
      {
         Player current = todo.first();
         
         todo.remove(current);
         
         if ( ! result.contains(current))
         {
            result.add(current);
            
            if ( ! result.contains(current.getPrev()))
            {
               todo.with(current.getPrev());
            }
         }
      }
      
      return result;
   }

   public PlayerSet withPrev(Player value)
   {
      for (Player obj : this)
      {
         obj.withPrev(value);
      }
      
      return this;
   }

   public DiceSet getDice()
   {
      DiceSet result = new DiceSet();
      
      for (Player obj : this)
      {
         result.add(obj.getDice());
      }
      
      return result;
   }

   public PlayerSet hasDice(Object value)
   {
      ObjectSet neighbors = new ObjectSet();

      if (value instanceof Collection)
      {
         neighbors.addAll((Collection<?>) value);
      }
      else
      {
         neighbors.add(value);
      }
      
      PlayerSet answer = new PlayerSet();
      
      for (Player obj : this)
      {
         if (neighbors.contains(obj.getDice()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public PlayerSet withDice(Dice value)
   {
      for (Player obj : this)
      {
         obj.withDice(value);
      }
      
      return this;
   }

   public FieldSet getStart()
   {
      FieldSet result = new FieldSet();
      
      for (Player obj : this)
      {
         result.add(obj.getStart());
      }
      
      return result;
   }

   public PlayerSet hasStart(Object value)
   {
      ObjectSet neighbors = new ObjectSet();

      if (value instanceof Collection)
      {
         neighbors.addAll((Collection<?>) value);
      }
      else
      {
         neighbors.add(value);
      }
      
      PlayerSet answer = new PlayerSet();
      
      for (Player obj : this)
      {
         if (neighbors.contains(obj.getStart()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public PlayerSet withStart(Field value)
   {
      for (Player obj : this)
      {
         obj.withStart(value);
      }
      
      return this;
   }

   public FieldSet getBase()
   {
      FieldSet result = new FieldSet();
      
      for (Player obj : this)
      {
         result.add(obj.getBase());
      }
      
      return result;
   }

   public PlayerSet hasBase(Object value)
   {
      ObjectSet neighbors = new ObjectSet();

      if (value instanceof Collection)
      {
         neighbors.addAll((Collection<?>) value);
      }
      else
      {
         neighbors.add(value);
      }
      
      PlayerSet answer = new PlayerSet();
      
      for (Player obj : this)
      {
         if (neighbors.contains(obj.getBase()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public PlayerSet withBase(Field value)
   {
      for (Player obj : this)
      {
         obj.withBase(value);
      }
      
      return this;
   }

   public FieldSet getLanding()
   {
      FieldSet result = new FieldSet();
      
      for (Player obj : this)
      {
         result.add(obj.getLanding());
      }
      
      return result;
   }

   public PlayerSet hasLanding(Object value)
   {
      ObjectSet neighbors = new ObjectSet();

      if (value instanceof Collection)
      {
         neighbors.addAll((Collection<?>) value);
      }
      else
      {
         neighbors.add(value);
      }
      
      PlayerSet answer = new PlayerSet();
      
      for (Player obj : this)
      {
         if (neighbors.contains(obj.getLanding()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public PlayerSet withLanding(Field value)
   {
      for (Player obj : this)
      {
         obj.withLanding(value);
      }
      
      return this;
   }

   public PawnSet getPawns()
   {
      PawnSet result = new PawnSet();
      
      for (Player obj : this)
      {
         result.addAll(obj.getPawns());
      }
      
      return result;
   }

   public PlayerSet hasPawns(Object value)
   {
      ObjectSet neighbors = new ObjectSet();

      if (value instanceof Collection)
      {
         neighbors.addAll((Collection<?>) value);
      }
      else
      {
         neighbors.add(value);
      }
      
      PlayerSet answer = new PlayerSet();
      
      for (Player obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getPawns()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public PlayerSet withPawns(Pawn value)
   {
      for (Player obj : this)
      {
         obj.withPawns(value);
      }
      
      return this;
   }

   public PlayerSet withoutPawns(Pawn value)
   {
      for (Player obj : this)
      {
         obj.withoutPawns(value);
      }
      
      return this;
   }


   public static final PlayerSet EMPTY_SET = new PlayerSet().withFlag(PlayerSet.READONLY);
   public PlayerSet hasColor(String lower, String upper)
   {
      PlayerSet result = new PlayerSet();
      
      for (Player obj : this)
      {
         if (lower.compareTo(obj.getColor()) <= 0 && obj.getColor().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public PlayerSet hasEnumColor(LudoColor value)
   {
      PlayerSet result = new PlayerSet();
      
      for (Player obj : this)
      {
         if (value == obj.getEnumColor())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public PlayerSet hasName(String lower, String upper)
   {
      PlayerSet result = new PlayerSet();
      
      for (Player obj : this)
      {
         if (lower.compareTo(obj.getName()) <= 0 && obj.getName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public PlayerSet hasX(int lower, int upper)
   {
      PlayerSet result = new PlayerSet();
      
      for (Player obj : this)
      {
         if (lower <= obj.getX() && obj.getX() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public PlayerSet hasY(int lower, int upper)
   {
      PlayerSet result = new PlayerSet();
      
      for (Player obj : this)
      {
         if (lower <= obj.getY() && obj.getY() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }



   public PlayerPO filterPlayerPO()
   {
      return new PlayerPO(this.toArray(new Player[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.test.examples.ludo.model.Player";
   }

   /**
    * Loop through the current set of Player objects and collect those Player objects where the color attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Player objects that match the parameter
    */
   public PlayerSet filterColor(String value)
   {
      PlayerSet result = new PlayerSet();
      
      for (Player obj : this)
      {
         if (value.equals(obj.getColor()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Player objects and collect those Player objects where the color attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Player objects that match the parameter
    */
   public PlayerSet filterColor(String lower, String upper)
   {
      PlayerSet result = new PlayerSet();
      
      for (Player obj : this)
      {
         if (lower.compareTo(obj.getColor()) <= 0 && obj.getColor().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Player objects and collect those Player objects where the enumColor attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Player objects that match the parameter
    */
   public PlayerSet filterEnumColor(LudoColor value)
   {
      PlayerSet result = new PlayerSet();
      
      for (Player obj : this)
      {
         if (value == obj.getEnumColor())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Player objects and collect those Player objects where the name attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Player objects that match the parameter
    */
   public PlayerSet filterName(String value)
   {
      PlayerSet result = new PlayerSet();
      
      for (Player obj : this)
      {
         if (value.equals(obj.getName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Player objects and collect those Player objects where the name attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Player objects that match the parameter
    */
   public PlayerSet filterName(String lower, String upper)
   {
      PlayerSet result = new PlayerSet();
      
      for (Player obj : this)
      {
         if (lower.compareTo(obj.getName()) <= 0 && obj.getName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Player objects and collect those Player objects where the x attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Player objects that match the parameter
    */
   public PlayerSet filterX(int value)
   {
      PlayerSet result = new PlayerSet();
      
      for (Player obj : this)
      {
         if (value == obj.getX())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Player objects and collect those Player objects where the x attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Player objects that match the parameter
    */
   public PlayerSet filterX(int lower, int upper)
   {
      PlayerSet result = new PlayerSet();
      
      for (Player obj : this)
      {
         if (lower <= obj.getX() && obj.getX() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Player objects and collect those Player objects where the y attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Player objects that match the parameter
    */
   public PlayerSet filterY(int value)
   {
      PlayerSet result = new PlayerSet();
      
      for (Player obj : this)
      {
         if (value == obj.getY())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Player objects and collect those Player objects where the y attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Player objects that match the parameter
    */
   public PlayerSet filterY(int lower, int upper)
   {
      PlayerSet result = new PlayerSet();
      
      for (Player obj : this)
      {
         if (lower <= obj.getY() && obj.getY() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   public PlayerSet()
   {
      // empty
   }

   public PlayerSet(Player... objects)
   {
      for (Player obj : objects)
      {
         this.add(obj);
      }
   }

   public PlayerSet(Collection<Player> objects)
   {
      this.addAll(objects);
   }


   public PlayerPO createPlayerPO()
   {
      return new PlayerPO(this.toArray(new Player[this.size()]));
   }


   @Override
   public PlayerSet getNewList(boolean keyValue)
   {
      return new PlayerSet();
   }


   public PlayerSet filter(Condition<Player> condition) {
      PlayerSet filterList = new PlayerSet();
      filterItems(filterList, condition);
      return filterList;
   }}
