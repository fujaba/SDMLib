/*
   Copyright (c) 2014 NeTH 
   
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
   
package org.sdmlib.test.examples.ludoreverse.model.util;

import java.util.Collection;

import de.uniks.networkparser.list.ObjectSet;
import de.uniks.networkparser.list.StringList;
import org.sdmlib.models.modelsets.intList;
import org.sdmlib.test.examples.ludoreverse.model.Ludo;
import org.sdmlib.test.examples.ludoreverse.model.Player;

import de.uniks.networkparser.list.SimpleSet;
import org.sdmlib.test.examples.ludoreverse.model.util.PlayerSet;

public class LudoSet extends SimpleSet<Ludo>
{


   public LudoPO hasLudoPO()
   {
      return new LudoPO(this.toArray(new Ludo[this.size()]));
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

   public StringList getStyle()
   {
      StringList result = new StringList();
      
      for (Ludo obj : this)
      {
         result.add(obj.getStyle());
      }
      
      return result;
   }

   public LudoSet hasStyle(String value)
   {
      LudoSet result = new LudoSet();
      
      for (Ludo obj : this)
      {
         if (value.equals(obj.getStyle()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public LudoSet withStyle(String value)
   {
      for (Ludo obj : this)
      {
         obj.setStyle(value);
      }
      
      return this;
   }

   public intList getAge()
   {
      intList result = new intList();
      
      for (Ludo obj : this)
      {
         result.add(obj.getAge());
      }
      
      return result;
   }

   public LudoSet hasAge(int value)
   {
      LudoSet result = new LudoSet();
      
      for (Ludo obj : this)
      {
         if (value == obj.getAge())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public LudoSet withAge(int value)
   {
      for (Ludo obj : this)
      {
         obj.setAge(value);
      }
      
      return this;
   }

   public PlayerSet getGame()
   {
      PlayerSet result = new PlayerSet();
      
      for (Ludo obj : this)
      {
         result.add(obj.getGame());
      }
      
      return result;
   }

   public LudoSet hasGame(Object value)
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
         if (neighbors.contains(obj.getGame()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public LudoSet withGame(Player value)
   {
      for (Ludo obj : this)
      {
         obj.withGame(value);
      }
      
      return this;
   }


   public static final LudoSet EMPTY_SET = new LudoSet().withFlag(LudoSet.READONLY);
   public LudoSet hasStyle(String lower, String upper)
   {
      LudoSet result = new LudoSet();
      
      for (Ludo obj : this)
      {
         if (lower.compareTo(obj.getStyle()) <= 0 && obj.getStyle().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public LudoSet hasAge(int lower, int upper)
   {
      LudoSet result = new LudoSet();
      
      for (Ludo obj : this)
      {
         if (lower <= obj.getAge() && obj.getAge() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }



   public LudoPO filterLudoPO()
   {
      return new LudoPO(this.toArray(new Ludo[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.test.examples.ludoreverse.model.Ludo";
   }

   /**
    * Loop through the current set of Ludo objects and collect those Ludo objects where the style attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Ludo objects that match the parameter
    */
   public LudoSet filterStyle(String value)
   {
      LudoSet result = new LudoSet();
      
      for (Ludo obj : this)
      {
         if (value.equals(obj.getStyle()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Ludo objects and collect those Ludo objects where the style attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Ludo objects that match the parameter
    */
   public LudoSet filterStyle(String lower, String upper)
   {
      LudoSet result = new LudoSet();
      
      for (Ludo obj : this)
      {
         if (lower.compareTo(obj.getStyle()) <= 0 && obj.getStyle().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Ludo objects and collect those Ludo objects where the age attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Ludo objects that match the parameter
    */
   public LudoSet filterAge(int value)
   {
      LudoSet result = new LudoSet();
      
      for (Ludo obj : this)
      {
         if (value == obj.getAge())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Ludo objects and collect those Ludo objects where the age attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Ludo objects that match the parameter
    */
   public LudoSet filterAge(int lower, int upper)
   {
      LudoSet result = new LudoSet();
      
      for (Ludo obj : this)
      {
         if (lower <= obj.getAge() && obj.getAge() <= upper)
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
}
