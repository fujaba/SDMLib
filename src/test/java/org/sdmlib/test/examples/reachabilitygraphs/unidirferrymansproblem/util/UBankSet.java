/*
   Copyright (c) 2017 zuendorf
   
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
   
package org.sdmlib.test.examples.reachabilitygraphs.unidirferrymansproblem.util;

import de.uniks.networkparser.list.SimpleSet;
import org.sdmlib.test.examples.reachabilitygraphs.unidirferrymansproblem.UBank;
import de.uniks.networkparser.interfaces.Condition;
import java.util.Collection;
import de.uniks.networkparser.list.ObjectSet;
import java.util.Collections;
import org.sdmlib.test.examples.reachabilitygraphs.unidirferrymansproblem.util.UCargoSet;
import org.sdmlib.test.examples.reachabilitygraphs.unidirferrymansproblem.UCargo;

public class UBankSet extends SimpleSet<UBank>
{
	public Class<?> getTypClass() {
		return UBank.class;
	}

   public UBankSet()
   {
      // empty
   }

   public UBankSet(UBank... objects)
   {
      for (UBank obj : objects)
      {
         this.add(obj);
      }
   }

   public UBankSet(Collection<UBank> objects)
   {
      this.addAll(objects);
   }

   public static final UBankSet EMPTY_SET = new UBankSet().withFlag(UBankSet.READONLY);


   public UBankPO createUBankPO()
   {
      return new UBankPO(this.toArray(new UBank[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.test.examples.reachabilitygraphs.unidirferrymansproblem.UBank";
   }


   @Override
   public UBankSet getNewList(boolean keyValue)
   {
      return new UBankSet();
   }


   public UBankSet filter(Condition<UBank> condition) {
      UBankSet filterList = new UBankSet();
      filterItems(filterList, condition);
      return filterList;
   }

   @SuppressWarnings("unchecked")
   public UBankSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<UBank>)value);
      }
      else if (value != null)
      {
         this.add((UBank) value);
      }
      
      return this;
   }
   
   public UBankSet without(UBank value)
   {
      this.remove(value);
      return this;
   }


   /**
    * Loop through the current set of UBank objects and collect a list of the name attribute values. 
    * 
    * @return List of String objects reachable via name attribute
    */
   public ObjectSet getName()
   {
      ObjectSet result = new ObjectSet();
      
      for (UBank obj : this)
      {
         result.add(obj.getName());
      }
      
      return result;
   }


   /**
    * Loop through the current set of UBank objects and collect those UBank objects where the name attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of UBank objects that match the parameter
    */
   public UBankSet createNameCondition(String value)
   {
      UBankSet result = new UBankSet();
      
      for (UBank obj : this)
      {
         if (value.equals(obj.getName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of UBank objects and collect those UBank objects where the name attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of UBank objects that match the parameter
    */
   public UBankSet createNameCondition(String lower, String upper)
   {
      UBankSet result = new UBankSet();
      
      for (UBank obj : this)
      {
         if (lower.compareTo(obj.getName()) <= 0 && obj.getName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of UBank objects and assign value to the name attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of UBank objects now with new attribute values.
    */
   public UBankSet withName(String value)
   {
      for (UBank obj : this)
      {
         obj.setName(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of UBank objects and collect a set of the UCargo objects reached via cargos. 
    * 
    * @return Set of UCargo objects reachable via cargos
    */
   public UCargoSet getCargos()
   {
      UCargoSet result = new UCargoSet();
      
      for (UBank obj : this)
      {
         result.with(obj.getCargos());
      }
      
      return result;
   }

   /**
    * Loop through the current set of UBank objects and collect all contained objects with reference cargos pointing to the object passed as parameter. 
    * 
    * @param value The object required as cargos neighbor of the collected results. 
    * 
    * @return Set of UCargo objects referring to value via cargos
    */
   public UBankSet filterCargos(Object value)
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
      
      UBankSet answer = new UBankSet();
      
      for (UBank obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getCargos()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the UBank object passed as parameter to the Cargos attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Cargos attributes.
    */
   public UBankSet withCargos(UCargo value)
   {
      for (UBank obj : this)
      {
         obj.withCargos(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the UBank object passed as parameter from the Cargos attribute of each of it. 
    * 
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public UBankSet withoutCargos(UCargo value)
   {
      for (UBank obj : this)
      {
         obj.withoutCargos(value);
      }
      
      return this;
   }

}
