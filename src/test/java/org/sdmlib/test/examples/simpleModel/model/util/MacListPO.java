package org.sdmlib.test.examples.simpleModel.model.util;

import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.examples.simpleModel.model.MacList;

public class MacListPO extends PatternObject<MacListPO, MacList>
{

    public MacListSet allMatches()
   {
      this.setDoAllMatches(true);
      
      MacListSet matches = new MacListSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((MacList) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public MacListPO(){
      newInstance(org.sdmlib.test.examples.simpleModel.model.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public MacListPO(MacList... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(org.sdmlib.test.examples.simpleModel.model.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   public MacListPO hasName(String value)
   {
      new AttributeConstraint()
      .withAttrName(MacList.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public MacListPO hasName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(MacList.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public MacListPO createName(String value)
   {
      this.startCreate().hasName(value).endCreate();
      return this;
   }
   
   public String getName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((MacList) getCurrentMatch()).getName();
      }
      return null;
   }
   
   public MacListPO withName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((MacList) getCurrentMatch()).setName(value);
      }
      return this;
   }
   
   public MacListPO filterSerialVersionUID(long value)
   {
      new AttributeConstraint()
      .withAttrName(MacList.PROPERTY_SERIALVERSIONUID)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public MacListPO filterSerialVersionUID(long lower, long upper)
   {
      new AttributeConstraint()
      .withAttrName(MacList.PROPERTY_SERIALVERSIONUID)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public MacListPO createSerialVersionUID(long value)
   {
      this.startCreate().filterSerialVersionUID(value).endCreate();
      return this;
   }
   
   public long getSerialVersionUID()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((MacList) getCurrentMatch()).getSerialVersionUID();
      }
      return 0;
   }
   
   public MacListPO withSerialVersionUID(long value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((MacList) getCurrentMatch()).setSerialVersionUID(value);
      }
      return this;
   }
   
   public MacListPO filterName(String value)
   {
      new AttributeConstraint()
      .withAttrName(MacList.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public MacListPO filterName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(MacList.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
}
