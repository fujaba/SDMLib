package org.sdmlib.examples.studyrightextends.creators;

import java.util.LinkedHashSet;
import org.sdmlib.examples.studyrightextends.Male;
import org.sdmlib.models.modelsets.StringList;

public class MaleSet extends LinkedHashSet<Male>
{


   public String toString()
   {
      StringList stringList = new StringList();
      
      for (Male elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }


   public MaleSet with(Male value)
   {
      this.add(value);
      return this;
   }
   
   public MaleSet without(Male value)
   {
      this.remove(value);
      return this;
   }
}


