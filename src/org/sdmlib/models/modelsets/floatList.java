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

}
