package org.sdmlib.simple.model.interface_b.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.simple.model.interface_b.Pupil;
import org.sdmlib.models.pattern.AttributeConstraint;

public class PupilPO extends PatternObject<PupilPO, Pupil>
{

    public PupilSet allMatches()
   {
      this.setDoAllMatches(true);
      
      PupilSet matches = new PupilSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Pupil) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public PupilPO(){
      newInstance(null);
   }

   public PupilPO(Pupil... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }
   public PupilPO filterName(String value)
   {
      new AttributeConstraint()
      .withAttrName(Pupil.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PupilPO filterName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Pupil.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PupilPO createName(String value)
   {
      this.startCreate().filterName(value).endCreate();
      return this;
   }
   
   public String getName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Pupil) getCurrentMatch()).getName();
      }
      return null;
   }
   
   public PupilPO withName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Pupil) getCurrentMatch()).setName(value);
      }
      return this;
   }
   
   
   //==========================================================================
   
   public void think()
   {
      if (this.getPattern().getHasMatch())
      {
          ((Pupil) getCurrentMatch()).think();
      }
   }

   
   //==========================================================================
   
   public void sayName(String p0)
   {
      if (this.getPattern().getHasMatch())
      {
          ((Pupil) getCurrentMatch()).sayName(p0);
      }
   }

}
