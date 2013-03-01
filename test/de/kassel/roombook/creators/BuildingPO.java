package de.kassel.roombook.creators;

import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.models.pattern.PatternObject;

import de.kassel.roombook.Building;

public class BuildingPO extends PatternObject
{
   public BuildingPO startNAC()
   {
      return (BuildingPO) super.startNAC();
   }
   
   public BuildingPO endNAC()
   {
      return (BuildingPO) super.endNAC();
   }
   
   public BuildingPO hasName(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Building.PROPERTY_NAME)
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
         return ((Building) getCurrentMatch()).getName();
      }
      return null;
   }
   
   public FloorPO hasHas()
   {
      FloorPO result = new FloorPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Building.PROPERTY_HAS, result);
      
      return result;   }
   
   public BuildingPO hasHas(FloorPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Building.PROPERTY_HAS)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public FloorSet getHas()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Building) this.getCurrentMatch()).getHas();
      }
      return null;
   }
   
}

