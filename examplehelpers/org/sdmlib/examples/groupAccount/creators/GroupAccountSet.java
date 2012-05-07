package org.sdmlib.examples.groupAccount.creators;

import java.util.LinkedHashSet;
import org.sdmlib.examples.groupAccount.GroupAccount;
import org.sdmlib.examples.groupAccount.Person;
import org.sdmlib.examples.groupAccount.Item;

public class GroupAccountSet extends LinkedHashSet<GroupAccount>
{
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
}

