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

import org.sdmlib.models.modelsets.intList;
import org.sdmlib.test.examples.ludo.model.Field;
import org.sdmlib.test.examples.ludo.model.Pawn;
import org.sdmlib.test.examples.ludo.model.Player;

import de.uniks.networkparser.list.ObjectSet;
import de.uniks.networkparser.list.SimpleSet;
import de.uniks.networkparser.list.StringList;

public class PawnSet extends SimpleSet<Pawn>
{


   public PawnPO hasPawnPO()
   {
      return new PawnPO(this.toArray(new Pawn[this.size()]));
   }

   @SuppressWarnings("unchecked")
   public PawnSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Pawn>)value);
      }
      else if (value != null)
      {
         this.add((Pawn) value);
      }
      
      return this;
   }
   
   public PawnSet without(Pawn value)
   {
      this.remove(value);
      return this;
   }

   public StringList getColor()
   {
      StringList result = new StringList();
      
      for (Pawn obj : this)
      {
         result.add(obj.getColor());
      }
      
      return result;
   }

   public PawnSet hasColor(String value)
   {
      PawnSet result = new PawnSet();
      
      for (Pawn obj : this)
      {
         if (value.equals(obj.getColor()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public PawnSet withColor(String value)
   {
      for (Pawn obj : this)
      {
         obj.setColor(value);
      }
      
      return this;
   }

   public intList getX()
   {
      intList result = new intList();
      
      for (Pawn obj : this)
      {
         result.add(obj.getX());
      }
      
      return result;
   }

   public PawnSet hasX(int value)
   {
      PawnSet result = new PawnSet();
      
      for (Pawn obj : this)
      {
         if (value == obj.getX())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public PawnSet withX(int value)
   {
      for (Pawn obj : this)
      {
         obj.setX(value);
      }
      
      return this;
   }

   public intList getY()
   {
      intList result = new intList();
      
      for (Pawn obj : this)
      {
         result.add(obj.getY());
      }
      
      return result;
   }

   public PawnSet hasY(int value)
   {
      PawnSet result = new PawnSet();
      
      for (Pawn obj : this)
      {
         if (value == obj.getY())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public PawnSet withY(int value)
   {
      for (Pawn obj : this)
      {
         obj.setY(value);
      }
      
      return this;
   }

   public PlayerSet getPlayer()
   {
      PlayerSet result = new PlayerSet();
      
      for (Pawn obj : this)
      {
         result.add(obj.getPlayer());
      }
      
      return result;
   }

   public PawnSet hasPlayer(Object value)
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
      
      PawnSet answer = new PawnSet();
      
      for (Pawn obj : this)
      {
         if (neighbors.contains(obj.getPlayer()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public PawnSet withPlayer(Player value)
   {
      for (Pawn obj : this)
      {
         obj.withPlayer(value);
      }
      
      return this;
   }

   public FieldSet getPos()
   {
      FieldSet result = new FieldSet();
      
      for (Pawn obj : this)
      {
         result.add(obj.getPos());
      }
      
      return result;
   }

   public PawnSet hasPos(Object value)
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
      
      PawnSet answer = new PawnSet();
      
      for (Pawn obj : this)
      {
         if (neighbors.contains(obj.getPos()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public PawnSet withPos(Field value)
   {
      for (Pawn obj : this)
      {
         obj.withPos(value);
      }
      
      return this;
   }


   public static final PawnSet EMPTY_SET = new PawnSet().withFlag(PawnSet.READONLY);
   public PawnSet hasColor(String lower, String upper)
   {
      PawnSet result = new PawnSet();
      
      for (Pawn obj : this)
      {
         if (lower.compareTo(obj.getColor()) <= 0 && obj.getColor().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public PawnSet hasX(int lower, int upper)
   {
      PawnSet result = new PawnSet();
      
      for (Pawn obj : this)
      {
         if (lower <= obj.getX() && obj.getX() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public PawnSet hasY(int lower, int upper)
   {
      PawnSet result = new PawnSet();
      
      for (Pawn obj : this)
      {
         if (lower <= obj.getY() && obj.getY() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }



   public PawnPO filterPawnPO()
   {
      return new PawnPO(this.toArray(new Pawn[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.test.examples.ludo.model.Pawn";
   }

   /**
    * Loop through the current set of Pawn objects and collect those Pawn objects where the color attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Pawn objects that match the parameter
    */
   public PawnSet filterColor(String value)
   {
      PawnSet result = new PawnSet();
      
      for (Pawn obj : this)
      {
         if (value.equals(obj.getColor()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Pawn objects and collect those Pawn objects where the color attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Pawn objects that match the parameter
    */
   public PawnSet filterColor(String lower, String upper)
   {
      PawnSet result = new PawnSet();
      
      for (Pawn obj : this)
      {
         if (lower.compareTo(obj.getColor()) <= 0 && obj.getColor().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Pawn objects and collect those Pawn objects where the x attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Pawn objects that match the parameter
    */
   public PawnSet filterX(int value)
   {
      PawnSet result = new PawnSet();
      
      for (Pawn obj : this)
      {
         if (value == obj.getX())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Pawn objects and collect those Pawn objects where the x attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Pawn objects that match the parameter
    */
   public PawnSet filterX(int lower, int upper)
   {
      PawnSet result = new PawnSet();
      
      for (Pawn obj : this)
      {
         if (lower <= obj.getX() && obj.getX() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Pawn objects and collect those Pawn objects where the y attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Pawn objects that match the parameter
    */
   public PawnSet filterY(int value)
   {
      PawnSet result = new PawnSet();
      
      for (Pawn obj : this)
      {
         if (value == obj.getY())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Pawn objects and collect those Pawn objects where the y attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Pawn objects that match the parameter
    */
   public PawnSet filterY(int lower, int upper)
   {
      PawnSet result = new PawnSet();
      
      for (Pawn obj : this)
      {
         if (lower <= obj.getY() && obj.getY() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   public PawnSet()
   {
      // empty
   }

   public PawnSet(Pawn... objects)
   {
      for (Pawn obj : objects)
      {
         this.add(obj);
      }
   }

   public PawnSet(Collection<Pawn> objects)
   {
      this.addAll(objects);
   }


   public PawnPO createPawnPO()
   {
      return new PawnPO(this.toArray(new Pawn[this.size()]));
   }


   @Override
   public PawnSet getNewList(boolean keyValue)
   {
      return new PawnSet();
   }

   /**
    * Loop through the current set of Pawn objects and collect those Pawn objects where the color attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Pawn objects that match the parameter
    */
   public PawnSet createColorCondition(String value)
   {
      PawnSet result = new PawnSet();
      
      for (Pawn obj : this)
      {
         if (value.equals(obj.getColor()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Pawn objects and collect those Pawn objects where the color attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Pawn objects that match the parameter
    */
   public PawnSet createColorCondition(String lower, String upper)
   {
      PawnSet result = new PawnSet();
      
      for (Pawn obj : this)
      {
         if (lower.compareTo(obj.getColor()) <= 0 && obj.getColor().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Pawn objects and collect those Pawn objects where the x attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Pawn objects that match the parameter
    */
   public PawnSet createXCondition(int value)
   {
      PawnSet result = new PawnSet();
      
      for (Pawn obj : this)
      {
         if (value == obj.getX())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Pawn objects and collect those Pawn objects where the x attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Pawn objects that match the parameter
    */
   public PawnSet createXCondition(int lower, int upper)
   {
      PawnSet result = new PawnSet();
      
      for (Pawn obj : this)
      {
         if (lower <= obj.getX() && obj.getX() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Pawn objects and collect those Pawn objects where the y attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Pawn objects that match the parameter
    */
   public PawnSet createYCondition(int value)
   {
      PawnSet result = new PawnSet();
      
      for (Pawn obj : this)
      {
         if (value == obj.getY())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Pawn objects and collect those Pawn objects where the y attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Pawn objects that match the parameter
    */
   public PawnSet createYCondition(int lower, int upper)
   {
      PawnSet result = new PawnSet();
      
      for (Pawn obj : this)
      {
         if (lower <= obj.getY() && obj.getY() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

}
