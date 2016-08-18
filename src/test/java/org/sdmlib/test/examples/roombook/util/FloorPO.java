package org.sdmlib.test.examples.roombook.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.examples.roombook.Floor;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.test.examples.roombook.util.BuildingPO;
import org.sdmlib.test.examples.roombook.util.FloorPO;
import org.sdmlib.test.examples.roombook.Building;
import org.sdmlib.models.pattern.Pattern;

public class FloorPO extends PatternObject<FloorPO, Floor>
{

    public FloorSet allMatches()
   {
      this.setDoAllMatches(true);
      
      FloorSet matches = new FloorSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Floor) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public FloorPO(){
      newInstance(null);
   }

   public FloorPO(Floor... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }
   public FloorPO filterLevel(int value)
   {
      new AttributeConstraint()
      .withAttrName(Floor.PROPERTY_LEVEL)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public FloorPO filterLevel(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(Floor.PROPERTY_LEVEL)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public FloorPO createLevel(int value)
   {
      this.startCreate().filterLevel(value).endCreate();
      return this;
   }
   
   public int getLevel()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Floor) getCurrentMatch()).getLevel();
      }
      return 0;
   }
   
   public FloorPO withLevel(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Floor) getCurrentMatch()).setLevel(value);
      }
      return this;
   }
   
   public FloorPO filterName(String value)
   {
      new AttributeConstraint()
      .withAttrName(Floor.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public FloorPO filterName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Floor.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public FloorPO createName(String value)
   {
      this.startCreate().filterName(value).endCreate();
      return this;
   }
   
   public String getName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Floor) getCurrentMatch()).getName();
      }
      return null;
   }
   
   public FloorPO withName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Floor) getCurrentMatch()).setName(value);
      }
      return this;
   }
   
   public FloorPO filterGuest(String value)
   {
      new AttributeConstraint()
      .withAttrName(Floor.PROPERTY_GUEST)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public FloorPO filterGuest(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Floor.PROPERTY_GUEST)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public FloorPO createGuest(String value)
   {
      this.startCreate().filterGuest(value).endCreate();
      return this;
   }
   
   public String getGuest()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Floor) getCurrentMatch()).getGuest();
      }
      return null;
   }
   
   public FloorPO withGuest(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Floor) getCurrentMatch()).setGuest(value);
      }
      return this;
   }
   
   public BuildingPO filterBuildings()
   {
      BuildingPO result = new BuildingPO(new org.sdmlib.test.examples.roombook.Building[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Floor.PROPERTY_BUILDINGS, result);
      
      return result;
   }

   public BuildingPO createBuildings()
   {
      return this.startCreate().filterBuildings().endCreate();
   }

   public FloorPO filterBuildings(BuildingPO tgt)
   {
      return hasLinkConstraint(tgt, Floor.PROPERTY_BUILDINGS);
   }

   public FloorPO createBuildings(BuildingPO tgt)
   {
      return this.startCreate().filterBuildings(tgt).endCreate();
   }

   public Building getBuildings()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Floor) this.getCurrentMatch()).getBuildings();
      }
      return null;
   }


   public FloorPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public FloorPO createLevelCondition(int value)
   {
      new AttributeConstraint()
      .withAttrName(Floor.PROPERTY_LEVEL)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public FloorPO createLevelCondition(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(Floor.PROPERTY_LEVEL)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public FloorPO createLevelAssignment(int value)
   {
      new AttributeConstraint()
      .withAttrName(Floor.PROPERTY_LEVEL)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public FloorPO createNameCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(Floor.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public FloorPO createNameCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Floor.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public FloorPO createNameAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(Floor.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public FloorPO createGuestCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(Floor.PROPERTY_GUEST)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public FloorPO createGuestCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Floor.PROPERTY_GUEST)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public FloorPO createGuestAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(Floor.PROPERTY_GUEST)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public BuildingPO createBuildingsPO()
   {
      BuildingPO result = new BuildingPO(new org.sdmlib.test.examples.roombook.Building[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Floor.PROPERTY_BUILDINGS, result);
      
      return result;
   }

   public BuildingPO createBuildingsPO(String modifier)
   {
      BuildingPO result = new BuildingPO(new org.sdmlib.test.examples.roombook.Building[]{});
      
      result.setModifier(modifier);
      super.hasLink(Floor.PROPERTY_BUILDINGS, result);
      
      return result;
   }

   public FloorPO createBuildingsLink(BuildingPO tgt)
   {
      return hasLinkConstraint(tgt, Floor.PROPERTY_BUILDINGS);
   }

   public FloorPO createBuildingsLink(BuildingPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Floor.PROPERTY_BUILDINGS, modifier);
   }

}
