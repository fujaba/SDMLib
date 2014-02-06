package de.kassel.roombook.creators;

import org.sdmlib.models.pattern.PatternObject;
import de.kassel.roombook.Building;
import de.kassel.roombook.creators.BuildingSet;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternLink;
import de.kassel.roombook.creators.FloorPO;
import org.sdmlib.models.pattern.LinkConstraint;
import de.kassel.roombook.creators.BuildingPO;
import de.kassel.roombook.Floor;
import de.kassel.roombook.creators.FloorSet;

public class BuildingPO extends PatternObject<BuildingPO, Building>
{
   public BuildingSet allMatches()
   {
      this.setDoAllMatches(true);
      
      BuildingSet matches = new BuildingSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Building) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
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
   
   public BuildingPO hasName(String lower, String upper)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Building.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
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
   
   public BuildingPO withName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Building) getCurrentMatch()).setName(value);
      }
      return this;
   }
   
   public FloorPO hasHas()
   {
      FloorPO result = new FloorPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Building.PROPERTY_HAS, result);
      
      return result;
   }

   public BuildingPO hasHas(FloorPO tgt)
   {
      return hasLinkConstraint(tgt, Building.PROPERTY_HAS);
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

