package org.sdmlib.examples.studyright.pathes;

import java.util.ArrayList;

public class intPath extends ArrayList<Integer>
{

   public int sum()
   {
      int result = 0;
      
      for (int x : this)
      {
         result += x;
      }
      
      return result;
   }
   
}
