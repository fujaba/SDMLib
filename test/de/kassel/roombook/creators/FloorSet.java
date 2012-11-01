package de.kassel.roombook.creators;

import java.util.LinkedHashSet;
import de.kassel.roombook.Floor;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.models.modelsets.intList;
import de.kassel.roombook.Building;
import java.util.List;

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


   public FloorSet with(Floor value)
   {
      this.add(value);
      return this;
   }
   
   public FloorSet without(Floor value)
   {
      this.remove(value);
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

   public FloorSet withGuest(String value)
   {
      for (Floor obj : this)
      {
         obj.withGuest(value);
      }
      
      return this;
   }

}








