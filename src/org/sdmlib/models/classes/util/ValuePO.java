package org.sdmlib.models.classes.util;

import org.sdmlib.models.classes.DataType;
import org.sdmlib.models.classes.Value;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternObject;

public class ValuePO extends PatternObject<ValuePO, Value>
{

    public ValueSet allMatches()
   {
      this.setDoAllMatches(true);
      
      ValueSet matches = new ValueSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Value) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public ValuePO(){
      newInstance(CreatorCreator.createIdMap("PatternObjectType"));
   }

   public ValuePO(Value... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   public ValuePO hasInitialization(String value)
   {
      new AttributeConstraint()
      .withAttrName(Value.PROPERTY_INITIALIZATION)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public ValuePO hasInitialization(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Value.PROPERTY_INITIALIZATION)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public ValuePO createInitialization(String value)
   {
      this.startCreate().hasInitialization(value).endCreate();
      return this;
   }
   
   public String getInitialization()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Value) getCurrentMatch()).getInitialization();
      }
      return null;
   }
   
   public ValuePO withInitialization(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Value) getCurrentMatch()).setInitialization(value);
      }
      return this;
   }
   
   public ValuePO hasType(DataType value)
   {
      new AttributeConstraint()
      .withAttrName(Value.PROPERTY_TYPE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public ValuePO createType(DataType value)
   {
      this.startCreate().hasType(value).endCreate();
      return this;
   }
   
   public DataType getType()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Value) getCurrentMatch()).getType();
      }
      return null;
   }
   
   public ValuePO withType(DataType value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Value) getCurrentMatch()).setType(value);
      }
      return this;
   }
   
   public ValuePO hasName(String value)
   {
      new AttributeConstraint()
      .withAttrName(Value.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public ValuePO hasName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Value.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public ValuePO createName(String value)
   {
      this.startCreate().hasName(value).endCreate();
      return this;
   }
   
   public String getName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Value) getCurrentMatch()).getName();
      }
      return null;
   }
   
   public ValuePO withName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Value) getCurrentMatch()).withName(value);
      }
      return this;
   }
   
}
