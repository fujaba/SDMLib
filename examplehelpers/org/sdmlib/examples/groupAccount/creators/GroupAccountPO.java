package org.sdmlib.examples.groupAccount.creators;

import org.sdmlib.examples.groupAccount.GroupAccount;
import org.sdmlib.examples.groupAccount.Item;
import org.sdmlib.examples.groupAccount.Person;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.models.pattern.PatternObject;

public class GroupAccountPO extends PatternObject
{
   
   //==========================================================================
   
   public double initAccounts(double p0, String p1)
   {
      if (this.getPattern().getHasMatch())
      {
         return ((GroupAccount) getCurrentMatch()).initAccounts( p0,  p1);
      }
      return 0;
   }

   
   //==========================================================================
   
   public void updateBalances()
   {
      if (this.getPattern().getHasMatch())
      {
          ((GroupAccount) getCurrentMatch()).updateBalances();
      }
   }

   public PersonPO hasPersons()
   {
      PersonPO result = new PersonPO();
      
      PatternLink patternLink = new PatternLink()
      .withTgt(result).withTgtRoleName(GroupAccount.PROPERTY_PERSONS)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().addToElements(result);
      
      this.getPattern().findMatch();
      
      return result;
   }
   
   public GroupAccountPO hasPersons(PersonPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(GroupAccount.PROPERTY_PERSONS)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public GroupAccountPO withPersons(PersonPO tgtPO)
   {
      if (this.getPattern().getHasMatch())
      {
         ((GroupAccount) this.getCurrentMatch()).withPersons((Person) tgtPO.getCurrentMatch());
      }
      return this;
   }
   
   public GroupAccountPO withoutPersons(PersonPO tgtPO)
   {
      if (this.getPattern().getHasMatch())
      {
         ((GroupAccount) this.getCurrentMatch()).withoutPersons((Person) tgtPO.getCurrentMatch());
      }
      return this;
   }
   
   public ItemPO hasItems()
   {
      ItemPO result = new ItemPO();
      
      PatternLink patternLink = new PatternLink()
      .withTgt(result).withTgtRoleName(GroupAccount.PROPERTY_ITEMS)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().addToElements(result);
      
      this.getPattern().findMatch();
      
      return result;
   }
   
   public GroupAccountPO hasItems(ItemPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(GroupAccount.PROPERTY_ITEMS)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public GroupAccountPO withItems(ItemPO tgtPO)
   {
      if (this.getPattern().getHasMatch())
      {
         ((GroupAccount) this.getCurrentMatch()).withItems((Item) tgtPO.getCurrentMatch());
      }
      return this;
   }
   
   public GroupAccountPO withoutItems(ItemPO tgtPO)
   {
      if (this.getPattern().getHasMatch())
      {
         ((GroupAccount) this.getCurrentMatch()).withoutItems((Item) tgtPO.getCurrentMatch());
      }
      return this;
   }
   
   public PersonSet getPersons()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((GroupAccount) this.getCurrentMatch()).getPersons();
      }
      return null;
   }
   
   public ItemSet getItems()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((GroupAccount) this.getCurrentMatch()).getItems();
      }
      return null;
   }
   
}



