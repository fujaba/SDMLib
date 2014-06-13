package org.sdmlib.models.classes.util;

import org.sdmlib.models.classes.SDMLibClass;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternObject;

public class SDMLibClassPO extends PatternObject<SDMLibClassPO, SDMLibClass>
{

    public SDMLibClassSet allMatches()
   {
      this.setDoAllMatches(true);
      
      SDMLibClassSet matches = new SDMLibClassSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((SDMLibClass) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public SDMLibClassPO(){
      newInstance(CreatorCreator.createIdMap("PatternObjectType"));
   }

   public SDMLibClassPO(SDMLibClass... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   public SDMLibClassPO hasName(String value)
   {
      new AttributeConstraint()
      .withAttrName(SDMLibClass.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public SDMLibClassPO hasName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(SDMLibClass.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public SDMLibClassPO createName(String value)
   {
      this.startCreate().hasName(value).endCreate();
      return this;
   }
   
   public String getName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((SDMLibClass) getCurrentMatch()).getName();
      }
      return null;
   }
   
   public SDMLibClassPO withName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((SDMLibClass) getCurrentMatch()).withName(value);
      }
      return this;
   }
   
}
