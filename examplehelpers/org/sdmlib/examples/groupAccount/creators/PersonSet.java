package org.sdmlib.examples.groupAccount.creators;

import java.util.LinkedHashSet;

import org.sdmlib.examples.groupAccount.Person;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.models.modelsets.doubleList;
import java.util.List;
import org.sdmlib.examples.groupAccount.GroupAccount;
import org.sdmlib.examples.groupAccount.Item;

public class PersonSet extends LinkedHashSet<Person>
{
   private static final long serialVersionUID = 1L;
   public StringList getName()
   {
      StringList result = new StringList();
      
      for (Person obj : this)
      {
         result.add(obj.getName());
      }
      
      return result;
   }

   public doubleList getBalance()
   {
      doubleList result = new doubleList();
      
      for (Person obj : this)
      {
         result.add(obj.getBalance());
      }
      
      return result;
   }

   public GroupAccountSet getParent()
   {
      GroupAccountSet result = new GroupAccountSet();
      
      for (Person obj : this)
      {
         result.add(obj.getParent());
      }
      
      return result;
   }
   public ItemSet getItems()
   {
      ItemSet result = new ItemSet();
      
      for (Person obj : this)
      {
         result.addAll(obj.getItems());
      }
      
      return result;
   }
   
   public PersonSet setParent(GroupAccount value)
   {
      for (Person obj : this)
      {
         obj.setParent(value);
      }
      
      return this;
   }

   public PersonSet withName(String value)
   {
      for (Person obj : this)
      {
         obj.withName(value);
      }
      
      return this;
   }

   public PersonSet withBalance(double value)
   {
      for (Person obj : this)
      {
         obj.withBalance(value);
      }
      
      return this;
   }

   public PersonSet withParent(GroupAccount value)
   {
      for (Person obj : this)
      {
         obj.withParent(value);
      }
      
      return this;
   }

   public PersonSet withItems(Item value)
   {
      for (Person obj : this)
      {
         obj.withItems(value);
      }
      
      return this;
   }

   public PersonSet withoutItems(Item value)
   {
      for (Person obj : this)
      {
         obj.withoutItems(value);
      }
      
      return this;
   }

}
