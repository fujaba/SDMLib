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

}
