package org.sdmlib.models.modelsets;

import java.util.ArrayList;

public class booleanSet extends ArrayList<Boolean>
{
   private static final long serialVersionUID = 1L;

   public boolean and()
   {
      for (Boolean value : this)
      {
         if (!value)
         {
            return false;
         }
      }

      return true;
   }

   public boolean or()
   {
      for (Boolean value : this)
      {
         if (value)
         {
            return true;
         }
      }

      return false;
   }

}
