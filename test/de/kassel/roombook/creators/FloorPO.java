package de.kassel.roombook.creators;

import org.sdmlib.models.pattern.PatternObject;
import de.kassel.roombook.Floor;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternLink;
import de.kassel.roombook.creators.BuildingPO;
import org.sdmlib.models.pattern.LinkConstraint;
import de.kassel.roombook.creators.FloorPO;
import de.kassel.roombook.Building;
import de.kassel.roombook.creators.FloorSet;

public class FloorPO extends PatternObject
{
   public FloorPO startNAC()
   {
      return (FloorPO) super.startNAC();
   }
   
   public FloorPO endNAC()
   {
      return (FloorPO) super.endNAC();
   }
   
   public FloorPO hasLevel(int value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Floor.PROPERTY_LEVEL)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
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
   
   public FloorPO hasName(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Floor.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
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
   
   public BuildingPO hasBuildings()
   {
      BuildingPO result = new BuildingPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Floor.PROPERTY_BUILDINGS, result);
      
      return result;   }
   
   public FloorPO hasBuildings(BuildingPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Floor.PROPERTY_BUILDINGS)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public Building getBuildings()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Floor) this.getCurrentMatch()).getBuildings();
      }
      return null;
   }
   
   public FloorPO hasGuest(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Floor.PROPERTY_GUEST)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
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
   
}


