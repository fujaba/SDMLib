package org.sdmlib.examples.studyright.pathes;

import java.util.ArrayList;

public class intPath extends ArrayList<Integer>
{

	private static final long serialVersionUID = -2345886837862490672L;

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
