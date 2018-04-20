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
import org.sdmlib.test.examples.reachabilitygraphs.unidirferrymansproblem.URiver;
import de.uniks.networkparser.interfaces.Condition;
import java.util.Collection;
import de.uniks.networkparser.list.ObjectSet;
import java.util.Collections;
import org.sdmlib.test.examples.reachabilitygraphs.unidirferrymansproblem.util.UBankSet;
import org.sdmlib.test.examples.reachabilitygraphs.unidirferrymansproblem.UBank;
import org.sdmlib.test.examples.reachabilitygraphs.unidirferrymansproblem.util.UBoatSet;
import org.sdmlib.test.examples.reachabilitygraphs.unidirferrymansproblem.UBoat;
   /**
    * 
    * @see <a href='../../../../../../../../../../src/test/java/org/sdmlib/test/examples/reachabilitygraphs/ReachabilityGraphFerrymansProblemExample.java'>ReachabilityGraphFerrymansProblemExample.java</a>
 * @see org.sdmlib.test.examples.reachabilitygraphs.ReachabilityGraphFerrymansProblemExample#FerrymansProblemLazyBackup
 */
   public class URiverSet extends SimpleSet<URiver>
{
	public Class<?> getTypClass() {
		return URiver.class;
	}

     /**
    * 
    * @see <a href='../../../../../../../../../../src/test/java/org/sdmlib/test/examples/reachabilitygraphs/ReachabilityGraphFerrymansProblemExample.java'>ReachabilityGraphFerrymansProblemExample.java</a>
 * @see org.sdmlib.test.examples.reachabilitygraphs.ReachabilityGraphFerrymansProblemExample#FerrymansProblemLazyBackup
 */
   public URiverSet()
   {
      // empty
   }

     /**
    * 
    * @see <a href='../../../../../../../../../../src/test/java/org/sdmlib/test/examples/reachabilitygraphs/ReachabilityGraphFerrymansProblemExample.java'>ReachabilityGraphFerrymansProblemExample.java</a>
 * @see org.sdmlib.test.examples.reachabilitygraphs.ReachabilityGraphFerrymansProblemExample#FerrymansProblemLazyBackup
 */
   public URiverSet(URiver... objects)
   {
      for (URiver obj : objects)
      {
         this.add(obj);
      }
   }

     /**
    * 
    * @see <a href='../../../../../../../../../../src/test/java/org/sdmlib/test/examples/reachabilitygraphs/ReachabilityGraphFerrymansProblemExample.java'>ReachabilityGraphFerrymansProblemExample.java</a>
 * @see org.sdmlib.test.examples.reachabilitygraphs.ReachabilityGraphFerrymansProblemExample#FerrymansProblemLazyBackup
 */
   public URiverSet(Collection<URiver> objects)
   {
      this.addAll(objects);
   }

   public static final URiverSet EMPTY_SET = new URiverSet().withFlag(URiverSet.READONLY);


   public URiverPO createURiverPO()
   {
      return new URiverPO(this.toArray(new URiver[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.test.examples.reachabilitygraphs.unidirferrymansproblem.URiver";
   }


   @Override
   public URiverSet getNewList(boolean keyValue)
   {
      return new URiverSet();
   }


   @SuppressWarnings("unchecked")
   public URiverSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<URiver>)value);
      }
      else if (value != null)
      {
         this.add((URiver) value);
      }
      
      return this;
   }
   
   public URiverSet without(URiver value)
   {
      this.remove(value);
      return this;
   }

   /**
    * Loop through the current set of URiver objects and collect a set of the UBank objects reached via banks. 
    * 
    * @return Set of UBank objects reachable via banks
    * @see <a href='../../../../../../../../../../src/test/java/org/sdmlib/test/examples/reachabilitygraphs/ReachabilityGraphFerrymansProblemExample.java'>ReachabilityGraphFerrymansProblemExample.java</a>
 * @see org.sdmlib.test.examples.reachabilitygraphs.ReachabilityGraphFerrymansProblemExample#FerrymansProblemLazyBackup
 */
   public UBankSet getBanks()
   {
      UBankSet result = new UBankSet();
      
      for (URiver obj : this)
      {
         result.with(obj.getBanks());
      }
      
      return result;
   }

   /**
    * Loop through the current set of URiver objects and collect all contained objects with reference banks pointing to the object passed as parameter. 
    * 
    * @param value The object required as banks neighbor of the collected results. 
    * 
    * @return Set of UBank objects referring to value via banks
    */
   public URiverSet filterBanks(Object value)
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
      
      URiverSet answer = new URiverSet();
      
      for (URiver obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getBanks()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the URiver object passed as parameter to the Banks attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Banks attributes.
    */
   public URiverSet withBanks(UBank value)
   {
      for (URiver obj : this)
      {
         obj.withBanks(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the URiver object passed as parameter from the Banks attribute of each of it. 
    * 
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public URiverSet withoutBanks(UBank value)
   {
      for (URiver obj : this)
      {
         obj.withoutBanks(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of URiver objects and collect a set of the UBoat objects reached via boat. 
    * 
    * @return Set of UBoat objects reachable via boat
    */
   public UBoatSet getBoat()
   {
      UBoatSet result = new UBoatSet();
      
      for (URiver obj : this)
      {
         result.with(obj.getBoat());
      }
      
      return result;
   }

   /**
    * Loop through the current set of URiver objects and collect all contained objects with reference boat pointing to the object passed as parameter. 
    * 
    * @param value The object required as boat neighbor of the collected results. 
    * 
    * @return Set of UBoat objects referring to value via boat
    */
   public URiverSet filterBoat(Object value)
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
      
      URiverSet answer = new URiverSet();
      
      for (URiver obj : this)
      {
         if (neighbors.contains(obj.getBoat()) || (neighbors.isEmpty() && obj.getBoat() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the URiver object passed as parameter to the Boat attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Boat attributes.
    */
   public URiverSet withBoat(UBoat value)
   {
      for (URiver obj : this)
      {
         obj.withBoat(value);
      }
      
      return this;
   }

}
