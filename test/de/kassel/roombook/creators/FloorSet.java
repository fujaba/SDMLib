package de.kassel.roombook.creators;

import java.util.LinkedHashSet;
import de.kassel.roombook.Floor;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.models.modelsets.intList;
import de.kassel.roombook.Building;

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

}


