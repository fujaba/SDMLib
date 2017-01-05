package org.sdmlib.test.examples.groupaccount.model.util;

import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.examples.groupaccount.model.GroupAccount;
import org.sdmlib.test.examples.groupaccount.model.Item;
import org.sdmlib.test.examples.groupaccount.model.Person;

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


   public PersonPO(){
      newInstance(org.sdmlib.test.examples.groupaccount.model.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public PersonPO(Person... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(org.sdmlib.test.examples.groupaccount.model.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   public PersonPO hasName(String value)
   {
      new AttributeConstraint()
      .withAttrName(Person.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public PersonPO hasName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Person.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public PersonPO createName(String value)
   {
      this.startCreate().hasName(value).endCreate();
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
      new AttributeConstraint()
      .withAttrName(Person.PROPERTY_BALANCE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public PersonPO hasBalance(double lower, double upper)
   {
      new AttributeConstraint()
      .withAttrName(Person.PROPERTY_BALANCE)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public PersonPO createBalance(double value)
   {
      this.startCreate().hasBalance(value).endCreate();
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
      GroupAccountPO result = new GroupAccountPO(new GroupAccount[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Person.PROPERTY_PARENT, result);
      
      return result;
   }

   public GroupAccountPO createParent()
   {
      return this.startCreate().hasParent().endCreate();
   }

   public PersonPO hasParent(GroupAccountPO tgt)
   {
      return hasLinkConstraint(tgt, Person.PROPERTY_PARENT);
   }

   public PersonPO createParent(GroupAccountPO tgt)
   {
      return this.startCreate().hasParent(tgt).endCreate();
   }

   public GroupAccount getParent()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Person) this.getCurrentMatch()).getParent();
      }
      return null;
   }

   public ItemPO hasItem()
   {
      ItemPO result = new ItemPO(new Item[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Person.PROPERTY_ITEM, result);
      
      return result;
   }

   public ItemPO createItem()
   {
      return this.startCreate().hasItem().endCreate();
   }

   public PersonPO hasItem(ItemPO tgt)
   {
      return hasLinkConstraint(tgt, Person.PROPERTY_ITEM);
   }

   public PersonPO createItem(ItemPO tgt)
   {
      return this.startCreate().hasItem(tgt).endCreate();
   }

   public ItemSet getItem()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Person) this.getCurrentMatch()).getItem();
      }
      return null;
   }

   public PersonPO filterName(String value)
   {
      new AttributeConstraint()
      .withAttrName(Person.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PersonPO filterName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Person.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PersonPO filterBalance(double value)
   {
      new AttributeConstraint()
      .withAttrName(Person.PROPERTY_BALANCE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PersonPO filterBalance(double lower, double upper)
   {
      new AttributeConstraint()
      .withAttrName(Person.PROPERTY_BALANCE)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public GroupAccountPO filterParent()
   {
      GroupAccountPO result = new GroupAccountPO(new GroupAccount[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Person.PROPERTY_PARENT, result);
      
      return result;
   }

   public PersonPO filterParent(GroupAccountPO tgt)
   {
      return hasLinkConstraint(tgt, Person.PROPERTY_PARENT);
   }

   public ItemPO filterItem()
   {
      ItemPO result = new ItemPO(new Item[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Person.PROPERTY_ITEM, result);
      
      return result;
   }

   public PersonPO filterItem(ItemPO tgt)
   {
      return hasLinkConstraint(tgt, Person.PROPERTY_ITEM);
   }

}
