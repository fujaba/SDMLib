package org.sdmlib.examples.studyright.model.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.examples.studyright.model.Female;
import org.sdmlib.examples.studyright.model.util.FemaleSet;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.AttributeConstraint;

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
      Pattern<Object> pattern = new Pattern<Object>(CreatorCreator.createIdMap("PatternObjectType"));
      pattern.addToElements(this);
   }

   public FemalePO(Female... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
          return;
      }
      Pattern<Object> pattern = new Pattern<Object>(CreatorCreator.createIdMap("PatternObjectType"));
      pattern.addToElements(this);
      if(hostGraphObject.length>1){
           this.withCandidates(hostGraphObject);
      } else {
           this.withCandidates(hostGraphObject[0]);
      }
      pattern.findMatch();
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
   
}

