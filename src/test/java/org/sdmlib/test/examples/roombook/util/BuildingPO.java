package org.sdmlib.test.examples.roombook.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.examples.roombook.Building;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.test.examples.roombook.util.FloorPO;
import org.sdmlib.test.examples.roombook.util.BuildingPO;
import org.sdmlib.test.examples.roombook.util.FloorSet;
import org.sdmlib.models.pattern.Pattern;

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


   public BuildingPO(){
      newInstance(null);
   }

   public BuildingPO(Building... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }
   public BuildingPO filterName(String value)
   {
      new AttributeConstraint()
      .withAttrName(Building.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public BuildingPO filterName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Building.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public BuildingPO createName(String value)
   {
      this.startCreate().filterName(value).endCreate();
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
   
   public FloorPO filterHas()
   {
      FloorPO result = new FloorPO(new org.sdmlib.test.examples.roombook.Floor[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Building.PROPERTY_HAS, result);
      
      return result;
   }

   public FloorPO createHas()
   {
      return this.startCreate().filterHas().endCreate();
   }

   public BuildingPO filterHas(FloorPO tgt)
   {
      return hasLinkConstraint(tgt, Building.PROPERTY_HAS);
   }

   public BuildingPO createHas(FloorPO tgt)
   {
      return this.startCreate().filterHas(tgt).endCreate();
   }

   public FloorSet getHas()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Building) this.getCurrentMatch()).getHas();
      }
      return null;
   }


   public BuildingPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public BuildingPO createNameCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(Building.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public BuildingPO createNameCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Building.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public BuildingPO createNameAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(Building.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public FloorPO createHasPO()
   {
      FloorPO result = new FloorPO(new org.sdmlib.test.examples.roombook.Floor[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Building.PROPERTY_HAS, result);
      
      return result;
   }

   public FloorPO createHasPO(String modifier)
   {
      FloorPO result = new FloorPO(new org.sdmlib.test.examples.roombook.Floor[]{});
      
      result.setModifier(modifier);
      super.hasLink(Building.PROPERTY_HAS, result);
      
      return result;
   }

   public BuildingPO createHasLink(FloorPO tgt)
   {
      return hasLinkConstraint(tgt, Building.PROPERTY_HAS);
   }

   public BuildingPO createHasLink(FloorPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Building.PROPERTY_HAS, modifier);
   }

}
