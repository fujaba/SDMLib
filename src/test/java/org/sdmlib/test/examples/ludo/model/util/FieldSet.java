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

import java.awt.Point;
import java.util.Collection;
import java.util.Collections;

import org.sdmlib.models.modelsets.ObjectSet;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.models.modelsets.intList;
import org.sdmlib.test.examples.ludo.model.Field;
import org.sdmlib.test.examples.ludo.model.Ludo;
import org.sdmlib.test.examples.ludo.model.Pawn;
import org.sdmlib.test.examples.ludo.model.Player;

import de.uniks.networkparser.list.SimpleSet;

public class FieldSet extends SimpleSet<Field>
{


   public FieldPO hasFieldPO()
   {
      return new FieldPO(this.toArray(new Field[this.size()]));
   }

   @SuppressWarnings("unchecked")
   public FieldSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Field>)value);
      }
      else if (value != null)
      {
         this.add((Field) value);
      }
      
      return this;
   }
   
   public FieldSet without(Field value)
   {
      this.remove(value);
      return this;
   }

   public StringList getColor()
   {
      StringList result = new StringList();
      
      for (Field obj : this)
      {
         result.add(obj.getColor());
      }
      
      return result;
   }

   public FieldSet hasColor(String value)
   {
      FieldSet result = new FieldSet();
      
      for (Field obj : this)
      {
         if (value.equals(obj.getColor()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public FieldSet withColor(String value)
   {
      for (Field obj : this)
      {
         obj.setColor(value);
      }
      
      return this;
   }

   public StringList getKind()
   {
      StringList result = new StringList();
      
      for (Field obj : this)
      {
         result.add(obj.getKind());
      }
      
      return result;
   }

   public FieldSet hasKind(String value)
   {
      FieldSet result = new FieldSet();
      
      for (Field obj : this)
      {
         if (value.equals(obj.getKind()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public FieldSet withKind(String value)
   {
      for (Field obj : this)
      {
         obj.setKind(value);
      }
      
      return this;
   }

   public intList getX()
   {
      intList result = new intList();
      
      for (Field obj : this)
      {
         result.add(obj.getX());
      }
      
      return result;
   }

   public FieldSet hasX(int value)
   {
      FieldSet result = new FieldSet();
      
      for (Field obj : this)
      {
         if (value == obj.getX())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public FieldSet withX(int value)
   {
      for (Field obj : this)
      {
         obj.setX(value);
      }
      
      return this;
   }

   public intList getY()
   {
      intList result = new intList();
      
      for (Field obj : this)
      {
         result.add(obj.getY());
      }
      
      return result;
   }

   public FieldSet hasY(int value)
   {
      FieldSet result = new FieldSet();
      
      for (Field obj : this)
      {
         if (value == obj.getY())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public FieldSet withY(int value)
   {
      for (Field obj : this)
      {
         obj.setY(value);
      }
      
      return this;
   }

   public PointSet getPoint()
   {
      PointSet result = new PointSet();
      
      for (Field obj : this)
      {
         result.add(obj.getPoint());
      }
      
      return result;
   }

   public FieldSet withPoint(Point value)
   {
      for (Field obj : this)
      {
         obj.setPoint(value);
      }
      
      return this;
   }

   public LudoSet getGame()
   {
      LudoSet result = new LudoSet();
      
      for (Field obj : this)
      {
         result.add(obj.getGame());
      }
      
      return result;
   }

   public FieldSet hasGame(Object value)
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
      
      FieldSet answer = new FieldSet();
      
      for (Field obj : this)
      {
         if (neighbors.contains(obj.getGame()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public FieldSet withGame(Ludo value)
   {
      for (Field obj : this)
      {
         obj.withGame(value);
      }
      
      return this;
   }

   public FieldSet getNext()
   {
      FieldSet result = new FieldSet();
      
      for (Field obj : this)
      {
         result.add(obj.getNext());
      }
      
      return result;
   }

   public FieldSet hasNext(Object value)
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
      
      FieldSet answer = new FieldSet();
      
      for (Field obj : this)
      {
         if (neighbors.contains(obj.getNext()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }


   public FieldSet getNextTransitive()
   {
      FieldSet todo = new FieldSet().with(this);
      
      FieldSet result = new FieldSet();
      
      while ( ! todo.isEmpty())
      {
         Field current = todo.first();
         
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

   public FieldSet withNext(Field value)
   {
      for (Field obj : this)
      {
         obj.withNext(value);
      }
      
      return this;
   }

   public FieldSet getPrev()
   {
      FieldSet result = new FieldSet();
      
      for (Field obj : this)
      {
         result.add(obj.getPrev());
      }
      
      return result;
   }

   public FieldSet hasPrev(Object value)
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
      
      FieldSet answer = new FieldSet();
      
      for (Field obj : this)
      {
         if (neighbors.contains(obj.getPrev()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }


   public FieldSet getPrevTransitive()
   {
      FieldSet todo = new FieldSet().with(this);
      
      FieldSet result = new FieldSet();
      
      while ( ! todo.isEmpty())
      {
         Field current = todo.first();
         
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

   public FieldSet withPrev(Field value)
   {
      for (Field obj : this)
      {
         obj.withPrev(value);
      }
      
      return this;
   }

   public FieldSet getLanding()
   {
      FieldSet result = new FieldSet();
      
      for (Field obj : this)
      {
         result.add(obj.getLanding());
      }
      
      return result;
   }

   public FieldSet hasLanding(Object value)
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
      
      FieldSet answer = new FieldSet();
      
      for (Field obj : this)
      {
         if (neighbors.contains(obj.getLanding()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }


   public FieldSet getLandingTransitive()
   {
      FieldSet todo = new FieldSet().with(this);
      
      FieldSet result = new FieldSet();
      
      while ( ! todo.isEmpty())
      {
         Field current = todo.first();
         
         todo.remove(current);
         
         if ( ! result.contains(current))
         {
            result.add(current);
            
            if ( ! result.contains(current.getLanding()))
            {
               todo.with(current.getLanding());
            }
         }
      }
      
      return result;
   }

   public FieldSet withLanding(Field value)
   {
      for (Field obj : this)
      {
         obj.withLanding(value);
      }
      
      return this;
   }

   public FieldSet getEntry()
   {
      FieldSet result = new FieldSet();
      
      for (Field obj : this)
      {
         result.add(obj.getEntry());
      }
      
      return result;
   }

   public FieldSet hasEntry(Object value)
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
      
      FieldSet answer = new FieldSet();
      
      for (Field obj : this)
      {
         if (neighbors.contains(obj.getEntry()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }


   public FieldSet getEntryTransitive()
   {
      FieldSet todo = new FieldSet().with(this);
      
      FieldSet result = new FieldSet();
      
      while ( ! todo.isEmpty())
      {
         Field current = todo.first();
         
         todo.remove(current);
         
         if ( ! result.contains(current))
         {
            result.add(current);
            
            if ( ! result.contains(current.getEntry()))
            {
               todo.with(current.getEntry());
            }
         }
      }
      
      return result;
   }

   public FieldSet withEntry(Field value)
   {
      for (Field obj : this)
      {
         obj.withEntry(value);
      }
      
      return this;
   }

   public PlayerSet getStarter()
   {
      PlayerSet result = new PlayerSet();
      
      for (Field obj : this)
      {
         result.add(obj.getStarter());
      }
      
      return result;
   }

   public FieldSet hasStarter(Object value)
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
      
      FieldSet answer = new FieldSet();
      
      for (Field obj : this)
      {
         if (neighbors.contains(obj.getStarter()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public FieldSet withStarter(Player value)
   {
      for (Field obj : this)
      {
         obj.withStarter(value);
      }
      
      return this;
   }

   public PlayerSet getBaseowner()
   {
      PlayerSet result = new PlayerSet();
      
      for (Field obj : this)
      {
         result.add(obj.getBaseowner());
      }
      
      return result;
   }

   public FieldSet hasBaseowner(Object value)
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
      
      FieldSet answer = new FieldSet();
      
      for (Field obj : this)
      {
         if (neighbors.contains(obj.getBaseowner()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public FieldSet withBaseowner(Player value)
   {
      for (Field obj : this)
      {
         obj.withBaseowner(value);
      }
      
      return this;
   }

   public PlayerSet getLander()
   {
      PlayerSet result = new PlayerSet();
      
      for (Field obj : this)
      {
         result.add(obj.getLander());
      }
      
      return result;
   }

   public FieldSet hasLander(Object value)
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
      
      FieldSet answer = new FieldSet();
      
      for (Field obj : this)
      {
         if (neighbors.contains(obj.getLander()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public FieldSet withLander(Player value)
   {
      for (Field obj : this)
      {
         obj.withLander(value);
      }
      
      return this;
   }

   public PawnSet getPawns()
   {
      PawnSet result = new PawnSet();
      
      for (Field obj : this)
      {
         result.addAll(obj.getPawns());
      }
      
      return result;
   }

   public FieldSet hasPawns(Object value)
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
      
      FieldSet answer = new FieldSet();
      
      for (Field obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getPawns()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public FieldSet withPawns(Pawn value)
   {
      for (Field obj : this)
      {
         obj.withPawns(value);
      }
      
      return this;
   }

   public FieldSet withoutPawns(Pawn value)
   {
      for (Field obj : this)
      {
         obj.withoutPawns(value);
      }
      
      return this;
   }


   public static final FieldSet EMPTY_SET = new FieldSet().withFlag(FieldSet.READONLY);
   public FieldSet hasColor(String lower, String upper)
   {
      FieldSet result = new FieldSet();
      
      for (Field obj : this)
      {
         if (lower.compareTo(obj.getColor()) <= 0 && obj.getColor().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public FieldSet hasKind(String lower, String upper)
   {
      FieldSet result = new FieldSet();
      
      for (Field obj : this)
      {
         if (lower.compareTo(obj.getKind()) <= 0 && obj.getKind().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public FieldSet hasX(int lower, int upper)
   {
      FieldSet result = new FieldSet();
      
      for (Field obj : this)
      {
         if (lower <= obj.getX() && obj.getX() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public FieldSet hasY(int lower, int upper)
   {
      FieldSet result = new FieldSet();
      
      for (Field obj : this)
      {
         if (lower <= obj.getY() && obj.getY() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public FieldSet hasPoint(Point value)
   {
      FieldSet result = new FieldSet();
      
      for (Field obj : this)
      {
         if (value == obj.getPoint())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

}
