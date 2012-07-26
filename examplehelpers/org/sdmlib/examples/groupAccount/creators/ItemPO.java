package org.sdmlib.examples.groupAccount.creators;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.examples.groupAccount.Item;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.examples.groupAccount.creators.GroupAccountPO;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.examples.groupAccount.creators.ItemPO;
import org.sdmlib.examples.groupAccount.GroupAccount;
import org.sdmlib.examples.groupAccount.creators.PersonPO;
import org.sdmlib.examples.groupAccount.Person;

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
   
   public GroupAccountPO hasParent()
   {
      GroupAccountPO result = new GroupAccountPO();
      
      PatternLink patternLink = new PatternLink()
      .withTgt(result).withTgtRoleName(Item.PROPERTY_PARENT)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().addToElements(result);
      
      this.getPattern().findMatch();
      
      return result;
   }
   
   public ItemPO hasParent(GroupAccountPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Item.PROPERTY_PARENT)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public ItemPO withParent(GroupAccountPO tgtPO)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Item) this.getCurrentMatch()).withParent((GroupAccount) tgtPO.getCurrentMatch());
      }
      return this;
   }
   
   public PersonPO hasBuyer()
   {
      PersonPO result = new PersonPO();
      
      PatternLink patternLink = new PatternLink()
      .withTgt(result).withTgtRoleName(Item.PROPERTY_BUYER)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().addToElements(result);
      
      this.getPattern().findMatch();
      
      return result;
   }
   
   public ItemPO hasBuyer(PersonPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Item.PROPERTY_BUYER)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public ItemPO withBuyer(PersonPO tgtPO)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Item) this.getCurrentMatch()).withBuyer((Person) tgtPO.getCurrentMatch());
      }
      return this;
   }
   
}




