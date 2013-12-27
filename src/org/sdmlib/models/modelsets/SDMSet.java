package org.sdmlib.models.modelsets;

import java.util.Collections;
import java.util.LinkedHashSet;


public abstract class SDMSet<T> extends LinkedHashSet<T> implements ModelSet 
{
   public String toString()
   {
      StringList stringList = new StringList();
      
      for (T elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }

   public <ST extends SDMSet<T>> ST union(ST other)
   {
      ST result = (ST) this.clone();
      
      result.addAll(other);
      
      return result;
   }
   
   
   public <ST extends SDMSet<T>> ST intersection(ST other)
   {
      ST result = (ST) this.clone();
      
      result.retainAll(other);
      
      return result;
   }
   
   
   public <ST extends SDMSet<T>> ST minus(ST other)
   {
      ST result = (ST) this.clone();
      
      result.removeAll(other);
      
      return result;
   }


}
