package org.sdmlib.examples.groupAccount.creators;

import org.sdmlib.examples.groupAccount.GroupAccount;
import org.sdmlib.examples.groupAccount.Item;
import org.sdmlib.examples.groupAccount.Person;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.models.pattern.PatternObject;

public class PersonPO extends PatternObject
{

   public PersonPO hasName(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Person.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public PersonPO withName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Person) getCurrentMatch()).withName(value);
      }
      return this;
   }
   
   public PersonPO hasBalance(double value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Person.PROPERTY_BALANCE)
      .withTgtValue(value)
      .withSrc(this)
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public PersonPO withBalance(double value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Person) getCurrentMatch()).withBalance(value);
      }
      return this;
   }
   
   public GroupAccountPO hasParent()
   {
      GroupAccountPO result = new GroupAccountPO();
      
      PatternLink patternLink = new PatternLink()
      .withTgt(result).withTgtRoleName(Person.PROPERTY_PARENT)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().addToElements(result);
      
      this.getPattern().findMatch();
      
      return result;
   }
   
   public PersonPO hasParent(GroupAccountPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Person.PROPERTY_PARENT)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public PersonPO withParent(GroupAccountPO tgtPO)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Person) this.getCurrentMatch()).withParent((GroupAccount) tgtPO.getCurrentMatch());
      }
      return this;
   }
   
   public ItemPO hasItems()
   {
      ItemPO result = new ItemPO();
      
      PatternLink patternLink = new PatternLink()
      .withTgt(result).withTgtRoleName(Person.PROPERTY_ITEMS)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().addToElements(result);
      
      this.getPattern().findMatch();
      
      return result;
   }
   
   public PersonPO hasItems(ItemPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Person.PROPERTY_ITEMS)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public PersonPO withItems(ItemPO tgtPO)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Person) this.getCurrentMatch()).withItems((Item) tgtPO.getCurrentMatch());
      }
      return this;
   }
   
   public PersonPO withoutItems(ItemPO tgtPO)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Person) this.getCurrentMatch()).withoutItems((Item) tgtPO.getCurrentMatch());
      }
      return this;
   }
   
   public String getName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Person) getCurrentMatch()).getName();
      }
      return null;
   }
   
   public double getBalance()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Person) getCurrentMatch()).getBalance();
      }
      return 0;
   }
   
   public GroupAccount getParent()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Person) this.getCurrentMatch()).getParent();
      }
      return null;
   }
   
   public ItemSet getItems()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Person) this.getCurrentMatch()).getItems();
      }
      return null;
   }
   
}





