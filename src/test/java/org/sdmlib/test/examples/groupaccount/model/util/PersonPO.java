package org.sdmlib.test.examples.groupaccount.model.util;

import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.examples.groupaccount.model.Item;
import org.sdmlib.test.examples.groupaccount.model.Party;
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
      newInstance(null);
   }

   public PersonPO(Person... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public PersonPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public PersonPO createNameCondition(String value)
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
   
   public PersonPO createNameCondition(String lower, String upper)
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
   
   public PersonPO createNameAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(Person.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
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
   
   public PersonPO createSaldoCondition(double value)
   {
      new AttributeConstraint()
      .withAttrName(Person.PROPERTY_SALDO)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PersonPO createSaldoCondition(double lower, double upper)
   {
      new AttributeConstraint()
      .withAttrName(Person.PROPERTY_SALDO)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PersonPO createSaldoAssignment(double value)
   {
      new AttributeConstraint()
      .withAttrName(Person.PROPERTY_SALDO)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public double getSaldo()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Person) getCurrentMatch()).getSaldo();
      }
      return 0;
   }
   
   public PersonPO withSaldo(double value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Person) getCurrentMatch()).setSaldo(value);
      }
      return this;
   }
   
   public PersonPO createTotalCondition(double value)
   {
      new AttributeConstraint()
      .withAttrName(Person.PROPERTY_TOTAL)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PersonPO createTotalCondition(double lower, double upper)
   {
      new AttributeConstraint()
      .withAttrName(Person.PROPERTY_TOTAL)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PersonPO createTotalAssignment(double value)
   {
      new AttributeConstraint()
      .withAttrName(Person.PROPERTY_TOTAL)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public double getTotal()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Person) getCurrentMatch()).getTotal();
      }
      return 0;
   }
   
   public PersonPO withTotal(double value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Person) getCurrentMatch()).setTotal(value);
      }
      return this;
   }
   
   public PartyPO createPartyPO()
   {
      PartyPO result = new PartyPO(new Party[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Person.PROPERTY_PARTY, result);
      
      return result;
   }

   public PartyPO createPartyPO(String modifier)
   {
      PartyPO result = new PartyPO(new Party[]{});
      
      result.setModifier(modifier);
      super.hasLink(Person.PROPERTY_PARTY, result);
      
      return result;
   }

   public PersonPO createPartyLink(PartyPO tgt)
   {
      return hasLinkConstraint(tgt, Person.PROPERTY_PARTY);
   }

   public PersonPO createPartyLink(PartyPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Person.PROPERTY_PARTY, modifier);
   }

   public Party getParty()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Person) this.getCurrentMatch()).getParty();
      }
      return null;
   }

   public ItemPO createItemsPO()
   {
      ItemPO result = new ItemPO(new Item[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Person.PROPERTY_ITEMS, result);
      
      return result;
   }

   public ItemPO createItemsPO(String modifier)
   {
      ItemPO result = new ItemPO(new Item[]{});
      
      result.setModifier(modifier);
      super.hasLink(Person.PROPERTY_ITEMS, result);
      
      return result;
   }

   public PersonPO createItemsLink(ItemPO tgt)
   {
      return hasLinkConstraint(tgt, Person.PROPERTY_ITEMS);
   }

   public PersonPO createItemsLink(ItemPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Person.PROPERTY_ITEMS, modifier);
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
