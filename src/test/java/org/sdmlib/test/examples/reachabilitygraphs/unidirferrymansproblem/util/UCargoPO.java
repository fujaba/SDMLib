package org.sdmlib.test.examples.reachabilitygraphs.unidirferrymansproblem.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.examples.reachabilitygraphs.unidirferrymansproblem.UCargo;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.Pattern;

public class UCargoPO extends PatternObject<UCargoPO, UCargo>
{

    public UCargoSet allMatches()
   {
      this.setDoAllMatches(true);
      
      UCargoSet matches = new UCargoSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((UCargo) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public UCargoPO(){
      newInstance(null);
   }

   public UCargoPO(UCargo... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public UCargoPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public UCargoPO createNameCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(UCargo.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public UCargoPO createNameCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(UCargo.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public UCargoPO createNameAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(UCargo.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public String getName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((UCargo) getCurrentMatch()).getName();
      }
      return null;
   }
   
   public UCargoPO withName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((UCargo) getCurrentMatch()).setName(value);
      }
      return this;
   }
   
}
