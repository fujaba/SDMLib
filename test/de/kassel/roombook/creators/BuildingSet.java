package de.kassel.roombook.creators;

import java.util.LinkedHashSet;

import org.sdmlib.models.modelsets.StringList;

import de.kassel.roombook.Building;
import de.kassel.roombook.Floor;

public class BuildingSet extends LinkedHashSet<Building>
{
   public StringList getId()
   {
      StringList result = new StringList();
      
      for (Building obj : this)
      {
         result.add(obj.getId());
      }
      
      return result;
   }

   public FloorSet getHas()
   {
      FloorSet result = new FloorSet();
      
      for (Building obj : this)
      {
         result.addAll(obj.getHas());
      }
      
      return result;
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

   public BuildingSet withName(String value)
   {
      for (Building obj : this)
      {
         obj.withName(value);
      }
      
      return this;
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



   public String toString()
   {
      StringList stringList = new StringList();
      
      for (Building elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }


   public String getEntryType()
   {
      return "de.kassel.roombook.Building";
   }


   public BuildingSet with(Building value)
   {
      this.add(value);
      return this;
   }
   
   public BuildingSet without(Building value)
   {
      this.remove(value);
      return this;
   }
}








