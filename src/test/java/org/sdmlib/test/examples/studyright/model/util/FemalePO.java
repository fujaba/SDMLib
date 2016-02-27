package org.sdmlib.test.examples.studyright.model.util;

import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.examples.studyright.model.Female;

public class FemalePO extends PatternObject<FemalePO, Female>
{

    public FemaleSet allMatches()
   {
      this.setDoAllMatches(true);
      
      FemaleSet matches = new FemaleSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Female) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public FemalePO(){
      newInstance(CreatorCreator.createIdMap("PatternObjectType"));
   }

   public FemalePO(Female... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   
   //==========================================================================
   
   public void findMyPosition()
   {
      if (this.getPattern().getHasMatch())
      {
          ((Female) getCurrentMatch()).findMyPosition();
      }
   }

   
   //==========================================================================
   
   public void findMyPosition(String p0)
   {
      if (this.getPattern().getHasMatch())
      {
          ((Female) getCurrentMatch()).findMyPosition(p0);
      }
   }

   
   //==========================================================================
   
   public void findMyPosition(String p0, int p1)
   {
      if (this.getPattern().getHasMatch())
      {
          ((Female) getCurrentMatch()).findMyPosition(p0, p1);
      }
   }

   public FemalePO hasName(String value)
   {
      new AttributeConstraint()
      .withAttrName(Female.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public FemalePO hasName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Female.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public FemalePO createName(String value)
   {
      this.startCreate().hasName(value).endCreate();
      return this;
   }
   
   public String getName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Female) getCurrentMatch()).getName();
      }
      return null;
   }
   
   public FemalePO withName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Female) getCurrentMatch()).setName(value);
      }
      return this;
   }
   
   public FemalePO filterName(String value)
   {
      new AttributeConstraint()
      .withAttrName(Female.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public FemalePO filterName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Female.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
}
