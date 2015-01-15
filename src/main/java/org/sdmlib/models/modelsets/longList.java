package org.sdmlib.models.modelsets;

import java.util.ArrayList;

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

}
