package org.sdmlib.test.examples.groupaccount.model.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.examples.groupaccount.model.GroupAccount;
import org.sdmlib.test.examples.groupaccount.model.Item;
import org.sdmlib.test.examples.groupaccount.model.Person;

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


   public GroupAccountPO(){
      newInstance(org.sdmlib.test.examples.groupaccount.model.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public GroupAccountPO(GroupAccount... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(org.sdmlib.test.examples.groupaccount.model.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   
   //==========================================================================
   
   public double getTaskNames(double p0, String p1)
   {
      if (this.getPattern().getHasMatch())
      {
         return ((GroupAccount) getCurrentMatch()).getTaskNames(p0, p1);
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
      PersonPO result = new PersonPO(new Person[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(GroupAccount.PROPERTY_PERSONS, result);
      
      return result;
   }

   public PersonPO createPersons()
   {
      return this.startCreate().hasPersons().endCreate();
   }

   public GroupAccountPO hasPersons(PersonPO tgt)
   {
      return hasLinkConstraint(tgt, GroupAccount.PROPERTY_PERSONS);
   }

   public GroupAccountPO createPersons(PersonPO tgt)
   {
      return this.startCreate().hasPersons(tgt).endCreate();
   }

   public PersonSet getPersons()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((GroupAccount) this.getCurrentMatch()).getPersons();
      }
      return null;
   }

   public ItemPO hasItem()
   {
      ItemPO result = new ItemPO(new Item[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(GroupAccount.PROPERTY_ITEM, result);
      
      return result;
   }

   public ItemPO createItem()
   {
      return this.startCreate().hasItem().endCreate();
   }

   public GroupAccountPO hasItem(ItemPO tgt)
   {
      return hasLinkConstraint(tgt, GroupAccount.PROPERTY_ITEM);
   }

   public GroupAccountPO createItem(ItemPO tgt)
   {
      return this.startCreate().hasItem(tgt).endCreate();
   }

   public ItemSet getItem()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((GroupAccount) this.getCurrentMatch()).getItem();
      }
      return null;
   }

}
