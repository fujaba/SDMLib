package org.sdmlib.examples.groupAccount.creators;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.examples.groupAccount.Item;

public class ItemPO extends PatternObject
{

   public ItemPO hasDescription(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Item.PROPERTY_DESCRIPTION)
      .withTgtValue(value)
      .withSrc(this)
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public ItemPO withDescription(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Item) getCurrentMatch()).withDescription(value);
      }
      return this;
   }
   
   public ItemPO hasValue(double value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Item.PROPERTY_VALUE)
      .withTgtValue(value)
      .withSrc(this)
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public ItemPO withValue(double value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Item) getCurrentMatch()).withValue(value);
      }
      return this;
   }
   
}



