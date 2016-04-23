package org.sdmlib.models.modelsets;

import java.util.ArrayList;

public class doubleList extends ArrayList<Double>
{
   private static final long serialVersionUID = 1L;

   /**
    * Loop through the list and sum up its values
    * @return sum of list values
    */
   public double sum()
   {
      double result = 0;

      for (Double value : this)
      {
         result += value;
      }

      return result;
   }

   /**
    * Loop through the list and find the maximal value.
    * @return maximal value or Double.MIN_VALUE, if empty.
    */
   public double max()
   {
      double max = Double.NEGATIVE_INFINITY;

      for (double x : this)
      {
         if (x > max)
         {
            max = x;
         }
      }

      return max;
   }

   /**
    * Loop through the list and find the minimal value.
    * @return minimal value or Double.MAX_VALUE, if empty.
    */
   public double min()
   {
      double min = Double.MAX_VALUE;

      for (double x : this)
      {
         if (x < min)
         {
            min = x;
         }
      }

      return min;
   }

}
