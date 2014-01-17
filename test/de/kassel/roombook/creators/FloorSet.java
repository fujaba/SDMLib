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
   
package de.kassel.roombook.creators;

import java.util.Collection;
import java.util.LinkedHashSet;

import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.models.modelsets.intList;

import de.kassel.roombook.Building;
import de.kassel.roombook.Floor;

public class FloorSet extends LinkedHashSet<Floor>
{
   public StringList getId()
   {
      StringList result = new StringList();
      
      for (Floor obj : this)
      {
         result.add(obj.getId());
      }
      
      return result;
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

   public BuildingSet getBuildings()
   {
      BuildingSet result = new BuildingSet();
      
      for (Floor obj : this)
      {
         result.add(obj.getBuildings());
      }
      
      return result;
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

   public FloorSet withLevel(int value)
   {
      for (Floor obj : this)
      {
         obj.withLevel(value);
      }
      
      return this;
   }

   public FloorSet withName(String value)
   {
      for (Floor obj : this)
      {
         obj.withName(value);
      }
      
      return this;
   }

   public FloorSet withBuildings(Building value)
   {
      for (Floor obj : this)
      {
         obj.withBuildings(value);
      }
      
      return this;
   }



   public String toString()
   {
      StringList stringList = new StringList();
      
      for (Floor elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }




   public String getEntryType()
   {
      return "de.kassel.roombook.Floor";
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

   public FloorSet withGuest(String value)
   {
      for (Floor obj : this)
      {
         obj.withGuest(value);
      }
      
      return this;
   }



   public FloorPO startModelPattern()
   {
      de.kassel.roombook.creators.ModelPattern pattern = new de.kassel.roombook.creators.ModelPattern();
      
      FloorPO patternObject = pattern.hasElementFloorPO();
      
      patternObject.withCandidates(this.clone());
      
      pattern.setHasMatch(true);
      pattern.findMatch();
      
      return patternObject;
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

}









