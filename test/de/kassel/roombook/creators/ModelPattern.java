package de.kassel.roombook.creators;

import org.sdmlib.models.pattern.Pattern;
import de.kassel.roombook.creators.BuildingPO;
import de.kassel.roombook.Building;
import de.kassel.roombook.creators.FloorPO;
import de.kassel.roombook.Floor;

public class ModelPattern extends Pattern
{
   public ModelPattern()
   {
      super(CreatorCreator.createIdMap("hg"));
   }
   
   public ModelPattern startCreate()
   {
      super.startCreate();
      return this;
   }

   public BuildingPO hasElementBuildingPO()
   {
      BuildingPO value = new BuildingPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public BuildingPO hasElementBuildingPO(Building hostGraphObject)
   {
      BuildingPO value = new BuildingPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public FloorPO hasElementFloorPO()
   {
      FloorPO value = new FloorPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public FloorPO hasElementFloorPO(Floor hostGraphObject)
   {
      FloorPO value = new FloorPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

}


