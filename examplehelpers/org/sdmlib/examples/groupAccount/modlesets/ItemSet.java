package org.sdmlib.examples.groupAccount.modlesets;

import java.util.ArrayList;
import java.util.LinkedHashSet;

import org.sdmlib.examples.groupAccount.Item;

public class ItemSet extends LinkedHashSet<Item>
{

   public doubleList getValue()
   {
      doubleList result = new doubleList();
      
      for (Item item : this)
      {
         result.add(item.getValue());
      }
      return result;
   }

}
