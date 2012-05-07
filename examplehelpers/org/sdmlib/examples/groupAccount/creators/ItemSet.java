package org.sdmlib.examples.groupAccount.creators;

import java.util.LinkedHashSet;
import org.sdmlib.examples.groupAccount.Item;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.models.modelsets.doubleList;
import org.sdmlib.examples.groupAccount.GroupAccount;
import org.sdmlib.examples.groupAccount.Person;

public class ItemSet extends LinkedHashSet<Item>
{
   public StringList getDescription()
   {
      StringList result = new StringList();
      
      for (Item obj : this)
      {
         result.add(obj.getDescription());
      }
      
      return result;
   }

   public doubleList getValue()
   {
      doubleList result = new doubleList();
      
      for (Item obj : this)
      {
         result.add(obj.getValue());
      }
      
      return result;
   }

   public GroupAccountSet getParent()
   {
      GroupAccountSet result = new GroupAccountSet();
      
      for (Item obj : this)
      {
         result.add(obj.getParent());
      }
      
      return result;
   }
   public PersonSet getBuyer()
   {
      PersonSet result = new PersonSet();
      
      for (Item obj : this)
      {
         result.add(obj.getBuyer());
      }
      
      return result;
   }

}


