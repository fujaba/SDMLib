/*
   Copyright (c) 2018 zuendorf
   
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
   
package org.sdmlib.test.examples.groupaccount.model.util;

import de.uniks.networkparser.list.SimpleSet;
import org.sdmlib.test.examples.groupaccount.model.Party;
import de.uniks.networkparser.interfaces.Condition;
import java.util.Collection;
import de.uniks.networkparser.list.ObjectSet;
import de.uniks.networkparser.list.NumberList;
import java.util.Collections;
import org.sdmlib.test.examples.groupaccount.model.util.PersonSet;
import org.sdmlib.test.examples.groupaccount.model.Person;

public class PartySet extends SimpleSet<Party>
{
	public Class<?> getTypClass() {
		return Party.class;
	}

   public PartySet()
   {
      // empty
   }

   public PartySet(Party... objects)
   {
      for (Party obj : objects)
      {
         this.add(obj);
      }
   }

   public PartySet(Collection<Party> objects)
   {
      this.addAll(objects);
   }

   public static final PartySet EMPTY_SET = new PartySet().withFlag(PartySet.READONLY);


   public PartyPO createPartyPO()
   {
      return new PartyPO(this.toArray(new Party[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.test.examples.groupaccount.model.Party";
   }


   @Override
   public PartySet getNewList(boolean keyValue)
   {
      return new PartySet();
   }


   @SuppressWarnings("unchecked")
   public PartySet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Party>)value);
      }
      else if (value != null)
      {
         this.add((Party) value);
      }
      
      return this;
   }
   
   public PartySet without(Party value)
   {
      this.remove(value);
      return this;
   }


   /**
    * Loop through the current set of Party objects and collect a list of the partyName attribute values. 
    * 
    * @return List of String objects reachable via partyName attribute
    */
   public ObjectSet getPartyName()
   {
      ObjectSet result = new ObjectSet();
      
      for (Party obj : this)
      {
         result.add(obj.getPartyName());
      }
      
      return result;
   }


   /**
    * Loop through the current set of Party objects and collect those Party objects where the partyName attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Party objects that match the parameter
    */
   public PartySet createPartyNameCondition(String value)
   {
      PartySet result = new PartySet();
      
      for (Party obj : this)
      {
         if (value.equals(obj.getPartyName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Party objects and collect those Party objects where the partyName attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Party objects that match the parameter
    */
   public PartySet createPartyNameCondition(String lower, String upper)
   {
      PartySet result = new PartySet();
      
      for (Party obj : this)
      {
         if (lower.compareTo(obj.getPartyName()) <= 0 && obj.getPartyName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Party objects and assign value to the partyName attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of Party objects now with new attribute values.
    */
   public PartySet withPartyName(String value)
   {
      for (Party obj : this)
      {
         obj.setPartyName(value);
      }
      
      return this;
   }


   /**
    * Loop through the current set of Party objects and collect a list of the share attribute values. 
    * 
    * @return List of double objects reachable via share attribute
    */
   public NumberList getShare()
   {
      NumberList result = new NumberList();
      
      for (Party obj : this)
      {
         result.add(obj.getShare());
      }
      
      return result;
   }


   /**
    * Loop through the current set of Party objects and collect those Party objects where the share attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Party objects that match the parameter
    */
   public PartySet createShareCondition(double value)
   {
      PartySet result = new PartySet();
      
      for (Party obj : this)
      {
         if (value == obj.getShare())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Party objects and collect those Party objects where the share attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Party objects that match the parameter
    */
   public PartySet createShareCondition(double lower, double upper)
   {
      PartySet result = new PartySet();
      
      for (Party obj : this)
      {
         if (lower <= obj.getShare() && obj.getShare() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Party objects and assign value to the share attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of Party objects now with new attribute values.
    */
   public PartySet withShare(double value)
   {
      for (Party obj : this)
      {
         obj.setShare(value);
      }
      
      return this;
   }


   /**
    * Loop through the current set of Party objects and collect a list of the total attribute values. 
    * 
    * @return List of double objects reachable via total attribute
    */
   public NumberList getTotal()
   {
      NumberList result = new NumberList();
      
      for (Party obj : this)
      {
         result.add(obj.getTotal());
      }
      
      return result;
   }


   /**
    * Loop through the current set of Party objects and collect those Party objects where the total attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Party objects that match the parameter
    */
   public PartySet createTotalCondition(double value)
   {
      PartySet result = new PartySet();
      
      for (Party obj : this)
      {
         if (value == obj.getTotal())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Party objects and collect those Party objects where the total attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Party objects that match the parameter
    */
   public PartySet createTotalCondition(double lower, double upper)
   {
      PartySet result = new PartySet();
      
      for (Party obj : this)
      {
         if (lower <= obj.getTotal() && obj.getTotal() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Party objects and assign value to the total attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of Party objects now with new attribute values.
    */
   public PartySet withTotal(double value)
   {
      for (Party obj : this)
      {
         obj.setTotal(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Party objects and collect a set of the Person objects reached via guests. 
    * 
    * @return Set of Person objects reachable via guests
    */
   public PersonSet getGuests()
   {
      PersonSet result = new PersonSet();
      
      for (Party obj : this)
      {
         result.with(obj.getGuests());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Party objects and collect all contained objects with reference guests pointing to the object passed as parameter. 
    * 
    * @param value The object required as guests neighbor of the collected results. 
    * 
    * @return Set of Person objects referring to value via guests
    */
   public PartySet filterGuests(Object value)
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
      
      PartySet answer = new PartySet();
      
      for (Party obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getGuests()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Party object passed as parameter to the Guests attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Guests attributes.
    */
   public PartySet withGuests(Person value)
   {
      for (Party obj : this)
      {
         obj.withGuests(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the Party object passed as parameter from the Guests attribute of each of it. 
    * 
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public PartySet withoutGuests(Person value)
   {
      for (Party obj : this)
      {
         obj.withoutGuests(value);
      }
      
      return this;
   }

}
