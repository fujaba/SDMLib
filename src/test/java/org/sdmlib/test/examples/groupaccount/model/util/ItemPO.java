package org.sdmlib.test.examples.groupaccount.model.util;

import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.examples.groupaccount.model.Item;
import org.sdmlib.test.examples.groupaccount.model.Person;

public class ItemPO extends PatternObject<ItemPO, Item>
{

    public ItemSet allMatches()
   {
      this.setDoAllMatches(true);
      
      ItemSet matches = new ItemSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Item) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public ItemPO(){
      newInstance(null);
   }

   public ItemPO(Item... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public ItemPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public ItemPO createDescriptionCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(Item.PROPERTY_DESCRIPTION)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ItemPO createDescriptionCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Item.PROPERTY_DESCRIPTION)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ItemPO createDescriptionAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(Item.PROPERTY_DESCRIPTION)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
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
   
   public ItemPO withDescription(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Item) getCurrentMatch()).setDescription(value);
      }
      return this;
   }
   
   public ItemPO createPriceCondition(double value)
   {
      new AttributeConstraint()
      .withAttrName(Item.PROPERTY_PRICE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ItemPO createPriceCondition(double lower, double upper)
   {
      new AttributeConstraint()
      .withAttrName(Item.PROPERTY_PRICE)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ItemPO createPriceAssignment(double value)
   {
      new AttributeConstraint()
      .withAttrName(Item.PROPERTY_PRICE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public double getPrice()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Item) getCurrentMatch()).getPrice();
      }
      return 0;
   }
   
   public ItemPO withPrice(double value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Item) getCurrentMatch()).setPrice(value);
      }
      return this;
   }
   
   public PersonPO createPersonPO()
   {
      PersonPO result = new PersonPO(new Person[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Item.PROPERTY_PERSON, result);
      
      return result;
   }

   public PersonPO createPersonPO(String modifier)
   {
      PersonPO result = new PersonPO(new Person[]{});
      
      result.setModifier(modifier);
      super.hasLink(Item.PROPERTY_PERSON, result);
      
      return result;
   }

   public ItemPO createPersonLink(PersonPO tgt)
   {
      return hasLinkConstraint(tgt, Item.PROPERTY_PERSON);
   }

   public ItemPO createPersonLink(PersonPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Item.PROPERTY_PERSON, modifier);
   }

   public Person getPerson()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Item) this.getCurrentMatch()).getPerson();
      }
      return null;
   }

}
