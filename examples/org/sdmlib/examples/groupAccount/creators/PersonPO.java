package org.sdmlib.examples.groupAccount.creators;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.examples.groupAccount.Person;
import org.sdmlib.examples.groupAccount.creators.PersonSet;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.examples.groupAccount.creators.GroupAccountPO;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.examples.groupAccount.creators.PersonPO;
import org.sdmlib.examples.groupAccount.GroupAccount;
import org.sdmlib.examples.groupAccount.creators.ItemPO;
import org.sdmlib.examples.groupAccount.Item;
import org.sdmlib.examples.groupAccount.creators.ItemSet;

public class PersonPO extends PatternObject<PersonPO, Person>
{
   public PersonSet allMatches()
   {
      this.setDoAllMatches(true);
      
      PersonSet matches = new PersonSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Person) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }
   
   public PersonPO hasName(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Person.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
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
   
   public PersonPO withName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Person) getCurrentMatch()).setName(value);
      }
      return this;
   }
   
   public PersonPO hasBalance(double value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Person.PROPERTY_BALANCE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public double getBalance()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Person) getCurrentMatch()).getBalance();
      }
      return 0;
   }
   
   public PersonPO withBalance(double value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Person) getCurrentMatch()).setBalance(value);
      }
      return this;
   }
   
   public GroupAccountPO hasParent()
   {
      GroupAccountPO result = new GroupAccountPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Person.PROPERTY_PARENT, result);
      
      return result;
   }

   public PersonPO hasParent(GroupAccountPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Person.PROPERTY_PARENT)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }

   public GroupAccount getParent()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Person) this.getCurrentMatch()).getParent();
      }
      return null;
   }

   public ItemPO hasItems()
   {
      ItemPO result = new ItemPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Person.PROPERTY_ITEMS, result);
      
      return result;
   }

   public PersonPO hasItems(ItemPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Person.PROPERTY_ITEMS)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }

   public ItemSet getItems()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Person) this.getCurrentMatch()).getItems();
      }
      return null;
   }

   public PersonPO hasName(String lower, String upper)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Person.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public PersonPO hasBalance(double lower, double upper)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Person.PROPERTY_BALANCE)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
}


