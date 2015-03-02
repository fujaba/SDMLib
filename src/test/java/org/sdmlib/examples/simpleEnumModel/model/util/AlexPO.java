package org.sdmlib.examples.simpleEnumModel.model.util;

import org.sdmlib.examples.simpleEnumModel.model.Alex;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternObject;

public class AlexPO extends PatternObject<AlexPO, Alex>
{

    public AlexSet allMatches()
   {
      this.setDoAllMatches(true);
      
      AlexSet matches = new AlexSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Alex) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public AlexPO(){
      newInstance(org.sdmlib.examples.simpleEnumModel.model.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public AlexPO(Alex... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(org.sdmlib.examples.simpleEnumModel.model.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   public AlexPO hasName(String value)
   {
      new AttributeConstraint()
      .withAttrName(Alex.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public AlexPO hasName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Alex.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public AlexPO createName(String value)
   {
      this.startCreate().hasName(value).endCreate();
      return this;
   }
   
   public String getName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Alex) getCurrentMatch()).getName();
      }
      return null;
   }
   
   public AlexPO withName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Alex) getCurrentMatch()).setName(value);
      }
      return this;
   }
   
}
