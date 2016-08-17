/*
   Copyright (c) 2016 zuendorf
   
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
   
package org.sdmlib.test.examples.roombook.util;

import org.sdmlib.models.modelsets.SDMSet;
import org.sdmlib.test.examples.roombook.Building;
import java.util.Collection;
import de.uniks.networkparser.interfaces.Condition;
import de.uniks.networkparser.list.StringList;
import de.uniks.networkparser.list.ObjectSet;
import java.util.Collections;
import org.sdmlib.test.examples.roombook.util.FloorSet;
import org.sdmlib.test.examples.roombook.Floor;

public class BuildingSet extends SDMSet<Building>
{

   public static final BuildingSet EMPTY_SET = new BuildingSet().withFlag(BuildingSet.READONLY);


   public BuildingPO filterBuildingPO()
   {
      return new BuildingPO(this.toArray(new Building[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.test.examples.roombook.Building";
   }


   @SuppressWarnings("unchecked")
   public BuildingSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Building>)value);
      }
      else if (value != null)
      {
         this.add((Building) value);
      }
      
      return this;
   }
   
   public BuildingSet without(Building value)
   {
      this.remove(value);
      return this;
   }

   @Override
   public BuildingSet filter(Condition<Building> newValue) {
      BuildingSet filterList = new BuildingSet();
      filterItems(filterList, newValue);
      return filterList;
   }

   /**
    * Loop through the current set of Building objects and collect a list of the name attribute values. 
    * 
    * @return List of String objects reachable via name attribute
    */
   public StringList getName()
   {
      StringList result = new StringList();
      
      for (Building obj : this)
      {
         result.add(obj.getName());
      }
      
      return result;
   }


   /**
    * Loop through the current set of Building objects and collect those Building objects where the name attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Building objects that match the parameter
    */
   public BuildingSet filterName(String value)
   {
      BuildingSet result = new BuildingSet();
      
      for (Building obj : this)
      {
         if (value.equals(obj.getName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Building objects and collect those Building objects where the name attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Building objects that match the parameter
    */
   public BuildingSet filterName(String lower, String upper)
   {
      BuildingSet result = new BuildingSet();
      
      for (Building obj : this)
      {
         if (lower.compareTo(obj.getName()) <= 0 && obj.getName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Building objects and assign value to the name attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of Building objects now with new attribute values.
    */
   public BuildingSet withName(String value)
   {
      for (Building obj : this)
      {
         obj.setName(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Building objects and collect a set of the Floor objects reached via has. 
    * 
    * @return Set of Floor objects reachable via has
    */
   public FloorSet getHas()
   {
      FloorSet result = new FloorSet();
      
      for (Building obj : this)
      {
         result.with(obj.getHas());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Building objects and collect all contained objects with reference has pointing to the object passed as parameter. 
    * 
    * @param value The object required as has neighbor of the collected results. 
    * 
    * @return Set of Floor objects referring to value via has
    */
   public BuildingSet filterHas(Object value)
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
      
      BuildingSet answer = new BuildingSet();
      
      for (Building obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getHas()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Building object passed as parameter to the Has attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Has attributes.
    */
   public BuildingSet withHas(Floor value)
   {
      for (Building obj : this)
      {
         obj.withHas(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the Building object passed as parameter from the Has attribute of each of it. 
    * 
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public BuildingSet withoutHas(Floor value)
   {
      for (Building obj : this)
      {
         obj.withoutHas(value);
      }
      
      return this;
   }


   public BuildingSet()
   {
      // empty
   }

   public BuildingSet(Building... objects)
   {
      for (Building obj : objects)
      {
         this.add(obj);
      }
   }

   public BuildingSet(Collection<Building> objects)
   {
      this.addAll(objects);
   }
}
