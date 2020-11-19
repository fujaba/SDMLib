/*
   Copyright (c) 2015 zuendorf
   
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
import java.util.Date;

import org.sdmlib.models.modelsets.DateList;
import org.sdmlib.test.examples.ludo.model.Dice;
import org.sdmlib.test.examples.ludo.model.Field;
import org.sdmlib.test.examples.ludo.model.Ludo;
import org.sdmlib.test.examples.ludo.model.Player;

import de.uniks.networkparser.list.ObjectSet;
import de.uniks.networkparser.list.SimpleSet;

public class LudoSet extends SimpleSet<Ludo>
{

   public static final LudoSet EMPTY_SET = new LudoSet().withFlag(LudoSet.READONLY);


   public LudoPO hasLudoPO()
   {
      return new LudoPO(this.toArray(new Ludo[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.test.examples.ludo.model.Ludo";
   }


   @SuppressWarnings("unchecked")
   public LudoSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Ludo>)value);
      }
      else if (value != null)
      {
         this.add((Ludo) value);
      }
      
      return this;
   }
   
   public LudoSet without(Ludo value)
   {
      this.remove(value);
      return this;
   }

   public DateList getDate()
   {
      DateList result = new DateList();
      
      for (Ludo obj : this)
      {
         result.add(obj.getDate());
      }
      
      return result;
   }

   public LudoSet hasDate(Date value)
   {
      LudoSet result = new LudoSet();
      
      for (Ludo obj : this)
      {
         if (value == obj.getDate())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public LudoSet withDate(Date value)
   {
      for (Ludo obj : this)
      {
         obj.setDate(value);
      }
      
      return this;
   }

   public PlayerSet getPlayers()
   {
      PlayerSet result = new PlayerSet();
      
      for (Ludo obj : this)
      {
         result.addAll(obj.getPlayers());
      }
      
      return result;
   }

   public LudoSet hasPlayers(Object value)
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
      
      LudoSet answer = new LudoSet();
      
      for (Ludo obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getPlayers()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public LudoSet withPlayers(Player value)
   {
      for (Ludo obj : this)
      {
         obj.withPlayers(value);
      }
      
      return this;
   }

   public LudoSet withoutPlayers(Player value)
   {
      for (Ludo obj : this)
      {
         obj.withoutPlayers(value);
      }
      
      return this;
   }

   public DiceSet getDice()
   {
      DiceSet result = new DiceSet();
      
      for (Ludo obj : this)
      {
         result.add(obj.getDice());
      }
      
      return result;
   }

   public LudoSet hasDice(Object value)
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
      
      LudoSet answer = new LudoSet();
      
      for (Ludo obj : this)
      {
         if (neighbors.contains(obj.getDice()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public LudoSet withDice(Dice value)
   {
      for (Ludo obj : this)
      {
         obj.withDice(value);
      }
      
      return this;
   }

   public FieldSet getFields()
   {
      FieldSet result = new FieldSet();
      
      for (Ludo obj : this)
      {
         result.addAll(obj.getFields());
      }
      
      return result;
   }

   public LudoSet hasFields(Object value)
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
      
      LudoSet answer = new LudoSet();
      
      for (Ludo obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getFields()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public LudoSet withFields(Field value)
   {
      for (Ludo obj : this)
      {
         obj.withFields(value);
      }
      
      return this;
   }

   public LudoSet withoutFields(Field value)
   {
      for (Ludo obj : this)
      {
         obj.withoutFields(value);
      }
      
      return this;
   }



   public LudoPO filterLudoPO()
   {
      return new LudoPO(this.toArray(new Ludo[this.size()]));
   }

   /**
    * Loop through the current set of Ludo objects and collect those Ludo objects where the date attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Ludo objects that match the parameter
    */
   public LudoSet filterDate(Date value)
   {
      LudoSet result = new LudoSet();
      
      for (Ludo obj : this)
      {
         if (value == obj.getDate())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   public LudoSet()
   {
      // empty
   }

   public LudoSet(Ludo... objects)
   {
      for (Ludo obj : objects)
      {
         this.add(obj);
      }
   }

   public LudoSet(Collection<Ludo> objects)
   {
      this.addAll(objects);
   }


   public LudoPO createLudoPO()
   {
      return new LudoPO(this.toArray(new Ludo[this.size()]));
   }


   @Override
   public LudoSet getNewList(boolean keyValue)
   {
      return new LudoSet();
   }

   /**
    * Loop through the current set of Ludo objects and collect those Ludo objects where the date attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Ludo objects that match the parameter
    */
   public LudoSet createDateCondition(Date value)
   {
      LudoSet result = new LudoSet();
      
      for (Ludo obj : this)
      {
         if (value == obj.getDate())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

}
