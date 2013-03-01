package org.sdmlib.examples.adamandeve.creators;

import java.util.LinkedHashSet;

import org.sdmlib.examples.adamandeve.Eve;
import org.sdmlib.models.modelsets.StringList;

public class EveSet extends LinkedHashSet<Eve>
{
   public String toString()
   {
      StringList stringList = new StringList();
      
      for (Eve elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }




   public EveSet with(Eve value)
   {
      this.add(value);
      return this;
   }
   
   public EveSet without(Eve value)
   {
      this.remove(value);
      return this;
   }
}



