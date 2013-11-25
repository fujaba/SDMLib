package de.kassel.roombook.creators;

import org.sdmlib.models.objects.GenericObjectsTest;
import org.sdmlib.models.objects.creators.GenericObjectsTestPO;
import org.sdmlib.models.pattern.Pattern;

import de.kassel.roombook.Building;
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

   public ModelPattern startDestroy()
   {
      super.startDestroy();
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
      value.setModifier(this.getModifier());
      
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
      value.setModifier(this.getModifier());
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public GenericObjectsTestPO hasElementGenericObjectsTestPO()
   {
      GenericObjectsTestPO value = new GenericObjectsTestPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public GenericObjectsTestPO hasElementGenericObjectsTestPO(GenericObjectsTest hostGraphObject)
   {
      GenericObjectsTestPO value = new GenericObjectsTestPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

}


