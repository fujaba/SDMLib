package org.sdmlib.examples.groupAccount.creators;

import java.util.LinkedHashSet;

import org.sdmlib.examples.groupAccount.GroupAccount;
import org.sdmlib.examples.groupAccount.Person;
import org.sdmlib.examples.groupAccount.Item;
import org.sdmlib.models.modelsets.doubleList;

public class GroupAccountSet extends LinkedHashSet<GroupAccount>
{
	private static final long serialVersionUID = 1L;

	public PersonSet getPersons()
   {
      PersonSet result = new PersonSet();
      
      for (GroupAccount obj : this)
      {
         result.addAll(obj.getPersons());
      }
      
      return result;
   }

	public ItemSet getItems()
   {
      ItemSet result = new ItemSet();
      
      for (GroupAccount obj : this)
      {
         result.addAll(obj.getItems());
      }
      
      return result;
   }

   public GroupAccountSet withPersons(Person value)
   {
      for (GroupAccount obj : this)
      {
         obj.withPersons(value);
      }
      
      return this;
   }

   public GroupAccountSet withItems(Item value)
   {
      for (GroupAccount obj : this)
      {
         obj.withItems(value);
      }
      
      return this;
   }

   public GroupAccountSet withoutPersons(Person value)
   {
      for (GroupAccount obj : this)
      {
         obj.withoutPersons(value);
      }
      
      return this;
   }

   public GroupAccountSet withoutItems(Item value)
   {
      for (GroupAccount obj : this)
      {
         obj.withoutItems(value);
      }
      
      return this;
   }

   
   //==========================================================================
   
   public GroupAccountSet updateBalances()
   {
      for (GroupAccount obj : this)
      {
         obj.updateBalances();
      }
      return this;
   }


   
   //==========================================================================
   
   public doubleList initAccounts(double p0, String p1)
   {
      doubleList result = new doubleList();
      for (GroupAccount obj : this)
      {
         result.add(obj.initAccounts( p0,  p1));
      }
      return result;
   }

}












