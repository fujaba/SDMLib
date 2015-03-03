/*
   Copyright (c) 2014 zuendorf 
   
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
   
package de.kassel.roombook.util;

import java.util.Collection;

import org.sdmlib.models.modelsets.ObjectSet;
import org.sdmlib.models.modelsets.SDMSet;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.models.modelsets.intList;

import de.kassel.roombook.Building;
import de.kassel.roombook.Floor;

public class FloorSet extends SDMSet<Floor>
{
        private static final long serialVersionUID = 1L;


   public FloorPO hasFloorPO()
   {
      return new FloorPO(this.toArray(new Floor[this.size()]));
   }


   @Override
   public String getEntryType()
   {
      return "de.kassel.roombook.Floor";
   }


   public FloorSet with(Object value)
   {
      if (value instanceof java.util.Collection)
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

   public intList getLevel()
   {
      intList result = new intList();
      
      for (Floor obj : this)
      {
         result.add(obj.getLevel());
      }
      
      return result;
   }

   public FloorSet hasLevel(int value)
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

   public FloorSet withLevel(int value)
   {
      for (Floor obj : this)
      {
         obj.setLevel(value);
      }
      
      return this;
   }

   public StringList getName()
   {
      StringList result = new StringList();
      
      for (Floor obj : this)
      {
         result.add(obj.getName());
      }
      
      return result;
   }

   public FloorSet hasName(String value)
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

   public FloorSet withName(String value)
   {
      for (Floor obj : this)
      {
         obj.setName(value);
      }
      
      return this;
   }

   public StringList getGuest()
   {
      StringList result = new StringList();
      
      for (Floor obj : this)
      {
         result.add(obj.getGuest());
      }
      
      return result;
   }

   public FloorSet hasGuest(String value)
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

   public FloorSet withGuest(String value)
   {
      for (Floor obj : this)
      {
         obj.setGuest(value);
      }
      
      return this;
   }

   public BuildingSet getBuildings()
   {
      BuildingSet result = new BuildingSet();
      
      for (Floor obj : this)
      {
         result.add(obj.getBuildings());
      }
      
      return result;
   }

   public FloorSet hasBuildings(Object value)
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
         if (neighbors.contains(obj.getBuildings()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public FloorSet withBuildings(Building value)
   {
      for (Floor obj : this)
      {
         obj.withBuildings(value);
      }
      
      return this;
   }


   public static final FloorSet EMPTY_SET = new FloorSet().withReadOnly(true);
}
