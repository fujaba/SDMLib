package de.kassel.roombook.creators;

import java.util.LinkedHashSet;
import de.kassel.roombook.Building;
import org.sdmlib.models.modelsets.StringList;
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

}



