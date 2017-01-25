package org.sdmlib.models.modelsets;

import java.util.ArrayList;

import de.uniks.networkparser.interfaces.Condition;

public class longList extends ArrayList<Long>
{
   private static final long serialVersionUID = 1L;

   public long sum()
   {
      long result = 0;

      for (Long value : this)
      {
         result += value;
      }

      return result;
   }

   public long max()
   {
      long max = Long.MIN_VALUE;

      for (long x : this)
      {
         if (x > max)
         {
            max = x;
         }
      }

      return max;
   }

   public long min()
   {
      long min = Long.MAX_VALUE;

      for (long x : this)
      {
         if (x < min)
         {
            min = x;
         }
      }

      return min;
   }
   
   public String toString(String seperator)
   {
      StringBuffer buf = new StringBuffer();
      
      for (int i = 0; i < this.size(); i++)
      {
         buf.append(get(i));
         if (i < this.size()-1)
         {
            buf.append(seperator);
         }
      }
      
      return buf.toString();
   }
   
   public longList filter(Condition<Long> cond) 
   {
      longList result = new longList();
      
      for (long l : this)
      {
         if (cond.update(l))
         {
            result.add(l);
         }
      }
      
      return result;
   }

}
