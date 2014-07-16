package org.sdmlib.models.modelsets;

import java.util.ArrayList;

public class floatList extends ArrayList<Float>
{
   private static final long serialVersionUID = 1L;

   public double sum()
   {
      double result = 0;

      for (Float value : this)
      {
         result += value;
      }

      return result;
   }

   public float max()
   {
      float max = 0;

      for (float x : this)
      {
         if (x > max)
         {
            max = x;
         }
      }

      return max;
   }

   public float min()
   {
      float min = Float.MAX_VALUE;

      for (float x : this)
      {
         if (x < min)
         {
            min = x;
         }
      }

      return min;
   }

}
