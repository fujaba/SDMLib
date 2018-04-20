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

import java.util.Collection;

import org.sdmlib.models.modelsets.SDMSet;
import org.sdmlib.models.modelsets.intList;
import org.sdmlib.test.examples.roombook.Building;
import org.sdmlib.test.examples.roombook.Floor;

import de.uniks.networkparser.list.ObjectSet;
import de.uniks.networkparser.list.StringList;
import de.uniks.networkparser.list.NumberList;
import org.sdmlib.test.examples.roombook.util.BuildingSet;

public class FloorSet extends SDMSet<Floor>
{

   public static final FloorSet EMPTY_SET = new FloorSet().withFlag(FloorSet.READONLY);


   public FloorPO filterFloorPO()
   {
      return new FloorPO(this.toArray(new Floor[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.test.examples.roombook.Floor";
   }


   @SuppressWarnings("unchecked")
   public FloorSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Floor>)value);
      }
      else if (value != null)
      {
         this.add((Floor) value);
      }
      
      return this;
   }
   
   public FloorSet without(Floor value)
   {
      this.remove(value);
      return this;
   }

   /**
    * Loop through the current set of Floor objects and collect a list of the level attribute values. 
    * 
    * @return List of int objects reachable via level attribute
    */
   public intList getLevel()
   {
      intList result = new intList();
      
      for (Floor obj : this)
      {
         result.add(obj.getLevel());
      }
      
      return result;
   }


   /**
    * Loop through the current set of Floor objects and collect those Floor objects where the level attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Floor objects that match the parameter
    */
   public FloorSet filterLevel(int value)
   {
      FloorSet result = new FloorSet();
      
      for (Floor obj : this)
      {
         if (value == obj.getLevel())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Floor objects and collect those Floor objects where the level attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Floor objects that match the parameter
    */
   public FloorSet filterLevel(int lower, int upper)
   {
      FloorSet result = new FloorSet();
      
      for (Floor obj : this)
      {
         if (lower <= obj.getLevel() && obj.getLevel() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Floor objects and assign value to the level attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of Floor objects now with new attribute values.
    */
   public FloorSet withLevel(int value)
   {
      for (Floor obj : this)
      {
         obj.setLevel(value);
      }
      
      return this;
   }


   /**
    * Loop through the current set of Floor objects and collect a list of the name attribute values. 
    * 
    * @return List of String objects reachable via name attribute
    */
   public StringList getName()
   {
      StringList result = new StringList();
      
      for (Floor obj : this)
      {
         result.add(obj.getName());
      }
      
      return result;
   }


   /**
    * Loop through the current set of Floor objects and collect those Floor objects where the name attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Floor objects that match the parameter
    */
   public FloorSet filterName(String value)
   {
      FloorSet result = new FloorSet();
      
      for (Floor obj : this)
      {
         if (value.equals(obj.getName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Floor objects and collect those Floor objects where the name attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Floor objects that match the parameter
    */
   public FloorSet filterName(String lower, String upper)
   {
      FloorSet result = new FloorSet();
      
      for (Floor obj : this)
      {
         if (lower.compareTo(obj.getName()) <= 0 && obj.getName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Floor objects and assign value to the name attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of Floor objects now with new attribute values.
    */
   public FloorSet withName(String value)
   {
      for (Floor obj : this)
      {
         obj.setName(value);
      }
      
      return this;
   }


   /**
    * Loop through the current set of Floor objects and collect a list of the guest attribute values. 
    * 
    * @return List of String objects reachable via guest attribute
    */
   public StringList getGuest()
   {
      StringList result = new StringList();
      
      for (Floor obj : this)
      {
         result.add(obj.getGuest());
      }
      
      return result;
   }


   /**
    * Loop through the current set of Floor objects and collect those Floor objects where the guest attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Floor objects that match the parameter
    */
   public FloorSet filterGuest(String value)
   {
      FloorSet result = new FloorSet();
      
      for (Floor obj : this)
      {
         if (value.equals(obj.getGuest()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Floor objects and collect those Floor objects where the guest attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Floor objects that match the parameter
    */
   public FloorSet filterGuest(String lower, String upper)
   {
      FloorSet result = new FloorSet();
      
      for (Floor obj : this)
      {
         if (lower.compareTo(obj.getGuest()) <= 0 && obj.getGuest().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Floor objects and assign value to the guest attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of Floor objects now with new attribute values.
    */
   public FloorSet withGuest(String value)
   {
      for (Floor obj : this)
      {
         obj.setGuest(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Floor objects and collect a set of the Building objects reached via buildings. 
    * 
    * @return Set of Building objects reachable via buildings
    */
   public BuildingSet getBuildings()
   {
      BuildingSet result = new BuildingSet();
      
      for (Floor obj : this)
      {
         result.with(obj.getBuildings());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Floor objects and collect all contained objects with reference buildings pointing to the object passed as parameter. 
    * 
    * @param value The object required as buildings neighbor of the collected results. 
    * 
    * @return Set of Building objects referring to value via buildings
    */
   public FloorSet filterBuildings(Object value)
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
      
      FloorSet answer = new FloorSet();
      
      for (Floor obj : this)
      {
         if (neighbors.contains(obj.getBuildings()) || (neighbors.isEmpty() && obj.getBuildings() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Floor object passed as parameter to the Buildings attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Buildings attributes.
    */
   public FloorSet withBuildings(Building value)
   {
      for (Floor obj : this)
      {
         obj.withBuildings(value);
      }
      
      return this;
   }


   public FloorSet()
   {
      // empty
   }

   public FloorSet(Floor... objects)
   {
      for (Floor obj : objects)
      {
         this.add(obj);
      }
   }

   public FloorSet(Collection<Floor> objects)
   {
      this.addAll(objects);
   }


   public FloorPO createFloorPO()
   {
      return new FloorPO(this.toArray(new Floor[this.size()]));
   }

   /**
    * Loop through the current set of Floor objects and collect those Floor objects where the level attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Floor objects that match the parameter
    */
   public FloorSet createLevelCondition(int value)
   {
      FloorSet result = new FloorSet();
      
      for (Floor obj : this)
      {
         if (value == obj.getLevel())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Floor objects and collect those Floor objects where the level attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Floor objects that match the parameter
    */
   public FloorSet createLevelCondition(int lower, int upper)
   {
      FloorSet result = new FloorSet();
      
      for (Floor obj : this)
      {
         if (lower <= obj.getLevel() && obj.getLevel() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Floor objects and collect those Floor objects where the name attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Floor objects that match the parameter
    */
   public FloorSet createNameCondition(String value)
   {
      FloorSet result = new FloorSet();
      
      for (Floor obj : this)
      {
         if (value.equals(obj.getName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Floor objects and collect those Floor objects where the name attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Floor objects that match the parameter
    */
   public FloorSet createNameCondition(String lower, String upper)
   {
      FloorSet result = new FloorSet();
      
      for (Floor obj : this)
      {
         if (lower.compareTo(obj.getName()) <= 0 && obj.getName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Floor objects and collect those Floor objects where the guest attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Floor objects that match the parameter
    */
   public FloorSet createGuestCondition(String value)
   {
      FloorSet result = new FloorSet();
      
      for (Floor obj : this)
      {
         if (value.equals(obj.getGuest()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Floor objects and collect those Floor objects where the guest attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Floor objects that match the parameter
    */
   public FloorSet createGuestCondition(String lower, String upper)
   {
      FloorSet result = new FloorSet();
      
      for (Floor obj : this)
      {
         if (lower.compareTo(obj.getGuest()) <= 0 && obj.getGuest().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }



   @Override
   public FloorSet getNewList(boolean keyValue)
   {
      return new FloorSet();
   }
}
