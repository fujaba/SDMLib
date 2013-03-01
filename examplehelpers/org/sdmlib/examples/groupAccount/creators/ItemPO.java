package org.sdmlib.examples.groupAccount.creators;

import org.sdmlib.examples.groupAccount.GroupAccount;
import org.sdmlib.examples.groupAccount.Item;
import org.sdmlib.examples.groupAccount.Person;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.models.pattern.PatternObject;

public class ItemPO extends PatternObject
{

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
   
   public ItemPO hasDescription(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Item.PROPERTY_DESCRIPTION)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public String getDescription()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Item) getCurrentMatch()).getDescription();
      }
      return null;
   }
   
   public ItemPO hasValue(double value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Item.PROPERTY_VALUE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public double getValue()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Item) getCurrentMatch()).getValue();
      }
      return 0;
   }
   
   public GroupAccount getParent()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Item) this.getCurrentMatch()).getParent();
      }
      return null;
   }
   
   public Person getBuyer()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Item) this.getCurrentMatch()).getBuyer();
      }
      return null;
   }
   
}






