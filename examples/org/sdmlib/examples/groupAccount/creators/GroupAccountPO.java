package org.sdmlib.examples.groupAccount.creators;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.examples.groupAccount.GroupAccount;
import org.sdmlib.examples.groupAccount.creators.GroupAccountSet;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.examples.groupAccount.creators.PersonPO;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.examples.groupAccount.creators.GroupAccountPO;
import org.sdmlib.examples.groupAccount.Person;
import org.sdmlib.examples.groupAccount.creators.PersonSet;
import org.sdmlib.examples.groupAccount.creators.ItemPO;
import org.sdmlib.examples.groupAccount.Item;
import org.sdmlib.examples.groupAccount.creators.ItemSet;

public class GroupAccountPO extends PatternObject<GroupAccountPO, GroupAccount>
{
   public GroupAccountSet allMatches()
   {
      this.setDoAllMatches(true);
      
      GroupAccountSet matches = new GroupAccountSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((GroupAccount) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }
   
   
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
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(GroupAccount.PROPERTY_PERSONS, result);
      
      return result;
   }

   public GroupAccountPO hasPersons(PersonPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(GroupAccount.PROPERTY_PERSONS)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
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

   public ItemPO hasItems()
   {
      ItemPO result = new ItemPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(GroupAccount.PROPERTY_ITEMS, result);
      
      return result;
   }

   public GroupAccountPO hasItems(ItemPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(GroupAccount.PROPERTY_ITEMS)
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
         return ((GroupAccount) this.getCurrentMatch()).getItems();
      }
      return null;
   }

   public PersonPO createPersons()
   {
      return this.startCreate().hasPersons().endCreate();
   }

   public GroupAccountPO createPersons(PersonPO tgt)
   {
      return this.startCreate().hasPersons(tgt).endCreate();
   }

   public ItemPO createItems()
   {
      return this.startCreate().hasItems().endCreate();
   }

   public GroupAccountPO createItems(ItemPO tgt)
   {
      return this.startCreate().hasItems(tgt).endCreate();
   }

}


