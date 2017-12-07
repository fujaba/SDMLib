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
   
package org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.util;

import java.util.Collection;
import java.util.Collections;

import org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.LBank;
import org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.LBoat;
import org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.LRiver;

import de.uniks.networkparser.list.ObjectSet;
import de.uniks.networkparser.list.SimpleSet;
   /**
    * 
    * @see <a href='../../../../../../../../../../src/test/java/org/sdmlib/test/examples/reachabilitygraphs/ReachabilityGraphFerrymansProblemExample.java'>ReachabilityGraphFerrymansProblemExample.java</a>
 */
   public class LRiverSet extends SimpleSet<LRiver>
{
	public Class<?> getTypClass() {
		return LRiver.class;
	}

     /**
    * 
    * @see <a href='../../../../../../../../../../src/test/java/org/sdmlib/test/examples/reachabilitygraphs/ReachabilityGraphFerrymansProblemExample.java'>ReachabilityGraphFerrymansProblemExample.java</a>
 */
   public LRiverSet()
   {
      // empty
   }

     /**
    * 
    * @see <a href='../../../../../../../../../../src/test/java/org/sdmlib/test/examples/reachabilitygraphs/ReachabilityGraphFerrymansProblemExample.java'>ReachabilityGraphFerrymansProblemExample.java</a>
 */
   public LRiverSet(LRiver... objects)
   {
      for (LRiver obj : objects)
      {
         this.add(obj);
      }
   }

     /**
    * 
    * @see <a href='../../../../../../../../../../src/test/java/org/sdmlib/test/examples/reachabilitygraphs/ReachabilityGraphFerrymansProblemExample.java'>ReachabilityGraphFerrymansProblemExample.java</a>
 */
   public LRiverSet(Collection<LRiver> objects)
   {
      this.addAll(objects);
   }

   public static final LRiverSet EMPTY_SET = new LRiverSet().withFlag(LRiverSet.READONLY);


   public LRiverPO createLRiverPO()
   {
      return new LRiverPO(this.toArray(new LRiver[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.LRiver";
   }


   @Override
   public LRiverSet getNewList(boolean keyValue)
   {
      return new LRiverSet();
   }

   @SuppressWarnings("unchecked")
   public LRiverSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<LRiver>)value);
      }
      else if (value != null)
      {
         this.add((LRiver) value);
      }
      
      return this;
   }
   
   public LRiverSet without(LRiver value)
   {
      this.remove(value);
      return this;
   }

   /**
    * Loop through the current set of LRiver objects and collect a set of the LBoat objects reached via boat. 
    * 
    * @return Set of LBoat objects reachable via boat
    */
   public LBoatSet getBoat()
   {
      LBoatSet result = new LBoatSet();
      
      for (LRiver obj : this)
      {
         result.with(obj.getBoat());
      }
      
      return result;
   }

   /**
    * Loop through the current set of LRiver objects and collect all contained objects with reference boat pointing to the object passed as parameter. 
    * 
    * @param value The object required as boat neighbor of the collected results. 
    * 
    * @return Set of LBoat objects referring to value via boat
    */
   public LRiverSet filterBoat(Object value)
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
      
      LRiverSet answer = new LRiverSet();
      
      for (LRiver obj : this)
      {
         if (neighbors.contains(obj.getBoat()) || (neighbors.isEmpty() && obj.getBoat() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the LRiver object passed as parameter to the Boat attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Boat attributes.
    */
   public LRiverSet withBoat(LBoat value)
   {
      for (LRiver obj : this)
      {
         obj.withBoat(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of LRiver objects and collect a set of the LBank objects reached via banks. 
    * 
    * @return Set of LBank objects reachable via banks
    * @see <a href='../../../../../../../../../../src/test/java/org/sdmlib/test/examples/reachabilitygraphs/ReachabilityGraphFerrymansProblemExample.java'>ReachabilityGraphFerrymansProblemExample.java</a>
 */
   public LBankSet getBanks()
   {
      LBankSet result = new LBankSet();
      
      for (LRiver obj : this)
      {
         result.with(obj.getBanks());
      }
      
      return result;
   }

   /**
    * Loop through the current set of LRiver objects and collect all contained objects with reference banks pointing to the object passed as parameter. 
    * 
    * @param value The object required as banks neighbor of the collected results. 
    * 
    * @return Set of LBank objects referring to value via banks
    */
   public LRiverSet filterBanks(Object value)
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
      
      LRiverSet answer = new LRiverSet();
      
      for (LRiver obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getBanks()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the LRiver object passed as parameter to the Banks attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Banks attributes.
    */
   public LRiverSet withBanks(LBank value)
   {
      for (LRiver obj : this)
      {
         obj.withBanks(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the LRiver object passed as parameter from the Banks attribute of each of it. 
    * 
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public LRiverSet withoutBanks(LBank value)
   {
      for (LRiver obj : this)
      {
         obj.withoutBanks(value);
      }
      
      return this;
   }

}
