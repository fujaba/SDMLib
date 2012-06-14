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

}
