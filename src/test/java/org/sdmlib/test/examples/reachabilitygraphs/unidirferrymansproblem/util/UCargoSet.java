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
import org.sdmlib.test.examples.reachabilitygraphs.unidirferrymansproblem.UCargo;
import de.uniks.networkparser.interfaces.Condition;
import java.util.Collection;
import de.uniks.networkparser.list.ObjectSet;

public class UCargoSet extends SimpleSet<UCargo>
{
	public Class<?> getTypClass() {
		return UCargo.class;
	}

   public UCargoSet()
   {
      // empty
   }

   public UCargoSet(UCargo... objects)
   {
      for (UCargo obj : objects)
      {
         this.add(obj);
      }
   }

   public UCargoSet(Collection<UCargo> objects)
   {
      this.addAll(objects);
   }

   public static final UCargoSet EMPTY_SET = new UCargoSet().withFlag(UCargoSet.READONLY);


   public UCargoPO createUCargoPO()
   {
      return new UCargoPO(this.toArray(new UCargo[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.test.examples.reachabilitygraphs.unidirferrymansproblem.UCargo";
   }


   @Override
   public UCargoSet getNewList(boolean keyValue)
   {
      return new UCargoSet();
   }


   @SuppressWarnings("unchecked")
   public UCargoSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<UCargo>)value);
      }
      else if (value != null)
      {
         this.add((UCargo) value);
      }
      
      return this;
   }
   
   public UCargoSet without(UCargo value)
   {
      this.remove(value);
      return this;
   }


   /**
    * Loop through the current set of UCargo objects and collect a list of the name attribute values. 
    * 
    * @return List of String objects reachable via name attribute
    */
   public ObjectSet getName()
   {
      ObjectSet result = new ObjectSet();
      
      for (UCargo obj : this)
      {
         result.add(obj.getName());
      }
      
      return result;
   }


   /**
    * Loop through the current set of UCargo objects and collect those UCargo objects where the name attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of UCargo objects that match the parameter
    */
   public UCargoSet createNameCondition(String value)
   {
      UCargoSet result = new UCargoSet();
      
      for (UCargo obj : this)
      {
         if (value.equals(obj.getName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of UCargo objects and collect those UCargo objects where the name attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of UCargo objects that match the parameter
    */
   public UCargoSet createNameCondition(String lower, String upper)
   {
      UCargoSet result = new UCargoSet();
      
      for (UCargo obj : this)
      {
         if (lower.compareTo(obj.getName()) <= 0 && obj.getName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of UCargo objects and assign value to the name attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of UCargo objects now with new attribute values.
    */
   public UCargoSet withName(String value)
   {
      for (UCargo obj : this)
      {
         obj.setName(value);
      }
      
      return this;
   }

}
