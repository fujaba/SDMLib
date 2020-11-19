/*
   Copyright (c) 2015 philipp-pc
   
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
   
package org.sdmlib.test.model.refactoring.util;

import java.util.Collection;
import java.util.Collections;

import org.sdmlib.test.model.refactoring.Ludo;
import org.sdmlib.test.model.refactoring.Player;

import de.uniks.networkparser.list.ObjectSet;
import de.uniks.networkparser.list.SimpleSet;
import de.uniks.networkparser.list.StringList;

public class LudoSet extends SimpleSet<Ludo>
{

   public static final LudoSet EMPTY_SET = new LudoSet().withFlag(LudoSet.READONLY);


   public LudoPO hasLudoPO()
   {
      return new LudoPO(this.toArray(new Ludo[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.test.model.refactoring.Ludo";
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

   
   


   


   


   


   

   /**
    * Loop through the current set of Ludo objects and collect a set of the Player objects reached via players. 
    * 
    * @return Set of Player objects reachable via players
    */
   public PlayerSet getPlayers()
   {
      PlayerSet result = new PlayerSet();
      
      for (Ludo obj : this)
      {
         result.addAll(obj.getPlayers());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Ludo objects and collect all contained objects with reference players pointing to the object passed as parameter. 
    * 
    * @param value The object required as players neighbor of the collected results. 
    * 
    * @return Set of Player objects referring to value via players
    */
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

   /**
    * Loop through current set of ModelType objects and attach the Ludo object passed as parameter to the Players attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Players attributes.
    */
   public LudoSet withPlayers(Player value)
   {
      for (Ludo obj : this)
      {
         obj.withPlayers(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the Ludo object passed as parameter from the Players attribute of each of it. 
    * 
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public LudoSet withoutPlayers(Player value)
   {
      for (Ludo obj : this)
      {
         obj.withoutPlayers(value);
      }
      
      return this;
   }

   
   //==========================================================================
   
   public LudoSet init(String p)
   {
      for (Ludo obj : this)
      {
         obj.init(p);
      }
      return this;
   }


   /**
    * Loop through the current set of Ludo objects and collect a list of the location attribute values. 
    * 
    * @return List of String objects reachable via location attribute
    */
   public StringList getLocation()
   {
      StringList result = new StringList();
      
      for (Ludo obj : this)
      {
         result.add(obj.getLocation());
      }
      
      return result;
   }


   /**
    * Loop through the current set of Ludo objects and collect those Ludo objects where the location attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Ludo objects that match the parameter
    */
   public LudoSet hasLocation(String value)
   {
      LudoSet result = new LudoSet();
      
      for (Ludo obj : this)
      {
         if (value.equals(obj.getLocation()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Ludo objects and collect those Ludo objects where the location attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Ludo objects that match the parameter
    */
   public LudoSet hasLocation(String lower, String upper)
   {
      LudoSet result = new LudoSet();
      
      for (Ludo obj : this)
      {
         if (lower.compareTo(obj.getLocation()) <= 0 && obj.getLocation().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Ludo objects and assign value to the location attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of Ludo objects now with new attribute values.
    */
   public LudoSet withLocation(String value)
   {
      for (Ludo obj : this)
      {
         obj.setLocation(value);
      }
      
      return this;
   }



   public LudoPO filterLudoPO()
   {
      return new LudoPO(this.toArray(new Ludo[this.size()]));
   }

   /**
    * Loop through the current set of Ludo objects and collect those Ludo objects where the location attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Ludo objects that match the parameter
    */
   public LudoSet filterLocation(String value)
   {
      LudoSet result = new LudoSet();
      
      for (Ludo obj : this)
      {
         if (value.equals(obj.getLocation()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Ludo objects and collect those Ludo objects where the location attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Ludo objects that match the parameter
    */
   public LudoSet filterLocation(String lower, String upper)
   {
      LudoSet result = new LudoSet();
      
      for (Ludo obj : this)
      {
         if (lower.compareTo(obj.getLocation()) <= 0 && obj.getLocation().compareTo(upper) <= 0)
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
    * Loop through the current set of Ludo objects and collect those Ludo objects where the location attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Ludo objects that match the parameter
    */
   public LudoSet createLocationCondition(String value)
   {
      LudoSet result = new LudoSet();
      
      for (Ludo obj : this)
      {
         if (value.equals(obj.getLocation()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Ludo objects and collect those Ludo objects where the location attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Ludo objects that match the parameter
    */
   public LudoSet createLocationCondition(String lower, String upper)
   {
      LudoSet result = new LudoSet();
      
      for (Ludo obj : this)
      {
         if (lower.compareTo(obj.getLocation()) <= 0 && obj.getLocation().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

}
