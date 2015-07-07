package org.sdmlib.test.examples.simpleEnumModel.model.util;

import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.examples.simpleEnumModel.model.Mac;

public class MacPO extends PatternObject<MacPO, Mac>
{

    public MacSet allMatches()
   {
      this.setDoAllMatches(true);
      
      MacSet matches = new MacSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Mac) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public MacPO(){
      newInstance(org.sdmlib.test.examples.simpleEnumModel.model.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public MacPO(Mac... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(org.sdmlib.test.examples.simpleEnumModel.model.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   public MacPO hasName(String value)
   {
      new AttributeConstraint()
      .withAttrName(Mac.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public MacPO hasName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Mac.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public MacPO createName(String value)
   {
      this.startCreate().hasName(value).endCreate();
      return this;
   }
   
   public String getName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Mac) getCurrentMatch()).getName();
      }
      return null;
   }
   
   public MacPO withName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Mac) getCurrentMatch()).setName(value);
      }
      return this;
   }
   
}
