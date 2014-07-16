package org.sdmlib.models.modelsets;

import java.util.ArrayList;

public class doubleList extends ArrayList<Double>
{
   private static final long serialVersionUID = 1L;

   public double sum()
   {
      double result = 0;

      for (Double value : this)
      {
         result += value;
      }

      return result;
   }

   public double max()
   {
      double max = 0;

      for (double x : this)
      {
         if (x > max)
         {
            max = x;
         }
      }

      return max;
   }

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
