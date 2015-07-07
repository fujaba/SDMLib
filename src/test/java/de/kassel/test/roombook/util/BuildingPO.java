package de.kassel.test.roombook.util;

import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternObject;

import de.kassel.test.roombook.Building;

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
      newInstance(CreatorCreator.createIdMap("PatternObjectType"));
   }

   public BuildingPO(Building... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   public BuildingPO hasName(String value)
   {
      new AttributeConstraint()
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
      new AttributeConstraint()
      .withAttrName(Building.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public BuildingPO createName(String value)
   {
      this.startCreate().hasName(value).endCreate();
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
      FloorPO result = new FloorPO(new de.kassel.test.roombook.Floor[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Building.PROPERTY_HAS, result);
      
      return result;
   }

   public FloorPO createHas()
   {
      return this.startCreate().hasHas().endCreate();
   }

   public BuildingPO hasHas(FloorPO tgt)
   {
      return hasLinkConstraint(tgt, Building.PROPERTY_HAS);
   }

   public BuildingPO createHas(FloorPO tgt)
   {
      return this.startCreate().hasHas(tgt).endCreate();
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
