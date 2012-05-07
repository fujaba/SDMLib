package org.sdmlib.models.modelsets;

import java.util.ArrayList;

public class doubleList extends ArrayList<Double>
{

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
