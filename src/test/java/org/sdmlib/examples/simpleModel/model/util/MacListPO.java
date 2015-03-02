package org.sdmlib.examples.simpleModel.model.util;

import org.sdmlib.examples.simpleModel.model.MacList;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternObject;

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
      newInstance(org.sdmlib.examples.simpleModel.model.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public MacListPO(MacList... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(org.sdmlib.examples.simpleModel.model.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
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
   
}
