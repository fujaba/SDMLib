package org.sdmlib.examples.adamandeve.creators;

import java.util.LinkedHashSet;

import org.sdmlib.examples.adamandeve.Adam;
import org.sdmlib.models.modelsets.StringList;

public class AdamSet extends LinkedHashSet<Adam>
{
   public String toString()
   {
      StringList stringList = new StringList();
      
      for (Adam elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }


   public AdamSet with(Adam value)
   {
      this.add(value);
      return this;
   }
   
   public AdamSet without(Adam value)
   {
      this.remove(value);
      return this;
   }
}



