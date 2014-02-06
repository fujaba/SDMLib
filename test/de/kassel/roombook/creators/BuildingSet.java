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

import org.sdmlib.models.modelsets.SDMSet;
import de.kassel.roombook.Building;
import org.sdmlib.models.modelsets.StringList;
import java.util.Collection;
import java.util.List;
import de.kassel.roombook.creators.FloorSet;
import java.util.Collections;
import org.sdmlib.models.modelsets.ObjectSet;
import de.kassel.roombook.Floor;

public class BuildingSet extends SDMSet<Building>
{


   public BuildingPO startModelPattern()
   {
      de.kassel.roombook.creators.ModelPattern pattern = new de.kassel.roombook.creators.ModelPattern();
      
      BuildingPO patternObject = pattern.hasElementBuildingPO();
      
      patternObject.withCandidates(this.clone());
      
      pattern.setHasMatch(true);
      pattern.findMatch();
      
      return patternObject;
   }


   @Override
   public String getEntryType()
   {
      return "de.kassel.roombook.Building";
   }


   public BuildingSet with(Object value)
   {
      if (value instanceof java.util.Collection)
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

   public StringList getName()
   {
      StringList result = new StringList();
      
      for (Building obj : this)
      {
         result.add(obj.getName());
      }
      
      return result;
   }

   public BuildingSet hasName(String value)
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

   public BuildingSet hasName(String lower, String upper)
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

   public BuildingSet withName(String value)
   {
      for (Building obj : this)
      {
         obj.setName(value);
      }
      
      return this;
   }

   public FloorSet getHas()
   {
      FloorSet result = new FloorSet();
      
      for (Building obj : this)
      {
         result.with(obj.getHas());
      }
      
      return result;
   }

   public BuildingSet hasHas(Object value)
   {
      ObjectSet neighbors = new ObjectSet();

      if (value instanceof Collection)
      {
         neighbors.addAll((Collection) value);
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

   public BuildingSet withHas(Floor value)
   {
      for (Building obj : this)
      {
         obj.withHas(value);
      }
      
      return this;
   }

   public BuildingSet withoutHas(Floor value)
   {
      for (Building obj : this)
      {
         obj.withoutHas(value);
      }
      
      return this;
   }

}

