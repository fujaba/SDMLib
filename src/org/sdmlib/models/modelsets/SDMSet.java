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
   
   public T first()
   {
      for (T elem : this)
      {
         return elem;
      }
      
      return null;
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

   public <ST extends SDMSet<T>> ST has(Condition condition)
   {
      ST result = (ST) this.clone();
      
      for (T elem : this)
      {
         if ( ! condition.check(elem))
         {
            result.remove(elem);
         }
      };
      
      return result;
   }
   
   public abstract class Condition
   {
      public abstract boolean check(T elem);
   }

}
